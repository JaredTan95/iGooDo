package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;


import cn.tanjianff.igoodo.common.db.repository.WaterPriceRepository;
import cn.tanjianff.igoodo.common.db.domain.IgdWaterPrice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tanjian on 2017/6/10.
 * 水的种类操作
 */
public class JdbcWaterPriceRepository implements WaterPriceRepository {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT="INSERT INTO IGD_WATER_PRICE (WATER_id,WATER_describe,WATER_price) " +
            "VALUES (?,?,?)";
    private static final String UPDATE="UPDATE IGD_WATER_DISPENSER SET WATER_describe=?,WATER_price=? WHERE WATER_id=?";
    private static final String FINDONE="SELECT * FROM IGD_WATER_PRICE WHERE WATER_id=?";
    private static final String DELETE="DELETE IGD_WATER_PRICE WHERE WATER_id=?";

    public JdbcWaterPriceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IgdWaterPrice save(IgdWaterPrice wp) {
        return jdbcTemplate.update(INSERT,wp.getWaterId(),
                wp.getWaterDescribe(),wp.getWaterPrice())>0?wp:new IgdWaterPrice();
    }

    @Override
    public IgdWaterPrice update(IgdWaterPrice wp) {
        return jdbcTemplate.update(UPDATE,wp.getWaterDescribe(),wp.getWaterPrice()
                ,wp.getWaterId())>0?wp:new IgdWaterPrice();
    }

    @Override
    public boolean delete(String id) {
        return jdbcTemplate.update(DELETE,id)>0;
    }

    @Override
    public IgdWaterPrice findById(String id) {
        return jdbcTemplate.queryForObject(FINDONE,new WaterPriceRowMapper(),id);
    }

    private static class WaterPriceRowMapper implements RowMapper<IgdWaterPrice>{
        @Override
        public IgdWaterPrice mapRow(ResultSet rs, int i) throws SQLException {
            return new IgdWaterPrice(rs.getString("WATER_id"),rs.getString("WATER_describe")
            ,rs.getLong("WATER_price"),rs.getTimestamp("update_time"));
        }
    }
}
