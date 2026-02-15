<template>
	<div class="home_view">

		<div class="countList">
			<el-collapse-transition v-if="btnAuth('cansaixuesheng','首页总数')">
				<el-card v-show="countTypeList.closecansaixueshengCountType" class="count-item">
					<template #header>
						<div class="card_head_title">
							参赛学生
						</div>
						<div class="card_head_right">
							<el-icon @click="countTypeClick('hiddencansaixueshengCountType')" class="showIcons"
								:class="countTypeList.hiddencansaixueshengCountType?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="countTypeClick('closecansaixueshengCountType')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddencansaixueshengCountType">
							<div class="count_title"><span>参赛学生总数</span></div>
							<div class="count_num"><countTo :startVal="0" :endVal="cansaixueshengCount" :duration="1000"></countTo></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('bisaijishutongji','首页总数')">
				<el-card v-show="countTypeList.closebisaijishutongjiCountType" class="count-item">
					<template #header>
						<div class="card_head_title">
							比赛技术统计
						</div>
						<div class="card_head_right">
							<el-icon @click="countTypeClick('hiddenbisaijishutongjiCountType')" class="showIcons"
								:class="countTypeList.hiddenbisaijishutongjiCountType?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="countTypeClick('closebisaijishutongjiCountType')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenbisaijishutongjiCountType">
							<div class="count_title"><span>比赛技术统计总数</span></div>
							<div class="count_num"><countTo :startVal="0" :endVal="bisaijishutongjiCount" :duration="1000"></countTo></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('forum','首页总数')">
				<el-card v-show="countTypeList.closeforumCountType" class="count-item">
					<template #header>
						<div class="card_head_title">
							互动交流
						</div>
						<div class="card_head_right">
							<el-icon @click="countTypeClick('hiddenforumCountType')" class="showIcons"
								:class="countTypeList.hiddenforumCountType?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="countTypeClick('closeforumCountType')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenforumCountType">
							<div class="count_title"><span>互动交流总数</span></div>
							<div class="count_num"><countTo :startVal="0" :endVal="forumCount" :duration="1000"></countTo></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>

<div class="zd"> 


