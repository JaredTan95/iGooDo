package cn.tanjianff.igoodo.common.db.repository;

import cn.tanjianff.igoodo.common.db.domain.Igd_personnal_cost_records;

import java.util.List;

/**
 * Created by tanjian on 2017/8/8.
 * 个人消费记录数据仓库接口
 */
public interface JdbcPersonalCostRecord {
    /**
     * 保存一条消费记录.
     *
     * @param records the records
     * @return the boolean
     */
    boolean save(Igd_personnal_cost_records records);

    /**
     * 查找某个用户所有消费记录.
     *
     * @param id the id
     * @return the list
     */
    List<Igd_personnal_cost_records> findByUserId(String id);
}
