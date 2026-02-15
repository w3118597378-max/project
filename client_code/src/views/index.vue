<template>
	<div id="index" class="main-containers">
        <div class="index_top">
<div class="top-header">
    <div class="projectName">{{projectName}}</div>





				<el-button v-if="!Token" class="login" @click="loginClick">
                    <el-icon><User/></el-icon>登录
				</el-button>

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

			<div class="rotation_view" >
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
		<el-scrollbar class="contain_view body-containers">
			<router-view :key="routerKey"/>
			<el-backtop :right="100" :bottom="100" />
			<div class="bottom_view ql-snow ql-editor">
                
			</div>
		</el-scrollbar>
	</div>
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
    padding: 0 0 0 30px;
    border: none;
    color: #000000;
    margin: 0 20px;
}
.index_top .login{
    background: none;
    border: 0px solid #000000;
    color: #000000;
    font-size: 16px;
    margin: 0 20px 0 0;
}

.index_top .avatar-wrapper {
    display: flex;
    align-items: center;
    gap: 6px;
}
.index_top img.user-avatar {
    width: 40px!important;
    height: 40px;
}
.index_top .avatar-wrapper .nickname{
    color: #000000;
    white-space: nowrap;
}
.index_top .avatar-wrapper .el-icon{
    color: #000000;   
}
.index_top .chat{
    white-space: nowrap;
    margin: 0 20px 0 0;
}
.index_top .cart{
    white-space: nowrap;
    margin: 0 20px 0 0;
}

li.center.el-dropdown-menu__item {
    display: none;
}
.bottom_view {
    width: 100%;
    padding: 20px 7%;
    margin: 0 auto;
    min-height: 100px;
background: #3B432C;
    color: #fff;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    align-content: center;
    justify-content: center;
    border-radius: 0 0 0px 0px;
}


.el-backtop {
    right:40px !important;
}
 .main-containers{
    background:#f5f7fa;
}
 #index {
    background: #f5f7fa;
}
.menu-wrapper{
    
    width: 100%;
    padding:10px 7%;
    margin: 0px auto 0px;
  background:#FFF7E8;
    border-top: 0px solid #f0f0f0;
    font-size: 16px;
    border: 1px solid #E5DCCD;
    border-radius: 0px;
    overflow-y: hidden;
}
.menu-wrapper .el-scrollbar__view{
}
.menu-wrapper ul.el-menu.el-menu--horizontal.menu_view {
    font-size: 16px;
    background: none;
    height: 50px;
    display: flex;
    justify-content: center;
}
.menu-wrapper .el-menu--horizontal.el-menu {
    border-bottom:0px;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item {
    align-items: center;
    border-bottom: 0px solid transparent;
    color: #000000;
    font-size: 16px;
    margin-right:20px;
    border-radius: 30px;
    padding: 0;
}
.menu-wrapper .el-menu--horizontal>.el-menu-item i{
    margin: 0 3px 0 0;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active{
    background: #000000 !important; 
    color: var(--theme) !important; 
    border-bottom: 0px solid var(--theme);
}


.menu-wrapper .menu_view .first-item{
    position: relative;
    margin-right:20px;
    padding: 0;
}
.menu-wrapper .menu_view .first-item .el-sub-menu__title{
    color: #000000;
    font-size: 16px;
    border-radius: 30px;
    padding: 0 20px 0 0;
}

.menu-wrapper .menu_view .first-item .el-sub-menu__title i{
    margin: 0 3px 0 0;
}

.menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title{
    background: #000000; 
    border-bottom: none;
    border-bottom: 0px solid var(--theme);
    border-radius: 30px;
}
.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active,.menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title {
    background: #000000;  
    color: var(--theme)!important;
    font-size: inherit;
    border-radius: 30px;
    border-bottom: 0px solid var(--theme);
}




.menu-wrapper .el-sub-menu .el-sub-menu__icon-arrow {
    font-size: 12px;
    margin-top: -6px !important;
    position: absolute;
    top: 50%;
    right: 0;
}
.menu-wrapper .menu_wrapper {
    --el-menu-bg-color: none;
    --el-menu-active-color: var(--theme-dark);
}
.menu-wrapper .el-menu--horizontal>.el-menu-item .el-sub-menu__icon-more{
    color: #000000;
}
.menu-wrapper .el-menu--horizontal>.el-sub-menu .el-sub-menu__title{
    border: 0;
}



.menu-wrapper .el-menu--popup {
    border: none;
    border-radius: var(--el-border-radius-small);
    box-shadow: var(--el-box-shadow-light);
    min-width: 200px;
    padding: 10px;
    z-index: 100;
}
.menu-wrapper .el-menu--popup .second-item{
    border-radius: 30px;
}


.menu-wrapper i.el-icon.el-sub-menu__icon-more {
    color: #0949f7 !important;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active{
background: #000000!important;
border-radius: 5px 5px 5px 5px;
height: 30px;
line-height:30px;
margin-top:auto;
margin-bottom:auto;
padding:10px;
color:#000000!important;
}

.menu-wrapper li.el-sub-menu.is-active.first-item {
background: #000000!important;
border-radius: 5px 5px 5px 5px;
height: 30px;
line-height:30px;
margin-top:auto;
margin-bottom:auto;
padding:10px;
color:#000000!important;
}

.menu-wrapper .el-menu--horizontal>.el-menu-item.is-active, .menu-wrapper .el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title {
    background:none;
    color:#FFFFFF!important;
}


.menu-wrapper i.el-icon.el-sub-menu__icon-arrow {
    display:none;
}


.menu-wrapper .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,.menu-wrapper  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
    background:none;
}
.menu-wrapper .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,.menu-wrapper  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
    color:#000000
}
.menu-wrapper .el-menu--horizontal>.el-sub-menu:hover .el-sub-menu__title{
    background:none;
}

.menu-wrapper ul.el-menu.el-menu--popup.el-menu--popup-bottom-start {
    
background:#FFF7E8;
border-radius: 5px 5px 5px 5px;
}


.menu-wrapper ul.el-menu.el-menu--popup.el-menu--popup-bottom-start li.el-menu-item.second-item {
    background:none;
    color:#000000;
    border:none;
    box-shadow:none;
}

/* .el-menu{
    background:none;
} */

ul.el-menu.el-menu--popup.el-menu--popup-bottom-start {
   background:#FFF7E8;
border-radius: 5px 5px 5px 5px;
border:none;
   box-shadow:none;
   padding:10px;
}
.el-menu--horizontal .el-menu .el-menu-item, .el-menu--horizontal .el-menu .el-sub-menu__title{
   background:none;
   color:#000000
}
.el-menu--horizontal .el-menu .el-menu-item.is-active, .el-menu--horizontal .el-menu .el-menu-item.is-active:hover, .el-menu--horizontal .el-menu .el-sub-menu.is-active>.el-sub-menu__title, .el-menu--horizontal .el-menu .el-sub-menu.is-active>.el-sub-menu__title:hover{
   background: #000000;
   color:#FFFFFF;
border-radius: 5px 5px 5px 5px;
}
.el-menu--horizontal .el-menu-item:not(.is-disabled):focus, .el-menu--horizontal .el-menu-item:not(.is-disabled):hover{
 background: #000000;
   color:#FFFFFF;
border-radius: 5px 5px 5px 5px;
}

/* .el-popper.is-light, .el-popper.is-light>.el-popper__arrow:before{
    background: none;
    border:none;
    box-shadow:none;
} */
.rotation_view{
    width: 100%;
    padding: 0;
    margin: 0px auto;
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