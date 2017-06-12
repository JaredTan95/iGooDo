package cn.tanjianff.igoodo.api.tcp;

import cn.tanjianff.igoodo.api.tcp.httpServer.HttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
//注解方式注入bean，名字是helloServer
@Service("helloServer")
public class BootstrapServer {
    private static Logger log = Logger.getLogger(BootstrapServer.class);
    /**
     * 服务端监听的端口地址
     */
    private static final int tcpPortNumber = 7878;
    private static final int httpPortNumber = 7879;

    //自动装备变量，spring会根据名字或者类型来装备这个变量，注解方式不需要set get方法了
    @Autowired
    private BootstrapServerInitializer bootstrapServerInitializer;

    //程序初始方法入口注解，提示spring这个程序先执行这里
	@PostConstruct
    public void serverStart() throws InterruptedException{
    	 EventLoopGroup bossGroup = new NioEventLoopGroup();
         EventLoopGroup workerGroup = new NioEventLoopGroup();
         try {
             ServerBootstrap tcp = new ServerBootstrap();
             ServerBootstrap http = new ServerBootstrap();
             tcp.group(bossGroup, workerGroup);
             tcp.channel(NioServerSocketChannel.class);
             tcp.childHandler(bootstrapServerInitializer);

             // 保持连接数
             tcp.option(ChannelOption.SO_BACKLOG,128);
             //保持长连接状态
             tcp.option(ChannelOption.TCP_NODELAY,true).childOption(ChannelOption.SO_KEEPALIVE,true);

             //添加Http服务,通过监听不同端口区分TCP和HTTP连接
             http.group(bossGroup,workerGroup);
             http.channel(NioServerSocketChannel.class);
             http.childHandler(new HttpServerInitializer());
             http.option(ChannelOption.SO_BACKLOG,128).childOption(ChannelOption.SO_KEEPALIVE,true);

             // 服务器绑定端口监听
             ChannelFuture tcpCf = tcp.bind(tcpPortNumber).sync();
             ChannelFuture httpCf = http.bind(httpPortNumber).sync();

             // 监听服务器关闭监听
             tcpCf.channel().closeFuture().sync();
             httpCf.channel().closeFuture().sync();

             if(tcpCf.isSuccess()){
                 log.info("Long Tcp Connection Started Success!");
             }else {
                 log.error("Long Tcp Connection Started  Failed!");
             }

             if(httpCf.isSuccess()){
                 log.info("Long Http Connection Started Success!");
             }else {
                 log.info("Long Http Connection Started Failed!");

             }
             // 可以简写为
             /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
         } finally {
             bossGroup.shutdownGracefully();
             workerGroup.shutdownGracefully();
         }
    }
    
}