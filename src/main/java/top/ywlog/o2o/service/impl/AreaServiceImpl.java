package top.ywlog.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.ywlog.o2o.dao.AreaDao;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.service.AreaService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 14:49
 * Description: 区域业务实现类
 */
@Service
public class AreaServiceImpl implements AreaService
{
    private final AreaDao areaDao;
    @Autowired
    public AreaServiceImpl(AreaDao areaDao)
    {
        this.areaDao = areaDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true, timeout = 5000)
    public List<Area> list()
    {
        return areaDao.list();
    }
}
