<template>
    <div class="home_box">




			<!-- 篮球赛事首页展示 -->
            <div class="homeList_view">
<div class="tableName"><span>篮球赛事展示</span></div>
<div class="list">
    <div v-for="(item,index) in lanqiusaishiHomeList" @click="detailClick('lanqiusaishi',item.id)" class="item" style="cursor: pointer;">
      
        <img v-if="item.imgUrls" :src="item.imgUrls[0]" alt="">
        <div class="content">

<div class="title">{{item.saishimingcheng}}</div>
<div class="title">比赛时间：{{item.bisaishijian}}</div>

              <div class="num-row">

<div class="storeupnum">
	<span class="iconfont icon-likeline4"></span>
	<div class="num">{{item.storeupNumber}}</div>
</div>


            </div>
        </div>
    </div>
</div>
<div class="more" @click="moreClick('lanqiusaishi')" style="cursor: pointer;"><span>查看更多 ></span></div>
            </div>




    </div>
</template>

<script setup>
	import {
		ref,
        computed,
		getCurrentInstance
	} from 'vue';
    const moment = window.moment
	import {
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
    const baseUrl = ref(context.$config.url)
	//篮球赛事首页展示
	const lanqiusaishiHomeList = ref([])
	const getlanqiusaishiHomeList = () => {
		let params = {
			page: 1,
			limit: 10,
            sort:'id',
            order:'desc',
		}
		context?.$http({
			url: 'lanqiusaishi/list',
			method: 'get',
			params: params
		}).then(res => {
			lanqiusaishiHomeList.value = res.data.data.list
			lanqiusaishiHomeList.value.forEach(item=>{
				if(item.fengmian!=null){
					item.imgUrls = item.fengmian.split(',').map(item=>(isHttp(item)?'':context.$config.url)+item)
				}else{
                    item.imgUrls = []
                }
			})
		})
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//篮球赛事首页展示
		getlanqiusaishiHomeList()
	}
	init()
</script>

<style lang="scss">
.home_box {
    width: 100%;
    margin: 0 auto;
    padding: 20px 0px 50px;
    display: flex;
    flex-wrap: wrap;
    background:#FFFFFF;  
}
.homeList_view {
       width: 100%;
    padding:10px 7%;
    margin-top: 30px;
    position: relative;
    order:2;
    background: #FFFFFF;
}

.homeList_view .tableName span{
    padding:0 0 6px;
 
}

.homeList_view .list {
    width: 100%;
    display: flex;
    gap: 30px;
    flex-wrap: wrap;
    margin-top: 30px;
}



.homeList_view .item img {
    width: 100%;
    height: 242px;
    object-fit: cover;
    border-radius: 20px;
}

.homeList_view .thumbsupnum,.homeList_view  .storeupnum,.homeList_view .clicknum {
    display: flex;
    align-items: center;
    gap: 6px;
}

.homeList_view .categoryList .item {
    background: #f0f0f0;
    padding: 10px 20px;
    text-align: center;
    min-width: 100px;
}

.homeList_view .el-carousel {
    height: 100%;
}

.homeList_view .el-carousel img {
    height: 100%;
}

.homeList_view .el-carousel__container {
    height: 100%;
}

.homeList_view .content {
    width: 100%;
    padding: 14px;
    display: flex;
    flex-wrap: wrap;
}


.homeList_view .price .number {
    font-weight: 900;
font-size: 24px;
color: #E50D0D;
}



.homeList_view .thumbsupnum {
background: none;
border-radius: 0px 0px 0px 0px;
    color:#3B432C;
    text-align:center;
 width:auto;
height: 30px;
    display: flex;
    align-items: center;
      justify-content:center;
}

.homeList_view .storeupnum {
background: none;
border-radius: 0px 0px 0px 0px;

    color:#FF6F2C;
    text-align:center;
 width:auto;
height: 30px;

    display: flex;
    align-items: center;
      justify-content:center;

}

.homeList_view .clicknum {
background: none;
border-radius: 0px 0px 0px 0px;
    color:#BBBE12;
    text-align:center;
 width:auto;
height: 30px;

    display: flex;
    align-items: center;
      justify-content:center;

}

.homeList_view .more {
    width: auto;
    position: absolute;
    top:20px;
    right:0;
}
.homeList_view .more span{
    color: #b51022;
}
/* nf9 */

.homeList_view .tableName  span {
    position:relative;
}



.homeList_view   .more span{
    color: var(--theme)!important;
    font-size: 16px;
}



/* nf13 */








/* nf15 */


.homeList_view .tableName {
   
    height:70px;
    line-height:70px;
    font-size:30px;
    font-weight:700;
    color:#000000;
}
.homeList_view .more {
    width: auto!important;
    padding:0px 10px;
    background:var(--theme)!important;
 
    position: absolute;
    top:25px;
    right:7%;
  background:none;
border-radius: 10px 10px 10px 10px;
    text-align:center;
    line-height:40px;
    border:1px solid var(--theme) ;
     box-sizing: border-box; /* 确保边框不会影响总大小 */
}
.homeList_view .more span{
   color:#FFF!important;
}

.homeList_view .num-row {
    margin-top:20px;
    display: flex;
    flex-wrap: nowrap;
    width:45%;
    order:4;
    gap:5px;
    justify-content:flex-end;
}
.homeList_view .price {
    width:45%;
    text-align: right;
font-weight: 900;
font-size: 24px;
color: #E50D0D;
    line-height: 1.5;
    margin: 20px auto 0 auto;
    order: 5;
    background: none;
}

.homeList_view .list .item{
  width: calc(25% - 30px)!important;
    border-radius: 0px;
    overflow: hidden;
    padding:0px;
    padding-top:0px;
    position:relative;

}



</style>