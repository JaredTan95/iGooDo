package cn.tanjianff.igoodo.api.tcp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
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
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //TODO:调试使用
    private static String uuid;
    @Autowired
    private JdbcTemplate jdbcTemplate;
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
        channels.remove(ctx.channel());
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

      /*  JdbcUserRepository jdbcUserRepository= new JdbcUserRepository(jdbcTemplate);
        System.out.println("==========================="+jdbcUserRepository.findById("123").toString());
        String str=jdbcUserRepository.findById("123").toString();*/
        Channel incoming=ctx.channel();
        SocketAddress remoteAdress=incoming.remoteAddress();
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n"+"Your remoteAdress:"+remoteAdress.toString()+"\nYou send:"+msg);
        if(msg.equals("print")){
            ClientTcpSocketChannelMap.getChannels().get(BootstrapServerHandler.uuid).writeAndFlush("\nrecevied from another socketchaneel,your uuid is:"+uuid);
        }
    }
    
    /*
     * 
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * 
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        
        /*ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");*/
        ctx.writeAndFlush( "Welcome to iGooDo Tcp service!\n"+"Your remoteAddress : "+ctx.channel().remoteAddress()+"\n");
        String uuid=ctx.channel().id().asLongText();
        BootstrapServerHandler.uuid=uuid;
        System.out.println(uuid);
        ClientTcpSocketChannelMap.addTcpSocketChannel(uuid, (SocketChannel) ctx.channel());

        super.channelActive(ctx);
    }
}