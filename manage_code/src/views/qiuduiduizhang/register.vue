<template>
	<div class="register_view">
<div class="bigdiv">
<div class="backgif"></div>
<div class="form" style="z-index: 1;">
    <div class="logo"></div>
    <div class="projectName">{{projectName}}注册</div>
			<div class="register_form">
				<div class="list_item">
					<div class="list_label">队长账号：</div>
					<el-input class="list_inp"
						 v-model="registerForm.duizhangzhanghao"
						 placeholder="请输入队长账号"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">队长姓名：</div>
					<el-input class="list_inp"
						 v-model="registerForm.duizhangxingming"
						 placeholder="请输入队长姓名"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">密码：</div>
					<el-input class="list_inp"
						 v-model="registerForm.mima"
						 placeholder="请输入密码"
						 type="password"
					     show-password
						 />
				</div>
				<div class="list_item">
					<div class="list_label">确认密码：</div>
					<el-input class="list_inp" v-model="registerForm.mima2" type="password" placeholder="请输入确认密码" show-password />
				</div>
				<div class="list_item">
					<div class="list_label">照片：</div>
					<div class="list_file_list">
						<uploads
							action="file/upload"
							tip="请上传照片"
							:fileUrls="registerForm.zhaopian?registerForm.zhaopian:''"
							@change="zhaopianUploadSuccess">
						</uploads>
					</div>
				</div>
				<div class="list_item">
					<div class="list_label">性别：</div>
					<el-select
						class="list_sel"
						v-model="registerForm.xingbie"
						placeholder="请选择性别"
						>
						<el-option v-for="item in qiuduiduizhangxingbieLists" :label="item" :value="item"></el-option>
					</el-select>
				</div>
				<div class="list_item">
					<div class="list_label">电话：</div>
					<el-input class="list_inp"
						 v-model="registerForm.dianhua"
						 placeholder="请输入电话"
						 type="text"
						/>
				</div>
			</div>

    <el-button class="register" @click="handleRegister">注册</el-button>
    <div class="back" @click="close">已有账号，直接登录</div>
    <div class="form-circle1"></div>
    <div class="form-circle2"></div>
