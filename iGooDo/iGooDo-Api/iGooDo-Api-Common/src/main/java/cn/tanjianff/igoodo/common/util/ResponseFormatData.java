package cn.tanjianff.igoodo.common.util;

/**
 * Created by tanjian on 2017/6/13.
 * 由于客户端发送的数据为字符串，因此，接收时需要将数据格式化，将对象转为;
 * 可能收到的数据格式如：
 * _____________________________________________________________________________________________________________
 * |                   |                 |                |                            |                        |
 * | 数据长度[4个字符]   |业务类型[4个字符]  |  水杯[1个字符]  |   App用户ID,手机号[11个字符]  |附加信息,比如水温         |
 * |___________________|_________________|________________|____________________________|________________________|
 */
public class ResponseFormatData {
    public static String OPEN = "OPEN";

    private int len;
    private String type;
    private int needCup;
    private String appUserId;
    private String msg;



    public int getNeedCup() {
        return needCup;
    }

    public void setNeedCup(int needCup) {
        this.needCup = needCup;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String FormatToString() {
        String out = OPEN + needCup + appUserId + msg;
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String totalLen = String.format("%04d", out.length() + 4);
        return totalLen + out;
    }
}
