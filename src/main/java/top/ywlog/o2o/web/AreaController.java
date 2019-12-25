package top.ywlog.o2o.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.service.AreaService;
import top.ywlog.o2o.util.JsonMapResult;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 15:03
 * Description:
 */
@Controller
@RequestMapping("/admin")
public class AreaController
{
    private final AreaService areaService;
    @Autowired
    public AreaController(AreaService areaService)
    {
        this.areaService = areaService;
    }

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    public JsonMapResult<Area> listArea()
    {
        JsonMapResult<Area> modelMap = new JsonMapResult<>();
        List<Area> areaList;
        try
        {
            areaList = areaService.list();
            modelMap.setTotal(areaList.size());
            modelMap.setRows(areaList);
            modelMap.setSuccess(true);
        } catch (Exception e)
        {
            e.printStackTrace();
            modelMap.setSuccess(false);
            modelMap.setErrMsg(e.toString());
        }
        return modelMap;
    }
}
