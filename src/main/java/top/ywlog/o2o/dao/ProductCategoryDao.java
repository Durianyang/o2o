package top.ywlog.o2o.dao;

import top.ywlog.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:02
 * Description: 商品分类管理
 */
public interface ProductCategoryDao
{
    /**
     * 列出相应商铺的商品分类列表
     *
     * @param shopId 商铺ID
     * @return 商品分类列表
     */
    List<ProductCategory> list(Long shopId);
}
