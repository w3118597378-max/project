<template>
	<aside class="admin_sidebar" :class="{ 'admin_sidebar--collapse': collapse }">
		<div class="admin_sidebar__logo" v-if="!collapse">
			<div class="admin_sidebar__logoIcon">
				<Trophy class="lucide-icon" />
			</div>
			<div class="admin_sidebar__logoText">
				<div class="admin_sidebar__title">篮球管理系统</div>
				<div class="admin_sidebar__subtitle">Basketball Admin</div>
			</div>
		</div>
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="true" :default-active="menuIndex" class="menu_view"
				:collapse="collapse">
				<el-menu-item class="nav_item" index="/" @click="menuHandler('')">
					<div class="nav_item__inner" :class="{ 'nav_item__inner--active': isActive('/') }">
						<div class="nav_item__left">
							<Home class="lucide-icon" v-if="!collapse" />
							<span class="nav_item__text">首页</span>
						</div>
						<ChevronRight class="lucide-icon nav_item__chevron" v-if="!collapse && isActive('/')" />
					</div>
				</el-menu-item>
				<template v-for="(item,index) in menuList.backMenu" :key="index">
					<el-sub-menu class="nav_group" :index="item.menu">
						<template #title>
							<div class="nav_item__inner">
								<div class="nav_item__left">
									<component :is="getMenuIcon(item.menu)" class="lucide-icon" v-if="!collapse && getMenuIcon(item.menu)" />
									<i class="iconfont" :class="item.fontClass" v-else-if="!collapse"></i>
									<span class="nav_item__text">{{ item.menu }}</span>
								</div>
							</div>
						</template>
						<el-menu-item class="nav_item nav_item--child" v-for="(child,sort) in item.child" :key="sort"
							:index="getPath(child.classname||child.tableName,child.menuJump)"
							@click="menuHandler(child.classname||child.tableName,child.menuJump)">
							<div class="nav_item__inner" :class="{ 'nav_item__inner--active': isActive(getPath(child.classname||child.tableName,child.menuJump)) }">
								<div class="nav_item__left">
									<span class="nav_item__text">{{ child.menu }}</span>
								</div>
								<ChevronRight class="lucide-icon nav_item__chevron" v-if="!collapse && isActive(getPath(child.classname||child.tableName,child.menuJump))" />
							</div>
						</el-menu-item>
					</el-sub-menu>
				</template>
			</el-menu>
		</el-scrollbar>
	</aside>
</template>

<script setup>
	import menu from '@/utils/menu'
	import { Home, Trophy, Users, ShieldAlert, ClipboardList, BarChart3, Scale, CalendarDays, Image, MessageCircle, ChevronRight } from 'lucide-vue-next'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		watch
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//props
	const props = defineProps({
		collapse: Boolean
	})
	const {
		collapse
	} = toRefs(props)
	//data
	const menuList = ref([])
	const role = ref('')

	const iconMap = {
		'首页': Home,
		'球队队长管理': Users,
		'论坛举报管理': ShieldAlert,
		'普级比赛结果管理': ClipboardList,
		'比赛技术统计管理': BarChart3,
		'裁判员管理': Scale,
		'篮球赛事管理': Trophy,
		'轮播图管理': Image,
		'敏感词管理': ShieldAlert,
		'论坛类型管理': MessageCircle,
		'普级申请管理': ClipboardList,
		'加入球队管理': Users,
		'比赛报名管理': ClipboardList,
		'参赛学生管理': Users,
		'比赛成绩管理': BarChart3,
		'互动交流管理': MessageCircle,
		'球队排行榜管理': BarChart3,
		'竞赛日程管理': CalendarDays,
	}
	const getMenuIcon = (menuName) => {
		return iconMap[menuName] || null
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	const init = () => {
		const menus = menu.list()
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('role')

		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				menuList.value = menuList.value[i];
				break;
			}
		}
	}
    // 默认菜单index
    const menuIndex = ref('')
    watch(() => context.$router.currentRoute.value,(value, oldValue) => {
        menuIndex.value = decodeURIComponent(value.fullPath)
    },{
        immediate:true
    })
	const isActive = (path) => {
		return menuIndex.value === path
	}
	const getPath = (name,menuJump) => {
        if(name == 'center'){
            return `/${role.value}Center`
        }else if(name == 'storeup'){
            return `/storeup?type=${menuJump}`
        }else if(name == 'exampaper' && menuJump == '12'){
            return '/exampaperlist'
        }else if(name == 'examrecord' && menuJump == '22'){
            return '/examfailrecord'
        }else{
            return `/${name}${menuJump?'?menuJump='+menuJump:''}`
        }
	}
    const menuHandler = (name,menuJump) => {
        let url = getPath(name,menuJump)
        context.$router.push(url)
    }
	init()
