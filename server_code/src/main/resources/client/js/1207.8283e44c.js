"use strict";
(self["webpackChunkvue3_nf0"] = self["webpackChunkvue3_nf0"] || []).push([[1207],{

/***/ 3674:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {


// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "Z": function() { return /* binding */ approval; }
});

// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(4818);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(3870);
;// CONCATENATED MODULE: ../../../../../../../node_modules_front/1/node_modules/thread-loader/dist/cjs.js!../../../../../../../node_modules_front/1/node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!../../../../../../../node_modules_front/1/node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/components/approval.vue?vue&type=script&setup=true&lang=js

const _hoisted_1 = {
  key: 0,
  class: "dialog-footer"
};
const _hoisted_2 = {
  key: 1,
  class: "dialog-footer"
};

/* harmony default export */ var approvalvue_type_script_setup_true_lang_js = ({
  __name: 'approval',
  props: {
    tableName: String
  },
  emits: ['shChange'],
  setup(__props, {
    expose: __expose,
    emit: __emit
  }) {
    const emit = __emit;
    const attrs = (0,runtime_core_esm_bundler/* useAttrs */.l1)(); //defineEmits中未注册的事件可在attrs中获取
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.FN)()?.appContext.config.globalProperties;
    //props
    const props = __props;
    const {
      tableName
    } = (0,reactivity_esm_bundler/* toRefs */.BK)(props);
    //props

    const rules = (0,reactivity_esm_bundler/* ref */.iH)({
      shhf: [{
        required: true,
        message: '请输入审核回复',
        trigger: 'blur'
      }]
    });
    const approvalForm = (0,reactivity_esm_bundler/* ref */.iH)({});
    const approvalVisible = (0,reactivity_esm_bundler/* ref */.iH)(false);
    //ref
    const ruleFormRef = (0,reactivity_esm_bundler/* ref */.iH)(null);
    const sfshType = (0,reactivity_esm_bundler/* ref */.iH)(1);
    const approvalClick = (row, type = 1) => {
      sfshType.value = type;
      approvalForm.value = JSON.parse(JSON.stringify(row));
      approvalVisible.value = true;
    };

    //声明父级调用
    __expose({
      approvalClick,
      approvalVisible
    });
    const approvalSave = type => {
      if (sfshType.value == 1) {
        approvalForm.value.sfsh = type;
      }
      ruleFormRef.value.validate(valid => {
        if (valid) {
          if (attrs.onApprovalSave) {
            //如果有指定在外部处理，则触发approvalSave事件，并return
            emit('approvalSave', approvalForm.value);
            return;
          }
          let url = `${tableName.value}/update`;
          context?.$http({
            url: url,
            method: 'post',
            data: approvalForm.value
          }).then(res => {
            context.$message.success('审核成功');
            approvalVisible.value = false;
            emit('shChange', type, approvalForm.value);
          });
        }
      });
    };
    return (_ctx, _cache) => {
      const _component_el_input = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-input");
      const _component_el_form_item = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-form-item");
      const _component_el_col = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-col");
      const _component_el_row = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-row");
      const _component_el_form = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-form");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-button");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-dialog");
      return (0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", null, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_dialog, {
        class: "edit_form",
        modelValue: approvalVisible.value,
        "onUpdate:modelValue": _cache[5] || (_cache[5] = $event => approvalVisible.value = $event),
        title: '审核',
        width: "70%",
        "destroy-on-close": ""
      }, {
        footer: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [sfshType.value == 1 ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("span", _hoisted_1, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
          type: "danger",
          onClick: _cache[1] || (_cache[1] = $event => approvalSave('否'))
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("拒绝", -1)])),
          _: 1,
          __: [6]
        }), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
          type: "primary",
          onClick: _cache[2] || (_cache[2] = $event => approvalSave('是'))
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[7] || (_cache[7] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)(" 通过 ", -1)])),
          _: 1,
          __: [7]
        })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), sfshType.value == 2 ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("span", _hoisted_2, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
          type: "danger",
          onClick: _cache[3] || (_cache[3] = $event => approvalVisible.value = false)
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[8] || (_cache[8] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("取消", -1)])),
          _: 1,
          __: [8]
        }), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
          type: "primary",
          onClick: _cache[4] || (_cache[4] = $event => approvalSave())
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[9] || (_cache[9] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)(" 回复 ", -1)])),
          _: 1,
          __: [9]
        })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true)]),
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_form, {
          ref_key: "ruleFormRef",
          ref: ruleFormRef,
          model: approvalForm.value,
          "label-width": "120px",
          rules: rules.value
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_row, null, {
            default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* renderSlot */.WI)(_ctx.$slots, "formItem", {
              row: approvalForm.value
            }), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_col, {
              span: 24
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_form_item, {
                label: "审核回复",
                prop: "shhf"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_input, {
                  modelValue: approvalForm.value.shhf,
                  "onUpdate:modelValue": _cache[0] || (_cache[0] = $event => approvalForm.value.shhf = $event),
                  type: "textarea"
                }, null, 8, ["modelValue"])]),
                _: 1
              })]),
              _: 1
            })]),
            _: 3
          })]),
          _: 3
        }, 8, ["model", "rules"])]),
        _: 3
      }, 8, ["modelValue"])]);
    };
  }
});
;// CONCATENATED MODULE: ./src/components/approval.vue?vue&type=script&setup=true&lang=js
 
