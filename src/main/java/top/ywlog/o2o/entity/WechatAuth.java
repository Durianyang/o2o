package top.ywlog.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2019/12/25 12:53
 * Description: 微信账号实体类
 */
@Data
public class WechatAuth
{
    /** 主键Id */
    private Long wechatAuthId;
    /** 授权Id */
    private String openId;
    /** 创建时间 */
    private Date createTime;
    /** 账号用户信息 */
    private PersonInfo personInfo;
}
