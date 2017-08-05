package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;

import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanjian on 2017/6/4.
 * 基于JdbcTemplate实现用户基础信息数据库仓库接口
 */
@ComponentScan
@Service("jdbcUserRepository")
public class JdbcUserRepository implements UserRepository {
    private static Logger log = Logger.getLogger(JdbcUserRepository.class);
    private static final String SAVE = "INSERT INTO iGooDo.IGD_USER (USER_phone, USER_pwd," +
            " USER_sex, USER_nickname, USER_regdate, USER_icon, USER_alipay_account, USER_credit," +
            " USER_reserved_field_01, USER_reserved_field_02, USER_reserved_field_03, update_time) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE IGD_USER SET USER_pwd=?, USER_sex=?,USER_nickname=?," +
            " USER_regdate=?, USER_icon=? USER_alipay_account=?, USER_credit=?" +
            "WHERE IGD_USER.USER_phone=?";
    private static final String SELECT_BASE_USER = "SELECT USER_phone, USER_pwd, USER_sex," +
            "USER_nickname, USER_regdate, USER_icon, USER_alipay_account, USER_credit, " +
            "USER_reserved_field_01, USER_reserved_field_02, USER_reserved_field_03, " +
            "update_time FROM IGD_USER WHERE USER_phone=?";
    private static final String IS_EXISTS = "SELECT  COUNT(*) FROM IGD_USER WHERE USER_phone=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcUserInfoRepository jdbcUserInfoRepository;

    @Autowired
    public void setJdbcUserInfoRepository(JdbcUserInfoRepository jdbcUserInfoRepository) {
        this.jdbcUserInfoRepository = jdbcUserInfoRepository;
    }

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(IgdUser user) {
        return jdbcTemplate.update(SAVE, user.getUser_phone(), user.getUser_pwd(), user.getUser_sex()
                , user.getUser_nickname(), user.getUser_regdate(), user.getUser_icon()
                , user.getUser_alipay_account(), user.getUser_credit(), user.getUser_reserved_field_01()
                , user.getUser_reserved_field_02(), user.getUser_reserved_field_03(), user.getUpdate_time()) > 0;
    }

    @Override
    public IgdUser findById(String id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BASE_USER, new UserRowMapper(), id);
        } catch (Exception e) {
            if (e instanceof DataAccessException) {
                e.printStackTrace();
                log.error("!!!!!!!!!!!!!! User id :" + id + " is not exsits!");
            }
            return null;
        }
    }

    @Override
    public boolean isExists(String id) {
        try {
            return jdbcTemplate.queryForObject(IS_EXISTS, Integer.class, id) > 0;
        } catch (Exception e) {
            if (e instanceof DataAccessException) {
                log.error("!!!!!!!!!!!!!! User id :" + id + " is not exsits!");
            }
            return false;
        }
    }

    @Override
    public IgdUser update(IgdUser user) {
        return jdbcTemplate.update(UPDATE_USER, user.getUser_pwd(), user.getUser_sex(), user.getUser_nickname()
                , user.getUser_regdate(), user.getUser_icon(), user.getUser_alipay_account(), user.getUser_credit()
                , user.getUser_phone()) > 0 ? user : new IgdUser();
    }

    @Override
    public Map<String, Object> getRelatedInfo(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_base", this.findById(id));
        map.put("user_extInfo", jdbcUserInfoRepository.findById(id));
        return map;
    }

    private static final class UserRowMapper implements RowMapper<IgdUser> {
        @Override
        public IgdUser mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IgdUser(resultSet.getString("USER_phone"),
                    resultSet.getString("USER_pwd"),
                    resultSet.getInt("USER_sex"),
                    resultSet.getString("USER_nickname"),
                    resultSet.getDate("USER_regdate"),
                    resultSet.getString("USER_icon"),
                    resultSet.getString("USER_alipay_account"),
                    resultSet.getLong("USER_credit"),
                    resultSet.getString("USER_reserved_field_01"),
                    resultSet.getString("USER_reserved_field_02"),
                    resultSet.getString("USER_reserved_field_03"),
                    resultSet.getTimestamp("update_time"));
        }
    }
}
