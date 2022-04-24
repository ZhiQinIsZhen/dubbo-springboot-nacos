# Security Server

## 功能

* security server部署成单独服务，这样会让token秘钥不被泄露（打成jar形式）
* 使用了dubbo的GenericService，这样会使调用不同group服务变成动态配置化，而不是写死