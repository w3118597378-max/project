<template>
    <div class="min-h-screen bg-slate-50">
        <!-- 顶部橙色强调条 -->
        <div class="h-1 bg-gradient-to-r from-orange-500 to-orange-400"></div>
        
        <!-- 主容器 -->
        <div class="max-w-7xl mx-auto px-6 py-10">
            <!-- 搜索区域卡片 -->
            <div class="list_search">
                <div class="search_content">
                    <div class="search_form">
                        <div class="search_view">
                            <div class="search_label">
                                <i class="el-icon-search"></i>
                                <span class="label-text">赛事名称</span>
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
                            <el-button class="search_btn" @click="searchClick" type="primary">
                                <i class="el-icon-search"></i>
                                搜索
                            </el-button>
                            <el-button class="add_btn" v-if="btnAuth('bisaijishutongji','新增')" @click="addClick">
                                <i class="el-icon-plus"></i>
                                新增记录
                            </el-button>
                        </div>
                    </div>
                    <div class="chartBtn-row">
                        <el-button class="chart_btn" @click="echartClick1" v-if="btnAuth('bisaijishutongji','比赛技术统计')" type="warning">
                            <i class="el-icon-data-line"></i>
                            数据统计
                        </el-button>
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
                        比赛成绩
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

            <!-- 表格区域卡片 -->
            <div class="table-view">
                <el-table v-loading="listLoading" class="data_table" :data="list" border :row-style="{'cursor':'pointer'}"
                    @row-click="tableDetailClick" :stripe='false' :row-class-name="tableRowClassName">
                    <el-table-column :resizable='true' align="center" header-align="center" type="selection" width="55" />
                    <el-table-column label="序号" width="120" :resizable='true' align="center" header-align="center">
                        <template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
                    </el-table-column>
                    <el-table-column label="赛事名称" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.saishimingcheng}}
                        </template>
                    </el-table-column>
                    <el-table-column label="比赛时间" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.bisaishijian}}
                        </template>
                    </el-table-column>
                    <el-table-column label="比赛场地" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.bisaichangdi}}
                        </template>
                    </el-table-column>
                    <el-table-column label="球队名称" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            <div class="team-name-with-dot">
                                <span class="team-dot"></span>
                                {{scope.row.qiuduimingcheng}}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column label="得分" :resizable='true' align="center" header-align="center" class-name="score-column">
                        <template #default="scope">
                            <span>{{scope.row.defen}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="犯规" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.fangui}}
                        </template>
                    </el-table-column>
                    <el-table-column label="暂停" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.zanting}}
                        </template>
                    </el-table-column>
                    <el-table-column label="记录时间" :resizable='true' align="center" header-align="center">
                        <template #default="scope">
                            {{scope.row.jilushijian}}
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

            <!-- 分页区域卡片 -->
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
        </div>

        <!-- 弹窗组件 -->
        <el-dialog v-model="preViewVisible" :title="'查看大图'" width="40%" destroy-on-close>
            <div style="text-align:center">
                <img :src="preViewUrl" style="max-width: 100%;" alt="">
            </div>
        </el-dialog>
        
        <!-- 统计图弹窗 -->
        <el-dialog v-model="echartVisible" modal-class="chart-dialog-modal" class="chart-dialog" title="统计图" width="70%">
            <div class="chart-wrapper">
                <div class="chart" id="qiuduimingchengEchart1" style="width:100%;height:600px;"></div>
            </div>
            <template #footer>
                <span class="formModel_btn_box">
                    <el-button class="cancel_btn" @click="echartVisible=false">关闭</el-button>
                </span>
            </template>
        </el-dialog>
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
	const tableName = 'bisaijishutongji'
	const formName = '比赛技术统计'
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
		router.push('/index/bisaijishutongjiAdd')
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
		if(searchQuery.value.saishimingcheng&&searchQuery.value.saishimingcheng!=''){
			params.saishimingcheng = '%' + searchQuery.value.saishimingcheng + '%'
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
			let dom = document.getElementById("qiuduimingchengEchart1")
			if(!dom)return
			var qiuduimingchengEchart1 = echarts.init(dom, null);
			let params = {}
			context.$http({
				url: `${tableName}/valueMul/qiuduimingcheng?yColumnNameMul=defen,fangui,zanting`,
				method: 'get',
				params
			}).then(res=>{
				let obj = res.data.data
                let xAxis1 = [];
                let yAxis1 = [];
                let pArray1 = []
                for(let i=0;i<obj[0].length;i++){
                    xAxis1.push(obj[0][i].qiuduimingcheng);
                    yAxis1.push(parseFloat((obj[0][i].total)));
                    pArray1.push({
                        value: parseFloat((obj[0][i].total)),
                        name: obj[0][i].qiuduimingcheng
                    })
                }
                let xAxis2 = [];
                let yAxis2 = [];
                let pArray2 = []
                for(let i=0;i<obj[1].length;i++){
                    xAxis2.push(obj[1][i].qiuduimingcheng);
                    yAxis2.push(parseFloat((obj[1][i].total)));
                    pArray2.push({
                        value: parseFloat((obj[1][i].total)),
                        name: obj[1][i].qiuduimingcheng
                    })
                }
                let xAxis3 = [];
                let yAxis3 = [];
                let pArray3 = []
                for(let i=0;i<obj[2].length;i++){
                    xAxis3.push(obj[2][i].qiuduimingcheng);
                    yAxis3.push(parseFloat((obj[2][i].total)));
                    pArray3.push({
                        value: parseFloat((obj[2][i].total)),
                        name: obj[2][i].qiuduimingcheng
                    })
                }
				var option = {};
				let series = [
					{
						data: yAxis1,
						type: 'line',
						name: '得分',
					},
					{
						data: yAxis2,
						type: 'line',
						name: '犯规数',
					},
					{
						data: yAxis3,
						type: 'line',
						name: '暂停数',
					},
				]
				let legend_data = ['得分','犯规数','暂停数',]
				option = {
    title: {
        text: '比赛技术统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    legend: {
        data: legend_data,
        left: 'center',
        bottom: 4,
    },
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        data: xAxis1,
    },
    yAxis: {
        type: 'value'
    },
    series: series,
}
                // 使用刚指定的配置项和数据显示图表。
                qiuduimingchengEchart1.setOption(option);
                  //根据窗口的大小变动图表
                window.onresize = function() {
                    qiuduimingchengEchart1.resize();
                };
			})
		})
	}
	// 表格行样式处理
	const tableRowClassName = ({row, rowIndex}) => {
		return 'table-row-hover'
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
/* 容器与背景 */
.min-h-screen {
    background-color: #f8fafc; /* slate-50 */
}

 /* 搜索区域美化 */
.list_search {
    background: #ffffff;
    padding: 18px 20px;
    border-radius: 12px;
    border: 1px solid #e2e8f0;
    margin-bottom: 24px;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);

    .search_content {
        display: flex;
        align-items: center;
        justify-content: flex-end;
        gap: 14px;
        flex-wrap: wrap;
    }

    .search_form {
        display: flex;
        align-items: center;
        justify-content: flex-end;
        gap: 14px;
        flex-wrap: wrap;
    }

    .search_view {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 10px;
        
        .search_label {
            display: flex;
            align-items: center;
            gap: 6px;
            color: #475569;
            font-size: 14px;
            font-weight: 500;
            padding: 6px 10px;
            background: #fff7ed;
            border: 1px solid #ffedd5;
            border-radius: 999px;
            
            i {
                color: #f97316;
            }
        }
    }

    .search_box {
        width: 320px;
    }

    /* 按钮样式优化 */
    .search_btn_view {
        display: flex;
        gap: 12px;
        
        .search_btn {
            background-color: #f97316 !important;
            border-color: #f97316 !important;
            border-radius: 8px;
            height: 40px;
            padding: 0 20px;
            font-weight: 500;
            transition: all 0.2s;
            
            &:hover {
                background-color: #ea580c !important;
                transform: translateY(-1px);
                box-shadow: 0 4px 6px -1px rgba(249, 115, 22, 0.2);
            }
        }
        
        .add_btn {
            background-color: #ffffff !important;
            color: #f97316 !important;
            border: 1.5px solid #f97316 !important;
            border-radius: 8px;
            height: 40px;
            padding: 0 20px;
            font-weight: 600;
            transition: all 0.2s;
            
            &:hover {
                background-color: #fff7ed !important;
                transform: translateY(-1px);
            }
        }
    }

    .chartBtn-row {
        margin-top: 16px;
        .chart_btn {
            border-radius: 8px;
            background-color: #fef3c7 !important;
            border-color: #fcd34d !important;
            color: #92400e !important;
            font-weight: 500;
            
            &:hover {
                background-color: #fde68a !important;
            }
        }
    }
}

 /* 表格区域美化 */
