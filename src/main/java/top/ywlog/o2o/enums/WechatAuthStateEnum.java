package top.ywlog.o2o.enums;

import lombok.Getter;

/**
 * Author: Durian
 * Date: 2020/1/4 17:39
 * Description:
 */
@Getter
public enum WechatAuthStateEnum
{
    LOGINFAIL(-1, "openId输入有误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006,
        "注册信息为空");

    private int state;

    private String stateInfo;

    WechatAuthStateEnum(int state, String stateInfo)
    {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static WechatAuthStateEnum getWechatAuthStateEnumByState(int index)
    {
        for (WechatAuthStateEnum state : values())
        {
            if (state.getState() == index)
            {
                return state;
            }
        }
        return null;
    }
}
