<template>
    <div class="min-h-screen bg-slate-50">
        <!-- 顶部橙色强调条 -->
        <div class="h-1 bg-gradient-to-r from-orange-500 to-orange-400"></div>
        
        <!-- 主容器 -->
        <div class="max-w-7xl mx-auto px-6 py-10">
            
            <!-- 搜索区域卡片 -->
            <div class="list_search bg-white border border-slate-200 rounded-lg shadow-sm p-6 mb-6">
                <div class="search_content">
                    <div class="search_form">
                        <div class="search_view">
                            <div class="search_label">
                                <i class="el-icon-search"></i>
                                <span class="label-text text-slate-700">赛事名称</span>
                            </div>
                            <div class="search_box">
                                <el-input 
                                    class="search_inp" 
                                    v-model="searchQuery.saishimingcheng" 
                                    placeholder="请输入赛事名称进行搜索..." 
                                    clearable
                                    prefix-icon="Search">
                                </el-input>
                            </div>
                        </div>
                        <div class="search_btn_view">
                            <el-button class="search_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" @click="searchClick" type="primary">
                                <i class="el-icon-search"></i>
                                搜索
                            </el-button>
                            <el-button class="add_btn rounded-lg shadow-sm hover:shadow-md transition-all duration-200" v-if="btnAuth('bisaichengji','新增')" @click="addClick">
                                <i class="el-icon-plus"></i>
                                新增记录
                            </el-button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="bg-white border border-slate-200 rounded-lg shadow-sm p-2 mb-6">
                <div class="grid grid-cols-2 gap-2">
                    <button
                        type="button"
                        @click="goToTab('record')"
                        class="h-12 rounded-lg flex items-center justify-center gap-2 font-semibold transition-all duration-200"
                        :class="isRecordTab ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20' : 'bg-white text-slate-700 hover:bg-slate-50'"
                    >
                        <i class="el-icon-trophy"></i>
                        比赛记录
                    </button>
                    <button
                        type="button"
                        @click="goToTab('stats')"
                        class="h-12 rounded-lg flex items-center justify-center gap-2 font-semibold transition-all duration-200"
                        :class="isStatsTab ? 'bg-orange-500 text-white shadow-md shadow-orange-500/20' : 'bg-white text-slate-700 hover:bg-slate-50'"
                    >
                        <i class="el-icon-data-line"></i>
                        比赛技术统计
                    </button>
                </div>
            </div>

            <!-- 统计卡片 -->
            <div class="stats-cards grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">
                <div class="stat-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 hover:shadow-md transition-all duration-200">
                    <div class="stat-label text-sm text-slate-500 mb-2">总比赛场次</div>
                    <div class="stat-number text-2xl font-bold text-slate-900">{{ total }}</div>
                    <div class="stat-decoration h-1 bg-gradient-to-r from-orange-500 to-orange-400 mt-3 rounded-full shadow-orange-500/20"></div>
                </div>
                <div class="stat-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 hover:shadow-md transition-all duration-200">
                    <div class="stat-label text-sm text-slate-500 mb-2">胜率</div>
                    <div class="stat-number text-2xl font-bold text-slate-900">{{ getWinRate() }}%</div>
                    <div class="stat-decoration h-1 bg-gradient-to-r from-orange-500 to-orange-400 mt-3 rounded-full shadow-orange-500/20"></div>
                </div>
                <div class="stat-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 hover:shadow-md transition-all duration-200">
                    <div class="stat-label text-sm text-slate-500 mb-2">平均得分</div>
                    <div class="stat-number text-2xl font-bold text-slate-900">{{ getAverageScore() }}</div>
                    <div class="stat-decoration h-1 bg-gradient-to-r from-orange-500 to-orange-400 mt-3 rounded-full shadow-orange-500/20"></div>
                </div>
                <div class="stat-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 hover:shadow-md transition-all duration-200">
                    <div class="stat-label text-sm text-slate-500 mb-2">最高排名</div>
                    <div class="stat-number text-2xl font-bold text-slate-900">{{ getBestRanking() }}</div>
                    <div class="stat-decoration h-1 bg-gradient-to-r from-orange-500 to-orange-400 mt-3 rounded-full shadow-orange-500/20"></div>
                </div>
            </div>

            <!-- 比赛成绩卡片布局 -->
            <div class="cards-container grid gap-4">
                <div v-for="(item, index) in list" :key="item.id" @click="tableDetailClick(item)"
                     class="match-card bg-white border border-slate-200 rounded-lg shadow-sm p-6 
                            hover:shadow-md hover:border-orange-300 transition-all duration-200 cursor-pointer">
                    
                    <div class="flex items-center gap-4">
                        <!-- 序号 -->
                        <div class="match-number bg-slate-100 text-slate-600 w-10 h-10 rounded-xl 
                                    flex items-center justify-center font-semibold text-sm shadow-sm">
                            {{ (listQuery.page-1)*listQuery.limit+index + 1 }}
                        </div>
                        
                        <!-- 赛事信息 -->
                        <div class="match-info flex-1">
                            <div class="space-y-1">
                                <div class="font-semibold text-slate-900">{{ item.saishimingcheng }}</div>
                                <div class="text-sm text-slate-700">
                                    <span class="inline-block mr-4">{{ item.bisaichangdi }}</span>
                                    <span>{{ item.bisaishijian }}</span>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 比分显示 -->
                        <div class="score-display text-center">
                            <div class="text-sm text-slate-500 mb-1">{{ item.qiuduimingcheng }}</div>
                            <div class="text-3xl font-bold text-orange-600">{{ item.chengjibifen || '--' }}</div>
                            <div class="text-xs text-slate-400 mt-1">比分</div>
                        </div>
                        
                        <!-- 状态标签 -->
                        <div class="status-badge">
                            <span class="px-3 py-1.5 text-sm font-medium rounded-full border shadow-sm"
                                  :class="getStatusBadgeClass(item.bisaijieguo)">
                                {{ item.bisaijieguo }}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        
        <!-- 分页 -->
        <div class="pagination-container">
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
        
        <!-- 弹窗组件 -->
        <el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
            <div style="text-align:center">
                <img :src="preViewUrl" style="max-width: 100%;" alt="">
            </div>
        </el-dialog>
    </div>
