package cn.tanjianff.igoodo.api.http.v1;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tanjian on 2017/6/22.
 * 饮水机业务控制器
 */
@Controller
@RequestMapping(value = "/api/v1/WaterDis")
public class WaterDispenserController {
    /**
     * 请求打开饮水机
     *
     * @param appUserId 用户ID
     * @param cup       是否需要水杯
     * @param Serialnum 饮水机序列号
     * @param temp      水温
     * @return 状态 string
     */
    @RequestMapping(value = "/open")
    @ResponseBody
    public String open(String appUserId,int cup,String Serialnum,int temp){
        //TODO:通过TCP向单片机发送消息，打开电子开关.暂时不采用Http，在netty中实现Http请求，通过SocketChannel发送打开饮水机请求
        return "";
    }


    /**
     * 添加一台饮水机记录信息
     *
     * @return MyRespMsgEntity
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public MyRespMsgEntity add(){
        //TODO:添加一台饮水机记录信息
        return null;
    }

    /**
     * 修改饮水机信息
     *
     * @return MyRespMsgEntity
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public MyRespMsgEntity update(){
        //TODO：修改饮水机信息
        return null;
    }
}
