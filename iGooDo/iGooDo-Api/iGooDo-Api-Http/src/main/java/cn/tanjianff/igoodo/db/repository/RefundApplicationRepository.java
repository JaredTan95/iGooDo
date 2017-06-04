package cn.tanjianff.igoodo.db.repository;

import cn.tanjianff.igoodo.db.domain.IgdRefundApplication;

/**
 * Created by tanjian on 2017/6/4.
 * 退款申请数据仓库接口
 */
public interface RefundApplicationRepository {

    /**
     * 新增一条退款申请.
     *
     * @param refundApplication 退款申请对象实例
     * @return IgdRefundApplication 新增的退款申请对象实例
     */
    IgdRefundApplication save(IgdRefundApplication refundApplication);

    /**
     * 更新一条退款申请.
     *
     * @param refundApplication 待更新的退款申请对象实例
     * @return IgdRefundApplication 更新后的退款申请对象实例
     */
    IgdRefundApplication update(IgdRefundApplication refundApplication);

    /**
     * 更查找一条退款申请.
     *
     * @param id 退款申请id
     * @return IgdRefundApplication 查找到的退款申请对象实例
     */
    IgdRefundApplication findById(String id);
}