.table-view {
    background: #ffffff;
    border-radius: 12px;
    border: 1px solid #e2e8f0;
    overflow: hidden;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);

    :deep(.el-table) {
        --el-table-border-color: #f1f5f9;
        --el-table-header-bg-color: #f8fafc;
        font-size: 14px;
        color: #334155;
        font-variant-numeric: tabular-nums;

        &.el-table--border {
            border: 0;
        }

        .el-table__inner-wrapper::before {
            background-color: transparent;
        }

        th.el-table__cell {
            padding: 12px 0;
            font-weight: 600;
            color: #64748b;
            font-size: 12px;
            letter-spacing: 0.02em;
            border-bottom: 1px solid #e2e8f0;
        }

        td.el-table__cell {
            padding: 12px 0;
            border-bottom: 1px solid #f1f5f9;
        }

        /* 让内容更像参考图：左右留白更明显 */
        .cell {
            padding-left: 14px;
            padding-right: 14px;
        }

        /* 弱化纵向分隔，贴近参考图的“轻表格” */
        .el-table__cell {
            border-right: 0 !important;
        }

        /* 斑马纹：极淡暖色 */
        .el-table__body tbody tr:nth-child(even) {
            background-color: #fffaf5;
        }

        .el-table__row {
            transition: background-color 0.18s ease, box-shadow 0.18s ease;
            &:hover {
                background-color: #fff2e6 !important;
            }
        }

        /* 复选框更精致 */
        .el-checkbox__inner {
            border-radius: 6px;
        }
        .el-checkbox__input.is-checked .el-checkbox__inner {
            background-color: #f97316;
            border-color: #f97316;
        }
    }
}

