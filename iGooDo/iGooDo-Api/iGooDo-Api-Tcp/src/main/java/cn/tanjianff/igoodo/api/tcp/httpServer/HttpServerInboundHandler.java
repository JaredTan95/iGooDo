package cn.tanjianff.igoodo.api.tcp.httpServer;

import cn.tanjianff.igoodo.api.tcp.ClientTcpSocketChannelMap;
import cn.tanjianff.igoodo.common.db.repository.JdbcRepository.JdbcUserRepository;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Service("httpServerInboundHandler")
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
    private static Log log = LogFactory.getLog(HttpServerInboundHandler.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Map<String, String> parmMap = new HttpRequestParser((FullHttpRequest) msg).parse(); // 将GET, POST所有请求参数转换成Map对象

        //TODO:请求分发待完善
      if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            URI uri = new URI(request.getUri());

            System.out.println("Uri:" + uri);
            System.out.println("UriPath:" + uri.getPath());
            System.out.println("getQuery:" + uri.getQuery());
            System.out.println("getUserInfo:" + uri.getUserInfo());

            if (uri.getPath().equals("/favicon.ico")) {
                sendSomething(ctx, "Your Request Info:" + uri);
            }
            if (uri.getPath().equals("/")) {
                //发送主页信息
                this.sendWelcome(ctx);
            }

            /*测试url*//*
             /*  if (uri.getPath().equals("/getUser")) {
                sendSomething(ctx, "hello," + uri);
                String string = jdbcUserRepository.findById("18323261979").toString();
                log.info(string);
            }*/

            /*对app发送打开请求参数做解析,WDSerialnum*/
            if (uri.getPath().equals("/open")) {
                try {
                    System.out.println(parmMap.get("User"));
                    System.out.println(parmMap.get("WDSerialnum"));
                    //TODO:做异常处理
              ClientTcpSocketChannelMap.getSocketChannel(parmMap.get("WDSerialnum"))
                        .writeAndFlush("0019OPEN"+parmMap.get("User"));
                    sendSomething(ctx, "{\"code\":\"0000\"}");
                } catch (Exception e) {
                    e.printStackTrace();
                    sendSomething(ctx, "{\"code\":\"1111\"");
                }
            }
        }
 /*
        if (msg instanceof HttpContent) {
     /*     HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            buf.release();

            String res = ":)\n" +
                    "Welcome to iGooDo's Netty Server!\n";
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH,
                    response.content().readableBytes());
            if (HttpHeaders.isKeepAlive(request)) {
                response.headers().set(CONNECTION, Values.KEEP_ALIVE);
            }
            ctx.write(response);
            ctx.flush();*//*
        }*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        ctx.close();
    }

    public void sendWelcome(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
        StringBuilder bufd = new StringBuilder();
        bufd.append("<h1>:)</h1>\n");
        bufd.append("<h2>Welcome to iGooDo's Netty Server!</h2>\n");
        ByteBuf byteBuf = Unpooled.copiedBuffer(bufd, CharsetUtil.UTF_8);
        response.content().writeBytes(byteBuf);
        byteBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    public void sendSomething(ChannelHandlerContext ctx, String string) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");
        StringBuilder bufd = new StringBuilder();
        bufd.append(string);
        ByteBuf byteBuf = Unpooled.copiedBuffer(bufd, CharsetUtil.UTF_8);
        response.content().writeBytes(byteBuf);
        byteBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}