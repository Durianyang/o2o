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
店铺注册开发过程：
1. 前端页面使用SUI Mobile，在填写店铺信息时，需要动态向后台获取店铺分类信息和区域信息供选择
2. 编写ShopDao类，添加insertShop和updateShop方法，在mapper文件中编写对应的SQL语句
3. ShopCategoryDao类，添加list()方法，查询所有可选子分类或者某个父类下的子分类
4. 编写对应的service接口和实现类
5. 在ShopServiceImpl中，需要设置店铺的某些初始化值，并根据插入SQL放回的主键值处理店铺缩略图，将返回的图片路径利用updateShop更新到数据库中
6. 编写Shop的controller类，添加getInitInfo和registerShop方法，前端动态获取getInitInfo返回的分类和区域信息，并通过AJAX将填写的店铺信息传入register方法中。
7. 在register方法中判断验证码，校验参数合法性成功后，调用ShopServiceImpl的addShop方法

**kaptcha**的验证插件使用
1. 导入jar包
````xml
<dependency>
      <groupId>com.github.penggle</groupId>
      <artifactId>kaptcha</artifactId>
      <version>2.3.2</version>
</dependency>
````
2. 在web.xml中配置kaptcha的参数
````xml
<servlet>
    <servlet-name>kaptcha</servlet-name>
    <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>
    <!--是否有边框-->
    <init-param>
      <param-name>kaptcha.border</param-name>
      <param-value>no</param-value>
    </init-param>
    <!--字体颜色-->
    <init-param>
      <param-name>kaptcha.textproducer.font.color</param-name>
      <param-value>red</param-value>
    </init-param>
    <!--使用哪些字符作为验证码-->
    <init-param>
      <param-name>kaptcha.textproducer.char.string</param-name>
      <param-value>ACDEFHKPRTWX345679</param-value>
    </init-param>
    <!--图片宽度-->
    <init-param>
      <param-name>kaptcha.image.width</param-name>
      <param-value>135</param-value>
    </init-param>
    <!--图片宽度-->
    <init-param>
      <param-name>kaptcha.image.height</param-name>
      <param-value>50</param-value>
    </init-param>
    <!--字体大小-->
    <init-param>
      <param-name>kaptcha.textproducer.font.size</param-name>
      <param-value>43</param-value>
    </init-param>
    <!--干扰线颜色-->
    <init-param>
      <param-name>kaptcha.noise.color</param-name>
      <param-value>black</param-value>
    </init-param>
    <!--字符个数-->
    <init-param>
      <param-name>kaptcha.textproducer.char.length</param-name>
      <param-value>4</param-value>
    </init-param>
    <!--字体-->
    <init-param>
      <param-name>kaptcha.textproducer.font.names</param-name>
      <param-value>Arial</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>kaptcha</servlet-name>
    <url-pattern>/kaptcha</url-pattern>
  </servlet-mapping>
````
3. 在前端页面的验证码图片标签设置"src='../kaptcha'",将src指向kaptcha Servlet的路径
4. 配置文件上传解析器，使验证码的值和输入的参数能保存在session中
````xml
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="utf-8"/>
        <!--1024*1024*20-->
        <property name="maxUploadSize" value="20971520"/>
        <property name="maxInMemorySize" value="20971520"/>
    </bean>
````
5. 后台判断验证码正确性
`````java
String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
String verifyCodeInput = HttpServletRequestUtil.getString(request, "verifyCodeInput");
`````
##### 店铺修改
#### 店铺列表展示
#### 店铺管理
#### 店铺商品类别管理
##### 商品类别添加
##### 商品类别删除
> 删除商品类别时需要先将该类别下的所有商品的分类ID置为空


问题：商品详情图显示的缩略图

#### 十一章
加密敏感信息
