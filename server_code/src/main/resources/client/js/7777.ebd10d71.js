"use strict";
(self["webpackChunkvue3_nf0"] = self["webpackChunkvue3_nf0"] || []).push([[7777],{

/***/ 2539:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": function() { return /* binding */ formModel; }
});

// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
// EXTERNAL MODULE: ./node_modules/@vue/shared/dist/shared.esm-bundler.js
var shared_esm_bundler = __webpack_require__(33);
// EXTERNAL MODULE: ./node_modules/axios/lib/axios.js + 40 modules
var axios = __webpack_require__(2586);
// EXTERNAL MODULE: ./node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(953);
// EXTERNAL MODULE: ./node_modules/vue-router/dist/vue-router.esm-bundler.js
var vue_router_esm_bundler = __webpack_require__(6166);
// EXTERNAL MODULE: ./node_modules/vuex/dist/vuex.esm-bundler.js + 5 modules
var vuex_esm_bundler = __webpack_require__(6278);
// EXTERNAL MODULE: ./src/components/approval.vue + 2 modules
var approval = __webpack_require__(7241);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/jinjishenqing/formModel.vue?vue&type=script&setup=true&lang=js

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
  class: "info_view"
};
const _hoisted_7 = {
  class: "title_view"
};
const _hoisted_8 = {
  class: "detail_title"
};
const _hoisted_9 = {
  class: "info_item"
};
const _hoisted_10 = {
  class: "info_text"
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
  key: 0,
  class: "info_item"
};
const _hoisted_28 = {
  class: "info_text"
};
const _hoisted_29 = {
  key: 1,
  class: "info_item"
};
const _hoisted_30 = {
  class: "info_text"
};
const _hoisted_31 = {
  class: "btn_view"
};





const tableName = 'jinjishenqing';
const formName = '晋级申请';
//基础信息

