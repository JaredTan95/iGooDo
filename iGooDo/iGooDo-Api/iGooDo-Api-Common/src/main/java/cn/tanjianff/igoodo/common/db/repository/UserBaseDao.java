package cn.tanjianff.igoodo.common.db.repository;

import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import org.springframework.stereotype.Component;

/**
 * Created by tanjian on 2017/8/5.
 */
@Component
public interface UserBaseDao extends JdbcBaseDao<IgdUser> {

}
