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
						<el-form-item label="比赛场地" prop="bisaichangdi">
							<el-input class="list_inp" v-model="form.bisaichangdi" placeholder="比赛场地"
                                type="text"
								:readonly="!isAdd||disabledForm.bisaichangdi?true:false" />
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
						<el-form-item label="首发阵容" prop="shoufazhenrong">
							<el-input class="list_inp" v-model="form.shoufazhenrong" placeholder="首发阵容"
                                type="text"
								:readonly="!isAdd||disabledForm.shoufazhenrong?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12" >
						<el-form-item label="对阵队伍" prop="duizhenduiwu">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.duizhenduiwu?true:false"
								v-model="form.duizhenduiwu" 
								placeholder="请选择对阵队伍"
								>
								<el-option v-for="(item,index) in duizhenduiwuLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="上场球员" prop="xingming">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.xingming?true:false"
								v-model="form.xingmings" 
								placeholder="请选择上场球员"
								multiple
								@change="jingsairichengxingmingChange">
								<el-option v-for="(item,index) in xingmingLists" :label="item" :value="item">
								</el-option>
							</el-select>
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

					<el-col :span="12">
						<el-form-item label="球队名称" prop="qiuduimingcheng">
							<el-input class="list_inp" v-model="form.qiuduimingcheng" placeholder="球队名称"
                                type="text"
								:readonly="!isAdd||disabledForm.qiuduimingcheng?true:false" />
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
	const tableName = 'jingsairicheng'
	const formName = '竞赛日程'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        saishimingcheng : false,
        bisaichangdi : false,
        bisaishijian : false,
        shoufazhenrong : false,
        duizhenduiwu : false,
        xingming : false,
        tijiaoshijian : false,
        duizhangzhanghao : false,
        duizhangxingming : false,
        qiuduimingcheng : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	const rules = ref({
		saishimingcheng: [
		],
		bisaichangdi: [
		],
		bisaishijian: [
		],
		shoufazhenrong: [
		],
		duizhenduiwu: [
		],
		xingming: [
		],
		tijiaoshijian: [
		],
		duizhangzhanghao: [
		],
		duizhangxingming: [
		],
		qiuduimingcheng: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//对阵队伍列表
	const duizhenduiwuLists = ref([])
	//上场球员列表
	const xingmingLists = ref([])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			saishimingcheng: '',
			bisaichangdi: '',
			bisaishijian: '',
			shoufazhenrong: '',
			duizhenduiwu: '',
			xingming: '',
			tijiaoshijian: '',
			duizhangzhanghao: '',
			duizhangxingming: '',
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
			if(res.data.data.xingming){
				res.data.data.xingmings = res.data.data.xingming.split(",")
			}else{
				res.data.data.xingmings = []
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
				if(x=='shoufazhenrong'){
					form.value.shoufazhenrong = row[x];
					disabledForm.value.shoufazhenrong = true;
					continue;
				}
				if(x=='duizhenduiwu'){
					form.value.duizhenduiwu = row[x];
					disabledForm.value.duizhenduiwu = true;
					continue;
				}
				if(x=='xingming'){
					form.value.xingming = row[x];
					disabledForm.value.xingming = true;
					continue;
				}
				if(x=='tijiaoshijian'){
					form.value.tijiaoshijian = row[x];
					disabledForm.value.tijiaoshijian = true;
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
			if(json.hasOwnProperty('duizhangzhanghao')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.duizhangzhanghao = json.duizhangzhanghao
				disabledForm.value.duizhangzhanghao = true;
			}
			if(json.hasOwnProperty('duizhangxingming')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.duizhangxingming = json.duizhangxingming
				disabledForm.value.duizhangxingming = true;
			}
			if(json.hasOwnProperty('qiuduimingcheng')&& context?.$toolUtil.storageGet("sessionTable")!="users"){
				form.value.qiuduimingcheng = json.qiuduimingcheng
				disabledForm.value.qiuduimingcheng = true;
			}
		})
		context?.$http({
			url: `option/qiuduipaixingbang/qiuduimingcheng`,
			method: 'get',
		}).then(res=>{
			duizhenduiwuLists.value = res.data.data
		})
		context?.$http({
			url: `option/cansaixuesheng/xingming`,
			method: 'get',
		}).then(res=>{
			xingmingLists.value = res.data.data
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
	const jingsairichengxingmingChange= ()=> {
		form.value.xingming = form.value.xingmings.join(',')
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
