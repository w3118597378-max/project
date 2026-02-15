<template>
	<div class="detail-page">
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view">
                <el-button class="back_btn" @click="backClick">返回</el-button>
            </div>
        </div>
		<div class="detail_view">
			<div class="swiper_view">
				<mySwiper :data="bannerList" :type="3"
					:loop="true"
					:navigation="false"
					:pagination="false"
					:paginationType="1"
					:scrollbar="false"
					:slidesPerView="1"
					:spaceBetween="22"
					:autoHeight="true"
					:centeredSlides="false"
					:freeMode="false"
					:effectType="1"
					direction="horizontal"
					:autoplay="true"
					:slidesPerColumn="1">
					<template #default="scope">
						<img :src="scope.row?(scope.row.startsWith('http')?'':baseUrl) + scope.row:''">
					</template>
				</mySwiper>
			</div>
			<div class="info_view">
				<div class="title_view">
					<div class="detail_title">
						<span>{{detail.saishimingcheng}}</span>
					</div>
					<div class="collect_view" v-if="!collectType" @click="collectClick(1)">
						<i class="iconfont icon-likefill4"></i>
						<span>收藏</span>
					</div>
					<div class="collect_view" v-if="collectType" @click="collectClick(-1)">
						<i class="iconfont iconfontActive icon-likefill4"></i>
						<span class="textActive">取消收藏</span>
					</div>
				</div>
				<div class="info_item">
					<div class="info_label">比赛场地</div>

					<div  class="info_text" >{{detail.bisaichangdi}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">比赛时间</div>

					<div  class="info_text" >{{detail.bisaishijian}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">比赛赛制</div>

					<div  class="info_text" >{{detail.bisaisaizhi}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">比赛规则</div>

					<div  class="info_text" >{{detail.bisaiguize}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">收藏数</div>

					<div  class="info_text" >{{detail.storeupNumber}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">评论数</div>

					<div  class="info_text" >{{detail.discussNumber}}</div>
				</div>
				<div class="btn_view">
					<el-button class="cross_btn" v-if="btnFrontAuth('lanqiusaishi','报名')" @click="bisaibaomingonAcross('报名','','','','')" type="warning">报名</el-button>
					<el-button class="edit_btn" v-if="centerType&&btnAuth('lanqiusaishi','修改')" type="primary" @click="editClick">修改</el-button>
					<el-button class="del_btn" v-if="centerType&&btnAuth('lanqiusaishi','删除')" type="danger" @click="delClick">删除</el-button>
				</div>
			</div>
		</div>
		<el-tabs type="border-card" v-model="activeName" class="tabs_view">
			<el-tab-pane label="比赛赛程" name="first">
				<div v-html="detail.bisaisaicheng"></div>
			</el-tab-pane>
			<el-tab-pane label="评论" name="commentActive">
				<div class="my_comment_view">
					<el-form ref="commentFormRef" :model="commentForm" class="my_comment_form"
						:rules="commentRules">
						<el-form-item prop="content">
                            <editor :value="commentForm.content" placeholder="善语结善缘,恶语伤人心"
                                    class="list_editor" @change="contentChange"></editor>
						</el-form-item>
					</el-form>
					<div class="comment_btn">
						<el-button class="add_btn" type="primary" @click="commentSave">立即评论</el-button>
						<el-button class="reset_btn" @click="resetForm">重置</el-button>
					</div>
				</div>
				<div class="comment_list">
					<div class="comment" v-for="(item,index) in commentList" :key="index">
						<div class="comment_top">
							<div class="comment_user">
								<div class="comment_user_img">
                                    <img v-if="item.avatarurl" :src="baseUrl + item.avatarurl" alt="">
                                    <img v-else src="@/assets/avatar.png" alt="">
								</div>
								<div class="comment_user_info">
									{{item.nickname}}
								</div>
							</div>
							<div class="comment_time">{{item.addtime}}</div>
                            <div class="istop" v-if="item.istop">
                                <span class="iconfont icon-flight-takeoff-fill"></span>置顶
                            </div>
                            <div class="zancai-box">
                                <div class="zan-item active" v-if="commentHasZan(item,1)" @click="commentZanCaiClick(item,1)" style="cursor: pointer;">
                                    <span class="iconfont icon-thumb-up-fill2"></span>
                                    <span class="label">赞</span>
                                    <span class="num">({{item.thumbsupnum}})</span>
                                </div>
                                <div class="zan-item" v-else @click="commentZanCaiClick(item,1)" style="cursor: pointer;">
                                    <span class="iconfont icon-thumb-up-line2"></span>
                                    <span class="label">赞</span>
                                    <span class="num">({{item.thumbsupnum}})</span>
                                </div>
                                <div class="cai-item active" v-if="commentHasCai(item,1)" @click="commentZanCaiClick(item,-1)" style="cursor: pointer;">
                                    <span class="iconfont icon-thumb-down-fill2"></span>
                                    <span class="label">踩</span>
                                    <span class="num">({{item.crazilynum}})</span>
                                </div>
                                <div class="cai-item" v-else @click="commentZanCaiClick(item,-1)" style="cursor: pointer;">
                                    <span class="iconfont icon-thumb-down-line2"></span>
                                    <span class="label">踩</span>
                                    <span class="num">({{item.crazilynum}})</span>
                                </div>
                            </div>
						</div>
						<div class="comment_bottom">
                            <div class="comment_content" v-html="item.content"></div>
                            <div v-if="item.userid==user.id" class="comment_action">
                                <span class="del" @click="commentDel(item)" style="cursor: pointer">删除</span>
                            </div>
							<div class="comment_reply" v-if="item.reply">
								回复：<span v-html="item.reply"></span>
							</div>
						</div>
					</div>
				</div>
				<el-pagination
					background
					:layout="layouts.join(',')"
					:total="commentTotal"
					:page-size="commentQuery.limit"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					@size-change="commentSizeChange"
					@current-change="commentCurrentChange" />
			</el-tab-pane>
		</el-tabs>
	</div>
</template>
<script setup>
	import axios from 'axios'
    const moment = window.moment
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed,
        inject
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const userAvatar = computed(()=>store.getters['user/avatar'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
    const baseUrl = ref(context.$config.url)
	//基础信息
	const tableName = 'lanqiusaishi'
	const formName = '篮球赛事'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	//查看权限验证
	const btnFrontAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isFrontAuth(e,a)
		}
	}
	// 返回
	const backClick = () =>{
		history.back()
	}
	// 轮播图
	const bannerList = ref([])
	// 详情
	const title = ref('')
	const detail = ref({})
    const activeName = ref('first')
	const getDetail = () => {
		context?.$http({
			url: `${tableName}/detail/${route.query.id}`,
			method: 'get'
		}).then(res => {
            bannerList.value = res.data.data.fengmian?res.data.data.fengmian.split(','):[]
            title.value = res.data.data.saishimingcheng
			detail.value = res.data.data
		})
	}
	// 下载文件
	const downClick = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('frontToken')
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
    const approvalSave = async (form)=>{
        context.$http.post(`${tableName}/update`,form).then(res => {
            context.$message.success('审核成功')
            approvalRef.value.approvalVisible = false
            init()
        })
    }
	// 判断是否从个人中心跳转
	const centerType = ref(false)
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getDetail()
		// 收藏
		getCollect()
		// 评论
		getCommentList()
	}
	// 收藏
	const collectType = ref(false)
	const collectInfo = ref({})
	const getCollect = () => {
		if (context?.$toolUtil.storageGet('frontToken')) {
			context?.$http({
				url: 'storeup/list',
				method: 'get',
				params: {
					page: 1,
					limit: 1,
					type: 1,
					refid: route.query.id,
					tablename: tableName,
					userid: context?.$toolUtil.storageGet('userid')
				}
			}).then(res => {
				if (res.data.data.list.length) {
					collectType.value = true
					collectInfo.value = res.data.data.list[0]
				}else{
					collectType.value = false
					collectInfo.value = {}
				}
			})
		}
	}
	// 收藏按钮
	const collectClick = (type) => {
		if (type == 1 && !collectType.value) {
			let params = {
				name: title.value,
				picture: bannerList.value[0],
				refid: detail.value.id,
				type: type,
				tablename: tableName,
				userid: context?.$toolUtil.storageGet('userid')
			}
			context?.$http({
				url: 'storeup/add',
				method: 'post',
				data: params
			}).then(res => {
				detail.value.storeupNumber += 1
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: detail.value
				})
				collectType.value = true
				getCollect()
				context?.$toolUtil.message('收藏成功', 'success')
			})
		}
		else if (type == -1 && collectType.value) {
			let ids = []
			ids.push(collectInfo.value.id)
			context?.$http({
				url: 'storeup/delete',
				method: 'post',
				data: ids
			}).then(res => {
				detail.value.storeupNumber -= 1
				context?.$http({
					url: `${tableName}/update`,
					method: 'post',
					data: detail.value
				})
				collectInfo.value = {}
				collectType.value = false
				context?.$toolUtil.message('取消成功', 'success')
			})
		}
	}
	const sensitiveWordsArr = ref([])
	const getSensitiveWords = ()=>{
		context.$http.get('sensitiveword/detail/1').then(res=>{
			sensitiveWordsArr.value = res.data.data.content.split(',')
		})
	}
	getSensitiveWords()
    const contentChange = (e)=>{
        commentForm.value.content = e
    }
	//评论
	const commentForm = ref({
		content: '',
		refid: route.query.id,
		userid: context?.$toolUtil.storageGet('userid'),
		nickname: context?.$toolUtil.storageGet('frontName'),
		avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : '',
	})
	const commentRules = ref({
		content: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ]
	})
	const commentQuery = ref({
		page: 1,
		limit: 10,
		refid: route.query.id,
        sort: 'istop',
        order: 'desc',
	})
	const layouts = ref(["total","prev","pager","next","sizes"])
	const commentList = ref([])
	const commentTotal = ref(0)
	const commentFormRef = ref(null)
	const commentSizeChange = (size) =>{
		commentQuery.value.limit = size
		getCommentList()
	}
	const commentCurrentChange = (page) =>{
		commentQuery.value.page = page
		getCommentList()
	}
	const getCommentList = () => {
		context?.$http({
			url: `discuss${tableName}/list`,
			params: commentQuery.value,
			method: 'get'
		}).then(res => {
			commentList.value = res.data.data.list
			commentTotal.value = res.data.data.total

		})
	}
	//提交评论
	const commentSave = async () => {
        if(!commentForm.value.content ||commentForm.value.content=='<p><br></p>'){
            return context.$message.error("请输入评论内容")
        }
        for (var i = 0; i < sensitiveWordsArr.value.length; i++) {
            //全局替换
            var reg = new RegExp(sensitiveWordsArr.value[i], "g");
            //判断内容中是否包括敏感词
            if (commentForm.value.content.indexOf(sensitiveWordsArr.value[i]) > -1) {
                // 将敏感词替换为 **
                commentForm.value.content = commentForm.value.content.replace(reg, "**");
            }
        }
		commentFormRef.value.validate((valid) => {
			if (valid) {
				context?.$http({
					url: `discuss${tableName}/add`,
					method: 'post',
					data: commentForm.value
				}).then(res => {
                    context.$http.get(`${tableName}/info/${detail.value.id}`).then(res=>{
                        let detail = res.data.data
                        detail.discussNumber++
                        context.$http.post(`${tableName}/update`,detail).then(()=>{
                            getDetail()
                        })
                    })
					context?.$toolUtil.message('评论成功', 'success')
                    resetForm()
                    getCommentList()
				})
			}
		})
	}
    const commentDel = (item)=>{
        context.$confirm("确定要删除该评论吗？","提示").then(()=>{
            context.$http.post(`discuss${tableName}/delete`,[item.id]).then(res=>{
                if(res.data.code==0){
                    context.$message.success("删除成功")
                    context.$http.get(`${tableName}/info/${detail.value.id}`).then(res=>{
                        let detail = res.data.data
                        detail.discussNumber--
                        context.$http.post(`${tableName}/update`,detail).then(()=>{
                            getDetail()
                        })
                    })
                    getCommentList()
                }
            })
        }).catch(()=>{})
    }
	const resetForm = () => {
		commentFormRef.value.resetFields()
	}
    const commentZanCaiClick = (item,type)=>{
        if(!user.value.id)return context.$message.error("请先登录")
        if(type==1){    //赞
            if(item.tuserids){
                let arr = item.tuserids.split(',')
                let index = arr.indexOf(`${user.value.id}`)
                if(index!=-1){
                    arr.splice(index,1)
                    item.thumbsupnum--
                }else{
                    arr.push(`${user.value.id}`)
                    item.thumbsupnum++
                }
                item.tuserids = arr.join(',')
            }else {
                item.tuserids = `${user.value.id}`
                item.thumbsupnum++
            }
            if(item.cuserids){
                let arr = item.cuserids.split(',')
                let index = arr.indexOf(`${user.value.id}`)
                if(index!=-1){  //点赞需要取消踩
                    arr.splice(index,1)
                    item.crazilynum--
                }
                item.cuserids = arr.join(',')
            }
        }else{  //踩
            if(item.cuserids){
                let arr = item.cuserids.split(',')
                let index = arr.indexOf(`${user.value.id}`)
                console.log(arr,user.value.id)
                if(index!=-1){
                    arr.splice(index,1)
                    item.crazilynum--
                }else{
                    arr.push(`${user.value.id}`)
                    item.crazilynum++
                }
                item.cuserids = arr.join(',')
            }else {
                item.cuserids = `${user.value.id}`
                item.crazilynum++
            }
            if(item.tuserids){
                let arr = item.tuserids.split(',')
                let index = arr.indexOf(`${user.value.id}`)
                if(index!=-1){  //点踩需要取消赞
                    arr.splice(index,1)
                    item.thumbsupnum--
                }
                item.tuserids = arr.join(',')
            }
        }
        context.$http.post(`discuss${tableName}/update`,item).then(res=>{
            getCommentList()
        })
    }
    const commentHasZan = (item,type)=>{
        if(!user.value.id)return false
        if(!item.tuserids)return false
        let arr = item.tuserids.split(',')
        return arr.includes(`${user.value.id}`)
    }
    const commentHasCai = (item,type)=>{
        if(!user.value.id)return false
        if(!item.cuserids)return false
        let arr = item.cuserids.split(',')
        return arr.includes(`${user.value.id}`)
    }
	//修改
	const editClick = () => {
		router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
	}
	//删除
	const delClick = () => {
		ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(()=>{
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: [detail.value.id]
			}).then(res=>{
				context?.$toolUtil.message('删除成功','success',()=>{
					history.back()
				})
			})

		}).catch(_ => {})
	}
	const bisaibaomingonAcross = async (btnType,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1) => {
		if(!context?.$toolUtil.storageGet('frontToken')){
			context?.$toolUtil.message('请登录后再操作！','error')
			return false
		}
		if(!btnAuth('lanqiusaishi',btnType)){
			context?.$toolUtil.message('暂无权限操作！','error')
			return false
		}
		context?.$toolUtil.storageSet('crossObj',JSON.stringify(detail.value))
		context?.$toolUtil.storageSet('crossTable',tableName)
		context?.$toolUtil.storageSet('crossStatusColumnName',statusColumnName)
		context?.$toolUtil.storageSet('crossTips',tips)
		context?.$toolUtil.storageSet('crossStatusColumnValue',statusColumnValue)
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = detail.value
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'warning')
					return;
				}
			}
		}
		nextTick(()=>{
			router.push(`/index/bisaibaomingAdd?type=cross&&id=${detail.value.id}`)
		})
	}
	onMounted(()=>{
		init()
	})
</script>
<style lang="scss" scoped>
	//底部盒子
	.tabs_view {
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
	}
</style>