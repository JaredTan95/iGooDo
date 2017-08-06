package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.UserBaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by tanjian on 2017/8/6.
 */
@Repository("userBaseDao")
public class UserBaseDaoImpl extends JdbcBaseDaoImpl<IgdUser> implements UserBaseDao{
}
