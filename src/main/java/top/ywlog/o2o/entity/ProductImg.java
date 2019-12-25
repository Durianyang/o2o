package top.ywlog.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2019/12/25 13:27
 * Description: 商品图片
 */
@Data
public class ProductImg
{
    /** 图片Id */
    private Long productImgId;
    /** 图片地址 */
    private String imgAddr;
    /** 描述 */
    private String imgDesc;
    /** 显示权重 */
    private Integer priority;
    /** 创建时间 */
    private Date createTime;
    /** 所属商品Id */
    private Long productId;
}
