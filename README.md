
# TODO

* Jenkins 持续集成
* Nginx 服务器
* [ ] Docker 容器
* [ ] Nginx


# 模块说明

- common 公共模块
- system 基础服务支持
- user 用户管理/后台管理员
- risk 风控



# 模块内容
        
    贷超
        基础模块
            管理员管理
            权限管理
                角色管理
                资源管理
            短信平台
                云片
                XXX
            文件存储
                阿里云OSS
        渠道商
            渠道商录入
            渠道商流量统计
                埋点统计
                    按天/统计存储
                    按三天/星期
                    按月/季度
            防爬
                APP
                    RSA
                    HTTPS
                    反抓包
                    客户端标识
                        UDID Android
                        IDFA iOS
                H5
                    RSA
                    HTTPS
                    代码压缩
                    代码混淆
                    Referer 检查
            APP
                埋点上报
                排序规则
                APP 没有登录的
        甲方
            甲方信息录入
            流量统计