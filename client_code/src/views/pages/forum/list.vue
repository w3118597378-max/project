<template>
	<div class="min-h-screen bg-slate-50">
		<div class="max-w-7xl mx-auto px-6 py-10">
			<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 flex items-center justify-between hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="flex items-center gap-4">
					<div class="w-12 h-12 rounded-lg bg-gradient-to-br from-orange-500 to-orange-400 shadow-lg shadow-orange-500/25 flex items-center justify-center">
						<MessageSquare class="w-6 h-6 text-white" />
					</div>
					<div>
						<div class="text-2xl font-bold text-slate-900">互动交流</div>
						<div class="text-sm text-slate-500">Forum & Community</div>
					</div>
				</div>
				<div class="flex items-center gap-3">
					<el-button v-if="centerType" class="back_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="backClick">返回</el-button>
				</div>
			</div>

			<div class="grid grid-cols-1 lg:grid-cols-12 gap-6">
				<!-- 左侧：分类导航 -->
				<aside class="lg:col-span-3">
					<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-4 hover:shadow-md hover:border-orange-300 transition-all duration-200">
						<div class="flex items-center justify-between gap-3 mb-4">
							<div class="text-sm font-semibold text-slate-900">论坛分类</div>
							<Tag class="w-4 h-4 text-slate-500" />
						</div>

						<button
							type="button"
							class="w-full mb-4 h-11 rounded-lg font-semibold transition-all duration-200 flex items-center justify-center gap-2"
							:class="(!myType && btnAuth('forum','新增')) ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20 hover:bg-orange-600' : 'bg-slate-100 text-slate-400 cursor-not-allowed'"
							@click="(!myType && btnAuth('forum','新增')) ? addClick() : null"
						>
							<Plus class="w-4 h-4" />
							发布新帖
						</button>

						<div class="space-y-2">
							<button
								type="button"
								class="w-full h-10 px-3 rounded-lg flex items-center gap-3 text-sm font-medium border transition-all duration-200"
								:class="categoryIndex==-1 ? 'bg-orange-50 text-orange-600 border-orange-200' : 'bg-white text-slate-700 border-slate-200 hover:bg-slate-50'"
								@click="categoryClick(-1)"
							>
								<Layers class="w-4 h-4" />
								<span class="flex-1 text-left">全部</span>
							</button>
							<button
								type="button"
								v-for="(item,index) in categoryList"
								:key="index"
								class="w-full h-10 px-3 rounded-lg flex items-center gap-3 text-sm font-medium border transition-all duration-200"
								:class="categoryIndex==index ? 'bg-orange-50 text-orange-600 border-orange-200' : 'bg-white text-slate-700 border-slate-200 hover:bg-slate-50'"
								@click="categoryClick(index)"
							>
								<Tag class="w-4 h-4" />
								<span class="flex-1 text-left truncate" :title="item">{{item}}</span>
							</button>
						</div>
					</div>
				</aside>

				<!-- 右侧：帖子列表 -->
				<section class="lg:col-span-9">
					<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
						<div class="grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
							<div class="md:col-span-2">
								<div class="text-sm font-medium text-slate-700 mb-2">标题</div>
								<el-input
									class="search_inp w-full"
									v-model="searchQuery.title"
									placeholder="请输入标题关键词"
									clearable
									prefix-icon="Search"
								/>
							</div>
							<div class="flex justify-end gap-3">
								<el-button class="search_btn_primary rounded-lg shadow-sm hover:shadow-md transition-all duration-200" type="primary" @click="searchClick">搜索</el-button>
							</div>
						</div>
					</div>

					<div class="space-y-4">
						<div
							class="post-card"
							v-for="(item,index) in list"
							:key="index"
							@mouseenter="forumEnter(index)"
							@mouseleave="forumLeave"
							@click.stop="detailClick(item.id)"
						>
							<div class="post-card__left">
								<div class="avatar">
									<img
										v-if="item.avatarurl"
										:src="(item.avatarurl && item.avatarurl.substring(0,4)=='http') ? item.avatarurl : (baseUrl + item.avatarurl)"
										alt=""
									/>
									<img v-else src="@/assets/avatar.png" alt="" />
								</div>
							</div>

							<div class="post-card__body">
								<div class="post-title" :title="item.title">{{item.title}}</div>
								<div class="post-excerpt" :title="getExcerpt(item.content)">{{ getExcerpt(item.content) }}</div>
								<div class="post-tags" v-if="item.typeName">
									<span class="tag-pill">{{ item.typeName }}</span>
								</div>
								<div class="post-meta">
									<span class="meta-item">
										<User class="meta-icon" />
										<span>发布人：{{item.isAnonymous==1&&item.userid!=userid?'匿名':item.username}}</span>
									</span>
									<span class="meta-item">
										<Clock class="meta-icon" />
										<span>{{item.addtime}}</span>
									</span>
								</div>
							</div>

							<div class="post-card__right">
								<div class="thumb" v-if="item.imgUrls && item.imgUrls.length">
									<img :src="item.imgUrls[0]" alt="" />
								</div>
								<div class="post-actions" v-if="item.userid==userid&&forumShowIndex==index&&(btnAuth('forum','修改')||btnAuth('forum','删除'))">
									<template v-if="centerType && item.isDel>0">
										<el-button class="forum_del_btn" type="danger" @click.stop="showDialog(item)">内容违规-已删除</el-button>
									</template>
									<template v-else>
										<el-button class="forum_edit_btn" v-if="btnAuth('forum','修改')" type="primary" @click.stop="editClick(item.id)">修改</el-button>
										<el-button class="forum_del_btn" v-if="btnAuth('forum','删除')" type="danger" @click.stop="delClick(item.id)">删除</el-button>
									</template>
								</div>
								<div class="post-time" v-else>{{item.addtime}}</div>
							</div>
						</div>
					</div>

					<div class="pagination-container bg-white border border-slate-200 rounded-lg shadow-sm p-4 mt-6 flex justify-center hover:shadow-md hover:border-orange-300 transition-all duration-200">
						<el-pagination
							background
							:layout="layouts.join(',')"
							:total="total"
							:page-size="listQuery.limit"
							v-model:current-page="listQuery.page"
							prev-text="上一页"
							next-text="下一页"
							:hide-on-single-page="false"
							@size-change="sizeChange"
							@current-change="currentChange" />
					</div>
				</section>
			</div>

			<button
				type="button"
				v-if="!myType&&btnAuth('forum','新增')"
				class="fab"
				@click="addClick"
				aria-label="发布新帖"
			>
				<Plus class="fab-icon" />
			</button>
		</div>
	</div>

	<el-dialog
		title="处理结果"
		v-model="reportDialog.visible"
		width="50%">
		<el-form>
			<el-form-item label="举报原因" label-width="80px">
				{{reportDialog.data.reason}}
			</el-form-item>
			<el-form-item label="补充截图" label-width="80px">
				<uploads
					action="file/upload"
					tip="请上传封面"
					style="width: 100%;text-align: left;"
					disabled
					:fileUrls="reportDialog.data.images?reportDialog.data.images:''">
				</uploads>
			</el-form-item>
			<el-form-item label="处理建议" label-width="80px">
				{{reportDialog.data.suggestion}}
			</el-form-item>
		</el-form>
		<div slot="footer" style="text-align: center">
			<el-button type="primary" @click="reportDialog.visible = false">关 闭</el-button>
		</div>
	</el-dialog>

	<forumAdd ref="forumAddRef" @close="getList()"></forumAdd>
