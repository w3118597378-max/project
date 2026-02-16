<template>
	<div class="min-h-screen bg-slate-50">
		<div class="max-w-7xl mx-auto px-6 py-10">
			<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6 flex items-center justify-between hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="flex items-center gap-4">
					<div class="w-12 h-12 rounded-lg bg-gradient-to-br from-orange-500 to-orange-400 shadow-lg shadow-orange-500/25 flex items-center justify-center">
						<User class="w-6 h-6 text-white" />
					</div>
					<div>
						<div class="text-2xl font-bold text-slate-900">{{formName}}</div>
						<div class="text-sm text-slate-500">Personal Center</div>
					</div>
				</div>
			</div>
			<div class="bg-white border border-slate-200 rounded-lg shadow-sm p-6 hover:shadow-md hover:border-orange-300 transition-all duration-200">
				<div class="flex flex-wrap gap-3 mb-6 border-b border-slate-200 pb-4">
					<button type="button" class="px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200" :class="tabIndex=='center' ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'" @click="tabClick({tableName:'center'})">
						<User class="w-4 h-4 inline mr-2" />
						个人中心
					</button>
					<button type="button" class="px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200" :class="tabIndex=='updatePassword' ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'" @click="tabClick({tableName:'updatePassword'})">
						<Lock class="w-4 h-4 inline mr-2" />
						修改密码
					</button>
					<template v-for="(item,index) in menuList">
						<div v-if="item.child.length>1" class="relative" @mouseenter="usersTabHover(index)" @mouseleave="usersTabLeave">
							<button type="button" class="px-4 py-2 rounded-lg text-sm font-medium bg-slate-100 text-slate-700 hover:bg-slate-200 transition-all duration-200">
								{{item.menu}}
								<ChevronDown class="w-4 h-4 inline ml-1" />
							</button>
							<el-collapse-transition>
								<div class="absolute top-full left-0 mt-1 bg-white border border-slate-200 rounded-lg shadow-lg z-10 min-w-[150px]" v-if="usersTabIndex==index">
									<button type="button" v-for="(items,indexs) in item.child" class="block w-full px-4 py-2 text-left text-sm hover:bg-orange-50 hover:text-orange-600 transition-colors" @click="tabClick(items)">
										{{items.menu}}
									</button>
								</div>
							</el-collapse-transition>
						</div>
						<div v-else-if="hasBack(item.child[0])">
							<button type="button" class="px-4 py-2 rounded-lg text-sm font-medium bg-slate-100 text-slate-700 hover:bg-slate-200 transition-all duration-200" @click="tabClick(item.child[0])">
								{{item.child[0].menu}}
							</button>
						</div>
					</template>
					<button type="button" v-if="btnAuth('storeup','查看')" class="px-4 py-2 rounded-lg text-sm font-medium bg-slate-100 text-slate-700 hover:bg-slate-200 transition-all duration-200" @click="tabClick({tableName:'storeup',type:1})">
						<Heart class="w-4 h-4 inline mr-2" />
						我的收藏
					</button>
				</div>
				<div class="space-y-6" v-if="tabIndex=='center'">
					<el-form class="usersForm" ref="userFormRef" :model="userForm" label-width="120px" :rules="rules">
						<el-row :gutter="6">
							<el-col :span="12">
								<el-form-item prop="duizhangzhanghao" label="队长账号">
									<el-input class="form-input" v-model="userForm.duizhangzhanghao" placeholder="队长账号" readonly></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item prop="duizhangxingming" label="队长姓名">
									<el-input class="form-input" v-model="userForm.duizhangxingming" placeholder="队长姓名" ></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="24">
								<el-form-item prop="zhaopian" label="照片">
									<uploads
										action="file/upload" 
										tip="请上传照片"
										style="width: 100%;text-align: left;"
										:fileUrls="userForm.zhaopian?userForm.zhaopian:''" 
										@change="zhaopianUploadSuccess">
									</uploads>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="性别" prop="xingbie">
									<el-select
										class="form-select"
										v-model="userForm.xingbie" 
										placeholder="请选择性别"
										style="width:100%;"
										>
										<el-option v-for="(item,index) in xingbieLists" :label="item" :value="item">
										</el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item prop="dianhua" label="电话">
									<el-input class="form-input" v-model="userForm.dianhua" placeholder="电话" ></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="球队名称" prop="qiuduimingcheng">
									<el-select
										disabled
										class="form-select"
										v-model="userForm.qiuduimingcheng" 
										placeholder="请选择球队名称"
										style="width:100%;"
										>
										<el-option v-for="(item,index) in qiuduimingchengLists" :label="item" :value="item">
										</el-option>
									</el-select>
								</el-form-item>
							</el-col>
						</el-row>
						<div class="flex justify-end gap-3 pt-6">
							<el-button class="px-6 py-2 bg-orange-500 text-white rounded-lg hover:bg-orange-600 transition-colors shadow-sm" @click="updateSession">
								<Save class="w-4 h-4 inline mr-2" />
								更新信息
							</el-button>
							<el-button class="px-6 py-2 bg-slate-500 text-white rounded-lg hover:bg-slate-600 transition-colors shadow-sm" @click="loginout">
								<LogOut class="w-4 h-4 inline mr-2" />
								退出登录
							</el-button>
						</div>
					</el-form>
				</div>
				<div class="space-y-6" v-if="tabIndex=='updatePassword'">
					<el-form class="usersForm" ref="passwordFormRef" :model="passwordForm" label-width="120px"
						:rules="passwordRules">
						<el-row :gutter="6">
							<el-col :span="12">
								<el-form-item label="原密码" prop="mima">
									<el-input class="form-input" v-model="passwordForm.mima" placeholder="原密码"
										type="password" show-password></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="新密码" prop="newmima">
									<el-input class="form-input" v-model="passwordForm.newmima" placeholder="新密码"
										type="password" show-password></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item label="确认密码" prop="newmima2">
									<el-input class="form-input" v-model="passwordForm.newmima2" placeholder="确认密码"
										type="password" show-password></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<div class="flex justify-end pt-6">
							<el-button class="px-6 py-2 bg-orange-500 text-white rounded-lg hover:bg-orange-600 transition-colors shadow-sm" @click="updatePassword">
								<Lock class="w-4 h-4 inline mr-2" />
								修改密码
							</el-button>
						</div>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>