</div>

		<div class="chartList">
			<el-collapse-transition v-if="btnAuth('cansaixuesheng','首页统计')">
				<el-card v-show="cardTypeList.closecansaixueshengChartType1" class="chart-item">
					<template #header>
						<div class="card_head_title">
							参赛学生
						</div>
						<div class="card_head_right">
							<el-icon @click="cardTypeClick('hiddencansaixueshengChartType1')" class="showIcons"
									 :class="cardTypeList.hiddencansaixueshengChartType1?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="cardTypeClick('closecansaixueshengChartType1')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddencansaixueshengChartType1">
							<div class="chart-title"><span>学生数量统计</span></div>
							<div id="cansaixueshengxingbieEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('bisaijishutongji','首页统计')">
				<el-card v-show="cardTypeList.closebisaijishutongjiChartType1" class="chart-item">
					<template #header>
						<div class="card_head_title">
							比赛技术统计
						</div>
						<div class="card_head_right">
							<el-icon @click="cardTypeClick('hiddenbisaijishutongjiChartType1')" class="showIcons"
									 :class="cardTypeList.hiddenbisaijishutongjiChartType1?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="cardTypeClick('closebisaijishutongjiChartType1')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenbisaijishutongjiChartType1">
							<div class="chart-title"><span>比赛技术统计</span></div>
							<div id="bisaijishutongjiqiuduimingchengEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('forum','首页统计')">
				<el-card v-show="cardTypeList.closeforumChartType1" class="chart-item">
					<template #header>
						<div class="card_head_title">
							互动交流
						</div>
						<div class="card_head_right">
							<el-icon @click="cardTypeClick('hiddenforumChartType1')" class="showIcons"
									 :class="cardTypeList.hiddenforumChartType1?'showIcons1':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="cardTypeClick('closeforumChartType1')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenforumChartType1">
							<div class="chart-title"><span>论坛活跃度统计</span></div>
							<div id="forumthumbsupNumberEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('forum','首页统计')">
				<el-card v-show="cardTypeList.closeforumChartType2" class="chart-item">
					<template #header>
						<div class="card_head_title">
							互动交流
						</div>
						<div class="card_head_right">
							<el-icon @click="cardTypeClick('hiddenforumChartType2')" class="showIcons"
									 :class="cardTypeList.hiddenforumChartType2?'showIcons2':''">
								<ArrowUpBold />
							</el-icon>
							<el-icon @click="cardTypeClick('closeforumChartType2')" class="closeIcons">
								<CloseBold />
							</el-icon>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenforumChartType2">
							<div class="chart-title"><span>发帖量统计</span></div>
							<div id="forumtypeNameEchart2" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>



	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance,
		computed,
		onMounted,
	} from 'vue';
	import {
		useStore
	} from 'vuex';
	const moment = window.moment
	const store = useStore()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const user = computed(()=>store.getters['user/session'])
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	import countTo from '@/components/count-to/vue-countTo.vue';
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closecansaixueshengCountType = true
		countTypeList.value.hiddencansaixueshengCountType = true
		if(btnAuth('cansaixuesheng','首页总数')){
			getcansaixueshengCount()
		}
		countTypeList.value.closebisaijishutongjiCountType = true
		countTypeList.value.hiddenbisaijishutongjiCountType = true
		if(btnAuth('bisaijishutongji','首页总数')){
			getbisaijishutongjiCount()
		}
		countTypeList.value.closeforumCountType = true
		countTypeList.value.hiddenforumCountType = true
		if(btnAuth('forum','首页总数')){
			getforumCount()
		}
	}
	const cansaixueshengCount = ref(0)
	const getcansaixueshengCount = () => {
		context?.$http({
			url:'cansaixuesheng/count',
			method: 'get'
		}).then(res=>{
			cansaixueshengCount.value = res.data.data
		})
	}
	const bisaijishutongjiCount = ref(0)
	const getbisaijishutongjiCount = () => {
		context?.$http({
			url:'bisaijishutongji/count',
			method: 'get'
		}).then(res=>{
			bisaijishutongjiCount.value = res.data.data
		})
	}
	const forumCount = ref(0)
	const getforumCount = () => {
		context?.$http({
			url:'forum/count',
			method: 'get'
		}).then(res=>{
			forumCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	//获取总数
	getCountList()
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getChartList()
		},1000)
	}
	const cardTypeList = ref({
		closecansaixueshengChartType1: true,
		hiddencansaixueshengChartType1: true,
		closebisaijishutongjiChartType1: true,
		hiddenbisaijishutongjiChartType1: true,
		closeforumChartType1: true,
		hiddenforumChartType1: true,
		closeforumChartType2: true,
		hiddenforumChartType2: true,
	})
	//判断是否有统计图筛选权限
	const changeStatQuery = (arr)=>{
		if(!arr){	//不传限制条件，默认都可见
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
	const getcansaixueshengChart1 = () => {
		nextTick(async ()=>{
			var xingbieEchart1 = echarts.init(document.getElementById("cansaixueshengxingbieEchart1"), null);
			let params = {}
			context?.$http({
				url: "cansaixuesheng/group/xingbie?order=desc",
				method: "get",
				params
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].xingbie);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].xingbie				    })
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
				xingbieEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				//根据窗口的大小变动图表
				xingbieEchart1.resize();
			})
		})
	}
	const getbisaijishutongjiChart1 = () => {
		nextTick(async ()=>{
			var qiuduimingchengEchart1 = echarts.init(document.getElementById("bisaijishutongjiqiuduimingchengEchart1"), null);
			let params = {}
			context?.$http({
				url: `bisaijishutongji/valueMul/qiuduimingcheng?yColumnNameMul=defen,fangui,zanting`,
				method: "get",
				params
			}).then(obj=>{
				let res = obj.data.data
                let xAxis1 = [];
                let yAxis1 = [];
                let pArray1 = []
                for(let i=0;i<res[0].length;i++){
                    xAxis1.push(res[0][i].qiuduimingcheng);
                    yAxis1.push(parseFloat((res[0][i].total)));
                    pArray1.push({
                        value: parseFloat((res[0][i].total)),
                        name: res[0][i].qiuduimingcheng
                    })
                }
                let xAxis2 = [];
                let yAxis2 = [];
                let pArray2 = []
                for(let i=0;i<res[1].length;i++){
                    xAxis2.push(res[1][i].qiuduimingcheng);
                    yAxis2.push(parseFloat((res[1][i].total)));
                    pArray2.push({
                        value: parseFloat((res[1][i].total)),
                        name: res[1][i].qiuduimingcheng
                    })
                }
                let xAxis3 = [];
                let yAxis3 = [];
                let pArray3 = []
                for(let i=0;i<res[2].length;i++){
                    xAxis3.push(res[2][i].qiuduimingcheng);
                    yAxis3.push(parseFloat((res[2][i].total)));
                    pArray3.push({
                        value: parseFloat((res[2][i].total)),
                        name: res[2][i].qiuduimingcheng
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
				qiuduimingchengEchart1.clear()
                qiuduimingchengEchart1.setOption(option);
                //根据窗口的大小变动图表
                qiuduimingchengEchart1.resize();
			})
		})
	}
	const getforumChart1 = () => {
		nextTick(async ()=>{
			var thumbsupNumberEchart1 = echarts.init(document.getElementById("forumthumbsupNumberEchart1"), null);
			let params = {}
			context?.$http({
				url: `forum/value/title/thumbsupNumber?order=desc`,
				method: "get",
				params
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].title);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].title				    })
				}
				var option = {};
				option = {
    title: {
        text: '论坛活跃度统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}
				thumbsupNumberEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				thumbsupNumberEchart1.setOption(option);
				//根据窗口的大小变动图表
				thumbsupNumberEchart1.resize();
			})
		})
	}
	const getforumChart2 = () => {
		nextTick(async ()=>{
			var typeNameEchart2 = echarts.init(document.getElementById("forumtypeNameEchart2"), null);
			let params = {}
			context?.$http({
				url: "forum/group/typeName?order=desc",
				method: "get",
				params
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].typeName);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].typeName				    })
				}
				var option = {};
				option = {
    title: {
        text: '发帖量统计',
        left: 'center'
    },
    grid:{
        containLabel:true
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
    },
    yAxis: {
        type: 'value'
    },
    series:{
        data: yAxis,
        type: 'bar'
    }
}
                let middle = option.xAxis
                option.xAxis = option.yAxis
                option.yAxis = middle
				typeNameEchart2.clear()
				// 使用刚指定的配置项和数据显示图表。
				typeNameEchart2.setOption(option);
				//根据窗口的大小变动图表
				typeNameEchart2.resize();
			})
		})
	}
	const getChartList = () => {
		if(btnAuth('cansaixuesheng','首页统计')){
			getcansaixueshengChart1()
		}
		if(btnAuth('bisaijishutongji','首页统计')){
			getbisaijishutongjiChart1()
		}
		if(btnAuth('forum','首页统计')){
			getforumChart1()
		}
		if(btnAuth('forum','首页统计')){
			getforumChart2()
		}
	}
	//获取统计图数据
	getChartList()
