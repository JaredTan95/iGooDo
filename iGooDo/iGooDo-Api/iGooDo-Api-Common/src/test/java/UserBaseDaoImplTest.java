import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.UserBaseDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tanjian on 2017/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class UserBaseDaoImplTest {

    @Test
    public void findOne(){
        new UserBaseDaoImpl().findById("18323261979");
    }
}
