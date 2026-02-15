import React, { useState } from 'react';
import { Search, Calendar, MapPin, Trophy, Users, TrendingUp } from 'lucide-react';

export default function CompetitionManagementUI() {
  const [activeTab, setActiveTab] = useState('matches');
  const [searchTerm, setSearchTerm] = useState('');

  // 比赛数据
  const matchesData = [
    { id: 1, event: 'A组第1轮：烈焰冲锋队 vs极速闪电团', venue: '中心体育馆1号场', time: '2024年3月1日 14:00', teamA: '烈焰冲锋队', scoreA: 21, teamB: '极速闪电团', scoreB: 19, rank: 1, status: '胜', date: '2024-03-01', code: 'CP2023001', referee: '赵铁面' },
    { id: 2, event: 'A组第1轮：无限火力队 vs青春风暴', venue: '中心体育馆2号场', time: '2024年3月1日 15:30', teamA: '无限火力队', scoreA: 20, teamB: '青春风暴', scoreB: 15, rank: 1, status: '胜', date: '2024-03-01', code: 'CP2023002', referee: '冯雷' },
    { id: 3, event: 'A组第2轮：烈焰冲锋队 vs无限火力队', venue: '中心体育馆1号场', time: '2024年3月2日 14:00', teamA: '无限火力队', scoreA: 19, teamB: '烈焰冲锋队', scoreB: 17, rank: 1, status: '胜', date: '2024-03-02', code: 'CP2023003', referee: '张明远' },
    { id: 4, event: 'A组第2轮：极速闪电团 vs青春风暴', venue: '中心体育馆2号场', time: '2024年3月2日 15:30', teamA: '极速闪电团', scoreA: 21, teamB: '青春风暴', scoreB: 18, rank: 1, status: '胜', date: '2024-03-02', code: 'CP2023004', referee: '王志刚' },
    { id: 5, event: 'A组第3轮：烈焰冲锋队 vs青春风暴', venue: '中心体育馆1号场', time: '2024年3月3日 14:00', teamA: '烈焰冲锋队', scoreA: 22, teamB: '青春风暴', scoreB: 16, rank: 1, status: '胜', date: '2024-03-03', code: 'CP2023005', referee: '孙公正' },
  ];

  // 技术统计数据
  const statsData = [
    { id: 1, event: 'A组第1轮：烈焰冲锋队vs极速闪电团', time: '2024年3月1日 14:00', venue: '中心体育馆1号场', team: '烈焰冲锋队', score: 21, offense: 8, defense: 2, record: '2024-03-01 14:30:00', code: 'CP2023001', referee: '赵铁面' },
    { id: 2, event: 'A组第1轮：烈焰冲锋队vs极速闪电团', time: '2024年3月1日 14:00', venue: '中心体育馆1号场', team: '极速闪电团', score: 19, offense: 10, defense: 1, record: '2024-03-01 14:30:00', code: 'CP2023001', referee: '赵铁面' },
    { id: 3, event: 'A组第1轮：无限火力队vs青春风暴', time: '2024年3月1日 15:30', venue: '中心体育馆2号场', team: '无限火力队', score: 20, offense: 7, defense: 1, record: '2024-03-01 16:00:00', code: 'CP2023002', referee: '冯雷' },
    { id: 4, event: 'A组第1轮：无限火力队vs青春风暴', time: '2024年3月1日 15:30', venue: '中心体育馆2号场', team: '青春风暴', score: 15, offense: 12, defense: 2, record: '2024-03-01 16:00:00', code: 'CP2023002', referee: '冯雷' },
  ];

  const filteredMatches = matchesData.filter(match => 
    match.event.toLowerCase().includes(searchTerm.toLowerCase()) ||
    match.teamA.toLowerCase().includes(searchTerm.toLowerCase()) ||
    match.teamB.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const filteredStats = statsData.filter(stat => 
    stat.event.toLowerCase().includes(searchTerm.toLowerCase()) ||
    stat.team.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-slate-50">
      {/* 顶部橙色强调条 */}
      <div className="h-1 bg-gradient-to-r from-orange-500 to-orange-400"></div>

      {/* 主容器 */}
      <div className="max-w-7xl mx-auto px-6 py-10">
        
        {/* 页眉 */}
        <header className="mb-12">
          <div className="flex items-center justify-between mb-8">
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl bg-gradient-to-br from-orange-500 to-orange-600 flex items-center justify-center shadow-lg shadow-orange-500/25">
                <Trophy className="w-7 h-7 text-white" />
              </div>
              <div>
                <h1 className="text-3xl font-bold text-slate-900 tracking-tight">
                  比赛管理系统
                </h1>
                <p className="text-slate-500 text-sm mt-0.5">Competition Management</p>
              </div>
            </div>
            <button className="px-5 py-2.5 text-sm font-medium text-slate-600 hover:text-slate-900 transition-colors">
              返回
            </button>
          </div>

          {/* 搜索栏 - 简约设计 */}
          <div className="relative">
            <Search className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-400" />
            <input
              type="text"
              placeholder="搜索赛事或球队"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full pl-12 pr-4 py-3.5 bg-white border border-slate-200 rounded-lg text-slate-900 placeholder-slate-400 focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent transition-shadow shadow-sm"
            />
          </div>
        </header>

        {/* 标签页导航 - 简约风格 */}
        <div className="flex gap-1 bg-white border border-slate-200 p-1 rounded-lg shadow-sm mb-8">
          <button
            onClick={() => setActiveTab('matches')}
            className={`flex-1 px-6 py-3 rounded-md font-medium transition-all duration-200 flex items-center justify-center gap-2 ${
              activeTab === 'matches'
                ? 'bg-orange-500 text-white shadow-lg shadow-orange-500/20'
                : 'text-slate-600 hover:text-slate-900 hover:bg-slate-50'
            }`}
          >
            <Trophy className="w-4 h-4" />
            比赛记录
          </button>
          <button
            onClick={() => setActiveTab('stats')}
            className={`flex-1 px-6 py-3 rounded-md font-medium transition-all duration-200 flex items-center justify-center gap-2 ${
              activeTab === 'stats'
                ? 'bg-orange-500 text-white shadow-lg shadow-orange-500/20'
                : 'text-slate-600 hover:text-slate-900 hover:bg-slate-50'
            }`}
          >
            <TrendingUp className="w-4 h-4" />
            比赛技术统计
          </button>
        </div>

        {/* 比赛记录页面 */}
        {activeTab === 'matches' && (
          <div className="space-y-3">
            {filteredMatches.map((match) => (
              <div
                key={match.id}
                className="bg-white rounded-lg p-6 hover:shadow-md transition-all duration-200 border border-slate-200 hover:border-orange-300 group shadow-sm"
              >
                <div className="flex items-center gap-6">
                  
                  {/* 序号 - 极简设计 */}
                  <div className="flex-shrink-0">
                    <div className="w-8 h-8 rounded-lg bg-slate-100 flex items-center justify-center border border-slate-200">
                      <span className="text-sm font-semibold text-slate-700">{match.id}</span>
                    </div>
                  </div>

                  {/* 赛事信息 - 清晰层次 */}
                  <div className="flex-1 min-w-0">
                    <h3 className="text-slate-900 font-medium mb-3 group-hover:text-orange-600 transition-colors">
                      {match.event}
                    </h3>
                    <div className="flex gap-6 text-sm text-slate-500">
                      <div className="flex items-center gap-1.5">
                        <MapPin className="w-4 h-4" />
                        <span>{match.venue}</span>
                      </div>
                      <div className="flex items-center gap-1.5">
                        <Calendar className="w-4 h-4" />
                        <span>{match.time}</span>
                      </div>
                    </div>
                  </div>

                  {/* 比分 - 突出显示 */}
                  <div className="flex-shrink-0">
                    <div className="flex items-center gap-6">
                      <div className="text-right">
                        <div className="text-sm text-slate-500 mb-1">{match.teamA}</div>
                        <div className="text-3xl font-bold text-slate-900">{match.scoreA}</div>
                      </div>
                      <div className="text-2xl font-light text-slate-300">:</div>
                      <div className="text-left">
                        <div className="text-sm text-slate-500 mb-1">{match.teamB}</div>
                        <div className="text-3xl font-bold text-slate-400">{match.scoreB}</div>
                      </div>
                    </div>
                  </div>

                  {/* 状态和详情 */}
                  <div className="flex-shrink-0 flex flex-col items-end gap-2">
                    <span className="px-3 py-1 bg-green-50 text-green-600 text-xs font-medium rounded-full border border-green-200">
                      {match.status}
                    </span>
                    <span className="text-xs text-slate-400 font-mono">{match.code}</span>
                    <span className="text-xs text-slate-400">裁判: {match.referee}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}

        {/* 技术统计页面 */}
        {activeTab === 'stats' && (
          <div>
            {/* 现代化表格 */}
            <div className="bg-white rounded-lg overflow-hidden border border-slate-200 shadow-sm">
              
              {/* 表头 */}
              <div className="bg-slate-50 border-b border-slate-200">
                <div className="grid grid-cols-12 gap-4 px-6 py-4">
                  <div className="col-span-1 text-xs font-semibold text-slate-600 uppercase tracking-wider">序号</div>
                  <div className="col-span-3 text-xs font-semibold text-slate-600 uppercase tracking-wider">赛事名称</div>
                  <div className="col-span-2 text-xs font-semibold text-slate-600 uppercase tracking-wider">时间</div>
                  <div className="col-span-2 text-xs font-semibold text-slate-600 uppercase tracking-wider">场地</div>
                  <div className="col-span-2 text-xs font-semibold text-slate-600 uppercase tracking-wider">球队</div>
                  <div className="col-span-1 text-xs font-semibold text-slate-600 uppercase tracking-wider">得分</div>
                  <div className="col-span-1 text-xs font-semibold text-slate-600 uppercase tracking-wider">进攻</div>
                </div>
              </div>

              {/* 数据行 */}
              <div className="divide-y divide-slate-100">
                {filteredStats.map((stat) => (
                  <div
                    key={stat.id}
                    className="grid grid-cols-12 gap-4 px-6 py-4 hover:bg-slate-50 transition-colors group"
                  >
                    <div className="col-span-1 flex items-center">
                      <span className="text-slate-500 text-sm">{stat.id}</span>
                    </div>
                    <div className="col-span-3 flex items-center">
                      <span className="text-slate-900 text-sm">{stat.event}</span>
                    </div>
                    <div className="col-span-2 flex items-center">
                      <span className="text-slate-500 text-sm">{stat.time}</span>
                    </div>
                    <div className="col-span-2 flex items-center">
                      <span className="text-slate-500 text-sm">{stat.venue}</span>
                    </div>
                    <div className="col-span-2 flex items-center">
                      <div className="flex items-center gap-2">
                        <div className="w-1.5 h-1.5 rounded-full bg-orange-500"></div>
                        <span className="text-slate-900 text-sm font-medium">{stat.team}</span>
                      </div>
                    </div>
                    <div className="col-span-1 flex items-center">
                      <span className="text-orange-600 font-bold text-lg">{stat.score}</span>
                    </div>
                    <div className="col-span-1 flex items-center">
                      <span className="text-slate-500 text-sm">{stat.offense}</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>

            {/* 统计卡片 - 简约风格 */}
            <div className="grid grid-cols-3 gap-4 mt-6">
              <div className="bg-white rounded-lg p-6 border border-slate-200 shadow-sm">
                <div className="text-slate-500 text-sm mb-2">最高得分</div>
                <div className="text-4xl font-bold text-slate-900 mb-1">21</div>
                <div className="h-1 w-12 bg-orange-500 rounded-full"></div>
              </div>
              
              <div className="bg-white rounded-lg p-6 border border-slate-200 shadow-sm">
                <div className="text-slate-500 text-sm mb-2">比赛场次</div>
                <div className="text-4xl font-bold text-slate-900 mb-1">{filteredStats.length}</div>
                <div className="h-1 w-12 bg-orange-500 rounded-full"></div>
              </div>
              
              <div className="bg-white rounded-lg p-6 border border-slate-200 shadow-sm">
                <div className="text-slate-500 text-sm mb-2">参赛队伍</div>
                <div className="text-4xl font-bold text-slate-900 mb-1">4</div>
                <div className="h-1 w-12 bg-orange-500 rounded-full"></div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
