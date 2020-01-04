package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.dto.WechatAuthExecution;
import top.ywlog.o2o.entity.PersonInfo;
import top.ywlog.o2o.entity.WechatAuth;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2020/1/4 17:58
 * Description:
 */
public class WechatAuthServiceTest extends BaseTest
{
    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void registerTest()
    {
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "registerTest";
        // 设置上用户信息，但是不设置用户ID
        personInfo.setCreateTime(new Date());
        personInfo.setName("注册测试");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution register = wechatAuthService.register(wechatAuth);
        System.out.println(register.getWechatAuth());
    }
}
