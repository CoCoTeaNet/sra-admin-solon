## 更新日志


#### 1.1.4
- 变更 Solon升级到3.0.9
- 变更 sqltoy升级到5.6.40
- 变更 hutool升级到5.8.35


#### 1.1.3
- 变更 quickvo-maven-plugin升级到1.0.4
- 优化 本地quickvo.xml模板，增加自定义注释生成
- 修复 使用强密码时校验异常问题


#### 1.1.2
- 新增 项目启动时打印banner内容
- 变更 smart-doc插件升级到3.0.9


#### 1.1.1
- 新增 code-generator模块（Maven插件生成实体类）


#### 1.1.0
- 变更 Solon框架升级到 >> 3.0.2
- 变更 sqltoy框架升级到 >> 5.6.22
- 变更 hutool框架升级到 >> 5.8.32
- 兼容 Solon3.x, 引入solon-security-validation模块
- 修复 500状态码时默认响应“ERROR”消息提示问题


#### 1.0.6
- 修复：登录日志没保存到用户ID问题


#### 1.0.5
- 新增：ApiPage模型增加create方法创建sqltoy的Page对象
- 新增：ApiPageDTO基础分页模型
- 优化：系统操作日志列表接口-屏蔽多余参数
- 优化：获取字典树形列表接口-屏蔽多余参数
- 优化：系统文件管理分页接口-屏蔽多余参数
- 优化：系统菜单相关列表接口-屏蔽多余参数
- 优化：系统角色管理列表接口-屏蔽多余参数
- 优化：系统用户管理列表接口-屏蔽多余参数
- 优化：系统版本管理列表接口-屏蔽多余参数
- 优化：补充系统操作日志列表接口VO字段注释
- 优化：减少在线用户续期IO
- 修复：数据库表结构ddl脚本默认时间有误问题
- 修复：系统用户管理列表接口-邮箱查询失效问题
- 修复：排除snack3框架，避免产生不可控问题（比如加了debug: true配置项导致序列化失败）


#### 1.0.4
- 新增：系统日志添加‘接口路径’保存字段
- 新增：系统日志添加@LogPersistence注解拦截存储
- 修复：文件上传无法自动创建目录
- 优化：创建Logger实例增加static关键词
- 废弃：删除FileUploadUtils注释的代码
- 变更：初始化脚本变成ddl+data两个文件（PS：table_ddl.sql是表结构）


#### 1.0.3
1. 修复：私钥缓存失效问题
2. 修复：序列化未生效问题
3. 修复：无法使用Redis缓存token问题
4. 新增：集成smart-doc插件


#### 1.0.2
1. 优化：验证码缓存键从ip改成用UUID标识
2. 优化：登陆成功删除验证码缓存
3. 变更：验证码接口方法post改成get
4. 新增：密码参数使用sm2加密传输


#### 1.0.1
1. 同步sra-admin功能修复


#### 1.0.0
1. 核心WEB开发框架切换到Solon