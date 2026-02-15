<template>
	<div>
		<div class="center_view edit_form">
			<el-form class="userinfo_form" ref="userinfoFormRef" :model="form">
				<el-row>
					<el-col :span="12">
						<el-form-item label="裁判账号" prop="caipanzhanghao">
							<el-input class="list_inp" v-model="user.caipanzhanghao" readonly placeholder="裁判账号" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="裁判姓名" prop="caipanxingming">
							<el-input class="list_inp" v-model="user.caipanxingming"  placeholder="裁判姓名" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="头像" prop="touxiang">
							<uploads
								action="file/upload" 
								tip="请上传头像"
								style="width: 100%;text-align: left;"
								:fileUrls="user.touxiang?user.touxiang:''" 
								@change="caipanyuantouxiangUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="性别" prop="xingbie">
							<el-select 
								class="list_sel" 
								v-model="user.xingbie" 
								placeholder="请选择性别"
								>
								<el-option v-for="item in caipanyuanxingbieLists" :label="item" :value="item"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="手机号码" prop="shoujihaoma">
							<el-input class="list_inp" v-model="user.shoujihaoma"  placeholder="手机号码" clearable />
						</el-form-item>
					</el-col>
					<span class="formModel_btn_box">
						<el-button class='confirm_btn' type="primary" @click="onSubmit">保存</el-button>
					</span>
				</el-row>
			</el-form>
		</div>
	</div>
</template>

<script setup>
	import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/toolUtil";
	import {
		reactive,
		ref,
		getCurrentInstance,
        computed
	} from 'vue'
	import { useStore } from 'vuex'
	const store = useStore()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const tableName = ref('caipanyuan')
	const user = ref({})
	const caipanyuanxingbieLists = ref([])
	const init = () => {
		caipanyuanxingbieLists.value = "男,女".split(',')
	}
	const caipanyuantouxiangUploadSuccess=(fileUrls)=> {
	    user.value.touxiang = fileUrls;
	}
	const onSubmit = () => {
		if((!user.value.caipanzhanghao)){
			context?.$toolUtil.message(`裁判账号不能为空`,'error')
			return false
		}
		if((!user.value.caipanxingming)){
			context?.$toolUtil.message(`裁判姓名不能为空`,'error')
			return false
		}
		if((!user.value.mima)){
			context?.$toolUtil.message(`密码不能为空`,'error')
			return false
		}
		if(user.value.touxiang!=null){
			user.value.touxiang = user.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if((user.value.shoujihaoma)&&(!context?.$toolUtil.isMobile(user.value.shoujihaoma))){
			context?.$toolUtil.message(`手机号码应输入手机格式`,'error')
			return false
		}
		store.dispatch('user/update',user.value).then(res=>{
			if(res?.data&&res.data.code==0)context?.$toolUtil.message('修改成功','success')
		})

	}
	const getInfo = () => {
		context?.$http({
			url: `${tableName.value}/session`,
			method: 'get'
		}).then(res => {
			user.value = res.data.data
			init()
		})
	}
	getInfo()
</script>

<style lang="scss" scoped>
	// 表单
	.userinfo_form {
		:deep(.el-form-item) {
		}
	}
</style>
