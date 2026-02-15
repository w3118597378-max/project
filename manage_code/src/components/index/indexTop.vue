<template>
	<div class="top_view">
		<div class="projectName">{{projectName}}</div>
<div class="right1">


    
</div>


			<el-dropdown class="avatar-container" trigger="hover">
				<div class="avatar-wrapper">
					<div class="nickname">欢迎 {{adminName}}</div>
					<img class="user-avatar" :src="store.getters['user/avatar']">
					<el-icon class="el-icon-arrow-down">
						<arrow-down />
					</el-icon>
				</div>
				<template #dropdown>
					<el-dropdown-menu class="user-dropDown" slot="dropdown">
						<el-dropdown-item class="center" @click="centerClick" v-if="role!='users'">
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="password" @click="updatepasswordClick">
							修改密码
						</el-dropdown-item>
						<el-dropdown-item class="front">
							<span style="display:block;" @click="frontClick">系统前台</span>
						</el-dropdown-item>
						<el-dropdown-item class="loginOut">
							<span style="display:block;" @click="onLogout">退出登录</span>
						</el-dropdown-item>
					</el-dropdown-menu>
				</template>
			</el-dropdown>


	</div>
</template>

<script setup>
	import axios from 'axios'
	const moment = window.moment
	import {
		toRefs,
		defineEmits,
		getCurrentInstance,
		ref,
		onBeforeUnmount,
		onMounted,
		computed,
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	const route = useRoute()
	const router = useRouter()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const baseUrl = ref(context.$config.url)
	const projectName = context.$project.projectName
	const emit = defineEmits(['collapseChange'])
	const role = context.$toolUtil.storageGet('sessionTable')
	const adminName = context.$toolUtil.storageGet('adminName')
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}

	const props = defineProps({
		collapse: Boolean
	})
	const {collapse} = toRefs(props)

	//获取用户信息
	import { useStore } from 'vuex'
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	store.dispatch('user/getSession').then(()=>{
		avatar.value = store.state.user.avatar
	})
	const toggleClick = () => {
		emit('collapseChange')
	}
	// 退出登录
	const onLogout = () => {
		let toolUtil = context?.$toolUtil
		store.dispatch('delAllCachedViews')
		store.dispatch('delAllVisitedViews')
        store.dispatch('user/loginOut')
		toolUtil.storageClear()
		router.replace({
			name: "login"
		});
	}
	// 跳转前台
	const frontClick = () => {
        window.open(`${context.$config.url}client/index.html#/index/home`,'_blank')
	}
	// 个人中心
	const centerClick = () => {
		router.push(`/${role}Center`)
	}
	// 修改密码
	const updatepasswordClick = () => {
		router.push(`/updatepassword`)
	}
</script>

<style lang="scss" scoped>
</style>
<style lang="scss">
/* 消息徽标容器 */
.message-badge-wrapper {
    margin-right: 15px;
    order: 10;
    position: relative;
    display: flex;
    align-items: center;
    cursor: pointer;
}

.message-badge-wrapper .message-icon {
    color: inherit;
    transition: opacity 0.3s;
}

.message-badge-wrapper:hover .message-icon {
    opacity: 0.8;
}

.message-badge-wrapper .message-badge {
    display: flex;
    align-items: center;
}

/* 徽标数字样式 */
.message-badge-wrapper :deep(.el-badge__content) {
    border: 2px solid #fff;
    font-size: 12px;
    font-weight: bold;
}
.top_view {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    padding: 20px;
background:none;
border-radius: 0px 0px 0px 0px;
     margin-top: 20px; 
}
.top_view .projectName {position: relative;padding-left: 50px;line-height: 44px;margin-right: auto;font-size: 24px;}



.top_view .currentDate {
    margin: 0 10px;
}

.top_view .notice-btn {
    margin: 0 10px;
}

.top_view img.user-avatar {
    width: 40px;
    height: 40px;
}

.top_view .avatar-wrapper {
    display: flex;
    align-items: center;
    gap: 6px;
}

.top_view .nickname {
    order: 2;
}

.top_view .avatar-wrapper>.el-icon {
    order: 3;
}
.top_view iframe {
    width: 220px;
    height: 18px;
}
.top_view .projectName {
    padding-left:0px!important;
}
.right1 {
    display:flex;
    background: rgba(255,255,255,0.7);
    align-items:center;
    justify-content:center;
border-radius: 45px 45px 45px 45px;
height: 60px;
line-height:60px;
padding:30px;
}

.top_view button.el-button.notice-btn {
background: rgba(255,255,255,0.7);
border-radius: 45px 45px 45px 45px;
height:60px;
padding:30px;
}

.top_view .el-dropdown.avatar-container {
background: rgba(255,255,255,0.7);
border-radius: 45px 45px 45px 45px;
height:60px;
padding:30px;
}
.right1 {
    margin-right:10px;
}
</style>