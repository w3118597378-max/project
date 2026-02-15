<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="12">
						<el-form-item label="晋级赛事" prop="jinjisaishi">
							<el-input class="list_inp" v-model="form.jinjisaishi" placeholder="晋级赛事"
                                type="text"
								:readonly="!isAdd||disabledForm.jinjisaishi?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12" >
						<el-form-item label="主队球队" prop="zhuduiqiudui">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.zhuduiqiudui?true:false"
								v-model="form.zhuduiqiudui" 
								placeholder="请选择主队球队"
								>
								<el-option v-for="(item,index) in zhuduiqiuduiLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12" >
						<el-form-item label="客队球队" prop="keduiqiudui">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.keduiqiudui?true:false"
								v-model="form.keduiqiudui" 
								placeholder="请选择客队球队"
								>
								<el-option v-for="(item,index) in keduiqiuduiLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="主场球员" prop="zhuchangqiuyuan">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.zhuchangqiuyuan?true:false"
								v-model="form.zhuchangqiuyuans" 
								placeholder="请选择主场球员"
								multiple
								@change="jinjibisaijieguozhuchangqiuyuanChange">
								<el-option v-for="(item,index) in zhuchangqiuyuanLists" :label="item" :value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="客场球员" prop="kechangqiuyuan">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.kechangqiuyuan?true:false"
								v-model="form.kechangqiuyuans" 
								placeholder="请选择客场球员"
								multiple
								@change="jinjibisaijieguokechangqiuyuanChange">
								<el-option v-for="(item,index) in kechangqiuyuanLists" :label="item" :value="item">
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="最终比分" prop="zuizhongbifen">
							<el-input class="list_inp" v-model="form.zuizhongbifen" placeholder="最终比分"
                                type="text"
								:readonly="!isAdd||disabledForm.zuizhongbifen?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="争议情况" prop="zhengyiqingkuang">
							<el-input class="list_inp" v-model="form.zhengyiqingkuang" placeholder="争议情况"
                                type="text"
								:readonly="!isAdd||disabledForm.zhengyiqingkuang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="提交时间" prop="tijiaoshijian">
							<el-date-picker
								class="list_date"
								v-model="form.tijiaoshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.tijiaoshijian?true:false"
								placeholder="请选择提交时间" />
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
	const tableName = 'jinjibisaijieguo'
	const formName = '晋级比赛结果'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        jinjisaishi : false,
        zhuduiqiudui : false,
        keduiqiudui : false,
        zhuchangqiuyuan : false,
        kechangqiuyuan : false,
        zuizhongbifen : false,
        zhengyiqingkuang : false,
        tijiaoshijian : false,
        caipanzhanghao : false,
        caipanxingming : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	const rules = ref({
		jinjisaishi: [
		],
		zhuduiqiudui: [
		],
		keduiqiudui: [
		],
		zhuchangqiuyuan: [
		],
		kechangqiuyuan: [
		],
		zuizhongbifen: [
		],
		zhengyiqingkuang: [
		],
		tijiaoshijian: [
		],
		caipanzhanghao: [
		],
		caipanxingming: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//主队球队列表
	const zhuduiqiuduiLists = ref([])
	//客队球队列表
	const keduiqiuduiLists = ref([])
	//主场球员列表
	const zhuchangqiuyuanLists = ref([])
	//客场球员列表
	const kechangqiuyuanLists = ref([])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			jinjisaishi: '',
			zhuduiqiudui: '',
			keduiqiudui: '',
			zhuchangqiuyuan: '',
			kechangqiuyuan: '',
			zuizhongbifen: '',
			zhengyiqingkuang: '',
			tijiaoshijian: '',
			caipanzhanghao: '',
			caipanxingming: '',

		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			if(res.data.data.zhuchangqiuyuan){
				res.data.data.zhuchangqiuyuans = res.data.data.zhuchangqiuyuan.split(",")
			}else{
				res.data.data.zhuchangqiuyuans = []
			}
			if(res.data.data.kechangqiuyuan){
				res.data.data.kechangqiuyuans = res.data.data.kechangqiuyuan.split(",")
			}else{
				res.data.data.kechangqiuyuans = []
			}
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
			form.value.tijiaoshijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='jinjisaishi'){
					form.value.jinjisaishi = row[x];
					disabledForm.value.jinjisaishi = true;
					continue;
				}
				if(x=='zhuduiqiudui'){
					form.value.zhuduiqiudui = row[x];
					disabledForm.value.zhuduiqiudui = true;
					continue;
				}
				if(x=='keduiqiudui'){
					form.value.keduiqiudui = row[x];
					disabledForm.value.keduiqiudui = true;
					continue;
				}
				if(x=='zhuchangqiuyuan'){
					form.value.zhuchangqiuyuan = row[x];
					disabledForm.value.zhuchangqiuyuan = true;
					continue;
				}
				if(x=='kechangqiuyuan'){
					form.value.kechangqiuyuan = row[x];
					disabledForm.value.kechangqiuyuan = true;
					continue;
				}
				if(x=='zuizhongbifen'){
					form.value.zuizhongbifen = row[x];
					disabledForm.value.zuizhongbifen = true;
					continue;
				}
				if(x=='zhengyiqingkuang'){
					form.value.zhengyiqingkuang = row[x];
					disabledForm.value.zhengyiqingkuang = true;
					continue;
				}
				if(x=='tijiaoshijian'){
					form.value.tijiaoshijian = row[x];
					disabledForm.value.tijiaoshijian = true;
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
		context?.$http({
			url: `option/qiuduipaixingbang/qiuduimingcheng`,
			method: 'get',
		}).then(res=>{
			zhuduiqiuduiLists.value = res.data.data
		})
		context?.$http({
			url: `option/qiuduipaixingbang/qiuduimingcheng`,
			method: 'get',
		}).then(res=>{
			keduiqiuduiLists.value = res.data.data
		})
		context?.$http({
			url: `option/cansaixuesheng/xingming`,
			method: 'get',
		}).then(res=>{
			zhuchangqiuyuanLists.value = res.data.data
		})
		context?.$http({
			url: `option/cansaixuesheng/xingming`,
			method: 'get',
		}).then(res=>{
			kechangqiuyuanLists.value = res.data.data
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
	const jinjibisaijieguozhuchangqiuyuanChange= ()=> {
		form.value.zhuchangqiuyuan = form.value.zhuchangqiuyuans.join(',')
	}
	const jinjibisaijieguokechangqiuyuanChange= ()=> {
		form.value.kechangqiuyuan = form.value.kechangqiuyuans.join(',')
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
