const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '互动交流',
					icon: 'icon-common18',
					child:[

						{
							name:'互动交流',
                            url:'/index/forumList'

						},
					]
				},
				{
					name: '篮球赛事管理',
					icon: '',
					child:[

						{
							name:'篮球赛事',
                            url:'/index/lanqiusaishiList'

						},
					]
				},
				{
					name: '球队排行榜管理',
					icon: '',
					child:[

						{
							name:'球队排行榜',
                            url:'/index/qiuduipaixingbangList'

						},
					]
				},
				{
					name: '比赛技术统计管理',
					icon: '',
					child:[

						{
							name:'比赛技术统计',
                            url:'/index/bisaijishutongjiList'

						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: `高校三人篮球联赛管理系统`
        } 
    }
}
export default config
