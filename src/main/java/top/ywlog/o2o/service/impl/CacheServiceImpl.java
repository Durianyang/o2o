package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ywlog.o2o.cache.JedisUtil;
import top.ywlog.o2o.service.CacheService;

import java.util.Set;

/**
 * Author: Durian
 * Date: 2020/1/6 21:49
 * Description:
 */
@Service
public class CacheServiceImpl implements CacheService
{
    private final JedisUtil.Keys jedisKeys;

    @Autowired
    public CacheServiceImpl(JedisUtil.Keys jedisKeys)
    {
        this.jedisKeys = jedisKeys;
    }

    @Override
    public void removeFromCache(String keyPrefix)
    {
        Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
        for (String key : keySet)
        {
            jedisKeys.del(key);
        }
    }
}
