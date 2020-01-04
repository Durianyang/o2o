package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.PersonInfo;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2020/1/4 17:09
 * Description:
 */
public class PersonInfoDaoTest extends BaseTest
{
    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    public void getPersonInfoTest()
    {
        PersonInfo info = personInfoDao.getPersonInfoById(8L);
        System.out.println("info = " + info);
    }

    @Test
    public void insertPersonInfoTest()
    {
        PersonInfo p = new PersonInfo();
        p.setName("testPersonInfo");
        p.setEmail("test@.com");
        p.setEnableStatus(1);
        p.setUserType(1);
        p.setCreateTime(new Date());
        p.setLastEditTime(new Date());
        p.setProfileImg("test.jpg");
        p.setGender("男");


        int i = personInfoDao.insertPersonInfo(p);
        System.out.println("i = " + i);
    }

}
