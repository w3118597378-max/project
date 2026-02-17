"use strict";
(self["webpackChunkvue3_nf0"] = self["webpackChunkvue3_nf0"] || []).push([[7555],{

/***/ 6422:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {


// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  A: function() { return /* binding */ createLucideIcon; }
});

// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/hasA11yProp.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

const hasA11yProp = (props) => {
  for (const prop in props) {
    if (prop.startsWith("aria-") || prop === "role" || prop === "title") {
      return true;
    }
  }
  return false;
};


//# sourceMappingURL=hasA11yProp.js.map

;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/isEmptyString.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

const isEmptyString = (value) => value === "";


//# sourceMappingURL=isEmptyString.js.map

;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/mergeClasses.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

const mergeClasses = (...classes) => classes.filter((className, index, array) => {
  return Boolean(className) && className.trim() !== "" && array.indexOf(className) === index;
}).join(" ").trim();


//# sourceMappingURL=mergeClasses.js.map

;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/toKebabCase.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

const toKebabCase = (string) => string.replace(/([a-z0-9])([A-Z])/g, "$1-$2").toLowerCase();


//# sourceMappingURL=toKebabCase.js.map

;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/toCamelCase.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

const toCamelCase = (string) => string.replace(
  /^([A-Z])|[\s-_]+(\w)/g,
  (match, p1, p2) => p2 ? p2.toUpperCase() : p1.toLowerCase()
);


//# sourceMappingURL=toCamelCase.js.map

;// ./node_modules/lucide-vue-next/dist/esm/shared/src/utils/toPascalCase.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const toPascalCase = (string) => {
  const camelCase = toCamelCase(string);
  return camelCase.charAt(0).toUpperCase() + camelCase.slice(1);
};


//# sourceMappingURL=toPascalCase.js.map

;// ./node_modules/lucide-vue-next/dist/esm/defaultAttributes.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */

var defaultAttributes = {
  xmlns: "http://www.w3.org/2000/svg",
  width: 24,
  height: 24,
  viewBox: "0 0 24 24",
  fill: "none",
  stroke: "currentColor",
  "stroke-width": 2,
  "stroke-linecap": "round",
  "stroke-linejoin": "round"
};


//# sourceMappingURL=defaultAttributes.js.map

;// ./node_modules/lucide-vue-next/dist/esm/Icon.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */









const Icon = ({
  name,
  iconNode,
  absoluteStrokeWidth,
  "absolute-stroke-width": absoluteStrokeWidthKebabCase,
  strokeWidth,
  "stroke-width": strokeWidthKebabCase,
  size = defaultAttributes.width,
  color = defaultAttributes.stroke,
  ...props
}, { slots }) => {
  return (0,runtime_core_esm_bundler.h)(
    "svg",
    {
      ...defaultAttributes,
      ...props,
      width: size,
      height: size,
      stroke: color,
      "stroke-width": isEmptyString(absoluteStrokeWidth) || isEmptyString(absoluteStrokeWidthKebabCase) || absoluteStrokeWidth === true || absoluteStrokeWidthKebabCase === true ? Number(strokeWidth || strokeWidthKebabCase || defaultAttributes["stroke-width"]) * 24 / Number(size) : strokeWidth || strokeWidthKebabCase || defaultAttributes["stroke-width"],
      class: mergeClasses(
        "lucide",
        props.class,
        ...name ? [`lucide-${toKebabCase(toPascalCase(name))}-icon`, `lucide-${toKebabCase(name)}`] : ["lucide-icon"]
      ),
      ...!slots.default && !hasA11yProp(props) && { "aria-hidden": "true" }
    },
    [...iconNode.map((child) => (0,runtime_core_esm_bundler.h)(...child)), ...slots.default ? [slots.default()] : []]
  );
};


//# sourceMappingURL=Icon.js.map

;// ./node_modules/lucide-vue-next/dist/esm/createLucideIcon.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */




const createLucideIcon = (iconName, iconNode) => (props, { slots, attrs }) => (0,runtime_core_esm_bundler.h)(
  Icon,
  {
    ...attrs,
    ...props,
    iconNode,
    name: iconName
  },
  slots
);


//# sourceMappingURL=createLucideIcon.js.map


/***/ }),

/***/ 7555:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": function() { return /* binding */ list; }
});

// EXTERNAL MODULE: ./node_modules/core-js/modules/esnext.iterator.constructor.js
var esnext_iterator_constructor = __webpack_require__(3725);
// EXTERNAL MODULE: ./node_modules/core-js/modules/esnext.iterator.for-each.js
var esnext_iterator_for_each = __webpack_require__(9838);
// EXTERNAL MODULE: ./node_modules/core-js/modules/esnext.iterator.map.js
var esnext_iterator_map = __webpack_require__(1339);
// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
// EXTERNAL MODULE: ./node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(953);
// EXTERNAL MODULE: ./node_modules/@vue/shared/dist/shared.esm-bundler.js
var shared_esm_bundler = __webpack_require__(33);
// EXTERNAL MODULE: ./node_modules/@vue/runtime-dom/dist/runtime-dom.esm-bundler.js
var runtime_dom_esm_bundler = __webpack_require__(3751);
// EXTERNAL MODULE: ./src/assets/avatar.png
var avatar = __webpack_require__(4211);
// EXTERNAL MODULE: ./node_modules/vue-router/dist/vue-router.esm-bundler.js
var vue_router_esm_bundler = __webpack_require__(6166);
// EXTERNAL MODULE: ./node_modules/lucide-vue-next/dist/esm/createLucideIcon.js + 8 modules
var createLucideIcon = __webpack_require__(6422);
;// ./node_modules/lucide-vue-next/dist/esm/icons/message-square.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const MessageSquare = (0,createLucideIcon/* default */.A)("message-square", [
  [
    "path",
    {
      d: "M22 17a2 2 0 0 1-2 2H6.828a2 2 0 0 0-1.414.586l-2.202 2.202A.71.71 0 0 1 2 21.286V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2z",
      key: "18887p"
    }
  ]
]);


//# sourceMappingURL=message-square.js.map

