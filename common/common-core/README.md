# Common Core

## 功能

* 对象拷贝：该项目没有使用Spring自带的BeanUtils进行对象的深拷贝，而是利用效率更高的BeanCopier进行深拷贝
*See [BaseBeanCopier.java](https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/common/common-core/src/main/java/com/liyz/dubbo/common/core/cglib/BaseBeanCopier.java) on GitHub.*
*See [SimpleBeanCopier.java](https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/common/common-core/src/main/java/com/liyz/dubbo/common/core/cglib/SimpleBeanCopier.java) on GitHub.*
*See [PageBeanCopier.java](https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/common/common-core/src/main/java/com/liyz/dubbo/common/core/cglib/PageBeanCopier.java) on GitHub.*
  
* Dubbo过滤器，这里有两个dubbo过滤器
  1.dubbo业务异常过滤器
  2.dubbo认证过滤器：将api层的token解析出来的登陆信息往下传递