import {
	createRouter,
	createWebHashHistory
} from 'vue-router'
import login from '@/views/login.vue'
import index from '@/views/index.vue'
import HomeView from '@/views/HomeView.vue'
export const routes = [{
		path: '/login',
		name: 'login',
        meta: { title: '登录' },
		component: login
	},{
		path: '/',
		name: '首页',
        meta: { title: '首页' },
		component: index,
		children: [{
			path: '/',
			name: 'home',
			component: HomeView,
			meta: {
				affix: true,
                title: '首页'
			}
		}, {
			path: '/updatepassword',
			name: 'updatepassword',
            meta: { title: '修改密码' },
			component: () => import('../views/updatepassword.vue')
		}

		,{
			path: '/qiuduiduizhangCenter',
			name: 'qiuduiduizhangCenter',
            meta: { title: '球队队长个人中心' },
			component: ()=>import('@/views/qiuduiduizhang/center')
		}
		,{
			path: '/caipanyuanCenter',
			name: 'caipanyuanCenter',
            meta: { title: '裁判员个人中心' },
			component: ()=>import('@/views/caipanyuan/center')
		}
		,{
			path: '/qiuduiduizhang',
			name: 'qiuduiduizhang',
            meta: { title: '球队队长' },
			component: ()=>import('@/views/qiuduiduizhang/list')
		}
		,{
			path: '/forumReport',
			name: 'forumReport',
            meta: { title: '论坛举报' },
			component: ()=>import('@/views/forumReport/list')
		}
		,{
			path: '/forumType',
			name: 'forumType',
            meta: { title: '论坛类型' },
			component: ()=>import('@/views/forumType/list')
		}
		,{
			path: '/discusslanqiusaishi',
			name: 'discusslanqiusaishi',
            meta: { title: '篮球赛事评论-评论' },
			component: ()=>import('@/views/discusslanqiusaishi/list')
		}
		,{
			path: '/lanqiusaishi',
			name: 'lanqiusaishi',
            meta: { title: '篮球赛事' },
			component: ()=>import('@/views/lanqiusaishi/list')
		}
		,{
			path: '/qiuduipaixingbang',
			name: 'qiuduipaixingbang',
            meta: { title: '球队排行榜' },
			component: ()=>import('@/views/qiuduipaixingbang/list')
		}
		,{
			path: '/cansaixuesheng',
			name: 'cansaixuesheng',
            meta: { title: '参赛学生' },
			component: ()=>import('@/views/cansaixuesheng/list')
		}
		,{
			path: '/storeup',
			name: 'storeup',
            meta: { title: '我的收藏' },
			component: ()=>import('@/views/storeup/list')
		}
		,{
			path: '/caipanyuan',
			name: 'caipanyuan',
            meta: { title: '裁判员' },
			component: ()=>import('@/views/caipanyuan/list')
		}
		,{
			path: '/jiaruqiudui',
			name: 'jiaruqiudui',
            meta: { title: '加入球队' },
			component: ()=>import('@/views/jiaruqiudui/list')
		}
		,{
			path: '/forum',
			name: 'forum',
            meta: { title: '互动交流' },
			component: ()=>import('@/views/forum/list')
		}
		,{
			path: '/jinjibisaijieguo',
			name: 'jinjibisaijieguo',
            meta: { title: '晋级比赛结果' },
			component: ()=>import('@/views/jinjibisaijieguo/list')
		}
		,{
			path: '/sensitiveWord',
			name: 'sensitiveWord',
            meta: { title: '敏感词' },
			component: ()=>import('@/views/sensitiveWord/list')
		}
		,{
			path: '/discussqiuduipaixingbang',
			name: 'discussqiuduipaixingbang',
            meta: { title: '球队排行榜评论-评论' },
			component: ()=>import('@/views/discussqiuduipaixingbang/list')
		}
		,{
			path: '/jinjishenqing',
			name: 'jinjishenqing',
            meta: { title: '晋级申请' },
			component: ()=>import('@/views/jinjishenqing/list')
		}
		,{
			path: '/jingsairicheng',
			name: 'jingsairicheng',
            meta: { title: '竞赛日程' },
			component: ()=>import('@/views/jingsairicheng/list')
		}
		,{
			path: '/bisaibaoming',
			name: 'bisaibaoming',
            meta: { title: '比赛报名' },
			component: ()=>import('@/views/bisaibaoming/list')
		}
		,{
			path: '/bisaichengji',
			name: 'bisaichengji',
            meta: { title: '比赛成绩' },
			component: ()=>import('@/views/bisaichengji/list')
		}
		,{
			path: '/bisaijishutongji',
			name: 'bisaijishutongji',
            meta: { title: '比赛技术统计' },
			component: ()=>import('@/views/bisaijishutongji/list')
		}
		,{
			path: '/config',
			name: 'config',
            meta: { title: '轮播图' },
			component: ()=>import('@/views/config/list')
		}
		]
	},
	{
		path: '/qiuduiduizhangRegister',
		name: 'qiuduiduizhangRegister',
        meta: { title: '球队队长注册' },
		component: ()=>import('@/views/qiuduiduizhang/register')
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
