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
					球队名称：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.qiuduimingcheng" placeholder="球队名称"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_view">
				<div class="search_label">
					队长姓名：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.duizhangxingming" placeholder="队长姓名"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" v-if="btnAuth('qiuduipaixingbang','新增')" @click="addClick">新增</el-button>
			</div>
		</div>

        <div class="sort-wrapper">
            <el-button class="item storeup" @click="sortClick('storeupNumber')" :class="{active:sortType=='storeupNumber'}">
                <el-icon class="icon" v-if="sortType!='storeupNumber'"><DCaret /></el-icon>
                <el-icon class="icon desc" v-else-if="sortOrder=='desc'"><SortDown /></el-icon>
                <el-icon class="icon asc" v-else><SortUp /></el-icon>
                收藏数
            </el-button>
        </div>


                <div class="table_view">
					<el-table v-loading="listLoading" class="data_table" :data="list" border :row-style="{'cursor':'pointer'}"
						@row-click="tableDetailClick" :stripe='false'>
						<el-table-column :resizable='true' align="center" header-align="center" type="selection" width="55" />
						<el-table-column label="序号" width="120" :resizable='true' align="center" header-align="center">
							<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
						</el-table-column>
						<el-table-column label="教练名称" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.jiaolianmingcheng}}
							</template>
						</el-table-column>
						<el-table-column label="球队名称" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuduimingcheng}}
							</template>
						</el-table-column>
						<el-table-column label="球队logo" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								<div v-if="scope.row.qiuduilogo">
									<el-image v-if="scope.row.qiuduilogo.substring(0,4)=='http'" preview-teleported
										:preview-src-list="[scope.row.qiuduilogo.split(',')[0]]"
										:src="scope.row.qiuduilogo.split(',')[0]" style="width:100px;height:100px" @click.stop></el-image>
									<el-image v-else preview-teleported
										:preview-src-list="[baseUrl+scope.row.qiuduilogo.split(',')[0]]"
										:src="baseUrl+scope.row.qiuduilogo.split(',')[0]" style="width:100px;height:100px" @click.stop>
									</el-image>
								</div>
								<div v-else>无图片</div>
							</template>
						</el-table-column>
						<el-table-column label="球衣" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								<div v-if="scope.row.qiuyi">
									<el-image v-if="scope.row.qiuyi.substring(0,4)=='http'" preview-teleported
										:preview-src-list="[scope.row.qiuyi.split(',')[0]]"
										:src="scope.row.qiuyi.split(',')[0]" style="width:100px;height:100px" @click.stop></el-image>
									<el-image v-else preview-teleported
										:preview-src-list="[baseUrl+scope.row.qiuyi.split(',')[0]]"
										:src="baseUrl+scope.row.qiuyi.split(',')[0]" style="width:100px;height:100px" @click.stop>
									</el-image>
								</div>
								<div v-else>无图片</div>
							</template>
						</el-table-column>
						<el-table-column label="球员人数" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuyuanrenshu}}
							</template>
						</el-table-column>
						<el-table-column label="积分" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.jifen}}
							</template>
						</el-table-column>
						<el-table-column label="胜" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.sheng}}
							</template>
						</el-table-column>
						<el-table-column label="负" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.fu}}
							</template>
						</el-table-column>
						<el-table-column label="平" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.ping}}
							</template>
						</el-table-column>
						<el-table-column label="胜率" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.shenglv}}
							</template>
						</el-table-column>
						<el-table-column label="是否在招募" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.shifouzaizhaomu}}
							</template>
						</el-table-column>
						<el-table-column label="创立时间" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.chuanglishijian}}
							</template>
						</el-table-column>
						<el-table-column label="球队口号" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuduikouhao}}
							</template>
						</el-table-column>
						<el-table-column label="球员名单" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.qiuyuanmingdan}}
							</template>
						</el-table-column>
						<el-table-column label="队长账号" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.duizhangzhanghao}}
							</template>
						</el-table-column>
						<el-table-column label="队长姓名" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.duizhangxingming}}
							</template>
						</el-table-column>
						<el-table-column label="评论数" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.discussNumber}}
							</template>
						</el-table-column>
						<el-table-column label="审核回复" v-if="centerType" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								{{scope.row.shhf}}
							</template>
						</el-table-column>
						<el-table-column label="审核状态" v-if="centerType" :resizable='true' align="center" header-align="center">
							<template #default="scope">
								<el-tag type="success" v-if="scope.row.sfsh=='是'">通过</el-tag>
								<el-tag type="danger" v-if="scope.row.sfsh=='否'">未通过</el-tag>
								<el-tag type="warning" v-if="scope.row.sfsh=='待审核'">待审核</el-tag>
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
	const tableName = 'qiuduipaixingbang'
	const formName = '球队排行榜'
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
		router.push('/index/qiuduipaixingbangAdd')
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
    const sortType = ref('')
    const sortOrder = ref('')
    const sortClick = (type)=>{
        if(sortType.value==type && sortOrder.value=='asc'){
            sortType.value = ''
            sortOrder.value = ''
        }else if(sortType.value==type && sortOrder.value=='desc'){
            sortOrder.value = 'asc'
        }else{
            sortType.value = type
            sortOrder.value = 'desc'
        }
        getList()
    }
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.qiuduimingcheng&&searchQuery.value.qiuduimingcheng!=''){
			params.qiuduimingcheng = '%' + searchQuery.value.qiuduimingcheng + '%'
		}
        params['sort'] = 'jifen';
        params['order'] = 'desc';
		if(searchQuery.value.duizhangxingming&&searchQuery.value.duizhangxingming!=''){
			params.duizhangxingming = '%' + searchQuery.value.duizhangxingming + '%'
		}
		if(!centerType.value){
			params['sfsh'] = '是';
		} 
        if(sortType.value){
            params['sort'] = sortType.value
            params['order'] = sortOrder.value
        }
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
            list.value.forEach(item=>{
                if(item.qiuduilogo!=null){
                    item.imgUrls = item.qiuduilogo.split(',').map(url=> {
                        if(url && url.substr(0,4)=='http'){
                            return url
                        }else{
                            return baseUrl.value + url
                        }
                    })
                }else{
                    item.imgUrls = []
                }
            })
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
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
</style>