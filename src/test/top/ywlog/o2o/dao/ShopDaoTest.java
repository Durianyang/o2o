package top.ywlog.o2o.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.entity.PersonInfo;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.entity.ShopCategory;

import java.util.Date;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 21:14
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 只能加载dao这一个配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ShopDaoTest
{
    @Autowired
    private ShopDao shopDao;

    @Test
    public void insertShopTest()
    {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(11L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(13L);
        shop.setArea(area);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setAdvice("无");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setLastEditTime(new Date());
        shop.setPhone("123456");
        shop.setPriority(100);
        shop.setShopAddr("测试");
        shop.setShopDesc("测试");
        shop.setShopImg("image");
        shop.setShopName("测试店铺");
        int i = shopDao.insertShop(shop);
        System.out.println(i);
    }

    @Test
    public void updateShopTest()
    {
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(13L);
        area.setAreaId(3);
        shop.setShopId(29L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setAdvice("审核中");
        shop.setEnableStatus(1);
        shop.setLastEditTime(new Date());
        shop.setPhone("123456");
        shop.setPriority(100);
        shop.setShopAddr("修改测试");
        shop.setShopDesc("修改测试");
        shop.setShopImg("image");
        shop.setShopName("测试修改后的店铺");
        int i = shopDao.updateShop(shop);
        System.out.println(i);
    }

    @Test
    public void getShopTest()
    {
        Shop shop = shopDao.getShopById(68L);
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    public void shopCountTest()
    {
        Shop shop = new Shop();
        shop.setShopName("旧车");
        int count = shopDao.shopCount(shop);
        System.out.println("count = " + count);
    }

    @Test
    public void shopListPageTest()
    {
        Shop shop = new Shop();
        ShopCategory child = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(10L);
        child.setParent(parent);
        shop.setShopCategory(child);
        int count = shopDao.shopCount(shop);
        System.out.println(count);
        List<Shop> shopList = shopDao.listShopPage(shop, 0, 30);
        for (Shop shop1 : shopList)
        {
            System.out.println("shop1 = " + shop1);
        }

    }

}
