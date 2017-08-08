package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.common.db.domain.Igd_personnal_cost_records;
import cn.tanjianff.igoodo.common.db.repository.JdbcPersonalCostRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tanjian on 2017/8/8.
 */
@Component
public class JdbcPersonalCostRecordRepository implements JdbcPersonalCostRecord {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT = "INSERT INTO IGD_Personnal_Cost_Records(_id,USER_phone,waterIntake_ml,cost) " +
            "VALUES (?,?,?,?)";

    private static final String FIND_ALL_BY_USERID = "SELECT _id,USER_phone,waterIntake_ml,cost,update_time " +
            "FROM IGD_Personnal_Cost_Records WHERE USER_phone=?";


    @Override
    public boolean save(Igd_personnal_cost_records records) {
        return jdbcTemplate.update(INSERT,records.get_id(),
                records.getUser_phone(),records.getWaterintake_ml(),records.getCost())>0;
    }

    @Override
    public List<Igd_personnal_cost_records> findByUserId(String id) {
        return jdbcTemplate.query(FIND_ALL_BY_USERID, new PersonalCostRecordRowMapper(),id);
    }

    private static final class PersonalCostRecordRowMapper implements RowMapper<Igd_personnal_cost_records> {
        @Override
        public Igd_personnal_cost_records mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Igd_personnal_cost_records(
                    resultSet.getString("_id"), resultSet.getDouble("USER_phone")
                    , resultSet.getLong("waterIntake_ml")
                    , resultSet.getDouble("cost")
                    , resultSet.getTimestamp("update_time"));
        }
    }
}
