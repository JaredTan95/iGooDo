package cn.tanjianff.igoodo.common.db.repository;


import cn.tanjianff.igoodo.common.db.domain.IgdWaterDispenser;

/**
 * Created by tanjian on 2017/6/4.
 * 饮水机数据仓库接口
 */
public interface WaterDispenserRepository {
    /**
     * 保存一条饮水机记录.
     *
     * @param waterDispenser 饮水机对象实例
     * @return IgdWaterDispenser 保存的饮水机对象实例
     */
    IgdWaterDispenser save(IgdWaterDispenser waterDispenser);

    /**
     * 查找一台饮水机的基础信息.
     *
     * @param id 饮水机id
     * @return IgdWaterDispenser 查找到的饮水机对象实例
     */
    IgdWaterDispenser findById(String id);

    /**
     * 更新一条饮水机记录.
     *
     * @param waterDispenser 饮水机对象实例
     * @return IgdWaterDispenser 更新的饮水机对象实例
     */
    IgdWaterDispenser update(IgdWaterDispenser waterDispenser);

    /**
     * 删除一条饮水机记录.
     *
     * @param id 饮水机id
     * @return IgdWaterDispenser 删除的饮水机对象实例
     */
    IgdWaterDispenser delete(String id);
}
