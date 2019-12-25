package top.ywlog.o2o.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ywlog.o2o.entity.Area;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 14:51
 * Description: AreaService测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 只能加载dao这一个配置文件
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class AreaServiceTest
{
    @Autowired
    private AreaService areaService;
    @Test
    public void areaServiceTest()
    {
        List<Area> list = areaService.list();
        for (Area area : list)
        {
            System.out.println(area);
        }
    }
}
