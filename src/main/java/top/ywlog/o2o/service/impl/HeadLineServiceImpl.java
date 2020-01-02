package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ywlog.o2o.dao.HeadLineDao;
import top.ywlog.o2o.entity.HeadLine;
import top.ywlog.o2o.service.HeadLineService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2020/1/2 14:49
 * Description:
 */
@Service
public class HeadLineServiceImpl implements HeadLineService
{
    private final HeadLineDao headLineDao;

    @Autowired
    public HeadLineServiceImpl(HeadLineDao headLineDao)
    {
        this.headLineDao = headLineDao;
    }

    @Override
    public List<HeadLine> listHeadLine(HeadLine headLineCondition)
    {
        return headLineDao.listHeadLine(headLineCondition);
    }
}
