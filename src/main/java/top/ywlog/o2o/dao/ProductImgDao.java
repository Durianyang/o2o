package top.ywlog.o2o.dao;

import top.ywlog.o2o.entity.ProductImg;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/30 15:38
 * Description: 商品图片Dao接口
 */
public interface ProductImgDao
{
    /**
     * 批量添加商品图片
     *
     * @param productImgList 待添加的图片
     * @return 添加图片数量
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