;// ./node_modules/lucide-vue-next/dist/esm/icons/tag.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const Tag = (0,createLucideIcon/* default */.A)("tag", [
  [
    "path",
    {
      d: "M12.586 2.586A2 2 0 0 0 11.172 2H4a2 2 0 0 0-2 2v7.172a2 2 0 0 0 .586 1.414l8.704 8.704a2.426 2.426 0 0 0 3.42 0l6.58-6.58a2.426 2.426 0 0 0 0-3.42z",
      key: "vktsd0"
    }
  ],
  ["circle", { cx: "7.5", cy: "7.5", r: ".5", fill: "currentColor", key: "kqv944" }]
]);


//# sourceMappingURL=tag.js.map

;// ./node_modules/lucide-vue-next/dist/esm/icons/plus.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const Plus = (0,createLucideIcon/* default */.A)("plus", [
  ["path", { d: "M5 12h14", key: "1ays0h" }],
  ["path", { d: "M12 5v14", key: "s699le" }]
]);


//# sourceMappingURL=plus.js.map

;// ./node_modules/lucide-vue-next/dist/esm/icons/layers.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const Layers = (0,createLucideIcon/* default */.A)("layers", [
  [
    "path",
    {
      d: "M12.83 2.18a2 2 0 0 0-1.66 0L2.6 6.08a1 1 0 0 0 0 1.83l8.58 3.91a2 2 0 0 0 1.66 0l8.58-3.9a1 1 0 0 0 0-1.83z",
      key: "zw3jo"
    }
  ],
  [
    "path",
    {
      d: "M2 12a1 1 0 0 0 .58.91l8.6 3.91a2 2 0 0 0 1.65 0l8.58-3.9A1 1 0 0 0 22 12",
      key: "1wduqc"
    }
  ],
  [
    "path",
    {
      d: "M2 17a1 1 0 0 0 .58.91l8.6 3.91a2 2 0 0 0 1.65 0l8.58-3.9A1 1 0 0 0 22 17",
      key: "kqbvx6"
    }
  ]
]);


//# sourceMappingURL=layers.js.map

// EXTERNAL MODULE: ./node_modules/lucide-vue-next/dist/esm/icons/user.js
var user = __webpack_require__(7731);
;// ./node_modules/lucide-vue-next/dist/esm/icons/clock.js
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const Clock = (0,createLucideIcon/* default */.A)("clock", [
  ["path", { d: "M12 6v6l4 2", key: "mmk7yg" }],
  ["circle", { cx: "12", cy: "12", r: "10", key: "1mglay" }]
]);


//# sourceMappingURL=clock.js.map

// EXTERNAL MODULE: ./src/components/img.vue + 4 modules
var img = __webpack_require__(8441);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/forum/add.vue?vue&type=script&setup=true&lang=js

const _hoisted_1 = {
  class: "formModel_btn_box"
};



