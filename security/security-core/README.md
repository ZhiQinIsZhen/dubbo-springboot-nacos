# Security Core

## 功能
* 定义了token生成以及校验的接口
* 定义了权限查询接口
* 增加了一个自定义注解：免登陆的注解（主要就是扫描controller，将mapping信息放入HttpSecurity.antMatchers().permitAll()）