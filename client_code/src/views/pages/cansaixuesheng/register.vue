<template>
    <div class="register_view">
<div class="backgif">
<div class="form" style="z-index: 1;">
    <div class="logo"></div>
    <div class="projectName">{{projectName}}注册</div>
			<div class="register_form">
				<div class="list_item">
					<div class="list_label">学号：</div>
					<el-input class="list_inp"
						 v-model="registerForm.xuehao"
						 placeholder="请输入学号"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">姓名：</div>
					<el-input class="list_inp"
						 v-model="registerForm.xingming"
						 placeholder="请输入姓名"
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
					<div class="list_label">性别：</div>
					<el-select
						class="list_sel"
						v-model="registerForm.xingbie"
						placeholder="请选择性别"
						>
						<el-option v-for="item in cansaixueshengxingbieLists" :label="item" :value="item"></el-option>
					</el-select>
				</div>
				<div class="list_item">
					<div class="list_label">年龄：</div>
					<el-input class="list_inp"
						 v-model="registerForm.nianling"
						 placeholder="请输入年龄"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">头像：</div>
					<div class="list_file_list">
						<uploads
							action="file/upload"
							tip="请上传头像"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="touxiangUploadSuccess">
						</uploads>
					</div>
				</div>
				<div class="list_item">
					<div class="list_label">手机：</div>
					<el-input class="list_inp"
						 v-model="registerForm.shouji"
						 placeholder="请输入手机"
						 type="text"
						/>
				</div>
				<div class="list_item">
					<div class="list_label">邮箱：</div>
					<el-input class="list_inp" v-model="registerForm.email" placeholder="邮箱" />
				</div>
				<div class="list_item list_code">
					<div class="list_label list_code_label">验证码：</div>
					<div class="list_code_item">
						<el-input class="list_code_inp" v-model="emailCode" placeholder="请输入验证码" />
						<el-button class="list_code_btn" :disabled="getCodeType" :type="getCodeType?'info':'primary'" @click="getEmailCode">{{setCode}}</el-button>
					</div>
				</div>
			</div>

    <el-button class="register" @click="handleRegister">注册</el-button>
    <div class="back" @click="close">已有账号，直接登录</div>

