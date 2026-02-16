<template>
	<div class="min-h-screen bg-slate-50">
		<div class="max-w-7xl mx-auto px-6 py-10">
			<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 flex items-center justify-between hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="flex items-center gap-4">
					<div class="w-12 h-12 rounded-lg bg-gradient-to-br from-orange-500 to-orange-400 shadow-lg shadow-orange-500/25 flex items-center justify-center">
						<Trophy class="w-6 h-6 text-white" />
					</div>
					<div>
						<div class="text-2xl font-bold text-slate-900">篮球赛事</div>
						<div class="text-sm text-slate-500">Basketball Events</div>
					</div>
				</div>
				<div class="flex items-center gap-3">
					<el-button class="rounded-lg shadow-sm hover:shadow-md transition-all duration-200" v-if="btnAuth('lanqiusaishi','新增')" @click="addClick">新增</el-button>
					<el-button v-if="centerType" class="back_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="backClick">返回</el-button>
				</div>
			</div>

			<div class="filter-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
					<div class="search_field">
						<div class="text-sm font-medium text-slate-700 mb-2">赛事名称</div>
						<el-input class="search_inp w-full" v-model="searchQuery.saishimingcheng" placeholder="请输入赛事名称" clearable prefix-icon="Search" />
					</div>
					<div class="search_field">
						<div class="text-sm font-medium text-slate-700 mb-2">比赛场地</div>
						<el-input class="search_inp w-full" v-model="searchQuery.bisaichangdi" placeholder="请输入比赛场地" clearable prefix-icon="Search" />
					</div>
					<div class="flex justify-end gap-3">
						<el-button class="search_btn_primary rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="searchClick">搜索</el-button>
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

			<div class="events-grid">
				<div class="event-card" v-for="(item,index) in list" :key="item.id" @click.stop="detailClick(item.id)">
					<div class="event-image">
						<img :src="item.imgUrls[0]" @click.stop="preViewClick(item.imgUrls[0])">
						<div class="event-badge" :class="index === 0 ? 'badge-hot' : 'badge-default'">{{ index === 0 ? '推荐' : '赛事' }}</div>
					</div>
					<div class="event-content">
						<div class="event-title" :title="item.saishimingcheng">{{item.saishimingcheng}}</div>
						<div class="event-meta">
							<div class="meta-item" :title="item.bisaishijian">
								<Calendar class="meta-icon" />
								<span> {{item.bisaishijian}}</span>
							</div>
							<div class="meta-item" :title="item.bisaichangdi">
								<MapPin class="meta-icon" />
								<span> {{item.bisaichangdi}}</span>
							</div>
						</div>
						<div class="event-actions">
							<div class="collect-pill" v-if="item.storeupNumber">
								<span class="iconfont icon-likeline4"></span>
								<span class="collect-num">{{item.storeupNumber}}</span>
							</div>
							<button type="button" class="detail-btn" @click.stop="detailClick(item.id)">
								查看详情
								<ArrowRight class="btn-icon" />
							</button>
						</div>
					</div>
				</div>
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
	import { Trophy, Calendar, MapPin, ArrowRight } from 'lucide-vue-next';
	//基础信息
	const tableName = 'lanqiusaishi'
	const formName = '篮球赛事'

	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: 10
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
		router.push('/index/lanqiusaishiAdd')
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
		if(searchQuery.value.saishimingcheng&&searchQuery.value.saishimingcheng!=''){
			params.saishimingcheng = '%' + searchQuery.value.saishimingcheng + '%'
		}
		if(searchQuery.value.bisaichangdi&&searchQuery.value.bisaichangdi!=''){
			params.bisaichangdi = '%' + searchQuery.value.bisaichangdi + '%'
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
                if(item.fengmian!=null){
                    item.imgUrls = item.fengmian.split(',').map(url=> {
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
	const detailClick = (id) => {
		router.push(`${tableName}Detail?id=` + id + (centerType.value?'&&centerType=1':''))
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
	.filter-card {
		.search_inp {
			:deep(.el-input__wrapper) {
				background: #fff;
				border: 1px solid var(--slate-200);
				box-shadow: 0 1px 2px rgba(0, 0, 0, 0.04);
				border-radius: 12px;
				transition: all 0.2s ease;
			}
			:deep(.el-input__wrapper.is-focus) {
				box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.2);
				border-color: rgba(249, 115, 22, 0.35);
			}
		}
	}

	.search_btn_primary {
		:deep(.el-button) {
			background: var(--orange-500);
			border-color: var(--orange-500);
			color: #fff;
			font-weight: 700;
			padding: 10px 18px;
			border-radius: 12px;
			box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
		}
		:deep(.el-button:hover) {
			background: var(--orange-600);
			border-color: var(--orange-600);
		}
	}

	.events-grid {
		display: grid;
		grid-template-columns: repeat(1, minmax(0, 1fr));
		gap: 24px;
	}

	@media (min-width: 768px) {
		.events-grid {
			grid-template-columns: repeat(2, minmax(0, 1fr));
		}
	}

	@media (min-width: 1024px) {
		.events-grid {
			grid-template-columns: repeat(3, minmax(0, 1fr));
		}
	}

	.event-card {
		background: #fff;
		border: 1px solid var(--slate-200);
		border-radius: 16px;
		overflow: hidden;
		box-shadow: var(--card-shadow);
		transition: all 0.25s ease;
		cursor: pointer;
	}

	.event-card:hover {
		transform: translateY(-4px);
		box-shadow: var(--card-hover-shadow);
		border-color: rgba(249, 115, 22, 0.35);
	}

	.event-image {
		position: relative;
		aspect-ratio: 16/9;
		background: var(--slate-50);
		overflow: hidden;
	}

	.event-image img {
		width: 100%;
		height: 100%;
		object-fit: cover;
		transition: transform 0.35s ease;
	}

	.event-card:hover .event-image img {
		transform: scale(1.05);
	}

	.event-badge {
		position: absolute;
		top: 12px;
		right: 12px;
		padding: 6px 12px;
		border-radius: 999px;
		font-size: 12px;
		font-weight: 800;
		color: #fff;
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
	}

	.badge-hot {
		background: rgba(249, 115, 22, 0.95);
	}

	.badge-default {
		background: rgba(51, 65, 85, 0.82);
	}

	.event-content {
		padding: 18px 18px 16px;
		display: flex;
		flex-direction: column;
		gap: 12px;
	}

	.event-title {
		font-size: 16px;
		font-weight: 800;
		color: var(--slate-900);
		line-height: 1.35;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.event-meta {
		display: flex;
		flex-direction: column;
		gap: 8px;
	}

	.meta-item {
		display: flex;
		align-items: center;
		gap: 8px;
		font-size: 13px;
		color: var(--slate-700);
		opacity: 0.9;
		overflow: hidden;
	}

	.meta-item span {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.meta-icon {
		width: 14px;
		height: 14px;
		color: var(--orange-500);
		flex: 0 0 auto;
	}

	.event-actions {
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 12px;
		margin-top: 2px;
	}

	.collect-pill {
		display: inline-flex;
		align-items: center;
		gap: 6px;
		padding: 6px 10px;
		border-radius: 999px;
		background: #fff7ed;
		border: 1px solid rgba(249, 115, 22, 0.2);
		color: var(--orange-500);
		font-weight: 800;
		flex: 0 0 auto;
	}

	.detail-btn {
		display: inline-flex;
		align-items: center;
		gap: 8px;
		padding: 8px 14px;
		border-radius: 12px;
		background: #fff7ed;
		border: 1px solid rgba(249, 115, 22, 0.22);
		color: var(--orange-600);
		font-weight: 800;
		font-size: 13px;
		transition: all 0.2s ease;
		cursor: pointer;
	}

	.detail-btn:hover {
		background: var(--orange-500);
		border-color: var(--orange-500);
		color: #fff;
		transform: translateY(-1px);
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
	}

	.btn-icon {
		width: 14px;
		height: 14px;
	}

	.pagination-container {
		:deep(.el-pager li.is-active) {
			background-color: var(--orange-500) !important;
			color: #fff !important;
		}
		:deep(.el-pager li:hover) {
			color: var(--orange-500);
		}
		:deep(.btn-prev:hover),
		:deep(.btn-next:hover) {
			color: var(--orange-500);
		}
	}
</style>