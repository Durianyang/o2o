package top.ywlog.o2o.dao;

import top.ywlog.o2o.entity.Shop;

/**
 * Author: Durian
 * Date: 2019/12/25 20:56
 * Description: 店铺dao
 */
public interface ShopDao
{
    /**
     * 新增店铺
     *
     * @param shop 新增店铺信息
     * @return int 插入影响行数
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop 更新信息
     * @return int 更新影响行数
     */
    int updateShop(Shop shop);
}
