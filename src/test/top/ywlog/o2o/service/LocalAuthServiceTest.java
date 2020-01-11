package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.dto.LocalAuthExecution;
import top.ywlog.o2o.entity.LocalAuth;
import top.ywlog.o2o.entity.PersonInfo;

import java.util.Date;

/**
 * Author: Durian
 * Date: 2020/1/11 17:27
 * Description:
 */
public class LocalAuthServiceTest extends BaseTest
{
    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void bindTest()
    {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo p = new PersonInfo();
        p.setUserId(17L);
        String username = "test";
        String password = "123456";
        localAuth.setPersonInfo(p);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
        System.out.println(localAuthExecution.getStateInfo());
    }

    @Test
    public void updatePasswordTest()
    {
        LocalAuthExecution test = localAuthService.updateLocalAuthPassword(8L , "a", "b", new Date());
        System.out.println(test.getStateInfo());
    }
}
