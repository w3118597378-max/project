<template>
    <div class="page_shell">
        <div class="page_top_line"></div>
        <div class="page_container">
            <div class="page_header">
                <div class="page_header__left">
                    <div class="page_icon">
                        <Settings class="page_icon__svg" />
                    </div>
                    <div class="page_titles">
                        <div class="page_title">菜单权限管理</div>
                        <div class="page_subtitle">管理系统菜单结构、角色权限与显示顺序</div>
                    </div>
                </div>
                <div class="page_header__right">
                    <el-button class="btn_primary" @click="save" v-if="btnAuth('menu','菜单管理')||btnAuth('menu','修改')">保存修改</el-button>
                </div>
            </div>

            <div class="table_card" v-if="btnAuth('menu','菜单管理')||btnAuth('menu','修改')">
                <el-tabs class="custom_tabs" v-model="role_index" @tab-change="tabClick">
                    <template v-for="(role,r_index) in menus" :key="role.roleName">
                        <el-tab-pane :label="role.roleName" :name="r_index">
                            <div v-if="r_index==role_index" class="menu_content">
                                <el-collapse v-model="collapse_default" class="custom_collapse">
                                    <el-collapse-item v-for="(table,t_index) in role.backMenu" :name="table.menu">
                                        <template #title>
                                            <div class="collapse_title_view">
                                                <div class="menu_name">{{table.menu}}</div>
                                                <div class="menu_actions">
                                                    <el-button class="btn_secondary" size="small" @click.stop="moveUp(role.backMenu,t_index)">
                                                        <ArrowUp class="page_action_icon" />上移
                                                    </el-button>
                                                    <el-button class="btn_secondary" size="small" @click.stop="changeName(table,role.backMenu)">
                                                        <Edit3 class="page_action_icon" />修改菜单名
                                                    </el-button>
                                                </div>
                                            </div>
                                        </template>
                                        <el-table
                                            :data="table.child"
                                            :show-header="false"
                                            class="inner_table">
                                            <el-table-column
                                                prop="menu"
                                                label="菜单名"
                                                width="180">
                                                <template #default="scope">
                                                    <span class="sub_menu_name">{{scope.row.menu}}</span>
                                                </template>
                                            </el-table-column>
                                            <el-table-column label="权限">
                                                <template #default="scope">
                                                    <el-checkbox-group v-model="scope.row.buttons" class="custom_checkbox_group">
                                                        <el-checkbox v-for="item in scope.row.allButtons" :label="item" :value="item" />
                                                    </el-checkbox-group>
                                                </template>
                                            </el-table-column>
                                            <el-table-column label="操作" width="300" align="right">
                                                <template #default="scope">
                                                    <el-button class="btn_secondary" size="small" @click="moveUp(table.child,scope.$index)">
                                                        上移
                                                    </el-button>
                                                    <el-button class="btn_secondary" size="small" @click="changeName(scope.row,table.child)">
                                                        修改菜单名
                                                    </el-button>
                                                    <el-button class="btn_secondary"
                                                               size="small"
                                                               @click="toMove(t_index,scope.$index)">
                                                        修改父级
                                                    </el-button>
                                                </template>
                                            </el-table-column>
                                        </el-table>
                                    </el-collapse-item>
                                </el-collapse>
                            </div>
                        </el-tab-pane>
                    </template>
                </el-tabs>
            </div>
        </div>

        <el-dialog
            title="修改父级菜单"
            v-model="dialogVisible"
            width="30%">
            <div style="display: flex;justify-content: center;align-items: center;margin-bottom: 20px">
                <div>父级菜单：</div>
                <el-select v-model="n_first_index" placeholder="请选择父级菜单" style="width: 200px">
                    <el-option
                        v-for="(item,index) in menus[role_index].backMenu"
                        :key="item.menu"
                        :label="item.menu"
                        :value="index">
                    </el-option>
                </el-select>
            </div>
            <div style="width: 100%;text-align: center">
                <el-button type="primary" @click="moveTo">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script setup>
import {getCurrentInstance,ref} from "vue";
import { Settings, ArrowUp, Edit3 } from 'lucide-vue-next'

const context = getCurrentInstance()?.appContext.config.globalProperties;

//权限验证
const btnAuth = (e,a)=>{
    return context?.$toolUtil.isAuth(e,a)
}

const menus = ref([])
const role_index = ref(0)   //当前选中的角色index
const first_index = ref(0) //当前选中的一级菜单index
const collapse_default = ref([])    //展开的collapse
const getMenus = ()=>{
    context.$http.get('menu/lists').then(res=>{
        menus.value = JSON.parse(res.data.data[0].menujson)
        collapse_default.value = menus.value[role_index.value].backMenu.map(item=>item.menu)
    })
}
getMenus()

const tabClick = ()=>{
    collapse_default.value = menus.value[role_index.value].backMenu.map(item=>item.menu)
}

const save = ()=>{
    context.$http.post('menu/update',{
        id:1,
        menujson:JSON.stringify(menus.value)
    }).then(res=>{
        if(res.data.code==0){
            ElMessage.success('保存成功，重新登录后生效')
        }
    })
}