const tableName = 'forum';
const formName = '论坛交流';
/* harmony default export */ var addvue_type_script_setup_true_lang_js = ({
  __name: 'add',
  emits: ['close'],
  setup(__props, {
    expose: __expose,
    emit: __emit
  }) {
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    //基础信息
    const router = (0,vue_router_esm_bundler/* useRouter */.rd)();
    const route = (0,vue_router_esm_bundler/* useRoute */.lq)();
    const sessionTable = localStorage.getItem('frontSessionTable');
    const baseUrl = (0,reactivity_esm_bundler/* ref */.KR)(context.$config.url);
    const userid = (0,reactivity_esm_bundler/* ref */.KR)(context.$toolUtil.storageGet('userid'));
    const formRef = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const formVisible = (0,reactivity_esm_bundler/* ref */.KR)(false);
    const formTitle = (0,reactivity_esm_bundler/* ref */.KR)('发布新帖');
    const form = (0,reactivity_esm_bundler/* ref */.KR)({
      title: '',
      isdone: '开放',
      content: '',
      parentid: 0,
      userid: userid.value,
      username: context.$toolUtil.storageGet('frontName'),
      avatarurl: context.$toolUtil.storageGet('headportrait') || ''
    });
    const rules = (0,reactivity_esm_bundler/* ref */.KR)({
      title: [{
        required: true,
        message: '请输入标题',
        trigger: 'blur'
      }],
      content: [{
        required: true,
        message: '请输入内容',
        trigger: 'blur'
      }],
      cover: [{
        required: true,
        message: '请上传封面',
        trigger: 'blur'
      }],
      typeName: [{
        required: true,
        message: '请选择分类',
        trigger: 'blur'
      }]
    });
    //分类列表
    const typeNameLists = (0,reactivity_esm_bundler/* ref */.KR)([]);
    //帖子内容回调
    const contentChange = e => {
      form.value.content = e;
    };
    //上传回调
    const uploadSuccess = e => {
      form.value.cover = e;
    };
    const emits = __emit;
    //发布帖子
    const save = () => {
      formRef.value.validate(valid => {
        if (valid) {
          for (var i = 0; i < sensitiveWordsArr.value.length; i++) {
            //全局替换
            var reg = new RegExp(sensitiveWordsArr.value[i], "g");
            //判断内容中是否包括敏感词
            if (form.value.content.indexOf(sensitiveWordsArr.value[i]) > -1) {
              // 将敏感词替换为 **
              form.value.content = form.value.content.replace(reg, "**");
            }
          }
          context.$http({
            url: `forum/${form.value.id ? 'update' : 'add'}`,
            method: 'post',
            data: form.value
          }).then(res => {
            context.$message.success(`${form.value.id ? '修改' : '发布'}成功`);
            formVisible.value = false;
            emits('close');
          });
        }
      });
    };
    const sensitiveWordsArr = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const getSensitiveWords = () => {
      context.$http.get('sensitiveword/detail/1').then(res => {
        sensitiveWordsArr.value = res.data.data.content.split(',');
      });
    };
    getSensitiveWords();
    const resetForm = () => {
      form.value = {
        title: '',
        isdone: '开放',
        isAnonymous: 0,
        content: '',
        parentid: 0,
        userid: userid.value,
        username: context.$toolUtil.storageGet('frontName'),
        avatarurl: context.$toolUtil.storageGet('headportrait') || ''
      };
    };
    const init = id => {
      if (!id) {
        resetForm();
        formTitle.value = '发布新帖';
        formVisible.value = true;
      } else {
        context.$http({
          url: `forum/detail/${id}`,
          method: 'get'
        }).then(res => {
          form.value = res.data.data;
          formTitle.value = '修改帖子';
          formVisible.value = true;
        });
      }
      context?.$http({
        url: `option/forumType/typeName`,
        method: 'get'
      }).then(res => {
        typeNameLists.value = res.data.data;
      });
    };
    __expose({
      init
    });
    return (_ctx, _cache) => {
      const _component_el_input = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-input");
      const _component_el_form_item = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form-item");
      const _component_el_col = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-col");
      const _component_el_option = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-option");
      const _component_el_select = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-select");
      const _component_uploads = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("uploads");
      const _component_el_radio = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-radio");
      const _component_el_radio_group = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-radio-group");
      const _component_editor = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("editor");
      const _component_el_row = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-row");
      const _component_el_form = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form");
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-dialog");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_dialog, {
        modelValue: formVisible.value,
        "onUpdate:modelValue": _cache[5] || (_cache[5] = $event => formVisible.value = $event),
        title: formTitle.value,
        width: "70%",
        class: "edit_view",
        "destroy-on-close": ""
      }, {
        footer: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", _hoisted_1, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          class: "formModel_cancel",
          onClick: _cache[4] || (_cache[4] = $event => formVisible.value = false)
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[10] || (_cache[10] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("关闭")])),
          _: 1,
          __: [10]
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          class: "formModel_confirm",
          type: "primary",
          onClick: save
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(form.value.id ? '修改保存' : '发布'), 1)]),
          _: 1
        })])]),
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form, {
          class: "add_form",
          model: form.value,
          "label-width": "120px",
          rules: rules.value,
          ref_key: "formRef",
          ref: formRef
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_row, null, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "标题",
                prop: "title"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_input, {
                  class: "list_inp",
                  modelValue: form.value.title,
                  "onUpdate:modelValue": _cache[0] || (_cache[0] = $event => form.value.title = $event),
                  placeholder: "请输入标题"
                }, null, 8, ["modelValue"])]),
                _: 1
              })]),
              _: 1
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "分类",
                prop: "typeName"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_select, {
                  class: "list_sel",
                  modelValue: form.value.typeName,
                  "onUpdate:modelValue": _cache[1] || (_cache[1] = $event => form.value.typeName = $event),
                  placeholder: "请选择分类",
                  style: {
                    "width": "100%"
                  }
                }, {
                  default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(typeNameLists.value, (item, index) => {
                    return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_option, {
                      label: item,
                      value: item
                    }, null, 8, ["label", "value"]);
                  }), 256))]),
                  _: 1
                }, 8, ["modelValue"])]),
                _: 1
              })]),
              _: 1
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "封面",
                prop: "cover"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_uploads, {
                  action: "file/upload",
                  tip: "请上传封面",
                  style: {
                    "width": "100%",
                    "text-align": "left"
                  },
                  fileUrls: form.value.cover ? form.value.cover : '',
                  onChange: uploadSuccess
                }, null, 8, ["fileUrls"])]),
                _: 1
              })]),
              _: 1
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "类型",
                prop: "isdone"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio_group, {
                  class: "list_radio",
                  modelValue: form.value.isdone,
                  "onUpdate:modelValue": _cache[2] || (_cache[2] = $event => form.value.isdone = $event)
                }, {
                  default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio, {
                    value: "开放"
                  }, {
                    default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("公开")])),
                    _: 1,
                    __: [6]
                  }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio, {
                    value: "关闭"
                  }, {
                    default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[7] || (_cache[7] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("私密")])),
                    _: 1,
                    __: [7]
                  })]),
                  _: 1
                }, 8, ["modelValue"])]),
                _: 1
              })]),
              _: 1
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "匿名发帖",
                prop: "isAnonymous"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio_group, {
                  class: "list_radio",
                  modelValue: form.value.isAnonymous,
                  "onUpdate:modelValue": _cache[3] || (_cache[3] = $event => form.value.isAnonymous = $event)
                }, {
                  default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio, {
                    value: 0
                  }, {
                    default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[8] || (_cache[8] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("否")])),
                    _: 1,
                    __: [8]
                  }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_radio, {
                    value: 1
                  }, {
                    default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[9] || (_cache[9] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("是")])),
                    _: 1,
                    __: [9]
                  })]),
                  _: 1
                }, 8, ["modelValue"])]),
                _: 1
              })]),
              _: 1
            }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_col, {
              span: 12
            }, {
              default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
                label: "内容",
                prop: "content"
              }, {
                default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_editor, {
                  value: form.value.content,
                  placeholder: "请输入内容",
                  class: "list_editor",
                  onChange: contentChange
                }, null, 8, ["value"])]),
                _: 1
              })]),
              _: 1
            })]),
            _: 1
          })]),
          _: 1
        }, 8, ["model", "rules"])]),
        _: 1
      }, 8, ["modelValue", "title"]);
    };
  }
});
;// ./src/views/pages/forum/add.vue?vue&type=script&setup=true&lang=js
 
;// ./src/views/pages/forum/add.vue



const __exports__ = addvue_type_script_setup_true_lang_js;

/* harmony default export */ var add = (__exports__);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/forum/list.vue?vue&type=script&setup=true&lang=js





