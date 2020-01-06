package top.ywlog.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.ywlog.o2o.cache.JedisUtil;
import top.ywlog.o2o.dao.HeadLineDao;
import top.ywlog.o2o.entity.HeadLine;
import top.ywlog.o2o.exceptions.HeadLineOperationException;
import top.ywlog.o2o.service.HeadLineService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Durian
 * Date: 2020/1/2 14:49
 * Description:
 */
@Service
public class HeadLineServiceImpl implements HeadLineService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HeadLineServiceImpl.class);
    private final JedisUtil.Keys jedisKeys;
    private final JedisUtil.Strings jedisStrings;
    private final HeadLineDao headLineDao;

    @Autowired
    public HeadLineServiceImpl(JedisUtil.Keys jedisKeys, JedisUtil.Strings jedisStrings, HeadLineDao headLineDao)
    {
        this.jedisKeys = jedisKeys;
        this.jedisStrings = jedisStrings;
        this.headLineDao = headLineDao;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<HeadLine> listHeadLine(HeadLine headLineCondition)
    {
        String key = HEADLINE_LIST_KEY;
        List<HeadLine> headLineList;
        ObjectMapper mapper = new ObjectMapper();
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null)
        {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        if (!jedisKeys.exists(key) || jedisStrings.get(key) == null)
        {
            headLineList = headLineDao.listHeadLine(headLineCondition);
            String jsonValue;
            try
            {
                jsonValue = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e)
            {
                LOGGER.error("json转化失败!" + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonValue);
        } else
        {
            String jsonValue = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try
            {
                headLineList = mapper.readValue(jsonValue, javaType);
            } catch (IOException e)
            {
                LOGGER.error("json转换失败！" + e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLineList;
    }
}