</script>
<style lang="scss" scoped>
	// 首页盒子
	.home_view {
        background-image: url("http://clfile.zggen.cn/20250728/efa8c4527e0d4b5fa31b9fa123463b16.webp")!important;
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
</style>
<style>
.condition-box {
	display: flex;
	gap: 10px;
	justify-content: center;
}

.condition-box>* {
	max-width: 300px;
}
.home_view{
    height: 970px!important;
   /* 新添加的关键属性 */
  overflow-y: auto; /* 当home子盒子内容超出时出现垂直滚动条 */
  /* 新增 - 隐藏所有滚动条 */
  scrollbar-width: none; /* Firefox 隐藏滚动条 */
  -ms-overflow-style: none; /* IE/Edge 隐藏滚动条 */
border-radius: 0px ;
    background:none !important;
}

/* 针对 Chrome/Safari 等 Webkit 浏览器隐藏滚动条 */
.home_view::-webkit-scrollbar {
  display: none; /* 隐藏滚动条 */
}
.home_view .countList {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
    
}

.home_view .count-item {
    flex: 1;
    min-width:30%;
    position: relative;
    border-radius: 14px;
}

.home_view .el-card__header {
    display: none;
}

.home_view .count_title {
    color: #999;
}

.home_view .count_num {
    font-size: 26px;
    font-weight: 700;
    line-height: 60px;
}

.home_view .count-item:before {
    content:'';
    width:95px;
    height: 62px;
        background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
 
    position: absolute;
    top:8%;
    right:5%;
    
    background-image: url('http://clfile.zggen.cn/20250828/fc8891c543a2441797477a62f3610155.png')!important;
}
.home_view .count-item:nth-child(4n+2):before{
    background-image: url('http://clfile.zggen.cn/20250828/5ce6196c35e04f94b6a95bc1a074589e.png')!important;
}
.home_view .count-item:nth-child(4n+3):before{
    background-image: url('http://clfile.zggen.cn/20250828/842b382641aa44b6822c908e4980b2a2.png')!important;
}
.home_view .count-item:nth-child(4n+4):before{
    background-image: url('http://clfile.zggen.cn/20250828/4a3d9ea817a94700bf08f6baa619cfcb.png')!important;
}


.home_view {
    padding: 30px;
    background: #fef7db;
}

.home_view .chartList {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
    margin-top: 30px;
}

.home_view .chart-item {
    flex: 1;
    min-width: 40%;
    border-radius: 16px;
}

.home_view .row3 {
    display: flex;
    margin-top: 30px;
    gap: 30px;
}
.home_view .el-carousel img {
    object-fit: cover;
}

.home_view .el-carousel {
    margin-bottom: 20px;
}
.home_view .chart-title {
    display: none;
}
.home_view .zd{
 width:100%;
 display:flex;

gap: 20px;
border-radius: 0px 30px 30px 30px;
    background: none!important;
}

.home_view .zdd {
flex: 2;
display:inline-block!important;
gap:20px;
 overflow-y: auto; /* 当home子盒子内容超出时出现垂直滚动条 */
  /* 新增 - 隐藏所有滚动条 */
  scrollbar-width: none; /* Firefox 隐藏滚动条 */
  -ms-overflow-style: none; /* IE/Edge 隐藏滚动条 */
border-radius: 0px 30px 30px 30px;
    background: none!important;
}

/* 针对 Chrome/Safari 等 Webkit 浏览器隐藏滚动条 */
.home_view .zdd::-webkit-scrollbar {
  display: none; /* 隐藏滚动条 */
}

.home_view .home_news {
flex:1;
 height:455px;
}

.home_view .home-calendar {
width:100%;
margin-top:20px;
}

.home_view .home_news .list {
 height:380px;
}
.home_view .countList {
background: linear-gradient( 360deg, #0949F7 0%, #B3D6EC 100%);
border-radius: 10px 10px 10px 10px;
gap:30px !important;
}



.home_view .count_item {
    display: flex;
  flex-direction: column-reverse; /* 元素上下对调 */
  justify-content: center; /* 垂直居中 */

color: #FFFFFF;

}

.home_view .el-card.is-always-shadow.count-item {
background:none;
border:none;
box-shadow:none;
}
.home_view .home-calendar {
    padding:0px;
    margin-bottom:0px!important;
height: 455px;
background: #F6F6F6;

}

.home_view .header {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%);
box-shadow: 5px 5px 10px 0px #BCD0FF, -5px -5px 10px 0px #FFFFFF;
border-radius: 5px 5px 5px 5px;

}

.home_view table {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%);
box-shadow: 5px 5px 10px 0px #BCD0FF, -5px -5px 10px 0px #FFFFFF;
border-radius: 5px 5px 5px 5px;

}

 



