<template>
	<div class="min-h-screen bg-slate-50">
		<div class="h-1 bg-gradient-to-r from-orange-500 to-orange-400"></div>
		<div class="max-w-7xl mx-auto px-6 py-10">
			<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 flex items-center justify-between hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="flex items-center gap-4">
					<div class="w-12 h-12 rounded-lg bg-gradient-to-br from-orange-500 to-orange-400 shadow-lg shadow-orange-500/25 flex items-center justify-center">
						<i class="el-icon-trophy" style="color:#fff;font-size:22px"></i>
					</div>
					<div>
						<div class="text-2xl font-bold text-slate-900">球队排行榜</div>
						<div class="text-sm text-slate-500">Competition Management</div>
					</div>
				</div>
				<div class="flex items-center gap-3">
					<el-button class="rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="backHomeClick">返回首页</el-button>
					<el-button v-if="centerType" class="back_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="backClick">返回</el-button>
				</div>
			</div>

			<div class="list_search bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
					<div class="search_field">
						<div class="text-sm font-medium text-slate-700 mb-2">球队名称</div>
						<el-input
							class="search_inp w-full"
							v-model="searchQuery.qiuduimingcheng"
							placeholder="请输入球队名称"
							clearable
							prefix-icon="Search"
						/>
					</div>
					<div class="search_field">
						<div class="text-sm font-medium text-slate-700 mb-2">队长姓名</div>
						<el-input
							class="search_inp w-full"
							v-model="searchQuery.duizhangxingming"
							placeholder="请输入队长姓名"
							clearable
							prefix-icon="Search"
						/>
					</div>
					<div class="flex justify-end gap-3">
						<el-button class="search_btn_primary rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="searchClick">搜索</el-button>
						<el-button class="add_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" v-if="btnAuth('qiuduipaixingbang','新增')" @click="addClick">新增</el-button>
					</div>
				</div>
			</div>

			<div class="sort-wrapper bg-white border border-slate-200 rounded-lg shadow-sm p-4 mb-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<el-button class="item storeup" @click="sortClick('storeupNumber')" :class="{active:sortType=='storeupNumber'}">
					<el-icon class="icon" v-if="sortType!='storeupNumber'"><DCaret /></el-icon>
					<el-icon class="icon desc" v-else-if="sortOrder=='desc'"><SortDown /></el-icon>
					<el-icon class="icon asc" v-else><SortUp /></el-icon>
					收藏数
				</el-button>
			</div>

			<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
				<div class="bg-white rounded-lg border border-slate-200 shadow-sm p-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
					<div class="text-slate-500 text-sm">总球队数</div>
					<div class="text-4xl font-bold text-slate-900 mt-2">{{ totalTeams }}</div>
					<div class="h-1 w-12 bg-orange-500 rounded-full mt-4"></div>
				</div>
				<div class="bg-white rounded-lg border border-slate-200 shadow-sm p-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
					<div class="text-slate-500 text-sm">总比赛场次</div>
					<div class="text-4xl font-bold text-slate-900 mt-2">{{ totalMatches }}</div>
					<div class="h-1 w-12 bg-orange-500 rounded-full mt-4"></div>
				</div>
				<div class="bg-white rounded-lg border border-slate-200 shadow-sm p-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
					<div class="text-slate-500 text-sm">胜率最高</div>
					<div class="text-4xl font-bold text-slate-900 mt-2">{{ bestWinRate }}</div>
					<div class="h-1 w-12 bg-orange-500 rounded-full mt-4"></div>
				</div>
				<div class="bg-white rounded-lg border border-slate-200 shadow-sm p-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
					<div class="text-slate-500 text-sm">总积分</div>
					<div class="text-4xl font-bold text-slate-900 mt-2">{{ totalPoints }}</div>
					<div class="h-1 w-12 bg-orange-500 rounded-full mt-4"></div>
				</div>
			</div>

			<div class="table_view bg-white border border-slate-200 rounded-lg shadow-sm p-2 overflow-hidden hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<el-table v-loading="listLoading" class="data_table ranking_table" :data="list" :row-style="{'cursor':'pointer'}"
					@row-click="tableDetailClick" :stripe='false'>
					<el-table-column fixed="left" :resizable='true' align="center" header-align="center" type="selection" width="55" />
					<el-table-column fixed="left" label="序号" width="96" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							<span
								class="inline-flex items-center justify-center min-w-[2.25rem] h-9 px-3 rounded-lg text-sm font-semibold"
								:class="scope.$index === 0 ? 'bg-orange-500 text-white shadow-sm shadow-orange-500/20' : 'bg-slate-100 text-slate-700'"
							>
								{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}
							</span>
						</template>
					</el-table-column>
					<el-table-column label="教练名称" width="140" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.jiaolianmingcheng}}
						</template>
					</el-table-column>
					<el-table-column fixed="left" label="球队名称" width="180" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.qiuduimingcheng}}
						</template>
					</el-table-column>
					<el-table-column label="球队logo" width="110" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							<div v-if="scope.row.qiuduilogo">
								<el-image v-if="scope.row.qiuduilogo.substring(0,4)=='http'" preview-teleported
									:preview-src-list="[scope.row.qiuduilogo.split(',')[0]]"
									:src="scope.row.qiuduilogo.split(',')[0]" style="width:48px;height:48px;border-radius:8px;border:1px solid #e2e8f0" @click.stop></el-image>
								<el-image v-else preview-teleported
									:preview-src-list="[baseUrl+scope.row.qiuduilogo.split(',')[0]]"
									:src="baseUrl+scope.row.qiuduilogo.split(',')[0]" style="width:48px;height:48px;border-radius:8px;border:1px solid #e2e8f0" @click.stop>
								</el-image>
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column label="球衣" width="110" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							<div v-if="scope.row.qiuyi">
								<el-image v-if="scope.row.qiuyi.substring(0,4)=='http'" preview-teleported
									:preview-src-list="[scope.row.qiuyi.split(',')[0]]"
									:src="scope.row.qiuyi.split(',')[0]" style="width:48px;height:48px;border-radius:8px;border:1px solid #e2e8f0" @click.stop></el-image>
								<el-image v-else preview-teleported
									:preview-src-list="[baseUrl+scope.row.qiuyi.split(',')[0]]"
									:src="baseUrl+scope.row.qiuyi.split(',')[0]" style="width:48px;height:48px;border-radius:8px;border:1px solid #e2e8f0" @click.stop>
								</el-image>
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column label="球员人数" width="110" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							{{scope.row.qiuyuanrenshu}}
						</template>
					</el-table-column>
					<el-table-column label="积分" width="100" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							<span class="text-orange-600 font-bold text-lg">{{scope.row.jifen}}</span>
						</template>
					</el-table-column>
					<el-table-column label="胜" width="80" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							{{scope.row.sheng}}
						</template>
					</el-table-column>
					<el-table-column label="负" width="80" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							{{scope.row.fu}}
						</template>
					</el-table-column>
					<el-table-column label="平" width="80" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							{{scope.row.ping}}
						</template>
					</el-table-column>
					<el-table-column label="胜率" width="120" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							<span class="inline-flex items-center px-2.5 py-1 rounded-full bg-green-50 text-green-600 text-sm font-semibold">
								{{scope.row.shenglv}}
							</span>
						</template>
					</el-table-column>
					<el-table-column label="是否在招募" width="120" :resizable='true' align="center" header-align="center" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.shifouzaizhaomu}}
						</template>
					</el-table-column>
					<el-table-column label="创立时间" width="140" :resizable='true' align="center" header-align="center" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.chuanglishijian}}
						</template>
					</el-table-column>
					<el-table-column label="球队口号" width="220" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.qiuduikouhao}}
						</template>
					</el-table-column>
					<el-table-column label="球员名单" width="240" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.qiuyuanmingdan}}
						</template>
					</el-table-column>
					<el-table-column label="队长账号" width="160" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.duizhangzhanghao}}
						</template>
					</el-table-column>
					<el-table-column label="队长姓名" width="140" :resizable='true' align="left" header-align="left" show-overflow-tooltip>
						<template #default="scope">
							{{scope.row.duizhangxingming}}
						</template>
					</el-table-column>
					<el-table-column label="评论数" width="110" :resizable='true' align="center" header-align="center">
						<template #default="scope">
							{{scope.row.discussNumber}}
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

			<div class="pagination-container bg-white border border-slate-200 rounded-lg shadow-sm p-4 mt-6 flex justify-center hover:shadow-md hover:border-orange-300 transition-all duration-200">
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
		</div>

		<el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
			<div style="text-align:center">
				<img :src="preViewUrl" style="max-width: 100%;" alt="">
			</div>
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		inject,
	} from 'vue';
	import moment from 'moment';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(() => store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
	const baseUrl = ref(context.$config.url)
	const backHomeClick = () => {
		router.push('/index/home')
	}
	const totalTeams = computed(() => {
		if (typeof total.value === 'number' && !Number.isNaN(total.value) && total.value > 0) return total.value
		return list.value?.length || 0
	})
	const totalMatches = computed(() => {
		if (!list.value?.length) return '--'
		let sum = 0
		for (const row of list.value) {
			const sheng = Number(row?.sheng)
			const fu = Number(row?.fu)
			const ping = Number(row?.ping)
			sum += (Number.isFinite(sheng) ? sheng : 0) + (Number.isFinite(fu) ? fu : 0) + (Number.isFinite(ping) ? ping : 0)
		}
		return Math.round(sum / 2)
	})
	const bestWinRate = computed(() => {
		if (!list.value?.length) return '--'
		let max = -Infinity
		for (const row of list.value) {
			const raw = row?.shenglv
			if (raw === undefined || raw === null || raw === '') continue
			const n = typeof raw === 'string' ? Number(raw.replace('%', '')) : Number(raw)
			if (Number.isFinite(n) && n > max) max = n
		}
		return Number.isFinite(max) ? `${max}%` : '--'
	})
	const totalPoints = computed(() => {
		if (!list.value?.length) return '--'
		let sum = 0
		for (const row of list.value) {
			const n = Number(row?.jifen)
			sum += Number.isFinite(n) ? n : 0
		}
		return sum
	})
	//基础信息
	const tableName = 'qiuduipaixingbang'
	const formName = '球队排行榜'
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
		router.push('/index/qiuduipaixingbangAdd')
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
    const sortType = ref('')
    const sortOrder = ref('')
    const sortClick = (type)=>{
        if(sortType.value==type && sortOrder.value=='asc'){
            sortType.value = ''
            sortOrder.value = ''
        }else if(sortType.value==type && sortOrder.value=='desc'){
            sortOrder.value = 'asc'
        }else{
            sortType.value = type
            sortOrder.value = 'desc'
        }
        getList()
    }
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.qiuduimingcheng&&searchQuery.value.qiuduimingcheng!=''){
			params.qiuduimingcheng = '%' + searchQuery.value.qiuduimingcheng + '%'
		}
        params['sort'] = 'jifen';
        params['order'] = 'desc';
		if(searchQuery.value.duizhangxingming&&searchQuery.value.duizhangxingming!=''){
			params.duizhangxingming = '%' + searchQuery.value.duizhangxingming + '%'
		}
		if(!centerType.value){
			params['sfsh'] = '是';
		} 
        if(sortType.value){
            params['sort'] = sortType.value
            params['order'] = sortOrder.value
        }
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
            list.value.forEach(item=>{
                if(item.qiuduilogo!=null){
                    item.imgUrls = item.qiuduilogo.split(',').map(url=> {
                        if(url && url.substr(0,4)=='http'){
                            return url
                        }else{
                            return baseUrl.value + url
                        }
                    })
                }else{
                    item.imgUrls = []
                }
            })
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
.list_search {
	.el-input__wrapper {
		background: #fff;
		border: 1px solid #e2e8f0;
		box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
		border-radius: 0.5rem;
		transition: all 0.2s ease-in-out;
	}
	.el-input__wrapper.is-focus {
		box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.2);
		border-color: #f97316;
	}
}

.search_btn_primary.el-button {
	background: #f97316;
	border-color: #f97316;
	color: #fff;
	box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.12), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
}

.search_btn_primary.el-button:hover {
	background: #ea580c;
	border-color: #ea580c;
	box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.16), 0 4px 6px -2px rgba(249, 115, 22, 0.1);
}

.ranking_table {
	--el-table-border-color: #e2e8f0;
	--el-table-header-bg-color: #f8fafc;

	::v-deep(.el-table__header-wrapper th.el-table__cell) {
		background: #f8fafc;
		border-bottom: 1px solid #e2e8f0;
		color: #475569;
		font-size: 12px;
		font-weight: 600;
		text-transform: uppercase;
		letter-spacing: 0.05em;
		padding: 12px 24px;
	}

	::v-deep(.el-table__body-wrapper td.el-table__cell) {
		padding: 16px 24px;
		border-bottom: 1px solid #f1f5f9;
	}

	::v-deep(.el-table__body tr:hover > td.el-table__cell) {
		background: #f8fafc;
		transition: background-color 0.2s ease;
	}
}
</style>