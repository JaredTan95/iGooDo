package cn.tanjianff.igoodo.api.tcp;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tanjian on 2017/6/12.
 * 全局静态变量，存放所有通过Tcp连接的 SocketChannel通道
 */
public class ClientTcpSocketChannelMap {
    private static Map<String ,SocketChannel> map= new ConcurrentHashMap<>();

    public static void addTcpSocketChannel(String key, SocketChannel socketChannel){
        map.put(key,socketChannel);
    }

    public static Map<String, SocketChannel> getChannels(){
        return map;
    }

    public static SocketChannel getSocketChannel(String key){
       return map.get(key);
    }

    public static void removeSocketChannel(String key){
        map.remove(key);
    }
}
