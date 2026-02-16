<template>
    <div class="home_box">
			<!-- 篮球赛事首页展示 -->
			<div class="homeList_view">
				<div class="section-header">
					<div class="section-title">
						<span class="section-title__accent"></span>
						<span class="section-title__text">篮球赛事展示</span>
					</div>
					<div class="more" @click="moreClick('lanqiusaishi')" style="cursor: pointer;"><span>查看更多</span></div>
				</div>

				<div class="list">
					<div v-for="(item,index) in lanqiusaishiHomeList" :key="item.id" @click="detailClick('lanqiusaishi',item.id)" class="item" style="cursor: pointer;">
						<div class="cover">
							<img v-if="item.imgUrls" :src="item.imgUrls[0]" alt="">
							<div v-else class="cover__empty">暂无封面</div>
							<div v-if="index === 0" class="badge">最新</div>
						</div>
						<div class="content">
							<div class="title" :title="item.saishimingcheng">{{item.saishimingcheng}}</div>
							<div class="meta" :title="item.bisaishijian">比赛时间：{{item.bisaishijian}}</div>
							<div class="num-row">
								<div class="storeupnum">
									<span class="iconfont icon-likeline4"></span>
									<div class="num">{{item.storeupNumber}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
	padding: 0 0 50px;
	display: flex;
	flex-wrap: wrap;
	background: transparent;
}

.homeList_view {
	width: 100%;
	max-width: 1280px;
	margin: 0 auto;
	padding: 22px 24px;
	margin-top: 18px;
	background: #ffffff;
	border: 1px solid var(--slate-200);
	border-radius: 16px;
	box-shadow: var(--card-shadow);
}

.section-header {
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 16px;
}

.section-title {
	display: flex;
	align-items: center;
	gap: 12px;
	font-size: 28px;
	font-weight: 800;
	color: var(--slate-900);
}

.section-title__accent {
	width: 40px;
	height: 40px;
	border-radius: 12px;
	background: var(--orange-gradient);
	box-shadow: 0 10px 25px -5px rgba(249, 115, 22, 0.25);
}

.more {
	padding: 10px 18px;
	border-radius: 12px;
	background: var(--orange-500);
	border: 1px solid rgba(249, 115, 22, 0.25);
	box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
	transition: all 0.2s ease;
}

.more span {
	color: #fff !important;
	font-size: 14px;
	font-weight: 700;
}

.more:hover {
	transform: translateY(-1px);
	background: var(--orange-600);
}

.list {
	width: 100%;
	display: grid;
	grid-template-columns: repeat(4, minmax(0, 1fr));
	gap: 24px;
	margin-top: 22px;
}

.item {
	overflow: hidden;
	border-radius: 14px;
	border: 1px solid var(--slate-200);
	background: #fff;
	box-shadow: var(--card-shadow);
	transition: all 0.25s ease;
}

.item:hover {
	transform: translateY(-4px);
	box-shadow: var(--card-hover-shadow);
	border-color: rgba(249, 115, 22, 0.35);
}

.cover {
	position: relative;
	width: 100%;
	height: 180px;
	overflow: hidden;
	background: var(--slate-50);
}

.cover img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.35s ease;
}

.item:hover .cover img {
	transform: scale(1.05);
}

.cover__empty {
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	color: var(--slate-700);
	font-weight: 600;
}

.badge {
	position: absolute;
	top: 12px;
	right: 12px;
	padding: 6px 12px;
	border-radius: 999px;
	background: rgba(249, 115, 22, 0.95);
	color: #fff;
	font-size: 12px;
	font-weight: 800;
	box-shadow: 0 10px 15px -3px rgba(249, 115, 22, 0.15), 0 4px 6px -2px rgba(249, 115, 22, 0.08);
}

.content {
	padding: 16px;
	display: flex;
	flex-direction: column;
	gap: 10px;
}

.title {
	font-size: 16px;
	font-weight: 800;
	color: var(--slate-900);
	line-height: 1.3;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.meta {
	font-size: 13px;
	color: var(--slate-700);
	opacity: 0.85;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.num-row {
	display: flex;
	justify-content: flex-end;
}

.storeupnum {
	display: inline-flex;
	align-items: center;
	gap: 6px;
	padding: 6px 10px;
	border-radius: 999px;
	background: #fff7ed;
	border: 1px solid rgba(249, 115, 22, 0.2);
	color: var(--orange-500);
	font-weight: 800;
}

.storeupnum .iconfont {
	font-size: 16px;
}

@media (max-width: 1024px) {
	.homeList_view {
		padding: 18px 18px;
	}
	.list {
		grid-template-columns: repeat(2, minmax(0, 1fr));
	}
}

@media (max-width: 768px) {
	.section-title {
		font-size: 22px;
	}
	.section-title__accent {
		width: 36px;
		height: 36px;
	}
	.list {
		grid-template-columns: 1fr;
	}
}



</style>