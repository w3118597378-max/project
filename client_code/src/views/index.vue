<template>
	<div id="index" class="main-containers">
		<!-- 顶部橙色强调条 -->
		<div class="orange-top-bar"></div>
		
        <div class="index_top">
            <div class="top-nav">
                <div class="modern-header">
                    <!-- 左侧：图标+项目名称 -->
                    <div class="header-left">
                        <div class="icon-container">
                            <i class="iconfont icon-trophy"></i>
                        </div>
                        <div class="project-info">
                            <h1 class="project-name">{{projectName}}</h1>
                            <p class="project-subtitle">比赛管理系统</p>
                        </div>
                    </div>

                    <!-- 右侧：登录/用户信息 -->
                    <div class="header-right">
                        <button v-if="!Token" class="login-modern" @click="loginClick">
                            <el-icon><User/></el-icon>登录
                        </button>

                        <div class="user" v-if="Token">
                            <el-dropdown class="avatar-container" trigger="hover">
                                <div class="avatar-wrapper">
                                    <img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px">
                                    <div class="nickname">{{store.getters['user/username']}}</div>
                                    <el-icon class="el-icon-arrow-down">
                                        <arrow-down />
                                    </el-icon>
                                </div>
                                <template #dropdown>
                                    <el-dropdown-menu class="user-dropDown" slot="dropdown">
                                        <el-dropdown-item @click="menuHandler('center')" class="center">
                                            <span>个人中心</span>
                                        </el-dropdown-item>
                                        <el-dropdown-item @click="loginOut" class="loginOut">
                                            <span>退出登录</span>
                                        </el-dropdown-item>
                                    </el-dropdown-menu>
                                </template>
                            </el-dropdown>
                        </div>
                    </div>
                </div>
                    <div class="menu-wrapper">
                        <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
                            <el-menu :unique-opened="true" :default-active="menuIndex"
                                 class="menu_view" mode="horizontal" @select="menuChange"
                                 :key="menuIndex"  :ellipsis="false">
                                <el-menu-item class="first-item" index="/index/home" @click="menuHandler('/')">
                                    <i class="iconfont icon-zhuye2"></i>
                                    <template #title>
                                        <span>首页</span>
                                    </template>
                                </el-menu-item>
                                <template v-for="(menu,index) in menuList" :key="menu.menu">
                                    <el-sub-menu v-if="menu.child.length>1" :index="menu.name" class="first-item" :teleported="true">
                                        <template #title>
                                            <i class="iconfont" :class="menu.icon"></i>
                                            <span>{{ menu.name }}</span>
                                        </template>
                                        <el-menu-item class="second-item" v-for=" (child,index1) in menu.child" :key="index1"
                                                      :index="child.url" @click="menuHandler(child.url)">{{ child.name }}
                                        </el-menu-item>
                                    </el-sub-menu>
                                    <el-menu-item v-else :index="menu.child[0].url" class="first-item" @click="menuHandler(menu.child[0].url)">
                                        <i class="iconfont" :class="menu.icon"></i>
                                        <template #title>
                                            <span>{{menu.child[0].name}}</span>
                                        </template>
                                    </el-menu-item>
                                </template>
                                <el-menu-item v-if="Token" :index="`/index/${context.$toolUtil.storageGet('frontSessionTable')}Center`" class="first-item" @click="menuHandler('center')">
                                    <i class="iconfont icon-user1"></i>
                                    <template #title>
                                        <span>个人中心</span>
                                    </template>
                                </el-menu-item>
                            </el-menu>
                        </el-scrollbar>
                    </div>
            </div>

                <div class="rotation_view">
                    <mySwiper :type="3" :data="rotationList" :autoHeight="false" :autoplay="true"
                        :loop="false" :navigation="true" :pagination="true"
                        paginationType="1" :scrollbar="false" slidesPerView="1"
                        spaceBetween="20" :centeredSlides="false"
                        :freeMode="false" effectType="0"
                        direction="horizontal">
                        <template #default="scope">
                            <img :src="scope.row.value?baseUrl + scope.row.value:''" @click="carouselClick(scope.row.url)">
                        </template>
                    </mySwiper>
                </div>
            </div>
        </div>
        
		<el-scrollbar class="contain_view body-containers">
			<router-view :key="routerKey"/>
			<el-backtop :right="100" :bottom="100" />
			<div class="bottom_view ql-snow ql-editor">
				<div class="front-footer">
					<div class="front-footer__container">
						<div class="front-footer__content">
							<div class="front-footer__section">
								<div class="front-footer__title">关于我们</div>
								<div class="front-footer__text">高校三人篮球联赛管理系统</div>
							</div>
							<div class="front-footer__section">
								<div class="front-footer__title">快速链接</div>
								<a href="#" class="front-footer__link">赛事规则</a>
								<a href="#" class="front-footer__link">联系我们</a>
							</div>
							<div class="front-footer__section">
								<div class="front-footer__title">关注我们</div>
								<div class="front-footer__text">欢迎关注最新赛事动态</div>
							</div>
						</div>
						<div class="front-footer__bottom">&copy; 2024 高校三人篮球联赛. All rights reserved.</div>
					</div>
				</div>
			</div>
		</el-scrollbar>
	</template>
