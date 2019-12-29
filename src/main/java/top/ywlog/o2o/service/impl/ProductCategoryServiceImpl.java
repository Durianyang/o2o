package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ywlog.o2o.dao.ProductCategoryDao;
import top.ywlog.o2o.entity.ProductCategory;
import top.ywlog.o2o.service.ProductCategoryService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:21
 * Description:
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService
{
    private final ProductCategoryDao productCategoryDao;
    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryDao productCategoryDao)
    {
        this.productCategoryDao = productCategoryDao;
    }

    @Override
    public List<ProductCategory> list(Long shopId)
    {
        return productCategoryDao.list(shopId);
    }
}
