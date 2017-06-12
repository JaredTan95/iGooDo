package cn.tanjianff.igoodo.api.http;


import cn.tanjianff.igoodo.util.CaptchaUtil;
import cn.tanjianff.igoodo.util.QRCodeUtil;
import cn.tanjianff.igoodo.util.RandomUtils;
import cn.tanjianff.igoodo.util.yunpianSmsUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by tanjian on 2017/6/3.
 * Http Api
 */
@ComponentScan
@Controller
@RequestMapping(value = "/api/v1",produces="text/html;charset=UTF-8")
public class Api4HttpController {
    /**
     * Gets chap.
     *
     * @param request  Http请求
     * @param response Htpp响应
     * @return ValiCode 在Response 头部中添加ValiCode属性，其value为生成的验证码随机数
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @RequestMapping(value = "/captcha",method = GET)
    public void getChap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuffer=new StringBuffer();
        CaptchaUtil.outputCaptcha(request, response,stringBuffer);
        System.out.println(stringBuffer.toString());
    }


    /**
     * 使用JDK发送单条短信,智能匹配短信模板
     *
     * @param phoneNum 接收的手机号,仅支持单号码发送
     * @param type     短信业务类型--{"reg","login","gback"}
     * @return json 消息状态
     * @throws IOException        the io exception
     * @throws URISyntaxException the uri syntax exception
     */
    @RequestMapping(value = "/code/{phoneNum}/{type}", method = GET)
    @ResponseBody
    public String getCode(@PathVariable("phoneNum") String phoneNum,@PathVariable("type")String type) throws IOException, URISyntaxException {
        String apikey= yunpianSmsUtil.getApiKey();
        //修改为您要发送的手机号
        String mobile = URLEncoder.encode(phoneNum,yunpianSmsUtil.ENCODING);
        String code=RandomUtils.getRandomNumber(4);
        String text="";
        if(type.equals("reg") || type.equals("login") || type.equals("gback")){
            switch (type){
                case "reg":text = yunpianSmsUtil.getRegisterSmsTpl(code);break;
                case "login":text=yunpianSmsUtil.getLoginSmsTpl(code);break;
                case "gback":text=yunpianSmsUtil.getPasswdBackSmsTpl(code);break;
            }
            //在云片返回的json基础上添加短信验证码
            return "["+yunpianSmsUtil.sendSms(apikey, text, mobile)+",{\"SmsCode\":\""+code+"\"}]";
        }else {
            return "{\"msg\":\"Parameter of type has error!参数错误!\"}";
        }
    }

    /**
     * 获取 QRCode-验证码,通过Http response返回二维码.
     *
     * @param response Http 响应实例
     * @param data     需要转换成QRCode的消息
     * @param width    QRCode二维码图片宽度
     * @param height   QRCode二维码图片高度
     */
    @RequestMapping(value = "/QRCode/{data}/{width}/{height}",method = GET)
    public void getQRCode(HttpServletResponse response,@PathVariable("data")String data,@PathVariable("width") Integer width,@PathVariable("height") Integer height ){
        try{
            int iWidth = (width == null?200: width);
            int iHeight = (height==null?200: height);
            QRCodeUtil.createRqCode(data, iWidth, iHeight, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
