package cn.tanjianff.igoodo.api.http.service.user;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;
import cn.tanjianff.igoodo.common.db.domain.IgdUser;
import cn.tanjianff.igoodo.common.db.domain.IgdUserInformation;

/**
 * Created by tanjian on 2017/7/6.
 * 用户服务接口
 */
public interface UserService {
    /**
     * 获取用户信息.
     *
     * @param id the id
     * @return the my info
     */
    MyRespMsgEntity getMyInfo(String id);

    /**
     * 获取用户基本信息.
     *
     * @param id the id
     * @return the my info
     */
    MyRespMsgEntity getMyBaseInfo(String id);

    /**
     * User regiter or login my resp msg entity.
     *
     * @param phoneNum the phone num
     * @return the my resp msg entity
     */
    MyRespMsgEntity userRegiterOrLogin(String phoneNum);


    /**
     * 带自动发送验证码的业务，备用
     *
     * @param phoneNum the phone num
     * @return the my resp msg entity
     */
    MyRespMsgEntity userRegiterOrLogin2(String phoneNum);

    /**
     * Gets ext info.
     *
     * @param phonNum the phon num
     * @return the ext info
     */
    MyRespMsgEntity getExtInfo(String phonNum);


    /**
     * 更新我的个人基础信息
     *
     * @param user the user
     * @return the my resp msg entity
     */
    MyRespMsgEntity updateMyBaseInfo(IgdUser user);


    /**
     * 更新我的个人扩展信息
     *
     * @param user the user
     * @return the my resp msg entity
     */
    MyRespMsgEntity updateMyExtInfo(IgdUserInformation userInfo);
}
