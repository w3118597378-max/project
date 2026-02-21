<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							帖子标题：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.title" placeholder="帖子标题"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							状态：
						</div>
						<div class="search_box">
							<el-select
									class="search_sel"
									clearable
									v-model="searchQuery.status"
									placeholder="请选择"
							>
								<el-option label="待处理" value="待处理"></el-option>
								<el-option label="已处理" value="已处理"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('forum_report','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('forum_report','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('forum_report','查看')"
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
					prop="title"
					label="帖子标题">
					<template #default="scope">
						<div class="title-highlight">
							<span class="title-dot"></span>
							<span class="title-text">{{scope.row.title}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="reportUsername"
					label="举报人账号">
					<template #default="scope">
						{{scope.row.reportUsername}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="reportedUsername"
					label="被举报人账号">
					<template #default="scope">
						{{scope.row.reportedUsername}}
					</template>
				</el-table-column>
				<el-table-column label="举报图片" min-width="140" width="120" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.images">
							<el-image v-if="scope.row.images.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.images.split(',')[0]]"
								:src="scope.row.images.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.images.split(',')[0]]"
								:src="$config.url+scope.row.images.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="status"
					label="处理状态">
					<template #default="scope">
						<span class="status_badge" :class="{
							'status_badge--pending': scope.row.status=='待处理',
							'status_badge--processed': scope.row.status=='已处理'
						}">{{ scope.row.status }}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="reportType"
					label="举报类型">
					<template #default="scope">
						<span class="type_pill">{{scope.row.reportType}}</span>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('forum_report','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('forum_report','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('forum_report','删除')">
							删除						</el-button>
						<el-button class="operate_btn" v-if="scope.row.status!='已处理'&&role=='users'" type="warning" @click="reportClick(scope.row)">
							举报审核
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
		<el-dialog :title="'举报审核'" v-model="reportVisible" width="50%">
			<el-form ref="form" label-width="100px">
				<el-form-item label="帖子标题">
					{{reportForm.title}}
				</el-form-item>
				<el-form-item label="举报人">
					{{reportForm.reportUsername}}
				</el-form-item>
				<el-form-item label="举报原因">
					{{reportForm.reason}}
				</el-form-item>
				<el-form-item label="图片补充">
					<el-image v-for="(item,index) in reportForm.picList" fit="cover" :src="item?baseUrl + item:''"
						style="width: 100px;height: 100px;margin: 0 5px 0 0"
						:preview-src-list="[item?baseUrl + item:'']"></el-image>
				</el-form-item>
				<el-form-item label="处理建议">
					<el-input type="textarea" v-model="reportForm.suggestion"></el-input>
				</el-form-item>
				<el-form-item label="是否违规">
					<el-switch
						v-model="reportForm.isDel"
						:active-value="1"
						:inactive-value="0"
						active-color="#00ad45"
						inactive-color="#ccc">
					</el-switch>
				</el-form-item>

			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="reportVisible=false">关闭</el-button>
				<el-button @click="reportSave()" type="primary">处理完成</el-button>
			</span>
		</el-dialog>
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
	const tableName = 'forumreport'
	const formName = '论坛举报'
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
		if(searchQuery.value.title&&searchQuery.value.title!=''){
			params['title'] = '%' + searchQuery.value.title + '%'
		}
		if(searchQuery.value.status!='' && searchQuery.value.status!=undefined) {
			params['status'] =searchQuery.value.status
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
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const reportVisible = ref(false)
	const reportForm = ref({})
	const reportClick = (row)=>{
		if(row.images){
			row.picList = row.images.split(',')
		}
		row.isDel = 0
		reportForm.value = row
		reportVisible.value = true
	}
	const reportSave = ()=>{
		reportForm.value.status = '已处理'
		context.$http.post('forumreport/update',reportForm.value).then(rs=>{
			if(reportForm.value.isDel){
				context.$http.get('forum/detail/' + reportForm.value.forumId).then(rs2=>{
					rs2.data.data.isDel = 1
					context.$http.post('forum/update',rs2.data.data).then(()=>{
						context.$message.success('审核成功！')
						reportVisible.value = false
					})
				})
			}else {
				context.$message.success('审核成功！')
				reportVisible.value = false
			}
		})
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

	// 帖子标题高亮样式
	.title-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.title-dot{
			width: 6px;
			height: 6px;
			border-radius: 50%;
			background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.title-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 状态Badge样式
	.status_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		padding: 6px 12px;
		border-radius: 999px;
		font-size: 12px;
		font-weight: 700;
		border: 1px solid transparent;
		white-space: nowrap;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}
	.status_badge--pending{
		background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
		color: #ffffff;
		border-color: #f59e0b;
		box-shadow: 0 2px 4px rgba(245, 158, 11, 0.25);
	}
	.status_badge--processed{
		background: linear-gradient(135deg, #10b981 0%, #059669 100%);
		color: #ffffff;
		border-color: #10b981;
		box-shadow: 0 2px 4px rgba(16, 185, 129, 0.25);
	}

	// 举报类型胶囊样式
	.type_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		padding: 4px 10px;
		border-radius: 999px;
		font-size: 12px;
		font-weight: 500;
		background-color: #f1f5f9;
		color: #64748b;
		border: 1px solid #e2e8f0;
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

		// 帖子标题列更突出
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}

		// 举报人/被举报人账号弱化
		.el-table__body tbody tr td:nth-child(4) .cell,
		.el-table__body tbody tr td:nth-child(5) .cell {
			color: #64748b;
			font-size: 13px;
		}

		// 举报图片优化
		.el-table__body tbody tr td:nth-child(6) .cell {
			.el-image {
				border-radius: 12px;
				box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			}
			div[style*="无图片"] {
				color: #94a3b8;
				font-size: 12px;
				font-style: italic;
			}
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
	:deep(.el-button--warning) {
		background: #f59e0b !important;
		border-color: #f59e0b !important;
		box-shadow: 0 2px 4px rgba(245, 158, 11, 0.2);
	}
	:deep(.el-button--info) {
		background: #6b7280 !important;
		border-color: #6b7280 !important;
		box-shadow: 0 2px 4px rgba(107, 114, 128, 0.2);
	}
</style>