package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:22
 * Description:
 */
public class ProductCategoryServiceTest extends BaseTest
{
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void listTest()
    {
        List<ProductCategory> list = productCategoryService.list(20L);
        for (ProductCategory productCategory : list)
        {
            System.out.println("productCategory = " + productCategory);
        }
    }
}
