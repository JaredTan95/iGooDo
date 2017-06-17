package cn.tanjianff.igoodo.common.db.repository;


import cn.tanjianff.igoodo.common.db.domain.IgdUser;

import java.util.Map;

/**
 * Created by tanjian on 2017/6/4.
 * 用户基础信息数据仓库接口
 */
public interface UserRepository {

    /**
     * 通过保存用户信息来完成用户的注册.
     *
     * @param user 需要保存的用户信息对象实例
     * @return IgdUser 用户对象实例;保存后的用户信息。
     */
    IgdUser save(IgdUser user);

    /**
     * 通过用户id查找用户细信息.
     *
     * @param id 用户id
     * @return IgdUser 用户对象
     */
    IgdUser findById(String id);

    /**
     * 更新用户信息.
     *
     * @param user 新的用户信息对象实例
     * @return IgdUser 用户对象实例;修改后的用户信息。
     */
    IgdUser update(IgdUser user);


    /**
     * 获取用户相关信息，用于用户中心信息展示.
     *
     * @param id 用户ID
     * @return Map IgdUser,IgdUserInformation 用户信息集合
     */
    Map<String,Object> getRelatedInfo(String id);
}