const listvue_type_script_setup_true_lang_js_hoisted_1 = {
  class: "min-h-screen bg-slate-50"
};
const _hoisted_2 = {
  class: "max-w-7xl mx-auto px-6 py-10"
};
const _hoisted_3 = {
  class: "bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 flex items-center justify-between hover:shadow-md hover:border-orange-300 transition-all duration-200"
};
const _hoisted_4 = {
  class: "flex items-center gap-4"
};
const _hoisted_5 = {
  class: "w-12 h-12 rounded-lg bg-gradient-to-br from-orange-500 to-orange-400 shadow-lg shadow-orange-500/25 flex items-center justify-center"
};
const _hoisted_6 = {
  class: "flex items-center gap-3"
};
const _hoisted_7 = {
  class: "grid grid-cols-1 lg:grid-cols-12 gap-6"
};
const _hoisted_8 = {
  class: "lg:col-span-3"
};
const _hoisted_9 = {
  class: "bg-white border border-slate-200 rounded-lg shadow-sm p-4 hover:shadow-md hover:border-orange-300 transition-all duration-200"
};
const _hoisted_10 = {
  class: "flex items-center justify-between gap-3 mb-4"
};
const _hoisted_11 = {
  class: "space-y-2"
};
const _hoisted_12 = ["onClick"];
const _hoisted_13 = ["title"];
const _hoisted_14 = {
  class: "lg:col-span-9"
};
const _hoisted_15 = {
  class: "bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 hover:shadow-md hover:border-orange-300 transition-all duration-200"
};
const _hoisted_16 = {
  class: "grid grid-cols-1 md:grid-cols-3 gap-4 items-end"
};
const _hoisted_17 = {
  class: "md:col-span-2"
};
const _hoisted_18 = {
  class: "flex justify-end gap-3"
};
const _hoisted_19 = {
  class: "space-y-4"
};
const _hoisted_20 = ["onMouseenter", "onClick"];
const _hoisted_21 = {
  class: "post-card__left"
};
const _hoisted_22 = {
  class: "avatar"
};
const _hoisted_23 = ["src"];
const _hoisted_24 = {
  key: 1,
  src: avatar,
  alt: ""
};
const _hoisted_25 = {
  class: "post-card__body"
};
const _hoisted_26 = ["title"];
const _hoisted_27 = ["title"];
const _hoisted_28 = {
  key: 0,
  class: "post-tags"
};
const _hoisted_29 = {
  class: "tag-pill"
};
const _hoisted_30 = {
  class: "post-meta"
};
const _hoisted_31 = {
  class: "meta-item"
};
const _hoisted_32 = {
  class: "meta-item"
};
const _hoisted_33 = {
  class: "post-card__right"
};
const _hoisted_34 = {
  key: 0,
  class: "thumb"
};
const _hoisted_35 = ["src"];
const _hoisted_36 = {
  key: 1,
  class: "post-actions"
};
const _hoisted_37 = {
  key: 2,
  class: "post-time"
};
const _hoisted_38 = {
  class: "pagination-container bg-white border border-slate-200 rounded-lg shadow-sm p-4 mt-6 flex justify-center hover:shadow-md hover:border-orange-300 transition-all duration-200"
};
const _hoisted_39 = {
  slot: "footer",
  style: {
    "text-align": "center"
  }
};




