package top.ywlog.o2o.util;

import lombok.Data;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/12/25 15:15
 * Description: Json结果封装
 */
@Data
public class JsonMapResult<T>
{
    private List<T> rows;
    private T row;
    private Integer total;
    private Boolean success;
    private String errMsg;
}
