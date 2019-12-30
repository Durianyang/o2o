package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.ywlog.o2o.dao.ProductCategoryDao;
import top.ywlog.o2o.dto.ProductCategoryExecution;
import top.ywlog.o2o.entity.ProductCategory;
import top.ywlog.o2o.enums.ProductCategoryEnum;
import top.ywlog.o2o.exceptions.ProductCategoryException;
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductCategory> list(Long shopId)
    {
        return productCategoryDao.list(shopId);
    }

    @Override
    public ProductCategoryExecution batchInsert(List<ProductCategory> productCategories) throws ProductCategoryException
    {
        if (productCategories != null && productCategories.size() > 0)
        {
            try
            {
                int count = productCategoryDao.batchInsert(productCategories);
                if (count <= 0)
                {
                    return new ProductCategoryExecution(ProductCategoryEnum.INNER_ERROR);
                } else
                {
                    return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS, productCategories, count);
                }
            } catch (Exception e)
            {
                throw new ProductCategoryException("batchInsert ProductCategory Error: " + e.getMessage());
            }
        } else
        {
            return new ProductCategoryExecution(ProductCategoryEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId) throws ProductCategoryException
    {
        // 首先将该分类下所有商品的分类ID置为空
        try
        {
            int count = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (count <= 0)
            {
                throw new ProductCategoryException("商品类别删除失败！");
            } else
            {
                return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS, count);
            }
        } catch (ProductCategoryException e)
        {
            throw new ProductCategoryException("delete productCategory Error: " + e.getMessage());
        }
    }
}
