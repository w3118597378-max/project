<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="12">
						<el-form-item label="赛事名称" prop="saishimingcheng">
							<el-input class="list_inp" v-model="form.saishimingcheng" placeholder="赛事名称"
                                type="text"
								:readonly="!isAdd||disabledForm.saishimingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="比赛时间" prop="bisaishijian">
							<el-input class="list_inp" v-model="form.bisaishijian" placeholder="比赛时间"
                                type="text"
								:readonly="!isAdd||disabledForm.bisaishijian?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="比赛场地" prop="bisaichangdi">
							<el-input class="list_inp" v-model="form.bisaichangdi" placeholder="比赛场地"
                                type="text"
								:readonly="!isAdd||disabledForm.bisaichangdi?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="球队名称" prop="qiuduimingcheng">
							<el-input class="list_inp" v-model="form.qiuduimingcheng" placeholder="球队名称"
                                type="text"
								:readonly="!isAdd||disabledForm.qiuduimingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="得分" prop="defen">
							<el-input class="list_inp" v-model.number="form.defen" placeholder="得分"
                                type="text"
								:readonly="!isAdd||disabledForm.defen?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="犯规" prop="fangui">
							<el-input class="list_inp" v-model.number="form.fangui" placeholder="犯规"
                                type="text"
								:readonly="!isAdd||disabledForm.fangui?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="暂停" prop="zanting">
							<el-input class="list_inp" v-model.number="form.zanting" placeholder="暂停"
                                type="text"
								:readonly="!isAdd||disabledForm.zanting?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="记录时间" prop="jilushijian">
							<el-date-picker
								class="list_date"
								v-model="form.jilushijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.jilushijian?true:false"
								placeholder="请选择记录时间" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="裁判账号" prop="caipanzhanghao">
							<el-input class="list_inp" v-model="form.caipanzhanghao" placeholder="裁判账号"
                                type="text"
								:readonly="!isAdd||disabledForm.caipanzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="裁判姓名" prop="caipanxingming">
							<el-input class="list_inp" v-model="form.caipanxingming" placeholder="裁判姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.caipanxingming?true:false" />
						</el-form-item>
					</el-col>


				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
	const moment = window.moment
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
    const isAdmin = localStorage.getItem('isAdmin')||context.$toolUtil.storageGet("sessionTable")=='users'
	//基础信息
	const tableName = 'bisaijishutongji'
	const formName = '比赛技术统计'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        saishimingcheng : false,
        bisaishijian : false,
        bisaichangdi : false,
        qiuduimingcheng : false,
        defen : false,
        fangui : false,
        zanting : false,
        jilushijian : false,
        caipanzhanghao : false,
        caipanxingming : false,
        crossuserid : false,
        crossrefid : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	const rules = ref({
		saishimingcheng: [
		],
		bisaishijian: [
		],
		bisaichangdi: [
		],
		qiuduimingcheng: [
		],
		defen: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		fangui: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		zanting: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		jilushijian: [
		],
		caipanzhanghao: [
		],
		caipanxingming: [
		],
		crossuserid: [
		],
		crossrefid: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			saishimingcheng: '',
			bisaishijian: '',
			bisaichangdi: '',
			qiuduimingcheng: '',
			defen: '',
			fangui: '',
			zanting: '',
			jilushijian: '',
			caipanzhanghao: '',
			caipanxingming: '',
			crossuserid: '',
			crossrefid: '',

		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
			form.value.jilushijian = context?.$toolUtil.getCurDateTime()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
			// getInfo()
			for(let x in row){
				if(x=='saishimingcheng'){
					form.value.saishimingcheng = row[x];
					disabledForm.value.saishimingcheng = true;
					continue;
				}
				if(x=='bisaishijian'){
					form.value.bisaishijian = row[x];
					disabledForm.value.bisaishijian = true;
					continue;
				}
				if(x=='bisaichangdi'){
					form.value.bisaichangdi = row[x];
					disabledForm.value.bisaichangdi = true;
					continue;
				}
				if(x=='qiuduimingcheng'){
					form.value.qiuduimingcheng = row[x];
					disabledForm.value.qiuduimingcheng = true;
					continue;
				}
				if(x=='defen'){
					form.value.defen = row[x];
					disabledForm.value.defen = true;
					continue;
				}
				if(x=='fangui'){
					form.value.fangui = row[x];
					disabledForm.value.fangui = true;
					continue;
				}
				if(x=='zanting'){
					form.value.zanting = row[x];
					disabledForm.value.zanting = true;
					continue;
				}
				if(x=='jilushijian'){
					form.value.jilushijian = row[x];
					disabledForm.value.jilushijian = true;
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
				if(x=='crossuserid'){
					form.value.crossuserid = row[x];
					disabledForm.value.crossuserid = true;
					continue;
				}
				if(x=='crossrefid'){
					form.value.crossrefid = row[x];
					disabledForm.value.crossrefid = true;
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
			formVisible.value = true
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('caipanzhanghao')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.caipanzhanghao = json.caipanzhanghao
				disabledForm.value.caipanzhanghao = true;
			}
			if(json.hasOwnProperty('caipanxingming')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.caipanxingming = json.caipanxingming
				disabledForm.value.caipanxingming = true;
			}
		})
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
							crossUserId = user.value.id
							crossRefId = objcross['id']
							crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
						}
					}
				}
				if(crossUserId&&crossRefId){
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
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row,key)=>{
        if(type.value == 'cross'){
			let data = row
			if(key){	//如果有指定key，则只更新key属性
				data = {
					id:row.id,
				}
				data[key] = row[key]
			}
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: data
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
</style>
