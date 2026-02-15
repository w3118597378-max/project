<template>
    <div class="list-page">
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view" v-if="centerType">
                <el-button class="back_btn" @click="backClick">返回</el-button>
            </div>
        </div>
		<div class="list_search">
			<div class="search_view">
				<div class="search_label">
					晋级赛事：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.jinjisaishi" placeholder="晋级赛事"
						clearable>
					</el-input>
				</div>
			</div>
            <div class="search_view">
                <div class="search_label">
                    主队球队：
                </div>
                <div class="search_box">
                    <el-select
                        class="search_sel"
                        clearable
                        v-model="searchQuery.zhuduiqiudui"
                        placeholder="主队球队"
                    >
                        <el-option v-for="item in zhuduiqiuduiLists" :label="item" :value="item"></el-option>
                    </el-select>
                </div>
            </div>
            <div class="search_view">
                <div class="search_label">
                    客队球队：
                </div>
                <div class="search_box">
                    <el-select
                        class="search_sel"
                        clearable
                        v-model="searchQuery.keduiqiudui"
                        placeholder="客队球队"
                    >
                        <el-option v-for="item in keduiqiuduiLists" :label="item" :value="item"></el-option>
                    </el-select>
                </div>
            </div>
			<div class="search_btn_view">
				<el-button class="search_btn" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" v-if="btnAuth('jinjibisaijieguo','新增')" @click="addClick">新增</el-button>
			</div>
		</div>



                <div class="table_view">
					<el-table v-loading="listLoading" class="data_table" :data="list" border :row-style="{'cursor':'pointer'}"
						@row-click="tableDetailClick" :stripe='false'>
						<el-table-column :resizable='true' align="center" header-align="center" type="selection" width="55" />
						<el-table-column label="序号" width="120" :resizable='true' align="center" header-align="center">
							<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
						</el-table-column>
						<el-table-column label="晋级赛事" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.jinjisaishi}}
							</template>
						</el-table-column>
						<el-table-column label="主队球队" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.zhuduiqiudui}}
							</template>
						</el-table-column>
						<el-table-column label="客队球队" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.keduiqiudui}}
							</template>
						</el-table-column>
						<el-table-column label="主场球员" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.zhuchangqiuyuan}}
							</template>
						</el-table-column>
						<el-table-column label="客场球员" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.kechangqiuyuan}}
							</template>
						</el-table-column>
						<el-table-column label="最终比分" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.zuizhongbifen}}
							</template>
						</el-table-column>
						<el-table-column label="争议情况" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.zhengyiqingkuang}}
							</template>
						</el-table-column>
						<el-table-column label="提交时间" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.tijiaoshijian}}
							</template>
						</el-table-column>
						<el-table-column label="裁判账号" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.caipanzhanghao}}
							</template>
						</el-table-column>
						<el-table-column label="裁判姓名" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.caipanxingming}}
							</template>
						</el-table-column>
					</el-table>
                </div>

				<el-pagination
					background
					:layout="layouts.join(',')"
					:total="total"
					:page-size="listQuery.limit"
                    v-model:current-page="listQuery.page"
					prev-text="上一页"
					next-text="下一页"
					:hide-on-single-page="false"
					@size-change="sizeChange"
					@current-change="currentChange"/>


    </div>

    <el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
        <div style="text-align:center">
            <img :src="preViewUrl" style="max-width: 100%;" alt="">
        </div>
    </el-dialog>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
        computed,
        inject,
	} from 'vue';
    const moment = window.moment
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
    const baseUrl = ref(context.$config.url)
	//基础信息
	const tableName = 'jinjibisaijieguo'
	const formName = '晋级比赛结果'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: 20,
	})
	const total = ref(0)
	const listLoading = ref(false)
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	const addClick = () => {
		router.push('/index/jinjibisaijieguoAdd')
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	//搜索
	const searchQuery = ref({})
	//下拉列表
	const zhuduiqiuduiLists = ref([])
	const getzhuduiqiuduiLists = () => {
		context?.$http({
			url: 'option/qiuduipaixingbang/qiuduimingcheng',
			method:'get',
		}).then(res=>{
			zhuduiqiuduiLists.value = res.data.data
		})
	}
	const keduiqiuduiLists = ref([])
	const getkeduiqiuduiLists = () => {
		context?.$http({
			url: 'option/qiuduipaixingbang/qiuduimingcheng',
			method:'get',
		}).then(res=>{
			keduiqiuduiLists.value = res.data.data
		})
	}
	const searchClick = async() => {
		listQuery.value.page = 1
		getList()
	}
	//分页
	const layouts = ref(["total","prev","pager","next","sizes"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.jinjisaishi&&searchQuery.value.jinjisaishi!=''){
			params.jinjisaishi = '%' + searchQuery.value.jinjisaishi + '%'
		}
		if(searchQuery.value.zhuduiqiudui&&searchQuery.value.zhuduiqiudui!=''){
			params.zhuduiqiudui = searchQuery.value.zhuduiqiudui
		}
		if(searchQuery.value.keduiqiudui&&searchQuery.value.keduiqiudui!=''){
			params.keduiqiudui = searchQuery.value.keduiqiudui
		}
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	const tableDetailClick = (row) => {
		router.push(`${tableName}Detail?id=` + row.id + (centerType.value?'&&centerType=1':''))
	}
	//下载文件
	const download = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		const a = document.createElement('a');
		a.style.display = 'none';
		a.setAttribute('target', '_blank');
		file && a.setAttribute('download', file);
		a.href = context?.$config.url + file;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	}
	// 查看大图
	const preViewUrl = ref('')
	const preViewVisible = ref(false)
	const preViewClick = (url) =>{
		preViewUrl.value = url
		preViewVisible.value = true
	}
	const init = async() => {
		if(route.query.centerType){
			centerType.value = true
		}
        if(context.$toolUtil.storageGet('frontToken') && !user.value.id){
            await store.dispatch("user/getSession")
        }
        getzhuduiqiuduiLists()
        getkeduiqiuduiLists()
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
</style>