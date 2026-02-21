<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
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
							比赛场地：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.bisaichangdi" placeholder="比赛场地"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							对阵队伍：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.duizhenduiwu"
								placeholder="对阵队伍"
								>
								<el-option v-for="item in duizhenduiwuLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							上场球员：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.xingming" placeholder="上场球员"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('jingsairicheng','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('jingsairicheng','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('jingsairicheng','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<div class="index_pill">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="saishimingcheng"
					label="赛事名称">
					<template #default="scope">
						<div class="event-highlight">
							<span class="event-icon">🏆</span>
							<span class="event-text">{{scope.row.saishimingcheng}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="bisaichangdi"
					label="比赛场地">
					<template #default="scope">
						<div class="venue-highlight">
							<span class="venue-icon">📍</span>
							<span class="venue-text">{{scope.row.bisaichangdi}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="220"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="bisaishijian"
					label="比赛时间">
					<template #default="scope">
						<div class="time-badge">
							<span class="time-icon">🕐</span>
							<span class="time-text">{{scope.row.bisaishijian}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="250"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="shoufazhenrong"
					label="首发阵容">
					<template #default="scope">
						<div class="lineup-badge">
							<span class="lineup-icon">👥</span>
							<span class="lineup-text">{{scope.row.shoufazhenrong}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="240"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="duizhenduiwu"
					label="对阵队伍">
					<template #default="scope">
						<div class="versus-badge">
							<span class="versus-icon">⚔️</span>
							<span class="versus-text">{{scope.row.duizhenduiwu}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="220"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="xingming"
					label="上场球员">
					<template #default="scope">
						<div class="player-badge">
							<span class="player-icon">🏃</span>
							<span class="player-text">{{scope.row.xingming}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="tijiaoshijian"
					label="提交时间">
					<template #default="scope">
						<span class="submit-time">{{scope.row.tijiaoshijian}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="duizhangzhanghao"
					label="队长账号">
					<template #default="scope">
						{{scope.row.duizhangzhanghao}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="duizhangxingming"
					label="队长姓名">
					<template #default="scope">
						{{scope.row.duizhangxingming}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="qiuduimingcheng"
					label="球队名称">
					<template #default="scope">
						<div class="team-highlight">
							<span class="team-dot"></span>
							<span class="team-text">{{scope.row.qiuduimingcheng}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('jingsairicheng','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('jingsairicheng','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('jingsairicheng','删除')">
							删除						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination
				background
				:layout="layouts.join(',')"
				:total="total"
				:page-size="listQuery.limit"
                v-model:current-page="listQuery.page"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:page-sizes="[10, 20, 30, 40, 50, 100]"
				@size-change="sizeChange"
				@current-change="currentChange"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	const moment = window.moment
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
		computed,
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = computed(()=>store.getters['user/avatar'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const baseUrl = ref(context.$config.url)
	import formModel from './formModel.vue'
	//基础信息
	const tableName = 'jingsairicheng'
	const formName = '竞赛日程'
	const route = useRoute()
    const router = useRouter()
	const role = context.$toolUtil.storageGet('sessionTable')
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			//table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.saishimingcheng&&searchQuery.value.saishimingcheng!=''){
			params['saishimingcheng'] = '%' + searchQuery.value.saishimingcheng + '%'
		}
		if(searchQuery.value.bisaichangdi&&searchQuery.value.bisaichangdi!=''){
			params['bisaichangdi'] = '%' + searchQuery.value.bisaichangdi + '%'
		}
		if(searchQuery.value.duizhenduiwu&&searchQuery.value.duizhenduiwu!=''){
			params['duizhenduiwu'] = searchQuery.value.duizhenduiwu
		}
		if(searchQuery.value.xingming&&searchQuery.value.xingming!=''){
			params['xingming'] = '%' + searchQuery.value.xingming + '%'
		}
		context.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id,row={}) => {
		let ids = []
		if (id) {
			ids = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		}).catch(_ => {})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//搜索
	const duizhenduiwuLists = ref([])
	const getduizhenduiwuLists = () => {
		let url = 'option/qiuduipaixingbang/qiuduimingcheng'
		context.$http({
			url: url,
			method:'get',
		}).then(res=>{
			duizhenduiwuLists.value = res.data.data
		})
	}
	getduizhenduiwuLists()
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = (id=null,row={})=>{
		if(id){
			formRef.value.init(id,'edit')
			return
		}
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}

	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	// 序号胶囊样式
	.index_pill{
		width: 36px;
		height: 36px;
		display: inline-flex;
		align-items: center;
		justify-content: center;
		border-radius: 10px;
		background: #f9fafb;
		color: #6b7280;
		font-weight: 600;
		font-size: 13px;
	}

	// 赛事名称高亮样式
	.event-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.event-icon{
			font-size: 14px;
		}
		.event-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 比赛场地高亮样式
	.venue-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.venue-icon{
			font-size: 14px;
		}
		.venue-text{
			font-weight: 500;
			color: #64748b;
			font-size: 13px;
		}
	}

	// 时间Badge样式
	.time-badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		gap: 8px;
		min-width: 120px;
		min-height: 32px;
		padding: 8px 16px;
		background: linear-gradient(135deg, #fef3c7 0%, #fed7aa 100%);
		color: #92400e;
		border: 1px solid #fbbf24;
		border-radius: 16px;
		font-weight: 600;
		font-size: 13px;
		white-space: normal;
		word-break: break-word;
		line-height: 1.4;
		.time-icon{
			font-size: 15px;
			flex-shrink: 0;
		}
		.time-text{
			font-weight: 600;
			text-align: left;
		}
	}

	// 首发阵容Badge样式
	.lineup-badge{
		display: inline-flex;
		align-items: center;
		justify-content: flex-start;
		gap: 8px;
		min-width: 150px;
		min-height: 32px;
		padding: 8px 16px;
		background: linear-gradient(135deg, #dcfce7 0%, #bbf7d0 100%);
		color: #166534;
		border: 1px solid #86efac;
		border-radius: 16px;
		font-weight: 600;
		font-size: 13px;
		white-space: normal;
		word-break: break-word;
		line-height: 1.4;
		.lineup-icon{
			font-size: 15px;
			flex-shrink: 0;
		}
		.lineup-text{
			font-weight: 600;
			text-align: left;
		}
	}

	// 对战Badge样式
	.versus-badge{
		display: inline-flex;
		align-items: center;
		justify-content: flex-start;
		gap: 8px;
		min-width: 140px;
		min-height: 32px;
		padding: 8px 16px;
		background: linear-gradient(135deg, #fef3c7 0%, #fed7aa 100%);
		color: #92400e;
		border: 1px solid #fbbf24;
		border-radius: 16px;
		font-weight: 600;
		font-size: 13px;
		white-space: normal;
		word-break: break-word;
		line-height: 1.4;
		.versus-icon{
			font-size: 15px;
			flex-shrink: 0;
		}
		.versus-text{
			font-weight: 600;
			text-align: left;
		}
	}

	// 球员Badge样式
	.player-badge{
		display: inline-flex;
		align-items: center;
		justify-content: flex-start;
		gap: 8px;
		min-width: 130px;
		min-height: 32px;
		padding: 8px 16px;
		background: linear-gradient(135deg, #dbeafe 0%, #bfdbfe 100%);
		color: #1e40af;
		border: 1px solid #93c5fd;
		border-radius: 16px;
		font-weight: 600;
		font-size: 13px;
		white-space: normal;
		word-break: break-word;
		line-height: 1.4;
		.player-icon{
			font-size: 15px;
			flex-shrink: 0;
		}
		.player-text{
			font-weight: 600;
			text-align: left;
		}
	}

	// 提交时间样式
	.submit-time{
		color: #94a3b8;
		font-size: 13px;
	}

	// 球队名称高亮样式
	.team-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.team-dot{
			width: 8px;
			height: 8px;
			border-radius: 50%;
			background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
		}
		.team-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 表格整体样式
	:deep(.el-table){
		--el-table-border-color: #f1f5f9;
		--el-table-header-bg-color: #f8fafc;
		font-size: 14px;
		color: #334155;
		font-variant-numeric: tabular-nums;
		
		.el-table__header-wrapper th.el-table__cell{
			background: #f8fafc;
			border-bottom: 1px solid #e2e8f0;
			color: #475569;
			font-size: 13px;
			font-weight: 700;
			text-transform: uppercase;
			letter-spacing: 0.04em;
		}
		
		// 斑马纹效果
		.el-table__body tbody tr:nth-child(even) {
			background-color: #f9fafb;
		}
		
		// Hover效果
		.el-table__row:hover {
			background-color: #f3f4f6 !important;
		}
		
		.el-table__row td.el-table__cell{
			border-bottom: 1px solid #f1f5f9;
		}
	}

	// 赛事名称列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 比赛时间列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(5) .cell {
			font-weight: 600;
			font-size: 13px;
		}
	}

	// 首发阵容列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(6) .cell {
			font-weight: 600;
			font-size: 13px;
		}
	}

	// 对战队伍列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(7) .cell {
			font-weight: 600;
			font-size: 13px;
		}
	}

	// 球队名称列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(11) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 按钮美化
	:deep(.el-button) {
		border-radius: 8px;
		font-weight: 500;
	}
	:deep(.el-button--primary) {
		background: #6366f1 !important;
		border-color: #6366f1 !important;
		box-shadow: 0 1px 3px rgba(99, 102, 241, 0.2);
	}
	:deep(.el-button--primary:hover) {
		background: #4f46e5 !important;
		border-color: #4f46e5 !important;
	}
	:deep(.el-button--success) {
		background: #10b981 !important;
		border-color: #10b981 !important;
		box-shadow: 0 1px 3px rgba(16, 185, 129, 0.2);
	}
	:deep(.el-button--danger) {
		border-radius: 8px;
		box-shadow: 0 1px 3px rgba(239, 68, 68, 0.2);
	}
	:deep(.el-button--info) {
		background: #6b7280 !important;
		border-color: #6b7280 !important;
		box-shadow: 0 1px 3px rgba(107, 114, 128, 0.2);
	}
</style>