package cn.tanjianff.igoodo.common.util;

/**
 * Created by tanjian on 2017/6/18.
 * 由于客户端发送的数据为字符串，因此，接收时需要将数据格式化，将对象转为;
 * 可能收到的数据格式如：
 * __________________________________________________________________________________________
 * |                   |                 |                             |                     |
 * | 数据长度[4个字符]   |业务类型[4个字符]  |   App用户ID,手机号[11个字符]  | 附加信息,结算金额      |
 * |___________________|_________________|_____________________________|_____________________|
 */
public class RequestFormatData {
    public static final String PING = "PING";
    public static final String LINK = "LINK";
    public static final String CHEC = "CHEC";

    private int len;
    private String type;
    private String appUserId;
    private int ml;
    private String todoString;
    private String msg;

    public RequestFormatData(String todoString) {
        this.todoString = todoString;
        this.len = Integer.parseInt(getTodoString().substring(0, 4));
        this.type = todoString.substring(4, 8);
        switch (type) {
            case PING: {

            }
            break;
            case CHEC: {
                this.appUserId = todoString.substring(8, 19);
                this.ml = Integer.parseInt(todoString.substring(19, 23));
                this.msg = todoString.substring(23, len);
            }
            break;
        }
    }

    public static String getLINK() {
        return LINK;
    }

    public static String getPING() {
        return PING;
    }


    public static String getCHEC() {
        return CHEC;
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

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public String getTodoString() {
        return todoString;
    }

    public void setTodoString(String todoString) {
        this.todoString = todoString;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RequestFormatData{" +
                "len=" + len +
                ", type='" + type + '\'' +
                ", appUserId='" + appUserId + '\'' +
                ", ml=" + ml +
                ", todoString='" + todoString + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
