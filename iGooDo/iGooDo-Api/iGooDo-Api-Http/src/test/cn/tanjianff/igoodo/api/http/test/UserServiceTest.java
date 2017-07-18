package cn.tanjianff.igoodo.api.http.test;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.api.http.service.user.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tanjian on 2017/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Test
    public void userRegiterOrLogin(){
        String phone="18323261979";
        MyRespMsgEntity rsps=new UserServiceImpl().userRegiterOrLogin(phone);
        System.out.println(rsps.getMsg());
    }
}
