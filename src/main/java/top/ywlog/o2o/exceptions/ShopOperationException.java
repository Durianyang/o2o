package top.ywlog.o2o.exceptions;

/**
 * Author: Durian
 * Date: 2019/12/26 13:10
 * Description: 店铺操作异常
 */
public class ShopOperationException extends RuntimeException
{
    public ShopOperationException(String msg)
    {
        super(msg);
    }
}
