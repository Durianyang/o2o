package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.ProductCategory;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void batchInsert()
    {
        ProductCategory p1 = new ProductCategory();
        p1.setProductCategoryName("类别1");
        p1.setCreateTime(new Date());
        p1.setShopId(71L);
        p1.setPriority(1);
        ProductCategory p2 = new ProductCategory();
        p2.setProductCategoryName("类别2");
        p2.setCreateTime(new Date());
        p2.setShopId(71L);
        p2.setPriority(2);
        List<ProductCategory> list = new ArrayList<>(2);
        list.add(p1);
        list.add(p2);
        int i = productCategoryDao.batchInsert(list);
        System.out.println(i);

    }

    @Test
    public void deleteProductCategory()
    {
        int i = productCategoryDao.deleteProductCategory(16L, 71L);
        int i1 = productCategoryDao.deleteProductCategory(17L, 71L);
        System.out.println(i + "-" + i1);
    }
}
