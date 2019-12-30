package top.ywlog.o2o.service;

import top.ywlog.o2o.dto.ImageHolder;
import top.ywlog.o2o.dto.ProductExecution;
import top.ywlog.o2o.entity.Product;
import top.ywlog.o2o.exceptions.ProductOperationException;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/30 16:06
 * Description:
 */
public interface ProductService
{
    /**
     * @param product            商品信息
     * @param thumbnail          缩略图
     * @param productImgList     详情图
     * @return top.ywlog.o2o.dto.ProductExecution
     * @throws ProductOperationException 商品操作异常
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> productImgList) throws ProductOperationException;
}
