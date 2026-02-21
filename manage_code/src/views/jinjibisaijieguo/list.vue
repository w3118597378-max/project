<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
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
					<div class="search_view">
						<div class="search_label">
							主队球队：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.zhuduiqiudui"
								placeholder="主队球队"
								>
								<el-option v-for="item in zhuduiqiuduiLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							客队球队：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.keduiqiudui"
								placeholder="客队球队"
								>
								<el-option v-for="item in keduiqiuduiLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('jinjibisaijieguo','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('jinjibisaijieguo','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('jinjibisaijieguo','查看')"
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
					prop="jinjisaishi"
					label="晋级赛事">
					<template #default="scope">
						<div class="event-highlight">
							<span class="event-dot"></span>
							<span class="event-text">{{scope.row.jinjisaishi}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="zhuduiqiudui"
					label="主队球队">
					<template #default="scope">
						<div class="team-name">
							<span class="team-dot home-team"></span>
							<span class="team-text">{{scope.row.zhuduiqiudui}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="keduiqiudui"
					label="客队球队">
					<template #default="scope">
						<div class="team-name">
							<span class="team-dot away-team"></span>
							<span class="team-text">{{scope.row.keduiqiudui}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="zhuchangqiuyuan"
					label="主场球员">
					<template #default="scope">
						<div class="player-badge home-player">
							<span class="player-icon">🏠</span>
							<span class="player-text">{{scope.row.zhuchangqiuyuan}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="kechangqiuyuan"
					label="客场球员">
					<template #default="scope">
						<div class="player-badge away-player">
							<span class="player-icon">🏃</span>
							<span class="player-text">{{scope.row.kechangqiuyuan}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="zuizhongbifen"
					label="最终比分">
					<template #default="scope">
						<div class="score-display">
							<span class="score-versus">VS</span>
							<span class="score-text">{{scope.row.zuizhongbifen}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="zhengyiqingkuang"
					label="争议情况">
					<template #default="scope">
						<div class="dispute-badge">
							<span class="dispute-icon">⚠️</span>
							<span class="dispute-text">{{scope.row.zhengyiqingkuang}}</span>
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
					prop="caipanzhanghao"
					label="裁判账号">
					<template #default="scope">
						<div class="referee-info">
							<span class="referee-icon">👤</span>
							<span class="referee-text">{{scope.row.caipanzhanghao}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="caipanxingming"
					label="裁判姓名">
					<template #default="scope">
						<div class="referee-info">
							<span class="referee-icon">👤</span>
							<span class="referee-text">{{scope.row.caipanxingming}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('jinjibisaijieguo','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('jinjibisaijieguo','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('jinjibisaijieguo','删除')">
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
	const tableName = 'jinjibisaijieguo'
	const formName = '晋级比赛结果'
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
		if(searchQuery.value.jinjisaishi&&searchQuery.value.jinjisaishi!=''){
			params['jinjisaishi'] = '%' + searchQuery.value.jinjisaishi + '%'
		}
		if(searchQuery.value.zhuduiqiudui&&searchQuery.value.zhuduiqiudui!=''){
			params['zhuduiqiudui'] = searchQuery.value.zhuduiqiudui
		}
		if(searchQuery.value.keduiqiudui&&searchQuery.value.keduiqiudui!=''){
			params['keduiqiudui'] = searchQuery.value.keduiqiudui
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
	const zhuduiqiuduiLists = ref([])
	const getzhuduiqiuduiLists = () => {
		let url = 'option/qiuduipaixingbang/qiuduimingcheng'
		context.$http({
			url: url,
			method:'get',
		}).then(res=>{
			zhuduiqiuduiLists.value = res.data.data
		})
	}
	getzhuduiqiuduiLists()
	const keduiqiuduiLists = ref([])
	const getkeduiqiuduiLists = () => {
		let url = 'option/qiuduipaixingbang/qiuduimingcheng'
		context.$http({
			url: url,
			method:'get',
		}).then(res=>{
			keduiqiuduiLists.value = res.data.data
		})
	}
	getkeduiqiuduiLists()
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
		background: #fff7ed;
		color: #ea580c;
		font-weight: 700;
		font-size: 13px;
	}

	// 晋级赛事高亮样式
	.event-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.event-dot{
			width: 6px;
			height: 6px;
			border-radius: 50%;
			background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.event-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 球队名称样式
	.team-name{
		display: flex;
		align-items: center;
		gap: 8px;
		.team-dot{
			width: 8px;
			height: 8px;
			border-radius: 50%;
			flex-shrink: 0;
		}
		.home-team{
			background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.away-team{
			background: linear-gradient(135deg, #64748b 0%, #94a3b8 100%);
			box-shadow: 0 2px 4px rgba(100, 116, 139, 0.25);
		}
		.team-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 最终比分Badge样式
	.score_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 50px;
		height: 32px;
		padding: 0 16px;
		background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
		color: #ffffff;
		border: none;
		border-radius: 16px;
		font-weight: 700;
		font-size: 16px;
		letter-spacing: 0.02em;
		box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
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
			background-color: #fffaf5;
		}
		
		// Hover效果
		.el-table__row:hover {
			background-color: #fff2e6 !important;
		}
		
		.el-table__row td.el-table__cell{
			border-bottom: 1px solid #f1f5f9;
		}

		// 晋级赛事列更突出
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}

		// 主队/客队球队列突出
		.el-table__body tbody tr td:nth-child(4) .cell,
		.el-table__body tbody tr td:nth-child(5) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}

		// 主场/客场球员弱化
		.el-table__body tbody tr td:nth-child(6) .cell,
		.el-table__body tbody tr td:nth-child(7) .cell {
			color: #64748b;
			font-size: 13px;
		}

		// 最终比分列最突出
		.el-table__body tbody tr td:nth-child(8) .cell {
			font-weight: 700;
			font-size: 16px;
		}

		// 争议情况列突出
		.el-table__body tbody tr td:nth-child(9) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}

		// 提交时间弱化
		.el-table__body tbody tr td:nth-child(10) .cell,
		.el-table__body tbody tr td:nth-child(11) .cell,
		.el-table__body tbody tr td:nth-child(12) .cell {
			color: #64748b;
			font-size: 13px;
		}
	}

		// 裁判信息列弱化
		:deep(.el-table){
			.el-table__body tbody tr td:nth-child(10) .cell {
				font-weight: 500;
				color: #64748b;
				font-size: 13px;
			}
			.el-table__body tbody tr td:nth-child(11) .cell {
				font-weight: 500;
				color: #64748b;
				font-size: 13px;
			}
		}

	// 按钮美化
	:deep(.el-button) {
		border-radius: 8px;
		font-weight: 500;
	}
	:deep(.el-button--primary) {
		background: #f97316 !important;
		border-color: #f97316 !important;
		box-shadow: 0 2px 4px rgba(249, 115, 22, 0.2);
	}
	:deep(.el-button--primary:hover) {
		background: #ea580c !important;
		border-color: #ea580c !important;
	}
	:deep(.el-button--success) {
		background: #10b981 !important;
		border-color: #10b981 !important;
		box-shadow: 0 2px 4px rgba(16, 185, 129, 0.2);
	}
	:deep(.el-button--danger) {
		border-radius: 8px;
		box-shadow: 0 2px 4px rgba(239, 68, 68, 0.2);
	}
	:deep(.el-button--info) {
		background: #6b7280 !important;
		border-color: #6b7280 !important;
		box-shadow: 0 2px 4px rgba(107, 114, 128, 0.2);
	}
</style>