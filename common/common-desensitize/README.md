# Common Desensitize

## 功能

* 自定义脱敏注解 [Desensitization.java](https://github.com/ZhiQinIsZhen/dubbo-springboot-nacos/blob/master/common/common-desensitize/src/main/java/com/liyz/dubbo/common/desensitize/annotation/Desensitization.java)
  目前支持Json框架：fastJson、jackson
  并且会结合 common-dao，设置在数据库查询时，进行对应的脱敏操作
  支持脱敏类型：姓名、身份证、手机号码、邮箱、加解密、自定义脱敏以及忽略该字段