</div>
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

    const isRecordTab = computed(() => route.path.includes('bisaichengjiList'))
    const isStatsTab = computed(() => route.path.includes('bisaijishutongjiList'))
    const goToTab = (tab) => {
        if (tab === 'record') {
            router.push('/index/bisaichengjiList')
        } else {
            router.push('/index/bisaijishutongjiList')
        }
    }
    //基础信息
    const tableName = 'bisaichengji'
    const formName = '比赛成绩'
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
        router.push('/index/bisaichengjiAdd')
    }
    //判断是否从个人中心跳转
    const centerType = ref(false)
    //返回
    const backClick = () => {
        router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
    }
    //获取状态样式类
    const getStatusClass = (status) => {
        switch(status) {
            case '胜':
                return 'match-status win'
            case '负':
                return 'match-status lose'
            case '平':
                return 'match-status draw'
            default:
                return 'match-status'
        }
    }
    //获取状态徽章样式类
    const getStatusBadgeClass = (status) => {
        switch(status) {
            case '胜':
                return 'bg-green-50 text-green-700 border-green-200'
            case '负':
                return 'bg-red-50 text-red-700 border-red-200'
            case '平':
                return 'bg-gray-50 text-gray-700 border-gray-200'
            default:
                return 'bg-gray-50 text-gray-700 border-gray-200'
        }
    }
    //统计计算函数
    const getWinRate = () => {
        if (!list.value.length) return 0
        const wins = list.value.filter(item => item.bisaijieguo === '胜').length
        return Math.round((wins / list.value.length) * 100)
    }
    const getAverageScore = () => {
        if (!list.value.length) return '0'
        const scores = list.value.filter(item => item.chengjibifen).map(item => parseFloat(item.chengjibifen))
        if (!scores.length) return '0'
        return (scores.reduce((a, b) => a + b, 0) / scores.length).toFixed(1)
    }
    const getBestRanking = () => {
        if (!list.value.length) return '--'
        const rankings = list.value.filter(item => item.bisaipaiming).map(item => parseInt(item.bisaipaiming))
        if (!rankings.length) return '--'
        return Math.min(...rankings)
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
        if(searchQuery.value.saishimingcheng&&searchQuery.value.saishimingcheng!=''){
            params.saishimingcheng = '%' + searchQuery.value.saishimingcheng + '%'
        }
        if(searchQuery.value.qiuduimingcheng&&searchQuery.value.qiuduimingcheng!=''){
            params.qiuduimingcheng = '%' + searchQuery.value.qiuduimingcheng + '%'
        }
        params['sort'] = 'qiuduijifen';
        params['order'] = 'desc';
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
        getList()
    }
    init()
</script>
<style lang="scss" scoped>
// 统计卡片样式
.stats-cards {
    .stat-card {
        transition: all 0.2s ease-in-out;
        
        &:hover {
            transform: translateY(-2px);
        }
        
        .stat-number {
            line-height: 1.2;
        }
        
        .stat-decoration {
            transition: all 0.3s ease-in-out;
        }
        
        &:hover .stat-decoration {
            transform: scaleX(1.05);
        }
    }
}

// 卡片容器样式
.cards-container {
    margin-top: 1rem;
}

.match-card {
    transition: all 0.2s ease-in-out;
    
    &:hover {
        transform: translateY(-2px);
    }
    
    .match-number {
        flex-shrink: 0;
    }
    
    .match-info {
        min-width: 0; // 允许文本截断
    }
    
    .score-display {
        flex-shrink: 0;
        min-width: 120px;
        
        .text-3xl {
            line-height: 1.2;
        }
    }
    
    .status-badge {
        flex-shrink: 0;
    }
}

// 按钮统一样式
.el-button {
    border-radius: 0.5rem !important;
    transition: all 0.2s ease-in-out !important;
    
    &:hover {
        transform: translateY(-1px);
    }
    
    &:focus {
        box-shadow: 0 0 0 3px rgba(251, 146, 60, 0.2) !important;
        outline: none !important;
    }
}

// 输入框样式
.el-input {
    .el-input__inner {
        border-radius: 0.5rem !important;
        transition: all 0.2s ease-in-out !important;
        
        &:focus {
            box-shadow: 0 0 0 3px rgba(251, 146, 60, 0.2) !important;
            border-color: rgb(251, 146, 60) !important;
        }
    }
}

// 分页样式
.pagination-container {
    margin-top: 2rem;
    display: flex;
    justify-content: center;
    
    .el-pagination {
        .btn-prev,
        .btn-next,
        .el-pager li {
            border-radius: 0.5rem !important;
            transition: all 0.2s ease-in-out !important;
        }
        
        .el-pager li.active {
            background: rgb(251, 146, 60) !important;
            color: white !important;
        }
    }
}

// 响应式设计
@media (max-width: 768px) {
    .stats-cards {
        grid-template-columns: 1fr 1fr;
    }
    
    .match-card {
        padding: 1rem;
        
        .flex {
            flex-direction: column;
            align-items: flex-start;
            gap: 0.75rem;
        }
        
        .score-display {
            align-self: center;
        }
        
        .status-badge {
            align-self: flex-end;
        }
    }
}

@media (max-width: 480px) {
    .stats-cards {
        grid-template-columns: 1fr;
    }
}
</style>