;// CONCATENATED MODULE: ./src/components/approval.vue



const __exports__ = approvalvue_type_script_setup_true_lang_js;

/* harmony default export */ var approval = (__exports__);

/***/ }),

/***/ 1207:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": function() { return /* binding */ formModel; }
});

// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(4818);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/@vue/shared/dist/shared.esm-bundler.js
var shared_esm_bundler = __webpack_require__(5406);
// EXTERNAL MODULE: ./src/assets/avatar.png
var avatar = __webpack_require__(8308);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/axios/lib/axios.js + 40 modules
var axios = __webpack_require__(362);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(3870);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/vue-router/dist/vue-router.esm-bundler.js
var vue_router_esm_bundler = __webpack_require__(1503);
// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/vuex/dist/vuex.esm-bundler.js + 5 modules
var vuex_esm_bundler = __webpack_require__(231);
// EXTERNAL MODULE: ./src/components/approval.vue + 2 modules
var approval = __webpack_require__(3674);
;// CONCATENATED MODULE: ../../../../../../../node_modules_front/1/node_modules/thread-loader/dist/cjs.js!../../../../../../../node_modules_front/1/node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!../../../../../../../node_modules_front/1/node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/qiuduipaixingbang/formModel.vue?vue&type=script&setup=true&lang=js


const _hoisted_1 = {
  class: "detail-page"
};
const _hoisted_2 = {
  class: "breadcrumb-wrapper",
  style: {
    "width": "100%"
  }
};
const _hoisted_3 = {
  class: "bread_view"
};
const _hoisted_4 = {
  class: "back_view"
};
const _hoisted_5 = {
  class: "detail_view"
};
const _hoisted_6 = {
  class: "swiper_view"
};
const _hoisted_7 = ["src"];
const _hoisted_8 = {
  class: "info_view"
};
const _hoisted_9 = {
  class: "title_view"
};
const _hoisted_10 = {
  class: "detail_title"
};
const _hoisted_11 = {
  class: "info_item"
};
const _hoisted_12 = {
  class: "info_text"
};
const _hoisted_13 = {
  class: "info_item"
};
const _hoisted_14 = {
  class: "info_text"
};
const _hoisted_15 = {
  class: "info_item"
};
const _hoisted_16 = {
  class: "info_text"
};
const _hoisted_17 = {
  class: "info_item"
};
const _hoisted_18 = {
  class: "info_text"
};
const _hoisted_19 = {
  class: "info_item"
};
const _hoisted_20 = {
  class: "info_text"
};
const _hoisted_21 = {
  class: "info_item"
};
const _hoisted_22 = {
  class: "info_text"
};
const _hoisted_23 = {
  class: "info_item"
};
const _hoisted_24 = {
  class: "info_text"
};
const _hoisted_25 = {
  class: "info_item"
};
const _hoisted_26 = {
  class: "info_text"
};
const _hoisted_27 = {
  class: "info_item"
};
const _hoisted_28 = {
  class: "info_text"
};
const _hoisted_29 = {
  class: "info_item"
};
const _hoisted_30 = {
  class: "info_text"
};
const _hoisted_31 = {
  class: "info_item"
};
const _hoisted_32 = {
  class: "info_text"
};
const _hoisted_33 = {
  class: "info_item"
};
const _hoisted_34 = {
  class: "info_text"
};
const _hoisted_35 = {
  class: "info_item"
};
const _hoisted_36 = {
  class: "info_text"
};
const _hoisted_37 = {
  class: "info_item"
};
const _hoisted_38 = {
  class: "info_text"
};
const _hoisted_39 = {
  class: "info_item"
};
const _hoisted_40 = {
  class: "info_text"
};
const _hoisted_41 = {
  key: 0,
  class: "info_item"
};
const _hoisted_42 = {
  class: "info_text"
};
const _hoisted_43 = {
  key: 1,
  class: "info_item"
};
const _hoisted_44 = {
  class: "info_text"
};
const _hoisted_45 = {
  class: "btn_view"
};
const _hoisted_46 = ["innerHTML"];
const _hoisted_47 = {
  class: "my_comment_view"
};
const _hoisted_48 = {
  class: "comment_btn"
};
const _hoisted_49 = {
  class: "comment_list"
};
const _hoisted_50 = {
  class: "comment_top"
};
const _hoisted_51 = {
  class: "comment_user"
};
const _hoisted_52 = {
  class: "comment_user_img"
};
const _hoisted_53 = ["src"];
const _hoisted_54 = {
  key: 1,
  src: avatar,
  alt: ""
};
const _hoisted_55 = {
  class: "comment_user_info"
};
const _hoisted_56 = {
  class: "comment_time"
};
const _hoisted_57 = {
  key: 0,
  class: "istop"
};
const _hoisted_58 = {
  class: "zancai-box"
};
const _hoisted_59 = ["onClick"];
const _hoisted_60 = {
  class: "num"
};
const _hoisted_61 = ["onClick"];
const _hoisted_62 = {
  class: "num"
};
const _hoisted_63 = ["onClick"];
const _hoisted_64 = {
  class: "num"
};
const _hoisted_65 = ["onClick"];
const _hoisted_66 = {
  class: "num"
};
const _hoisted_67 = {
  class: "comment_bottom"
};
const _hoisted_68 = ["innerHTML"];
const _hoisted_69 = {
  key: 0,
  class: "comment_action"
};
const _hoisted_70 = ["onClick"];
const _hoisted_71 = {
  key: 1,
  class: "comment_reply"
};
const _hoisted_72 = ["innerHTML"];