const listvue_type_script_setup_true_lang_js_tableName = 'forum';
const listvue_type_script_setup_true_lang_js_formName = '互动交流';
/* harmony default export */ var listvue_type_script_setup_true_lang_js = ({
  __name: 'list',
  setup(__props) {
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    //基础信息
    const router = (0,vue_router_esm_bundler/* useRouter */.rd)();
    const route = (0,vue_router_esm_bundler/* useRoute */.lq)();
    const baseUrl = (0,reactivity_esm_bundler/* ref */.KR)(context.$config.url);
    const sessionTable = localStorage.getItem('frontSessionTable');
    //判断是否调我的发布列表
    const myType = (0,reactivity_esm_bundler/* ref */.KR)(false);
    //判断是否从个人中心跳转
    const centerType = (0,reactivity_esm_bundler/* ref */.KR)(false);
    //权限验证
    const btnAuth = (e, a) => {
      if (context?.$toolUtil.isAuth(e, a)) {
        return true;
      }
      if (centerType.value) {
        return context?.$toolUtil.isBackAuth(e, a);
      }
    };
    //分类
    const categoryList = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const categoryIndex = (0,reactivity_esm_bundler/* ref */.KR)(-1);
    const getCategoryList = () => {
      context?.$http({
        url: 'option/forumType/typeName',
        method: 'get'
      }).then(res => {
        categoryList.value = res.data.data;
      });
    };
    const categoryClick = index => {
      listQuery.value.page = 1;
      categoryIndex.value = index;
      getList();
    };
    const list = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const listLoading = (0,reactivity_esm_bundler/* ref */.KR)(false);
    const listQuery = (0,reactivity_esm_bundler/* ref */.KR)({
      page: 1,
      limit: 20,
      isdone: '开放',
      sort: 'isTop,topTime,addtime',
      order: 'desc,desc,desc',
      isDel: centerType.value ? null : 0
    });
    const myListQuery = (0,reactivity_esm_bundler/* ref */.KR)({
      page: 1,
      limit: 20,
      parentid: 0,
      sort: 'addtime',
      order: 'desc'
    });
    const searchQuery = (0,reactivity_esm_bundler/* ref */.KR)({});
    //分页
    const layouts = (0,reactivity_esm_bundler/* ref */.KR)(["total", "prev", "pager", "next", "sizes"]);
    const total = (0,reactivity_esm_bundler/* ref */.KR)(0);
    const sizeChange = size => {
      listQuery.value.limit = size;
      getList();
    };
    const currentChange = page => {
      listQuery.value.page = page;
      getList();
    };
    //分页
    const searchClick = () => {
      listQuery.value.page = 1;
      getList();
    };
    const getList = () => {
      listLoading.value = true;
      let params = myType.value ? JSON.parse(JSON.stringify(myListQuery.value)) : JSON.parse(JSON.stringify(listQuery.value));
      if (searchQuery.value.title && searchQuery.value.title != '') {
        params['title'] = `%${searchQuery.value.title}%`;
      }
      if (categoryIndex.value != -1) {
        params.typeName = categoryList.value[categoryIndex.value];
      }
      context?.$http({
        url: `forum/${myType.value ? 'page' : 'flist'}`,
        method: 'get',
        params: params
      }).then(res => {
        listLoading.value = false;
        list.value = res.data.data.list;
        list.value.forEach(item => {
          if (item.cover) {
            item.imgUrls = item.cover.split(',').map(url => {
              if (url && url.substr(0, 4) == 'http') {
                return url;
              } else {
                return baseUrl.value + url;
              }
            });
          }
        });
        total.value = res.data.data.total;
      });
    };
    const userid = (0,reactivity_esm_bundler/* ref */.KR)(0);
    const forumShowIndex = (0,reactivity_esm_bundler/* ref */.KR)(-1);
    //判断按钮显示
    const forumEnter = index => {
      forumShowIndex.value = index;
    };
    const forumLeave = () => {
      forumShowIndex.value = -1;
    };
    const forumAddRef = (0,reactivity_esm_bundler/* ref */.KR)();
    //发布新贴
    const addClick = () => {
      forumAddRef.value.init();
    };
    //修改帖子
    const editClick = (id = null) => {
      forumAddRef.value.init(id);
    };
    //返回
    const backClick = () => {
      router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`);
    };
    //初始化
    const init = () => {
      if (route.query.centerType) {
        centerType.value = true;
      }
      if (route.query.myType) {
        myType.value = true;
      }
      userid.value = context?.$toolUtil.storageGet('userid');
      getList();
      getCategoryList();
    };
    const detailForm = (0,reactivity_esm_bundler/* ref */.KR)({});
    //详情
    const detailClick = (id = null) => {
      if (id) {
        router.push(`${listvue_type_script_setup_true_lang_js_tableName}Detail?id=` + id + (centerType.value ? '&&centerType=1' : ''));
      }
    };
    //删除
    const delClick = (id = null) => {
      if (id) {
        ElMessageBox.confirm(`是否删除选中帖子`, '提示', {
          confirmButtonText: '是',
          cancelButtonText: '否',
          type: 'warning'
        }).then(() => {
          context?.$http({
            url: `forum/delete`,
            method: 'post',
            data: [id]
          }).then(res => {
            context?.$toolUtil.message('删除成功', 'success', () => {
              getList();
            });
          });
        }).catch(_ => {});
      }
    };
    const reportDialog = (0,reactivity_esm_bundler/* ref */.KR)({
      visible: false,
      data: null
    });
    const showDialog = item => {
      context.$http.get('/forumreport/list?forumId=11&status=已处理&sort=id&order=desc').then(res => {
        if (res.data.code == 0 && res.data.data.list?.length) {
          reportDialog.value.data = res.data.data.list[0];
          reportDialog.value.visible = true;
        }
      });
    };
    init();
    const getExcerpt = html => {
      if (!html) return '';
      const text = String(html).replace(/<[^>]*>/g, ' ').replace(/&nbsp;/g, ' ').replace(/\s+/g, ' ').trim();
      if (!text) return '';
      return text.length > 60 ? `${text.slice(0, 60)}...` : text;
    };
    return (_ctx, _cache) => {
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      const _component_el_input = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-input");
      const _component_el_pagination = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-pagination");
      const _component_el_form_item = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form-item");
      const _component_uploads = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("uploads");
      const _component_el_form = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-form");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-dialog");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", listvue_type_script_setup_true_lang_js_hoisted_1, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_2, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_3, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_4, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_5, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(MessageSquare), {
        class: "w-6 h-6 text-white"
      })]), _cache[7] || (_cache[7] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", null, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "text-2xl font-bold text-slate-900"
      }, "互动交流"), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "text-sm text-slate-500"
      }, "Forum & Community")], -1))]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_6, [centerType.value ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
        key: 0,
        class: "back_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200",
        onClick: backClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[8] || (_cache[8] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("返回")])),
        _: 1,
        __: [8]
      })) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_7, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("aside", _hoisted_8, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_9, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_10, [_cache[9] || (_cache[9] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "text-sm font-semibold text-slate-900"
      }, "论坛分类", -1)), (0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Tag), {
        class: "w-4 h-4 text-slate-500"
      })]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("button", {
        type: "button",
        class: (0,shared_esm_bundler/* normalizeClass */.C4)(["w-full mb-4 h-11 rounded-lg font-semibold transition-all duration-200 flex items-center justify-center gap-2", !myType.value && btnAuth('forum', '新增') ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20 hover:bg-orange-600' : 'bg-slate-100 text-slate-400 cursor-not-allowed']),
        onClick: _cache[0] || (_cache[0] = $event => !myType.value && btnAuth('forum', '新增') ? addClick() : null)
      }, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Plus), {
        class: "w-4 h-4"
      }), _cache[10] || (_cache[10] = (0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 发布新帖 "))], 2), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_11, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("button", {
        type: "button",
        class: (0,shared_esm_bundler/* normalizeClass */.C4)(["w-full h-10 px-3 rounded-lg flex items-center gap-3 text-sm font-medium border transition-all duration-200", categoryIndex.value == -1 ? 'bg-orange-50 text-orange-600 border-orange-200' : 'bg-white text-slate-700 border-slate-200 hover:bg-slate-50']),
        onClick: _cache[1] || (_cache[1] = $event => categoryClick(-1))
      }, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Layers), {
        class: "w-4 h-4"
      }), _cache[11] || (_cache[11] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", {
        class: "flex-1 text-left"
      }, "全部", -1))], 2), ((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(categoryList.value, (item, index) => {
        return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("button", {
          type: "button",
          key: index,
          class: (0,shared_esm_bundler/* normalizeClass */.C4)(["w-full h-10 px-3 rounded-lg flex items-center gap-3 text-sm font-medium border transition-all duration-200", categoryIndex.value == index ? 'bg-orange-50 text-orange-600 border-orange-200' : 'bg-white text-slate-700 border-slate-200 hover:bg-slate-50']),
          onClick: $event => categoryClick(index)
        }, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Tag), {
          class: "w-4 h-4"
        }), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", {
          class: "flex-1 text-left truncate",
          title: item
        }, (0,shared_esm_bundler/* toDisplayString */.v_)(item), 9, _hoisted_13)], 10, _hoisted_12);
      }), 128))])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("section", _hoisted_14, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_15, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_16, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_17, [_cache[12] || (_cache[12] = (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
        class: "text-sm font-medium text-slate-700 mb-2"
      }, "标题", -1)), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_input, {
        class: "search_inp w-full",
        modelValue: searchQuery.value.title,
        "onUpdate:modelValue": _cache[2] || (_cache[2] = $event => searchQuery.value.title = $event),
        placeholder: "请输入标题关键词",
        clearable: "",
        "prefix-icon": "Search"
      }, null, 8, ["modelValue"])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_18, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
        class: "search_btn_primary rounded-lg shadow-sm hover:shadow-md transition-all duration-200",
        type: "primary",
        onClick: searchClick
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[13] || (_cache[13] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("搜索")])),
        _: 1,
        __: [13]
      })])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_19, [((0,runtime_core_esm_bundler/* openBlock */.uX)(true), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, null, (0,runtime_core_esm_bundler/* renderList */.pI)(list.value, (item, index) => {
        return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", {
          class: "post-card",
          key: index,
          onMouseenter: $event => forumEnter(index),
          onMouseleave: forumLeave,
          onClick: (0,runtime_dom_esm_bundler/* withModifiers */.D$)($event => detailClick(item.id), ["stop"])
        }, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_21, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_22, [item.avatarurl ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("img", {
          key: 0,
          src: item.avatarurl && item.avatarurl.substring(0, 4) == 'http' ? item.avatarurl : baseUrl.value + item.avatarurl,
          alt: ""
        }, null, 8, _hoisted_23)) : ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("img", _hoisted_24))])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_25, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
          class: "post-title",
          title: item.title
        }, (0,shared_esm_bundler/* toDisplayString */.v_)(item.title), 9, _hoisted_26), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", {
          class: "post-excerpt",
          title: getExcerpt(item.content)
        }, (0,shared_esm_bundler/* toDisplayString */.v_)(getExcerpt(item.content)), 9, _hoisted_27), item.typeName ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_28, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", _hoisted_29, (0,shared_esm_bundler/* toDisplayString */.v_)(item.typeName), 1)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_30, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", _hoisted_31, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(user/* default */.A), {
          class: "meta-icon"
        }), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", null, "发布人：" + (0,shared_esm_bundler/* toDisplayString */.v_)(item.isAnonymous == 1 && item.userid != userid.value ? '匿名' : item.username), 1)]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", _hoisted_32, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Clock), {
          class: "meta-icon"
        }), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("span", null, (0,shared_esm_bundler/* toDisplayString */.v_)(item.addtime), 1)])])]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_33, [item.imgUrls && item.imgUrls.length ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_34, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("img", {
          src: item.imgUrls[0],
          alt: ""
        }, null, 8, _hoisted_35)])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), item.userid == userid.value && forumShowIndex.value == index && (btnAuth('forum', '修改') || btnAuth('forum', '删除')) ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_36, [centerType.value && item.isDel > 0 ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
          key: 0,
          class: "forum_del_btn",
          type: "danger",
          onClick: (0,runtime_dom_esm_bundler/* withModifiers */.D$)($event => showDialog(item), ["stop"])
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[14] || (_cache[14] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("内容违规-已删除")])),
          _: 2,
          __: [14]
        }, 1032, ["onClick"])) : ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)(runtime_core_esm_bundler/* Fragment */.FK, {
          key: 1
        }, [btnAuth('forum', '修改') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
          key: 0,
          class: "forum_edit_btn",
          type: "primary",
          onClick: (0,runtime_dom_esm_bundler/* withModifiers */.D$)($event => editClick(item.id), ["stop"])
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[15] || (_cache[15] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("修改")])),
          _: 2,
          __: [15]
        }, 1032, ["onClick"])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true), btnAuth('forum', '删除') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
          key: 1,
          class: "forum_del_btn",
          type: "danger",
          onClick: (0,runtime_dom_esm_bundler/* withModifiers */.D$)($event => delClick(item.id), ["stop"])
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[16] || (_cache[16] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("删除")])),
          _: 2,
          __: [16]
        }, 1032, ["onClick"])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)], 64))])) : ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("div", _hoisted_37, (0,shared_esm_bundler/* toDisplayString */.v_)(item.addtime), 1))])], 40, _hoisted_20);
      }), 128))]), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_38, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_pagination, {
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
      }, null, 8, ["layout", "total", "page-size", "current-page"])])])]), !myType.value && btnAuth('forum', '新增') ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createElementBlock */.CE)("button", {
        key: 0,
        type: "button",
        class: "fab",
        onClick: addClick,
        "aria-label": "发布新帖"
      }, [(0,runtime_core_esm_bundler/* createVNode */.bF)((0,reactivity_esm_bundler/* unref */.R1)(Plus), {
        class: "fab-icon"
      })])) : (0,runtime_core_esm_bundler/* createCommentVNode */.Q3)("", true)])]), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_dialog, {
        title: "处理结果",
        modelValue: reportDialog.value.visible,
        "onUpdate:modelValue": _cache[5] || (_cache[5] = $event => reportDialog.value.visible = $event),
        width: "50%"
      }, {
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form, null, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
            label: "举报原因",
            "label-width": "80px"
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(reportDialog.value.data.reason), 1)]),
            _: 1
          }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
            label: "补充截图",
            "label-width": "80px"
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_uploads, {
              action: "file/upload",
              tip: "请上传封面",
              style: {
                "width": "100%",
                "text-align": "left"
              },
              disabled: "",
              fileUrls: reportDialog.value.data.images ? reportDialog.value.data.images : ''
            }, null, 8, ["fileUrls"])]),
            _: 1
          }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_form_item, {
            label: "处理建议",
            "label-width": "80px"
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createTextVNode */.eW)((0,shared_esm_bundler/* toDisplayString */.v_)(reportDialog.value.data.suggestion), 1)]),
            _: 1
          })]),
          _: 1
        }), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_39, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          type: "primary",
          onClick: _cache[4] || (_cache[4] = $event => reportDialog.value.visible = false)
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[17] || (_cache[17] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)("关 闭")])),
          _: 1,
          __: [17]
        })])]),
        _: 1
      }, 8, ["modelValue"]), (0,runtime_core_esm_bundler/* createVNode */.bF)(add, {
        ref_key: "forumAddRef",
        ref: forumAddRef,
        onClose: _cache[6] || (_cache[6] = $event => getList())
      }, null, 512)], 64);
    };
  }
});
;// ./src/views/pages/forum/list.vue?vue&type=script&setup=true&lang=js
 
;// ./node_modules/mini-css-extract-plugin/dist/loader.js??clonedRuleSet-22.use[0]!./node_modules/css-loader/dist/cjs.js??clonedRuleSet-22.use[1]!./node_modules/vue-loader/dist/stylePostLoader.js!./node_modules/postcss-loader/dist/cjs.js??clonedRuleSet-22.use[2]!./node_modules/sass-loader/dist/cjs.js??clonedRuleSet-22.use[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/pages/forum/list.vue?vue&type=style&index=0&id=eb66a376&lang=scss&scoped=true
// extracted by mini-css-extract-plugin

;// ./src/views/pages/forum/list.vue?vue&type=style&index=0&id=eb66a376&lang=scss&scoped=true

// EXTERNAL MODULE: ./node_modules/vue-loader/dist/exportHelper.js
var exportHelper = __webpack_require__(6262);
;// ./src/views/pages/forum/list.vue



;


const list_exports_ = /*#__PURE__*/(0,exportHelper/* default */.A)(listvue_type_script_setup_true_lang_js, [['__scopeId',"data-v-eb66a376"]])

/* harmony default export */ var list = (list_exports_);

/***/ }),

/***/ 7731:
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   A: function() { return /* binding */ User; }
/* harmony export */ });
/* harmony import */ var _createLucideIcon_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(6422);
/**
 * @license lucide-vue-next v0.564.0 - ISC
 *
 * This source code is licensed under the ISC license.
 * See the LICENSE file in the root directory of this source tree.
 */



const User = (0,_createLucideIcon_js__WEBPACK_IMPORTED_MODULE_0__/* ["default"] */ .A)("user", [
  ["path", { d: "M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2", key: "975kel" }],
  ["circle", { cx: "12", cy: "7", r: "4", key: "17ys0d" }]
]);


//# sourceMappingURL=user.js.map


/***/ }),

/***/ 8441:
/***/ (function(__unused_webpack_module, __unused_webpack___webpack_exports__, __webpack_require__) {


// UNUSED EXPORTS: default

// EXTERNAL MODULE: ./node_modules/@vue/runtime-core/dist/runtime-core.esm-bundler.js
var runtime_core_esm_bundler = __webpack_require__(641);
// EXTERNAL MODULE: ./node_modules/@vue/reactivity/dist/reactivity.esm-bundler.js
var reactivity_esm_bundler = __webpack_require__(953);
// EXTERNAL MODULE: ./node_modules/axios/lib/axios.js + 40 modules
var axios = __webpack_require__(2586);
;// ./node_modules/thread-loader/dist/cjs.js!./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[1]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/components/img.vue?vue&type=script&setup=true&lang=js

const _hoisted_1 = {
  class: "box"
};
const _hoisted_2 = ["width", "height"];
const _hoisted_3 = ["width", "height"];
const _hoisted_4 = {
  style: {
    "display": "flex",
    "justify-content": "flex-end"
  }
};


/* harmony default export */ var imgvue_type_script_setup_true_lang_js = ({
  __name: 'img',
  emits: ['imgChange'],
  setup(__props, {
    expose: __expose,
    emit: __emit
  }) {
    const context = (0,runtime_core_esm_bundler/* getCurrentInstance */.nI)()?.appContext.config.globalProperties;
    const visible = (0,reactivity_esm_bundler/* ref */.KR)(false); //弹窗
    const loading = (0,reactivity_esm_bundler/* ref */.KR)(false); //上传按钮加载
    const os = (0,reactivity_esm_bundler/* ref */.KR)(false); //控制摄像头开关
    const thisVideo = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const thisContext = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const thisCancas = (0,reactivity_esm_bundler/* ref */.KR)(null);
    const videoWidth = (0,reactivity_esm_bundler/* ref */.KR)(500);
    const videoHeight = (0,reactivity_esm_bundler/* ref */.KR)(400);
    const postOptions = (0,reactivity_esm_bundler/* ref */.KR)([]);
    const CertCtl = (0,reactivity_esm_bundler/* ref */.KR)('');
    const imgSrc = (0,reactivity_esm_bundler/* ref */.KR)('');
    const emit = __emit;
    const getActionUrl = (0,runtime_core_esm_bundler/* computed */.EW)(() => {
      return context?.$config.name + '/file/upload';
    });
    const uploadSuccess = e => {
      visible.value = false;
      emit('imgChange', e.file);
    };
    // 调用摄像头权限
    const getCompetence = () => {
      //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
      (0,runtime_core_esm_bundler/* nextTick */.dY)(() => {
        os.value = false; //切换成关闭摄像头
        thisCancas.value = document.getElementById('canvasCamera');
        thisContext.value = document.getElementById('canvasCamera').getContext('2d');
        thisVideo.value = document.getElementById('videoCamera');
        // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
        if (navigator.mediaDevices === undefined) {
          navigator.menavigatordiaDevices = {};
        }
        // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
        // 使用getUserMedia，因为它会覆盖现有的属性。
        // 这里，如果缺少getUserMedia属性，就添加它。
        if (navigator.mediaDevices.getUserMedia === undefined) {
          navigator.mediaDevices.getUserMedia = function (constraints) {
            // 首先获取现存的getUserMedia(如果存在)
            let getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia;
            // 有些浏览器不支持，会返回错误信息
            // 保持接口一致
            if (!getUserMedia) {
              return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
            }
            // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
            return new Promise(function (resolve, reject) {
              getUserMedia.call(navigator, constraints, resolve, reject);
            });
          };
        }
        const constraints = {
          audio: false,
          video: {
            width: videoWidth.value,
            height: videoHeight.value,
            transform: 'scaleX(-1)'
          }
        };
        navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
          // 旧的浏览器可能没有srcObject
          if ('srcObject' in thisVideo.value) {
            thisVideo.value.srcObject = stream;
          } else {
            // 避免在新的浏览器中使用它，因为它正在被弃用。
            thisVideo.value.src = window.URL.createObjectURL(stream);
          }
          thisVideo.value.onloadedmetadata = function (e) {
            thisVideo.value.play();
          };
        }).catch(err => {
          context?.$toolUtil.notify('警告', '没有开启摄像头权限或浏览器版本不兼容.', 'warning');
        });
      });
    };
    /*调用摄像头拍照开始*/
    const onTake = () => {
      visible.value = true;
      getCompetence();
    };

    //声明父级调用
    __expose({
      onTake
    });
    const base64toFile = (dataurl, filename = 'file') => {
      let arr = dataurl.split(',');
      let mime = arr[0].match(/:(.*?);/)[1];
      let suffix = mime.split('/')[1];
      let bstr = atob(arr[1]);
      let n = bstr.length;
      let u8arr = new Uint8Array(n);
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }
      let file = new File([u8arr], `${filename}.${suffix}`, {
        type: mime
      });
      return file;
    };
    const uploadImg = () => {
      let param = new FormData();
      param.append('file', base64toFile(imgSrc.value));
      axios/* default */.A.post(getActionUrl.value, param, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        emit('imgChange', res.data.file);
      });
    };
    const onCancel = () => {
      if (!imgSrc.value) {
        return context.$message.error('请先选择人脸图片');
      }
      uploadImg();
      visible.value = false;
      /* this.resetCanvas();*/
      stopNavigator();
    };
    const drawImage = () => {
      // 点击，canvas画图
      thisContext.value.drawImage(thisVideo.value, 0, 0, videoWidth.value, videoHeight.value);
      // 获取图片base64链接
      imgSrc.value = thisCancas.value.toDataURL('image/png');
      /*const imgSrc=this.imgSrc;*/
    };
    //清空画布
    const clearCanvas = id => {
      let c = document.getElementById(id);
      let cxt = c.getContext("2d");
      cxt.clearRect(0, 0, c.width, c.height);
    };
    //重置画布
    const resetCanvas = () => {
      imgSrc.value = "";
      clearCanvas('canvasCamera');
    };
    //关闭摄像头
    const stopNavigator = () => {
      if (thisVideo.value && thisVideo.value !== null) {
        thisVideo.value.srcObject.getTracks()[0].stop();
        os.value = true; //切换成打开摄像头
      }
    };
    return (_ctx, _cache) => {
      const _component_el_button = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-button");
      const _component_el_upload = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-upload");
      const _component_el_dialog = (0,runtime_core_esm_bundler/* resolveComponent */.g2)("el-dialog");
      return (0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_dialog, {
        title: "拍照上传",
        modelValue: visible.value,
        "onUpdate:modelValue": _cache[0] || (_cache[0] = $event => visible.value = $event),
        width: "1065px"
      }, {
        footer: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_4, [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_upload, {
          action: getActionUrl.value,
          "on-success": uploadSuccess,
          "show-file-list": false,
          accept: ".jpg,.png,.jpge",
          style: {
            "margin-right": "10px"
          }
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
            size: "small"
          }, {
            default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[1] || (_cache[1] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 上传图片 ")])),
            _: 1,
            __: [1]
          })]),
          _: 1
        }, 8, ["action"]), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          onClick: drawImage,
          size: "small"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[2] || (_cache[2] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 拍照 ")])),
          _: 1,
          __: [2]
        }), os.value ? ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
          key: 0,
          onClick: getCompetence,
          size: "small"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[3] || (_cache[3] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 打开摄像头 ")])),
          _: 1,
          __: [3]
        })) : ((0,runtime_core_esm_bundler/* openBlock */.uX)(), (0,runtime_core_esm_bundler/* createBlock */.Wv)(_component_el_button, {
          key: 1,
          onClick: stopNavigator,
          size: "small"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[4] || (_cache[4] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 关闭摄像头 ")])),
          _: 1,
          __: [4]
        })), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          onClick: resetCanvas,
          size: "small"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[5] || (_cache[5] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 重置 ")])),
          _: 1,
          __: [5]
        }), (0,runtime_core_esm_bundler/* createVNode */.bF)(_component_el_button, {
          onClick: onCancel,
          size: "small"
        }, {
          default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => _cache[6] || (_cache[6] = [(0,runtime_core_esm_bundler/* createTextVNode */.eW)(" 完成 ")])),
          _: 1,
          __: [6]
        })])]),
        default: (0,runtime_core_esm_bundler/* withCtx */.k6)(() => [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("div", _hoisted_1, [(0,runtime_core_esm_bundler/* createElementVNode */.Lk)("video", {
          id: "videoCamera",
          class: "canvas",
          width: videoWidth.value,
          height: videoHeight.value,
          autoPlay: ""
        }, null, 8, _hoisted_2), (0,runtime_core_esm_bundler/* createElementVNode */.Lk)("canvas", {
          id: "canvasCamera",
          class: "canvas",
          width: videoWidth.value,
          height: videoHeight.value
        }, null, 8, _hoisted_3)])]),
        _: 1
      }, 8, ["modelValue"]);
    };
  }
});
;// ./src/components/img.vue?vue&type=script&setup=true&lang=js
 
;// ./node_modules/mini-css-extract-plugin/dist/loader.js??clonedRuleSet-22.use[0]!./node_modules/css-loader/dist/cjs.js??clonedRuleSet-22.use[1]!./node_modules/vue-loader/dist/stylePostLoader.js!./node_modules/postcss-loader/dist/cjs.js??clonedRuleSet-22.use[2]!./node_modules/sass-loader/dist/cjs.js??clonedRuleSet-22.use[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/components/img.vue?vue&type=style&index=0&id=1013dedc&rel=stylesheet%2Fscss&lang=scss&scoped=true
// extracted by mini-css-extract-plugin

;// ./src/components/img.vue?vue&type=style&index=0&id=1013dedc&rel=stylesheet%2Fscss&lang=scss&scoped=true

// EXTERNAL MODULE: ./node_modules/vue-loader/dist/exportHelper.js
var exportHelper = __webpack_require__(6262);
;// ./src/components/img.vue



;


const __exports__ = /*#__PURE__*/(/* unused pure expression or super */ null && (exportComponent(script, [['__scopeId',"data-v-1013dedc"]])))

/* harmony default export */ var img = ((/* unused pure expression or super */ null && (__exports__)));

/***/ })

}]);
//# sourceMappingURL=7555.23c5f5a1.js.map