</script>

<style>
	.admin_sidebar {
		width: 256px;
		background: #ffffff;
		border-right: 1px solid #e2e8f0;
		position: sticky;
		top: 0;
		height: 100vh;
		display: flex;
		flex-direction: column;
		flex: 0 0 auto;
	}
	.admin_sidebar--collapse {
		width: 64px;
	}

	.admin_sidebar__logo {
		padding: 24px;
		display: flex;
		align-items: center;
		gap: 12px;
		border-bottom: 1px solid #e2e8f0;
	}
	.admin_sidebar__logoIcon {
		width: 40px;
		height: 40px;
		display: flex;
		align-items: center;
		justify-content: center;
		border-radius: 12px;
		background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
		box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.25), 0 4px 6px -4px rgba(249, 115, 22, 0.25);
		color: #ffffff;
		flex: 0 0 auto;
	}
	.admin_sidebar__logoText {
		min-width: 0;
	}
	.admin_sidebar__title {
		font-size: 16px;
		font-weight: 700;
		color: #0f172a;
		line-height: 1.2;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.admin_sidebar__subtitle {
		margin-top: 4px;
		font-size: 12px;
		color: #64748b;
		line-height: 1.2;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.menu_scrollbar {
		flex: 1 1 auto;
	}

	.menu_view {
		border-right: none;
		background: transparent;
		padding: 12px;
	}

	.lucide-icon {
		width: 20px;
		height: 20px;
	}

	.nav_item,
	.nav_group {
		margin-bottom: 4px;
	}

	.nav_item__inner {
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 12px;
		padding: 12px 16px;
		border-radius: 10px;
		color: #475569;
		transition: background-color 0.15s ease, color 0.15s ease, box-shadow 0.15s ease;
	}

	.nav_item__left {
		display: inline-flex;
		align-items: center;
		gap: 10px;
		min-width: 0;
	}
	.nav_item__text {
		font-size: 14px;
		line-height: 20px;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.nav_item__chevron {
		color: #ea580c;
		flex: 0 0 auto;
	}

	.el-menu-item.nav_item {
		height: auto;
		line-height: normal;
		padding: 0 !important;
		color: inherit;
	}
	.el-menu-item.nav_item:hover {
		background: transparent;
	}
	.el-menu-item.nav_item.is-active {
		background: transparent;
	}

	.el-sub-menu.nav_group > .el-sub-menu__title {
		padding: 0 !important;
		height: auto;
		line-height: normal;
		background: transparent;
		border-radius: 10px;
		color: inherit;
	}
	.el-sub-menu.nav_group > .el-sub-menu__title:hover {
		background: transparent;
	}

	.nav_item__inner:hover {
		background: #f8fafc;
		color: #0f172a;
	}

	.el-menu-item.is-active .nav_item__inner,
	.el-sub-menu.is-active > .el-sub-menu__title .nav_item__inner {
		background: #fff7ed;
		color: #ea580c;
		font-weight: 500;
		box-shadow: 0 1px 2px rgba(15, 23, 42, 0.06);
	}

	.nav_item--child .nav_item__inner {
		padding-left: 28px;
	}

	.admin_sidebar {
		--el-menu-active-color: #ea580c;
		--el-menu-hover-bg-color: transparent;
		--el-menu-bg-color: transparent;
	}

	.el-menu--popup {
		border: 1px solid #e2e8f0;
		border-radius: 12px;
		box-shadow: 0 10px 20px rgba(15, 23, 42, 0.08);
		padding: 8px;
	}
	.el-menu--popup .el-menu-item {
		border-radius: 10px;
	}
	.el-menu--popup .el-menu-item:hover {
		background: #f8fafc;
	}
</style>