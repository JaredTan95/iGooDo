package cn.tanjianff.igoodo.api.http.service.user;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;

/**
 * Created by tanjian on 2017/7/6.
 * 用户服务
 */
public interface UserService {
    /**
     * 获取用户基本信息.
     *
     * @param id the id
     * @return the my info
     */
    MyRespMsgEntity getMyInfo(String id);

    /**
     * User regiter or login my resp msg entity.
     *
     * @param phoneNum the phone num
     * @return the my resp msg entity
     */
    MyRespMsgEntity userRegiterOrLogin(String phoneNum);
}
