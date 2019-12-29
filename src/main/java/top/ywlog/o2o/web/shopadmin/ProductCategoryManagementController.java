package top.ywlog.o2o.web.shopadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ywlog.o2o.entity.ProductCategory;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.enums.ProductCategoryEnum;
import top.ywlog.o2o.service.ProductCategoryService;
import top.ywlog.o2o.util.JsonMapResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/29 16:45
 * Description: 店铺分类控制器
 */
@Controller
@RequestMapping("/shop")
public class ProductCategoryManagementController
{
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryManagementController(ProductCategoryService productCategoryService)
    {
        this.productCategoryService = productCategoryService;
    }

    @RequestMapping(value = "/getProductCategoryList", method = RequestMethod.GET)
    @ResponseBody
    public JsonMapResult<ProductCategory> getProductCategoryList(HttpServletRequest request)
    {
        JsonMapResult<ProductCategory> result = new JsonMapResult<>();
        // 单独测试用
//        Shop shop = new Shop();
//        shop.setShopId(20L);
//        request.getSession().setAttribute("currentShop", shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (currentShop != null && currentShop.getShopId() > 0)
        {
            List<ProductCategory> list = productCategoryService.list(currentShop.getShopId());
            result.setTotal(list.size());
            result.setRows(list);
            result.setSuccess(true);
        } else
        {
            result.setErrMsg(ProductCategoryEnum.INNER_ERROR.getStateInfo());
        }
        return result;
    }
}