</template>

<script setup>
	import {
		ref,
		nextTick,
		getCurrentInstance
	} from 'vue';

	import {
		useRoute,
		useRouter
	} from 'vue-router'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//基础信息
	const tableName = 'forum'
	const formName = '互动交流'
	const router = useRouter()
	const route = useRoute()
	import { MessageSquare, Tag, Layers, Plus, User, Clock } from 'lucide-vue-next'

    const baseUrl = ref(context.$config.url)
    const sessionTable = localStorage.getItem('frontSessionTable')
    //判断是否调我的发布列表
    const myType = ref(false)
    //判断是否从个人中心跳转
    const centerType = ref(false)
	//权限验证
    const btnAuth = (e, a) => {
        if(context?.$toolUtil.isAuth(e, a)){
            return true
        }
        if(centerType.value){
            return context?.$toolUtil.isBackAuth(e,a)
        }
    }
    //分类
    const categoryList = ref([])
    const categoryIndex = ref(-1)
    const getCategoryList = () => {
        context?.$http({
            url: 'option/forumType/typeName',
            method: 'get'
        }).then(res => {
            categoryList.value = res.data.data
        })
    }
    const categoryClick = (index) => {
        listQuery.value.page = 1
        categoryIndex.value = index
        getList()
    }
	const list = ref([])
	const listLoading = ref(false)

	const listQuery = ref({
		page: 1,
		limit: 20,
		isdone: '开放',
		sort: 'isTop,topTime,addtime',
		order: 'desc,desc,desc',
        isDel:centerType.value?null:0
	})
	const myListQuery = ref({
		page: 1,
		limit: 20,
		parentid: 0,
		sort: 'addtime',
		order: 'desc'
	})
	const searchQuery = ref({})
	//分页
	const layouts = ref(["total","prev","pager","next","sizes"])
	const total = ref(0)
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const getList = () => {
		listLoading.value = true
		let params = myType.value ? JSON.parse(JSON.stringify(myListQuery.value)) : JSON.parse(JSON.stringify(listQuery
			.value))
		if (searchQuery.value.title && searchQuery.value.title != '') {
			params['title'] = `%${searchQuery.value.title}%`
		}
        if (categoryIndex.value != -1) {
            params.typeName = categoryList.value[categoryIndex.value]
        }
		context?.$http({
			url: `forum/${myType.value?'page':'flist'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
            list.value.forEach(item=>{
                if(item.cover){
                    item.imgUrls = item.cover.split(',').map(url=> {
                        if(url && url.substr(0,4)=='http'){
                            return url
                        }else{
                            return baseUrl.value + url
                        }
                    })
                }
            })
			total.value = res.data.data.total
		})
	}
	const userid = ref(0)
	const forumShowIndex = ref(-1)
	//判断按钮显示
	const forumEnter = (index) => {
		forumShowIndex.value = index
	}
	const forumLeave = () => {
		forumShowIndex.value = -1
	}
    import forumAdd from './add.vue'
    const forumAddRef = ref()
	//发布新贴
	const addClick = () => {
        forumAddRef.value.init()
	}
	//修改帖子
	const editClick = (id = null) => {
        forumAddRef.value.init(id)
	}
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	//初始化
	const init = () => {
		if (route.query.centerType) {
			centerType.value = true
		}
		if (route.query.myType) {
			myType.value = true
		}
		userid.value = context?.$toolUtil.storageGet('userid')
		getList()
        getCategoryList()
	}
	const detailForm = ref({})
	//详情
	const detailClick = (id = null) => {
		if (id) {
            router.push(`${tableName}Detail?id=` + id + (centerType.value?'&&centerType=1':''))
		}
	}
	//删除
	const delClick = (id = null) => {
		if (id) {
			ElMessageBox.confirm(`是否删除选中帖子`, '提示', {
				confirmButtonText: '是',
				cancelButtonText: '否',
				type: 'warning',
			}).then(() => {
				context?.$http({
					url: `forum/delete`,
					method: 'post',
					data: [id]
				}).then(res => {
					context?.$toolUtil.message('删除成功', 'success', () => {
						getList()
					})
				})
			}).catch(_ => {})
		}
	}
    const reportDialog = ref({
        visible:false,
        data:null
    })
    const showDialog = (item)=>{
        context.$http.get('/forumreport/list?forumId=11&status=已处理&sort=id&order=desc').then(res=>{
            if(res.data.code==0 && res.data.data.list?.length){
                reportDialog.value.data = res.data.data.list[0]
                reportDialog.value.visible = true
            }
        })
    }
	init()

	const getExcerpt = (html) => {
		if (!html) return ''
		const text = String(html)
			.replace(/<[^>]*>/g, ' ')
			.replace(/&nbsp;/g, ' ')
			.replace(/\s+/g, ' ')
			.trim()
		if (!text) return ''
		return text.length > 60 ? `${text.slice(0, 60)}...` : text
	}
</script>

<style lang="scss" scoped>
	.search_inp {
		:deep(.el-input__wrapper) {
			background: #fff;
			border: 1px solid var(--slate-200);
			box-shadow: 0 1px 2px rgba(0, 0, 0, 0.04);
			border-radius: 12px;
			transition: all 0.2s ease;
		}
		:deep(.el-input__wrapper.is-focus) {
			box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.2);
			border-color: rgba(249, 115, 22, 0.35);
		}
	}

	.search_btn_primary {
		:deep(.el-button) {
			background: var(--orange-500);
			border-color: var(--orange-500);
			color: #fff;
			font-weight: 700;
			padding: 10px 18px;
			border-radius: 12px;
			box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
		}
		:deep(.el-button:hover) {
			background: var(--orange-600);
			border-color: var(--orange-600);
		}
	}

	.post-card {
		display: grid;
		grid-template-columns: 56px 1fr;
		gap: 16px;
		padding: 18px;
		background: #fff;
		border: 1px solid var(--slate-200);
		border-radius: 16px;
		box-shadow: var(--card-shadow);
		transition: all 0.25s ease;
		cursor: pointer;
	}

	@media (min-width: 768px) {
		.post-card {
			grid-template-columns: 56px 1fr 160px;
			align-items: start;
		}
	}

	.post-card:hover {
		transform: translateY(-4px);
		box-shadow: var(--card-hover-shadow);
		border-color: rgba(249, 115, 22, 0.35);
	}

	.avatar {
		width: 56px;
		height: 56px;
		border-radius: 14px;
		overflow: hidden;
		background: var(--slate-50);
		border: 1px solid var(--slate-200);
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.avatar img {
		width: 100%;
		height: 100%;
		object-fit: cover;
	}

	.post-title {
		font-size: 16px;
		font-weight: 700;
		color: var(--slate-900);
		line-height: 1.35;
		margin-bottom: 8px;
	}

	.post-excerpt {
		font-size: 14px;
		color: var(--slate-700);
		opacity: 0.9;
		line-height: 1.6;
		margin-bottom: 10px;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}

	.post-tags {
		margin-bottom: 10px;
	}

	.tag-pill {
		display: inline-flex;
		align-items: center;
		padding: 4px 10px;
		border-radius: 999px;
		font-size: 12px;
		font-weight: 700;
		background: #fff7ed;
		border: 1px solid rgba(249, 115, 22, 0.2);
		color: var(--orange-600);
	}

	.post-meta {
		display: flex;
		flex-wrap: wrap;
		gap: 10px 14px;
		font-size: 12px;
		color: var(--slate-700);
		opacity: 0.9;
	}

	.meta-item {
		display: inline-flex;
		align-items: center;
		gap: 6px;
		white-space: nowrap;
	}

	.meta-icon {
		width: 14px;
		height: 14px;
		color: var(--orange-500);
	}

	.post-card__right {
		display: none;
	}

	@media (min-width: 768px) {
		.post-card__right {
			display: flex;
			flex-direction: column;
			align-items: flex-end;
			gap: 10px;
		}
	}

	.thumb {
		width: 160px;
		height: 90px;
		border-radius: 14px;
		overflow: hidden;
		background: var(--slate-50);
		border: 1px solid var(--slate-200);
	}

</style>