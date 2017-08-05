package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by tanjian on 2017/8/5.
 */
@Component
public class UserBaseDaoImpl extends JdbcBaseDaoImpl<IgdUser> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserBaseDaoImpl() {
        this.setJdbcTemplate(this.jdbcTemplate);
    }
}
