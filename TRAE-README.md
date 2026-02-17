# 🏀 Trae开发指南

## 📋 项目状态交接

### 当前分支
- **分支名**: `trae-development`
- **基于**: `main` 分支的最新状态
- **提交**: `20ea551` - backup: 2026-02-16-1508

### 已完成工作 ✅
- 比赛技术统计页面UI美化
- 球队排行榜页面UI美化  
- 比赛成绩页面UI美化
- 前后台项目启动配置
- 完整的UI改造方案文档

### 待完成任务 🔄
- 15+个页面需要UI美化
- 侧边栏统一改造（深色→亮色，黄色→橙色）
- 核心管理页面美化
- 比赛相关页面美化
- 内容管理页面美化

## 🚀 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/w3118597378-max/project.git
cd project
git checkout trae-development
```

### 2. 环境要求
- Node.js 16+
- Java 11+
- MySQL 8.0+
- npm 或 yarn

### 3. 安装依赖
```bash
# 前台项目
cd client_code
npm install

# 后台管理
cd ../manage_code  
npm install

# 后端项目
cd ../server_code
mvn clean install
```

### 4. 启动项目
```bash
# 启动前台 (端口8082)
cd client_code && npm run serve

# 启动后台管理 (端口8081)  
cd manage_code && npm run serve

# 启动后端服务 (端口8080)
cd server_code && mvn spring-boot:run
```

## 📁 项目结构
```
project/
├── client_code/          # 前台Vue项目
├── manage_code/          # 后台管理Vue项目
├── server_code/          # SpringBoot后端
├── 篮球比赛管理系统Vue改造方案.md  # 详细改造方案
├── WINDSURF-GUIDE.md     # UI实施指南
├── 全站UI美化实施方案.md  # 完整实施方案
└── TRAE-README.md        # 本文件
```

## 🎨 UI改造指南

### 设计目标
- **风格**: 简约活力亮色风格
- **主色**: 白色背景 + 橙色强调
- **布局**: 统一卡片化设计

### 改造顺序
1. **侧边栏改造** (最重要)
2. **核心管理页面** (首页、球队管理、裁判员管理、赛事管理)
3. **比赛相关页面** (报名管理、参赛学生、竞赛日程)
4. **内容管理页面** (论坛管理、轮播图管理)
5. **申请类页面** (各类申请管理)

### 关键配置
- **前台端口**: 8082
- **后台端口**: 8081
- **后端端口**: 8080

## 📚 重要文档

1. **篮球比赛管理系统Vue改造方案.md** - 完整改造方案和实施顺序
2. **WINDSURF-GUIDE.md** - UI设计规范和实施指南  
3. **全站UI美化实施方案.md** - 详细的技术实施方案

## 🔧 开发规范

### 代码规范
- 遵循Vue 3最佳实践
- 使用TailwindCSS类名
- 保持组件复用性

### Git工作流
```bash
# 每完成2-3个页面提交一次
git add .
git commit -m "feat: 美化XXX页面UI"
git push origin trae-development
```

### 测试要求
- 每个页面功能正常
- 响应式布局适配
- 浏览器兼容性检查

## 🆘 遇到问题？

1. 查看改造方案文档
2. 检查WINDSURF-GUIDE.md
3. 参考已完成的页面代码
4. 保持与设计文档一致

## 💡 建议

- 先从侧边栏改造开始，影响所有页面
- 每完成一个模块及时提交
- 保持与现有设计风格一致
- 遇到问题及时沟通

---

**Good luck! 🎯**
