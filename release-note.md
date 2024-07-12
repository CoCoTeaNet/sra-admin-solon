## 更新日志


#### 1.0.4
- 修复：文件上传无法自动创建目录
- 废弃：删除FileUploadUtils注释的代码


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