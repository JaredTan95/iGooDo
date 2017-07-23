package cn.tanjianff.igoodo.api.http.service.plugin.impl;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.plugin.PluginsService;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RandomUtils;
import cn.tanjianff.igoodo.common.util.yunpianSmsUtil;
import com.qiniu.util.Auth;
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

    @Override
    public MyRespMsgEntity getQiNiuYunToken() {
        String accessKey = "5c8XqP_Hene9ZnSCs6_akY6U0E-FBW84s1cQoVLf";
        String secretKey = "7Yb7n4i8BMFgzZ2SgiC8-JXUpPpr3QfxM0i1HUnz";
        String bucket = "igoodo-icon";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println("QiNiuYun Token:"+upToken);
        return null;
    }
}
