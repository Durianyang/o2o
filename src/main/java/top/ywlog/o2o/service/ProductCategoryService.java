package top.ywlog.o2o.service;

import top.ywlog.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:20
 * Description:
 */
public interface ProductCategoryService
{
    /**
     * 列出相应商铺的商品分类列表
     *
     * @param shopId 商铺ID
     * @return 商品分类列表
     */
    List<ProductCategory> list(Long shopId);
}
