package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.PersonInfo;
import top.ywlog.o2o.entity.WechatAuth;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2020/1/4 17:09
 * Description:
 */
public class WechatAuthDaoTest extends BaseTest
{
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void getWechatAuthTest()
    {
        WechatAuth wechatAuth = wechatAuthDao.getWechatAuthByOpenId("test");
        System.out.println("wechatAuth = " + wechatAuth);
    }

    @Test
    public void insertWechatAuthTest()
    {
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo p = new PersonInfo();
        p.setUserId(8L);
        wechatAuth.setOpenId("test2");
        wechatAuth.setPersonInfo(p);
        wechatAuth.setCreateTime(new Date());
        int i = wechatAuthDao.insertWechatAuth(wechatAuth);
        System.out.println("i = " + i);

    }
}
