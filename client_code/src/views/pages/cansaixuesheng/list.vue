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
					学号：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.xuehao" placeholder="学号"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_view">
				<div class="search_label">
					姓名：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.xingming" placeholder="姓名"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" v-if="btnAuth('cansaixuesheng','新增')" @click="addClick">新增</el-button>
			</div>
            <div class="chartBtn-row">
                <el-button class="chart_btn" type="warning" @click="echartClick1" v-if="btnAuth('cansaixuesheng','学生数量统计')">
                    学生数量统计
                </el-button>
            </div>
		</div>



                <div class="data_view">
<div class="item" v-for="(item,index) in list" @click.stop="detailClick(item.id)">
  
  <div class="content">
    <div class="avatar-container">
      <img v-if="item.touxiang" 
           :src="item.touxiang.substring(0,4)=='http' ? item.touxiang.split(',')[0] : (baseUrl + item.touxiang.split(',')[0])" 
           :alt="item.xingming" 
           class="student-avatar"
           @click.stop="preViewClick(item.touxiang.substring(0,4)=='http' ? item.touxiang.split(',')[0] : (baseUrl + item.touxiang.split(',')[0]))">
      <div v-else class="no-avatar">
        <i class="el-icon-user"></i>
      </div>
    </div>
    <div class="student-info">
      <h3 class="student-name">{{ item.xingming }}</h3>
      <p class="student-id">学号：{{ item.xuehao }}</p>
      <p class="student-gender">性别：{{ item.xingbie }}</p>
      <p class="student-age">年龄：{{ item.nianling }}</p>
    </div>
  </div>
</div>
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
    <!-- 统计图弹窗 -->
    <el-dialog v-model="echartVisible" modal-class="chart-dialog-modal" class="chart-dialog" title="统计图" width="70%">
        <div  class="chart-wrapper">
            <div class="chart" id="xingbieEchart1" style="width:100%;height:600px;"></div>
        </div>
        <template #footer>
            <span class="formModel_btn_box">
                <el-button class="cancel_btn" @click="echartVisible=false">关闭</el-button>
            </span>
        </template>
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
	const tableName = 'cansaixuesheng'
	const formName = '参赛学生'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: 10
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
		router.push('/index/cansaixueshengAdd')
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
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.xuehao&&searchQuery.value.xuehao!=''){
			params.xuehao = '%' + searchQuery.value.xuehao + '%'
		}
		if(searchQuery.value.xingming&&searchQuery.value.xingming!=''){
			params.xingming = '%' + searchQuery.value.xingming + '%'
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
	const detailClick = (id) => {
		router.push(`${tableName}Detail?id=` + id + (centerType.value?'&&centerType=1':''))
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
	//判断是否有统计图筛选权限
	const changeStatQuery = (arr)=>{
		if(!arr){
			return true
		}
		let role = localStorage.getItem('role')
		for(let x in arr){
			if(arr[x] == role) {
				return true
			}
		}
		return false
	}
	// 统计图1
	const echartVisible = ref(false)
	const echartClick1 = ()=>{
		if(!route.path.endsWith('Analysis')){
			echartVisible.value = true
		}
		nextTick(async ()=>{
			let dom = document.getElementById("xingbieEchart1")
			if(!dom)return
			var xingbieEchart1 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/group/xingbie?order=desc`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].xingbie);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].xingbie				    })
				}
				var option = {};
				option = {
    title:{
        text: '学生数量统计',
        left: 'center'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c} ({d}%)'
    },
    series: [
        {
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: dataList,
            emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
}
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    xingbieEchart1.resize();
				};
			})
		})
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
.condition-box {
    display: flex;
    gap: 10px;
    justify-content: center;
}
.condition-box>* {
    max-width: 300px;
}

.data_view {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
    padding: 20px 0;
}

.item {
    background: white;
    border-radius: 12px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    cursor: pointer;
    overflow: hidden;
    
    &:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
    }
}

.content {
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
}

.avatar-container {
    margin-bottom: 15px;
    position: relative;
}

.student-avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
    border: 3px solid #f0f0f0;
    transition: all 0.3s ease;
    
    &:hover {
        border-color: #ff6b35;
        transform: scale(1.05);
    }
}

.no-avatar {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    background: #f5f5f5;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 3px solid #e0e0e0;
    
    .el-icon-user {
        font-size: 32px;
        color: #999;
    }
}

.student-info {
    flex: 1;
    width: 100%;
}

.student-name {
    margin: 0 0 10px 0;
    font-size: 18px;
    font-weight: 600;
    color: #333;
    line-height: 1.4;
}

.student-id,
.student-gender,
.student-age {
    margin: 5px 0;
    font-size: 14px;
    color: #666;
    line-height: 1.4;
}

@media (max-width: 768px) {
    .data_view {
        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
        gap: 15px;
        padding: 15px 0;
    }
    
    .content {
        padding: 15px;
    }
    
    .student-avatar {
        width: 70px;
        height: 70px;
    }
    
    .no-avatar {
        width: 70px;
        height: 70px;
    }
}
</style>