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
					<el-form-item label="首发阵容" prop="shoufazhenrong">
						<el-input class="list_inp"
                                  v-model="form.shoufazhenrong"
                                  placeholder="首发阵容"
                                  type="text"
							      :readonly="!isAdd||disabledForm.shoufazhenrong?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="对阵队伍" prop="duizhenduiwu">
						<el-select
							class="list_sel"
							:disabled="!isAdd||disabledForm.duizhenduiwu?true:false"
							v-model="form.duizhenduiwu" 
							placeholder="请选择对阵队伍"
							style="width:100%;"
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
							style="width:100%;"
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
							style="width:100%;"
							:readonly="!isAdd||disabledForm.tijiaoshijian?true:false"
							placeholder="请选择提交时间" />
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="队长账号" prop="duizhangzhanghao">
						<el-input class="list_inp"
                                  v-model="form.duizhangzhanghao"
                                  placeholder="队长账号"
                                  type="text"
							      :readonly="!isAdd||disabledForm.duizhangzhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="队长姓名" prop="duizhangxingming">
						<el-input class="list_inp"
                                  v-model="form.duizhangxingming"
                                  placeholder="队长姓名"
                                  type="text"
							      :readonly="!isAdd||disabledForm.duizhangxingming?true:false" />
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
	const tableName = 'jingsairicheng'
	const formName = '竞赛日程'
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
		shoufazhenrong: '',
		duizhenduiwu: '',
		xingming: [],
		tijiaoshijian: '',
		duizhangzhanghao: '',
		duizhangxingming: '',
		qiuduimingcheng: '',
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
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
	const isAdd = ref(false)
	//表单验证
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
	//对阵队伍列表
	const duizhenduiwuLists = ref([])
	//上场球员列表
	const xingmingLists = ref([])
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
                    if(form.value.xingming){
                        form.value.xingmings = form.value.xingming.split(',')
                    }
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
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('duizhangzhanghao') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.duizhangzhanghao = json.duizhangzhanghao
				disabledForm.value.duizhangzhanghao = true;
			}
			if(json.hasOwnProperty('duizhangxingming') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.duizhangxingming = json.duizhangxingming
				disabledForm.value.duizhangxingming = true;
			}
			if(json.hasOwnProperty('qiuduimingcheng') && context?.$toolUtil.storageGet("frontSessionTable")!="users"){
				form.value.qiuduimingcheng = json.qiuduimingcheng
				disabledForm.value.qiuduimingcheng = true;
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
	//取消
	const backClick = () => {
		history.back()
	}
	//多选框数组转字符串
	const jingsairichengxingmingChange= ()=> {
		form.value.xingming = form.value.xingmings.join(',')
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