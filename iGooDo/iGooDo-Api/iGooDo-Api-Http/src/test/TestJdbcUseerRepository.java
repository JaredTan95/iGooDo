import cn.tanjianff.igoodo.db.domain.IgdUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by tanjian on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class TestJdbcUseerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void save(){
        /*Double user_phone, Double igd_user_phone, String user_pwd, Long user_sex, String user_nickname,
         Date user_regdate, String user_icon, String user_alipay_account, Long user_credit,
         String user_reserved_field_01, String user_reserved_field_02, String user_reserved_field_03,
          Timestamp user_update_time*/
        IgdUser igdUser=new IgdUser(Double.parseDouble("123"),Double.parseDouble("123"),
                "sdfsadf",Long.parseLong("1"),"tanjian",new java.sql.Date(System.currentTimeMillis()),"icon",
                "sdfsafsd", (long) 1232,"","","",new Timestamp(System.currentTimeMillis()));
        System.out.println("Albums Count:"+new JdbcUserRepository(jdbcTemplate).save(igdUser).getUser_nickname());

    }

    //@Test
    public void find(){
        Map<String, Object> map=new JdbcUserRepository(jdbcTemplate).getRelatedInfo("123");
            System.out.println(map.get("user_base").toString());
            System.out.println(map.get("user_extInfo").toString());
    }
}
