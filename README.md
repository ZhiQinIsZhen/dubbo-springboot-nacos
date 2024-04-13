# Dubbo Springboot2 Project

[![Build Status](https://img.shields.io/badge/Build-ZhiQinlsZhen-red)](https://github.com/ZhiQinIsZhen/spring-security-demo)
![Maven](https://img.shields.io/maven-central/v/org.apache.dubbo/dubbo.svg)
[![License](https://img.shields.io/badge/License-MIT-yellow)](https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/License)
![Springboot Version](https://img.shields.io/badge/Springboot-2.7.18-brightgreen)
![jjwt Version](https://img.shields.io/badge/jjwt-0.12.3-brightgreen)
![Dubbo Version](https://img.shields.io/badge/Dubbo-3.2.9-brightgreen)
![Netty Version](https://img.shields.io/badge/Netty-4.1.90.Final-brightgreen)
![Mybatis-plus Version](https://img.shields.io/badge/MybatisPlus-3.5.4.1-brightgreen)
![Swagger Version](https://img.shields.io/badge/knife4j-4.4.0-brightgreen)
![ElasticJob Version](https://img.shields.io/badge/ElasticJob-3.0.3-brightgreen)

这是一个[Apache Dubbo](https://cn.dubbo.apache.org/zh-cn/index.html)项目的脚手架，
基于[SpringBoot](https://spring.io/projects/spring-boot)、[Mybatis-plus](https://baomidou.com/)、
[Nacos](https://nacos.io/zh-cn/)、[Seata](https://seata.io/zh-cn/index.html)等框架。

## 项目结构

1. `dubbo-api`：网关层
2. `dubbo-common`：基础包的框架
3. `dubbo-dependencies-bom`：Jar版本管理父pom
4. `dubbo-service`：Dubbo的服务提供者

## api结构说明

1. `dubbo-api-admin`: 管理后台网关层，鉴权基于spring-security
    ```java
    @Api(tags = "员工权限信息")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 1, message = "失败")
    })
    @Slf4j
    @RestController
    @RequestMapping("/staff/authority")
    public class StaffAuthorityController {
    
        @DubboReference
        private RemoteStaffRoleService remoteStaffRoleService;
    
        @PreAuthorize("hasAuthority('DUBBO-API-ADMIN:STAFF-BIND-ROLE'.toUpperCase())")
        @ApiOperation("给员工绑定一个角色")
        @PostMapping("/bindRole")
        @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
                paramType = "header", defaultValue = "Bearer ")
        public Result<Boolean> bindRole(@RequestBody @Validated StaffRoleDTO staffRoleDTO) {
            AuthUserBO authUser = AuthContext.getAuthUser();
            staffRoleDTO.setStaffId(Objects.nonNull(staffRoleDTO.getStaffId()) ? staffRoleDTO.getStaffId() : authUser.getAuthId());
            remoteStaffRoleService.bindRole(BeanUtil.copyProperties(staffRoleDTO, StaffRoleBO.class));
            return Result.success(Boolean.TRUE);
        }
    }
    ```
2. `dubbo-api-user`: 客户前台网关层

## common结构说明

1. `dubbo-common-util`: 通用工具类框架
    >  + DateUtil: 日期工具类
    >  + IPUtil: IP地址获取工具类
    >  + JsonMapperUtil: Json转化工具类
    >  + PatternUtil: 正则表达式工具类
    >  + RandomUtil: 随机字符串或者数字生成工具
    >  + TraceIdUtil: Skywalking获取Traceid的工具类
    >  + TestUtil: 单元测试工具
    >  + @LyzJsonProperty: 作用与@JsonProperty效果一样，但是你可以再class LyzBeanSerializerModifier中增加序列化逻辑，动态控制
2. `dubbo-common-remote`: 通用Dubbo远程接口框架(包含了参数验证器:validation)
    > + RemoteServiceException: 业务异常的基类，每个服务自己的业务异常可以继承该接口
    > + IExceptionService: 业务异常错误枚举接口，每个服务自己的错误枚举可以继承该接口
    > + BasePageBO: 分页查询的基类
    > + RemotePage: 分页查询的返回类
3. `dubbo-common-dao-starter`: 通用DAO层的框架(基于Mybatis-plus)
    > + 自动装配类中，加入了分页插件，以及乐观锁插件，where条件校验
    > + 原数据Handler中装配了inset、update自动填充属性
    > + BaseDO: 该DO是数据库模型的基类，里面定义了模型中通用的字段
4. `dubbo-common-service`: 业务通用核心框架
    > + BeanUtil: 对象深拷贝，基于`BeanCopier`，有单个对象Object，数组对象List以及分页对象RemotePage
    > + Dubbo Filter: 针对服务提供者发生业务异常时，可以向服务调用者直接抛出该业务异常，而不需要包装
5. `dubbo-common-api-starter`: 通用web或者网关层框架
    > + GlobalControllerExceptionAdvice: 全局异常Advice
    > + 注解@Trim: 可以去除字符串前后的空格，该优先级比@validation参数校验注解高
    ```java
     @Getter
     @Setter
     public class TestDTO implements Serializable {
         private static final long serialVersionUID = 5512564697160213915L;
    
         @ApiModelProperty(value = "名字")
         @NotBlank(groups = {Hello.class}, message = "名字不能为空")
         @Length(min = 2, max = 10, groups = {Hello.class}, message = "请输入长度2到10长度的名字")
         @Trim
         private String name;
    
         @ApiModelProperty(value = "时间")
         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
         private Date time;
    
         public interface Hello {}
     }
    ```
    > + SwaggerConfig: Swagger配置的基础类，每个服务网关可以继承该类
    > + ErrorApiController: 错误重定向网关
    > + Result、PageResult: 网关结果返回包装类
6. `dubbo-common-desensitize`: 脱敏包
    > + Desensitization: 脱敏注解，基于Jackson框架
    ```java
        @Getter
        @Setter
        public class TestVO implements Serializable {
            private static final long serialVersionUID = -8814136605692691847L;
        
            @ApiModelProperty(value = "名字")
            @Desensitization(value = DesensitizationType.REAL_NAME)
            private String name;
        
            @ApiModelProperty(value = "时间")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date time;
        }
    ```
    ```json
        {
          "code": "0",
          "message": "成功",
          "data": {
            "name": "至*秦",
            "time": "2023-06-13 00:59:59"
          }
        }
    ```
    > + DesensitizationType: 现在实现了名字、手机号码、邮箱地址、身份证号码以及自定义的脱敏规则
    ```java
        @AllArgsConstructor
        public enum DesensitizationType {
            DEFAULT("默认"),
            MOBILE("手机号码"),
            EMAIL("邮箱地址"),
            REAL_NAME("真实姓名"),
            CARD_NO("身份证号码"),
            SELF_DEFINITION("自定义"),
            IGNORE("忽略"),
            ENCRYPT_DECRYPT("加密"),
            DFA("DFA算法"),
            ;
        
            @Getter
            private final String desc;
        }
    ```
7. `dubbo-common-limit-starter`: 限流包
    > + Limits、Limit: 限流注解
    ```java
        @Limits({@Limit(type = LimitType.IP, count = 2), @Limit(count = 1)})
        @ApiOperation("你好")
        @GetMapping("/hello")
        public Result<TestVO> hello(@Validated(TestDTO.Hello.class) TestDTO testDTO) {
            return Result.success(BeanUtil.copyProperties(testDTO, TestVO::new));
        }
    ```
    > + LimitType: 限流规则 -> IP、Mapping、自定义等
    ```java
        @AllArgsConstructor
        public enum LimitType {
            IP("IP地址"),
            IP_MAPPING("IP地址+API Mapping级"),
            TOTAL("全局总的次数"),
            MAPPING("API Mapping级"),
            USER_MAPPING("用户+API Mapping级"),
            CUSTOMIZATION("自定义"),
            ;
        
            @Getter
            private final String desc;
        }
    ```
8. `dubbo-security-client-starter`: security-client，适用于各个网关服务中
    > + AuthExceptionHandleAdvice: 认证网关的Advice
    > + Anonymous: 匿名访问注解
    > + AuthSecurityClientAutoConfig: Security的配置类
    > + JwtAuthenticationTokenFilter: JWT认证的过滤器
    > + UserDetailsServiceImpl: Security的用户获取接口(UserDetailsService)的实现类
9. `dubbo-common-cacge-starter`: 缓存，基于spring-cache和redisson
    ```java
        @Override
        @Cacheable(cacheNames = {"userInfo"}, key = "'email:' + #username", unless = "#result == null")
        public UserAuthBaseDO getByUsername(String username) {
            return getOne(Wrappers.lambdaQuery(UserAuthEmailDO.builder().email(username).build()));
        }
    
        @Override
        @CacheEvict(cacheNames = {"userInfo"}, key = "'email:' + #entity.email")
        public boolean save(UserAuthEmailDO entity) {
            return super.save(entity);
        }
    
        @Override
        @Cacheable(cacheNames = {"userInfo"}, key = "'email:id:' + #id", unless = "#result == null")
        public UserAuthEmailDO getById(Serializable id) {
            return super.getById(id);
        }
    ```
   
## service结构说明
1. `dubbo-service-auth`: 认证资源服务，基于spring-security以及jwt
2. `dubbo-service-staff`: 员工信息服务
3. `dubbo-service-user`: 客户信息服务
4. `dubbo-service-job`: 定时任务服务：基于Elastic-Job
5. `dubbo-service-monitor`: Springboot监控monitor

## 如需之前版本，请关注tag标签，重新拉取tag代码

## 开源共建
1. 如有问题可以提交[issue](https://github.com/ZhiQinIsZhen/dubbo-springboot-project/issues)
2. 如有需要Spring Cloud，请点击[Spring Cloud](https://github.com/ZhiQinIsZhen/springcloud-demo)
3. 如有需要Springboot3，请点击[Dubbo-Springboot3](https://github.com/ZhiQinIsZhen/dubbo-springboot3)

## License
Dubbo Springboot2 Project is under the MIT License.