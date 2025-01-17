package org.example.practice_platform_backend.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.practice_platform_backend.entity.Community;
import org.example.practice_platform_backend.entity.CommunityNeed;
import org.example.practice_platform_backend.entity.FruitMedia;
import org.example.practice_platform_backend.entity.User;
import org.example.practice_platform_backend.mapper.*;
import org.example.practice_platform_backend.utils.FFmpegUtils;
import org.example.practice_platform_backend.utils.ImageUtils;
import org.example.practice_platform_backend.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Arrays.asList;

@Service
public class SaveFileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveFileService.class);
    // 上传的路径
    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FruitMapper fruitMapper;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private NeedMapper needMapper;

    @Autowired
    private ImageUtils imageUtils;


    private final List<String> typeMap = asList("community","need","fruit");

    private final Map<String, Function<Integer, String>> existsVideoMap;
    private final Map<String, BiFunction<String, Integer, Boolean>> addVideoMap;
    private final Map<String, BiFunction<String, Integer, Boolean>> ModifyVideoMap;
    private final Map<String,Function<Integer, Integer>> existsImageMap;
    private final Map<String, Function<Integer, Integer>> existsCoverMap;
//    private final Map<String, BiFunction<String, Integer, Boolean>> addImageMap;
//    private final Map<String, BiFunction<String, Integer, Object>> addCoverMap;
    private final Map<String, Function<Integer, Boolean>> deleteImageMap;
    private final Map<String, Function<Integer, String>> searchImageMap;


    public SaveFileService(CommunityMapper communityMapper,NeedMapper needMapper, FruitMapper fruitMapper) {
        // 初始化映射，这部分依赖注入或在构造函数中设置
        existsVideoMap = Map.of(
                "community", communityMapper::existsCommunityVideo,
                "need", needMapper::existsNeedVideo,
                "fruit", fruitMapper::existsFruitVideo
        );
        addVideoMap = Map.of(
                "community", communityMapper::addCommunityVideo,
                "need", needMapper::addNeedVideo,
                "fruit", fruitMapper::addFruitVideo
        );
        ModifyVideoMap = Map.of(
                "community", (m3u8Path, id) -> communityMapper.deleteCommunityVideo(id) && communityMapper.addCommunityVideo(m3u8Path, id),
                "need", (m3u8Path, id) -> needMapper.deleteNeedVideo(id) && needMapper.addNeedVideo(m3u8Path, id),
                "fruit", (m3u8Path, id) -> fruitMapper.deleteFruitVideo(id) && fruitMapper.addFruitVideo(m3u8Path, id)
        );
        existsImageMap = Map.of(
                "community", communityMapper::existsCommunityImage,
                "need", needMapper::existsNeedImage,
                "fruit", fruitMapper::existsFruitImage
        );
        existsCoverMap= Map.of(
                "community", communityMapper::existsCommunityCover,
                "need", needMapper::existsNeedCover,
                "fruit", fruitMapper::existsFruitCover
        );

//        addImageMap= Map.of(
//                "community", communityMapper::addCommunityImage,
//                "need", needMapper::addNeedImage,
//                "fruit", fruitMapper::addFruitImage
//        );
//        addCoverMap= Map.of(
//                "community", communityMapper::addCommunityCover,
//                "need", needMapper::addNeedCover,
//                "fruit", fruitMapper::addFruitCover
//        );
        deleteImageMap = Map.of(
                "community", communityMapper::deleteCommunityImage,
                "need", needMapper::deleteNeedImage,
                "fruit", fruitMapper::deleteFruitImage
        );
        searchImageMap = Map.of(
                "community", communityMapper::getCommunityMediaPathByMediaId,
                "need", needMapper::getNeedMediaPathByMediaId,
                "fruit", fruitMapper::getFruitMediaPathByMediaId
        );
    }

    // 处理保存操作
    public void saveAvatar(MultipartFile file, int user_id) throws IOException {
        // 把传进来的 MultipartFile 文件转换成 File 并创建临时文件
        String originalFilename = file.getOriginalFilename();
        String suffix = ".jpg";
        String thumbnailFileName = "avatar/" + user_id + "_avatar" + suffix ;
        String fileName ="avatar/" + user_id + "_avatar" + "_origin" + suffix ;
        String fileDir = uploadPath;
        //保存原图
        saveBytesFile(file.getInputStream(),fileDir,fileName);
        //保存缩略图
        imageUtils.photoSmaller(file.getInputStream(),fileDir+thumbnailFileName);
        //将头像名称保存到数据库中
        userMapper.updateAvatar(user_id, thumbnailFileName);
        CompletableFuture.completedFuture(true);
    }

    // 处理保存社区头像操作
    public void saveCommunityAvatar(MultipartFile file, int id) throws IOException {
        // 把传进来的 MultipartFile 文件转换成 File 并创建临时文件
        String suffix="jpg";
        String fileName ="community_avatar/" + id + "_avatar." + suffix ;
        String fileDir = uploadPath;
        //保存原图
        saveBytesFile(file.getInputStream(),fileDir,fileName);
        //将头像名称保存到数据库中
        communityMapper.updateCommunityAvatar(fileName,id);
        CompletableFuture.completedFuture(true);
    }

    @Transactional
    // 保存图片
    public ResponseEntity<String> saveImage(MultipartFile file, int id, int index,boolean isCover){

        String type = typeMap.get(index);

        if(existsImageMap.get(type).apply(id)>6){
            return ResponseEntity.status(400).body("图片超过限制");
        }
        if(isCover&&existsCoverMap.get(type).apply(id)>0){
            return ResponseEntity.status(400).body("不能有多张封面");
        }
        try{
            // 保存文件的逻辑
            String filename = ImageUtils.getUUIDName(Objects.requireNonNull(file.getOriginalFilename()));
            String imagePath = type + "_images/" + type + "_" + id + "/";
            saveBytesFile(file.getInputStream(),uploadPath+imagePath,filename);
            // 记录到数据库
            int media_id=0;
            if(!isCover){
                if(index==0){
                    Community.media media = new Community.media();
                    media.setPath(imagePath+filename);
                    media.setCommunity_id(id);
                    communityMapper.addCommunityImage(media);
                    media_id = media.getMedia_id();
                }
                if(index==1){
                    CommunityNeed.media media = new CommunityNeed.media();
                    media.setPath(imagePath+filename);
                    media.setNeed_id(id);
                    needMapper.addNeedImage(media);
                    media_id = media.getMedia_id();
                }
                if(index==2){
                    FruitMedia media = new FruitMedia();
                    media.setPath(imagePath+filename);
                    media.setFruit_id(id);
                    fruitMapper.addFruitImage(media);
                    media_id = media.getMedia_id();
                }
                if(media_id == 0){
                    return ResponseEntity.status(400).body("添加图片失败");
                }
            }
            else{
                if(index==0){
                    Community.media media = new Community.media();
                    media.setPath(imagePath+filename);
                    media.setCommunity_id(id);
                    communityMapper.addCommunityCover(media);
                    media_id = media.getMedia_id();
                }
                if(index==1){
                    CommunityNeed.media media = new CommunityNeed.media();
                    media.setPath(imagePath+filename);
                    media.setNeed_id(id);
                    needMapper.addNeedCover(media);
                    media_id = media.getMedia_id();
                }
                if(index==2){
                    FruitMedia media = new FruitMedia();
                    media.setPath(imagePath+filename);
                    media.setFruit_id(id);
                    fruitMapper.addFruitCover(media);
                    media_id = media.getMedia_id();
                }
                if(media_id == 0){
                    return ResponseEntity.status(400).body("添加封面失败");
                }
            }
            return ResponseEntity.status(200).header("id", String.valueOf(media_id)).body("添加图片成功");

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(400).body("出现异常");
        }
    }

    @Transactional
    public Boolean deleteImage(int id,int media_id,int index) throws IOException {
        String type = typeMap.get(index);
        String fileName = searchImageMap.get(type).apply(media_id);
        if(fileName!=null){
            fileName = ImageUtils.getRealName(fileName);
            String filePath;
            //处理默认封面的情况
            if(fileName.equals("default.jpg")){
                deleteImageMap.get(type).apply(media_id);
                return true;
            }else {
                filePath = uploadPath + type + "_images/" + type + "_" + id + "/" + fileName;
                // 将字符串路径转换为 Path 对象
                Path path = Paths.get(filePath);
                // 删除文件
                Files.delete(path);
                deleteImageMap.get(type).apply(media_id);
                return true;
            }
        }
        return false;
    }

    @Transactional
    // 修改图片
    public ResponseEntity<String> modifyImage(MultipartFile file, int id, int media_id, int index, Boolean isCover) throws IOException {
        if(!deleteImage(id,media_id,index)){
            return ResponseEntity.status(400).body("图片删除失败，不存在目标图片");
        }
        return saveImage(file,id,index,isCover);
    }

    /**
     * 保存二进制文件
     * @param inputStream 视频的输入流
     * @param filepath 视频的路径
     * @param filename 视频的名字
     * @return 是否执行成功
     */
    public boolean saveBytesFile(InputStream inputStream, String filepath, String filename) throws IOException {
        File file = new File(filepath, filename);

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return false;
        }

        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();
        return true;
    }

    /**
     * 保存m3u8视频的完整操作
     */
    public String handleVideo(InputStream inputStream, String filename, int id, String type,int typeIndex) throws IOException {
        // 相对路径（需要返回这个，避免暴露服务器的文件结构）
        String relatedPath = type + "_video/" + type + "_" + id + "/";
        // 视频文件保存的绝对路径
        String originPath = uploadPath + relatedPath;
        // 生成的随机名字
        String videoName = ImageUtils.getUUIDName(filename);
        //获取到原视频名字的后缀
        int index = videoName.lastIndexOf(".");
        // m3u8的文件名字，更改后缀
        String m3u8Name =  videoName.substring(0,index) + ".m3u8";
        // m3u8的完整文件路径
        String m3u8Path = originPath  + "m3u8/" + m3u8Name;
        // 如果不存在该路径就创建一个
        Path path = Paths.get(m3u8Path);
        Path directoryPath = path.getParent();
        if (Files.notExists(directoryPath)){
            try{
                // 用 createDirectories 而非是 createDirectory 创建所有目录
                Files.createDirectories(directoryPath);
            }catch (IOException e){
                e.fillInStackTrace();
            }
        }
        // 视频文件的路径
        String videoPath = originPath  + "video/";
        boolean isSave = saveBytesFile(inputStream,videoPath,videoName);
        if(!isSave){
            return null;
        }
        // 转换成m3u8
        boolean isConvert = FFmpegUtils.convert(videoPath+videoName,m3u8Path,typeIndex,id);
        if(!isConvert){
            return null;
        }
        return m3u8Name;
    }

    /**
     * 保存视频
     * m3u8只是一个处理格式，原视频还是要保存的，保存路径（成果为例）:fruit_video/fruit_{id}/video; fruit_video/fruit_{id}/m3u8
     * @param inputStream 视频的输入流
     * @param filename 视频的名字
     * @param id 需求/成果的 id
     * @param index 说明是社区、需求还是成果
     * @return boolean 的事务执行结果
     */
    @Transactional
    public ResponseEntity<String> saveVideo(InputStream inputStream, String filename, int id, Integer index) throws IOException {
        String type = typeMap.get(index);

        // Check if video exists
        if (existsVideoMap.get(type).apply(id)!=null) {
            return ResponseEntity.status(400).body("已存在视频");
        }
        // Handle video processing
        String m3u8Path = handleVideo(inputStream, filename, id, type,index);
        if (m3u8Path == null || m3u8Path.isEmpty()) {
            return ResponseEntity.status(400).body("已存在m3u8");
        }
        // Add video
        if(!addVideoMap.get(type).apply(m3u8Path, id)){
            return ResponseEntity.status(400).body("添加视频失败");
        }
        return ResponseEntity.status(200).body("视频上传成功");
    }

    public void deleteVideo(String path) throws IOException {
        Path fullPath = Paths.get(path);
        Path directoryPath = fullPath.getParent(); // 获取文件所在目录
        cleanDirectoryContents(directoryPath);
    }

    /**
     * 修改视频
     * @param inputStream 视频的输入流
     * @param filename 视频的名字
     * @param id 需求/成果的 id
     * @param index 说明是社区、需求还是成果
     * @return boolean 事务执行结果
     */
    @Transactional
    public ResponseEntity<String> modifyVideo(InputStream inputStream, String filename, int id, Integer index) throws IOException {
        String type = typeMap.get(index);
        // 删掉视频的 m3u8 目录
        String absolutePath = existsVideoMap.get(type).apply(id);
        if(absolutePath==null){
            return ResponseEntity.status(400).body("不存在m3u8目录");
        }
        String origin_path = uploadPath + type + "_video/" + type + "_" + id + "/" + absolutePath;
        deleteVideo(origin_path);
        // 处理视频并获取新的m3u8路径
        String m3u8Path = handleVideo(inputStream, filename, id, type,index);
        if (m3u8Path == null || m3u8Path.isEmpty()) {
            return ResponseEntity.status(400).body("更新m3u8目录失败");
        }
        if(!ModifyVideoMap.getOrDefault(type, (p, i) -> false).apply(m3u8Path, id)){
            return ResponseEntity.status(400).body("修改视频失败");
        }
        return ResponseEntity.status(200).body("视频上传成功");
    }

    /**
     * 清空指定目录下的所有内容，但不删除该目录本身。
     * @param dirPath 需要被清空的目录路径。
     */
    private void cleanDirectoryContents(Path dirPath) throws IOException {
        try (var paths = Files.walk(dirPath)) {
            paths
                    .sorted(Comparator.reverseOrder())  // 先删除子目录和文件
                    .filter(p -> !p.equals(dirPath))    // 跳过根目录本身
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            e.fillInStackTrace();
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    public ResponseEntity<String> privilegeCheck(Integer type, Integer id, boolean isModify, HttpServletRequest request){
        User user = jwtUtils.getUserInfoFromToken(request.getHeader("token"),User.class);
        if(Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        int user_id = user.getUser_id();
        String identity = user.getUser_category();

        // 如果是学生或老师，只让他修改自己发布的成果
        if((Objects.equals(identity, "student"))||(Objects.equals(identity, "teacher"))){
            if(!isModify && type!=2){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("该身份只允许发布和修改成果");
            }

            if(isModify && !fruitMapper.getFruitIdByUserId(user_id).contains(id)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("不是该用户发表的成果");
            }
        }

        // 如果是社区负责人，只能修改他自己的社区视频或者修改他自己社区的需求
        if(Objects.equals(identity, "community")){
            if(!isModify && type == 2){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("该身份不允许发布成果");
            }
            if(isModify && type == 0 && !Objects.equals(id, communityMapper.findCommunityIdByUserId(user_id))){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("不是该用户所属的社区");
            }
            if(isModify && type==1&&!needMapper.selectNeedByUserId(user_id).contains(id)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("不是该用户发布的需求");
            }
        }
        return ResponseEntity.status(200).build();
    }
}