/* 核心：得分 Badge 样式（更醒目，参考图风格） */
:deep(.score-column) {
    .cell {
        display: flex;
        justify-content: center;

        span {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-width: 40px;
            height: 26px;
            padding: 0 12px;
            background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
            color: #ffffff;
            border: none;
            border-radius: 13px;
            font-weight: 700;
            font-size: 15px;
            letter-spacing: 0.02em;
            box-shadow: 0 2px 4px rgba(249, 115, 22, 0.25);
        }
    }
}

/* 数值列（犯规/暂停）轻量灰胶囊 */
:deep(.el-table) {
    .el-table__body tbody tr td:nth-child(8) .cell,
    .el-table__body tbody tr td:nth-child(9) .cell {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        min-width: 28px;
        height: 22px;
        padding: 0 8px;
        background-color: #f1f5f9;
        color: #64748b;
        border: 1px solid #e2e8f0;
        border-radius: 11px;
        font-weight: 500;
        font-size: 13px;
    }
}

/* 时间/场地/记录时间更弱化 */
:deep(.el-table) {
    .el-table__body tbody tr td:nth-child(4) .cell,
    .el-table__body tbody tr td:nth-child(5) .cell,
    .el-table__body tbody tr td:nth-child(10) .cell {
        color: #94a3b8;
        font-size: 12px;
    }
}

/* 赛事名称列更突出 */
:deep(.el-table) {
    .el-table__body tbody tr td:nth-child(3) .cell {
        font-weight: 600;
        color: #1e293b;
        font-size: 13px;
        line-height: 1.4;
    }
}

/* 球队名称列 */
:deep(.el-table) {
    .el-table__body tbody tr td:nth-child(6) .cell {
        font-weight: 500;
        color: #334155;
    }
}

/* 球队名称点缀优化 */
.team-name-with-dot {
    display: flex;
    align-items: center;
    gap: 6px;
    justify-content: center;
    
    .team-dot {
        width: 6px;
        height: 6px;
        background-color: #f97316;
        border-radius: 50%;
        flex-shrink: 0;
    }
}

/* 分页美化 */
.pagination-container {
    margin-top: 32px;
    display: flex;
    justify-content: center;

    :deep(.el-pagination.is-background) {
        --el-pagination-border-radius: 8px;

        .el-pager li {
            border-radius: 8px;
        }
        .el-pager li:not(.is-active):hover {
            color: #f97316;
        }
        .el-pager li.is-active {
            background-color: #f97316 !important;
        }
        .btn-prev,
        .btn-next {
            border-radius: 8px;
            &:hover {
                color: #f97316;
            }
        }
    }
}

/* 输入框聚焦效果 */
:deep(.el-input__wrapper) {
    border-radius: 8px;
    transition: all 0.2s;
    &.is-focus {
        box-shadow: 0 0 0 1px #f97316 inset !important;
    }
}

/* 弹窗样式 */
:deep(.el-dialog) {
    border-radius: 16px;
    overflow: hidden;
    .el-dialog__header {
        margin-right: 0;
        padding: 20px 24px;
        background-color: #f8fafc;
        border-bottom: 1px solid #e2e8f0;
    }
    .el-dialog__title {
        font-weight: 600;
        color: #1e293b;
    }
}
</style>