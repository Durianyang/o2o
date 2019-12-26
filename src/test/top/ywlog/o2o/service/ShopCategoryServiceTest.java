package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/26 16:45
 * Description:
 */
public class ShopCategoryServiceTest extends BaseTest
{
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void list()
    {
        List<ShopCategory> list = shopCategoryService.list(new ShopCategory());
        for (ShopCategory shopCategory : list)
        {
            System.out.println(shopCategory.getShopCategoryName());
        }

    }
}
