require("../../common/manifest.js")
require("../../common/vendor.js")
global.webpackJsonpMpvue([11],{

/***/ 240:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(241);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 241:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(243);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_5f264ed4_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_fileExt_template_wxml_script_js_style_wxss_platform_wx_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(244);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(242)
}
var normalizeComponent = __webpack_require__(2)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = null
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_5f264ed4_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_fileExt_template_wxml_script_js_style_wxss_platform_wx_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\new_commun_info\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-5f264ed4", Component.options)
  } else {
    hotAPI.reload("data-v-5f264ed4", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 242:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 243:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__ = __webpack_require__(26);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_promise__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_promise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_promise__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_tdesign_miniprogram_miniprogram_npm_tdesign_miniprogram_toast_index__ = __webpack_require__(3);


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      confirmBtn: '确认',
      cancelBtn: '取消',
      title: '选择地区',
      shangchuan: {
        fileList: []
      },
      VideoFiles: [],
      show: {
        id: 1,
        name: '',
        intro: '',
        address: '',
        text: '',
        current: 0, // 只是初始化时会锁定在哪个页面而已！
        imageCurrent: 0 // 自定义
      },
      originFiles: [],
      gridConfig: {
        column: 4,
        width: 160,
        height: 160
      },
      config: {
        count: 1
      },
      areaList: {
        provinces: {
          440000: '广东省'
        },
        cities: {
          440100: '广州市',
          440200: '韶关市',
          440300: '深圳市',
          440400: '珠海市',
          440500: '汕头市',
          440600: '佛山市'
        },
        counties: {
          440103: '荔湾区',
          440104: '越秀区',
          440105: '海珠区',
          440106: '天河区',
          440111: '白云区',
          440112: '黄埔区',
          440113: '番禺区',
          440114: '花都区',
          440115: '南沙区',
          440117: '从化区',
          440118: '增城区',
          440203: '武江区',
          440204: '浈江区',
          440205: '曲江区',
          440222: '始兴县',
          440224: '仁化县',
          440229: '翁源县',
          440232: '乳源瑶族自治县',
          440233: '新丰县',
          440281: '乐昌市',
          440282: '南雄市',
          440303: '罗湖区',
          440304: '福田区',
          440305: '南山区',
          440306: '宝安区',
          440307: '龙岗区',
          440308: '盐田区',
          440309: '龙华区',
          440310: '坪山区',
          440311: '光明区',
          440402: '香洲区',
          440403: '斗门区',
          440404: '金湾区',
          440507: '龙湖区',
          440511: '金平区',
          440512: '濠江区',
          440513: '潮阳区',
          440514: '潮南区',
          440515: '澄海区',
          440523: '南澳县',
          440604: '禅城区',
          440605: '南海区',
          440606: '顺德区',
          440607: '三水区',
          440608: '高明区'
        }
      },
      data: {
        areaText: '请选择',
        areaVisible: false,
        areaValue: [],
        provinces: [],
        cities: [],
        counties: []
      },
      wd: 0,
      jd: 0,
      address: ''
    };
  },
  onLoad: function onLoad(options) {
    // this.fetchShowData(options.comID) // 根据社区ID去找对应的show中的数据后端接口！
  },
  mounted: function mounted() {
    this.initAreaData();
  },

  methods: {
    initAreaData: function initAreaData() {
      this.data.provinces = this.getOptions(this.areaList.provinces);

      var _getCities = this.getCities(this.data.provinces[0].value),
          cities = _getCities.cities,
          counties = _getCities.counties;

      this.data.cities = cities;
      this.data.counties = counties;
    },
    showSuccessToast: function showSuccessToast(text) {
      Object(__WEBPACK_IMPORTED_MODULE_2_tdesign_miniprogram_miniprogram_npm_tdesign_miniprogram_toast_index__["a" /* default */])({
        selector: '#t-toast',
        message: text,
        theme: 'success',
        direction: 'column'
      });
    },
    showWarningToast: function showWarningToast(text) {
      Object(__WEBPACK_IMPORTED_MODULE_2_tdesign_miniprogram_miniprogram_npm_tdesign_miniprogram_toast_index__["a" /* default */])({
        selector: '#t-toast',
        message: text,
        theme: 'warning',
        direction: 'column'
      });
    },
    AgreeAudit: function AgreeAudit() {
      // 连接后端接口！！！
      var token = wx.getStorageSync('token');
      var that = this;
      wx.request({
        url: 'http://120.78.1.231:8084/api/leader/register/community',
        method: 'POST',
        data: {
          community_name: this.show.name,
          introduction: this.show.intro,
          address: this.data.areaText,
          longitude: this.jd,
          latitude: this.wd
        },
        header: {
          'content-type': 'application/json',
          'token': '' + token
        },
        success: function success(res) {
          if (res.statusCode === 200) {
            var Mid = res.header.media_id || res.header.Media_id;
            var id = res.header.id || res.header.Id;
            var uploadTasks = that.originFiles.filter(function (file) {
              return !file.type.includes('video');
            }).map(function (file, index) {
              var isFirstImage = index === 0 ? 1 : 0;
              return that.uploadFile(file.url, isFirstImage, Mid, id);
            });
            // 如果有视频，上传视频
            var videoFile = that.VideoFiles.find(function (file) {
              return file.type.includes('video');
            });
            if (videoFile) {
              that.uploadVideo(videoFile.url, id);
            }
            // 并发上传所有图片
            __WEBPACK_IMPORTED_MODULE_1_babel_runtime_core_js_promise___default.a.all(uploadTasks).then(function () {
            }).catch(function (err) {
              console.error('上传图片过程中发生错误:', err);
            });
          } else if (res.statusCode === 400) {
            that.showWarningToast('您有一个社区待审核！');
          } else {
            console.error('注册文本失败', res.data);
            // 处理登录失败的逻辑，例如显示错误信息
          }
          that.show = {
            id: 1,
            name: '',
            intro: '',
            address: '',
            text: '',
            current: 0, // 只是初始化时会锁定在哪个页面而已！
            imageCurrent: 0 // 自定义
          };
        },
        fail: function fail(error) {
          console.error('请求失败', error);
          // 处理请求失败的逻辑
        }
      });
    },
    uploadVideo: function uploadVideo(filePath, ReturnID) {
      var that = this;
      var token = wx.getStorageSync('token');
      wx.uploadFile({
        url: 'http://120.78.1.231:8084/api/media/video/upload',
        filePath: filePath,
        name: 'video', // 后端接收文件的字段名
        formData: {
          type: 0,
          id: ReturnID,
          isModify: 0
        },
        header: {
          'content-type': 'multipart/form-data',
          'token': '' + token
        },
        success: function success(res) {
          if (res.statusCode === 200) {
            that.showSuccessToast('已完成');
            setTimeout(function () {
              wx.navigateBack({
                delta: 1
              });
            }, 800);
          } else {
            console.error('上传视频失败', res.data);
            // 处理上传失败的逻辑，例如显示错误信息
          }
        },
        fail: function fail(error) {
          console.error('请求失败', error);
          // 处理请求失败的逻辑
        }
      });
    },

    // uploadFile (fileObj, ReturnID) {
    //   const token = wx.getStorageSync('token')
    //   wx.uploadFile({
    //     url: 'http://120.78.1.231:8084/api/upload/image',
    //     filePath: fileObj,
    //     name: 'img', // 后端接收文件的字段名
    //     formData: {
    //       type: 0,
    //       id: ReturnID,
    //       isCover: 0
    //     },
    //     header: {
    //       'content-type': 'multipart/form-data',
    //       'token': `${token}`
    //     },
    //     success: function (res) {
    //       if (res.statusCode === 200) {
    //       } else {
    //         console.error('上传图片失败', res.data)
    //         // 处理上传失败的逻辑，例如显示错误信息
    //       }
    //     },
    //     fail: function (error) {
    //       console.error('请求失败', error)
    //       // 处理请求失败的逻辑
    //     }
    //   })
    // },
    uploadFile: function uploadFile(fileObj, isCover, CoverID, ReturnID) {
      var token = wx.getStorageSync('token');
      // if (isCover === 1) {
      //   wx.uploadFile({
      //     url: 'http://120.78.1.231:8084/api/media/modify/image',
      //     filePath: fileObj,
      //     name: 'image', // 后端接收文件的字段名
      //     formData: {
      //       type: 1,
      //       id: ReturnID,
      //       media_id: CoverID,
      //       isCover: 1
      //     },
      //     header: {
      //       'content-type': 'multipart/form-data',
      //       'token': `${token}`
      //     },
      //     success: function (res) {
      //       if (res.statusCode === 200) {
      //       } else {
      //         console.error('上传封面失败', res.data)
      //         // 处理上传失败的逻辑，例如显示错误信息
      //       }
      //     },
      //     fail: function (error) {
      //       console.error('请求失败', error)
      //       // 处理请求失败的逻辑
      //     }
      //   })
      //   return
      // }
      wx.uploadFile({
        url: 'http://120.78.1.231:8084/api/upload/image',
        filePath: fileObj,
        name: 'img', // 后端接收文件的字段名
        formData: {
          type: 1,
          id: ReturnID,
          isCover: 0
        },
        header: {
          'content-type': 'multipart/form-data',
          'token': '' + token
        },
        success: function success(res) {
          if (res.statusCode === 200) {
          } else {
            console.error('上传图片失败', res.data);
            // 处理上传失败的逻辑，例如显示错误信息
          }
        },
        fail: function fail(error) {
          console.error('请求失败', error);
          // 处理请求失败的逻辑
        }
      });
    },
    changeName: function changeName(e) {
      this.show.name = e.target.value;
    },
    changeIntro: function changeIntro(e) {
      this.show.intro = e.target.value;
    },
    getOptions: function getOptions(obj, filter) {
      var res = __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default()(obj).map(function (key) {
        return { value: key, label: obj[key] };
      });
      if (filter) {
        return res.filter(filter);
      }
      return res;
    },
    match: function match(v1, v2, size) {
      return v1.toString().slice(0, size) === v2.toString().slice(0, size);
    },
    getCities: function getCities(provinceValue) {
      var _this = this;

      var cities = this.getOptions(this.areaList.cities, function (city) {
        return _this.match(city.value, provinceValue, 2);
      });
      var counties = this.getCounties(cities[0].value);
      return { cities: cities, counties: counties };
    },
    getCounties: function getCounties(cityValue) {
      var _this2 = this;

      return this.getOptions(this.areaList.counties, function (county) {
        return _this2.match(county.value, cityValue, 4);
      });
    },
    onColumnChange: function onColumnChange(e) {
      var _e$target = e.target,
          column = _e$target.column,
          index = _e$target.index;

      var _data = this.data,
          provinces = _data.provinces,
          cities = _data.cities;

      if (column === 0) {
        // 更改省份

        var _getCities2 = this.getCities(provinces[index].value),
            _cities = _getCities2.cities,
            counties = _getCities2.counties;

        this.data.cities = _cities;
        this.data.counties = counties;
      }
      if (column === 1) {
        // 更改城市
        var _counties = this.getCounties(cities[index].value);
        this.data.counties = _counties;
      }
      if (column === 2) {
        // 更改区县
      }
    },
    onPickerChange: function onPickerChange(e) {
      var _e$target2 = e.target,
          value = _e$target2.value,
          label = _e$target2.label;

      this.data.areaVisible = false;
      this.data.areaValue = value;
      this.data.areaText = label.join(' ');
    },
    onPickerCancel: function onPickerCancel(e) {
      this.data.areaVisible = false;
    },
    onAreaPicker: function onAreaPicker() {
      // this.data.areaVisible = true
      var that = this;
      wx.chooseLocation({
        success: function success(res) {
          // success
          // that.wd = res.latitude
          // that.jd = res.longitude
          // that.address = res.address
          that.wd = res.latitude;
          that.jd = res.longitude;
          that.address = res.address;
          that.data.areaText = that.address;
        },
        fail: function fail() {
          // fail
        },
        complete: function complete() {
          // complete
        }
      });
    },
    handleRemove: function handleRemove(e) {
      this.originFiles.splice(e.target.index, 1);
    },
    handleRemoveVideo: function handleRemoveVideo(e) {
      this.VideoFiles.splice(e.target.index, 1);
    },
    handleClick: function handleClick(e) {
    },
    handleAdd: function handleAdd(e) {
      var file = e.target.files[0];
      this.originFiles.push({
        url: file.url, // 只存储文件的本地路径
        name: file.name,
        type: file.type
      });
    },
    handleAddVideo: function handleAddVideo(e) {
      var file = e.target.files[0];
      this.VideoFiles.push({
        url: file.url, // 只存储文件的本地路径
        name: file.name,
        type: file.type
      });
    }
  }
});

