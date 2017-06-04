package cn.tanjianff.igoodo.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.db.domain.IgdUser;
import cn.tanjianff.igoodo.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.db.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by tanjian on 2017/6/4.
 * 基于JdbcTemplate实现用户基础信息数据库仓库接口
 */
public class JdbcUserRepository implements UserRepository {
    private static final String SAVE="INSERT INTO iGooDo.IGD_USER (USER_phone, IGD_USER_phone, USER_pwd," +
            " USER_sex, USER_nickname, USER_regdate, USER_icon, USER_alipay_account, USER_credit," +
            " USER_reserved_field_01, USER_reserved_field_02, USER_reserved_field_03, USER_update_time) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public IgdUser save(IgdUser user) {
        return jdbcTemplate.update(SAVE,user.getUser_phone(),user.getIgd_user_phone(),user.getUser_pwd()
                            ,user.getUser_sex(),user.getUser_nickname(),user.getUser_regdate()
                            ,user.getUser_icon(),user.getUser_alipay_account(),user.getUser_credit()
                            ,user.getUser_reserved_field_01(),user.getUser_reserved_field_02()
                            ,user.getUser_reserved_field_03(),user.getUser_update_time())>0?user:new IgdUser();
    }

    @Override
    public IgdUser findById(String id) {
        return null;
    }

    @Override
    public IgdUser update(IgdUser user) {
        return null;
    }

    @Override
    public Map<IgdUser, IgdUserInformation> getRelatedInfo(String id) {
        return null;
    }

    private static final class UserRowMapper implements RowMapper<IgdUser>{
        @Override
        public IgdUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IgdUser(resultSet.getDouble("USER_phone"),
                                resultSet.getDouble("IGD_USER_phone"),
                                resultSet.getString("USER_pwd"),
                                resultSet.getLong("USER_sex"),
                                resultSet.getString("USER_nickname"),
                                resultSet.getDate("USER_regdate"),
                                resultSet.getString("USER_icon"),
                                resultSet.getString("USER_alipay_account"),
                                resultSet.getLong("USER_credit"),
                                resultSet.getString("USER_reserved_field_01"),
                                resultSet.getString("USER_reserved_field_02"),
                                resultSet.getString("USER_reserved_field_03"),
                                resultSet.getTimestamp("USER_update_time"));
        }
    }
}
