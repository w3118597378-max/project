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
							比赛时间：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.bisaishijian" placeholder="比赛时间"
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
							审核状态：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.sfsh"
								placeholder="审核状态"
								>
								<el-option v-for="item in approvalLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('bisaibaoming','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('bisaibaoming','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('bisaibaoming','查看')"
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
					prop="bisaichangdi"
					label="比赛场地">
					<template #default="scope">
						<span class="secondary_text">{{scope.row.bisaichangdi}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="180"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="cansaiduiyuan"
					label="参赛队员">
					<template #default="scope">
						<span class="player_badge">{{scope.row.cansaiduiyuan}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="baomingshijian"
					label="报名时间">
					<template #default="scope">
						<span class="time_pill">{{scope.row.baomingshijian}}</span>
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
						<span class="light_pill">{{scope.row.duizhangzhanghao}}</span>
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
						<div class="captain-highlight">
							<span class="captain-icon">👤</span>
							<span class="captain-text">{{scope.row.duizhangxingming}}</span>
						</div>
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
				<el-table-column label="审核回复" min-width="140" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<span class="light_pill">{{scope.row.shhf}}</span>
					</template>
				</el-table-column>
				<el-table-column prop="sfsh" label="审核状态" min-width="140" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
                        <div v-if="scope.row.sfsh=='是'" class="status-badge approved">
                            <span class="status-icon">✓</span>
                            <span class="status-text">通过</span>
                        </div>
                        <div v-else-if="scope.row.sfsh=='否'" class="status-badge rejected">
                            <span class="status-icon">✗</span>
                            <span class="status-text">未通过</span>
                        </div>
                        <div v-else-if="scope.row.sfsh=='待审核'" class="status-badge pending">
                            <span class="status-icon">⏳</span>
                            <span class="status-text">待审核</span>
                        </div>
					</template>
				</el-table-column>
				<el-table-column label="审核" v-if="btnAuth('bisaibaoming','审核')" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button v-if="scope.row.sfsh=='待审核'" size="small" @click="approvalClick(scope.row)">审核</el-button>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('bisaibaoming','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('bisaibaoming','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('bisaibaoming','删除')">
							删除						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('bisaibaoming','日程')" type="success" @click="jingsairichengCrossAddOrUpdateHandler(scope.row,'cross','是','','','')">
							日程
						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('bisaibaoming','记录')" type="success" @click="bisaijishutongjiCrossAddOrUpdateHandler(scope.row,'cross','是','','[1]','已记录')">
							记录
						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('bisaibaoming','成绩')" type="success" @click="bisaichengjiCrossAddOrUpdateHandler(scope.row,'cross','是','','','')">
							成绩
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
		<Approval ref="approvalRef" @approvalSave="approvalSave" :tableName="tableName">
		</Approval>
		<jingsairichengFormModel ref="jingsairichengFormModelRef" @formModelChange="formModelChange"></jingsairichengFormModel>
		<bisaijishutongjiFormModel ref="bisaijishutongjiFormModelRef" @formModelChange="formModelChange"></bisaijishutongjiFormModel>
		<bisaichengjiFormModel ref="bisaichengjiFormModelRef" @formModelChange="formModelChange"></bisaichengjiFormModel>
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
	const tableName = 'bisaibaoming'
	const formName = '比赛报名'
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
		if(searchQuery.value.bisaishijian&&searchQuery.value.bisaishijian!=''){
			params['bisaishijian'] = '%' + searchQuery.value.bisaishijian + '%'
		}
		if(searchQuery.value.bisaichangdi&&searchQuery.value.bisaichangdi!=''){
			params['bisaichangdi'] = '%' + searchQuery.value.bisaichangdi + '%'
		}
		if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
			params['sfsh'] = searchQuery.value.sfsh
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
    const approvalSave = async (form)=>{
		context.$http.post(`${tableName}/update`,form).then(res => {
            context.$message.success('审核成功')
            approvalRef.value.approvalVisible = false
			searchClick()
        })
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
	//审核
	import Approval from '@/components/common/approval.vue'
	const approvalRef = ref(null)
	const approvalClick = (row) => {
		nextTick(() => {
			approvalRef.value.approvalClick(row )
		})
	}
	import jingsairichengFormModel from '@/views/jingsairicheng/formModel'
	const jingsairichengFormModelRef = ref(null)
    const jingsairichengCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
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
			jingsairichengFormModelRef.value.init(row.id,'cross','日程',row,'bisaibaoming',statusColumnName,tips,statusColumnValue)
		})
    }
	import bisaijishutongjiFormModel from '@/views/bisaijishutongji/formModel'
	const bisaijishutongjiFormModelRef = ref(null)
    const bisaijishutongjiCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
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
			bisaijishutongjiFormModelRef.value.init(row.id,'cross','记录',row,'bisaibaoming',statusColumnName,tips,statusColumnValue)
		})
    }
	import bisaichengjiFormModel from '@/views/bisaichengji/formModel'
	const bisaichengjiFormModelRef = ref(null)
    const bisaichengjiCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
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
			bisaichengjiFormModelRef.value.init(row.id,'cross','成绩',row,'bisaibaoming',statusColumnName,tips,statusColumnValue)
		})
    }
	//查询审核状态列表
	const approvalLists = ref([])
	//初始化
	const init = () => {
        approvalLists.value = "是,否,待审核".split(',');
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

	// 参赛队员Badge样式
	.player_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 80px;
		max-width: 160px;
		height: auto;
		min-height: 32px;
		padding: 6px 16px;
		background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
		color: #ffffff;
		border-radius: 16px;
		font-weight: 700;
		font-size: 14px;
		box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		white-space: normal;
		word-break: break-all;
		line-height: 1.4;
		text-align: center;
	}

	// 队长姓名高亮样式
	.captain-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.captain-icon{
			font-size: 14px;
		}
		.captain-text{
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
			width: 6px;
			height: 6px;
			border-radius: 50%;
			background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.team-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 轻量胶囊样式
	.light_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 60px;
		height: 24px;
		padding: 0 12px;
		background-color: #f1f5f9;
		color: #64748b;
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		font-weight: 500;
		font-size: 13px;
		white-space: nowrap;
	}

	// 时间胶囊样式
	.time_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 80px;
		height: 24px;
		padding: 0 12px;
		background-color: #f8fafc;
		color: #64748b;
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		font-weight: 500;
		font-size: 12px;
		white-space: nowrap;
	}

	// 次要信息弱化样式
	.secondary_text{
		color: #64748b;
		font-size: 13px;
	}

	// 审核状态Badge样式
	.status-badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		gap: 6px;
		min-width: 80px;
		height: 28px;
		padding: 0 12px;
		border-radius: 14px;
		font-weight: 600;
		font-size: 12px;
		&.approved{
			background: linear-gradient(135deg, #10b981 0%, #059669 100%);
			color: #ffffff;
			box-shadow: 0 2px 4px rgba(16, 185, 129, 0.25);
		}
		&.rejected{
			background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
			color: #ffffff;
			box-shadow: 0 2px 4px rgba(239, 68, 68, 0.25);
		}
		&.pending{
			background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
			color: #ffffff;
			box-shadow: 0 2px 4px rgba(245, 158, 11, 0.25);
		}
		.status-icon{
			font-size: 14px;
		}
		.status-text{
			font-weight: 600;
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
			background-color: #fffaf5;
		}
		
		// Hover效果
		.el-table__row:hover {
			background-color: #fff2e6 !important;
		}
		
		.el-table__row td.el-table__cell{
			border-bottom: 1px solid #f1f5f9;
		}
	}

	// 赛事名称列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 参赛队员列最突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(6) .cell {
			font-weight: 700;
			color: #ea580c;
			font-size: 14px;
		}
	}

	// 队长账号列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(8) .cell {
			font-weight: 500;
			color: #475569;
			font-size: 13px;
		}
	}

	// 队长姓名列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(9) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 球队名称列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(10) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 裁判信息列弱化
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(11) .cell,
		.el-table__body tbody tr td:nth-child(12) .cell {
			color: #64748b;
			font-size: 13px;
		}
	}

	// 按钮美化
	:deep(.el-button) {
		border-radius: 12px;
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
		border-radius: 12px;
		box-shadow: 0 2px 4px rgba(239, 68, 68, 0.2);
	}
	:deep(.el-button--info) {
		background: #6b7280 !important;
		border-color: #6b7280 !important;
		box-shadow: 0 2px 4px rgba(107, 114, 128, 0.2);
	}
</style>