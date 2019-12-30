package top.ywlog.o2o.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.dto.ProductCategoryExecution;
import top.ywlog.o2o.entity.ProductCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:22
 * Description:
 */
// 按方法名称排序执行
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        ProductCategoryExecution productCategoryExecution = productCategoryService.batchInsert(list);
        List<ProductCategory> categoryList = productCategoryExecution.getProductCategoryList();
        for (ProductCategory productCategory : categoryList)
        {
            System.out.println("productCategory = " + productCategory);
        }
    }
}
