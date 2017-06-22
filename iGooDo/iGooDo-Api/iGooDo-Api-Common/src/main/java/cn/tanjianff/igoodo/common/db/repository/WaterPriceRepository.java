package cn.tanjianff.igoodo.common.db.repository;


import cn.tanjianff.igoodo.common.db.domain.IgdWaterPrice;

/**
 * Created by tanjian on 2017/6/4.
 * 饮水基本价格数据仓库接口
 */
public interface WaterPriceRepository {
    /**
     * 保存饮水价格.
     *
     * @param waterPrice 饮水价格对象实例
     * @return IgdWaterPrice 保存的饮水价格对象实例
     */
    IgdWaterPrice save(IgdWaterPrice waterPrice);

    /**
     * 更新饮水价格.
     *
     * @param waterPrice 饮水价格对象实例
     * @return IgdWaterPrice 更新后的饮水价格对象实例
     */
    IgdWaterPrice update(IgdWaterPrice waterPrice);

    /**
     * 删除一条饮水价格记录.
     *
     * @param id 饮水价格id
     * @return IgdWaterPrice 删除的饮水价格对象实例
     */
    boolean delete(String id);

    /**
     * 查找一条饮水价格记录.
     *
     * @param id 饮水价格id
     * @return IgdWaterPrice 查找到的饮水价格对象实例
     */
    IgdWaterPrice findById(String id);
}
