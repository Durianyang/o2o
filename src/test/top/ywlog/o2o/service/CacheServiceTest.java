package top.ywlog.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;

/**
 * Author: Durian
 * Date: 2020/1/6 21:52
 * Description:
 */
public class CacheServiceTest extends BaseTest
{
    @Autowired
    private CacheService cacheService;

    @Test
    public void delTest()
    {
        cacheService.removeFromCache("headLineList");
    }
}
