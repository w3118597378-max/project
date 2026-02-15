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
					<el-form-item label="晋级赛事" prop="jinjisaishi">
						<el-input class="list_inp"
                                  v-model="form.jinjisaishi"
                                  placeholder="晋级赛事"
                                  type="text"
							      :readonly="!isAdd||disabledForm.jinjisaishi?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="主队球队" prop="zhuduiqiudui">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.zhuduiqiudui?true:false"
							v-model="form.zhuduiqiudui" 
							placeholder="请选择主队球队"
							style="width:100%;"
							>
							<el-option v-for="(item,index) in zhuduiqiuduiLists" :label="item"
								:value="item"
								>
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="客队球队" prop="keduiqiudui">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.keduiqiudui?true:false"
							v-model="form.keduiqiudui" 
							placeholder="请选择客队球队"
							style="width:100%;"
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
							style="width:100%;"
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
							style="width:100%;"
							@change="jinjibisaijieguokechangqiuyuanChange">
							<el-option v-for="(item,index) in kechangqiuyuanLists" :label="item" :value="item">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="最终比分" prop="zuizhongbifen">
						<el-input class="list_inp"
                                  v-model="form.zuizhongbifen"
                                  placeholder="最终比分"
                                  type="text"
							      :readonly="!isAdd||disabledForm.zuizhongbifen?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="争议情况" prop="zhengyiqingkuang">
						<el-input class="list_inp"
                                  v-model="form.zhengyiqingkuang"
                                  placeholder="争议情况"
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
							style="width:100%;"
							:readonly="!isAdd||disabledForm.tijiaoshijian?true:false"
							placeholder="请选择提交时间" />
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
	const tableName = 'jinjibisaijieguo'
	const formName = '晋级比赛结果'
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
		jinjisaishi: '',
		zhuduiqiudui: '',
		keduiqiudui: '',
		zhuchangqiuyuan: [],
		kechangqiuyuan: [],
		zuizhongbifen: '',
		zhengyiqingkuang: '',
		tijiaoshijian: '',
		caipanzhanghao: '',
		caipanxingming: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
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
	const isAdd = ref(false)
	//表单验证
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
	//主队球队列表
	const zhuduiqiuduiLists = ref([])
	//客队球队列表
	const keduiqiuduiLists = ref([])
	//主场球员列表
	const zhuchangqiuyuanLists = ref([])
	//客场球员列表
	const kechangqiuyuanLists = ref([])
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
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
        form.value.tijiaoshijian = context?.$toolUtil.getCurDateTime()
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
                    if(form.value.zhuchangqiuyuan){
                        form.value.zhuchangqiuyuans = form.value.zhuchangqiuyuan.split(',')
                    }
					continue;
				}
				if(x=='kechangqiuyuan'){
					form.value.kechangqiuyuan = row[x];
					disabledForm.value.kechangqiuyuan = true;
                    if(form.value.kechangqiuyuan){
                        form.value.kechangqiuyuans = form.value.kechangqiuyuan.split(',')
                    }
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
	//取消
	const backClick = () => {
		history.back()
	}
	//多选框数组转字符串
	const jinjibisaijieguozhuchangqiuyuanChange= ()=> {
		form.value.zhuchangqiuyuan = form.value.zhuchangqiuyuans.join(',')
	}
	//多选框数组转字符串
	const jinjibisaijieguokechangqiuyuanChange= ()=> {
		form.value.kechangqiuyuan = form.value.kechangqiuyuans.join(',')
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

.edit_view .add_form .el-form-item .el-form-item__content .list_sel{
    line-height: 36px;
    
    box-sizing: border-box;
    width: calc(100% - 120px);
    padding: 0px 10px;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

.edit_view .list_date{
    line-height: 36px;
}








</style>