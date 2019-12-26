package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/26 16:30
 * Description:
 */
public class ShopCategoryDaoTest extends BaseTest
{
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void listTest()
    {
        List<ShopCategory> list = shopCategoryDao.list(null);
        for (ShopCategory shopCategory : list)
        {
            System.out.println(shopCategory.getShopCategoryName());
        }

        ShopCategory shopCategory1 = new ShopCategory();
        ShopCategory shopCategory2 = new ShopCategory();
        shopCategory2.setShopCategoryId(10L);
        shopCategory1.setParent(shopCategory2);
        List<ShopCategory> list1 = shopCategoryDao.list(shopCategory1);
        for (ShopCategory shopCategory : list1)
        {
            System.out.println(shopCategory.getShopCategoryName());
        }
    }
}
