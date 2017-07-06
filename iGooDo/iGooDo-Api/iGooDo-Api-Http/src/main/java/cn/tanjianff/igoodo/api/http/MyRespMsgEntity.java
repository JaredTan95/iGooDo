package cn.tanjianff.igoodo.api.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanjian on 2017/6/25.
 * 封装一下控制器除了返回Json之外的一些响应消息.
 */
public class MyRespMsgEntity {
    public static int SUCCESS = 200;
    private static int ERROR = 500;
    private static String SUCCESS_INFO = "请求成功";
    private static String ERROR_INFO = "请求出错";
    private int code;
    private String info;
    private Map<String, Object> msg = new HashMap<>();

    /**
     * 设置自定义处理提示消息.
     *
     * @param info 自定义提示消息
     * @return 响应实体
     */
    public MyRespMsgEntity setInfo(String info) {
        this.info = info;
        return this;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public MyRespMsgEntity setMsg(HashMap msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    /**
     * 设置自定义响应状态.
     *
     * @param code 自定义响应状态码
     * @return 响应实体
     */
    public MyRespMsgEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }

    /**
     * 向响应实体中塞数据.
     *
     * @param key   键
     * @param value 值
     * @return 响应实体
     */
    public MyRespMsgEntity put(String key, Object value) {
        msg.put(key, value);
        return this;
    }

    /**
     * 默认处理成功时的消息实体.
     *
     * @return the success msg
     */
    public static MyRespMsgEntity getSuccessMsg() {
        return new MyRespMsgEntity()
                .setCode(SUCCESS).setInfo(SUCCESS_INFO);
    }

    /**
     * 默认处理出错时的消息实体.
     *
     * @return the failed msg
     */
    public static MyRespMsgEntity getFailedMsg() {
        return new MyRespMsgEntity()
                .setCode(ERROR).setInfo(ERROR_INFO);
    }
}
