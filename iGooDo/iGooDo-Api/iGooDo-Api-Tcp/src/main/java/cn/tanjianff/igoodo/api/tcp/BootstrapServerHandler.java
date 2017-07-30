package cn.tanjianff.igoodo.api.tcp;

import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import cn.tanjianff.igoodo.common.util.RequestFormatData;
import cn.tanjianff.igoodo.common.util.ResponseFormatData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.SocketAddress;

@Service("helloServerHandler")
@Scope("prototype")
//特别注意这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
//地址是import io.netty.channel.ChannelHandler.Sharable;
@Sharable
public class BootstrapServerHandler extends SimpleChannelInboundHandler<String> {
    private static Log log = LogFactory.getLog(BootstrapServerHandler.class);

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static String uuid;
    private int counter = 0;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        ClientTcpSocketChannelMap.removeSocketChannel(ctx.channel().id().asLongText());//从map中移除该连接通道
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        Channel incoming = ctx.channel();
        SocketAddress remoteAdress = incoming.remoteAddress();
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received:" + msg + "\nYour R:" + remoteAdress.toString() + "\n");

        //调试时是否沾包

        if (msg.equals("PING")) {
            //客户端ping,心跳检测，并返回0000状态状态码,
            // 代表服务端已经收到客户端发送的心跳检测，并忽视此次放送的消息
            ctx.writeAndFlush("0000\n");
        }

        if (msg.equals("OPEN")) {
            ResponseFormatData response = new ResponseFormatData();
            response.setNeedCup(1);
            response.setAppUserId("18323261979");
            response.setMsg("60");
            ClientTcpSocketChannelMap.getSocketChannel("12345678")
                    .writeAndFlush(response.FormatToString());
        }

        //根据msg长度来初步判断是否是有用的数据请求,如果是，则尝试从客户端发送的信息中提取信息,否则不理会;
        if (msg.length() >= 16) {
            try {
                //响应 LINK
                RequestFormatData request = new RequestFormatData(msg);
                if (request.getType().equals(RequestFormatData.LINK)) {
                    //从消息中解析到机器的序列号，同时更新连接Map
                    String serialNum = request.getSerialNum();
                    System.out.print("在解析过程中得到的UUID,是否改变:\n" + ctx.channel().id().asLongText());
                    ClientTcpSocketChannelMap.getSerialNumMap().put(serialNum, ctx.channel().id().asLongText());
                    ctx.writeAndFlush("0000");
                }
                //响应CHEC
                if (request.getType().equals(RequestFormatData.CHEC)) {
                    //TODO:执行业务逻辑
                    /*Thread.sleep(50000);//模拟执行数据库操作*/
                    ctx.writeAndFlush("0000\n");
                    System.out.println("收到CHEC!!!已发送 0000");
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    //提取消息总长度异常
                    ctx.writeAndFlush("Data Length(TotalLength) Format error!\n");
                }
                if (e instanceof StringIndexOutOfBoundsException) {
                    //提取消息时越界异常
                    ctx.writeAndFlush("Data Length(OutOfBound) Format error!\n");
                }
            }
        }
    }

    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("R:" + ctx.channel().remoteAddress() + "Active!");
        
        /*ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");*/
        ctx.writeAndFlush("Welcome to iGooDo Tcp service!\n" + "Your R:" + ctx.channel().remoteAddress() + "\n");

        //TODO:待切换为以连接客户端IP为key---> String key= RegexUtils.getIp(ctx.channel().remoteAddress().toString());
        uuid = ctx.channel().id().asLongText();
        System.out.print("刚建立连接时的UUID:\n" + uuid + "\n");
        ClientTcpSocketChannelMap.addTcpSocketChannel(uuid, (SocketChannel) ctx.channel());

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        ctx.close();
    }
}