.home_view .header .title {
margin:auto
}

.home_view .el-card.is-always-shadow.chart-item {

background: #FFFFFF;
box-shadow: 0px 12px 32px 0px rgba(0,0,0,0.02), 0px 8px 16px -8px rgba(0,0,0,0.02), 0px 16px 48px 16px rgba(0,0,0,0.01)!important;
border-radius: 10px 10px 10px 10px!important;
}
.home_view .el-carousel.el-carousel--horizontal.el-carousel--card {
    display:none;
}


.home_view .zdd {
    border-radius:0px!important;
}




.home_view .countList {
    background:none!important;
}

.home_view .el-card.is-always-shadow.count-item {
  
 border: none!important;
    box-shadow: none!important;
}



.home_view  .count_title {
    order:10!important;
}

.home_view .count_num {
    order:-1!important;
    margin-left:10px;
}

.home_view .home-calendar {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%)!important;
}
.home_view .header .title {
    width:100%;
    text-align:center;
}

.home_view .chartList .el-card.is-always-shadow.chart-item {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%);
box-shadow: 5px 5px 10px 0px #BCD0FF, -5px -5px 10px 0px #FFFFFF;
    border-radius: 5px 5px 5px 5px;
}
.home_view .home_news {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%)!important;
box-shadow: 5px 5px 10px 0px #BCD0FF, -5px -5px 10px 0px #FFFFFF!important;
border-radius: 5px 5px 5px 5px!important;
height: 455px!important;
margin-top: 20px!important;
}

