package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;


import cn.tanjianff.igoodo.common.db.domain.IgdUserInformation;
import cn.tanjianff.igoodo.common.db.repository.UserInfoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tanjian on 2017/6/5.
 */
@Component("jdbcUserInfoRepository")
public class JdbcUserInfoRepository implements UserInfoRepository {
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_USER_INFO="SELECT * FROM IGD_USER_INFORMATION WHERE USER_phone=?";
    private static final String INSET="INSERT INTO IGD_USER_INFORMATION(USER_phone,INF_birthday,INF_weight_kg," +
            "INF_height_cm,INF_reserved_field_01,INF_reserved_field_02,INF_reserved_field_03," +
            "INF_reserved_field_04,INF_reserved_field_05,INF_reserved_field_06,INF_reserved_field_07," +
            "INF_reserved_field_08,update_time ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE="DELETE FROM IGD_USER_INFORMATION WHERE USER_phone=?";
    private static final String UPDATE="UPDATE IGD_USER_INFORMATION " +
            "SET INF_birthday=?,INF_weight_kg=?,INF_height_cm=?,INF_reserved_field_01=?" +
            ",INF_reserved_field_02=?,INF_reserved_field_03=?,INF_reserved_field_04=?" +
            ",INF_reserved_field_05=?,INF_reserved_field_06=?,INF_reserved_field_07=?" +
            ",INF_reserved_field_08=?,update_time=?";

    public JdbcUserInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(IgdUserInformation info) {
        return jdbcTemplate.update(INSET,info.getUserPhone(),info.getInfBirthday()
        ,info.getInfWeightKg(),info.getInfHeightCm(),info.getInfReservedField01()
        ,info.getInfReservedField02(),info.getInfReservedField03()
        ,info.getInfReservedField04(),info.getInfReservedField05()
        ,info.getInfReservedField06(),info.getInfReservedField07()
        ,info.getInfReservedField08(),info.getUpdateTime())>0;
    }

    @Override
    public IgdUserInformation findById(String id) {
        return jdbcTemplate.queryForObject(SELECT_USER_INFO,new UserInfoRowMapper(),id);
    }

    @Override
    public IgdUserInformation update(IgdUserInformation info) {
        return jdbcTemplate.update(UPDATE,info.getInfBirthday(),info.getInfWeightKg()
        ,info.getInfHeightCm(),info.getInfReservedField01(),info.getInfReservedField02()
        ,info.getInfReservedField03(),info.getInfReservedField04(),info.getInfReservedField05()
        ,info.getInfReservedField06(),info.getInfReservedField07(),info.getInfReservedField08()
        ,info.getUpdateTime())>0?info:new IgdUserInformation();
    }

    @Override
    public IgdUserInformation delete(IgdUserInformation information) {
        return jdbcTemplate.update(DELETE,information.getUserPhone())>0?information:new IgdUserInformation();
    }

    private static final class UserInfoRowMapper implements RowMapper<IgdUserInformation> {
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
