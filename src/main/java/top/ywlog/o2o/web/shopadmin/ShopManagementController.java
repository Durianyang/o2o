package top.ywlog.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import top.ywlog.o2o.dto.ShopExecution;
import top.ywlog.o2o.entity.Area;
import top.ywlog.o2o.entity.PersonInfo;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.entity.ShopCategory;
import top.ywlog.o2o.enums.ShopStateEnum;
import top.ywlog.o2o.service.AreaService;
import top.ywlog.o2o.service.ShopCategoryService;
import top.ywlog.o2o.service.ShopService;
import top.ywlog.o2o.util.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Durian
 * Date: 2019/12/26 14:04
 * Description: 店铺controller
 */
@Controller
@RequestMapping("/shop")
public class ShopManagementController
{
    private final ShopService shopService;
    private final ShopCategoryService shopCategoryService;
    private final AreaService areaService;

    @Autowired
    public ShopManagementController(ShopService shopService, ShopCategoryService shopCategoryService, AreaService areaService)
    {
        this.shopService = shopService;
        this.shopCategoryService = shopCategoryService;
        this.areaService = areaService;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private JsonMapResult<Shop> registerShop(HttpServletRequest request)
    {
        JsonMapResult<Shop> result = new JsonMapResult<>();
        ObjectMapper mapper = new ObjectMapper();
        if (!CodeUtil.checkVerifyCode(request))
        {
            result.setSuccess(false);
            result.setErrMsg("验证码错误！");
            return result;
        }
        // 接收并转换相应的参数（店铺信息，图片）
        CommonsMultipartFile shopImg;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request))
        {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else
        {
            result.setSuccess(false);
            result.setErrMsg("上传图片不能为空！");
            return result;
        }
        // 注册店铺
        Shop shop = new Shop();
        String shopJson = HttpServletRequestUtil.getString(request, "shop");
        try
        {
            shop = mapper.readValue(shopJson, Shop.class);
        } catch (IOException e)
        {
            result.setSuccess(false);
            result.setErrMsg("shop信息转换失败！");
            return result;
        }
        if (shop != null && shopImg != null)
        {
            // 测试用
            PersonInfo owner = new PersonInfo();
            owner.setUserId(8L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try
            {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
            } catch (IOException e)
            {
                result.setSuccess(false);
                result.setErrMsg(e.getMessage());
            }
            if (se.getState() == ShopStateEnum.CHECK.getState())
            {
                result.setSuccess(true);
                result.setTotal(se.getCount());
                result.setRow(se.getShop());
            } else
            {
                result.setSuccess(false);
                result.setErrMsg(se.getStateInfo());
            }
            return result;
        } else
        {
            result.setSuccess(false);
            result.setErrMsg("店铺信息和图片不能为空！");
            return result;
        }
    }


    @RequestMapping(value = "/getShopInitInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInitInfo()
    {
        Map<String, Object> model = new HashMap<>();
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try
        {
            shopCategoryList = shopCategoryService.list(new ShopCategory());
            areaList = areaService.list();
            model.put("shopCategoryList", shopCategoryList);
            model.put("areaList", areaList);
            model.put("success", true);
        } catch (Exception e)
        {
            model.put("success", false);
            model.put("errMsg", e.getMessage());
        }
        return model;
    }
}
