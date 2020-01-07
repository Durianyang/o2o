package top.ywlog.o2o;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import top.ywlog.o2o.entity.Shop;

/**
 * Author: Durian
 * Date: 2019/12/26 13:16
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 只能加载dao这一个配置文件
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-redis.xml",
        "classpath:spring/spring-aop.xml"})
public class BaseTest
{
    @Test
    public void test()
    {
        Shop shop = new Shop();
        System.out.println(shop == null);
    }

    @Test
    public void redisConnectionTest()
    {
        Jedis j = new Jedis("47.93.11.144", 6379);
        System.out.println(j.ping());
    }
}
