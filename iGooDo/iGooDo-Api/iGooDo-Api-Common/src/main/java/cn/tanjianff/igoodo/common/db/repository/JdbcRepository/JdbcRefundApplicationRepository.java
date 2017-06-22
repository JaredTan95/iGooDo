package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.common.db.domain.IgdRefundApplication;
import cn.tanjianff.igoodo.common.db.repository.RefundApplicationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tanjian on 2017/6/10.
 * 用户反馈操作
 */
public class JdbcRefundApplicationRepository implements RefundApplicationRepository {
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT="INSERT INTO IGD_REFUND_APPLICATION(USER_phone, REF_UUID, REF_apply_date," +
            " REF_commit, REF_feedback) VALUES (?,?,?,?,?)";
    private static final String UPDATE="UPDATE IGD_REFUND_APPLICATION SET REF_apply_date=?,REF_dealed,REF_feedback=? WHERE REF_UUID=?";

    private static final String FINDONE="SELECT * FROM IGD_REFUND_APPLICATION WHERE REF_UUID=?";

    public JdbcRefundApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IgdRefundApplication save(IgdRefundApplication ra) {
        return jdbcTemplate.update(INSERT,ra.getUserPhone(),ra.getRefUuid(),ra.getRefApplyDate()
                ,ra.getRefCommit(),ra.getRefFeedback())>0?ra:new IgdRefundApplication();
    }

    @Override
    public IgdRefundApplication update(IgdRefundApplication ra) {
        return jdbcTemplate.update(UPDATE,ra.getRefApplyDate(),ra.getRefDealed()
                ,ra.getRefFeedback(),ra.getRefUuid())>0?ra:new IgdRefundApplication();
    }

    @Override
    public IgdRefundApplication findById(String id) {
        return jdbcTemplate.queryForObject(FINDONE,new ReffundRowMapper(),id);
    }

    private static class ReffundRowMapper implements RowMapper<IgdRefundApplication>{
        @Override
        public IgdRefundApplication mapRow(ResultSet rs, int i) throws SQLException {
            return new IgdRefundApplication(rs.getDouble("USER_phone")
            ,rs.getString("REF_UUID"),rs.getDate("REF_apply_date")
            ,rs.getInt("REF_dealed"),rs.getString("REF_commit")
            ,rs.getString("REF_feedback"),rs.getTimestamp("update_time"));
        }
    }
}
