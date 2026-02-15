import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'cansaixueshengList',
			component: ()=>import('@/views/pages/cansaixuesheng/list')
		}, {
			path: 'cansaixueshengDetail',
			component: ()=>import('@/views/pages/cansaixuesheng/formModel')
		}, {
			path: 'cansaixueshengAdd',
			component: ()=>import('@/views/pages/cansaixuesheng/formAdd')
		}
		, {
			path: 'cansaixueshengCenter',
			component: ()=>import('@/views/pages/cansaixuesheng/center')
		}
		, {
			path: 'qiuduiduizhangList',
			component: ()=>import('@/views/pages/qiuduiduizhang/list')
		}, {
			path: 'qiuduiduizhangDetail',
			component: ()=>import('@/views/pages/qiuduiduizhang/formModel')
		}, {
			path: 'qiuduiduizhangAdd',
			component: ()=>import('@/views/pages/qiuduiduizhang/formAdd')
		}
		, {
			path: 'caipanyuanList',
			component: ()=>import('@/views/pages/caipanyuan/list')
		}, {
			path: 'caipanyuanDetail',
			component: ()=>import('@/views/pages/caipanyuan/formModel')
		}, {
			path: 'caipanyuanAdd',
			component: ()=>import('@/views/pages/caipanyuan/formAdd')
		}
		, {
			path: 'qiuduipaixingbangList',
			component: ()=>import('@/views/pages/qiuduipaixingbang/list')
		}, {
			path: 'qiuduipaixingbangDetail',
			component: ()=>import('@/views/pages/qiuduipaixingbang/formModel')
		}, {
			path: 'qiuduipaixingbangAdd',
			component: ()=>import('@/views/pages/qiuduipaixingbang/formAdd')
		}
		, {
			path: 'jiaruqiuduiList',
			component: ()=>import('@/views/pages/jiaruqiudui/list')
		}, {
			path: 'jiaruqiuduiDetail',
			component: ()=>import('@/views/pages/jiaruqiudui/formModel')
		}, {
			path: 'jiaruqiuduiAdd',
			component: ()=>import('@/views/pages/jiaruqiudui/formAdd')
		}
		, {
			path: 'lanqiusaishiList',
			component: ()=>import('@/views/pages/lanqiusaishi/list')
		}, {
			path: 'lanqiusaishiDetail',
			component: ()=>import('@/views/pages/lanqiusaishi/formModel')
		}, {
			path: 'lanqiusaishiAdd',
			component: ()=>import('@/views/pages/lanqiusaishi/formAdd')
		}
		, {
			path: 'bisaibaomingList',
			component: ()=>import('@/views/pages/bisaibaoming/list')
		}, {
			path: 'bisaibaomingDetail',
			component: ()=>import('@/views/pages/bisaibaoming/formModel')
		}, {
			path: 'bisaibaomingAdd',
			component: ()=>import('@/views/pages/bisaibaoming/formAdd')
		}
		, {
			path: 'jingsairichengList',
			component: ()=>import('@/views/pages/jingsairicheng/list')
		}, {
			path: 'jingsairichengDetail',
			component: ()=>import('@/views/pages/jingsairicheng/formModel')
		}, {
			path: 'jingsairichengAdd',
			component: ()=>import('@/views/pages/jingsairicheng/formAdd')
		}
		, {
			path: 'bisaijishutongjiList',
			component: ()=>import('@/views/pages/bisaijishutongji/list')
		}, {
			path: 'bisaijishutongjiDetail',
			component: ()=>import('@/views/pages/bisaijishutongji/formModel')
		}, {
			path: 'bisaijishutongjiAdd',
			component: ()=>import('@/views/pages/bisaijishutongji/formAdd')
		}
		, {
			path: 'bisaichengjiList',
			component: ()=>import('@/views/pages/bisaichengji/list')
		}, {
			path: 'bisaichengjiDetail',
			component: ()=>import('@/views/pages/bisaichengji/formModel')
		}, {
			path: 'bisaichengjiAdd',
			component: ()=>import('@/views/pages/bisaichengji/formAdd')
		}
        , {
            path: 'storeupList',
            component: ()=>import('@/views/pages/storeup/list')
        }
		, {
			path: 'sensitiveWordList',
			component: ()=>import('@/views/pages/sensitiveWord/list')
		}, {
			path: 'sensitiveWordDetail',
			component: ()=>import('@/views/pages/sensitiveWord/formModel')
		}, {
			path: 'sensitiveWordAdd',
			component: ()=>import('@/views/pages/sensitiveWord/formAdd')
		}
        , {
            path: 'forumList',
            component: ()=>import('@/views/pages/forum/list')
        }
        , {
            path: 'forumDetail',
            component: () => import('../views/pages/forum/detail')
        }
		, {
			path: 'forumTypeList',
			component: ()=>import('@/views/pages/forumType/list')
		}, {
			path: 'forumTypeDetail',
			component: ()=>import('@/views/pages/forumType/formModel')
		}, {
			path: 'forumTypeAdd',
			component: ()=>import('@/views/pages/forumType/formAdd')
		}
		, {
			path: 'forumReportList',
			component: ()=>import('@/views/pages/forumReport/list')
		}, {
			path: 'forumReportDetail',
			component: ()=>import('@/views/pages/forumReport/formModel')
		}, {
			path: 'forumReportAdd',
			component: ()=>import('@/views/pages/forumReport/formAdd')
		}
		, {
			path: 'jinjishenqingList',
			component: ()=>import('@/views/pages/jinjishenqing/list')
		}, {
			path: 'jinjishenqingDetail',
			component: ()=>import('@/views/pages/jinjishenqing/formModel')
		}, {
			path: 'jinjishenqingAdd',
			component: ()=>import('@/views/pages/jinjishenqing/formAdd')
		}
		, {
			path: 'jinjibisaijieguoList',
			component: ()=>import('@/views/pages/jinjibisaijieguo/list')
		}, {
			path: 'jinjibisaijieguoDetail',
			component: ()=>import('@/views/pages/jinjibisaijieguo/formModel')
		}, {
			path: 'jinjibisaijieguoAdd',
			component: ()=>import('@/views/pages/jinjibisaijieguo/formAdd')
		}
		, {
			path: 'emailregistercodeList',
			component: ()=>import('@/views/pages/emailregistercode/list')
		}, {
			path: 'emailregistercodeDetail',
			component: ()=>import('@/views/pages/emailregistercode/formModel')
		}, {
			path: 'emailregistercodeAdd',
			component: ()=>import('@/views/pages/emailregistercode/formAdd')
		}
		]
	},
	{
		path: '/login',
		component: ()=>import('../views/pages/login.vue')
	}
	,{
		path: '/cansaixueshengRegister',
		component: ()=>import('@/views/pages/cansaixuesheng/register')
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})
// 全局后置钩子，路由跳转后执行
router.afterEach(() => {
    // 滚动到顶部
    window.scrollTo(0, 0)
})

export default router