.home_view .home_news .list {
    height:380px!important;
          scrollbar-width: none; /* Firefox 隐藏滚动条 */
  -ms-overflow-style: none; /* IE/Edge 隐藏滚动条 */
}

.home_view .home_news .list .item {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%);
box-shadow: 1px 1px 10px 0px #BCD0FF, -1px -1px 10px 0px #FFFFFF;
border-radius: 5px 5px 5px 5px;
border: 1px solid #FFFFFF;

}
.home_view .zdd {
    padding:10px;
}

.home_view .home-calendar ::before {
    display:none!important;
}
.home_view .header {
    display:none!important;
}



.home_view .home-calendar {
    position:relative!important;
    text-align:center;
    width: 100%!important;
 
    
}


.home_view .home-calendar:before{
   display: inline-block;
    color:#FEBA55!important;
      width: 350px!important; /* 设置 logo 的宽度 */
  height: 80px!important; /* 设置 logo 的高度 */
  background-image: url('http://clfile.zggen.cn/20250828/2ad296fef82d4a8e9b8f8c654aa3a529.png'); /* logo 图片路径 */
         background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
   
}

.home_view  .chart-title {
color: #FEBA55!important;
}

.home_view  .chartList .el-card.is-always-shadow.chart-item .el-card__body .card_item .chart-title span {
color: #FEBA55!important;
}

.home_view .count_title {
color: #B0A594!important;
font-weight: 500;
font-size: 16px;
}

.home_view .count_num span {
    font-weight: 700;
font-size: 30px;
color: #000000;
}
.home_view .el-card.is-always-shadow.chart-item {
  position: relative!important; /* 使其成为定位容器 */
}
.home_view .el-card.is-always-shadow.chart-item::before {
  content: ""; /* 伪元素的内容为空 */
  position: absolute;
 top: -3%;             /* 紧贴容器顶部 */
left: 50%;          /* 定位到容器水平中心点 */
transform: translateX(-50%); /* 向左移动自身宽度50%，实现完美居中 */
  width: 350px; /* 设置 logo 的宽度 */
  height: 80px; /* 设置 logo 的高度 */
  background-image: url('http://clfile.zggen.cn/20250828/2ad296fef82d4a8e9b8f8c654aa3a529.png'); /* logo 图片路径 */
        background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
}
.home_view .list .item {
    display:flex;
    gap:5px;
    max-height:96px;
    overflow:hidden;
    
}

