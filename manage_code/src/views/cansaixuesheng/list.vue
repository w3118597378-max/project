<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							学号：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.xuehao" placeholder="学号"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							姓名：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.xingming" placeholder="姓名"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('cansaixuesheng','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('cansaixuesheng','删除')">
						删除
					</el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick1" v-if="btnAuth('cansaixuesheng','学生数量统计')">
                        学生数量统计
                    </el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('cansaixuesheng','查看')"
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
					prop="xuehao"
					label="学号">
					<template #default="scope">
						<span class="student_id_badge">{{scope.row.xuehao}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="xingming"
					label="姓名">
					<template #default="scope">
						<div class="student-highlight">
							<span class="student-icon">👨‍🎓</span>
							<span class="student-text">{{scope.row.xingming}}</span>
						</div>
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
						<span v-if="scope.row.xingbie=='男'" class="gender-badge male">
							<span class="gender-icon">👦</span>
							<span class="gender-text">男</span>
						</span>
						<span v-else-if="scope.row.xingbie=='女'" class="gender-badge female">
							<span class="gender-icon">👧</span>
							<span class="gender-text">女</span>
						</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="nianling"
					label="年龄">
					<template #default="scope">
						<span class="light_pill">{{scope.row.nianling}}</span>
					</template>
				</el-table-column>
				<el-table-column label="头像" min-width="140" width="120" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.touxiang" class="avatar-container">
							<el-image v-if="scope.row.touxiang.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.touxiang.split(',')[0]]"
								:src="scope.row.touxiang.split(',')[0]" class="student-avatar"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.touxiang.split(',')[0]]"
								:src="$config.url+scope.row.touxiang.split(',')[0]" class="student-avatar">
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
					prop="shouji"
					label="手机">
					<template #default="scope">
						<span class="light_pill">{{scope.row.shouji}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="email"
					label="邮箱">
					<template #default="scope">
						<span class="secondary_text">{{scope.row.email}}</span>
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('cansaixuesheng','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('cansaixuesheng','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('cansaixuesheng','删除')">
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
		<!-- 统计图弹窗 -->
		<el-dialog v-model="echartVisible" modal-class="edit_form_modal" class="edit_form" title="统计图" width="70%">
			<div >
				<div id="xingbieEchart1" style="width:100%;height:600px;"></div>
			</div>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="echartVisible=false">取消</el-button>
				</span>
			</template>
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
		inject
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
	const tableName = 'cansaixuesheng'
	const formName = '参赛学生'
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
		if(searchQuery.value.xuehao&&searchQuery.value.xuehao!=''){
			params['xuehao'] = '%' + searchQuery.value.xuehao + '%'
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
	//判断是否有统计图筛选权限
	const changeStatQuery = (arr)=>{
		if(!arr){
			return true
		}
		let role = localStorage.getItem('role')
		for(let x in arr){
			if(arr[x] == role) {
				return true
			}
		}
		return false
	}
	// 统计图1
	const echartVisible = ref(false)
	const echartClick1 = ()=>{
		if(!route.path.endsWith('Analysis')){
			echartVisible.value = true
		}
		nextTick(async ()=>{
			let dom = document.getElementById("xingbieEchart1")
			if(!dom)return
			var xingbieEchart1 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/group/xingbie?order=desc`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].xingbie);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].xingbie				    })
				}
				var option = {};
				option = {
    title:{
        text: '学生数量统计',
        left: 'center'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c} ({d}%)'
    },
    series: [
        {
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: dataList,
            emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
}
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    xingbieEchart1.resize();
				};
			})
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

	// 学生姓名高亮样式
	.student-highlight{
		display: flex;
		align-items: center;
		gap: 8px;
		.student-icon{
			font-size: 14px;
		}
		.student-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 学号Badge样式
	.student_id_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 80px;
		height: 28px;
		padding: 0 12px;
		background-color: #fef3c7;
		color: #92400e;
		border: 1px solid #fed7aa;
		border-radius: 8px;
		font-weight: 500;
		font-size: 12px;
	}

	// 性别Badge样式
	.gender-badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		gap: 6px;
		min-width: 60px;
		height: 28px;
		padding: 0 12px;
		border-radius: 8px;
		font-weight: 500;
		font-size: 12px;
		&.male{
			background-color: #dbeafe;
			color: #1e40af;
			border: 1px solid #bfdbfe;
		}
		&.female{
			background-color: #fce7f3;
			color: #9f1239;
			border: 1px solid #fbcfe8;
		}
		.gender-icon{
			font-size: 14px;
		}
		.gender-text{
			font-weight: 500;
		}
	}

	// 头像容器样式
	.avatar-container{
		display: flex;
		justify-content: center;
		align-items: center;
		.student-avatar{
			width: 60px;
			height: 60px;
			border-radius: 50%;
			object-fit: cover;
			border: 3px solid #fff;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		}
	}
	.no-avatar{
		color: #9ca3af;
		font-size: 12px;
		text-align: center;
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

	// 次要信息弱化样式
	.secondary_text{
		color: #64748b;
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

	// 学号列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 500;
			color: #92400e;
			font-size: 13px;
		}
	}

	// 姓名列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(4) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 性别列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(5) .cell {
			font-weight: 500;
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
	:deep(.el-button--warning) {
		background: #f59e0b !important;
		border-color: #f59e0b !important;
		box-shadow: 0 1px 3px rgba(245, 158, 11, 0.2);
	}
</style>