</div>
</div>
<div class="circle1"></div>
<div class="circle2"></div>

	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	//获取注册类型
	import { useRoute } from 'vue-router';
	const route = useRoute()
	const tableName = ref('qiuduiduizhang')
	
	//公共方法
	const getUUID=()=> {
		return new Date().getTime();
	}
	const registerForm = ref({
        xingbie: '',
        qiuduimingcheng: '',
	})
	const qiuduiduizhangxingbieLists = ref([])
	const qiuduiduizhangqiuduimingchengLists = ref([])
	const init=()=>{
		qiuduiduizhangxingbieLists.value = "男,女".split(',')
		context?.$http({
			url:`option/qiuduipaixingbang/qiuduimingcheng`,
			method:'get',
		}).then(res=>{
			qiuduiduizhangqiuduimingchengLists.value = res.data.data
		})
	}
    const zhaopianUploadSuccess=(fileUrls)=> {
        registerForm.value.zhaopian = fileUrls;
    }

	//注册按钮
	const handleRegister = () => {
		let url = tableName.value +"/register";
		if((!registerForm.value.duizhangzhanghao)){
			context?.$toolUtil.message(`队长账号不能为空`,'error')
			return false
		}
		if((!registerForm.value.mima)){
			context?.$toolUtil.message(`密码不能为空`,'error')
			return false
		}
		if(registerForm.value.mima!=registerForm.value.mima2){
			context?.$toolUtil.message('两次密码输入不一致','error')
			return false
		}
		if(registerForm.value.zhaopian!=null){
			registerForm.value.zhaopian = registerForm.value.zhaopian.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(registerForm.value.dianhua&&(!context?.$toolUtil.isMobile(registerForm.value.dianhua))){
			context?.$toolUtil.message(`电话应输入手机格式`,'error')
			return false
		}
		context?.$http({
			url:url,
			method:'post',
			data:registerForm.value
		}).then(res=>{
			context?.$toolUtil.message('注册成功','success', obj=>{
				context?.$router.push({
					path: "/login"
				});
			})
		})
	}
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
	init()
	onMounted(()=>{

	})
</script>
<style lang="scss">
	.register_view {
        background-image: url("http://clfile.zggen.cn/20250728/efa8c4527e0d4b5fa31b9fa123463b16.webp")!important;
	}
.register_view {
     background-color:none;
    background: linear-gradient( 168deg, #E3E5E6 14%, #F1E9C5 80%);
    min-height: 100vh;
    display: flex;
    flex-direction: row-reverse; 
         background: url('') no-repeat center;
            background-size: cover;
            background-attachment: fixed;
     justify-content: center;
            align-items: center;
    position: relative;
flex-shrink: 0;
}

.register_view .form {

  
    padding: 10px 60px 0px 30px;
    border-radius: 24px;
    position: relative;
    overflow: hidden;
    text-align: center;
}

.register_view .projectName {
    font-size: 24px;
    font-weight: 700;
    width: 100%;
    text-align: center;
    margin-top: 20px;
}
.register_view .logo {
    background: url(http://clfile.zggen.cn/20250725/889d208e5ddd4107bce6600713cdf6d1.webp);
    background-size: 100% 100%;
    width: 80px;
    height: 80px;
    animation: float 3s ease-in-out infinite;
    margin: 0 auto;
}
@keyframes float{
    0%, 100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-10px);
    }
}

.register_view .circle1 {
    position: absolute;
    left: 100px;
    top: 100px;
    background: #daf4dc;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    z-index: 1;
    animation: float 4s ease-in-out infinite;
}

.register_view .circle2 {
    position: absolute;
    right: 100px;
    bottom: 100px;
    background: #EBEFD9;
    width: 300px;
    height: 300px;
    border-radius: 50%;
    z-index: 0;
    animation: float 5s ease-in-out infinite;
}
.register_view .form-circle1 {
    position: absolute;
    right: -34px;
    top: -32px;
    background: #dff6e9;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}

.register_view .form-circle2 {
    position: absolute;
    left: -34px;
    bottom: -32px;
    background: #f7efeb;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}


.register_view .register_form {
    text-align: left;
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin-top: 40px;
    padding: 0 50px;
}

.register_view .list_item {
    display: flex;
    align-items: center;
}

.register_view .list_label {
    min-width: 100px;
    text-align: right;
}

.register_view .list_code {
    display: flex;
}

.register_view .list_code_item {display: flex;gap: 20px;}

.register_view .register {
    background: var(--theme);
    border: none;
    color: #000000;
    width: 60%;
    margin-top: 30px;
    height: 40px!important;
    font-size: 18px;
    border-radius: 35px 35px 35px 35px;
}

.register_view .back {
    width: 60%;
    margin: 20px auto;
}
.register_view .backgif {
width: 601.2px;
height: 634.5px;

  background-image: url('http://clfile.zggen.cn/20250825/224f9c30281b40e684838fb061db7595.png');
  background-size: cover;  
  background-position: top center;  
  background-repeat: no-repeat;
}
.register_view  .form {
 width: 601.2px!important;
height: 634.5px!important;
    background:none!important;

}
.circle1 {
    display:none;
}

.circle2 {
    display:none;
    
}

.register_view .logo {
    display:none;
    
}

.form-circle1 {
    display:none;
    
}

.form-circle2 {
    display:none;
    
}
.register_view .register_form {
    display:block!important;
}

.register_view .list_item {
    width:500!important;
    display: flex; 
    flex-direction: column; 
    margin-top:40px;
}

.register_view .list_label {
    width:500px!important;
    text-align: left!important;
}

.register_view .el-input.list_inp {
    width:500px !important;
}
.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger {
}

.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger {
    width:500px!important;
}

.register_view .el-select.list_sel {
    width:500px !important;
 
}

.register_view .list_file_list {
    width:500px;
}

.register_view .upload-demo {
    margin-right:auto!important;
}



.register_view .list_label {
    font-family: Source Han Sans, Source Han Sans;
font-weight: 400;
font-size: 14px;
color: #9CA3AF;
line-height: 12px;
text-align: left;
font-style: normal;
text-transform: none;
margin-bottom:5px;
}

.register_view .register_form {
        height:400px;
      overflow-x: hidden !important; 
    overflow-y: auto !important;
    
  
  scrollbar-width: none; 
  -ms-overflow-style: none; 
}

.register_view .list_item {
    margin-left:120px;
    align-items:flex-start!important;
}

.register_view .form {

  
    display: block !important;
    z-index: 1000;
    
}
.register_view .form {
  
    display: block !important;
    z-index: 1000;

    border-radius: 24px;
    position: relative;
    text-align: center;
}
.register_view .form {
border-radius:0px!important;
}
.register_view .list_item {
    margin: 15px auto!important;
    
    width:450px;
}
.register_view .list_item {
  display: flex;
  flex-direction: row; 
   align-items: center!important; 
  margin-top: 40px;
background: #F8F8F8!important;
border-radius: 30px 30px 30px 30px;
    box-shadow:none!important;
  padding:5px;
}
.register_view .list_label {
  width: 17%!important;
  min-width: 17%!important;
 padding:5px;
 color: #646464;
}

.register_view .el-input__wrapper {
  border:0px!important;
  box-shadow:none;
}

.register_view .el-input.list_inp {
    width: 83%!important;
  min-width: 83%!important;
}


.register_view .el-input__wrapper {
  background:none;
}

.register_view .el-select__wrapper.el-tooltip__trigger.el-tooltip__trigger {
 background:none;
}
.register_view .bigdiv {
    display:flex;
    flex-direction: row-reverse; 
}
.register_view .back {
border-radius: 30px 30px 30px 30px;
border: 1px solid #BDB8A1;
height: 35px;
line-height:35px;
}
.register_view button.el-button.el-button--primary.list_code_btn span {
    color:#000000;
}
.register_view .list_label {
    width: 30% !important;
    min-width: 30% !important;
    padding: 5px;
    color: #646464;
}

</style>