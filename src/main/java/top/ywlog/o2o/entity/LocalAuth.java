package top.ywlog.o2o.entity;

import lombok.Data;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2019/12/25 12:55
 * Description: 本地账号
 */
@Data
public class LocalAuth
{
    /** 主键Id */
    private Long localAuthId;
    /** 账号名称 */
    private String username;
    /** 账号密码 */
    private String password;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date lastEditTime;
    /** 账号用户信息 */
    private PersonInfo personInfo;

}
