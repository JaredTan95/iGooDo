package cn.tanjianff.igoodo.api.http.service.plugin;

import cn.tanjianff.igoodo.api.http.MyRespMsgEntity;

/**
 * Created by tanjian on 2017/7/19.
 * 插件服务接口
 */
public interface PluginsService {
    MyRespMsgEntity getSmsCode(String phoneNum);

    MyRespMsgEntity getQiNiuYunToken();
}
