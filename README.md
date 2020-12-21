# 访客管理系统  visitor-manage

## 简介

访客管理系统，程序由管理员端，治安保卫部端（门卫），微信访客端，微信接洽人端。

## 项目结构

dubbo架构，分为接口定义3个子模块
1. `visitor-interface`：接口定义，里面存放实体类定义；
2. `visitor-provider`：实现类，或者说是生产者，存放service层代码
3. `visitor-consumer`：对外接口提供，或者说是消费者，存放controller层代码


## 登录账号密码

管理员账号为：`admin`，`admin`

治安保卫部账号为：`zhibao`，`zhibao`

更加详细的介绍请阅读访客管理系统简介.docx


## 关于系统部署

基本配置8G内存，双核心以上，需要启动`redis`和`zookeeper`服务，注意和程序的端口相匹配。
程序数据库在根目录下直接恢复即可，由于程序功能较为单一是一个综合服务的模块化的程序。
后续增加服务门户将系统链接。如果提示`fkglxt-interface.jar`找不到，
请先执行`fkglxt-interface`里面的`install`，然后在运行。
执行顺序为`provide`,`consumer`，
项目打包之后使用`java -jar`命令即可启动:

```
1. java -jar provider.jar
2. java -jar consumeer.jar
 ```


## 运行环境

```code
zookeeper-cli:2181
zookeeper-server:20001
dubbo:10001
tomcat:9090
```