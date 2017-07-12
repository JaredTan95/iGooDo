package cn.tanjianff.igoodo.api.http.service.user.impl;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.user.UserService;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserInfoRepository;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by tanjian on 2017/7/6.
 * 用户业务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private JdbcUserRepository jdbcUserRepository;
    private JdbcUserInfoRepository jdbcUserInfoRepository;

    @Autowired
    public void setJdbcUserRepository(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Autowired
    public void setJdbcUserInfoRepository(JdbcUserInfoRepository jdbcUserInfoRepository) {
        this.jdbcUserInfoRepository = jdbcUserInfoRepository;
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
        try {
            if (RegexUtils.isPhoneNumber(phoneNum)) {
                boolean isExists = jdbcUserRepository.isExists(phoneNum);
                if (isExists) {
                    return MyRespMsgEntity.getSuccessMsg();
                } else {
                    IgdUser user = new IgdUser();
                    user.setUser_phone(phoneNum);
                    user.setUser_regdate(new Date(System.currentTimeMillis()));
                    user.setUser_credit(100L);
                    IgdUserInformation userInformation = new IgdUserInformation();
                    userInformation.setUserPhone(Double.parseDouble(phoneNum));
                    userInformation.setUpdateTime(Timestamp.valueOf(String.valueOf(new Date(System.currentTimeMillis()))));
                    //TODO:待完善，创建事务，同时创建用户表和用户信息表的记录
                    if (jdbcUserRepository.save(user) && jdbcUserInfoRepository.save(userInformation)) {
                        return MyRespMsgEntity.getSuccessMsg().put("user", user);
                    } else {
                        return MyRespMsgEntity.getSuccessMsg().put("error", "Registration failed!");
                    }
                }
            } else {
                return MyRespMsgEntity.getFailedMsg().put("error", phoneNum + " is not a tellphone number!");
            }
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", "异常：" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity getExtInfo(String phonNum) {
        try {
            if (RegexUtils.isPhoneNumber(phonNum)) {
                return MyRespMsgEntity.getSuccessMsg().put("userInfo", jdbcUserInfoRepository.findById(phonNum));
            } else {
                return MyRespMsgEntity.getSuccessMsg().put("error", phonNum + " is not a tellphone number!");
            }
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", "异常：" + e.getMessage());
        }
    }
}
