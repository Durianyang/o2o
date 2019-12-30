package top.ywlog.o2o.dao;

import top.ywlog.o2o.entity.Product;

/**
 * Author: Durian
 * Date: 2019/12/30 15:37
 * Description: 商品类dao接口
 */
public interface ProductDao
{
    /**
     * 新增商品
     *
     * @param product 待添加的商品
     * @return 影响记录数
     */
    int insertProduct(Product product);


}
