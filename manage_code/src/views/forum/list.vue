<template>
	<div>
		<div class="center_view">
			<div v-if="forumChild">
				<el-button type="success" @click="searchClick()">返回</el-button>
			</div>
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" v-if="!forumChild">
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
							帖子内容：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.content" placeholder="帖子内容"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('forum','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('forum','删除')">
						删除
					</el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick1" v-if="btnAuth('forum','论坛活跃度统计')">
                        论坛活跃度统计
                    </el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick2" v-if="btnAuth('forum','发帖量统计')">
                        发帖量统计
                    </el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('forum','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' align="left" header-align="left">
					<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="title"
					v-if="!forumChild"
					label="帖子标题">
					<template #default="scope">
						{{scope.row.title}}
					</template>
				</el-table-column>
                <el-table-column label="帖子内容" v-if="forumChild" min-width="140" :resizable='true' :sortable='false' align="left" header-align="left">
                    <template #default="scope">
                        <span v-html="scope.row.content"></span>
                    </template>
                </el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="username"
					label="用户名">
					<template #default="scope">
						{{scope.row.username}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="isdone"
					v-if="!forumChild"
					label="状态">
					<template #default="scope">
						{{scope.row.isdone}}
					</template>
				</el-table-column>
                <el-table-column v-if="btnAuth('forum','修改') && !forumChild" prop="isTop" label="是否置顶" :resizable='true' :sortable='false' align="left" header-align="left">
                    <template #default="scope">
                        <el-switch v-model="scope.row.isTop"
                                   :active-value="1"
                                   :inactive-value="0"
                                   @change="setTop(scope.row)">
                        </el-switch>
                    </template>
                </el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="topTime"
					v-if="!forumChild"
					label="置顶时间">
					<template #default="scope">
						{{scope.row.topTime}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="typeName"
					label="分类名称">
					<template #default="scope">
						{{scope.row.typeName}}
					</template>
				</el-table-column>
				<el-table-column label="封面" min-width="140" width="120" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.cover">
							<el-image v-if="scope.row.cover.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.cover.split(',')[0]]"
								:src="scope.row.cover.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.cover.split(',')[0]]"
								:src="$config.url+scope.row.cover.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140" prop="isAnonymous" label="是否匿名">
					<template #default="scope">
						{{scope.row.isAnonymous>0?'是':'否'}}
					</template>
				</el-table-column>
				<el-table-column min-width="140" prop="isDel" label="是否删除">
					<template #default="scope">
						{{scope.row.isDel>0?'是':'否'}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='false'
					align="left"
					header-align="left"
					prop="thumbsupNumber"
					v-if="!forumChild"
					label="赞">
					<template #default="scope">
						{{scope.row.thumbsupNumber}}
					</template>
				</el-table-column>
				<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('forum','查看')" @click="infoClick(scope.row.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id,scope.row)" v-if="!forumChild &&  btnAuth('forum','修改')">
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id,scope.row)"  v-if="btnAuth('forum','删除')">
							删除						</el-button>
						<el-button class="operate_btn" v-if="!forumChild" type="warning" @click="searchClick(scope.row.id)">
							查看评论
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
		<!-- 统计图弹窗 -->
		<el-dialog v-model="echartVisible" modal-class="edit_form_modal" class="edit_form" title="统计图" width="70%">
			<el-tabs v-model="echartActive" class="demo-tabs" @tab-change="echartTabClick" type="card">
                <el-tab-pane label="论坛活跃度统计" name="1" v-if="btnAuth('forum','论坛活跃度统计')"></el-tab-pane>
                <el-tab-pane label="发帖量统计" name="2" v-if="btnAuth('forum','发帖量统计')"></el-tab-pane>
			</el-tabs>
			<div v-if="echartActive==1">
				<div id="thumbsupNumberEchart1" style="width:100%;height:600px;"></div>
			</div>
			<div v-if="echartActive==2">
				<div id="typeNameEchart2" style="width:100%;height:600px;"></div>
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
	const tableName = 'forum'
	const formName = '互动交流'
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
	const getList = (id=null)=>{
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['parentid'] = 0
		if(id){
			params['parentid'] = id
		}
		if(searchQuery.value.title&&searchQuery.value.title!=''){
			params['title'] = '%' + searchQuery.value.title + '%'
		}
		context.$http({
			url: `forum/page`,
			method: 'get',
			params: params
		}).then(res=>{
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
			listLoading.value = false
		})
	}
	const delClick = (id,row={}) => {
		let ids = []
		if (id) {
			ids = [id]
			selRows.value = [row]
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
	const forumChild = ref(false)
	const searchClick = (id) => {
		if(id){
			forumChild.value = true;
		}else{
			forumChild.value = false
		}
		listQuery.value.page = 1
		getList(id)
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
			echartActive.value = '1'
			echartVisible.value = true
		}
		nextTick(async ()=>{
			let dom = document.getElementById("thumbsupNumberEchart1")
			if(!dom)return
			var thumbsupNumberEchart1 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/value/title/thumbsupNumber?order=desc`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].title);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].title				    })
				}
				var option = {};
				option = {
    title: {
        text: '论坛活跃度统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}
				// 使用刚指定的配置项和数据显示图表。
				thumbsupNumberEchart1.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    thumbsupNumberEchart1.resize();
				};
			})
		})
	}
	// 统计图2
    const echartActive = ref('1')
    const echartTabClick = () =>{
		if(echartActive.value==1){
			echartClick1()
		}
		else if(echartActive.value==2){
			echartClick2()
		}
	}
	const echartClick2 = ()=>{
		if(!route.path.endsWith('Analysis')){
			echartActive.value = '2'
			echartVisible.value = true
		}
		nextTick(async ()=>{
			let dom = document.getElementById("typeNameEchart2")
			if(!dom)return
			var typeNameEchart2 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/group/typeName?order=desc`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].typeName);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].typeName				    })
				}
				var option = {};
				option = {
    title: {
        text: '发帖量统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}
                var middle = option.xAxis
                option.xAxis = option.yAxis
                option.yAxis = middle
				// 使用刚指定的配置项和数据显示图表。
				typeNameEchart2.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    typeNameEchart2.resize();
				};
			})
		})
	}
    const setTop = (row)=>{
		row.topTime = moment().format("YYYY-MM-DD HH:mm:ss")
        context.$http.post(`${tableName}/update`,row).then(res=>{
            if(res.data.code==0){
				context.$message.success("操作成功")
                searchClick()
            }
        })
    }
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
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