package top.ywlog.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * Author: Durian
 * Date: 2019/12/25 13:16
 * Description: 店铺实体类
 */
@Getter
@Setter
public class Shop
{
    /** 店铺Id */
    private Long shopId;
    /** 店铺名称 */
    private String shopName;
    /** 店铺描述 */
    private String shopDesc;
    /** 店铺地址 */
    private String shopAddr;
    /** 店铺联系电话 */
    private String phone;
    /** 店铺图片 */
    private String shopImg;
    /** 店铺展示权重 */
    private Integer priority;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date lastEditTime;
    /** 店铺状态 -1：不可用 0：审核中 1：可用 */
    private Integer enableStatus;
    /** 管理员建议 */
    private String advice;
    /** 店铺区域 */
    private Area area;
    /** 店铺所有者信息 */
    private PersonInfo personInfo;
    /** 店铺分类信息 */
    private ShopCategory shopCategory;
}


