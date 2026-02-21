<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							裁判账号：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.caipanzhanghao" placeholder="裁判账号"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('caipanyuan','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('caipanyuan','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('caipanyuan','查看')"
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
					prop="caipanzhanghao"
					label="裁判账号">
					<template #default="scope">
						{{scope.row.caipanzhanghao}}
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
						<div class="name-highlight">
							<span class="name-dot"></span>
							<span class="name-text">{{scope.row.caipanxingming}}</span>
						</div>
					</template>
				</el-table-column>
				<el-table-column label="头像" min-width="140" width="120" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.touxiang" class="avatar-container">
							<el-image v-if="scope.row.touxiang.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.touxiang.split(',')[0]]"
								:src="scope.row.touxiang.split(',')[0]" class="avatar-img"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.touxiang.split(',')[0]]"
								:src="$config.url+scope.row.touxiang.split(',')[0]" class="avatar-img">
							</el-image>
						</div>
						<div v-else class="no-avatar">无图片</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="xingbie"
					label="性别">
					<template #default="scope">
						<span class="gender_badge" :class="scope.row.xingbie === '男' ? 'male' : 'female'">{{scope.row.xingbie}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="shoujihaoma"
					label="手机号码">
					<template #default="scope">
						<span class="phone_pill">{{scope.row.shoujihaoma}}</span>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('caipanyuan','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('caipanyuan','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('caipanyuan','删除')">
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
	const tableName = 'caipanyuan'
	const formName = '裁判员'
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
		if(searchQuery.value.caipanzhanghao&&searchQuery.value.caipanzhanghao!=''){
			params['caipanzhanghao'] = '%' + searchQuery.value.caipanzhanghao + '%'
		}
		// 添加调试日志
		console.log('裁判员管理 - 请求参数:', params)
		console.log('裁判员管理 - 接口URL:', `${tableName}/page`)
		
		context.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			console.log('裁判员管理 - 接口响应:', res)
			listLoading.value = false
			
			// 检查数据结构
			if(res.data && res.data.data) {
				list.value = res.data.data.list || []
				total.value = Number(res.data.data.total) || 0
				console.log('裁判员管理 - 数据列表:', list.value)
				console.log('裁判员管理 - 总数:', total.value)
			} else {
				console.error('裁判员管理 - 数据结构异常:', res)
				list.value = []
				total.value = 0
			}
		}).catch(error => {
			console.error('裁判员管理 - 接口调用失败:', error)
			listLoading.value = false
			list.value = []
			total.value = 0
			context?.$toolUtil.message('数据加载失败', 'error')
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
	//初始化
	const init = () => {
		console.log('裁判员管理 - 页面初始化')
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

	// 裁判姓名高亮样式
	.name-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.name-dot{
			width: 6px;
			height: 6px;
			border-radius: 50%;
			background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.name-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 头像样式
	.avatar-container{
		display: flex;
		justify-content: center;
		align-items: center;
		.avatar-img{
			width: 60px;
			height: 60px;
			border-radius: 50%;
			object-fit: cover;
			border: 2px solid #f1f5f9;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			transition: all 0.3s ease;
			&:hover{
				transform: scale(1.05);
				border-color: #f97316;
				box-shadow: 0 4px 12px rgba(249, 115, 22, 0.2);
			}
		}
	}
	.no-avatar{
		width: 60px;
		height: 60px;
		border-radius: 50%;
		background: #f1f5f9;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #64748b;
		font-size: 12px;
		border: 2px solid #e2e8f0;
	}

	// 性别Badge样式
	.gender_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 32px;
		height: 24px;
		padding: 0 10px;
		border-radius: 12px;
		font-weight: 600;
		font-size: 12px;
		&.male{
			background: linear-gradient(135deg, #3b82f6 0%, #2563eb 100%);
			color: #ffffff;
			box-shadow: 0 2px 4px rgba(59, 130, 246, 0.25);
		}
		&.female{
			background: linear-gradient(135deg, #ec4899 0%, #db2777 100%);
			color: #ffffff;
			box-shadow: 0 2px 4px rgba(236, 72, 153, 0.25);
		}
	}

	// 手机号码胶囊样式
	.phone_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 80px;
		height: 24px;
		padding: 0 10px;
		background-color: #f1f5f9;
		color: #64748b;
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		font-weight: 500;
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

	// 裁判姓名列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(4) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 裁判账号列弱化
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(3) .cell {
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