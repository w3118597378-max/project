<template>
	<div>
		<div class="center_view">
			<div class="page_shell">
				<div class="page_top_line"></div>
				<div class="page_container">
					<div class="page_header">
						<div class="page_header__left">
							<div class="page_icon">
								<Basketball class="page_icon__svg" />
							</div>
							<div class="page_titles">
								<div class="page_title">篮球赛事管理</div>
								<div class="page_subtitle">以卡片方式快速浏览赛事信息与操作入口</div>
							</div>
						</div>
					</div>

					<div class="search_card list_search_view">
						<el-form :model="searchQuery" class="search_form">
							<div class="search_view">
								<div class="search_label">赛事名称：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.saishimingcheng" placeholder="赛事名称" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_view">
								<div class="search_label">比赛场地：</div>
								<div class="search_box">
									<el-input class="search_inp" v-model="searchQuery.bisaichangdi" placeholder="比赛场地" clearable>
										<template #prefix><Search class="field_icon" /></template>
									</el-input>
								</div>
							</div>
							<div class="search_btn_view">
								<el-button class="btn_primary" type="primary" @click="searchClick()" size="small">搜索</el-button>
							</div>
						</el-form>
						<div class="btn_view">
							<el-button class="btn_primary" type="success" @click="addClick" v-if="btnAuth('lanqiusaishi','新增')">新增</el-button>
						</div>
					</div>

					<div class="card_list" v-if="btnAuth('lanqiusaishi','查看')">
						<div class="card_item" v-for="(item,index) in list" :key="item.id">
							<div class="card_left" @click.stop>
								<div class="card_cover" v-if="item.fengmian">
									<el-image v-if="item.fengmian.substring(0,4)=='http'" preview-teleported
										:preview-src-list="[item.fengmian.split(',')[0]]"
										:src="item.fengmian.split(',')[0]" style="width:96px;height:96px;border-radius:14px"></el-image>
									<el-image v-else preview-teleported
										:preview-src-list="[$config.url+item.fengmian.split(',')[0]]"
										:src="$config.url+item.fengmian.split(',')[0]" style="width:96px;height:96px;border-radius:14px"></el-image>
								</div>
								<div class="card_cover muted" v-else>无封面</div>
							</div>

							<div class="card_main">
								<div class="card_title">{{item.saishimingcheng}}</div>
								<div class="card_meta">比赛时间：{{item.bisaishijian}}</div>
								<div class="card_actions">
									<el-button class="btn_secondary" type="info" v-if=" btnAuth('lanqiusaishi','查看')" @click.stop="infoClick(item.id)">详情</el-button>
									<el-button class="btn_secondary" type="primary" v-if=" btnAuth('lanqiusaishi','修改')" @click.stop="editClick(item.id,item)">修改</el-button>
									<el-button class="btn_danger" type="danger" v-if="btnAuth('lanqiusaishi','删除')" @click.stop="delClick(item.id,item)">删除</el-button>
									<el-button class="btn_secondary" v-if="btnAuth('lanqiusaishi','报名')" type="success" @click.stop="bisaibaomingCrossAddOrUpdateHandler(item,'cross','','','','')">报名</el-button>
									<el-button class="btn_secondary" v-if="btnAuth('lanqiusaishi','查看评论')" type="warning" @click.stop="commentClick(item.id)">查看评论</el-button>
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
						@current-change="currentChange" />
				</div>
			</div>
			<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
			<bisaibaomingFormModel ref="bisaibaomingFormModelRef" @formModelChange="formModelChange"></bisaibaomingFormModel>
		</div>
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
	const user = computed(() => store.getters['user/session'])
	const avatar = computed(() => store.getters['user/avatar'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const baseUrl = ref(context.$config.url)
	import formModel from './formModel.vue'
	import { Basketball, Search } from 'lucide-vue-next'
	//基础信息
	const tableName = 'lanqiusaishi'
	const formName = '篮球赛事'
	const route = useRoute()
	const router = useRouter()
	const role = context.$toolUtil.storageGet('sessionTable')
	//基础信息
	onMounted(() => {
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
	const listChange = (row) => {
		nextTick(() => {
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
		if (searchQuery.value.saishimingcheng && searchQuery.value.saishimingcheng != '') {
			params['saishimingcheng'] = '%' + searchQuery.value.saishimingcheng + '%'
		}
		if (searchQuery.value.bisaichangdi && searchQuery.value.bisaichangdi != '') {
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
	const delClick = (id, row = {}) => {
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
				context?.$toolUtil.message('删除成功', 'success', () => {
					getList()
				})
			})
		}).catch(_ => { })
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total", "prev", "pager", "next"])
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
	const btnAuth = (e, a) => {
		return context?.$toolUtil.isAuth(e, a)
	}
	//搜索
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange = () => {
		searchClick()
	}
	const addClick = () => {
		formRef.value.init()
	}
	const editClick = (id = null, row = {}) => {
		if (id) {
			formRef.value.init(id, 'edit')
			return
		}
		if (selRows.value.length) {
			formRef.value.init(selRows.value[0].id, 'edit')
		}
	}

	const infoClick = (id = null) => {
		if (id) {
			formRef.value.init(id, 'info')
		}
		else if (selRows.value.length) {
			formRef.value.init(selRows.value[0].id, 'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) => {
		if (!file) {
			context?.$toolUtil.message('文件不存在', 'error')
		}
		window.open(context?.$config.url + file)
	}
	// 下载文件
	const download = (file) => {
		if (!file) {
			context?.$toolUtil.message('文件不存在', 'error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length > 1 ? location.href.split(context?.$config.name)[0] : '') + context?.$config.name + '/file/download?fileName=' + arr, {
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
	const commentClick = (id) => {
		context?.$router.push('/discusslanqiusaishi?refid=' + id)
	}
	import bisaibaomingFormModel from '@/views/bisaibaoming/formModel'
	const bisaibaomingFormModelRef = ref(null)
	const bisaibaomingCrossAddOrUpdateHandler = (row, type, crossOptAudit, crossOptPay, statusColumnName, tips, statusColumnValue) => {
		if (statusColumnName != '' && !statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj) {
				if (o == statusColumnName && obj[o] == statusColumnValue) {
					context?.$toolUtil.message(tips, 'error')
					return;
				}
			}
		}
		nextTick(() => {
			bisaibaomingFormModelRef.value.init(row.id, 'cross', '报名', row, 'lanqiusaishi', statusColumnName, tips, statusColumnValue)
		})
	}
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>

<style lang="scss" scoped>
	.page_shell {
		width: 100%;
		background: #f8fafc;
		border-radius: 18px;
		border: 1px solid #e2e8f0;
		overflow: hidden;
	}

	.page_top_line {
		height: 1px;
		background: linear-gradient(90deg, rgba(249, 115, 22, 0) 0%, rgba(249, 115, 22, 1) 35%, rgba(234, 88, 12, 1) 65%, rgba(249, 115, 22, 0) 100%);
	}

	.page_container {
		max-width: 1280px;
		margin: 0 auto;
		padding: 40px 24px;
	}

	.page_header {
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		gap: 16px;
		margin-bottom: 18px;
	}

	.page_header__left {
		display: flex;
		align-items: center;
		gap: 14px;
	}

	.page_icon {
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

	.page_icon__svg {
		width: 22px;
		height: 22px;
	}

	.page_title {
		font-size: 28px;
		font-weight: 800;
		color: #0f172a;
		line-height: 1.15;
	}

	.page_subtitle {
		margin-top: 6px;
		font-size: 12px;
		color: #64748b;
		line-height: 1.25;
	}

	.search_card {
		background: #fff;
		border: 1px solid #e2e8f0;
		border-radius: 16px;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
		padding: 16px;
	}

	.search_form {
		display: flex;
		flex-wrap: wrap;
		align-items: center;
		gap: 12px 16px;
	}

	.field_icon {
		width: 16px;
		height: 16px;
		color: #94a3b8;
	}

	:deep(.search_inp .el-input__wrapper),
	:deep(.search_sel .el-select__wrapper) {
		border-radius: 12px;
	}

	:deep(.search_inp .el-input__wrapper.is-focus),
	:deep(.search_sel .el-select__wrapper.is-focused) {
		box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.18) !important;
		border-color: #f97316 !important;
	}

	.btn_primary {
		border-radius: 12px;
		background: #f97316 !important;
		border-color: #f97316 !important;
		color: #fff !important;
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.20), 0 4px 6px -4px rgba(249, 115, 22, 0.20);
	}

	.btn_primary:hover {
		background: #ea580c !important;
		border-color: #ea580c !important;
	}

	.btn_secondary {
		border-radius: 12px;
		background: #fff !important;
		border-color: #e2e8f0 !important;
		color: #334155 !important;
	}

	.btn_secondary:hover {
		background: #f8fafc !important;
		border-color: #cbd5e1 !important;
	}

	.btn_danger {
		border-radius: 12px;
	}

	.card_list {
		margin-top: 18px;
		display: grid;
		grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
		gap: 14px;
	}

	.card_item {
		background: #fff;
		border: 1px solid #e2e8f0;
		border-radius: 16px;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
		padding: 14px;
		display: flex;
		gap: 14px;
		align-items: flex-start;
	}

	.card_left {
		flex: 0 0 auto;
	}

	.card_cover {
		width: 96px;
		height: 96px;
		border-radius: 14px;
		background: #f8fafc;
		border: 1px solid #e2e8f0;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.card_main {
		flex: 1 1 auto;
		min-width: 0;
	}

	.card_title {
		font-size: 16px;
		font-weight: 800;
		color: #0f172a;
		line-height: 1.25;
		word-break: break-all;
	}

	.card_meta {
		margin-top: 6px;
		font-size: 12px;
		color: #64748b;
		line-height: 1.25;
	}

	.card_actions {
		margin-top: 10px;
		display: flex;
		flex-wrap: wrap;
		gap: 8px;
	}

	.muted {
		color: #94a3b8;
		font-size: 12px;
	}
</style>