.home_view .newleft {
    flex:1;
    overflow:hidden;
    
}
.home_view .newleft img{
 position: relative;
  top: 50%;
  transform: translateY(-50%); /* 垂直居中 */
  max-width: 100%;
  max-height: 100%;
  width: auto; /* 保持原始比例 */
  height: auto;
    border-radius: 5px 5px 5px 5px;
border: 1px solid #FFFFFF;
}

.home_view .newright {
    flex:5;
}

.home_view .home_news .title {
    text-align:center;
    font-weight: 700;
font-size: 24px;
color: #FEBA55;
    position:relative!important;
    
}
.home_view .home_news .title::before {
  content: ""; /* 伪元素的内容为空 */
  position: absolute;
 top: -3%;             /* 紧贴容器顶部 */
left: 50%;          /* 定位到容器水平中心点 */
transform: translateX(-50%); /* 向左移动自身宽度50%，实现完美居中 */
  width: 350px; /* 设置 logo 的宽度 */
  height: 80px; /* 设置 logo 的高度 */
  background-image: url('http://clfile.zggen.cn/20250828/2ad296fef82d4a8e9b8f8c654aa3a529.png'); /* logo 图片路径 */
        background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
    z-index:-1;
}
.home_view .home-calendar table {
background: linear-gradient( 36deg, #E4E9F7 0%, #FFFFFF 100%)!important;
box-shadow: 5px 5px 10px 0px #BCD0FF, -5px -5px 10px 0px #FFFFFF!important;
border-radius: 5px 5px 5px 5px!important;
}
.home_view .el-card.is-always-shadow.count-item {
    min-width:20%;
}
.home_view {
    padding: 30px;
    background:none!important; 
    height: 100vh;
    overflow-y:auto;
      scrollbar-width: none; /* Firefox 隐藏滚动条 */
  -ms-overflow-style: none; /* IE/Edge 隐藏滚动条 */
}
.home_view .countList .el-card.is-always-shadow.count-item{
    background: rgba(255,255,255,0.5);
box-shadow: inset 4px 4px 10px 0px rgba(255,255,255,0.3);
border-radius: 20px 20px 20px 20px;
}

.home_view .count_title span {
color: #BDB8A1!important;
}

.home_view .count_num span{
color:#000000!important;
}
.home_view .count_item {
    flex-direction: column-reverse;
}

.home_view .count_item  {
    position:relative!important;

}
.home_view .count_item:before{
   content:'';
    width:100%;
    height: 10px;
        background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
 
    position: absolute;
    bottom:-15%;
    
    background-image: url('http://clfile.zggen.cn/20250828/9ea8130499b949d2a06e5a5db0d0087b.png')!important;
}
.home_view .home-calendar {
    flex:1;
}
.home_view .home-calendar:before{
   display: inline-block;
    color:#000000!important;
      width: 350px!important; /* 设置 logo 的宽度 */
  height: 80px!important; /* 设置 logo 的高度 */
  background-image: url('http://clfile.zggen.cn/20250828/2ad296fef82d4a8e9b8f8c654aa3a529.png'); /* logo 图片路径 */
         background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
    line-height:80px;
}
.home_view .home_news{
    box-shadow:none!important;
    background: rgba(255,255,255,0.5)!important;
box-shadow: inset 4px 4px 10px 0px rgba(255,255,255,0.3)!important;
border-radius: 20px 20px 20px 20px!important;
          scrollbar-width: none; /* Firefox 隐藏滚动条 */
  -ms-overflow-style: none; /* IE/Edge 隐藏滚动条 */
}

.home_view .home-calendar{
 box-shadow:none!important;
    background: rgba(255,255,255,0.5)!important;
box-shadow: inset 4px 4px 10px 0px rgba(255,255,255,0.3)!important;
border-radius: 20px 20px 20px 20px!important;
    padding-right:10px;
    padding-left: 10px;
}
.home_view .home-calendar table{
 background:none!important;
 box-shadow:none!important;
}
.home_view .home-calendar th{
    border-radius: 0px 0px 0px 0px;
border: 1px solid #BDB8A1;
}
.home_view .home-calendar td {
 border-radius: 0px 0px 0px 0px;
border: 1px solid #BDB8A1;
}
.home_view .festival{
 background:#000000;
 color:#ffffff;

}
.home_view .home_news .list .item{
 padding-bottom:10px;
 background:none;
 box-shadow:none;
 border:none;
 border-radius:0px;
 border-bottom:1px dashed #BEB9A2; 
}
.home_view .home_news .list .item:hover{
 
 padding-bottom:10px;
 background:#FFD85F!important;
 box-shadow:none;
 border:none;
 border-radius:0px;
 border-bottom:1px dashed  
}



.home_view .newleft img {
 border:none!important;
}

.row1  .t1{
color: #000000;
}


.home_view .home_news .list .item:hover .row2 div {
  color:#ffffff;
}

.home_view .home_news .list .item:hover .time {
  color:#ffffff;
}
.home_view .home_news .title{
 color:#000!important;
 z-index:1!important;
}
.home_view .home_news .title::before {
  content: ""; /* 伪元素的内容为空 */
  position: absolute;
 top:-20%;             /* 紧贴容器顶部 */
left: 50%;          /* 定位到容器水平中心点 */
transform: translateX(-50%); /* 向左移动自身宽度50%，实现完美居中 */
  width: 350px; /* 设置 logo 的宽度 */
  height: 80px; /* 设置 logo 的高度 */
  background-image: url('http://clfile.zggen.cn/20250828/2ad296fef82d4a8e9b8f8c654aa3a529.png'); /* logo 图片路径 */
        background-size: contain; /* 关键修改：保持图片比例 */
    background-repeat: no-repeat; /* 防止重复 */
    background-position: center; /* 居中显示 */
    z-index:-1;
}
.home_view .chartList .el-card.is-always-shadow.chart-item {
background: rgba(255,255,255,0.5);
box-shadow: inset 4px 4px 10px 0px rgba(255,255,255,0.3);
border-radius: 20px 20px 20px 20px;
}
.home-calendar{
  border: 0px solid rgb(238, 238, 238);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  color: rgb(102, 102, 102);
  width: 30%;
  min-width: 480px;
  font-size: inherit;
  height: auto;
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  padding: 0 30px 30px;
  height: 600px;
}
.home-calendar .header{
  padding: 17px 40px;
  border-color: rgba(126, 96, 16, 0.1);
  flex-wrap: wrap;
  background: #fef7db;
  border-width: 0px 0px 1px;
  display: flex;
  width: 100%;
  border-style: solid;
  justify-content: space-between;
  height: auto;
}
.home-calendar .header .btn{
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: rgb(102, 102, 102);
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: inherit;
  justify-content: center;
  order: 2;
}
.home-calendar .header .title{
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: rgb(51, 51, 51);
}
.home-calendar table{
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
}
.home-calendar tbody,.home-calendar thead{
  width: 100%;
}
.home-calendar tr{
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th{
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td{
  cursor: pointer;
  padding: 6px 12px 12px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}

.home-calendar .header .btn.lastYear,.home-calendar .header .btn.nextYear {
    display: none;
}

.home-calendar .btn.lastMonth {
    margin-left: auto;
}

.home-calendar:before {
    content: '日历';
    font-size: 22px;
    color: #555;
    line-height: 60px;
}

.home-calendar table {
    background: #fef7db;
}

.home-calendar .header .btn .text {
    display: none;
}

.home-calendar .lastMonth .iconfont {
    transform: rotate(180deg);
}

.home-calendar .iconfont {
    font-size: 28px;
    background: #fff;
    border-radius: 50%;
}
</style>