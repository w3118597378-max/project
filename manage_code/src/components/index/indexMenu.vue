<template>
	<div class="menu_wrapper" :class="{'menu_wrapper_collapse':collapse}">
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="true" :default-active="menuIndex" class="menu_view"
				:collapse="collapse">
				<el-menu-item class="first-item" index="/" @click="menuHandler('')">
					<i class="iconfont icon-zhuye2" v-if="collapse?false:true"></i>
					<template #title>
						<span>首页</span>
					</template>
				</el-menu-item>
                <template v-for=" (item,index) in menuList.backMenu">
                    <el-sub-menu   class="first-item" :index="item.menu">
                        <template #title>
                            <i class="iconfont" :class="item.fontClass" v-if="collapse?false:true"></i>
                            <span>{{ item.menu }}</span>
                        </template>
                        <el-menu-item class="second-item" v-for=" (child,sort) in item.child" :key="sort"
                            :index="getPath(child.classname||child.tableName,child.menuJump)"
                            @click="menuHandler(child.classname||child.tableName,child.menuJump)">{{ child.menu }}
                        </el-menu-item>
                    </el-sub-menu>
                </template>
			</el-menu>
		</el-scrollbar>
	</div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		nextTick,
        watch
	} from 'vue';
	import { useStore } from 'vuex'
	const store = useStore()
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
  .main-container {
    margin-left: 250px;
     background-color:none;
    margin-top: 0px;
    padding: 1px;
    min-height: calc(100vh - 0px);
 background: linear-gradient( 168deg, #E3E5E6 14%, #F1E9C5 80%);
  
}
.menu_wrapper{
      background-color:none;
    background: linear-gradient( 168deg, #E3E5E6 14%, #F1E9C5 80%);
    width: 250px;
    position: fixed;
    top:0px;
    left: 0;
    height: calc(100vh - 0px);
    padding: 20px 0;
    padding-top:20px;
    padding-left:10px;
    padding-right: 10px;
    padding-bottom: 10px;
}
.menu_wrapper .menu_view {
    background-color: var(--el-menu-bg-color);
    border-right:none;
    box-sizing: border-box;
    list-style: none;
    margin: 0;
    padding-left: 0;
    position: relative;
}

/** 首页 **/
.menu_wrapper .el-menu-item {
    align-items: center;
    border-bottom: 0px solid transparent;
    color: #000;
    font-size: 16px;
 
}
.menu_wrapper .el-menu-item:hover{
 background:none;
}
.menu_wrapper .el-menu-item.is-active{
    background: var(--theme) !important; 
    color: #fff !important; 
    font-size: 16px !important; 
    border-bottom: none;

}
.menu_wrapper .el-menu-item .iconfont{
    margin-right: 10px;
}
/** 其他 **/
.menu_wrapper .menu_view .first-item{
    position: relative;
}
.menu_wrapper .menu_view .first-item .el-sub-menu__title{
    color: #000;
    font-size: 16px;
}
.menu_wrapper .menu_view .first-item .el-sub-menu__title:hover{
    background:none;
}
.menu_wrapper .el-sub-menu.is-active .el-sub-menu__title{
   
    border-bottom: none;
    font-size:16px;
}
.menu_wrapper .el-menu-item.is-active,.menu_wrapper .el-sub-menu.is-active .el-sub-menu__title {
   
    color: #fff!important;
    font-size: 16px;
}
.menu_wrapper .el-menu-item:not(.is-disabled):focus,.menu_wrapper  .el-menu-item:not(.is-disabled):hover {
 background:none;
}
.menu_wrapper .menu_view .first-item .el-sub-menu__title .iconfont{
    margin-right: 10px;
}
.menu_wrapper {
    --el-menu-bg-color: none;
    --el-menu-active-color: var(--theme-dark);
}
.menu_wrapper .el-menu-item .el-sub-menu__icon-more{
    color: #fff;
}

/** 二级盒子 **/
.menu_wrapper .el-menu--popup {
    border: none;
    border-radius: var(--el-border-radius-small);
    box-shadow: var(--el-box-shadow-light);
    min-width: auto;
    padding: 0px 0;
    z-index: 100; 
}
.menu_wrapper li.el-menu-item.second-item {
    padding-left:20px !important;
    border-left:none;
}
.menu_wrapper li.el-menu-item.second-item:hover{
  background:none;
}
.menu_wrapper li.el-menu-item.second-item.is-active{
    background: var(--theme50) !important;
    color: #333!important;
}
.menu_wrapper i.el-icon.el-sub-menu__icon-more {
    color: #fff;
}
/* nb15 */
.menu_wrapper ul.el-menu.el-menu--vertical.menu_view.el-menu--vertical {
background: rgba(255,255,255,0.5);
border-radius: 20px 20px 20px 20px;
padding-top:40px;
padding-bottom:40px;
  min-height: calc(100vh - 20px);
}

.menu_wrapper  .el-sub-menu__title {
  position:relative!important;
  padding-left:40px!important;
 
}
.menu_wrapper .el-sub-menu__title::before {
    content: '';
    background: url(http://clfile.zggen.cn/20250827/3315fccd4aaf496eb5a1fe9d4989cfdc.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    background-size: 90% 100%;
    width:100%;
    height: 100%;
    text-align: center;
    line-height: 100px !important;
    font-size: 24px !important;
    padding: 0px !important;
    position: absolute !important;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    

}

.menu_wrapper i.iconfont{
  color:#FFD85F;
  z-index:1
}

.menu_wrapper .el-sub-menu__title span {
  color:#FFFFFF;
  z-index:1
}

.menu_wrapper ul.el-menu.el-menu--vertical.menu_view.el-menu--vertical li{
  margin-bottom:10px;
}

.menu_wrapper li.el-menu-item.is-active.first-item {
  position:relative!important;
  padding-left:40px!important;
  background:none!important;
}
.menu_wrapper li.el-menu-item.is-active.first-item::before {
    content: '';
    background: url(http://clfile.zggen.cn/20250827/3315fccd4aaf496eb5a1fe9d4989cfdc.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    background-size: 90% 100%;
    width:100%;
    height: 100%;
    text-align: center;
    line-height: 100px !important;
    font-size: 24px !important;
    padding: 0px !important;
    position: absolute !important;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    

}

i.iconfont.icon-zhuye2 {
  color:#FFD85F
}

.menu_wrapper li.el-menu-item.is-active.first-item  span{
  color:#ffff;
  z-index:1;
}

.menu_wrapper i.el-icon.el-sub-menu__icon-arrow {
  position:absolute!important;
  color:#fff;
  top:49px;
  right:90px;
}

.menu_wrapper li.el-menu-item.first-item {
  position:relative!important;
  padding-left:40px!important;
  background:none!important;
}
.menu_wrapper li.el-menu-item.first-item::before {
    content: '';
    background: url(http://clfile.zggen.cn/20250827/3315fccd4aaf496eb5a1fe9d4989cfdc.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: 90% 100%;
    background-repeat: no-repeat;
    background-position: center;
    width:100%;
    height: 100%;
    text-align: center;
    line-height: 100px !important;
    font-size: 24px !important;
    padding: 0px !important;
    position: absolute !important;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    

}

li.el-menu-item.first-item i.iconfont.icon-zhuye2 {
  color:#FFD85F

}

.menu_wrapper li.el-menu-item.first-item span {
 color:#ffff;
  z-index:1;

}

.menu_wrapper li.el-sub-menu.is-active.first-item ul.el-menu.el-menu--inline {
background:#F0E6C9!important;
  width:70%;
  margin:auto;
  border-radius: 5px 5px 5px 5px!important;
}

.menu_wrapper li.el-sub-menu.is-opened.first-item ul.el-menu.el-menu--inline{
background:#F0E6C9!important;
  width:90%;
  margin:auto;
  border-radius: 5px 5px 5px 5px!important;
}





.menu_wrapper li.el-menu-item.is-active.second-item {
  
background: #FFD85F!important;
border-radius: 0px 0px 0px 0px;
  color:#fff!important;
  border:10px solid #F0E6C9;
}
.menu_wrapper li.el-sub-menu.is-active.is-opened.first-item .el-sub-menu__title {
  position:relative!important;
  padding-left:40px!important;
}

.menu_wrapper  .el-sub-menu__title {
  position:relative!important;
  padding-left:40px!important;
 
}
.menu_wrapper li.el-sub-menu.is-active.is-opened.first-item .el-sub-menu__title::before {
    content: '';
    background: url(http://clfile.zggen.cn/20260107/ee5f04cead784fa380b9aea7e043fda2.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: 90% 100%;
    background-repeat: no-repeat;
    background-position: center;
    width:100%;
    height: 100%;
    text-align: center;
    line-height: 100px !important;
    font-size: 24px !important;
    padding: 0px !important;
    position: absolute !important;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    

}

li.el-sub-menu.is-active.is-opened.first-item .el-sub-menu__title i.iconfont.icon-common44 {
  color:#fff;
}
.menu_wrapper li.el-sub-menu.is-active.is-opened.first-item  i {
  color:#fff;
}

.menu_wrapper li.el-menu-item.is-active.second-item {
    padding-left:10px!important;
}
</style>