<script setup>
	import menu from '@/utils/menu'
	import axios from 'axios'
    const moment = window.moment
	import {
		ref,
		onBeforeUnmount,
		getCurrentInstance,
		nextTick,
		computed,
		watch,
        provide
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const router = useRouter()
	const route = useRoute();
	const context = getCurrentInstance()?.appContext.config.globalProperties;
    const baseUrl = ref(context.$config.url)
    const projectName = ref(`高校三人篮球联赛管理系统`)
	const Token = ref('')
	if(localStorage.getItem('frontToken') && !store.getters['user/session'].id){
		store.dispatch('user/getSession')
	}
	onBeforeUnmount(() => {
	})
	// 获取默认菜单index
	const setMenuIndex = (path)=>{
        if(path){
            menuIndex.value = path.replace('Detail','List')
        }else{
            menuIndex.value = ''
        }
	}
	// 默认菜单index
	const menuIndex = ref('')
    const routerKey = ref(Math.random())
	watch(() => router.currentRoute.value,(value, oldValue) => {
		Token.value = context?.$toolUtil.storageGet('frontToken')
        if(value.path==oldValue?.path){
            //改变路由query时重新加载router-view
            routerKey.value = Math.random()
        }
        if(value.query.centerType){
            setMenuIndex()
        }else{
            setMenuIndex(value.path)
        }
	},{
		immediate:true
	})
	const init = () => {
		// 获取菜单
		getMenu()
		// 赋值token
		Token.value = context?.$toolUtil.storageGet('frontToken')
		// 轮播图
		getRotationList()
		if(Token.value){
			getSession()
		}
	}
	// 切换菜单保存index
	const menuChange = (e)=>{
		if(e=='chat')return
	}
	// 轮播图
	const rotationList = ref([])
	const getRotationList = () => {
		context?.$http({
			url: 'config/list',
			method: 'get',
			params: {
				page: 1,
				limit: 3
			}
		}).then(res => {
			rotationList.value = res.data.data.list
		})
	}

    // 轮播图跳转
    const carouselClick = (url)=>{
        if (url) {
            if (url.startsWith('http')) {
                window.open(url)
            } else {
                context.$router.push(url)
            }
        }
    }
	const menuList = ref([])
	const role = ref('')
	const getMenu = () => {
		let params = {
			page: 1,
			limit: 1,
			sort: 'id',
		}
		context?.$http({
			url: "menu/list",
			method: "get",
			params: params
		}).then(res => {
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
		})
		menuList.value = context?.$config.menuList
	}
	const loginClick = () => {
		context?.$toolUtil.storageSet('toPath',window.history.state.current)
		router.push('/login')
	}
	const loginOut = () => {
        store.dispatch('user/loginOut')
		context?.$toolUtil.message('退出成功', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
		Token.value = ''
	}
	//菜单跳转
	const menuHandler = (name) => {
		if (name == 'center') {
			name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
			menuChange('center')
		}
		router.push(name)
	}
	// 获取用户信息
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'cansaixuesheng'){
				context?.$toolUtil.storageSet("frontName", res.data.data.xuehao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'cansaixuesheng'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'qiuduiduizhang'){
				context?.$toolUtil.storageSet("frontName", res.data.data.duizhangzhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'qiuduiduizhang'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.zhaopian||'')
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'caipanyuan'){
				context?.$toolUtil.storageSet("frontName", res.data.data.caipanzhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'caipanyuan'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			}
		})
	}
	init()