/* harmony default export */ var formModelvue_type_script_setup_true_lang_js = ({
  __name: 'formModel',
  setup(__props) {
    const moment = window.moment;
    const store = (0,vuex_esm_bundler/* useStore */.Pj)();
    const user = (0,runtime_core_esm_bundler/* computed */.EW)(() => store.getters['user/session']);
    const userAvatar = (0,runtime_core_esm_bundler/* computed */.EW)(() => store.getters['user/avatar']);
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    const route = (0,vue_router_esm_bundler/* useRoute */.lq)();
    const router = (0,vue_router_esm_bundler/* useRouter */.rd)();
    const baseUrl = (0,reactivity_esm_bundler/* ref */.KR)(context.$config.url);
    //基础信息
    const breadList = (0,reactivity_esm_bundler/* ref */.KR)([{
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
    const bannerList = (0,reactivity_esm_bundler/* ref */.KR)([]);
    // 详情
    const title = (0,reactivity_esm_bundler/* ref */.KR)('');
    const detail = (0,reactivity_esm_bundler/* ref */.KR)({});
    const activeName = (0,reactivity_esm_bundler/* ref */.KR)('false');
    const getDetail = () => {
      context?.$http({
        url: `${tableName}/detail/${route.query.id}`,
        method: 'get'
      }).then(res => {
        title.value = res.data.data.saishimingcheng;
        detail.value = res.data.data;
      });
    };
    // 下载文件
    const downClick = file => {
      if (!file) {
        context?.$toolUtil.message('文件不存在', 'error');
      }
      let arr = file.replace(new RegExp('file/', "g"), "");
      axios/* default */.A.get((location.href.split(context?.$config.name).length > 1 ? location.href.split(context?.$config.name)[0] : '') + context?.$config.name + '/file/download?fileName=' + arr, {
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
    const centerType = (0,reactivity_esm_bundler/* ref */.KR)(false);
    const init = () => {
      if (route.query.centerType) {
        centerType.value = true;
      }
      getDetail();
    };
    const sensitiveWordsArr = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const getSensitiveWords = () => {
      context.$http.get('sensitiveword/detail/1').then(res => {
        sensitiveWordsArr.value = res.data.data.content.split(',');
      });
    };
    getSensitiveWords();
    //审核
    const approvalRef = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const approvalClick = (btnType = '审核') => {
      if (!context?.$toolUtil.storageGet('frontToken')) {
        context?.$toolUtil.message('请登录后再操作！', 'error');
        return false;
      }
      if (!btnAuth('jinjishenqing', btnType)) {
        context?.$toolUtil.message('暂无权限操作！', 'error');
        return false;
      }
      let row = detail.value;
      (0,runtime_core_esm_bundler/* nextTick */.dY)(() => {
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
    const jinjibisaijieguoonAcross = async (btnType, crossOptAudit, crossOptPay, statusColumnName, tips, statusColumnValue, type = 1) => {
      if (!context?.$toolUtil.storageGet('frontToken')) {
        context?.$toolUtil.message('请登录后再操作！', 'error');
        return false;
      }
      if (!btnAuth('jinjishenqing', btnType)) {
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
      (0,runtime_core_esm_bundler/* nextTick */.dY)(() => {
        router.push(`/index/jinjibisaijieguoAdd?type=cross&&id=${detail.value.id}`);
      });
    };
    (0,runtime_core_esm_bundler/* onMounted */.sV)(() => {
      init();
    });
    return (_ctx, _cache) => {
      const _component_el_breadcrumb_item = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-breadcrumb-item");
      const _component_el_breadcrumb = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-breadcrumb");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_1, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_2, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_3, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_breadcrumb, {
        separator: "",
        class: "breadcrumb"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_breadcrumb_item, {
          class: "first_breadcrumb",
          to: {
            path: '/'
          }
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[2] || (_cache[2] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("首页")])),
          _: 1,
          __: [2]
        }), ((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(breadList.value, (item, index) => {
          return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_breadcrumb_item, {
            class: "second_breadcrumb",
            key: index
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(item.name), 1)]),
            _: 2
          }, 1024);
        }), 128))]),
        _: 1
      })]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_4, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
        class: "back_btn",
        onClick: backClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[3] || (_cache[3] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("返回")])),
        _: 1,
        __: [3]
      })])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_5, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_6, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_7, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_8, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", null, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.saishimingcheng), 1)])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_9, [_cache[4] || (_cache[4] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "球队名称", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_10, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.qiuduimingcheng), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_11, [_cache[5] || (_cache[5] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "球队积分", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_12, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.qiuduijifen), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_13, [_cache[6] || (_cache[6] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "比赛排名", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_14, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.bisaipaiming), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_15, [_cache[7] || (_cache[7] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "裁判账号", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_16, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.caipanzhanghao), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_17, [_cache[8] || (_cache[8] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "裁判姓名", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_18, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.caipanxingming), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_19, [_cache[9] || (_cache[9] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "晋级赛事", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_20, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.jinjisaishi), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_21, [_cache[10] || (_cache[10] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "申请时间", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_22, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.shenqingshijian), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_23, [_cache[11] || (_cache[11] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "队长账号", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_24, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.duizhangzhanghao), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_25, [_cache[12] || (_cache[12] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "队长姓名", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_26, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.duizhangxingming), 1)]), centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_27, [_cache[13] || (_cache[13] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "是否审核", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_28, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.sfsh), 1)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_29, [_cache[14] || (_cache[14] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "info_label"
      }, "回复内容", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_30, (0,shared_esm_bundler/* toDisplayString */.v_)(detail.value.shhf), 1)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_31, [btnFrontAuth('jinjishenqing', '结果') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 0,
        class: "cross_btn",
        onClick: _cache[0] || (_cache[0] = $event => jinjibisaijieguoonAcross('结果', '是', '', '', '')),
        type: "warning"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[15] || (_cache[15] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("结果")])),
        _: 1,
        __: [15]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), btnAuth('jinjishenqing', '审核') && detail.value.sfsh == '待审核' ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 1,
        class: "approval_btn",
        type: "warning",
        onClick: _cache[1] || (_cache[1] = $event => approvalClick())
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[16] || (_cache[16] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("审核")])),
        _: 1,
        __: [16]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), centerType.value && btnAuth('jinjishenqing', '修改') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 2,
        class: "edit_btn",
        type: "primary",
        onClick: editClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[17] || (_cache[17] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("修改")])),
        _: 1,
        __: [17]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), centerType.value && btnAuth('jinjishenqing', '删除') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 3,
        class: "del_btn",
        type: "danger",
        onClick: delClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[18] || (_cache[18] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("删除")])),
        _: 1,
        __: [18]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)])])]), (0,runtime_core_esm_bundler/* createVNode */.bF)(approval/* default */.A, {
        ref_key: "approvalRef",
        ref: approvalRef,
        onApprovalSave: approvalSave,
        tableName: tableName
      }, null, 512)]);
    };
  }
});
;// ./src/views/pages/jinjishenqing/formModel.vue?vue&type=script&setup=true&lang=js
 
