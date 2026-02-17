"use strict";
(self["webpackChunkvue3_nf0"] = self["webpackChunkvue3_nf0"] || []).push([[6764],{

/***/ 6764:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": function() { return /* binding */ list; }
});

// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
// EXTERNAL MODULE: ./node_modules/@vue/shared/dist/shared.esm-bundler.js
var shared_esm_bundler = __webpack_require__(33);
// EXTERNAL MODULE: ./node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(953);
// EXTERNAL MODULE: ./node_modules/vue-router/dist/vue-router.esm-bundler.js
var vue_router_esm_bundler = __webpack_require__(6166);
// EXTERNAL MODULE: ./node_modules/vuex/dist/vuex.esm-bundler.js + 5 modules
var vuex_esm_bundler = __webpack_require__(6278);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/jinjibisaijieguo/list.vue?vue&type=script&setup=true&lang=js

const _hoisted_1 = {
  class: "list-page"
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
  key: 0,
  class: "back_view"
};
const _hoisted_5 = {
  class: "list_search"
};
const _hoisted_6 = {
  class: "search_view"
};
const _hoisted_7 = {
  class: "search_box"
};
const _hoisted_8 = {
  class: "search_view"
};
const _hoisted_9 = {
  class: "search_box"
};
const _hoisted_10 = {
  class: "search_view"
};
const _hoisted_11 = {
  class: "search_box"
};
const _hoisted_12 = {
  class: "search_btn_view"
};
const _hoisted_13 = {
  class: "table_view"
};
const _hoisted_14 = {
  style: {
    "text-align": "center"
  }
};
const _hoisted_15 = ["src"];



const tableName = 'jinjibisaijieguo';
const formName = '晋级比赛结果';
//基础信息

/* harmony default export */ var listvue_type_script_setup_true_lang_js = ({
  __name: 'list',
  setup(__props) {
    const moment = window.moment;
    const store = (0,vuex_esm_bundler/* useStore */.Pj)();
    const user = (0,runtime_core_esm_bundler/* computed */.EW)(() => store.getters['user/session']);
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    const router = (0,vue_router_esm_bundler/* useRouter */.rd)();
    const route = (0,vue_router_esm_bundler/* useRoute */.lq)();
    const baseUrl = (0,reactivity_esm_bundler/* ref */.KR)(context.$config.url);
    //基础信息
    const breadList = (0,reactivity_esm_bundler/* ref */.KR)([{
      name: formName
    }]);
    const list = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const listQuery = (0,reactivity_esm_bundler/* ref */.KR)({
      page: 1,
      limit: 20
    });
    const total = (0,reactivity_esm_bundler/* ref */.KR)(0);
    const listLoading = (0,reactivity_esm_bundler/* ref */.KR)(false);
    //权限验证
    const btnAuth = (e, a) => {
      if (centerType.value) {
        return context?.$toolUtil.isBackAuth(e, a);
      } else {
        return context?.$toolUtil.isAuth(e, a);
      }
    };
    const addClick = () => {
      router.push('/index/jinjibisaijieguoAdd');
    };
    //判断是否从个人中心跳转
    const centerType = (0,reactivity_esm_bundler/* ref */.KR)(false);
    //返回
    const backClick = () => {
      router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`);
    };
    //搜索
    const searchQuery = (0,reactivity_esm_bundler/* ref */.KR)({});
    //下拉列表
    const zhuduiqiuduiLists = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const getzhuduiqiuduiLists = () => {
      context?.$http({
        url: 'option/qiuduipaixingbang/qiuduimingcheng',
        method: 'get'
      }).then(res => {
        zhuduiqiuduiLists.value = res.data.data;
      });
    };
    const keduiqiuduiLists = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const getkeduiqiuduiLists = () => {
      context?.$http({
        url: 'option/qiuduipaixingbang/qiuduimingcheng',
        method: 'get'
      }).then(res => {
        keduiqiuduiLists.value = res.data.data;
      });
    };
    const searchClick = async () => {
      listQuery.value.page = 1;
      getList();
    };
    //分页
    const layouts = (0,reactivity_esm_bundler/* ref */.KR)(["total", "prev", "pager", "next", "sizes"]);
    const sizeChange = size => {
      listQuery.value.limit = size;
      getList();
    };
    const currentChange = page => {
      listQuery.value.page = page;
      getList();
    };
    //分页
    //列表
    const getList = () => {
      listLoading.value = true;
      let params = JSON.parse(JSON.stringify(listQuery.value));
      if (searchQuery.value.jinjisaishi && searchQuery.value.jinjisaishi != '') {
        params.jinjisaishi = '%' + searchQuery.value.jinjisaishi + '%';
      }
      if (searchQuery.value.zhuduiqiudui && searchQuery.value.zhuduiqiudui != '') {
        params.zhuduiqiudui = searchQuery.value.zhuduiqiudui;
      }
      if (searchQuery.value.keduiqiudui && searchQuery.value.keduiqiudui != '') {
        params.keduiqiudui = searchQuery.value.keduiqiudui;
      }
      context?.$http({
        url: `${tableName}/${centerType.value ? 'page' : 'list'}`,
        method: 'get',
        params: params
      }).then(res => {
        listLoading.value = false;
        list.value = res.data.data.list;
        total.value = Number(res.data.data.total);
      });
    };
    const tableDetailClick = row => {
      router.push(`${tableName}Detail?id=` + row.id + (centerType.value ? '&&centerType=1' : ''));
    };
    //下载文件
    const download = file => {
      if (!file) {
        context?.$toolUtil.message('文件不存在', 'error');
      }
      const a = document.createElement('a');
      a.style.display = 'none';
      a.setAttribute('target', '_blank');
      file && a.setAttribute('download', file);
      a.href = context?.$config.url + file;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    };
    // 查看大图
    const preViewUrl = (0,reactivity_esm_bundler/* ref */.KR)('');
    const preViewVisible = (0,reactivity_esm_bundler/* ref */.KR)(false);
    const preViewClick = url => {
      preViewUrl.value = url;
      preViewVisible.value = true;
    };
    const init = async () => {
      if (route.query.centerType) {
        centerType.value = true;
      }
      if (context.$toolUtil.storageGet('frontToken') && !user.value.id) {
        await store.dispatch("user/getSession");
      }
      getzhuduiqiuduiLists();
      getkeduiqiuduiLists();
      getList();
    };
    init();
    return (_ctx, _cache) => {
      const _component_el_breadcrumb_item = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-breadcrumb-item");
      const _component_el_breadcrumb = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-breadcrumb");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      const _component_el_input = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-input");
      const _component_el_option = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-option");
      const _component_el_select = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-select");
      const _component_el_table_column = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-table-column");
      const _component_el_table = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-table");
      const _component_el_pagination = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-pagination");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-dialog");
      const _directive_loading = (0,runtime_core_esm_bundler/* resolveDirective */.gN)("loading");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_1, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_2, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_3, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_breadcrumb, {
        separator: "",
        class: "breadcrumb"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_breadcrumb_item, {
          class: "first_breadcrumb",
          to: {
            path: '/'
          }
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[5] || (_cache[5] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("首页")])),
          _: 1,
          __: [5]
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
      })]), centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_4, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
        class: "back_btn",
        onClick: backClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("返回")])),
        _: 1,
        __: [6]
      })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_5, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_6, [_cache[7] || (_cache[7] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "search_label"
      }, " 晋级赛事： ", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_7, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_input, {
        class: "search_inp",
        modelValue: searchQuery.value.jinjisaishi,
        "onUpdate:modelValue": _cache[0] || (_cache[0] = $event => searchQuery.value.jinjisaishi = $event),
        placeholder: "晋级赛事",
        clearable: ""
      }, null, 8, ["modelValue"])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_8, [_cache[8] || (_cache[8] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "search_label"
      }, " 主队球队： ", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_9, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_select, {
        class: "search_sel",
        clearable: "",
        modelValue: searchQuery.value.zhuduiqiudui,
        "onUpdate:modelValue": _cache[1] || (_cache[1] = $event => searchQuery.value.zhuduiqiudui = $event),
        placeholder: "主队球队"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(zhuduiqiuduiLists.value, item => {
          return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_option, {
            label: item,
            value: item
          }, null, 8, ["label", "value"]);
        }), 256))]),
        _: 1
      }, 8, ["modelValue"])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_10, [_cache[9] || (_cache[9] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "search_label"
      }, " 客队球队： ", -1)), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_11, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_select, {
        class: "search_sel",
        clearable: "",
        modelValue: searchQuery.value.keduiqiudui,
        "onUpdate:modelValue": _cache[2] || (_cache[2] = $event => searchQuery.value.keduiqiudui = $event),
        placeholder: "客队球队"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(keduiqiuduiLists.value, item => {
          return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_option, {
            label: item,
            value: item
          }, null, 8, ["label", "value"]);
        }), 256))]),
        _: 1
      }, 8, ["modelValue"])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_12, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
        class: "search_btn",
        onClick: searchClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[10] || (_cache[10] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("搜索")])),
        _: 1,
        __: [10]
      }), btnAuth('jinjibisaijieguo', '新增') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 0,
        class: "add_btn",
        onClick: addClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[11] || (_cache[11] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("新增")])),
        _: 1,
        __: [11]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_13, [(0,runtime_core_esm_bundler/* withDirectives */.bo)(((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_table, {
        class: "data_table",
        data: list.value,
        border: "",
        "row-style": {
          'cursor': 'pointer'
        },
        onRowClick: tableDetailClick,
        stripe: false
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          resizable: true,
          align: "center",
          "header-align": "center",
          type: "selection",
          width: "55"
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "序号",
          width: "120",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)((listQuery.value.page - 1) * listQuery.value.limit + scope.$index + 1), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "晋级赛事",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.jinjisaishi), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "主队球队",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.zhuduiqiudui), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "客队球队",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.keduiqiudui), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "主场球员",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.zhuchangqiuyuan), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "客场球员",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.kechangqiuyuan), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "最终比分",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.zuizhongbifen), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "争议情况",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.zhengyiqingkuang), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "提交时间",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.tijiaoshijian), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "裁判账号",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.caipanzhanghao), 1)]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_table_column, {
          label: "裁判姓名",
          resizable: true,
          align: "center",
          "header-align": "center"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(scope => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(scope.row.caipanxingming), 1)]),
          _: 1
        })]),
        _: 1
      }, 8, ["data"])), [[_directive_loading, listLoading.value]])]), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_pagination, {
        background: "",
        layout: layouts.value.join(','),
        total: total.value,
        "page-size": listQuery.value.limit,
        "current-page": listQuery.value.page,
        "onUpdate:currentPage": _cache[3] || (_cache[3] = $event => listQuery.value.page = $event),
        "prev-text": "上一页",
        "next-text": "下一页",
        "hide-on-single-page": false,
        onSizeChange: sizeChange,
        onCurrentChange: currentChange
      }, null, 8, ["layout", "total", "page-size", "current-page"])]), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_dialog, {
        modelValue: preViewVisible.value,
        "onUpdate:modelValue": _cache[4] || (_cache[4] = $event => preViewVisible.value = $event),
        title: '查看大图',
        width: "40%",
        "destroy-on-close": ""
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_14, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("img", {
          src: preViewUrl.value,
          style: {
            "max-width": "100%"
          },
          alt: ""
        }, null, 8, _hoisted_15)])]),
        _: 1
      }, 8, ["modelValue"])], 64);
    };
  }
});
;// ./src/views/pages/jinjibisaijieguo/list.vue?vue&type=script&setup=true&lang=js
 
;// ./src/views/pages/jinjibisaijieguo/list.vue



const __exports__ = listvue_type_script_setup_true_lang_js;

/* harmony default export */ var list = (__exports__);

/***/ })

}]);
//# sourceMappingURL=6764.3fe1f637.js.map