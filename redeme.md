### o2o开发
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
- top.ywlog.o2o.entity.Area
- top.ywlog.o2o.entity.PersonInfo
- top.ywlog.o2o.entity.LocalAuth 数据库表中local_auth和wechat_auth通过personInfo的userId关联
- top.ywlog.o2o.entity.WechatAuth 
- top.ywlog.o2o.entity.HeadLine
- top.ywlog.o2o.entity.ShopCategory
- top.ywlog.o2o.entity.Shop
- top.ywlog.o2o.entity.Product
- top.ywlog.o2o.entity.ProductCategory
- top.ywlog.o2o.entity.ProductImg