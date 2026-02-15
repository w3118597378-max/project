<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="12">
						<el-form-item label="教练名称" prop="jiaolianmingcheng">
							<el-input class="list_inp" v-model="form.jiaolianmingcheng" placeholder="教练名称"
                                type="text"
								:readonly="!isAdd||disabledForm.jiaolianmingcheng?true:false" />
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
						<el-form-item prop="qiuduilogo"
									  label="球队logo"
						>
							<uploads
								:disabled="!isAdd||disabledForm.qiuduilogo?true:false"
								action="file/upload"
								tip="请上传球队logo"
								style="width: 100%;text-align: left;"
								:fileUrls="form.qiuduilogo?form.qiuduilogo:''" 
								@change="qiuduilogoUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item prop="qiuyi"
									  label="球衣"
						>
							<uploads
								:disabled="!isAdd||disabledForm.qiuyi?true:false"
								action="file/upload"
								tip="请上传球衣"
								style="width: 100%;text-align: left;"
								:fileUrls="form.qiuyi?form.qiuyi:''" 
								@change="qiuyiUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="球员人数" prop="qiuyuanrenshu">
							<el-input class="list_inp" v-model.number="form.qiuyuanrenshu" placeholder="球员人数"
                                type="text"
								:readonly="!isAdd||disabledForm.qiuyuanrenshu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="积分" prop="jifen">
							<el-input class="list_inp" v-model.number="form.jifen" placeholder="积分"
                                type="text"
								:readonly="!isAdd||disabledForm.jifen?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="胜" prop="sheng">
							<el-input class="list_inp" v-model.number="form.sheng" placeholder="胜"
                                type="text"
								:readonly="!isAdd||disabledForm.sheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="负" prop="fu">
							<el-input class="list_inp" v-model.number="form.fu" placeholder="负"
                                type="text"
								:readonly="!isAdd||disabledForm.fu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="平" prop="ping">
							<el-input class="list_inp" v-model.number="form.ping" placeholder="平"
                                type="text"
								:readonly="!isAdd||disabledForm.ping?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="胜率" prop="shenglv">
							<el-input class="list_inp" v-model="form.shenglv" placeholder="胜率"
                                type="text"
								:readonly="!isAdd||disabledForm.shenglv?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12" >
						<el-form-item label="是否在招募" prop="shifouzaizhaomu">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.shifouzaizhaomu?true:false"
								v-model="form.shifouzaizhaomu" 
								placeholder="请选择是否在招募"
								>
								<el-option v-for="(item,index) in shifouzaizhaomuLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="创立时间" prop="chuanglishijian">
							<el-input class="list_inp" v-model="form.chuanglishijian" placeholder="创立时间"
                                type="text"
								:readonly="!isAdd||disabledForm.chuanglishijian?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="球队口号" prop="qiuduikouhao">
							<el-input class="list_inp" v-model="form.qiuduikouhao" placeholder="球队口号"
                                type="text"
								:readonly="!isAdd||disabledForm.qiuduikouhao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="球员名单" prop="qiuyuanmingdan">
							<el-input class="list_inp" v-model="form.qiuyuanmingdan" placeholder="球员名单"
                                type="text"
								:readonly="!isAdd||disabledForm.qiuyuanmingdan?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="队长账号" prop="duizhangzhanghao">
							<el-input class="list_inp" v-model="form.duizhangzhanghao" placeholder="队长账号"
                                type="text"
								:readonly="!isAdd||disabledForm.duizhangzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="队长姓名" prop="duizhangxingming">
							<el-input class="list_inp" v-model="form.duizhangxingming" placeholder="队长姓名"
                                type="text"
								:readonly="!isAdd||disabledForm.duizhangxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="球队介绍" prop="qiuduijieshao">
							<editor :value="form.qiuduijieshao" placeholder="请输入球队介绍"
							   	:readonly="!isAdd||disabledForm.qiuduijieshao?true:false" :key="!isAdd"
								class="list_editor" @change="(e)=>editorChange(e,'qiuduijieshao')"></editor>
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
	const tableName = 'qiuduipaixingbang'
	const formName = '球队排行榜'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        jiaolianmingcheng : false,
        qiuduimingcheng : false,
        qiuduilogo : false,
        qiuyi : false,
        qiuyuanrenshu : false,
        jifen : false,
        sheng : false,
        fu : false,
        ping : false,
        shenglv : false,
        shifouzaizhaomu : false,
        chuanglishijian : false,
        qiuduikouhao : false,
        qiuyuanmingdan : false,
        qiuduijieshao : false,
        duizhangzhanghao : false,
        duizhangxingming : false,
        storeupNumber : false,
        discussNumber : false,
        sfsh : false,
        shhf : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	const rules = ref({
		jiaolianmingcheng: [
		],
		qiuduimingcheng: [
		],
		qiuduilogo: [
		],
		qiuyi: [
		],
		qiuyuanrenshu: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		jifen: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		sheng: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		fu: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		ping: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		shenglv: [
		],
		shifouzaizhaomu: [
		],
		chuanglishijian: [
		],
		qiuduikouhao: [
		],
		qiuyuanmingdan: [
		],
		qiuduijieshao: [
		],
		duizhangzhanghao: [
		],
		duizhangxingming: [
		],
		storeupNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		discussNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		sfsh: [
		],
		shhf: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//球队logo上传回调
	const qiuduilogoUploadSuccess=(e)=>{
		form.value.qiuduilogo = e
	}
	//球衣上传回调
	const qiuyiUploadSuccess=(e)=>{
		form.value.qiuyi = e
	}
	//是否在招募列表
	const shifouzaizhaomuLists = ref([])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			jiaolianmingcheng: '',
			qiuduimingcheng: '',
			qiuduilogo: '',
			qiuyi: '',
			qiuyuanrenshu: '',
			jifen: '',
			sheng: '',
			fu: '',
			ping: '',
			shenglv: '',
			shifouzaizhaomu: '',
			chuanglishijian: '',
			qiuduikouhao: '',
			qiuyuanmingdan: '',
			qiuduijieshao: '',
			duizhangzhanghao: '',
			duizhangxingming: '',
			storeupNumber: '0',
			shhf: '',

		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.qiuduijieshao = res.data.data.qiuduijieshao?(res.data.data.qiuduijieshao.replace(reg,'../../../cl583636164/file')):'';
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
				if(x=='jiaolianmingcheng'){
					form.value.jiaolianmingcheng = row[x];
					disabledForm.value.jiaolianmingcheng = true;
					continue;
				}
				if(x=='qiuduimingcheng'){
					form.value.qiuduimingcheng = row[x];
					disabledForm.value.qiuduimingcheng = true;
					continue;
				}
				if(x=='qiuduilogo'){
					form.value.qiuduilogo = row[x];
					disabledForm.value.qiuduilogo = true;
					continue;
				}
				if(x=='qiuyi'){
					form.value.qiuyi = row[x];
					disabledForm.value.qiuyi = true;
					continue;
				}
				if(x=='qiuyuanrenshu'){
					form.value.qiuyuanrenshu = row[x];
					disabledForm.value.qiuyuanrenshu = true;
					continue;
				}
				if(x=='jifen'){
					form.value.jifen = row[x];
					disabledForm.value.jifen = true;
					continue;
				}
				if(x=='sheng'){
					form.value.sheng = row[x];
					disabledForm.value.sheng = true;
					continue;
				}
				if(x=='fu'){
					form.value.fu = row[x];
					disabledForm.value.fu = true;
					continue;
				}
				if(x=='ping'){
					form.value.ping = row[x];
					disabledForm.value.ping = true;
					continue;
				}
				if(x=='shenglv'){
					form.value.shenglv = row[x];
					disabledForm.value.shenglv = true;
					continue;
				}
				if(x=='shifouzaizhaomu'){
					form.value.shifouzaizhaomu = row[x];
					disabledForm.value.shifouzaizhaomu = true;
					continue;
				}
				if(x=='chuanglishijian'){
					form.value.chuanglishijian = row[x];
					disabledForm.value.chuanglishijian = true;
					continue;
				}
				if(x=='qiuduikouhao'){
					form.value.qiuduikouhao = row[x];
					disabledForm.value.qiuduikouhao = true;
					continue;
				}
				if(x=='qiuyuanmingdan'){
					form.value.qiuyuanmingdan = row[x];
					disabledForm.value.qiuyuanmingdan = true;
					continue;
				}
				if(x=='qiuduijieshao'){
					form.value.qiuduijieshao = row[x];
					disabledForm.value.qiuduijieshao = true;
					continue;
				}
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
				if(x=='storeupNumber'){
					form.value.storeupNumber = row[x];
					disabledForm.value.storeupNumber = true;
					continue;
				}
				if(x=='discussNumber'){
					form.value.discussNumber = row[x];
					disabledForm.value.discussNumber = true;
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
			form.value.storeupNumber='0'
			formVisible.value = true
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('duizhangzhanghao')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.duizhangzhanghao = json.duizhangzhanghao
				disabledForm.value.duizhangzhanghao = true;
			}
			if(json.hasOwnProperty('duizhangxingming')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.duizhangxingming = json.duizhangxingming
				disabledForm.value.duizhangxingming = true;
			}
		})
		shifouzaizhaomuLists.value = "正在招募,停止招募".split(',')
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
		if(form.value.qiuduilogo!=null) {
			form.value.qiuduilogo = form.value.qiuduilogo.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(form.value.qiuyi!=null) {
			form.value.qiuyi = form.value.qiuyi.replace(new RegExp(context?.$config.url,"g"),"");
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
