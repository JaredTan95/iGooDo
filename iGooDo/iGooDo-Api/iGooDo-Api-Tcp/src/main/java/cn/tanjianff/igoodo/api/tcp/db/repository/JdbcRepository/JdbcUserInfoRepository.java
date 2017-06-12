package cn.tanjianff.igoodo.api.tcp.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.api.tcp.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.api.tcp.db.repository.UserInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tanjian on 2017/6/5.
 */
@Service("jdbcUserInfoRepository")
public class JdbcUserInfoRepository implements UserInfoRepository {
    //TODO:
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_USER_INFO="SELECT * FROM IGD_USER_INFORMATION WHERE USER_phone=?";
    public JdbcUserInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IgdUserInformation save(IgdUserInformation information) {
        return null;
    }

    @Override
    public IgdUserInformation findById(String id) {
        return jdbcTemplate.queryForObject(SELECT_USER_INFO,new UserInfoRowMapper(),id);
    }

    @Override
    public IgdUserInformation update(IgdUserInformation information) {
        return null;
    }

    @Override
    public IgdUserInformation delete(String id) {
        return null;
    }

    private static final class UserInfoRowMapper implements RowMapper<IgdUserInformation>{
        @Override
        public IgdUserInformation mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IgdUserInformation(resultSet.getDouble("USER_phone")
                    ,resultSet.getDate("INF_birthday"),
                    resultSet.getLong("INF_weight_kg")
                    ,resultSet.getLong("INF_height_cm"),
                    resultSet.getString("INF_reserved_field_01")
                    ,resultSet.getString("INF_reserved_field_02")
                    ,resultSet.getString("INF_reserved_field_03")
                    ,resultSet.getString("INF_reserved_field_04")
                    ,resultSet.getString("INF_reserved_field_05")
                    ,resultSet.getString("INF_reserved_field_06")
                    ,resultSet.getString("INF_reserved_field_07")
                    ,resultSet.getString("INF_reserved_field_08")
                    ,resultSet.getTimestamp("update_time"));
        }
    }
}
