## 介绍
SraAdmin-Solon 是一个前后端分离的脚手架项目，国产化核心框架，实现了用户、字典、角色、权限等常见功能，
能够快速搭建一个web项目。  
后端技术栈：solon+sqltoy+satoken+hutool  
前端技术栈：vue3+vue-router+typescript+elementui


## 应用场景
- XXX网站 | 博客网站
- 编程学习 | 毕业设计
- XXX管理系统


## 其它仓库地址
- SraAdmin-Vue（前端项目，基于VUE开发）：https://github.com/CoCoTeaNet/sra-admin-vue


## 图片演示
<table>
    <tr>
        <td><img src="./doc/imgs/ys_21.jpg" alt="ys_1"/></td>
        <td><img src="./doc/imgs/ys_22.png" alt="ys_2"/></td>
    </tr>
    <tr>
        <td><img src="./doc/imgs/ys_23.jpg" alt="ys_3"/></td>
        <td><img src="./doc/imgs/ys_24.jpg" alt="ys_4"/></td>
    </tr>
</table>


## 软件架构
[![image.png](https://i.postimg.cc/Bn7TLCXv/image.png)](https://i.postimg.cc/Bn7TLCXv/image.png)


## 启动说明
### 步骤
1. 运行数据库脚本：表结构、初始化数据
2. 启动本地Redis并运行后端服务
3. 安装前端依赖并运行前端项目

### 接口文档
使用命令或者idea的插件生成，具体使用参考地址：https://smart-doc-group.github.io/#/zh-cn/start/quickstart

### 备注
```text
1. 配置文件：src/main/resources/app.yml
2. 数据库执行脚本：/doc/sra-admin-sql/*.sql
3. 项目启动类：src/main/java/net/cocotea/admin/Launcher.java
```

### 访问地址
测试后端接口：http://localhost:9000/test/index

## 项目结构
```
├─doc
│  ├─bin                服务运行脚本
│  ├─imgs               演示图片
│  └─sra-admin-sql      数据库运行脚本 
│      ├─table_ddl.sql  表结构
│      └─table_data.sql 初始化数据
└─sra-admin-service     
    ├─sra-common        通用模块
    └─sra-web           web模块
```


## 核心技术栈
- [Solon](https://gitee.com/opensolon/solon) ：Java “纯血国产”应用开发框架：更快、更小、更简单！！！
- [Sqltoy](https://gitee.com/sagacity/sagacity-sqltoy) ：java真正最强大的ORM框架，支持市场上流行数据库。
- [Satoken](https://sa-token.cc/doc.html#/) ：一个轻量级 Java 权限认证框架，让鉴权变得简单、优雅！
- [Hutool](https://www.hutool.cn/) ：小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。


## 软件版本要求
- JDK：17+
- Nodejs：14.0 ~ 19
- MySQL：8.0+


## 参与贡献
1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

- 如有更好的想法，欢迎提[issue](https://github.com/CoCoTeaNet/sra-admin-solon/issues)


## SraAdmin交流群
QQ群：[![加入QQ群](https://img.shields.io/badge/-543112505-brightgreen)](https://jq.qq.com/?_wv=1027&k=lxODRWpq)


## 感谢支持
- [JetBrain Idea](https://jb.gg/OpenSourceSupport)  
![jetbrains](./doc/imgs/jb_beam.svg)
