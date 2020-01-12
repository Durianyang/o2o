package top.ywlog.o2o.interceptor.shop;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.ywlog.o2o.entity.PersonInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author: Durian
 * Date: 2020/1/12 13:39
 * Description:
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter
{
    /**
     * 操作前拦截器
     *
     * @param request  请求
     * @param response 响应
     * @param handler  助理
     * @return 拦截结果
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Object user = request.getSession().getAttribute("user");
        if (user != null)
        {
            // 若用户信息不为空
            PersonInfo personInfo = (PersonInfo)user;
            if (personInfo.getUserId() != null && personInfo.getUserId() > 0 && personInfo.getEnableStatus() == 1)
            {
                return true;
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('" + request.getContextPath() + "/local/login?userType=2', '_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