</div>
     <div class="gifdiv">
        
    </div>
    </div>


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
	const tableName = ref('cansaixuesheng')

	//公共方法
	const getUUID=()=> {
		return new Date().getTime();
	}
	const registerForm = ref({
        xingbie: '',
	})
	//验证码
	const time = ref(0)
	const setCode = ref('发送验证码')
	const getCodeType = ref(false)
	const timer=()=>{
		if(time.value>0){
			getCodeType.value = true
			setCode.value = time.value + 's'
			time.value--
			setTimeout(()=>{
				timer()
			},1000)
		}else{
			time.value = 0
			setCode.value = '发送验证码'
			getCodeType.value = false
		}
	}
	//邮箱验证码
	const emailCode = ref('')
	const getEmailCode=()=>{
		if((!registerForm.value.email)){
			context?.$toolUtil.message(`邮箱不能为空`,'error')
			return false
		}
		if(!context?.$toolUtil.isEmail(registerForm.value.email)){
			context?.$toolUtil.message(`邮箱格式不正确`,'error')
			return false
		}
		time.value = 60
		timer()
		context?.$http({
			url:`${tableName.value}/sendemail?email=`+registerForm.value.email,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.message(`发送成功`,'success')
		})
	}
	const cansaixueshengxingbieLists = ref([])
	const init=()=>{
		cansaixueshengxingbieLists.value = "男,女".split(',')
	}
    const touxiangUploadSuccess=(fileUrls)=> {
        registerForm.value.touxiang = fileUrls;
    }

	//注册按钮
	const handleRegister = () => {
		let url = tableName.value +"/register";
		if((!registerForm.value.xuehao)){
			context?.$toolUtil.message(`学号不能为空`,'error')
			return false
		}
		if(registerForm.value.xuehao&& (String(registerForm.value.xuehao).length<13)){
			context?.$toolUtil.message(`学号长度不能小于13`,'error')
			return false
		}
		if(registerForm.value.xuehao&& (String(registerForm.value.xuehao).length>13)){
			context?.$toolUtil.message(`学号长度不能大于13`,'error')
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
		if(registerForm.value.nianling&&(!context?.$toolUtil.isIntNumer(registerForm.value.nianling))){
			context?.$toolUtil.message(`年龄应输入整数`,'error')
			return false
		}
		if(registerForm.value.touxiang!=null){
			registerForm.value.touxiang = registerForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(registerForm.value.shouji&&(!context?.$toolUtil.isMobile(registerForm.value.shouji))){
			context?.$toolUtil.message(`手机应输入手机格式`,'error')
			return false
		}
		if(registerForm.value.email&&(!context?.$toolUtil.isEmail(registerForm.value.email))){
			context?.$toolUtil.message(`邮箱应输入邮件格式`,'error')
			return false
		}
		url=tableName.value +"/register?emailcode="+emailCode.value;
		if(registerForm.value.email&&(!context?.$toolUtil.isEmail(registerForm.value.email))){
			context?.$toolUtil.message('请输入正确的邮箱格式','error')
			return false
		}
		if((!emailCode.value)){
			context?.$toolUtil.message('验证码不能为空','error')
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
        background-image: url("http://clfile.zggen.cn/20251107/c3e7e4e7c0d7493fb92a1e3d6249f692.png")!important;
	}
.register_view {
   
       background: #F1FAFF;
    
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    overflow: hidden;
     

      background-size: cover;
  background-position: center;
  background-attachment: fixed;
   height:100vh ;
}

.register_view .form {
    background: #fcfcf4;
    width: 700px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    padding: 50px 60px 30px 30px;
    border-radius: 24px;
    position: relative;
    overflow: hidden;
    text-align: center;
}

.register_view .projectName {
    font-size: 24px;
    font-weight: 700;
    color:#000000;
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

}

.register_view .list_label {
    text-align: right;
}

.register_view .list_code {
    display: flex;
}

.register_view .list_code_item {display: flex;gap: 20px;}

.register_view .register {
    background: #3B432C;
    border: none;
    color: #fff;
    width: 60%;
    margin-top: 30px;
    height: 40px;
    font-size: 18px;
    border-radius: 20px!important;
}



.register_view  .form {
   width:813px!important;
    height: 1080px;
  
    background:#FFFFFF!important;
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
 
    display: flex; 

    margin-top:40px;
}

.register_view .list_label {
    text-align: left!important;
}

.register_view .el-input.list_inp {
    width:50% !important;
}
.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger {
}

.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger {

}

.register_view .el-select.list_sel {

 
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
    height:350px;
  overflow-y: auto!important;  
     
  scrollbar-width: none; 
  -ms-overflow-style: none; 
}

.register_view .list_item {
    margin-left:120px;
    align-items:flex-start!important;
}

.register_view .form {

    z-index: 1000;
    
}
.register_view .form {

    
    z-index: 1000;
    
    background:#FFFFFF!important;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    padding: 50px 60px 30px 30px;
    border-radius: 24px;
    position: relative;
    text-align: center;
}
.register_view .form {
   width:50%!important;
    
    height:100%!important;
    margin-left: 0px !important;
    margin-top: 10px !important;
    background: #FFFFFF !important;
box-shadow: 0px 6px 30px 0px #3B432C;
border-radius: 30px 0px 0px 30px;
    padding:0px 30px;
  
  scrollbar-width: none; 
  -ms-overflow-style: none; 
}


.register_view .list_item {
  margin:20px auto!important;
  padding:1px;
  padding-left:5px;
}
.register_view .list_item {
  display: flex;


  margin-top: 40px;
background: #F8F8F8!important;
border-radius: 20px!important;
    box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color)) inset;
}



.register_view .list_label {
 color: #000000;
}

.register_view .el-input__wrapper {
  border:0px!important;
  box-shadow:none;
}




.register_view .el-select__wrapper.el-tooltip__trigger.el-tooltip__trigger {
  border:0px!important;
  box-shadow:none;
}
.register_view .el-input.list_inp{
    border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}
.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger{
      border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}
.register_view .el-select.list_sel{
   border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}

.register_view .list_code_item{
    border-radius: 5px 5px 5px 5px;
border: 1px solid #E7EAE9;
}
.register_view .list_item{
   border:none!important;
   box-shadow:none;
    width:80%;
}


.register_view .rightimg {
    width:450px;
    height:550px;
    border-radius:20px;
    background:url(http://clfile.zggen.cn/20250905/e13654edfb0c408e94a59ffc9e43dce0.png) no-repeat;
    background-size:cover;
   
}





.register_view  .el-input__wrapper {
    background:none;
    box-shadow:none!important;
    border:none!important;
}

.register_view .el-input.list_inp {
border:none!important;
}

.register_view .el-select.list_sel {
    border:none!important;
    
}

.register_view .el-select__wrapper.el-tooltip__trigger.el-tooltip__trigger{
    background:none;
}
.register_view .el-input.el-input--prefix.el-input--suffix.el-date-editor.el-date-editor--date.list_date.el-tooltip__trigger.el-tooltip__trigger{
border:none;
}



.register_view .list_code_item{
    border:none;
    margin-left:auto;
}

.register_view  .rightimg {
    width: 550px;
    height: 673px;
    border-radius: 20px;
  
    background-size: cover;
}

.register_view .backgif {
   
    width:1320px;
    height:650px;
   
    background-size:100% 100%;

    display:flex;
    flex-wrap:nowrap;
    align-items:flex-end;
    justify-content:space-between;
}
.register_view  .gifdiv {
    width:50%;
    height:100%;
     background:url('http://clfile.zggen.cn/20251107/ed4301675bd844ba968a859889e0679f.png')no-repeat;
        background-size:100% 100%;
}
.register_view .list_item {
border: 0px solid var(--theme2)!important;
border-radius: 20px;
gap:10px;
}
.register_view .list_label {
    width:20%;
    margin-top:auto;
    margin-bottom:auto!important;
    background: none;
border-radius: 90px 90px 90px 90px;
    height:30px;
    line-height:30px!important;
    color:#3B432C;
}


.register_view button.el-button.el-button--primary.list_code_btn {
  margin-top:auto;
    margin-bottom:auto;
}

.register_view .back {
    width: 60%;
    margin: 20px auto;
    background: #FBFD86;
border-radius: 20px;
    height:40px;
    line-height:40px;
    color:#3B432C
}
</style>