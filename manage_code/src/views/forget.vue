<template>
	<div class="forget_view">
<div class="backgif"></div>
<div class="form" style="z-index: 1;">
    <div class="pageTitle">{{pageTitle}}</div>
				<div class="tab_view">
					<div class="tab" :class="pageType==1?'tab_active':''">输入账号</div>
					<div class="tab" :class="pageType==2?'tab_active tab_active1':''"> 输入密保</div>
					<div class="tab" :class="pageType==3?'tab_active tab_active1 tab_active2':''">重置密码</div>
				</div>

			<div class="form-box">
			</div>

      
                    <el-button v-if="pageType==1" class="get_btn" @click="getSecurity">获取密保</el-button>
                    <el-button v-if="pageType==2" class="valid_btn" @click="validateSecurity">确认密保</el-button>
					<el-button v-if="pageType==3" class="update_btn" @click="updatePassword">重置密码</el-button>

  <div class="back" @click="close">已有账号，直接登录</div>
    <div class="form-circle1"></div>
    <div class="form-circle2"></div>
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
		computed,
	} from 'vue';
    import { useRoute } from 'vue-router'
    const route = useRoute()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
    onMounted(()=>{
    })
	const pageType = ref(1)
	const pageTitle = computed(()=>{
		return pageType.value==1?'输入账号':pageType.value==2?'输入密保':'重置密码'
	})
	const forgetForm = ref({})
	const userForm = ref({})
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss">
	.forget_view {
        background-image: url("http://clfile.zggen.cn/20250725/fb77905eda474cf29de2854b7bbfed30.webp")!important;
	}
