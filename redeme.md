### o2o开发

技术栈：JavaSe， JavaEE， Mysql， HTML， CSS， JS， easyUI， SSM ， logback

开发环境：windows10, Java8, idea2019, mysql5.7, tomcat8.5.47



#### 一、系统功能模块划分
- 前端展示系统

    - 头条展示
    - 店铺类别展示
    - 区域展示
    - 店铺
    - 商品
    
- 店家系统

    - 本地账号维护
    - 微信账号维护
    - 店铺信息维护
    - 权限验证
    - 商品类别维护
    
- 管理系统

    - 头条信息维护
    - 店铺类别信息维护
    - 区域信息维护
    - 权限验证
    - 店铺管理
    - 用户管理
    
#### 实体类设计
（优化：定义一个创建时间和更新时间的父类）
- top.ywlog.o2o.entity.Area 区域类
- top.ywlog.o2o.entity.PersonInfo 用户信息类
- top.ywlog.o2o.entity.LocalAuth 本地账号类 数据库表中local_auth和wechat_auth通过personInfo的userId关联
- top.ywlog.o2o.entity.WechatAuth 微信账号类
- top.ywlog.o2o.entity.HeadLine 头条信息展示
- top.ywlog.o2o.entity.ShopCategory 店铺分类
- top.ywlog.o2o.entity.Shop 店铺
- top.ywlog.o2o.entity.Product 商品
- top.ywlog.o2o.entity.ProductCategory 商品 分类
- top.ywlog.o2o.entity.ProductImg 商品详情图片

#### 店铺系统开发
##### 店铺注册

