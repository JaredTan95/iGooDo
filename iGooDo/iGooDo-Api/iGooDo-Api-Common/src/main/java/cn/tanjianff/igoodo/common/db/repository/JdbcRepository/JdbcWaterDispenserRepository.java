package cn.tanjianff.igoodo.common.db.repository.JdbcRepository;


import cn.tanjianff.igoodo.common.db.domain.IgdWaterDispenser;
import cn.tanjianff.igoodo.common.db.repository.WaterDispenserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by tanjian on 2017/6/10.
 */
public class JdbcWaterDispenserRepository implements WaterDispenserRepository {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT = "INSERT INTO IGD_WATER_DISPENSER(WD_UUID,WD_Serialnum,WD_lat,WD_lon" +
            ",WD_placed_date,WD_IP,WD_reserved_field_01,WD_reserved_field_02,WD_reserved_field_03" +
            ",WD_reserved_field_04) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String FINDONE="SELECT * FROM IGD_WATER_DISPENSER WHERE WD_UUID=?";
    private static final String UPDATE="UPDATE IGD_WATER_DISPENSER SET WD_lat=?,WD_lon=?," +
            " WD_placed_date=?,WD_IP=? WHERE WD_UUID=?";

    public JdbcWaterDispenserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public IgdWaterDispenser save(IgdWaterDispenser wd) {
        return jdbcTemplate.update(INSERT,wd.getWdUuid(),wd.getWdSerialnum(),wd.getWdLat()
        ,wd.getWdLon(),wd.getWdPlacedDate(),wd.getWdIp(),wd.getWdReservedField01()
        ,wd.getWdReservedField02(),wd.getWdReservedField03(),wd.getWdReservedField04())>0?wd:new IgdWaterDispenser();
    }

    @Override
    public IgdWaterDispenser findById(String id) {
        return jdbcTemplate.queryForObject(FINDONE,new WaterDispenserRowMapper(),id);
    }

    @Override
    public IgdWaterDispenser update(IgdWaterDispenser wd) {
        return jdbcTemplate.update(UPDATE,wd.getWdLat(),wd.getWdLon()
                ,wd.getWdPlacedDate(),wd.getWdIp(),wd.getWdUuid()) >0?wd:new IgdWaterDispenser();
    }

    @Override
    public IgdWaterDispenser delete(String id) {
        //TODO:删除不实现
        return null;
    }

    private static class WaterDispenserRowMapper implements RowMapper<IgdWaterDispenser> {
        @Override
        public IgdWaterDispenser mapRow(ResultSet resultSet, int i) throws SQLException {
            return new IgdWaterDispenser(resultSet.getString("WD_UUID"), resultSet.getString("WD_Serialnum")
                    , resultSet.getString("WD_IP"), resultSet.getString("WD_lat")
                    , resultSet.getString("WD_lon"), resultSet.getDate("WD_placed_date")
                    , resultSet.getString("WD_reserved_field_01"), resultSet.getString("WD_reserved_field_02")
                    , resultSet.getString("WD_reserved_field_03"), resultSet.getString("WD_reserved_field_04")
                    , resultSet.getTimestamp("update_time"));
        }
    }
}
