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
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('bisaijishutongji','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('bisaijishutongji','删除')">
						删除
					</el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick1" v-if="btnAuth('bisaijishutongji','比赛技术统计')">
                        比赛技术统计
                    </el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('bisaijishutongji','查看')"
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
							<span class="event-dot"></span>
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
						{{scope.row.bisaishijian}}
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
						{{scope.row.bisaichangdi}}
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
						<div class="team-name">
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
					prop="defen"
					label="得分">
					<template #default="scope">
						<span class="score_badge">{{scope.row.defen}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="fangui"
					label="犯规">
					<template #default="scope">
						<span class="stat_pill">{{scope.row.fangui}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="zanting"
					label="暂停">
					<template #default="scope">
						<span class="stat_pill">{{scope.row.zanting}}</span>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="jilushijian"
					label="记录时间">
					<template #default="scope">
						{{scope.row.jilushijian}}
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
						{{scope.row.caipanxingming}}
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('bisaijishutongji','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if=" btnAuth('bisaijishutongji','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('bisaijishutongji','删除')">
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
				<div id="qiuduimingchengEchart1" style="width:100%;height:600px;"></div>
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
	const tableName = 'bisaijishutongji'
	const formName = '比赛技术统计'
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
			let dom = document.getElementById("qiuduimingchengEchart1")
			if(!dom)return
			var qiuduimingchengEchart1 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/valueMul/qiuduimingcheng?yColumnNameMul=defen,fangui,zanting`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
                let xAxis1 = [];
                let yAxis1 = [];
                let pArray1 = []
                for(let i=0;i<obj[0].length;i++){
                    xAxis1.push(obj[0][i].qiuduimingcheng);
                    yAxis1.push(parseFloat((obj[0][i].total)));
                    pArray1.push({
                        value: parseFloat((obj[0][i].total)),
                        name: obj[0][i].qiuduimingcheng
                    })
                }
                let xAxis2 = [];
                let yAxis2 = [];
                let pArray2 = []
                for(let i=0;i<obj[1].length;i++){
                    xAxis2.push(obj[1][i].qiuduimingcheng);
                    yAxis2.push(parseFloat((obj[1][i].total)));
                    pArray2.push({
                        value: parseFloat((obj[1][i].total)),
                        name: obj[1][i].qiuduimingcheng
                    })
                }
                let xAxis3 = [];
                let yAxis3 = [];
                let pArray3 = []
                for(let i=0;i<obj[2].length;i++){
                    xAxis3.push(obj[2][i].qiuduimingcheng);
                    yAxis3.push(parseFloat((obj[2][i].total)));
                    pArray3.push({
                        value: parseFloat((obj[2][i].total)),
                        name: obj[2][i].qiuduimingcheng
                    })
                }
				var option = {};
				let series = [
					{
						data: yAxis1,
						type: 'line',
						name: '得分',
					},
					{
						data: yAxis2,
						type: 'line',
						name: '犯规数',
					},
					{
						data: yAxis3,
						type: 'line',
						name: '暂停数',
					},
				]
				let legend_data = ['得分','犯规数','暂停数',]
				option = {
    title: {
        text: '比赛技术统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    legend: {
        data: legend_data,
        left: 'center',
        bottom: 4,
    },
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        data: xAxis1,
    },
    yAxis: {
        type: 'value'
    },
    series: series,
}
                // 使用刚指定的配置项和数据显示图表。
                qiuduimingchengEchart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    qiuduimingchengEchart1.resize();
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

	// 赛事名称高亮样式
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
			background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
			box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
		}
		.team-text{
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 得分Badge样式
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

	// 犯规/暂停胶囊样式
	.stat_pill{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		min-width: 28px;
		height: 22px;
		padding: 0 8px;
		background-color: #f1f5f9;
		color: #64748b;
		border: 1px solid #e2e8f0;
		border-radius: 11px;
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

	// 赛事名称列更突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(3) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 球队名称列突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(6) .cell {
			font-weight: 600;
			color: #1e293b;
			font-size: 14px;
		}
	}

	// 得分列最突出
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(7) .cell {
			font-weight: 700;
			font-size: 16px;
		}
	}

	// 犯规/暂停列样式
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(9) .cell,
		.el-table__body tbody tr td:nth-child(10) .cell {
			// 使用stat_pill样式
		}
	}

	// 时间相关列弱化
	:deep(.el-table){
		.el-table__body tbody tr td:nth-child(4) .cell,
		.el-table__body tbody tr td:nth-child(5) .cell,
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
	:deep(.el-button--warning) {
		background: #f59e0b !important;
		border-color: #f59e0b !important;
		box-shadow: 0 2px 4px rgba(245, 158, 11, 0.2);
	}

	// 表格样式
	.el-table {
		:deep(.el-table__body-wrapper) {
			tbody {
			}
		}
	}
	.condition-box {
		display: flex;
		gap: 10px;
		justify-content: center;
	}

	.condition-box>* {
		max-width: 300px;
	}
</style>