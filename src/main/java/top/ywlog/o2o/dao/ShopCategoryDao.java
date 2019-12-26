package top.ywlog.o2o.dao;

import org.apache.ibatis.annotations.Param;
import top.ywlog.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/26 16:25
 * Description:
 */
public interface ShopCategoryDao
{
    List<ShopCategory> list(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
