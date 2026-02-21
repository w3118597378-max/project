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
							球队名称：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.qiuduimingcheng" placeholder="球队名称"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('bisaichengji','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('bisaichengji','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('bisaichengji','查看')"
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
						<span class="secondary_text">{{scope.row.bisaichangdi}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="bisaishijian"
					label="比赛时间">
					<template #default="scope">
						<span class="time_pill">{{scope.row.bisaishijian}}</span>
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
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="qiuduijifen"
					label="球队积分">
					<template #default="scope">
						<span class="score_pill">{{scope.row.qiuduijifen}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="bisaipaiming"
					label="比赛排名">
					<template #default="scope">
						<div class="ranking-badge">
							<span class="ranking-number">{{scope.row.bisaipaiming}}</span>
							<span class="ranking-label">名</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="chengjibifen"
					label="成绩比分">
					<template #default="scope">
						<div class="score-display">
							<span class="score-value">{{scope.row.chengjibifen}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="bisaijieguo"
					label="比赛结果">
					<template #default="scope">
						<span v-if="scope.row.bisaijieguo=='胜'" class="result-badge win">
							<span class="result-icon">🏆</span>
							<span class="result-text">胜</span>
						</span>
						<span v-else-if="scope.row.bisaijieguo=='负'" class="result-badge lose">
							<span class="result-icon">📉</span>
							<span class="result-text">负</span>
						</span>
						<span v-else-if="scope.row.bisaijieguo=='平'" class="result-badge draw">
							<span class="result-icon">🤝</span>
							<span class="result-text">平</span>
						</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="teshushijian"
					label="特殊事件">
					<template #default="scope">
						<span class="light_pill">{{scope.row.teshushijian}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="panfashuoming"
					label="判罚说明">
					<template #default="scope">
						<span class="secondary_text">{{scope.row.panfashuoming}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="fabushijian"
					label="发布时间">
					<template #default="scope">
						<span class="secondary_text">{{scope.row.fabushijian}}</span>
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
						<span class="secondary_text">{{scope.row.caipanzhanghao}}</span>
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
						<span class="secondary_text">{{scope.row.caipanxingming}}</span>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('bisaichengji','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('bisaichengji','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('bisaichengji','删除')">
							删除						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('bisaichengji','申请')" type="success" @click="jinjishenqingCrossAddOrUpdateHandler(scope.row,'cross','','','','')">
							申请
						</el-button>
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
		<jinjishenqingFormModel ref="jinjishenqingFormModelRef" @formModelChange="formModelChange"></jinjishenqingFormModel>
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
	const tableName = 'bisaichengji'
	const formName = '比赛成绩'
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
		if(searchQuery.value.qiuduimingcheng&&searchQuery.value.qiuduimingcheng!=''){
			params['qiuduimingcheng'] = '%' + searchQuery.value.qiuduimingcheng + '%'
		}
		params['sort'] = 'qiuduijifen';
		params['order'] = 'desc';
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
	import jinjishenqingFormModel from '@/views/jinjishenqing/formModel'
	const jinjishenqingFormModelRef = ref(null)
    const jinjishenqingCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			jinjishenqingFormModelRef.value.init(row.id,'cross','申请',row,'bisaichengji',statusColumnName,tips,statusColumnValue)
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

	// 积分胶囊样式
	.score_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 60px;
		height: 24px;
		padding: 0 12px;
		background-color: #fef3c7;
		color: #92400e;
		border: 1px solid #fed7aa;
		border-radius: 12px;
		font-weight: 500;
		font-size: 13px;
	}

	// 排名徽章样式
	.ranking-badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		gap: 4px;
		min-width: 60px;
		height: 28px;
		padding: 0 12px;
		background: linear-gradient(135deg, #fed7aa 0%, #fbbf24 100%);
		color: #92400e;
		border-radius: 14px;
		font-weight: 600;
		font-size: 12px;
		.ranking-number{
			font-weight: 700;
		}
		.ranking-label{
			font-size: 11px;
			opacity: 0.8;
		}
	}

	// 成绩显示样式
	.score-display{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		.score-value{
			font-size: 18px;
			font-weight: 700;
			color: #f59e0b;
			background: linear-gradient(135deg, #fef3c7 0%, #fed7aa 100%);
			padding: 4px 12px;
			border-radius: 8px;
			border: 1px solid #fbbf24;
		}
	}

	// 比赛结果Badge样式
	.result-badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		gap: 6px;
		min-width: 60px;
		height: 28px;
		padding: 0 12px;
		border-radius: 14px;
		font-weight: 600;
		font-size: 12px;
		&.win{
			background: linear-gradient(135deg, #dcfce7 0%, #bbf7d0 100%);
			color: #166534;
			border: 1px solid #86efac;
		}
		&.lose{
			background: linear-gradient(135deg, #fef2f2 0%, #fecaca 100%);
			color: #991b1b;
			border: 1px solid #fca5a5;
		}
		&.draw{
			background: linear-gradient(135deg, #f0f9ff 0%, #e0f2fe 100%);
			color: #0369a1;
			border: 1px solid #7dd3fc;
		}
		.result-icon{
			font-size: 14px;
		}
		.result-text{
			font-weight: 600;
		}
	}

	// 时间胶囊样式
	.time_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 80px;
		height: 24px;
		padding: 0 12px;
		background-color: #f1f5f9;
		color: #64748b;
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		font-weight: 500;
		font-size: 12px;
		white-space: nowrap;
	}

	// 轻量胶囊样式
	.light_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 60px;
		height: 24px;
		padding: 0 12px;
		background-color: #f8fafc;
		color: #94a3b8;
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		font-weight: 500;
		font-size: 12px;
		white-space: nowrap;
	}

	// 次要信息弱化样式
	.secondary_text{
		color: #94a3b8;
		font-size: 13px;
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

	// 球队名称列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(5) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 积分列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(6) .cell {
			font-weight: 500;
			color: #92400e;
			font-size: 13px;
		}
	}

	// 排名列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(7) .cell {
			font-weight: 600;
			color: #92400e;
			font-size: 13px;
		}
	}

	// 成绩比分列最突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(8) .cell {
			font-weight: 700;
			color: #f59e0b;
			font-size: 16px;
		}
	}

	// 比赛结果列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(9) .cell {
			font-weight: 600;
			font-size: 13px;
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