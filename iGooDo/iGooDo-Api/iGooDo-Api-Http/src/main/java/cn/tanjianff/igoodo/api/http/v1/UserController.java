package cn.tanjianff.igoodo.api.http.v1;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tanjian on 2017/6/22.
 * 用户业务控制器
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取莫个用户的基础信息.
     *
     * @param id 用户id
     * @return 响应消息
     */
    @RequestMapping(value = "")
    @ResponseBody
    public MyRespMsgEntity get(String id) {
        return userService.getMyInfo(id);
    }

    /**
     * 注册或登录接口.如果存在该用户则发送短信，否则自动注册
     *
     * @param phoneNum 电话号码
     * @return 响应消息
     */
    @RequestMapping(value = "/RegOrLogin/{phoneNum}")
    @ResponseBody
    public MyRespMsgEntity register(@PathVariable("phoneNum") String phoneNum) {
        return userService.userRegiterOrLogin(phoneNum);
    }

}