const dialogVisible = ref(false)
const n_first_index = ref(null)     //选中的父级菜单
const o_index = ref(0)      //需要移动的二级菜单的index
const toMove = (t_index,index)=>{
    first_index.value = t_index
    n_first_index.value = t_index
    o_index.value = index
    dialogVisible.value = true
}
const moveTo = ()=>{
    let items = menus.value[role_index.value].backMenu[first_index.value].child.splice(o_index.value,1)
    menus.value[role_index.value].backMenu[n_first_index.value].child.push(...items)
    dialogVisible.value = false
}
const changeName = (row,arr)=>{
    ElMessageBox.prompt('请输入新菜单名','修改菜单名',{
        inputValidator:(value)=>{
            if(!value || !value.trim())return '请输入菜单名'
            if(arr.find(item=>item.menu==value.trim())){
                return '该菜单名已存在'
            }
        },
    }).then(({value})=>{
        row.menu = value.trim()
    }).catch(()=>{})
}
const moveUp = (arr,index)=>{
    if(index==0)return
    arr.splice(index-1,0, ...arr.splice(index,1))
    arr = JSON.parse(JSON.stringify(arr))
}

</script>

<style scoped lang="scss">
    .page_shell{
        width: 100%;
        background: #f8fafc;
        border-radius: 18px;
        border: 1px solid #e2e8f0;
        overflow: hidden;
    }
    .page_top_line{
        height: 1px;
        background: linear-gradient(90deg, rgba(249,115,22,0) 0%, rgba(249,115,22,1) 35%, rgba(234,88,12,1) 65%, rgba(249,115,22,0) 100%);
    }
    .page_container{
        max-width: 1280px;
        margin: 0 auto;
        padding: 40px 24px;
    }
    .page_header{
        display: flex;
        align-items: flex-start;
        justify-content: space-between;
        gap: 16px;
        margin-bottom: 24px;
    }
    .page_header__left{
        display: flex;
        align-items: center;
        gap: 14px;
    }
    .page_icon{
        width: 48px;
        height: 48px;
        border-radius: 14px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
        box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.20), 0 4px 6px -4px rgba(249, 115, 22, 0.20);
        color: #fff;
        flex: 0 0 auto;
    }
    .page_icon__svg{
        width: 22px;
        height: 22px;
    }
    .page_title{
        font-size: 28px;
        font-weight: 800;
        color: #0f172a;
        line-height: 1.15;
    }
    .page_subtitle{
        margin-top: 6px;
        font-size: 12px;
        color: #64748b;
        line-height: 1.25;
    }
    .btn_primary{
        border-radius: 12px;
        background: #f97316 !important;
        border-color: #f97316 !important;
        color: #fff !important;
        box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.20), 0 4px 6px -4px rgba(249, 115, 22, 0.20);
        padding: 8px 20px;
        height: 40px;
    }
    .btn_primary:hover{
        background: #ea580c !important;
        border-color: #ea580c !important;
    }
    .btn_secondary{
        border-radius: 10px;
        background: #fff !important;
        border-color: #e2e8f0 !important;
        color: #334155 !important;
        font-size: 12px;
    }
    .btn_secondary:hover{
        background: #f8fafc !important;
        border-color: #cbd5e1 !important;
    }
    .page_action_icon{
        width: 14px;
        height: 14px;
        margin-right: 4px;
    }
    .table_card{
        background: #fff;
        border: 1px solid #e2e8f0;
        border-radius: 16px;
        box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
        overflow: hidden;
        padding: 0;
    }
    :deep(.custom_tabs){
        .el-tabs__header{
            margin: 0;
            background: #f8fafc;
            border-bottom: 1px solid #e2e8f0;
            padding: 8px 16px 0;
        }
        .el-tabs__nav-wrap::after{
            display: none;
        }
        .el-tabs__item{
            font-weight: 600;
            color: #64748b;
            &.is-active{
                color: #f97316;
            }
        }
        .el-tabs__active-bar{
            background-color: #f97316;
            height: 3px;
            border-radius: 3px 3px 0 0;
        }
    }
    .menu_content{
        padding: 24px;
    }
    .custom_collapse{
        border: none;
        :deep(.el-collapse-item__header){
            height: auto;
            line-height: 1.5;
            padding: 16px;
            background: #fff;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            margin-bottom: 12px;
            transition: all 0.2s;
            &:hover{
                border-color: #cbd5e1;
                background: #f8fafc;
            }
            &.is-active{
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
                margin-bottom: 0;
                background: #fdfaf8;
                border-color: #fed7aa;
            }
        }
        :deep(.el-collapse-item__wrap){
            border: 1px solid #fed7aa;
            border-top: none;
            border-radius: 0 0 12px 12px;
            margin-bottom: 16px;
            overflow: hidden;
        }
        :deep(.el-collapse-item__content){
            padding: 0;
        }
    }
    .collapse_title_view{
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-right: 12px;
    }
    .menu_name{
        font-weight: 700;
        font-size: 16px;
        color: #1e293b;
    }
    .inner_table{
        width: 100%;
        background: #fff;
        :deep(.el-table__row){
            background: transparent;
        }
        :deep(td.el-table__cell){
            padding: 12px 16px;
            border-bottom: 1px solid #fff7ed;
        }
    }
    .sub_menu_name{
        font-weight: 500;
        color: #475569;
    }
    .custom_checkbox_group{
        display: flex;
        flex-wrap: wrap;
        gap: 8px;
        :deep(.el-checkbox){
            margin-right: 12px;
            .el-checkbox__label{
                font-size: 12px;
            }
            .el-checkbox__inner{
                border-radius: 4px;
            }
            &.is-checked .el-checkbox__inner{
                background-color: #f97316;
                border-color: #f97316;
            }
        }
    }
</style>