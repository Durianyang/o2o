package top.ywlog.o2o.service;

import top.ywlog.o2o.dto.ShopExecution;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

/**
 * Author: Durian
 * Date: 2019/12/26 12:43
 * Description: 店铺操作接口
 */
public interface ShopService
{
    /**
     * 添加店铺
     *
     * @param shop               添加的店铺信息
     * @param shopImgInputStream 添加的店铺图片
     * @param fileName           图片文件名称
     * @return top.ywlog.o2o.dto.ShopExecution
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 根据店铺ID查询店铺信息
     *
     * @param shopId 店铺ID
     * @return 店铺信息
     */
    Shop getShopById(Long shopId);

    /**
     * 更新店铺信息
     *
     * @param shop               更新的店铺信息
     * @param shopImgInputStream 更新的店铺图片
     * @param fileName           图片文件名称
     * @return top.ywlog.o2o.dto.ShopExecution
     */
    ShopExecution updateShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
