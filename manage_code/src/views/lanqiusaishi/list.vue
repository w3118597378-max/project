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
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('lanqiusaishi','新增')">
						新增
					</el-button>
				</div>
			</div>
			<div class="dataList"
				 v-if="btnAuth('lanqiusaishi','查看')"
			>
				<div class="item" v-for="(item,index) in list" :key="item.id">
                    <template v-if="item.fengmian">
                        <el-image v-if="item.fengmian.substring(0,4)=='http'" preview-teleported
                                  :preview-src-list="[item.fengmian.split(',')[0]]"
                                  :src="item.fengmian.split(',')[0]"></el-image>
                        <el-image v-else preview-teleported
                                  :preview-src-list="[$config.url+item.fengmian.split(',')[0]]"
                                  :src="$config.url+item.fengmian.split(',')[0]">
                        </el-image>
                    </template>
					<div class="title">{{item.saishimingcheng}}</div>
					<div class="title">比赛时间：{{item.bisaishijian}}</div>
                    <div class="title status-row">
                    </div>
					<div class="btns">
						<el-button class="view_btn" type="info" v-if=" btnAuth('lanqiusaishi','查看')" @click="infoClick(item.id)">
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(item.id,item)" v-if=" btnAuth('lanqiusaishi','修改')">
							修改
						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(item.id,item)"  v-if="btnAuth('lanqiusaishi','删除')">
							删除
						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('lanqiusaishi','报名')" type="success" @click="bisaibaomingCrossAddOrUpdateHandler(item,'cross','','','','')">
							报名
						</el-button>
						<el-button class="operate_btn" v-if="btnAuth('lanqiusaishi','查看评论')" type="warning" @click="commentClick(item.id)">
							查看评论
						</el-button>
					</div>
				</div>
			</div>
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
		<bisaibaomingFormModel ref="bisaibaomingFormModelRef" @formModelChange="formModelChange"></bisaibaomingFormModel>
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
	const tableName = 'lanqiusaishi'
	const formName = '篮球赛事'
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
    // 查看评论
	const commentClick=(id)=>{
		context?.$router.push('/discusslanqiusaishi?refid=' + id)
	}
	import bisaibaomingFormModel from '@/views/bisaibaoming/formModel'
	const bisaibaomingFormModelRef = ref(null)
    const bisaibaomingCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
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
			bisaibaomingFormModelRef.value.init(row.id,'cross','报名',row,'lanqiusaishi',statusColumnName,tips,statusColumnValue)
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
    .status-row{
        display:flex;
        gap: 6px;
    }
</style>