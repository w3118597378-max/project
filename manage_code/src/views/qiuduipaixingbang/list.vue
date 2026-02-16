<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
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
					<div class="search_view">
						<div class="search_label">
							队长姓名：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.duizhangxingming" placeholder="队长姓名"
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
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('qiuduipaixingbang','新增')">
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('qiuduipaixingbang','删除')">
						删除
					</el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading" border :stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('qiuduipaixingbang','查看')"
				:data="list"
				@row-click="listChange"
				:show-header="false"
				:header-cell-style="{ background: 'transparent', border: 'none', padding: '0' }"
				:cell-style="{ border: 'none', padding: '8px 0' }">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' align="left" header-align="left">
					<template #default="scope">
						<div class="rank_index">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</div>
					</template>
				</el-table-column>
				<el-table-column min-width="600" :resizable='true' :sortable='false' align="left" header-align="left">
					<template #default="scope">
						<div class="rank_card">
							<div class="rank_card__header">
								<div class="rank_card__team">
									<div class="rank_card__logo" v-if="scope.row.qiuduilogo" @click.stop>
										<el-image v-if="scope.row.qiuduilogo.substring(0,4)=='http'" preview-teleported
											:preview-src-list="[scope.row.qiuduilogo.split(',')[0]]"
											:src="scope.row.qiuduilogo.split(',')[0]" style="width:64px;height:64px;border-radius:14px"></el-image>
										<el-image v-else preview-teleported
											:preview-src-list="[$config.url+scope.row.qiuduilogo.split(',')[0]]"
											:src="$config.url+scope.row.qiuduilogo.split(',')[0]" style="width:64px;height:64px;border-radius:14px"></el-image>
									</div>
									<div class="rank_card__title">
										<div class="rank_card__name">{{ scope.row.qiuduimingcheng }}</div>
										<div class="rank_card__meta">教练：{{ scope.row.jiaolianmingcheng }}<span class="rank_card__dot">·</span>队长：{{ scope.row.duizhangxingming }}（{{ scope.row.duizhangzhanghao }}）</div>
									</div>
								</div>
								<div class="rank_card__status">
									<span class="rank_badge" :class="{
										'rank_badge--pass': scope.row.sfsh=='是',
										'rank_badge--reject': scope.row.sfsh=='否',
										'rank_badge--wait': scope.row.sfsh=='待审核'
									}">
										{{ scope.row.sfsh }}
									</span>
								</div>
							</div>

							<div class="rank_card__stats">
								<div class="rank_stat">
									<div class="rank_stat__label">积分</div>
									<div class="rank_stat__value rank_stat__value--primary">{{ scope.row.jifen }}</div>
								</div>
								<div class="rank_stat">
									<div class="rank_stat__label">胜 / 负 / 平</div>
									<div class="rank_stat__value">{{ scope.row.sheng }} / {{ scope.row.fu }} / {{ scope.row.ping }}</div>
								</div>
								<div class="rank_stat">
									<div class="rank_stat__label">胜率</div>
									<div class="rank_stat__value">{{ scope.row.shenglv }}</div>
								</div>
								<div class="rank_stat">
									<div class="rank_stat__label">招募</div>
									<div class="rank_stat__value">{{ scope.row.shifouzaizhaomu }}</div>
								</div>
								<div class="rank_stat">
									<div class="rank_stat__label">创立时间</div>
									<div class="rank_stat__value">{{ scope.row.chuanglishijian }}</div>
								</div>
								<div class="rank_stat" v-if="scope.row.qiuyi">
									<div class="rank_stat__label">球衣</div>
									<div class="rank_stat__value" @click.stop>
										<el-image v-if="scope.row.qiuyi.substring(0,4)=='http'" preview-teleported
											:preview-src-list="[scope.row.qiuyi.split(',')[0]]"
											:src="scope.row.qiuyi.split(',')[0]" style="width:64px;height:64px;border-radius:14px"></el-image>
										<el-image v-else preview-teleported
											:preview-src-list="[$config.url+scope.row.qiuyi.split(',')[0]]"
											:src="$config.url+scope.row.qiuyi.split(',')[0]" style="width:64px;height:64px;border-radius:14px"></el-image>
									</div>
								</div>
							</div>

							<div class="rank_card__footer">
								<div class="rank_card__extra">收藏：{{ scope.row.storeupNumber }}<span class="rank_card__dot">·</span>评论：{{ scope.row.discussNumber }}</div>
								<div class="rank_card__actions">
									<el-button class="view_btn" type="info" v-if=" btnAuth('qiuduipaixingbang','查看')" @click.stop="infoClick(scope.row.id)">
										详情
									</el-button>
									<el-button class="edit_btn" type="primary" v-if=" btnAuth('qiuduipaixingbang','修改')" @click.stop="editClick(scope.row.id,scope.row)">
										修改
									</el-button>
									<el-button class="del_btn" type="danger" v-if="btnAuth('qiuduipaixingbang','删除')" @click.stop="delClick(scope.row.id,scope.row)">
										删除
									</el-button>
									<el-button class="cross_btn" type="success" v-if="btnAuth('qiuduipaixingbang','申请加入')" @click.stop="jiaruqiuduiCrossAddOrUpdateHandler(scope.row,'cross','是','','','')">
										申请加入
									</el-button>
									<el-button class="operate_btn" type="warning" v-if="btnAuth('qiuduipaixingbang','查看评论')" @click.stop="commentClick(scope.row.id)">
										查看评论
									</el-button>
									<el-button v-if="btnAuth('qiuduipaixingbang','审核') && scope.row.sfsh=='待审核'" size="small" @click.stop="approvalClick(scope.row)">审核</el-button>
								</div>
							</div>
						</div>
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
		<jiaruqiuduiFormModel ref="jiaruqiuduiFormModelRef" @formModelChange="formModelChange"></jiaruqiuduiFormModel>
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
	const tableName = 'qiuduipaixingbang'
	const formName = '球队排行榜'
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
		if(searchQuery.value.qiuduimingcheng&&searchQuery.value.qiuduimingcheng!=''){
			params['qiuduimingcheng'] = '%' + searchQuery.value.qiuduimingcheng + '%'
		}
		params['sort'] = 'jifen';
		params['order'] = 'desc';
		if(searchQuery.value.duizhangxingming&&searchQuery.value.duizhangxingming!=''){
			params['duizhangxingming'] = '%' + searchQuery.value.duizhangxingming + '%'
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
    // 查看评论
	const commentClick=(id)=>{
		context?.$router.push('/discussqiuduipaixingbang?refid=' + id)
	}
	import jiaruqiuduiFormModel from '@/views/jiaruqiudui/formModel'
	const jiaruqiuduiFormModelRef = ref(null)
    const jiaruqiuduiCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
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
			jiaruqiuduiFormModelRef.value.init(row.id,'cross','申请加入',row,'qiuduipaixingbang',statusColumnName,tips,statusColumnValue)
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
	// 表格样式
	:deep(.el-table) {
		--el-table-border-color: transparent;
		--el-table-border: 0;
		background: transparent;
	}
	:deep(.el-table__row) {
		background: transparent;
	}
	:deep(.el-table__cell) {
		background: transparent;
	}

	.rank_index{
		width: 44px;
		height: 44px;
		display: inline-flex;
		align-items: center;
		justify-content: center;
		border-radius: 12px;
		background: #fff7ed;
		color: #ea580c;
		font-weight: 700;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
	}

	.rank_card{
		background: #ffffff;
		border: 1px solid #e2e8f0;
		border-radius: 16px;
		padding: 16px;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
		transition: box-shadow 0.15s ease, border-color 0.15s ease, transform 0.15s ease;
	}
	.rank_card:hover{
		border-color: #cbd5e1;
		box-shadow: 0 12px 24px rgba(15, 23, 42, 0.08);
		transform: translateY(-1px);
	}
	.rank_card__header{
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		gap: 12px;
	}
	.rank_card__team{
		display: flex;
		align-items: center;
		gap: 12px;
		min-width: 0;
	}
	.rank_card__title{
		min-width: 0;
	}
	.rank_card__name{
		font-size: 16px;
		font-weight: 700;
		color: #0f172a;
		line-height: 1.2;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.rank_card__meta{
		margin-top: 6px;
		font-size: 12px;
		color: #64748b;
		line-height: 1.2;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.rank_card__dot{
		margin: 0 6px;
		opacity: 0.7;
	}
	.rank_badge{
		display: inline-flex;
		align-items: center;
		justify-content: center;
		padding: 6px 10px;
		border-radius: 999px;
		font-size: 12px;
		font-weight: 600;
		border: 1px solid transparent;
		white-space: nowrap;
	}
	.rank_badge--pass{
		background: #ecfdf5;
		color: #059669;
		border-color: #a7f3d0;
	}
	.rank_badge--reject{
		background: #fef2f2;
		color: #dc2626;
		border-color: #fecaca;
	}
	.rank_badge--wait{
		background: #fffbeb;
		color: #d97706;
		border-color: #fde68a;
	}

	.rank_card__stats{
		margin-top: 14px;
		display: grid;
		grid-template-columns: repeat(3, minmax(0, 1fr));
		gap: 10px 14px;
	}
	.rank_stat{
		background: #f8fafc;
		border: 1px solid #e2e8f0;
		border-radius: 14px;
		padding: 10px 12px;
		min-width: 0;
	}
	.rank_stat__label{
		font-size: 12px;
		color: #64748b;
		line-height: 1.2;
	}
	.rank_stat__value{
		margin-top: 6px;
		font-size: 14px;
		font-weight: 600;
		color: #0f172a;
		line-height: 1.2;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.rank_stat__value--primary{
		color: #ea580c;
	}

	.rank_card__footer{
		margin-top: 14px;
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 12px;
		padding-top: 12px;
		border-top: 1px solid #e2e8f0;
		flex-wrap: wrap;
	}
	.rank_card__extra{
		font-size: 12px;
		color: #64748b;
	}
	.rank_card__actions{
		display: flex;
		align-items: center;
		gap: 8px;
		flex-wrap: wrap;
	}
	:deep(.rank_card__actions .el-button){
		border-radius: 12px;
	}
</style>