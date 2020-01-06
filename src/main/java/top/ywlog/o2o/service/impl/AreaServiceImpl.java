package top.ywlog.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ywlog.o2o.cache.JedisUtil;
import top.ywlog.o2o.dao.AreaDao;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.exceptions.AreaOperationException;
import top.ywlog.o2o.service.AreaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 14:49
 * Description: 区域业务实现类
 */
@Service
public class AreaServiceImpl implements AreaService
{
    private final JedisUtil.Keys jedisKeys;
    private final JedisUtil.Strings jedisStrings;
    private final AreaDao areaDao;

    @Autowired
    public AreaServiceImpl(AreaDao areaDao, JedisUtil.Keys jedisKeys, JedisUtil.Strings jedisStrings)
    {
        this.areaDao = areaDao;
        this.jedisKeys = jedisKeys;
        this.jedisStrings = jedisStrings;
    }

    /** redis数据 库的key值 */
    private static final String AREA_LIST_KEY = "areaList";
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    public List<Area> list()
    {
        List<Area> areaList;
        ObjectMapper mapper = new ObjectMapper();
        // 如果key不存在，或者value为空
        if (!jedisKeys.exists(AREA_LIST_KEY) || jedisStrings.get("areaList") == null)
        {
            // redis中不存在，查询后放入redis
            areaList = areaDao.list();
            String jsonValue;
            try
            {
                jsonValue = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e)
            {
                LOGGER.error("json转换失败！" + e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(AREA_LIST_KEY, jsonValue);
        } else
        {
            String jsonValue = jedisStrings.get("areaList");
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try
            {
                areaList = mapper.readValue(jsonValue, javaType);
            } catch (IOException e)
            {
                LOGGER.error("json转换失败！" + e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
