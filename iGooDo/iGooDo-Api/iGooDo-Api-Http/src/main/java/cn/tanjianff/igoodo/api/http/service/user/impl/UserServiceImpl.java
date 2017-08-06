package cn.tanjianff.igoodo.api.http.service.user.impl;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.plugin.PluginsService;
import cn.tanjianff.igoodo.api.http.service.user.UserService;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserInfoRepository;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RegexUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private PluginsService pluginsService;
    private static Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    public void setPluginsService(PluginsService pluginsService) {
        this.pluginsService = pluginsService;
    }

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
            IgdUserInformation userInfo = jdbcUserInfoRepository.findById(id);
            return user != null && userInfo != null ? MyRespMsgEntity.getSuccessMsg()
                    .put("userBase", user).put("userExt", userInfo)
                    : MyRespMsgEntity.getSuccessMsg().put("error", "再确认一下信息是否正确吧～");
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", "服务器处理出错了,你看异常--->" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity getMyBaseInfo(String id) {
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
            if (RegexUtils.isChinaPhoneLegal(phoneNum)) {
                boolean isExists = jdbcUserRepository.isExists(phoneNum);
                if (isExists) {
                    return MyRespMsgEntity.getSuccessMsg().put("phoneNum", phoneNum);
                } else {
                    IgdUser user = new IgdUser();
                    user.setUser_phone(phoneNum);
                    user.setUser_regdate(new Date(System.currentTimeMillis()));
                    user.setUser_credit(100L);
                    user.setUpdate_time(new Timestamp(System.currentTimeMillis()));
                    IgdUserInformation userInformation = new IgdUserInformation();
                    userInformation.setUserPhone(Double.parseDouble(phoneNum));
                    userInformation.setUpdateTime(new Timestamp(System.currentTimeMillis()));
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
            e.printStackTrace();
            return MyRespMsgEntity.getFailedMsg().put("error", "异常：" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity userRegiterOrLogin2(String phoneNum) {
        try {
            if (RegexUtils.isChinaPhoneLegal(phoneNum)) {
                boolean isExists = jdbcUserRepository.isExists(phoneNum);
                if (isExists) {
                    return MyRespMsgEntity.getSuccessMsg().put("getSmsMsg", pluginsService.getSmsCode(phoneNum));
                } else {
                    IgdUser user = new IgdUser();
                    user.setUser_phone(phoneNum);
                    user.setUser_regdate(new Date(System.currentTimeMillis()));
                    user.setUser_credit(100L);
                    user.setUpdate_time(new Timestamp(System.currentTimeMillis()));
                    IgdUserInformation userInformation = new IgdUserInformation();
                    userInformation.setUserPhone(Double.parseDouble(phoneNum));
                    userInformation.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    if (jdbcUserRepository.save(user) && jdbcUserInfoRepository.save(userInformation)) {
                        return MyRespMsgEntity.getSuccessMsg().put("user", user).put("getSmsMsg", pluginsService.getSmsCode(phoneNum));
                    } else {
                        return MyRespMsgEntity.getSuccessMsg().put("error", "Registration failed!");
                    }
                }
            } else {
                return MyRespMsgEntity.getFailedMsg().put("error", phoneNum + " is not a tellphone number!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MyRespMsgEntity.getFailedMsg().put("error", "异常：" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity getExtInfo(String phonNum) {
        try {
            if (RegexUtils.isChinaPhoneLegal(phonNum)) {
                return MyRespMsgEntity.getSuccessMsg().put("userExt", jdbcUserInfoRepository.findById(phonNum));
            } else {
                return MyRespMsgEntity.getSuccessMsg().put("error", phonNum + " is not a tellphone number!");
            }
        } catch (Exception e) {
            return MyRespMsgEntity.getFailedMsg().put("error", "异常：" + e.getMessage());
        }
    }

    @Override
    public MyRespMsgEntity updateMyBaseInfo(IgdUser user) {
        try {
            return jdbcUserRepository.update(user) != null ?
                    MyRespMsgEntity.getSuccessMsg().put("msg", "更新成功了~").put("user", user)
                    : MyRespMsgEntity.getFailedMsg().put("msg", "没有更新成功哦~");
        } catch (Exception e) {
            log.info("updateMyBaseInfo:" + e.getMessage());
            return MyRespMsgEntity.getFailedMsg().put("msg", "使用的人太多了,待会儿再试试哦~");
        }
    }

    @Override
    public MyRespMsgEntity updateMyExtInfo(IgdUserInformation userInfo) {
        try {
            return jdbcUserInfoRepository.update(userInfo) != null ?
                    MyRespMsgEntity.getSuccessMsg().put("msg", "更新成功了~").put("userExtInfo", userInfo)
                    : MyRespMsgEntity.getFailedMsg().put("msg", "没有更新成功哦~");
        } catch (Exception e) {
            log.info("updateMyExtInfo:" + e.getMessage());
            return MyRespMsgEntity.getFailedMsg().put("msg", "使用的人太多了,待会儿再试试哦~");
        }
    }
}
