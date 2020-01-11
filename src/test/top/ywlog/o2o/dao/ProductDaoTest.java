package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.Product;
import top.ywlog.o2o.entity.ProductCategory;
import top.ywlog.o2o.entity.ProductImg;
import top.ywlog.o2o.entity.Shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/30 15:49
 * Description:
 */
public class ProductDaoTest extends BaseTest
{
    @Autowired
    private ProductImgDao productImgDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void batchInsertImg()
    {
        ProductImg img1 = new ProductImg();
        img1.setImgAddr("/test/test1");
        img1.setImgDesc("测试图片1");
        img1.setPriority(1);
        img1.setCreateTime(new Date());
        img1.setProductId(10L);
        ProductImg img2 = new ProductImg();
        img2.setImgAddr("/test/test2");
        img2.setImgDesc("测试图片2");
        img2.setPriority(2);
        img2.setCreateTime(new Date());
        img2.setProductId(10L);
        List<ProductImg> list = new ArrayList<>(2);
        list.add(img1);
        list.add(img2);
        int i = productImgDao.batchInsertProductImg(list);
        System.out.println("i = " + i);
    }

    @Test
    public void insertProductTest()
    {
        Shop shop = new Shop();
        shop.setShopId(20L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(11L);
        Product product = new Product();
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品");
        product.setProductDesc("测试使用");
        product.setImgAddr("test/test1");
        product.setPriority(1);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        int i = productDao.insertProduct(product);
        System.out.println("i = " + i);
    }

    @Test
    public void getProductByIdTest()
    {
        Product product = productDao.getProductById(30L);
        System.out.println(product);
    }

    @Test
    public void updateProductTest()
    {
        Product product = productDao.getProductById(10L);
        product.setProductName("台湾珍珠奶茶");
        int i = productDao.updateProduct(product);
        System.out.println("i = " + i);
    }

    @Test
    public void deleteProductImgByProductIdTest()
    {
        int i = productImgDao.deleteProductImgByProductId(26L);
        System.out.println("i = " + i);
    }

    @Test
    public void listProductImgTest()
    {
        List<ProductImg> imgList = productImgDao.listProductImg(4L);
        System.out.println("imgList = " + imgList);
    }

    @Test
    public void listProductCountTest()
    {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        product.setShop(shop);
        int count = productDao.listProductCount(product);
        System.out.println("count = " + count);
    }

    @Test
    public void listProductTest()
    {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        product.setShop(shop);
        List<Product> productList = productDao.listProduct(product, 0, 5);
        for (Product product1 : productList)
        {
            System.out.println("product1 = " + product1);
        }
    }

    @Test
    public void deleteProductByIdTest()
    {
        int i = productDao.deleteProductById(31L);
        System.out.println("i = " + i);
    }

    @Test
    public void updateProductCategoryToNullTest()
    {
        int i = productDao.updateProductCategoryToNull(31L);
        System.out.println("i = " + i);
    }
}
