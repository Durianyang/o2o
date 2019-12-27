package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.dto.ShopExecution;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.entity.PersonInfo;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.entity.ShopCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Author: Durian
 * Date: 2019/12/26 13:15
 * Description:
 */
public class ShopServiceTest extends BaseTest
{
    @Autowired
    private ShopService shopService;
    @Test
    public void addShopTest() throws FileNotFoundException
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
        shop.setPhone("123456");
        shop.setPriority(100);
        shop.setShopAddr("店铺service测试");
        shop.setShopDesc("店铺service测试");
        shop.setShopName("店铺service测试");
        File shopImg = new File("C:\\Users\\Durian\\Pictures\\Saved Pictures\\190337-1574852617ea0a.jpg");
        InputStream in = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, in, shopImg.getName());
        System.out.println(shopExecution.getStateInfo());
    }

    @Test
    public void updateShopTest() throws FileNotFoundException
    {
        Shop shop = shopService.getShopById(68L);
        shop.setShopName("茶颜悦色");
        File shopImg = new File("C:\\Users\\Durian\\Pictures\\Saved Pictures\\212516-15666531161ade.jpg");
        InputStream in = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.updateShop(shop, in, shopImg.getName());
        System.out.println(shopExecution.getStateInfo());
    }
}