<script setup>
    const moment = window.moment
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import { useStore } from 'vuex'
	const store = useStore()
	import menu from '@/utils/menu'
	import { User, Lock, ChevronDown, Heart, Save, LogOut } from 'lucide-vue-next'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
    const baseUrl = ref(context.$config.url)
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'qiuduiduizhang'
	const formName = '个人中心'
	//基础信息
	const uploadUrl = context.$config.url + 'file/upload'
	//个人信息
	const userFormRef = ref(null)
	const userForm = ref({})
    //权限验证
    const btnAuth = (e, a) => {
        return context?.$toolUtil.isBackAuth(e, a)
    }
	//修改密码
	const passwordFormRef = ref(null)
	const passwordForm = ref({
		mima: '',
		newmima: '',
		newmima2: ''
	})
	const passwordRules = ref({
		mima: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
		newmima: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
		newmima2: [{
			required: true,
			message: '请输入',
			trigger: 'blur'
		}, ],
	})
	//验证规则
	const rules = ref({
		duizhangzhanghao: [
			{required: true,message: '请输入',trigger: 'blur'},
		],
		duizhangxingming: [
		],
		mima: [
			{required: true,message: '请输入',trigger: 'blur'},
		],
		zhaopian: [
		],
		xingbie: [
		],
		dianhua: [
			{ validator: context.$toolUtil.validator.mobile, trigger: 'blur' },
		],
		sfsh: [
		],
		shhf: [
		],
		qiuduimingcheng: [
		],
	})
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			context?.$toolUtil.storageSet("frontName", res.data.data.duizhangzhanghao)
			context?.$toolUtil.storageSet('headportrait',res.data.data.zhaopian||'')
			userForm.value = res.data.data
		})
	}
	//菜单跳转
	const tabIndex = ref('center')
	const tabClick = (item) => {
		if (item.tableName == 'center') {
			tabIndex.value = 'center'
			return
		}
		if (item.tableName == 'updatePassword') {
			passwordForm.value = {
				mima: '',
				newmima: '',
				newmima2: ''
			}
			tabIndex.value = 'updatePassword'
			return
		}
		if(item.tableName=='examrecord'&&item.menuJump=='22'){
			router.push(`/index/examfailrecord?centerType=1`)
			return
		}
		if(item.tableName=='forum'&&item.menuJump=='14'){
			router.push(`/index/forumList?centerType=1&myType=1`)
			return
		}
        if(item.tableName=='storeup'){
            router.push(`/index/storeupList?centerType=1&type=${item.type}`)
            return;
        }
        router.push(`/index/${item.classname||item.tableName}List?centerType=1${item.menuJump?'&menuJump='+item.menuJump:''}`)
	}
    const hasBack = (menu)=>{
        if(menu.tableName=='storeup'){
            return false
        }
        return true
    }
	// 修改密码
	const updatePassword = async ()=>{
		passwordFormRef.value.validate(async (valid) => {
			if (valid) {
				if(passwordForm.value.mima != userForm.value.mima){
					context?.$toolUtil.message('原密码不正确', 'error')
					return false
				}
				if(passwordForm.value.newmima != passwordForm.value.newmima2){
					context?.$toolUtil.message('两次输入密码不一致', 'error')
					return false
				}
                if(passwordForm.value.mima==passwordForm.value.newmima){
                    context?.$toolUtil.message('新密码不能与原密码相同', 'error')
                    return false
                }
				userForm.value.mima = passwordForm.value.newmima
				store.dispatch('user/update',userForm.value).then(res=>{
					if(res?.data&&res.data.code==0){
						context?.$toolUtil.message('更新成功','success')
						passwordForm.value = {
							mima: '',
							newmima: '',
							newmima2: ''
						}
						getSession()
					}
				})
			}
		})
	}
	//菜单
	const menuList = ref([])
	const role = ref('')
	//照片上传回调
	const zhaopianUploadSuccess=(e)=>{
		userForm.value.zhaopian = e
	}
	//性别列表
	const xingbieLists = ref([])
	//球队名称列表
	const qiuduimingchengLists = ref([])
	//初始化
	const init = () => {
		const menus = menu.list()
		let arr = []
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('frontRole')
		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				arr = menuList.value[i].backMenu
				break;
			}
		}
		menuList.value = arr
		xingbieLists.value = "男,女".split(',')
		context?.$http({
			url: `option/qiuduipaixingbang/qiuduimingcheng`,
			method: 'get',
		}).then(res=>{
			qiuduimingchengLists.value = res.data.data
		})
		getSession()
	}
	//菜单悬浮的显示与隐藏
	const usersTabIndex = ref(-1)
	const usersTabHover = (index) => {
		usersTabIndex.value = index
	}
	const usersTabLeave = () => {
		usersTabIndex.value = -1
	}
	//富文本
	const editorChange = (e,name) =>{
		userForm.value[name] = e
	}
	//保存
	const updateSession = () => {
		userFormRef.value.validate((valid)=>{
			if(valid){
				if(userForm.value.zhaopian!=null){
					userForm.value.zhaopian = userForm.value.zhaopian.replace(new RegExp(context?.$config.url,"g"),"");
				}
				store.dispatch('user/update',userForm.value).then(res=>{
					if(res?.data&&res.data.code==0){
						context?.$toolUtil.message('更新成功','success')
						getSession()
					}
				})
			}
		})
	}
	//退出登录
	const loginout = () => {
		context?.$toolUtil.message('退出成功', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
	}
	init()
</script>

<style lang="scss" scoped>
	.usersForm {
		:deep(.el-form-item) {
			.el-form-item__content {
			}
		}
	}
	
	.form-input {
		:deep(.el-input__wrapper) {
			border-radius: 8px;
			border: 1px solid #e2e8f0;
			box-shadow: none;
			transition: all 0.2s;
			
			&:hover {
				border-color: #cbd5e1;
			}
			
			&.is-focus {
				border-color: #f97316;
				box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.1);
			}
		}
	}
	
	.form-select {
		:deep(.el-select__wrapper) {
			border-radius: 8px;
			border: 1px solid #e2e8f0;
			box-shadow: none;
			transition: all 0.2s;
			
			&:hover {
				border-color: #cbd5e1;
			}
			
			&.is-focused {
				border-color: #f97316;
				box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.1);
			}
		}
	}
	
	:deep(.el-upload--picture-card) {
		border: 2px dashed #e2e8f0;
		border-radius: 8px;
		background: #f8fafc;
		transition: all 0.2s;
		
		&:hover {
			border-color: #f97316;
			background: #fff7ed;
		}
	}
	
	:deep(.el-upload-list--picture-card .el-upload-list__item) {
		border-radius: 8px;
	}
</style>