;// ./node_modules/mini-css-extract-plugin/dist/loader.js??clonedRuleSet-22.use[0]!./node_modules/css-loader/dist/cjs.js??clonedRuleSet-22.use[1]!./node_modules/vue-loader/dist/stylePostLoader.js!./node_modules/postcss-loader/dist/cjs.js??clonedRuleSet-22.use[2]!./node_modules/sass-loader/dist/cjs.js??clonedRuleSet-22.use[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/jinjishenqing/formModel.vue?vue&type=style&index=0&id=a90efd4a&lang=scss&scoped=true
// extracted by mini-css-extract-plugin

;// ./src/views/pages/jinjishenqing/formModel.vue?vue&type=style&index=0&id=a90efd4a&lang=scss&scoped=true

// EXTERNAL MODULE: ./node_modules/vue-loader/dist/exportHelper.js
var exportHelper = __webpack_require__(6262);
;// ./src/views/pages/jinjishenqing/formModel.vue



;


const __exports__ = /*#__PURE__*/(0,exportHelper/* default */.A)(formModelvue_type_script_setup_true_lang_js, [['__scopeId',"data-v-a90efd4a"]])

/* harmony default export */ var formModel = (__exports__);

/***/ }),

/***/ 7241:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {


// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  A: function() { return /* binding */ approval; }
});

// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
// EXTERNAL MODULE: ./node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(953);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/components/approval.vue?vue&type=script&setup=true&lang=js

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
    const attrs = (0,runtime_core_esm_bundler/* useAttrs */.OA)(); //defineEmits中未注册的事件可在attrs中获取
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    //props
    const props = __props;
    const {
      tableName
    } = (0,reactivity_esm_bundler/* toRefs */.QW)(props);
    //props

    const rules = (0,reactivity_esm_bundler/* ref */.KR)({
      shhf: [{
        required: true,
        message: '请输入审核回复',
        trigger: 'blur'
      }]
    });
    const approvalForm = (0,reactivity_esm_bundler/* ref */.KR)({});
    const approvalVisible = (0,reactivity_esm_bundler/* ref */.KR)(false);
    //ref
    const ruleFormRef = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const sfshType = (0,reactivity_esm_bundler/* ref */.KR)(1);
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
      const _component_el_input = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-input");
      const _component_el_form_item = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form-item");
      const _component_el_col = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-col");
      const _component_el_row = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-row");
      const _component_el_form = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-dialog");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", null, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_dialog, {
        class: "edit_form",
        modelValue: approvalVisible.value,
        "onUpdate:modelValue": _cache[5] || (_cache[5] = $event => approvalVisible.value = $event),
        title: '审核',
        width: "70%",
        "destroy-on-close": ""
      }, {
        footer: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [sfshType.value == 1 ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("span", _hoisted_1, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          type: "danger",
          onClick: _cache[1] || (_cache[1] = $event => approvalSave('否'))
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("拒绝")])),
          _: 1,
          __: [6]
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          type: "primary",
          onClick: _cache[2] || (_cache[2] = $event => approvalSave('是'))
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[7] || (_cache[7] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 通过 ")])),
          _: 1,
          __: [7]
        })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), sfshType.value == 2 ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("span", _hoisted_2, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          type: "danger",
          onClick: _cache[3] || (_cache[3] = $event => approvalVisible.value = false)
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[8] || (_cache[8] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("取消")])),
          _: 1,
          __: [8]
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          type: "primary",
          onClick: _cache[4] || (_cache[4] = $event => approvalSave())
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[9] || (_cache[9] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 回复 ")])),
          _: 1,
          __: [9]
        })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)]),
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form, {
          ref_key: "ruleFormRef",
          ref: ruleFormRef,
          model: approvalForm.value,
          "label-width": "120px",
          rules: rules.value
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_row, null, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* renderSlot */.RG)(_ctx.$slots, "formItem", {
              row: approvalForm.value
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 24
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "审核回复",
                prop: "shhf"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_input, {
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
;// ./src/components/approval.vue?vue&type=script&setup=true&lang=js
 
;// ./src/components/approval.vue



const __exports__ = approvalvue_type_script_setup_true_lang_js;

/* harmony default export */ var approval = (__exports__);

/***/ })

}]);
//# sourceMappingURL=7777.ebd10d71.js.map