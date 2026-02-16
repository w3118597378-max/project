<template>
	<div>
		<div class="center_view">
			<div class="page_shell">
				<div class="page_top_line"></div>
				<div class="page_container">
					<div class="page_header">
						<div class="page_header__left">
							<div class="page_icon">
								<Trophy class="page_icon__svg" />
							</div>
							<div class="page_titles">
								<div class="page_title">球队排行榜</div>
								<div class="page_subtitle">查看球队积分排名、胜率统计与招募状态</div>
							</div>
						</div>
					</div>

					<div class="search_card list_search_view">
						<el-form :model="searchQuery" class="search_form">
							<div class="search_view">
								<div class="search_label">球队名称：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.qiuduimingcheng" placeholder="球队名称" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_view">
								<div class="search_label">队长姓名：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.duizhangxingming" placeholder="队长姓名" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_view">
								<div class="search_label">审核状态：</div>
								<div class="search_box">
									<el-select class="search_sel" clearable v-model="searchQuery.sfsh" placeholder="审核状态">
										<el-option v-for="item in approvalLists" :label="item" :value="item"></el-option>
									</el-select>
								</div>
							</div>
							<div class="search_btn_view">
								<el-button class="btn_primary" type="primary" @click="searchClick()" size="small">搜索</el-button>
							</div>
						</el-form>
						<div class="btn_view">
							<el-button class="btn_primary" type="success" @click="addClick" v-if="btnAuth('qiuduipaixingbang','新增')">新增</el-button>
							<el-button class="btn_danger" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)" v-if="btnAuth('qiuduipaixingbang','删除')">删除</el-button>
						</div>
					</div>

					<div class="table_card card_grid_container">
						<div v-loading="listLoading" class="rank_grid">
							<div v-for="(item, index) in list" :key="item.id" class="rank_card_wrapper">
								<div class="rank_card" :class="'rank_card--' + ((listQuery.page-1)*listQuery.limit+index + 1)" @click="infoClick(item.id)">
									<div class="rank_badge_top" :class="'rank_badge--' + ((listQuery.page-1)*listQuery.limit+index + 1)">
										RANK {{ (listQuery.page-1)*listQuery.limit+index + 1 }}
									</div>
									
									<div class="rank_card__main">
										<div class="rank_card__left">
											<div class="rank_logo_box" @click.stop>
												<el-image v-if="item.qiuduilogo" preview-teleported
													:preview-src-list="[item.qiuduilogo.substring(0,4)=='http'?item.qiuduilogo.split(',')[0]:$config.url+item.qiuduilogo.split(',')[0]]"
													:src="item.qiuduilogo.substring(0,4)=='http'?item.qiuduilogo.split(',')[0]:$config.url+item.qiuduilogo.split(',')[0]" 
													class="rank_logo_img"></el-image>
											</div>
											<div class="rank_info">
												<div class="rank_name">{{ item.qiuduimingcheng }}</div>
												<div class="rank_leader">队长：{{ item.duizhangxingming }}</div>
											</div>
										</div>
										<div class="rank_card__right">
											<div class="rank_score_label">当前积分</div>
											<div class="rank_score_value">{{ item.jifen }}</div>
										</div>
									</div>

									<div class="rank_stats_grid">
										<div class="rank_stat_item">
											<span class="stat_label">胜/平/负</span>
											<span class="stat_value">{{ item.sheng }}/{{ item.ping }}/{{ item.fu }}</span>
										</div>
										<div class="rank_stat_item">
											<span class="stat_label">胜率</span>
											<span class="stat_value">{{ item.shenglv }}</span>
										</div>
										<div class="rank_stat_item">
											<span class="stat_label">招募状态</span>
											<span class="stat_value" :class="item.shifouzaizhaomu=='是'?'status_active':''">{{ item.shifouzaizhaomu }}</span>
										</div>
									</div>

									<div class="rank_card_footer">
										<div class="rank_tags">
											<span class="rank_tag">收藏 {{ item.storeupNumber }}</span>
											<span class="rank_tag">评论 {{ item.discussNumber }}</span>
										</div>
										<div class="rank_actions" @click.stop>
											<el-button class="btn_text" v-if="btnAuth('qiuduipaixingbang','修改')" @click="editClick(item.id,item)">编辑</el-button>
											<el-button class="btn_text btn_delete" v-if="btnAuth('qiuduipaixingbang','删除')" @click="delClick(item.id,item)">删除</el-button>
											<el-button class="btn_mini_primary" v-if="btnAuth('qiuduipaixingbang','申请加入')" @click="jiaruqiuduiCrossAddOrUpdateHandler(item,'cross','是','','','')">申请加入</el-button>
										</div>
									</div>
								</div>
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
			</div>
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
	import { Trophy, Search } from 'lucide-vue-next'
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

	.card_grid_container {
		margin-top: 24px;
		background: transparent !important;
		border: none !important;
		box-shadow: none !important;
	}
	.rank_grid {
		display: grid;
		grid-template-columns: repeat(2, 1fr);
		gap: 24px;
		width: 100%;
	}
	.rank_card_wrapper {
		width: 100%;
	}
	.rank_card {
		background: #fff;
		border-radius: 24px;
		padding: 32px;
		border: 1px solid #f1f5f9;
		box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.02), 0 2px 4px -1px rgba(0, 0, 0, 0.01);
		transition: all 0.4s cubic-bezier(0.34, 1.56, 0.64, 1);
		cursor: pointer;
		position: relative;
		overflow: hidden;
		display: flex;
		flex-direction: column;
		gap: 20px;
	}
	.rank_card:hover {
		transform: translateY(-8px);
		box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.05), 0 10px 10px -5px rgba(0, 0, 0, 0.02);
		border-color: #e2e8f0;
	}
	
	.rank_badge_top {
		position: absolute;
		top: 0;
		right: 0;
		padding: 8px 20px;
		background: #f1f5f9;
		color: #64748b;
		font-size: 11px;
		font-weight: 800;
		letter-spacing: 0.1em;
		border-bottom-left-radius: 20px;
		transition: all 0.3s ease;
	}
	.rank_badge--1 { background: #fff7ed; color: #f97316; }
	.rank_badge--2 { background: #f8fafc; color: #94a3b8; }
	.rank_badge--3 { background: #fffaf0; color: #d97706; }

	.rank_card__main {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.rank_card__left {
		display: flex;
		align-items: center;
		gap: 20px;
	}
	.rank_logo_box {
		width: 72px;
		height: 72px;
		border-radius: 20px;
		overflow: hidden;
		background: #f8fafc;
		border: 1px solid #f1f5f9;
		box-shadow: inset 0 2px 4px 0 rgba(0,0,0,0.05);
	}
	.rank_logo_img {
		width: 100%;
		height: 100%;
		object-fit: cover;
		transition: transform 0.5s ease;
	}
	.rank_card:hover .rank_logo_img {
		transform: scale(1.1);
	}
	.rank_name {
		font-size: 20px;
		font-weight: 800;
		color: #1e293b;
		margin-bottom: 4px;
	}
	.rank_leader {
		font-size: 14px;
		color: #94a3b8;
	}

	.rank_card__right {
		text-align: right;
	}
	.rank_score_label {
		font-size: 11px;
		color: #94a3b8;
		font-weight: 700;
		text-transform: uppercase;
		letter-spacing: 0.05em;
		margin-bottom: 2px;
	}
	.rank_score_value {
		font-size: 32px;
		font-weight: 900;
		color: #f97316;
		line-height: 1;
	}

	.rank_stats_grid {
		display: grid;
		grid-template-columns: repeat(3, 1fr);
		gap: 12px;
		padding: 16px;
		background: #f8fafc;
		border-radius: 20px;
		border: 1px solid #f1f5f9;
	}
	.rank_stat_item {
		display: flex;
		flex-direction: column;
		gap: 4px;
	}
	.stat_label {
		font-size: 11px;
		color: #94a3b8;
		font-weight: 600;
	}
	.stat_value {
		font-size: 15px;
		font-weight: 700;
		color: #334155;
	}
	.status_active {
		color: #10b981;
	}

	.rank_card_footer {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-top: 10px;
	}
	.rank_tags {
		display: flex;
		gap: 12px;
	}
	.rank_tag {
		font-size: 12px;
		color: #cbd5e1;
		font-weight: 500;
	}
	.rank_actions {
		display: flex;
		gap: 8px;
	}
	.btn_text {
		padding: 6px 12px;
		font-size: 13px;
		font-weight: 600;
		color: #64748b;
		border: none;
		background: transparent;
		transition: all 0.2s ease;
	}
	.btn_text:hover {
		color: #f97316;
		background: #fff7ed;
		border-radius: 8px;
	}
	.btn_delete:hover {
		color: #ef4444;
		background: #fef2f2;
	}
	.btn_mini_primary {
		padding: 6px 16px;
		font-size: 13px;
		font-weight: 700;
		background: #1e293b;
		border-color: #1e293b;
		color: #fff;
		border-radius: 10px;
		transition: all 0.2s ease;
	}
	.btn_mini_primary:hover {
		background: #334155;
		transform: scale(1.05);
	}

	@media (max-width: 1024px) {
		.rank_grid {
			grid-template-columns: 1fr;
		}
	}
</style>
