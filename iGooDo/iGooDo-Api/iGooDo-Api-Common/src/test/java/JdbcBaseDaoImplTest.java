import cn.tanjianff.igoodo.common.db.JdbcBaseDaoImpl;
import cn.tanjianff.igoodo.common.db.domain.IGD_WATER_PRICE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tanjian on 2017/7/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JdbcConfig.class})
public class JdbcBaseDaoImplTest {
    @Test
    public void insert(){
        new JdbcBaseDaoImpl<IGD_WATER_PRICE>().execSQL("select * from IGD_WATER_PRICE");
    }
}
