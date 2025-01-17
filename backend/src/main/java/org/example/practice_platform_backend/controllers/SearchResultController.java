package org.example.practice_platform_backend.controllers;

import com.alibaba.fastjson.JSONObject;
import org.example.practice_platform_backend.entity.CommunityNeed;
import org.example.practice_platform_backend.entity.Project;
import org.example.practice_platform_backend.entity.Team;
import org.example.practice_platform_backend.mapper.*;
import org.example.practice_platform_backend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.example.practice_platform_backend.entity.SearchResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@EnableAsync
@RequestMapping("/api/")
public class SearchResultController {

    @Value("${uploadPath}")
    private String uploadPath;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private SearchResultMapper searchResultMapper;

    @Autowired
    private MemberListMapper memberListMapper;

    @Autowired
    private ImageUtils imageUtils;
    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private FruitMapper fruitMapper;

    @Transactional
    @GetMapping(value = "search")
    public ResponseEntity<?> searchResult(@RequestParam Map<String, String> searchRequest) {
        String address = searchRequest.get("location");
        String text = searchRequest.get("text");
        String category = searchRequest.get("label");

        if (address == null || text == null || category == null) {
            return ResponseEntity.badRequest().body("必需的请求参数缺失");
        }

        List<SearchResult> searchResults;
        List<String> tags;
        Map<String,String> memberList;

        try {
            switch (category) {
                case "示范项目":
                    searchResults = searchResultMapper.searchFruit(address, text);
                    if(Objects.isNull(searchResults)){
                        return ResponseEntity.ok("没有找到匹配的结果");
                    }
                    for (SearchResult searchResult : searchResults) {
                        tags = tagsMapper.searchFruitTags(searchResult.getId());
                        memberList = memberListMapper.getCommunityAndTeamNameByFruitId(searchResult.getId());
                        searchResult.setTags(tags);
                        searchResult.setList(memberList);
                    }
                    break;
                case "村镇需求":
                    searchResults = searchResultMapper.searchNeed(address, text);
                    if(Objects.isNull(searchResults)){
                        return ResponseEntity.ok("没有找到匹配的结果");
                    }
                    for (SearchResult searchResult : searchResults) {
                        tags = tagsMapper.searchTags(searchResult.getId());
                        memberList = memberListMapper.getCommunityNamesByNeedId(searchResult.getId());
                        searchResult.setTags(tags);
                        searchResult.setList(memberList);
                    }
                    break;
                case "高校突击队":
                    searchResults = searchResultMapper.searchTeam(address, text);
                    if(Objects.isNull(searchResults)){
                        return ResponseEntity.ok("没有找到匹配的结果");
                    }
                    for (SearchResult searchResult : searchResults) {
                        tags = tagsMapper.searchTeamTags(searchResult.getId());
                        memberList = memberListMapper.getCollegeByTeamNumber(searchResult.getId());
                        searchResult.setTags(tags);
                        searchResult.setList(memberList);
                    }
                    break;
                case "结对成功墙":
                    searchResults = searchResultMapper.searchSuccessNeed(address, text);
                    if(Objects.isNull(searchResults)){
                        return ResponseEntity.ok("没有找到匹配的结果");
                    }
                    for (SearchResult searchResult : searchResults) {
                        tags = tagsMapper.searchTags(searchResult.getId());
                        memberList = memberListMapper.getCommunityAndTeamNameByNeedId(searchResult.getId());
                        searchResult.setTags(tags);
                        searchResult.setList(memberList);
                    }
                    break;
                default:
                    return ResponseEntity.badRequest().body("无效的搜索类别");
            }

            for (SearchResult searchResult : searchResults) {
                //获取缩略图
                String path = uploadPath + searchResult.getImage();
                searchResult.setImage(imageUtils.getThumbnail(path));
            }
            return ResponseEntity.ok(searchResults);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("数据库访问出错");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //首页
    @GetMapping("/mainPage")
    public ResponseEntity<?> getMainPage(@RequestParam("category") String category,
                                         @RequestParam("tag_id") Integer tag_id) throws IOException {
        if(tag_id == null||category==null){
            return ResponseEntity.status(400).body("tag_id 和 category 不能为空");
        }
        switch (category) {
            case "村镇需求" -> {
                List<SearchResult> needs = needMapper.getNeedByTagId(tag_id);
                needs.forEach(need -> {
                    String path = needMapper.getCoverPathByNeedId(need.getId());
                    List<String> tags = tagsMapper.searchTags(need.getId());
                    Map<String, String> memberList = memberListMapper.getCommunityNamesByNeedId(need.getId());
                    try {
                        need.setImage(imageUtils.getFileBytes(uploadPath + path));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    need.setTags(tags);
                    need.setList(memberList);
                });
                return ResponseEntity.ok(needs);
            }
            case "高校突击队" -> {
                List<SearchResult> teams = teamMapper.getTeamByTagId(tag_id);
                teams.forEach(team -> {
                    try {
                        List<String> tags = tagsMapper.searchTeamTags(team.getId());
                        Map<String,String> list= new java.util.HashMap<>(Map.of());
                        list.put("first_name",teamMapper.getAcademyByTeam(team.getId()));
                        team.setImage(imageUtils.getFileBytes(uploadPath + team.getImage()));
                        team.setTags(tags);
                        team.setList(list);
                    } catch (IOException e) {
                        e.fillInStackTrace();
                    }
                });
                return ResponseEntity.ok(teams);
            }
            case "结对成功墙" -> {
                List<SearchResult> searchResults = projectMapper.getProjectByProjectId(tag_id);
                for (SearchResult searchResult : searchResults) {
                    List<String> tags = tagsMapper.searchTags(searchResult.getId());
                    Map<String, String> memberList = memberListMapper.getCommunityAndTeamNameByNeedId(searchResult.getId());
                    String path = needMapper.getCoverPathByNeedId(searchResult.getId());
                    searchResult.setImage(imageUtils.getFileBytes(uploadPath + path));
                    searchResult.setTags(tags);
                    searchResult.setList(memberList);
                }
                return ResponseEntity.ok(searchResults);
            }
            case "示范项目" -> {
                List<SearchResult> searchResults = searchResultMapper.getFruitByTagId(tag_id);
                if (Objects.isNull(searchResults)) {
                    return ResponseEntity.ok("没有找到匹配的结果");
                }
                for (SearchResult searchResult : searchResults) {
                    List<String> tags = tagsMapper.searchFruitTags(searchResult.getId());
                    Map<String, String> memberList = memberListMapper.getCommunityAndTeamNameByFruitId(searchResult.getId());
                    String path = fruitMapper.getFruitCover(searchResult.getId());
                    searchResult.setImage(imageUtils.getFileBytes(uploadPath + path));
                    searchResult.setTags(tags);
                    searchResult.setList(memberList);
                }
                return ResponseEntity.ok(searchResults);
            }
        }
        return ResponseEntity.status(400).body("类别错误");
    }
}