const tableName = 'qiuduipaixingbang';
const formName = '球队排行榜';
//基础信息

/* harmony default export */ var formModelvue_type_script_setup_true_lang_js = ({
  __name: 'formModel',
  setup(__props) {
    const moment = window.moment;
    const store = (0,vuex_esm_bundler/* useStore */.oR)();
    const user = (0,runtime_core_esm_bundler/* computed */.Fl)(() => store.getters['user/session']);
    const userAvatar = (0,runtime_core_esm_bundler/* computed */.Fl)(() => store.getters['user/avatar']);
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.FN)()?.appContext.config.globalProperties;
    const route = (0,vue_router_esm_bundler/* useRoute */.yj)();
    const router = (0,vue_router_esm_bundler/* useRouter */.tv)();
    const baseUrl = (0,reactivity_esm_bundler/* ref */.iH)(context.$config.url);
    //基础信息
    const breadList = (0,reactivity_esm_bundler/* ref */.iH)([{
      name: formName
    }]);
    //权限验证
    const btnAuth = (e, a) => {
      if (centerType.value) {
        return context?.$toolUtil.isBackAuth(e, a);
      } else {
        return context?.$toolUtil.isAuth(e, a);
      }
    };
    //查看权限验证
    const btnFrontAuth = (e, a) => {
      if (centerType.value) {
        return context?.$toolUtil.isBackAuth(e, a);
      } else {
        return context?.$toolUtil.isFrontAuth(e, a);
      }
    };
    // 返回
    const backClick = () => {
      history.back();
    };
    // 轮播图
    const bannerList = (0,reactivity_esm_bundler/* ref */.iH)([]);
    // 详情
    const title = (0,reactivity_esm_bundler/* ref */.iH)('');
    const detail = (0,reactivity_esm_bundler/* ref */.iH)({});
    const activeName = (0,reactivity_esm_bundler/* ref */.iH)('first');
    const getDetail = () => {
      context?.$http({
        url: `${tableName}/detail/${route.query.id}`,
        method: 'get'
      }).then(res => {
        bannerList.value = res.data.data.qiuduilogo ? res.data.data.qiuduilogo.split(',') : [];
        title.value = res.data.data.qiuduimingcheng;
        detail.value = res.data.data;
      });
    };
    // 下载文件
    const downClick = file => {
      if (!file) {
        context?.$toolUtil.message('文件不存在', 'error');
      }
      let arr = file.replace(new RegExp('file/', "g"), "");
      axios/* default.get */.Z.get((location.href.split(context?.$config.name).length > 1 ? location.href.split(context?.$config.name)[0] : '') + context?.$config.name + '/file/download?fileName=' + arr, {
        headers: {
          token: context?.$toolUtil.storageGet('frontToken')
        },
        responseType: "blob"
      }).then(({
        data
      }) => {
        const binaryData = [];
        binaryData.push(data);
        const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
          type: 'application/pdf;chartset=UTF-8'
        }));
        const a = document.createElement('a');
        a.href = objectUrl;
        a.download = arr;
        // a.click()
        // 下面这个写法兼容火狐
        a.dispatchEvent(new MouseEvent('click', {
          bubbles: true,
          cancelable: true,
          view: window
        }));
        window.URL.revokeObjectURL(data);
      });
    };
    const approvalSave = async form => {
      context.$http.post(`${tableName}/update`, form).then(res => {
        context.$message.success('审核成功');
        approvalRef.value.approvalVisible = false;
        init();
      });
    };
    // 判断是否从个人中心跳转
    const centerType = (0,reactivity_esm_bundler/* ref */.iH)(false);
    const init = () => {
      if (route.query.centerType) {
        centerType.value = true;
      }
      getDetail();
      // 收藏
      getCollect();
      // 评论
      getCommentList();
    };
    // 收藏
    const collectType = (0,reactivity_esm_bundler/* ref */.iH)(false);
    const collectInfo = (0,reactivity_esm_bundler/* ref */.iH)({});
    const getCollect = () => {
      if (context?.$toolUtil.storageGet('frontToken')) {
        context?.$http({
          url: 'storeup/list',
          method: 'get',
          params: {
            page: 1,
            limit: 1,
            type: 1,
            refid: route.query.id,
            tablename: tableName,
            userid: context?.$toolUtil.storageGet('userid')
          }
        }).then(res => {
          if (res.data.data.list.length) {
            collectType.value = true;
            collectInfo.value = res.data.data.list[0];
          } else {
            collectType.value = false;
            collectInfo.value = {};
          }
        });
      }
    };
    // 收藏按钮
    const collectClick = type => {
      if (type == 1 && !collectType.value) {
        let params = {
          name: title.value,
          picture: bannerList.value[0],
          refid: detail.value.id,
          type: type,
          tablename: tableName,
          userid: context?.$toolUtil.storageGet('userid')
        };
        context?.$http({
          url: 'storeup/add',
          method: 'post',
          data: params
        }).then(res => {
          detail.value.storeupNumber += 1;
          context?.$http({
            url: `${tableName}/update`,
            method: 'post',
            data: detail.value
          });
          collectType.value = true;
          getCollect();
          context?.$toolUtil.message('收藏成功', 'success');
        });
      } else if (type == -1 && collectType.value) {
        let ids = [];
        ids.push(collectInfo.value.id);
        context?.$http({
          url: 'storeup/delete',
          method: 'post',
          data: ids
        }).then(res => {
          detail.value.storeupNumber -= 1;
          context?.$http({
            url: `${tableName}/update`,
            method: 'post',
            data: detail.value
          });
          collectInfo.value = {};
          collectType.value = false;
          context?.$toolUtil.message('取消成功', 'success');
        });
      }
    };
    const sensitiveWordsArr = (0,reactivity_esm_bundler/* ref */.iH)([]);
    const getSensitiveWords = () => {
      context.$http.get('sensitiveword/detail/1').then(res => {
        sensitiveWordsArr.value = res.data.data.content.split(',');
      });
    };
    getSensitiveWords();
    const contentChange = e => {
      commentForm.value.content = e;
    };
    //评论
    const commentForm = (0,reactivity_esm_bundler/* ref */.iH)({
      content: '',
      refid: route.query.id,
      userid: context?.$toolUtil.storageGet('userid'),
      nickname: context?.$toolUtil.storageGet('frontName'),
      avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : ''
    });
    const commentRules = (0,reactivity_esm_bundler/* ref */.iH)({
      content: [{
        required: true,
        message: '请输入',
        trigger: 'blur'
      }]
    });
    const commentQuery = (0,reactivity_esm_bundler/* ref */.iH)({
      page: 1,
      limit: 10,
      refid: route.query.id,
      sort: 'istop',
      order: 'desc'
    });
    const layouts = (0,reactivity_esm_bundler/* ref */.iH)(["total", "prev", "pager", "next", "sizes"]);
    const commentList = (0,reactivity_esm_bundler/* ref */.iH)([]);
    const commentTotal = (0,reactivity_esm_bundler/* ref */.iH)(0);
    const commentFormRef = (0,reactivity_esm_bundler/* ref */.iH)(null);
    const commentSizeChange = size => {
      commentQuery.value.limit = size;
      getCommentList();
    };
    const commentCurrentChange = page => {
      commentQuery.value.page = page;
      getCommentList();
    };
    const getCommentList = () => {
      context?.$http({
        url: `discuss${tableName}/list`,
        params: commentQuery.value,
        method: 'get'
      }).then(res => {
        commentList.value = res.data.data.list;
        commentTotal.value = res.data.data.total;
      });
    };
    //提交评论
    const commentSave = async () => {
      if (!commentForm.value.content || commentForm.value.content == '<p><br></p>') {
        return context.$message.error("请输入评论内容");
      }
      for (var i = 0; i < sensitiveWordsArr.value.length; i++) {
        //全局替换
        var reg = new RegExp(sensitiveWordsArr.value[i], "g");
        //判断内容中是否包括敏感词
        if (commentForm.value.content.indexOf(sensitiveWordsArr.value[i]) > -1) {
          // 将敏感词替换为 **
          commentForm.value.content = commentForm.value.content.replace(reg, "**");
        }
      }
      commentFormRef.value.validate(valid => {
        if (valid) {
          context?.$http({
            url: `discuss${tableName}/add`,
            method: 'post',
            data: commentForm.value
          }).then(res => {
            context.$http.get(`${tableName}/info/${detail.value.id}`).then(res => {
              let detail = res.data.data;
              detail.discussNumber++;
              context.$http.post(`${tableName}/update`, detail).then(() => {
                getDetail();
              });
            });
            context?.$toolUtil.message('评论成功', 'success');
            resetForm();
            getCommentList();
          });
        }
      });
    };
    const commentDel = item => {
      context.$confirm("确定要删除该评论吗？", "提示").then(() => {
        context.$http.post(`discuss${tableName}/delete`, [item.id]).then(res => {
          if (res.data.code == 0) {
            context.$message.success("删除成功");
            context.$http.get(`${tableName}/info/${detail.value.id}`).then(res => {
              let detail = res.data.data;
              detail.discussNumber--;
              context.$http.post(`${tableName}/update`, detail).then(() => {
                getDetail();
              });
            });
            getCommentList();
          }
        });
      }).catch(() => {});
    };
    const resetForm = () => {
      commentFormRef.value.resetFields();
    };
    const commentZanCaiClick = (item, type) => {
      if (!user.value.id) return context.$message.error("请先登录");
      if (type == 1) {
        //赞
        if (item.tuserids) {
          let arr = item.tuserids.split(',');
          let index = arr.indexOf(`${user.value.id}`);
          if (index != -1) {
            arr.splice(index, 1);
            item.thumbsupnum--;
          } else {
            arr.push(`${user.value.id}`);
            item.thumbsupnum++;
          }
          item.tuserids = arr.join(',');
        } else {
          item.tuserids = `${user.value.id}`;
          item.thumbsupnum++;
        }
        if (item.cuserids) {
          let arr = item.cuserids.split(',');
          let index = arr.indexOf(`${user.value.id}`);
          if (index != -1) {
            //点赞需要取消踩
            arr.splice(index, 1);
            item.crazilynum--;
          }
          item.cuserids = arr.join(',');
        }
      } else {
        //踩
        if (item.cuserids) {
          let arr = item.cuserids.split(',');
          let index = arr.indexOf(`${user.value.id}`);
          console.log(arr, user.value.id);
          if (index != -1) {
            arr.splice(index, 1);
            item.crazilynum--;
          } else {
            arr.push(`${user.value.id}`);
            item.crazilynum++;
          }
          item.cuserids = arr.join(',');
        } else {
          item.cuserids = `${user.value.id}`;
          item.crazilynum++;
        }
        if (item.tuserids) {
          let arr = item.tuserids.split(',');
          let index = arr.indexOf(`${user.value.id}`);
          if (index != -1) {
            //点踩需要取消赞
            arr.splice(index, 1);
            item.thumbsupnum--;
          }
          item.tuserids = arr.join(',');
        }
      }
      context.$http.post(`discuss${tableName}/update`, item).then(res => {
        getCommentList();
      });
    };
    const commentHasZan = (item, type) => {
      if (!user.value.id) return false;
      if (!item.tuserids) return false;
      let arr = item.tuserids.split(',');
      return arr.includes(`${user.value.id}`);
    };
    const commentHasCai = (item, type) => {
      if (!user.value.id) return false;
      if (!item.cuserids) return false;
      let arr = item.cuserids.split(',');
      return arr.includes(`${user.value.id}`);
    };
    //审核
    const approvalRef = (0,reactivity_esm_bundler/* ref */.iH)(null);
    const approvalClick = (btnType = '审核') => {
      if (!context?.$toolUtil.storageGet('frontToken')) {
        context?.$toolUtil.message('请登录后再操作！', 'error');
        return false;
      }
      if (!btnAuth('qiuduipaixingbang', btnType)) {
        context?.$toolUtil.message('暂无权限操作！', 'error');
        return false;
      }
      let row = detail.value;
      (0,runtime_core_esm_bundler/* nextTick */.Y3)(() => {
        approvalRef.value.approvalClick(row);
      });
    };
    //修改
    const editClick = () => {
      router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`);
    };
    //删除
    const delClick = () => {
      ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        context?.$http({
          url: `${tableName}/delete`,
          method: 'post',
          data: [detail.value.id]
        }).then(res => {
          context?.$toolUtil.message('删除成功', 'success', () => {
            history.back();
          });
        });
      }).catch(_ => {});
    };
    const jiaruqiuduionAcross = async (btnType, crossOptAudit, crossOptPay, statusColumnName, tips, statusColumnValue, type = 1) => {
      if (!context?.$toolUtil.storageGet('frontToken')) {
        context?.$toolUtil.message('请登录后再操作！', 'error');
        return false;
      }
      if (!btnAuth('qiuduipaixingbang', btnType)) {
        context?.$toolUtil.message('暂无权限操作！', 'error');
        return false;
      }
      if (crossOptAudit == '是' && detail.value.sfsh != '是') {
        context?.$toolUtil.message('请审核通过后再操作！', 'error');
        return false;
      }
      context?.$toolUtil.storageSet('crossObj', JSON.stringify(detail.value));
      context?.$toolUtil.storageSet('crossTable', tableName);
      context?.$toolUtil.storageSet('crossStatusColumnName', statusColumnName);
      context?.$toolUtil.storageSet('crossTips', tips);
      context?.$toolUtil.storageSet('crossStatusColumnValue', statusColumnValue);
      if (statusColumnName != '' && !statusColumnName.startsWith("[")) {
        var obj = detail.value;
        for (var o in obj) {
          if (o == statusColumnName && obj[o] == statusColumnValue) {
            context?.$toolUtil.message(tips, 'warning');
            return;
          }
        }
      }
      (0,runtime_core_esm_bundler/* nextTick */.Y3)(() => {
        router.push(`/index/jiaruqiuduiAdd?type=cross&&id=${detail.value.id}`);
      });
    };
    (0,runtime_core_esm_bundler/* onMounted */.bv)(() => {
      init();
    });
    return (_ctx, _cache) => {
      const _component_el_breadcrumb_item = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-breadcrumb-item");
      const _component_el_breadcrumb = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-breadcrumb");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-button");
      const _component_mySwiper = (0,runtime_core_esm_bundler/* resolveComponent */.up)("mySwiper");
      const _component_el_tab_pane = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-tab-pane");
      const _component_editor = (0,runtime_core_esm_bundler/* resolveComponent */.up)("editor");
      const _component_el_form_item = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-form-item");
      const _component_el_form = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-form");
      const _component_el_pagination = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-pagination");
      const _component_el_tabs = (0,runtime_core_esm_bundler/* resolveComponent */.up)("el-tabs");
      return (0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_1, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_2, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_3, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_breadcrumb, {
        separator: "",
        class: "breadcrumb"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_breadcrumb_item, {
          class: "first_breadcrumb",
          to: {
            path: '/'
          }
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[5] || (_cache[5] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("首页", -1)])),
          _: 1,
          __: [5]
        }), ((0,runtime_core_esm_bundler/* openBlock */.wg)(true), (0,runtime_core_esm_bundler/* createElementBlock */.iD)(runtime_core_esm_bundler/* Fragment */.HY, null, (0,runtime_core_esm_bundler/* renderList */.Ko)(breadList.value, (item, index) => {
          return (0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createBlock */.j4)(_component_el_breadcrumb_item, {
            class: "second_breadcrumb",
            key: index
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)((0,shared_esm_bundler/* toDisplayString */.zw)(item.name), 1)]),
            _: 2
          }, 1024);
        }), 128))]),
        _: 1
      })]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_4, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
        class: "back_btn",
        onClick: backClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("返回", -1)])),
        _: 1,
        __: [6]
      })])]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_5, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_6, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_mySwiper, {
        data: bannerList.value,
        type: 3,
        loop: true,
        navigation: false,
        pagination: false,
        paginationType: 1,
        scrollbar: false,
        slidesPerView: 1,
        spaceBetween: 22,
        autoHeight: true,
        centeredSlides: false,
        freeMode: false,
        effectType: 1,
        direction: "horizontal",
        autoplay: true,
        slidesPerColumn: 1
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(scope => [(0,runtime_core_esm_bundler/* createElementVNode */._)("img", {
          src: scope.row ? (scope.row.startsWith('http') ? '' : baseUrl.value) + scope.row : ''
        }, null, 8, _hoisted_7)]),
        _: 1
      }, 8, ["data"])]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_8, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_9, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_10, [(0,runtime_core_esm_bundler/* createElementVNode */._)("span", null, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.qiuduimingcheng), 1)]), !collectType.value ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
        key: 0,
        class: "collect_view",
        onClick: _cache[0] || (_cache[0] = $event => collectClick(1))
      }, _cache[7] || (_cache[7] = [(0,runtime_core_esm_bundler/* createElementVNode */._)("i", {
        class: "iconfont icon-likefill4"
      }, null, -1), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", null, "收藏", -1)]))) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), collectType.value ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
        key: 1,
        class: "collect_view",
        onClick: _cache[1] || (_cache[1] = $event => collectClick(-1))
      }, _cache[8] || (_cache[8] = [(0,runtime_core_esm_bundler/* createElementVNode */._)("i", {
        class: "iconfont iconfontActive icon-likefill4"
      }, null, -1), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
        class: "textActive"
      }, "取消收藏", -1)]))) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_11, [_cache[9] || (_cache[9] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "教练名称", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_12, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.jiaolianmingcheng), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_13, [_cache[10] || (_cache[10] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "球员人数", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_14, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.qiuyuanrenshu), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_15, [_cache[11] || (_cache[11] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "积分", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_16, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.jifen), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_17, [_cache[12] || (_cache[12] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "胜", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_18, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.sheng), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_19, [_cache[13] || (_cache[13] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "负", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_20, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.fu), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_21, [_cache[14] || (_cache[14] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "平", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_22, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.ping), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_23, [_cache[15] || (_cache[15] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "胜率", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_24, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.shenglv), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_25, [_cache[16] || (_cache[16] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "是否在招募", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_26, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.shifouzaizhaomu), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_27, [_cache[17] || (_cache[17] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "创立时间", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_28, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.chuanglishijian), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_29, [_cache[18] || (_cache[18] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "球队口号", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_30, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.qiuduikouhao), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_31, [_cache[19] || (_cache[19] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "球员名单", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_32, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.qiuyuanmingdan), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_33, [_cache[20] || (_cache[20] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "队长账号", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_34, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.duizhangzhanghao), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_35, [_cache[21] || (_cache[21] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "队长姓名", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_36, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.duizhangxingming), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_37, [_cache[22] || (_cache[22] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "收藏数量", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_38, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.storeupNumber), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_39, [_cache[23] || (_cache[23] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "评论数", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_40, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.discussNumber), 1)]), centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_41, [_cache[24] || (_cache[24] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "是否审核", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_42, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.sfsh), 1)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_43, [_cache[25] || (_cache[25] = (0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
        class: "info_label"
      }, "回复内容", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_44, (0,shared_esm_bundler/* toDisplayString */.zw)(detail.value.shhf), 1)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_45, [btnFrontAuth('qiuduipaixingbang', '申请加入') ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createBlock */.j4)(_component_el_button, {
        key: 0,
        class: "cross_btn",
        onClick: _cache[2] || (_cache[2] = $event => jiaruqiuduionAcross('申请加入', '是', '', '', '')),
        type: "warning"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[26] || (_cache[26] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("申请加入", -1)])),
        _: 1,
        __: [26]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), btnAuth('qiuduipaixingbang', '审核') && detail.value.sfsh == '待审核' ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createBlock */.j4)(_component_el_button, {
        key: 1,
        class: "approval_btn",
        type: "warning",
        onClick: _cache[3] || (_cache[3] = $event => approvalClick())
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[27] || (_cache[27] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("审核", -1)])),
        _: 1,
        __: [27]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), centerType.value && btnAuth('qiuduipaixingbang', '修改') ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createBlock */.j4)(_component_el_button, {
        key: 2,
        class: "edit_btn",
        type: "primary",
        onClick: editClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[28] || (_cache[28] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("修改", -1)])),
        _: 1,
        __: [28]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), centerType.value && btnAuth('qiuduipaixingbang', '删除') ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createBlock */.j4)(_component_el_button, {
        key: 3,
        class: "del_btn",
        type: "danger",
        onClick: delClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[29] || (_cache[29] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("删除", -1)])),
        _: 1,
        __: [29]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true)])])]), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_tabs, {
        type: "border-card",
        modelValue: activeName.value,
        "onUpdate:modelValue": _cache[4] || (_cache[4] = $event => activeName.value = $event),
        class: "tabs_view"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_tab_pane, {
          label: "球队介绍",
          name: "first"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
            innerHTML: detail.value.qiuduijieshao
          }, null, 8, _hoisted_46)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_tab_pane, {
          label: "评论",
          name: "commentActive"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_47, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_form, {
            ref_key: "commentFormRef",
            ref: commentFormRef,
            model: commentForm.value,
            class: "my_comment_form",
            rules: commentRules.value
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_form_item, {
              prop: "content"
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_editor, {
                value: commentForm.value.content,
                placeholder: "善语结善缘,恶语伤人心",
                class: "list_editor",
                onChange: contentChange
              }, null, 8, ["value"])]),
              _: 1
            })]),
            _: 1
          }, 8, ["model", "rules"]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_48, [(0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
            class: "add_btn",
            type: "primary",
            onClick: commentSave
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[30] || (_cache[30] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("立即评论", -1)])),
            _: 1,
            __: [30]
          }), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_button, {
            class: "reset_btn",
            onClick: resetForm
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.w5)(() => _cache[31] || (_cache[31] = [(0,runtime_core_esm_bundler/* createTextVNode */.Uk)("重置", -1)])),
            _: 1,
            __: [31]
          })])]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_49, [((0,runtime_core_esm_bundler/* openBlock */.wg)(true), (0,runtime_core_esm_bundler/* createElementBlock */.iD)(runtime_core_esm_bundler/* Fragment */.HY, null, (0,runtime_core_esm_bundler/* renderList */.Ko)(commentList.value, (item, index) => {
            return (0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
              class: "comment",
              key: index
            }, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_50, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_51, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_52, [item.avatarurl ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("img", {
              key: 0,
              src: baseUrl.value + item.avatarurl,
              alt: ""
            }, null, 8, _hoisted_53)) : ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("img", _hoisted_54))]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_55, (0,shared_esm_bundler/* toDisplayString */.zw)(item.nickname), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_56, (0,shared_esm_bundler/* toDisplayString */.zw)(item.addtime), 1), item.istop ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_57, _cache[32] || (_cache[32] = [(0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "iconfont icon-flight-takeoff-fill"
            }, null, -1), (0,runtime_core_esm_bundler/* createTextVNode */.Uk)("置顶 ", -1)]))) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_58, [commentHasZan(item, 1) ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
              key: 0,
              class: "zan-item active",
              onClick: $event => commentZanCaiClick(item, 1),
              style: {
                "cursor": "pointer"
              }
            }, [_cache[33] || (_cache[33] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "iconfont icon-thumb-up-fill2"
            }, null, -1)), _cache[34] || (_cache[34] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "label"
            }, "赞", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", _hoisted_60, "(" + (0,shared_esm_bundler/* toDisplayString */.zw)(item.thumbsupnum) + ")", 1)], 8, _hoisted_59)) : ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
              key: 1,
              class: "zan-item",
              onClick: $event => commentZanCaiClick(item, 1),
              style: {
                "cursor": "pointer"
              }
            }, [_cache[35] || (_cache[35] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "iconfont icon-thumb-up-line2"
            }, null, -1)), _cache[36] || (_cache[36] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "label"
            }, "赞", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", _hoisted_62, "(" + (0,shared_esm_bundler/* toDisplayString */.zw)(item.thumbsupnum) + ")", 1)], 8, _hoisted_61)), commentHasCai(item, 1) ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
              key: 2,
              class: "cai-item active",
              onClick: $event => commentZanCaiClick(item, -1),
              style: {
                "cursor": "pointer"
              }
            }, [_cache[37] || (_cache[37] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "iconfont icon-thumb-down-fill2"
            }, null, -1)), _cache[38] || (_cache[38] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "label"
            }, "踩", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", _hoisted_64, "(" + (0,shared_esm_bundler/* toDisplayString */.zw)(item.crazilynum) + ")", 1)], 8, _hoisted_63)) : ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", {
              key: 3,
              class: "cai-item",
              onClick: $event => commentZanCaiClick(item, -1),
              style: {
                "cursor": "pointer"
              }
            }, [_cache[39] || (_cache[39] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "iconfont icon-thumb-down-line2"
            }, null, -1)), _cache[40] || (_cache[40] = (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "label"
            }, "踩", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", _hoisted_66, "(" + (0,shared_esm_bundler/* toDisplayString */.zw)(item.crazilynum) + ")", 1)], 8, _hoisted_65))])]), (0,runtime_core_esm_bundler/* createElementVNode */._)("div", _hoisted_67, [(0,runtime_core_esm_bundler/* createElementVNode */._)("div", {
              class: "comment_content",
              innerHTML: item.content
            }, null, 8, _hoisted_68), item.userid == user.value.id ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_69, [(0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              class: "del",
              onClick: $event => commentDel(item),
              style: {
                "cursor": "pointer"
              }
            }, "删除", 8, _hoisted_70)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true), item.reply ? ((0,runtime_core_esm_bundler/* openBlock */.wg)(), (0,runtime_core_esm_bundler/* createElementBlock */.iD)("div", _hoisted_71, [_cache[41] || (_cache[41] = (0,runtime_core_esm_bundler/* createTextVNode */.Uk)(" 回复：", -1)), (0,runtime_core_esm_bundler/* createElementVNode */._)("span", {
              innerHTML: item.reply
            }, null, 8, _hoisted_72)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.kq)("", true)])]);
          }), 128))]), (0,runtime_core_esm_bundler/* createVNode */.Wm)(_component_el_pagination, {
            background: "",
            layout: layouts.value.join(','),
            total: commentTotal.value,
            "page-size": commentQuery.value.limit,
            "prev-text": "上一页",
            "next-text": "下一页",
            "hide-on-single-page": false,
            onSizeChange: commentSizeChange,
            onCurrentChange: commentCurrentChange
          }, null, 8, ["layout", "total", "page-size"])]),
          _: 1
        })]),
        _: 1
      }, 8, ["modelValue"]), (0,runtime_core_esm_bundler/* createVNode */.Wm)(approval/* default */.Z, {
        ref_key: "approvalRef",
        ref: approvalRef,
        onApprovalSave: approvalSave,
        tableName: tableName
      }, null, 512)]);
    };
  }
});
;// CONCATENATED MODULE: ./src/views/pages/qiuduipaixingbang/formModel.vue?vue&type=script&setup=true&lang=js
 
;// CONCATENATED MODULE: ../../../../../../../node_modules_front/1/node_modules/mini-css-extract-plugin/dist/loader.js??clonedRuleSet-22.use[0]!../../../../../../../node_modules_front/1/node_modules/css-loader/dist/cjs.js??clonedRuleSet-22.use[1]!../../../../../../../node_modules_front/1/node_modules/vue-loader/dist/stylePostLoader.js!../../../../../../../node_modules_front/1/node_modules/postcss-loader/dist/cjs.js??clonedRuleSet-22.use[2]!../../../../../../../node_modules_front/1/node_modules/sass-loader/dist/cjs.js??clonedRuleSet-22.use[3]!../../../../../../../node_modules_front/1/node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/qiuduipaixingbang/formModel.vue?vue&type=style&index=0&id=649005cc&lang=scss&scoped=true
// extracted by mini-css-extract-plugin

;// CONCATENATED MODULE: ./src/views/pages/qiuduipaixingbang/formModel.vue?vue&type=style&index=0&id=649005cc&lang=scss&scoped=true

// EXTERNAL MODULE: ../../../../../../../node_modules_front/1/node_modules/vue-loader/dist/exportHelper.js
var exportHelper = __webpack_require__(3815);
;// CONCATENATED MODULE: ./src/views/pages/qiuduipaixingbang/formModel.vue



;


const __exports__ = /*#__PURE__*/(0,exportHelper/* default */.Z)(formModelvue_type_script_setup_true_lang_js, [['__scopeId',"data-v-649005cc"]])

/* harmony default export */ var formModel = (__exports__);

/***/ })

}]);
//# sourceMappingURL=1207.8283e44c.js.map