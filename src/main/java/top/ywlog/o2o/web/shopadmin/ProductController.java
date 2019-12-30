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
import top.ywlog.o2o.dto.ImageHolder;
import top.ywlog.o2o.dto.JsonMapResult;
import top.ywlog.o2o.dto.ProductExecution;
import top.ywlog.o2o.entity.Product;
import top.ywlog.o2o.entity.Shop;
import top.ywlog.o2o.enums.ProductStateEnum;
import top.ywlog.o2o.exceptions.ProductOperationException;
import top.ywlog.o2o.service.ProductService;
import top.ywlog.o2o.util.CodeUtil;
import top.ywlog.o2o.util.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/30 22:43
 * Description: 商品操作控制器
 */
@Controller
@RequestMapping("/shop")
public class ProductController
{
    /**
     * 支持详情图片上传的最大张数
     */
    private static final int MAX_IMAGE_COUNT = 5;

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public JsonMapResult<Product> addProduct(HttpServletRequest request)
    {
        // 判断验证码正误
        JsonMapResult<Product> result = new JsonMapResult<>();
        if (!CodeUtil.checkVerifyCode(request))
        {
            result.setSuccess(false);
            result.setErrMsg("验证码错误！");
            return result;
        }
        // 接收前端参数的变量初始化
        ObjectMapper mapper = new ObjectMapper();
        Product product;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartRequest;
        ImageHolder thumbnail;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        try
        {
            // 若请求中存在文件流，则取出相关文件（包括缩略图和详情图）
            if (multipartResolver.isMultipart(request))
            {
                multipartRequest = (MultipartHttpServletRequest) request;
                // 取出缩略图并构建ImageHolder对象
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest
                        .getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getInputStream(), thumbnailFile.getOriginalFilename());
                for (int i = 0; i < MAX_IMAGE_COUNT; i++)
                {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest
                            .getFile("productImg" + i);
                    if (productImgFile != null)
                    {
                        // 若取出的第i个详情图文件流不为空，则将其加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getInputStream(),
                                productImgFile.getOriginalFilename());
                        productImgList.add(productImg);
                    }
                }
            } else
            {
                result.setSuccess(false);
                result.setErrMsg("上传图片不能为空!");
                return result;
            }
        } catch (Exception e)
        {
            result.setSuccess(false);
            result.setErrMsg("添加图片失败！");
            return result;
        }
        try
        {
            // 获取product实体对象
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e)
        {
            result.setSuccess(false);
            result.setErrMsg("商品信息转换失败!");
            return result;
        }
        if (product != null && productImgList.size() > 0)
        {
            try
            {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                // 执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if (pe.getState() == ProductStateEnum.SUCCESS.getState())
                {
                    result.setSuccess(true);
                    result.setRow(product);
                } else
                {
                    result.setSuccess(false);
                    result.setErrMsg(pe.getStateInfo());
                }
            } catch (ProductOperationException e)
            {
                result.setSuccess(false);
                result.setErrMsg("商品添加失败！");
                return result;
            }

        } else
        {
            result.setSuccess(false);
            result.setErrMsg("请输入商品信息！");
        }
        return result;
    }


}
