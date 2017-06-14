package cn.tanjianff.igoodo.db.repository;


import cn.tanjianff.igoodo.db.domain.IgdUserInformation;

/**
 * Created by tanjian on 2017/6/4.
 * 用户扩展信息数据仓库接口
 */
public interface UserInfoRepository {
    /**
     * 保存用户扩展信息.
     *
     * @param information 用户扩展信息对象实例
     * @return IgdUserInformation 保存的用户扩展信息的对象实例
     */
    IgdUserInformation save(IgdUserInformation information);

    /**
     * 通过用户扩展信息的id查找到与之对应的信息.
     *
     * @param id 用户id
     * @return IgdUserInformation 用户扩展信息的对象实例
     */
    IgdUserInformation findById(String id);

    /**
     * 更新用户扩展信息.
     *
     * @param information 用户扩展信息对象实例
     * @return IgdUserInformation 更新过后的用户扩展信息对象实例
     */
    IgdUserInformation update(IgdUserInformation information);

    /**
     * 通过用户扩展信息id删除该用户的扩展信息.
     *
     * @param id 用户信息id
     * @return IgdUserInformation 删除的用户扩展信息对象实例
     */
    IgdUserInformation delete(String id);
}
