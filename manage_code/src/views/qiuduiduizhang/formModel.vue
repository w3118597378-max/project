<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="12">
						<el-form-item label="队长账号" prop="duizhangzhanghao">
							<el-input class="list_inp" v-model="form.duizhangzhanghao" placeholder="队长账号"
								type="text" :readonly="!isAdd||disabledForm.duizhangzhanghao||form.id?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="队长姓名" prop="duizhangxingming">
							<el-input class="list_inp" v-model="form.duizhangxingming" placeholder="队长姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.duizhangxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="密码" prop="mima">
							<el-input class="list_inp" v-model="form.mima" placeholder="密码"
								type="password" :readonly="!isAdd||disabledForm.mima?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item prop="zhaopian"
									  label="照片"
						>
							<uploads
								:disabled="!isAdd||disabledForm.zhaopian?true:false"
								action="file/upload"
								tip="请上传照片"
								style="width: 100%;text-align: left;"
								:fileUrls="form.zhaopian?form.zhaopian:''" 
								@change="zhaopianUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12" >
						<el-form-item label="性别" prop="xingbie">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.xingbie?true:false"
								v-model="form.xingbie" 
								placeholder="请选择性别"
								>
								<el-option v-for="(item,index) in xingbieLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="电话" prop="dianhua">
							<el-input class="list_inp" v-model="form.dianhua" placeholder="电话"
                                type="text"
								:readonly="!isAdd||disabledForm.dianhua?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12" >
						<el-form-item label="球队名称" prop="qiuduimingcheng">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.qiuduimingcheng?true:false"
								v-model="form.qiuduimingcheng" 
								placeholder="请选择球队名称"
								>
								<el-option v-for="(item,index) in qiuduimingchengLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
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
	const tableName = 'qiuduiduizhang'
	const formName = '球队队长'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        duizhangzhanghao : false,
        duizhangxingming : false,
        mima : false,
        zhaopian : false,
        xingbie : false,
        dianhua : false,
        sfsh : false,
        shhf : false,
        qiuduimingcheng : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
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
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//照片上传回调
	const zhaopianUploadSuccess=(e)=>{
		form.value.zhaopian = e
	}
	//性别列表
	const xingbieLists = ref([])
	//球队名称列表
	const qiuduimingchengLists = ref([])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			duizhangzhanghao: '',
			duizhangxingming: '',
			mima: '',
			zhaopian: '',
			xingbie: '',
			dianhua: '',
			shhf: '',
			qiuduimingcheng: '',

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
				if(x=='duizhangzhanghao'){
					form.value.duizhangzhanghao = row[x];
					disabledForm.value.duizhangzhanghao = true;
					continue;
				}
				if(x=='duizhangxingming'){
					form.value.duizhangxingming = row[x];
					disabledForm.value.duizhangxingming = true;
					continue;
				}
				if(x=='mima'){
					form.value.mima = row[x];
					disabledForm.value.mima = true;
					continue;
				}
				if(x=='zhaopian'){
					form.value.zhaopian = row[x];
					disabledForm.value.zhaopian = true;
					continue;
				}
				if(x=='xingbie'){
					form.value.xingbie = row[x];
					disabledForm.value.xingbie = true;
					continue;
				}
				if(x=='dianhua'){
					form.value.dianhua = row[x];
					disabledForm.value.dianhua = true;
					continue;
				}
				if(x=='qiuduimingcheng'){
					form.value.qiuduimingcheng = row[x];
					disabledForm.value.qiuduimingcheng = true;
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
			if(context?.$toolUtil.storageGet("sessionTable")!="users" && !context?.$toolUtil.storageGet("isAdmin")) {
				disabledForm.value.qiuduimingcheng = true;
			}
		})
		xingbieLists.value = "男,女".split(',')
		context?.$http({
			url: `option/qiuduipaixingbang/qiuduimingcheng`,
			method: 'get',
		}).then(res=>{
			qiuduimingchengLists.value = res.data.data
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
		if(form.value.zhaopian!=null) {
			form.value.zhaopian = form.value.zhaopian.replace(new RegExp(context?.$config.url,"g"),"");
		}
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
