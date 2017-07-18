package cn.tanjianff.igoodo.api.http.service.plugin.impl;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.plugin.PluginsService;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RandomUtils;
import cn.tanjianff.igoodo.common.util.yunpianSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;

/**
 * Created by tanjian on 2017/7/19.
 */
@Service
@Transactional
public class PluginServiceImpl implements PluginsService {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MyRespMsgEntity getSmsCode(String phoneNum) {
        String apikey = yunpianSmsUtil.getApiKey();
        String mobile= "";
        String code="";
        String text="";
        //修改为您要发送的手机号
        try {
            mobile = URLEncoder.encode(phoneNum, yunpianSmsUtil.ENCODING);
            code = RandomUtils.getRandomNumber(4);
            text = new JdbcUserRepository(jdbcTemplate).isExists(phoneNum)
                    ? yunpianSmsUtil.getLoginSmsTpl(code)
                    : yunpianSmsUtil.getRegisterSmsTpl(code);

            //在云片返回的json基础上添加短信验证码
            return MyRespMsgEntity.getSuccessMsg().put("sms",yunpianSmsUtil.sendSms(apikey, text, mobile)).put("smsCode",code);
        } catch (Exception e) {
            e.printStackTrace();
            return MyRespMsgEntity.getFailedMsg().put("error",e.getMessage());
        }
    }
}
