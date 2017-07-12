import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by tanjian on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class TestJdbcUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Test

    @Test
    public void hello(){
        System.out.println("hhhh");
    }

    //@Test
    public void find(){
        Map<String, Object> map=new JdbcUserRepository(jdbcTemplate).getRelatedInfo("123");
            System.out.println(map.get("user_base").toString());
            System.out.println(map.get("user_extInfo").toString());
    }
}