.forget_view {
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

.forget_view .form {

    padding: 50px 60px;
    border-radius: 24px;
    position: relative;
    overflow: hidden;
    text-align: center;
}

.forget_view .projectName {
    font-size: 24px;
    font-weight: 700;
    width: 100%;
    text-align: center;
    
    margin-top: 20px;
}
.forget_view .logo {
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

.forget_view .circle1 {
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

.forget_view .circle2 {
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
.forget_view .form-circle1 {
    position: absolute;
    right: -34px;
    top: -32px;
    background: #dff6e9;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}

.forget_view .form-circle2 {
    position: absolute;
    left: -34px;
    bottom: -32px;
    background: #f7efeb;
    width: 140px;
    height: 140px;
    border-radius: 50%;
    z-index: -1;
}






.forget_view .register {
    background: var(--theme);
    border: none;
    color: #fff;
    width: 60%;
    margin-top: 30px;
    height: 40px;
    font-size: 18px;
}

.forget_view .back {
    width: 50%;
    margin: 20px auto;
}

.forget_view .tab {
    width: calc(33.3333%);
    padding:0px;
    box-sizing: border-box;
    text-align: center;
}
.forget_view .tab.tab_active {
    width: calc(33.3333%);
    padding: 0px;
    box-sizing: border-box;
    text-align: center;
    clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
    background: var(--theme);
    color: rgb(255, 255, 255);
}
.forget_view .tab_view {
   
    display: flex;
    align-items: center;
    padding:0px;
    font-size: 16px;
}

.forget_view .pageTitle {
    font-size: 24px;
    font-weight: 700;
    width: 100%;
    text-align: center;
    margin-top: 20px;
}

.forget_view .el-input.list_inp {
    height: 40px;
}

.forget_view .el-select__wrapper {
    height: 40px;
}

.forget_view button.el-button {
    background: var(--theme);
    color: #fff;
    width: 50%;
    border: none;
    height: 40px;
    margin-top: 30px;
}

.forget_view  .form {
    display: block;
    overflow: auto;
    position: relative;
}


.forget_view  .backgif {
width: 601.2px;
height: 634.5px;

 
  background-image: url('http://clfile.zggen.cn/20250825/224f9c30281b40e684838fb061db7595.png');
  background-size: cover;  
  background-position: top center;  
  background-repeat: no-repeat;
}
.forget_view .form {
    display: block;
    overflow: auto;
    position: relative;
}

.forget_view  .form {
     width: 601.2px!important;
height: 634.5px!important;

 
    background:none!important;
         overflow-y: auto; 
  
  scrollbar-width: none; 
  -ms-overflow-style: none; 
}

.circle1 {
 display:none
}

.form-circle1 {
 display:none
 
}

.form-circle2 {
 display:none
 
}


.forget_view .tab {
    clip-path: none !important;


border-radius: 10px!important;
}


.forget_view .tab.tab_active {

box-shadow: 0px 4px 10px 0px rgba(0,0,0,0.3);
border-radius: 0px 5px 5px 0px!important;
}

.forget_view .tab_view {
 width:510px!important;
 margin:auto;
}





.forget_view button.el-button.get_btn {
 width: 400px;
height: 60px;
background: #0949F7;
border-radius: 5px 5px 5px 5px;
}

.forget_view button.el-button.get_btn span {
font-weight: 700;
font-size: 24px;
color: #FFFFFF;
line-height: 20px;
text-align: left;
font-style: normal;
text-transform: none;
}

.forget_view .el-input.list_inp {
 width:500px;
}

.forget_view .pageTitle {
 margin-top:10%!important;
}

.forget_view .el-select.list_sel {
 width:500px;
}
.circle2 {
display:none!important;
}

.forget_view .form {
border-radius:0px!important;
}

.forget_view .tab_view {
  background: #F8F8F8;
  border-radius: 10px;
  border: 0;
  box-shadow: none!important;
}

.forget_view .tab_view .tab {
  text-align: center;
  background: #F8F8F8; 
  font-weight: 400;
  font-size: 16px;
  color: #9E9E9E;
  box-shadow: none!important;
}


.forget_view .tab_view .tab.tab_active {
  background: url('http://clfile.zggen.cn/20250825/b067c3c76ca4443fb9a77e5ada7f74e1.png') no-repeat top center; 
  background-size: contain; 
  border-radius: 5px;
  font-weight: 400;
  font-size: 16px;
  color: #000000; 
}


.forget_view .tab_view .tab:not(.tab_active) {
  background: #F8F8F8; 
  color: #9E9E9E;  
}
.forget_view  .tab_view {
    margin-top:20px;
    width:500px!important;
    height:50px!important;
}

.forget_view .tab {
    width:180px;
    height:50px!important;
    line-height:50px!important;
}

.forget_view .list_item {
    
    width:450px!important;
    display:flex!important;
    flex-wrap:nowrap;
     align-items: center; 
    margin:40px auto;
    background: #F8F8F8;
border-radius: 30px 30px 30px 30px;
    padding:5px 10px;;
}

.forget_view .item_label {
      width:20%!important;
        text-align:left;
        color: #646464;
}

.forget_view .el-input.list_inp {
     width:80%!important;
    background:none!important;
}



.forget_view .el-select.list_sel {
       width:80%!important;
    
}

.forget_view .el-input__wrapper {
   background:none!important;
    box-shadow:none;
}

.forget_view .el-select__wrapper.el-tooltip__trigger.el-tooltip__trigger {
   background:none!important;
    box-shadow:none;
}

.forget_view  button.el-button.get_btn {
background: #FFD85F!important;
border-radius: 35px 35px 35px 35px!important;

}

.forget_view  button.el-button.get_btn span{
color:#000000!important;
}
.forget_view .back {
width:380px!important;
border-radius: 30px 30px 30px 30px;
border: 1px solid #BDB8A1;
height: 50px;
line-height:50px;
}

.forget_view button.el-button.valid_btn {
border-radius: 35px 35px 35px 35px!important;
width:400px;
height:60px;
    font-weight: 700;
    font-size: 24px;
color:#000000!important;
}

.forget_view button.el-button.update_btn {
border-radius: 35px 35px 35px 35px!important;
width:400px;
height:60px;
    font-weight: 700;
    font-size: 24px;
color:#000000!important;

}

</style>