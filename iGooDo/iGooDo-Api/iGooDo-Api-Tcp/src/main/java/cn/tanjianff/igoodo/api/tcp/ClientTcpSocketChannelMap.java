package cn.tanjianff.igoodo.api.tcp;

import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tanjian on 2017/6/12.
 * 全局静态变量，存放所有通过Tcp连接的 SocketChannel通道
 */
public class ClientTcpSocketChannelMap {
    //serialNum------>uuid<------SocketChannel

    //序列号,连接uuid-->tx.channel().id().asLongText()
    private static Map<String,String> serialNumMap=new ConcurrentHashMap<>();
    //连接uuid-->tx.channel().id().asLongText(),SocketChannel
    private static Map<String ,SocketChannel> map= new ConcurrentHashMap<>();

    public static void addTcpSocketChannel(String key, SocketChannel socketChannel){
        map.put(key,socketChannel);
    }

    public static Map<String, SocketChannel> getChannels(){
        return map;
    }

    //机器序列号
    public static SocketChannel getSocketChannel(String serialNum){
       /*return map.get(key);*/
        return map.get(serialNumMap.get(serialNum));
    }

    public static void removeSocketChannel(String key){
        map.remove(key);
    }

    public static Map<String, String> getSerialNumMap() {
        return serialNumMap;
    }

    public static void setSerialNumMap(Map<String, String> serialNumMap) {
        ClientTcpSocketChannelMap.serialNumMap = serialNumMap;
    }

    public static Map<String, SocketChannel> getMap() {
        return map;
    }

    public static void setMap(Map<String, SocketChannel> map) {
        ClientTcpSocketChannelMap.map = map;
    }
}
