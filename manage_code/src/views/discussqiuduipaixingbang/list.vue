<template>
	<div>
		<div class="center_view">
			<div class="page_shell">
				<div class="page_top_line"></div>
				<div class="page_container">
					<div class="page_header">
						<div class="page_header__left">
							<div class="page_icon">
								<MessageCircle class="page_icon__svg" />
							</div>
							<div class="page_titles">
								<div class="page_title">排行榜评论管理</div>
								<div class="page_subtitle">维护球队排行榜评论内容、置顶与账号状态</div>
							</div>
						</div>
						<div class="page_header__right">
							<el-button class="btn_secondary" @click="back()">
								<ArrowLeft class="page_action_icon" />
								返回
							</el-button>
						</div>
					</div>

					<div class="search_card list_search_view">
						<el-form :model="searchQuery" class="search_form">
							<div class="search_view">
								<div class="search_label">用户名：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.nickname" placeholder="用户名" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_view">
								<div class="search_label">评论内容：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.content" placeholder="评论内容" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_btn_view">
								<el-button class="btn_primary" type="primary" @click="searchClick()" size="small">搜索</el-button>
							</div>
						</el-form>
						<div class="btn_view">
							<el-button class="btn_primary" type="success" @click="addClick" v-if="btnAuth('discussqiuduipaixingbang','新增')">新增</el-button>
							<el-button class="btn_danger" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)">删除</el-button>
						</div>
					</div>

					<div class="table_card">
						<el-table
							v-loading="listLoading" border :stripe='false'
							@selection-change="handleSelectionChange"
							ref="table"
							:data="list"
							@row-click="listChange"
							class="data_table">
							<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
							<el-table-column label="序号" width="80" :resizable='true' align="left" header-align="left">
								<template #default="scope">
									<div class="index_pill">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</div>
								</template>
							</el-table-column>
							<el-table-column min-width="140"
								:resizable='true'
								:sortable='false'
								align="left"
								header-align="left"
								prop="nickname"
								label="用户名">
								<template #default="scope">
									{{scope.row.nickname}}
								</template>
							</el-table-column>
							<el-table-column label="评论内容" min-width="140" :resizable='true' :sortable='false' align="left" header-align="left">
								<template #default="scope">
									<span v-html="scope.row.content"></span>
								</template>
							</el-table-column>
							<el-table-column label="回复内容" min-width="140" :resizable='true' :sortable='false' align="left" header-align="left">
								<template #default="scope">
									<span v-html="scope.row.reply"></span>
								</template>
							</el-table-column>
							<el-table-column min-width="140"
								:resizable='true'
								:sortable='false'
								align="left"
								header-align="left"
								prop="thumbsupnum"
								label="赞">
								<template #default="scope">
									{{scope.row.thumbsupnum}}
								</template>
							</el-table-column>
							<el-table-column min-width="140"
								:resizable='true'
								:sortable='false'
								align="left"
								header-align="left"
								prop="crazilynum"
								label="踩">
								<template #default="scope">
									{{scope.row.crazilynum}}
								</template>
							</el-table-column>
							<el-table-column min-width="140" prop="istop" label="是否置顶">
								<template #default="scope">
									<div @click.stop>
										<el-switch v-model="scope.row.istop"
											:active-value="1"
											:inactive-value="0"
											@change="setTop(scope.row)">
										</el-switch>
									</div>
								</template>
							</el-table-column>
							<el-table-column v-if="btnAuth('discussqiuduipaixingbang','修改')" label="禁用账号" :resizable='true' :sortable='false' align="left" header-align="left">
								<template #default="scope">
									<div @click.stop>
										<el-switch v-model="scope.row.isLocked"
											:active-value="1"
											:inactive-value="0"
											active-color="red"
											@change="updateItem(scope.row)"></el-switch>
									</div>
								</template>
							</el-table-column>
							<el-table-column label="操作" class-name="operation-cell" width="300"  :resizable='true' :sortable='false' align="left" header-align="left">
								<template #default="scope">
									<el-button class="btn_secondary" type="primary" v-if=" btnAuth('discussqiuduipaixingbang','修改')" @click.stop="editClick(scope.row.id,scope.row)">修改</el-button>
									<el-button class="btn_danger" type="danger" @click.stop="delClick(scope.row.id,scope.row)">删除</el-button>
									<el-button class="btn_secondary" v-if="btnAuth('discussqiuduipaixingbang','查看评论')" type="warning" @click.stop="commentClick(scope.row.id)">查看评论</el-button>
									<el-button class="btn_secondary" type="warning" @click.stop="replyClick(scope.row.id)">回复</el-button>
								</template>
							</el-table-column>
						</el-table>
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
			</div>
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
	import { ArrowLeft, MessageCircle, Search } from 'lucide-vue-next'
	//基础信息
	const tableName = 'discussqiuduipaixingbang'
	const formName = '球队排行榜评论表'
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
		params['refid'] = route.query.refid
		if(searchQuery.value.nickname&&searchQuery.value.nickname!=''){
			params['nickname'] = '%' + searchQuery.value.nickname + '%'
		}
		if(searchQuery.value.content&&searchQuery.value.content!=''){
			params['content'] = '%' + searchQuery.value.content + '%'
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
                context.$http.get(`qiuduipaixingbang/info/${route.query.refid}`).then(res=>{
                    let detail = res.data.data
                    detail.discussNumber -= ids.length
                    context.$http.post(`qiuduipaixingbang/update`,detail)
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
	//评论置顶
	const setTop = (row)=>{
		context.$http.post(`${tableName}/update`,row).then(res=>{
			if(res.data.code==0){
				context.$message.success("操作成功")
				searchClick()
			}
		})
	}
    // 查看评论
	const commentClick=(id)=>{
		context?.$router.push('/discussdiscussqiuduipaixingbang?refid=' + id)
	}
	const replyClick=(id=null)=>{
		formRef.value.init(id,'reply')
	}
    const updateItem = (row)=>{
        context.$http.post(`${tableName}/update`,row).then(res=>{
            if(res.data.code==0){
                context.$message.success("更新成功")
            }
        })
    }
    const back = ()=>{
        store.dispatch('delThisView',route.path)
        context.$router.go(-1)
    }
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	// 表格样式
	.page_shell{
		width: 100%;
		background: #f8fafc;
		border-radius: 18px;
		border: 1px solid #e2e8f0;
		overflow: hidden;
	}
	.page_top_line{
		height: 1px;
		background: linear-gradient(90deg, rgba(249,115,22,0) 0%, rgba(249,115,22,1) 35%, rgba(234,88,12,1) 65%, rgba(249,115,22,0) 100%);
	}
	.page_container{
		max-width: 1280px;
		margin: 0 auto;
		padding: 40px 24px;
	}
	.page_header{
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		gap: 16px;
		margin-bottom: 18px;
	}
	.page_header__left{
		display: flex;
		align-items: center;
		gap: 14px;
	}
	.page_header__right{
		display: flex;
		align-items: center;
		gap: 12px;
	}
	.page_action_icon{
		width: 16px;
		height: 16px;
		margin-right: 6px;
	}
	.page_icon{
		width: 48px;
		height: 48px;
		border-radius: 14px;
		display: flex;
		align-items: center;
		justify-content: center;
		background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.20), 0 4px 6px -4px rgba(249, 115, 22, 0.20);
		color: #fff;
		flex: 0 0 auto;
	}
	.page_icon__svg{
		width: 22px;
		height: 22px;
	}
	.page_title{
		font-size: 28px;
		font-weight: 800;
		color: #0f172a;
		line-height: 1.15;
	}
	.page_subtitle{
		margin-top: 6px;
		font-size: 12px;
		color: #64748b;
		line-height: 1.25;
	}
	.search_card{
		background: #fff;
		border: 1px solid #e2e8f0;
		border-radius: 16px;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
		padding: 16px;
	}
	.search_form{
		display: flex;
		flex-wrap: wrap;
		align-items: center;
		gap: 12px 16px;
	}
	.field_icon{
		width: 16px;
		height: 16px;
		color: #94a3b8;
	}
	:deep(.search_inp .el-input__wrapper),
	:deep(.search_sel .el-select__wrapper){
		border-radius: 12px;
	}
	:deep(.search_inp .el-input__wrapper.is-focus),
	:deep(.search_sel .el-select__wrapper.is-focused){
		box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.18) !important;
		border-color: #f97316 !important;
	}
	.btn_primary{
		border-radius: 12px;
		background: #f97316 !important;
		border-color: #f97316 !important;
		color: #fff !important;
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.20), 0 4px 6px -4px rgba(249, 115, 22, 0.20);
	}
	.btn_primary:hover{
		background: #ea580c !important;
		border-color: #ea580c !important;
	}
	.btn_secondary{
		border-radius: 12px;
		background: #fff !important;
		border-color: #e2e8f0 !important;
		color: #334155 !important;
	}
	.btn_secondary:hover{
		background: #f8fafc !important;
		border-color: #cbd5e1 !important;
	}
	.btn_danger{
		border-radius: 12px;
	}
	.table_card{
		margin-top: 18px;
		background: #fff;
		border: 1px solid #e2e8f0;
		border-radius: 16px;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
		overflow: hidden;
	}
	.index_pill{
		width: 40px;
		height: 40px;
		display: inline-flex;
		align-items: center;
		justify-content: center;
		border-radius: 12px;
		background: #fff7ed;
		color: #ea580c;
		font-weight: 700;
	}
	.muted{
		color: #94a3b8;
		font-size: 12px;
	}
	:deep(.data_table){
		--el-table-border-color: transparent;
		--el-table-border: 0;
	}
	:deep(.data_table .el-table__header-wrapper th.el-table__cell){
		background: #f8fafc;
		border-bottom: 1px solid #e2e8f0;
		color: #475569;
		font-size: 12px;
		font-weight: 700;
		text-transform: uppercase;
		letter-spacing: 0.04em;
	}
	:deep(.data_table .el-table__row td.el-table__cell){
		border-bottom: 1px solid #f1f5f9;
	}
	:deep(.data_table .el-table__row:hover td.el-table__cell){
		background: #f8fafc;
	}
	:deep(.operation-cell .el-button){
		border-radius: 12px;
	}
</style>