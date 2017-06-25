package cn.tanjianff.igoodo.api.http;

/**
 * Created by tanjian on 2017/6/25.
 * 封装一下控制器除了返回Json之外的一些响应消息.
 */
public class MyRespMsgEntity {
    public static int SUCCESS=0;
    public static int EXCEPTION=1;
    private static int ERROR=2;
    private int code;
    private String msg;

    public MyRespMsgEntity() {
    }

    public MyRespMsgEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public MyRespMsgEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MyRespMsgEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "{ \"code\":" + code + ", \"msg\":\"" + msg + "\"}";
    }
}
