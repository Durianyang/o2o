package top.ywlog.o2o.cache;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Author: Durian
 * Date: 2020/1/6 20:16
 * Description: 强制指定redis的JedisPool接口构造函数，这样才能在centos成功创建jedispool
 */
@Setter
@Getter
public class JedisPoolWriper
{
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig jedisPoolConfig, final String host,
                           final int port, final String password)
    {
        jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000, password);
    }
}
