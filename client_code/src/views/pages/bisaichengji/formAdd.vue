<template>
	<div class="edit_view">
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
			<el-row>
				<el-col :span="12">
					<el-form-item label="赛事名称" prop="saishimingcheng">
						<el-input class="list_inp"
                                  v-model="form.saishimingcheng"
                                  placeholder="赛事名称"
                                  type="text"
							      :readonly="!isAdd||disabledForm.saishimingcheng?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="比赛场地" prop="bisaichangdi">
						<el-input class="list_inp"
                                  v-model="form.bisaichangdi"
                                  placeholder="比赛场地"
                                  type="text"
							      :readonly="!isAdd||disabledForm.bisaichangdi?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="比赛时间" prop="bisaishijian">
						<el-input class="list_inp"
                                  v-model="form.bisaishijian"
                                  placeholder="比赛时间"
                                  type="text"
							      :readonly="!isAdd||disabledForm.bisaishijian?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="球队名称" prop="qiuduimingcheng">
						<el-input class="list_inp"
                                  v-model="form.qiuduimingcheng"
                                  placeholder="球队名称"
                                  type="text"
							      :readonly="!isAdd||disabledForm.qiuduimingcheng?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="球队积分" prop="qiuduijifen">
						<el-input class="list_inp"
                                  v-model.number="form.qiuduijifen"
                                  placeholder="球队积分"
                                  type="text"
							      :readonly="!isAdd||disabledForm.qiuduijifen?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="比赛排名" prop="bisaipaiming">
						<el-input class="list_inp"
                                  v-model.number="form.bisaipaiming"
                                  placeholder="比赛排名"
                                  type="text"
							      :readonly="!isAdd||disabledForm.bisaipaiming?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="成绩比分" prop="chengjibifen">
						<el-input class="list_inp"
                                  v-model="form.chengjibifen"
                                  placeholder="成绩比分"
                                  type="text"
							      :readonly="!isAdd||disabledForm.chengjibifen?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="比赛结果" prop="bisaijieguo">
						<el-input class="list_inp"
                                  v-model="form.bisaijieguo"
                                  placeholder="比赛结果"
                                  type="text"
							      :readonly="!isAdd||disabledForm.bisaijieguo?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="特殊事件" prop="teshushijian">
						<el-input class="list_inp"
                                  v-model="form.teshushijian"
                                  placeholder="特殊事件"
                                  type="text"
							      :readonly="!isAdd||disabledForm.teshushijian?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="判罚说明" prop="panfashuoming">
						<el-input class="list_inp"
                                  v-model="form.panfashuoming"
                                  placeholder="判罚说明"
                                  type="text"
							      :readonly="!isAdd||disabledForm.panfashuoming?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="发布时间" prop="fabushijian">
						<el-date-picker
							class="list_date"
							v-model="form.fabushijian"
							format="YYYY 年 MM 月 DD 日"
							value-format="YYYY-MM-DD"
							type="datetime"
							:readonly="!isAdd||disabledForm.fabushijian?true:false"
							placeholder="请选择发布时间"
							style="width: 100%;" />
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="裁判账号" prop="caipanzhanghao">
						<el-input class="list_inp"
                                  v-model="form.caipanzhanghao"
                                  placeholder="裁判账号"
                                  type="text"
							      :readonly="!isAdd||disabledForm.caipanzhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="裁判姓名" prop="caipanxingming">
						<el-input class="list_inp"
                                  v-model="form.caipanxingming"
                                  placeholder="裁判姓名"
                                  type="text"
							      :readonly="!isAdd||disabledForm.caipanxingming?true:false" />
					</el-form-item>
				</el-col>

			</el-row>
			<div class="formModel_btn_box">
				<el-button class="formModel_cancel" @click="backClick">取消</el-button>
				<el-button class="formModel_confirm"
                           @click="save"
                           type="success"
				>
					保存
				</el-button>
			</div>
		</el-form>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
    const moment = window.moment
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'bisaichengji'
	const formName = '比赛成绩'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//form表单
	const form = ref({
		saishimingcheng: '',
		bisaichangdi: '',
		bisaishijian: '',
		qiuduimingcheng: '',
		qiuduijifen: 0,
		bisaipaiming: 0,
		chengjibifen: '',
		bisaijieguo: '',
		teshushijian: '',
		panfashuoming: '',
		fabushijian: '',
		caipanzhanghao: '',
		caipanxingming: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	const disabledForm = ref({
		saishimingcheng : false,
		bisaichangdi : false,
		bisaishijian : false,
		qiuduimingcheng : false,
		qiuduijifen : false,
		bisaipaiming : false,
		chengjibifen : false,
		bisaijieguo : false,
		teshushijian : false,
		panfashuoming : false,
		fabushijian : false,
		caipanzhanghao : false,
		caipanxingming : false,
	})
	const isAdd = ref(false)
	//表单验证
	const rules = ref({
		saishimingcheng: [
		],
		bisaichangdi: [
		],
		bisaishijian: [
		],
		qiuduimingcheng: [
		],
		qiuduijifen: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		bisaipaiming: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		chengjibifen: [
		],
		bisaijieguo: [
		],
		teshushijian: [
		],
		panfashuoming: [
		],
		fabushijian: [
		],
		caipanzhanghao: [
		],
		caipanxingming: [
		],
	})
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
        form.value.fabushijian = context?.$toolUtil.getCurDate()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			// getInfo()
			for(let x in row){
				if(x=='saishimingcheng'){
					form.value.saishimingcheng = row[x];
					disabledForm.value.saishimingcheng = true;
					continue;
				}
				if(x=='bisaichangdi'){
					form.value.bisaichangdi = row[x];
					disabledForm.value.bisaichangdi = true;
					continue;
				}
				if(x=='bisaishijian'){
					form.value.bisaishijian = row[x];
					disabledForm.value.bisaishijian = true;
					continue;
				}
				if(x=='qiuduimingcheng'){
					form.value.qiuduimingcheng = row[x];
					disabledForm.value.qiuduimingcheng = true;
					continue;
				}
				if(x=='qiuduijifen'){
					form.value.qiuduijifen = row[x];
					disabledForm.value.qiuduijifen = true;
					continue;
				}
				if(x=='bisaipaiming'){
					form.value.bisaipaiming = row[x];
					disabledForm.value.bisaipaiming = true;
					continue;
				}
				if(x=='chengjibifen'){
					form.value.chengjibifen = row[x];
					disabledForm.value.chengjibifen = true;
					continue;
				}
				if(x=='bisaijieguo'){
					form.value.bisaijieguo = row[x];
					disabledForm.value.bisaijieguo = true;
					continue;
				}
				if(x=='teshushijian'){
					form.value.teshushijian = row[x];
					disabledForm.value.teshushijian = true;
					continue;
				}
				if(x=='panfashuoming'){
					form.value.panfashuoming = row[x];
					disabledForm.value.panfashuoming = true;
					continue;
				}
				if(x=='fabushijian'){
					form.value.fabushijian = row[x];
					disabledForm.value.fabushijian = true;
					continue;
				}
				if(x=='caipanzhanghao'){
					form.value.caipanzhanghao = row[x];
					disabledForm.value.caipanzhanghao = true;
					continue;
				}
				if(x=='caipanxingming'){
					form.value.caipanxingming = row[x];
					disabledForm.value.caipanxingming = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('caipanzhanghao') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.caipanzhanghao = json.caipanzhanghao
				disabledForm.value.caipanzhanghao = true;
			}
			if(json.hasOwnProperty('caipanxingming') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.caipanxingming = json.caipanxingming
				disabledForm.value.caipanxingming = true;
			}
            if (localStorage.getItem('autoSave')) {
                localStorage.removeItem('autoSave')
                save()
            }
		})
	}
	//初始化
	//取消
	const backClick = () => {
		history.back()
	}
	//提交
	const save=()=>{
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		formRef.value.validate(async (valid)=>{
			if(valid){
                if(type.value == 'cross'){
                    if(crossColumnName.value!=''){
                        if(!crossColumnName.value.startsWith('[')){
                            for(let o in objcross){
                                if(o == crossColumnName.value){
                                    objcross[o] = crossColumnValue.value
                                }
                            }
                            //修改跨表数据
                            await changeCrossData(objcross)
                        }else{
                            crossUserId = context?.$toolUtil.storageGet('userid')
                            crossRefId = objcross['id']
                            crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
                        }
                    }
                }
				if(crossUserId&&crossRefId){    //限制用户操作次数
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(async (res)=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async (res)=>{
                                context?.$toolUtil.message(`操作成功`,'success')
                                history.back()
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
                        context?.$toolUtil.message(`操作成功`,'success')
                        history.back()
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData=(row,key)=>{
        if(type.value == 'cross'){
            let data = row
            if(key){	//如果有指定key，则只更新key属性
                data = {
                    id:row.id,
                }
                data[key] = row[key]
            }
            context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: data
            }).then(res=>{})
        }
	}
	onMounted(()=>{
		type.value = route.query.type?route.query.type:'add'
		let row = null
		let table = null
		let statusColumnName = null
		let tips = null
		let statusColumnValue = null
		if(type.value == 'cross'){
			row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
			table = context?.$toolUtil.storageGet('crossTable')
			statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
			tips = context?.$toolUtil.storageGet('crossTips')
			statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
		}
		init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
	})
    onUnmounted(()=>{
        Object.keys(localStorage).map(item=>{
            if(item.startsWith('cross')){
                localStorage.removeItem(item)
            }
        })
    })
</script>
<style lang="scss" scoped>
</style>
<style lang="scss">
.edit_view {
    width: 100%;
    padding: 0 7%;
    margin: 0 auto;
    position: relative;
    font-size:16px;
    color:#666;
    background: #FFFFFF;
}
.edit_view .add_form{
    width: 100%;
    margin: 30px auto;
  background: #FFF7E8;
border-radius: 20px 20px 20px 20px;
    padding: 50px 100px;
border: 0px solid #A8A8A8;
}
.edit_view .add_form .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: #585858;
    font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.edit_view .list_inp .el-input__wrapper{
    height: 36px;
}


.edit_view .list_date{
    line-height: 36px;
}








</style>