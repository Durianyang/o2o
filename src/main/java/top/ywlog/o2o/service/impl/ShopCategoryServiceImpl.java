package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ywlog.o2o.dao.ShopCategoryDao;
import top.ywlog.o2o.entity.ShopCategory;
import top.ywlog.o2o.service.ShopCategoryService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/26 16:43
 * Description:
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService
{
    private final ShopCategoryDao shopCategoryDao;
    @Autowired
    public ShopCategoryServiceImpl(ShopCategoryDao shopCategoryDao)
    {
        this.shopCategoryDao = shopCategoryDao;
    }

    @Override
    public List<ShopCategory> list(ShopCategory shopCategory)
    {
        return shopCategoryDao.list(shopCategory);
    }
}
