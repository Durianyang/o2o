package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.ywlog.o2o.dao.ShopDao;
import top.ywlog.o2o.dto.ShopExecution;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.enums.ShopStateEnum;
import top.ywlog.o2o.exceptions.ShopOperationException;
import top.ywlog.o2o.service.ShopService;
import top.ywlog.o2o.util.ImageUtil;
import top.ywlog.o2o.util.PathUtil;

import java.io.InputStream;
import java.util.Date;

/**
 * Author: Durian
 * Date: 2019/12/26 12:44
 * Description:
 */
@Service
public class ShopServiceImpl implements ShopService
{
    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao)
    {
        this.shopDao = shopDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException
    {
        int effectedNum = 0;
        // 还可以对店铺的区域等信息判断
        if (shop == null)
        {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try
        {
            // 设置店铺状态
            shop.setCreateTime(new Date());
            // 设置创建时间
            shop.setLastEditTime(new Date());
            shop.setEnableStatus(0);
            // shop信息插入数据库中
            effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0)
            {
                throw new ShopOperationException("店铺添加失败！");
            } else
            {
                if (shopImgInputStream != null)
                {
                    try
                    {
                        // 存储图片
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e)
                    {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    // 更新店铺图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0)
                    {
                        throw new ShopOperationException("更新店铺图片地址失败！");
                    }
                }
            }
        } catch (Exception e)
        {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        // 操作成功返回调用的构造器
        ShopExecution shopExecution = new ShopExecution(ShopStateEnum.CHECK, shop);
        shopExecution.setCount(effectedNum);
        return shopExecution;
    }

    /**
     * 存储店铺图片
     * @param shop 店铺信息
     * @param shopImgInputStream 店铺图片
     */
    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName)
    {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, dest, fileName);
        shop.setShopImg(shopImgAddr);
    }
}