/***/ }),

/***/ 244:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('t-row', {
    attrs: {
      "mpcomid": '0'
    }
  }, [_c('p', {
    staticStyle: {
      "padding-left": "10%",
      "font-size": "18px",
      "font-weight": "bold",
      "margin-top": "6%"
    }
  }, [_vm._v("\n      注册社区信息\n    ")])], 1), _vm._v(" "), _c('t-row', {
    attrs: {
      "mpcomid": '1'
    }
  }, [_c('p', {
    staticStyle: {
      "padding-left": "11%",
      "font-size": "14px",
      "margin-top": "6%"
    }
  }, [_vm._v("\n      社区名称\n    ")])], 1), _vm._v(" "), _c('t-input', {
    staticStyle: {
      "padding-left": "12%",
      "padding-right": "12%",
      "font-size": "13px",
      "margin-top": "1%"
    },
    attrs: {
      "value": _vm.show.name,
      "borderless": true,
      "eventid": '0',
      "mpcomid": '2'
    },
    on: {
      "change": _vm.changeName
    }
  }), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-left": "12%",
      "margin-right": "6%",
      "border-bottom": "1px solid #f2f2f2",
      "margin-bottom": "2%"
    }
  }), _vm._v(" "), _c('t-row', {
    attrs: {
      "mpcomid": '3'
    }
  }, [_c('p', {
    staticStyle: {
      "padding-left": "11%",
      "font-size": "14px",
      "margin-top": "6%"
    }
  }, [_vm._v("\n      社区简介\n    ")])], 1), _vm._v(" "), _c('t-input', {
    staticStyle: {
      "padding-left": "12%",
      "padding-right": "12%",
      "font-size": "13px",
      "margin-top": "1%"
    },
    attrs: {
      "value": _vm.show.intro,
      "borderless": true,
      "eventid": '1',
      "mpcomid": '4'
    },
    on: {
      "change": _vm.changeIntro
    }
  }), _vm._v(" "), _c('div', {
    staticStyle: {
      "margin-left": "12%",
      "margin-right": "6%",
      "border-bottom": "1px solid #f2f2f2",
      "margin-bottom": "2%",
      "margin-top": "2%"
    }
  }), _vm._v(" "), _c('t-row', {
    staticStyle: {
      "padding-left": "7%",
      "font-size": "16px",
      "margin-top": "6%"
    },
    attrs: {
      "mpcomid": '10'
    }
  }, [_c('t-cell', {
    attrs: {
      "title": "社区地址",
      "arrow": "",
      "hover": "",
      "note": _vm.data.areaText,
      "eventid": '2',
      "mpcomid": '5'
    },
    on: {
      "click": _vm.onAreaPicker
    }
  }), _vm._v(" "), _c('t-picker', {
    attrs: {
      "visible": _vm.data.areaVisible,
      "value": _vm.data.areaValue,
      "title": _vm.title,
      "cancelBtn": _vm.cancelBtn,
      "confirmBtn": _vm.confirmBtn,
      "usingCustomNavbar": "",
      "eventid": '3',
      "mpcomid": '9'
    },
    on: {
      "change": _vm.onPickerChange,
      "pick": _vm.onColumnChange,
      "cancel": _vm.onPickerCancel
    }
  }, [_c('t-picker-item', {
    attrs: {
      "options": _vm.data.provinces,
      "mpcomid": '6'
    }
  }), _vm._v(" "), _c('t-picker-item', {
    attrs: {
      "options": _vm.data.cities,
      "mpcomid": '7'
    }
  }), _vm._v(" "), _c('t-picker-item', {
    attrs: {
      "options": _vm.data.counties,
      "mpcomid": '8'
    }
  })], 1)], 1), _vm._v(" "), _c('p', {
    staticStyle: {
      "padding-left": "11%",
      "font-size": "14px",
      "margin-top": "6%"
    }
  }, [_vm._v("\n    上传图片（最多6张）\n  ")]), _vm._v(" "), _c('view', {
    staticClass: "wrapper",
    staticStyle: {
      "margin-left": "8%",
      "margin-right": "8%",
      "margin-top": "5%",
      "padding-left": "3%"
    }
  }, [_c('t-upload', {
    attrs: {
      "media-type": ['image'],
      "max": 6,
      "files": _vm.originFiles,
      "gridConfig": _vm.gridConfig,
      "eventid": '4',
      "mpcomid": '11'
    },
    on: {
      "add": _vm.handleAdd,
      "remove": _vm.handleRemove
    }
  })], 1), _vm._v(" "), _c('p', {
    staticStyle: {
      "padding-left": "11%",
      "font-size": "14px",
      "margin-top": "6%"
    }
  }, [_vm._v("\n    视频\n  ")]), _vm._v(" "), _c('view', {
    staticClass: "wrapper",
    staticStyle: {
      "margin-left": "8%",
      "margin-right": "8%",
      "margin-top": "5%",
      "padding-left": "3%"
    }
  }, [_c('t-upload', {
    attrs: {
      "media-type": ['video'],
      "max": 1,
      "files": _vm.VideoFiles,
      "eventid": '5',
      "mpcomid": '12'
    },
    on: {
      "add": _vm.handleAddVideo,
      "remove": _vm.handleRemoveVideo
    }
  })], 1), _vm._v(" "), _c('t-row', {
    staticStyle: {
      "padding-left": "12%",
      "padding-right": "12%",
      "margin-top": "4%"
    },
    attrs: {
      "mpcomid": '13'
    }
  }, [_c('button', {
    staticClass: "red-button",
    attrs: {
      "eventid": '6'
    },
    on: {
      "click": _vm.AgreeAudit
    }
  }, [_vm._v("提交审核")])], 1), _vm._v(" "), _c('t-toast', {
    attrs: {
      "id": "t-toast",
      "mpcomid": '14'
    }
  })], 1)
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-5f264ed4", esExports)
  }
}

/***/ })

},[240]);