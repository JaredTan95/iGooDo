package cn.tanjianff.igoodo.api.http.v1;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

/**
 * Created by tanjian on 2017/6/22.
 * 用户业务控制器
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    /**
     * 获取莫个用户的基础信息.
     *
     * @param id 用户id
     * @return 用户实体对象Json数据
     */
    @RequestMapping(value = "")
    @ResponseBody
    public IgdUser get(String id) {
        return jdbcUserRepository.findById(id);
    }

    /**
     * 注册或登录接口.如果存在该用户则发送短信，否则自动注册
     *
     * @param phoneNum 电话号码
     * @return 状态码 {"code":0,"msg":附加信息},0:操作成功，1:异常，2:错误
     */
    @RequestMapping(value = "/RegOrLogin/{phoneNum}")
    @ResponseBody
    public String register(@PathVariable("phoneNum") String phoneNum) {
        if (RegexUtils.isPhoneNumber(phoneNum)) {
            MyRespMsgEntity msg = new MyRespMsgEntity();
            boolean isExists = jdbcUserRepository.isExists(phoneNum);
            if (isExists) {
                return msg.setCode(MyRespMsgEntity.SUCCESS).setMsg("ok").toString();
            } else {
                IgdUser user = new IgdUser();
                user.setUser_phone(phoneNum);
                user.setUser_regdate(new Date(System.currentTimeMillis()));
                user.setUser_credit(100L);
                //TODO:待完善，创建事务，同时创建用户表和用户信息表的记录
                return jdbcUserRepository.save(user) != null ?
                        msg.setCode(MyRespMsgEntity.SUCCESS).setMsg(user.toString()).toString()
                        : msg.setCode(MyRespMsgEntity.EXCEPTION).setMsg("Registration failed!").toString();
            }
        } else {
            return new MyRespMsgEntity(MyRespMsgEntity.EXCEPTION, phoneNum + " is not a tellphone number!")
                    .toString();
        }
    }

}
