package cn.tanjianff.igoodo.api.http.v1;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.user.UserService;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.common.db.repository.UserBaseDao;
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
    private UserBaseDao userBaseDao;

    /**
     * Sets user service.
     *
     * @param userService the user service
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取莫个用户的信息.
     *
     * @param id 用户id
     * @return 响应消息 my info
     */
    @RequestMapping(value = "")
    @ResponseBody
    public MyRespMsgEntity getMyInfo(String id) {
        try {
            return userService.getMyInfo(id);
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", e.getMessage());
        }
    }


    /**
     * 获取用户基本信息.
     *
     * @param id 用户id
     * @return 基本信息 my base info
     */
    @RequestMapping(value = "/getMyBaseInfo")
    @ResponseBody
    public MyRespMsgEntity getMyBaseInfo(String id) {
        try {
            return userService.getMyBaseInfo(id);
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", e.getMessage());
        }
    }

    /**
     * 获取用户扩展信息.
     *
     * @param phoneNum 用户id，号码
     * @return 响应消息 ext info
     */
    @RequestMapping(value = "/getExtInfo")
    @ResponseBody
    public MyRespMsgEntity getExtInfo(String phoneNum) {
        try {
            return userService.getExtInfo(phoneNum);
        }catch (Exception e){
            return MyRespMsgEntity.getFailedMsg().put("error",e.getMessage());
        }
    }

    /**
     * 注册或登录接口.如果存在该用户则发送短信，否则自动注册
     *
     * @param phoneNum 电话号码
     * @return 响应消息 my resp msg entity
     */
    @RequestMapping(value = "/RegOrLogin/{phoneNum}")
    @ResponseBody
    public MyRespMsgEntity register(@PathVariable("phoneNum") String phoneNum) {
        try {
            return userService.userRegiterOrLogin(phoneNum);
        }catch (Exception e){
            return MyRespMsgEntity.getFailedMsg().put("error",e.getMessage());
        }
    }

    /**
     * 更新账户信息
     *
     * @return the my resp msg entity
     */
    @RequestMapping(value = "/updateBaseInfo")
    @ResponseBody
    public MyRespMsgEntity updateBaseInfo(IgdUser user){
        return userService.updateMyBaseInfo(user);
    }

    /**
     * 更新账户扩展信息
     *
     * @return the my resp msg entity
     */
    @RequestMapping(value = "/updateExtInfo")
    @ResponseBody
    public MyRespMsgEntity updateExtInfo(IgdUserInformation userInfo){
        return userService.updateMyExtInfo(userInfo);
    }
}
