package top.ywlog.o2o.web.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ywlog.o2o.dto.JsonMapResult;
import top.ywlog.o2o.entity.Product;
import top.ywlog.o2o.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Durian
 * Date: 2020/1/2 22:14
 * Description:
 */
@Controller
@RequestMapping("/front")
public class ProductDetailController
{
    private final ProductService productService;

    @Autowired
    public ProductDetailController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping(value = "/getProductDetailInfo", method = RequestMethod.GET)
    @ResponseBody
    private JsonMapResult<Product> getProductDetailInfo(Long productId)
    {
        JsonMapResult<Product> result = new JsonMapResult<>();
        Product product = null;
        if (productId != null && productId > 0)
        {
            product = productService.getProductById(productId);
            result.setRow(product);
            result.setSuccess(true);
        } else
        {
            result.setSuccess(false);
            result.setErrMsg("获取商品信息失败!");
        }
        return result;
    }
}
