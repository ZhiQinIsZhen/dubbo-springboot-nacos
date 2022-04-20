Dubbo-Springboot-Nacos-Seata
=========================================

[![Build Status](https://img.shields.io/badge/Build-ZhiQinlsZhen-brightgreen)](https://github.com/ZhiQinIsZhen/dubbo-springboot-project)
![Maven](https://img.shields.io/maven-central/v/org.apache.dubbo/dubbo.svg)
[![Dubbo](https://img.shields.io/badge/Dubbo-3.0.7-brightgreen)](http://dubbo.apache.org/zh-cn/index.html)
![Spring-boot](https://img.shields.io/badge/Springboot-2.6.5.RELEASE-brightgreen)
[![Mybatis](https://img.shields.io/badge/Mybatis-3.5.1-brightgreen)](https://mybatis.org/mybatis-3/zh/getting-started.html)
[![Seata](https://img.shields.io/badge/Seata-1.4.2-brightgreen)](https://seata.io/zh-cn/)

这是一个Apache Dubbo项目，基于SpringBoot、Mybatis、Nacos、Seata等框架。

- 主框架基于：Apache Dubbo、SpringBoot、Mybatis-plus、Nacos、Seata、Zookeeper、ElasticSearch、Kafka
- 登陆安全基于：Spring-security、jwt、redisson
- 接口文档基于：Swagger-knife4j
- 限流基于：Google-guava
- 分布式定时任务基于：Elastic-job（注解式）
- 推送聊天基于：Netty
- 分库分表读写分离基于：Sharding-jdbc


#### 核心依赖 

依赖 | 版本
--- | ---
Spring Boot |   2.6.5
Spring security | 2.3.3.RELEASE
Dubbo |  2.7.8
Seata | 1.3.0
Nacos | 1.3.1
Mybatis | 2.1.2
Zookeeper Curator | 5.1.0
Redisson |  3.13.0
Elastic Job | 3.0.0
Swagger Knife4j | 2.0.3


### 目录结构说明
```lua
liyz
├── common-parent -- 它是整个项目的父pom文件，里面主要包含了Springboot框架的版本，和一些常用工具包，方便以后升级版本
└── common-base -- 基本包，也是整个项目的基本包。里面主要增加了一个Dubbo框架的处理
	└── @Desensitization -- 自定义脱敏，这个自定义脱敏是基于fastjson，用法如下 注：我将springboot默认的jsckson序列化方式修改成了fastjson
	
	```java
	@Desensitization(endIndex = 3)
	private String loginName;

	private String nickName;

	@Desensitization(DesensitizationType.REAL_NAME)
	private String userName;

	@Desensitization(DesensitizationType.MOBILE)
	private String mobile;

	@Desensitization(DesensitizationType.EMAIL)
	private String email;
	```
	
├── common-dao -- 服务中dao层需要引用，定义了通用mapper的顶层接口（mapper）、service层的顶层接口以及抽象类，所有大家对于单表操作不再需要维护一个*Mapper.xml文件了，当然了我在这里也提倡大家尽量单表操作，将多表关系在业务层来实现
└── common-security -- Springboot的安全配置
	└── @Anonymous -- 加在方法或者类上，代表这个方法或者该类下所有的mapping方法可以免鉴权访问，否则所有的api必须登录后写到token来访问
└── ommon-controller -- 是api controller服务需要引用的
	└── @Limits、@Limit -- 限流注解，可以对IP、mapping、以及总调用次数进行限流
└── common-transaction -- 分布式事务，可以解决在分布式环境下最头痛的事务问题，采用阿里开源的Seata
├── common-export -- 自定义注解导出，只需要在实体类的field加上注解（Export即可），并且暂时只有csv的导出，如果需要excel的导出，大家可以自行添加
├── common-job -- 分布式定时任务，基于当当的 elastic job，当中有自定义注解：ElasticJob
├── service-member -- 用户服务，可以自己扩展
├── service-socket -- 是一个基于netty的一个实时推送服务，当中的登陆依赖了 service-member，如果有认证中心或者需要修改认证的地方，可以自行修改
├── service-job -- 分布式定时任务调度中心，具体的业务代码写在各个服务中，这个项目只用来触发，通过dubbo来远程调用
├── service-customer -- 后台管理用户或者open api服务的调用者，提供了url级别的授权访问
├── api-open -- 管理后台网关或者open api网关
├── api-web -- 对外统一的api出口，当然了大家也可以在每个服务对外开放api，看情况而定
└── service-file -- 文件上传下载服务

```

#### 开源共建
1.如有问题可以提交[issue](https://github.com/ZhiQinIsZhen/dubbo-springboot-project/issues)

2.如有需要Spring Cloud，请点击[Spring Cloud](https://github.com/ZhiQinIsZhen/springcloud-demo)
