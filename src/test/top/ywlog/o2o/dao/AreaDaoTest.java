package top.ywlog.o2o.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ywlog.o2o.entity.Area;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 14:31
 * Description: AreaDao测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 只能加载dao这一个配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class AreaDaoTest
{
    @Autowired
    private AreaDao areaDao;
    @Test
    public void areaDaoListTest()
    {
        List<Area> list = areaDao.list();
        for (Area area : list)
        {
            System.out.println(area);
        }
    }
}
