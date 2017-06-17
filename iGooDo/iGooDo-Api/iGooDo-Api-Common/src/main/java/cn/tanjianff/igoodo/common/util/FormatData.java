package cn.tanjianff.igoodo.common.util;

/**
 * Created by tanjian on 2017/6/13.
 * 由于客户端发送的数据为字符串，因此，接收时需要将数据格式化，从字符串根据约定提取出相应的信息;
 * 可能收到的数据格式如：
 * ____________________________________________________________________________________________
 * |                   |                 |                          |                           |
 * | 数据长度[4个字符]   |业务类型[4个字符]  |App用户ID,手机号[11个字符]  |附加信息,结算金额             |
 * |___________________|_________________|__________________________|___________________________|
 */
public class FormatData {
    private String todoString;
    private int len;
    private String type;
    private String appUserId;
    private String msg;

    public FormatData(String todoString) throws  NumberFormatException,StringIndexOutOfBoundsException{
        this.todoString = todoString;
        this.len = Integer.parseInt(getTodoString().substring(0, 4));
        this.type = todoString.substring(4, 8);
        this.appUserId = todoString.substring(8, 19);
        setMsg();
    }

    public String getTodoString() {
        return todoString;
    }

    public void setTodoString(String todoString) {
        this.todoString = todoString;
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

    public void setMsg() throws StringIndexOutOfBoundsException{
        int msgLen=this.len-19;
        this.msg=this.todoString.substring(len-msgLen,len);
    }

    @Override
    public String toString() {
        return "FormatData{" +
                "todoString='" + todoString + '\'' +
                ", len=" + len +
                ", type='" + type + '\'' +
                ", appUserId='" + appUserId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
