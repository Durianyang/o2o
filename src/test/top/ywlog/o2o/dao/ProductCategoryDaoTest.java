package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:11
 * Description:
 */
public class ProductCategoryDaoTest extends BaseTest
{
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void listTest()
    {
        List<ProductCategory> list = productCategoryDao.list(20L);
        for (ProductCategory productCategory : list)
        {
            System.out.println("productCategory = " + productCategory);
        }
    }
}
