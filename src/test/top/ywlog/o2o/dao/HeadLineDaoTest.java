package top.ywlog.o2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ywlog.o2o.BaseTest;
import top.ywlog.o2o.entity.HeadLine;

import java.util.List;

/**
 * Author: Durian
 * Date: 2020/1/2 14:42
 * Description:
 */
public class HeadLineDaoTest extends BaseTest
{
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void listTest()
    {
        HeadLine headLine = new HeadLine();
        List<HeadLine> headLines = headLineDao.listHeadLine(headLine);
        for (HeadLine line : headLines)
        {
            System.out.println("line = " + line);
        }
    }
}
