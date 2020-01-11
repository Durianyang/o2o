package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.LocalAuth;
import top.ywlog.o2o.entity.PersonInfo;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2020/1/11 16:37
 * Description:
 */
public class LocalAuthDaoTest extends BaseTest
{
    @Autowired
    private LocalAuthDao localAuthDao;

    @Test
    public void getLocalAuthByUserNameAndPwdTest()
    {
        System.out.println(localAuthDao.
                getLocalAuthByUserNameAndPwd("durian", "123"));
    }

    @Test
    public void getLocalAuthByUserIdTest()
    {
        System.out.println(localAuthDao.getLocalAuthByUserId(8L));
    }

    @Test
    public void insertLocalAuthTest()
    {
        LocalAuth auth = new LocalAuth();
        auth.setCreateTime(new Date());
        auth.setLastEditTime(new Date());
        auth.setUsername("durian");
        auth.setPassword("123");
        PersonInfo p = new PersonInfo();
        p.setUserId(8L);
        auth.setPersonInfo(p);
        System.out.println(localAuthDao.insertLocalAuth(auth));
    }

    @Test
    public void updatePasswordTest()
    {
        localAuthDao.updateLocalAuthPassword(8L, "a", "b", new Date());
        System.out.println(localAuthDao.getLocalAuthByUserId(8L));
    }
}
