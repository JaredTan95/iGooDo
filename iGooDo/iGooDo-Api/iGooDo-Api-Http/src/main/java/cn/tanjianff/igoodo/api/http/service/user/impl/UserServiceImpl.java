package cn.tanjianff.igoodo.api.http.service.user.impl;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.user.UserService;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by tanjian on 2017/7/6.
 */
@Service
public class UserServiceImpl implements UserService {
    private JdbcUserRepository jdbcUserRepository;
    @Autowired
    public void setJdbcUserRepository(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public MyRespMsgEntity getMyInfo(String id) {
        try {
            IgdUser user = jdbcUserRepository.findById(id);
            return user != null ? MyRespMsgEntity.getSuccessMsg().put("user", user)
                    : MyRespMsgEntity.getSuccessMsg().put("error", "再确认一下信息是否正确吧～");
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", "服务器处理出错了,你看异常--->" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity userRegiterOrLogin(String phoneNum) {
        if (RegexUtils.isPhoneNumber(phoneNum)) {
            boolean isExists = jdbcUserRepository.isExists(phoneNum);
            if (isExists) {
                return MyRespMsgEntity.getSuccessMsg();
            } else {
                IgdUser user = new IgdUser();
                user.setUser_phone(phoneNum);
                user.setUser_regdate(new Date(System.currentTimeMillis()));
                user.setUser_credit(100L);
                //TODO:待完善，创建事务，同时创建用户表和用户信息表的记录
                return jdbcUserRepository.save(user) != null
                        ? MyRespMsgEntity.getSuccessMsg().put("user", user)
                        : MyRespMsgEntity.getSuccessMsg().put("error", "Registration failed!");
            }
        } else {
            return MyRespMsgEntity.getFailedMsg().put("error", phoneNum + " is not a tellphone number!");
        }
    }
}
