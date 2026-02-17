<template>
    <div class="list-page">
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view" v-if="centerType">
                <el-button class="back_btn" @click="backClick">返回</el-button>
            </div>
        </div>
		<div class="list_search">
			<div class="search_view">
				<div class="search_label">
					赛事名称：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.saishimingcheng" placeholder="赛事名称"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_view">
				<div class="search_label">
					晋级赛事：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.jinjisaishi" placeholder="晋级赛事"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" v-if="btnAuth('jinjishenqing','新增')" @click="addClick">新增</el-button>
			</div>
		</div>



                <div class="table_view">
					<el-table v-loading="listLoading" class="data_table" :data="list" border :row-style="{'cursor':'pointer'}"
						@row-click="tableDetailClick" :stripe='false'>
						<el-table-column :resizable='true' align="center" header-align="center" type="selection" width="55" />
						<el-table-column label="序号" width="120" :resizable='true' align="center" header-align="center">
							<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
						</el-table-column>
						<el-table-column label="赛事名称" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.saishimingcheng}}
							</template>
						</el-table-column>
						<el-table-column label="球队名称" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuduimingcheng}}
							</template>
						</el-table-column>
						<el-table-column label="球队积分" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuduijifen}}
							</template>
						</el-table-column>
						<el-table-column label="比赛排名" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.bisaipaiming}}
							</template>
						</el-table-column>
						<el-table-column label="裁判账号" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.caipanzhanghao}}
							</template>
						</el-table-column>
						<el-table-column label="裁判姓名" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.caipanxingming}}
							</template>
						</el-table-column>
						<el-table-column label="晋级赛事" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.jinjisaishi}}
							</template>
						</el-table-column>
						<el-table-column label="申请时间" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.shenqingshijian}}
							</template>
						</el-table-column>
						<el-table-column label="队长账号" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.duizhangzhanghao}}
							</template>
						</el-table-column>
						<el-table-column label="队长姓名" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.duizhangxingming}}
							</template>
						</el-table-column>
						<el-table-column label="审核回复" v-if="centerType" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.shhf}}
							</template>
						</el-table-column>
						<el-table-column label="审核状态" v-if="centerType" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								<el-tag type="success" v-if="scope.row.sfsh=='是'">通过</el-tag>
								<el-tag type="danger" v-if="scope.row.sfsh=='否'">未通过</el-tag>
								<el-tag type="warning" v-if="scope.row.sfsh=='待审核'">待审核</el-tag>
							</template>
						</el-table-column>
					</el-table>
                </div>

				<el-pagination
					background
					:layout="layouts.join(',')"
					:total="total"
					:page-size="listQuery.limit"
                    v-model:current-page="listQuery.page"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					@size-change="sizeChange"
					@current-change="currentChange"/>


    </div>

    <el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
        <div style="text-align:center">
            <img :src="preViewUrl" style="max-width: 100%;" alt="">
        </div>
    </el-dialog>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
        computed,
        inject,
	} from 'vue';
    const moment = window.moment
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
    const baseUrl = ref(context.$config.url)
	//基础信息
	const tableName = 'jinjishenqing'
	const formName = '晋级申请'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: 20,
	})
	const total = ref(0)
	const listLoading = ref(false)
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	const addClick = () => {
		router.push('/index/jinjishenqingAdd')
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	//搜索
	const searchQuery = ref({})
	//下拉列表
	const searchClick = async() => {
		listQuery.value.page = 1
		getList()
	}
	//分页
	const layouts = ref(["total","prev","pager","next","sizes"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.saishimingcheng&&searchQuery.value.saishimingcheng!=''){
			params.saishimingcheng = '%' + searchQuery.value.saishimingcheng + '%'
		}
		if(searchQuery.value.jinjisaishi&&searchQuery.value.jinjisaishi!=''){
			params.jinjisaishi = '%' + searchQuery.value.jinjisaishi + '%'
		}
		if(!centerType.value){
			params['sfsh'] = '是';
		} 
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	const tableDetailClick = (row) => {
		router.push(`${tableName}Detail?id=` + row.id + (centerType.value?'&&centerType=1':''))
	}
	//下载文件
	const download = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		const a = document.createElement('a');
		a.style.display = 'none';
		a.setAttribute('target', '_blank');
		file && a.setAttribute('download', file);
		a.href = context?.$config.url + file;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	}
	// 查看大图
	const preViewUrl = ref('')
	const preViewVisible = ref(false)
	const preViewClick = (url) =>{
		preViewUrl.value = url
		preViewVisible.value = true
	}
	const init = async() => {
		if(route.query.centerType){
			centerType.value = true
		}
        if(context.$toolUtil.storageGet('frontToken') && !user.value.id){
            await store.dispatch("user/getSession")
        }
		getList()
	}
	init()
</script>

<style lang="scss" scoped>
    /* 搜索区域美化 - 橙色主题 */
    .list_search {
        /* ... */
        padding: 18px 20px;
        border-radius: 12px;
        border: 1px solid #e2e8f0;
        margin-bottom: 24px;
        box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        justify-content: flex-end;
        gap: 14px;
        flex-wrap: wrap;

        .search_view {
            display: flex;
            align-items: center;
            gap: 10px;

            .search_label {
                color: #475569;
                font-size: 14px;
                font-weight: 500;
                padding: 6px 10px;
                background: #fff7ed;
                border: 1px solid #ffedd5;
                border-radius: 999px;
            }
        }

        .search_box {
            width: 200px;
        }

        .search_btn_view {
            display: flex;
            gap: 12px;

            .search_btn {
                background-color: #f97316 !important;
                border-color: #f97316 !important;
                border-radius: 8px;
                height: 36px;
                padding: 0 18px;
                font-weight: 500;

                &:hover {
                    background-color: #ea580c !important;
                }
            }

            .add_btn {
                background-color: #ffffff !important;
                color: #f97316 !important;
                border: 1.5px solid #f97316 !important;
                border-radius: 8px;
                height: 36px;
                padding: 0 18px;
                font-weight: 600;

                &:hover {
                    background-color: #fff7ed !important;
                }
            }
        }
    }

    /* 表格区域美化 */
    .table_view {
        background: #ffffff;
        border-radius: 12px;
        border: 1px solid #e2e8f0;
        overflow: hidden;
        box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);

        :deep(.el-table) {
            --el-table-border-color: #f1f5f9;
            --el-table-header-bg-color: #f8fafc;
            font-size: 14px;
            color: #334155;

            &.el-table--border {
                border: 0;
            }

            th.el-table__cell {
                padding: 12px 0;
                font-weight: 600;
                color: #64748b;
                font-size: 12px;
                border-bottom: 1px solid #e2e8f0;
            }

            td.el-table__cell {
                padding: 10px 0;
                border-bottom: 1px solid #f1f5f9;
            }

            .cell {
                padding-left: 12px;
                padding-right: 12px;
            }

            /* 斑马纹 */
            .el-table__body tbody tr:nth-child(even) {
                background-color: #fffaf5;
            }

            /* hover效果 */
            .el-table__row {
                transition: background-color 0.18s ease;
                &:hover {
                    background-color: #fff2e6 !important;
                }
            }

            /* 复选框 */
            .el-checkbox__inner {
                border-radius: 6px;
            }
            .el-checkbox__input.is-checked .el-checkbox__inner {
                background-color: #f97316;
                border-color: #f97316;
            }
        }
    }

    /* 信息层级：主信息突出 */
    :deep(.el-table) {
        /* 赛事名称、球队名称、晋级赛事 */
        .el-table__body tbody tr td:nth-child(3) .cell,
        .el-table__body tbody tr td:nth-child(4) .cell,
        .el-table__body tbody tr td:nth-child(8) .cell {
            font-weight: 600;
            color: #1e293b;
        }

        /* 积分、排名做轻量胶囊 */
        .el-table__body tbody tr td:nth-child(5) .cell,
        .el-table__body tbody tr td:nth-child(6) .cell {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-width: 32px;
            height: 22px;
            padding: 0 8px;
            background-color: #f1f5f9;
            color: #64748b;
            border: 1px solid #e2e8f0;
            border-radius: 11px;
            font-weight: 500;
            font-size: 13px;
        }

        /* 时间、账号类信息弱化 */
        .el-table__body tbody tr td:nth-child(9) .cell,
        .el-table__body tbody tr td:nth-child(10) .cell,
        .el-table__body tbody tr td:nth-child(11) .cell {
            color: #94a3b8;
            font-size: 12px;
        }
    }
</style>