</script>
<style lang="scss">
    .el-aside {
        transition: width 0.15s;
        -webkit-transition: width 0.15s;
        -moz-transition: width 0.15s;
        -webkit-transition: width 0.15s;
        -o-transition: width 0.15s;
    }
    .chat-badge{
        position: absolute;
        background: red;
        color: rgb(255, 255, 255);
        font-size: 12px;
        border-radius: 14px;
        padding:0 4px;
        height: 14px;
        display: flex;
        justify-content: center;
        align-items: center;
        top: -4px;
        right: -10px;
    }
	.index_top .top-header {
		width: 100%;
		padding: 30px 7%;
		background: #FFF7E8;
		margin:0 auto;
		display: flex;
		align-items: center;
		color: #000000;
		border-radius: 0px 0px 0 0;
		font-size: 16px;
	}
	.index_top .projectName {
		margin-right: auto;
		font-size: 24px;
		color: #000000;
		font-weight: 500;
		white-space: nowrap;
	}
	.index_top .weather {
		color: #000000;
		white-space: nowrap;
		margin: 5px 0 0;
	}
	.index_top .notice-btn{
		background: none;
		padding: 0;
	}
	.rotation_view {
		width: 100%;
		margin: 0 auto 12px;
	}
	.rotation_view .swiper{
		height:450px;
	}
	.rotation_view .swiper-slide img {
		width: 100%;
		height:100%;
		object-fit: cover;
		border-radius: 0px; 
	}
	.swiper-pagination-bullet-active {
		background: var(--theme);
	}
	.rotation_view .swiper-button-prev{
		margin-left:40px !important; 
	}
	.rotation_view .swiper-button-next{
		margin-right:40px !important; 
	}
	.rotation_view .swiper-button-next:after,.rotation_view .swiper-button-prev:after {
		background:rgba(255,255,255,.5);
		font-size: 20px;
		color: #fff;
		font-weight: 600;
		padding: 0 20px;
		width: 50px !important;
		height: 50px !important;
		line-height: 50px !important;
		text-align: center;
		border-radius: 100%;
	}
	.breadcrumb-wrapper {
		width: 100%;
		padding: 0px;
		margin: 0px auto!important;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #F6F9FE;
		border-radius: 0px 0px 0px 0px;
		height:50px;
	}
	.breadcrumb-wrapper .el-breadcrumb{
		width: 100%;
		font-size: 16px; 
	}
	.breadcrumb-wrapper .el-breadcrumb__separator{
		color: #000000;
	}
	.breadcrumb-wrapper .el-breadcrumb span{
		color: #9E9E9E!important; 
	}
	.breadcrumb-wrapper .el-breadcrumb span {
		display:flex;
		align-items:center;
		border-radius: 0px 0px 0px 0px;
		height:100%!important;
		padding:0px 5px;
		background: #FFF7E8;
		border-radius: 0px 0px 0px 0px;
	}
	.breadcrumb-wrapper .bread_view {
		height:100%;
	}
	.breadcrumb-wrapper .el-breadcrumb {
		height:100%;
	}
	.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb {
		background: #3B432C;
		border-radius: 0px 0px 0px 0px;
		color:#FFFFFF;
	}
	.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb span{
		background: #3B432C;
		border-radius: 0px 0px 0px 0px;
		color:#FFFFFF;
	}
	.breadcrumb-wrapper span.el-breadcrumb__item.first_breadcrumb span.el-breadcrumb__inner a {
		color:#FFFFFF;
	}
</style>
