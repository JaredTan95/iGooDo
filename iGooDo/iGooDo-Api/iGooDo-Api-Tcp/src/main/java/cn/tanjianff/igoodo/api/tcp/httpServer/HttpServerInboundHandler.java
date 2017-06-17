package cn.tanjianff.igoodo.api.tcp.httpServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URI;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
    private static Log log = LogFactory.getLog(HttpServerInboundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //TODO:请求分发待完善
        if (msg instanceof HttpRequest) {

            HttpRequest request = (HttpRequest) msg;
            URI uri = new URI(request.getUri());

            System.out.println("Uri:" + uri);
            System.out.println("UriPath:" + uri.getPath());
            System.out.println("getQuery:" + uri.getQuery());
            System.out.println("getUserInfo:" + uri.getUserInfo());

            if (uri.getPath().equals("/favicon.ico")) {
                sendSomething(ctx,"Your Request Info:"+uri);
            }
            if (uri.getPath().equals("/")) {
                //发送主页信息
                this.sendWelcome(ctx);
            }
            if (uri.getPath().equals("/tanjian")) {
                sendSomething(ctx,"hello,"+uri);
            }

            //判断request请求是否是post请求
            if (request.getMethod().equals(HttpMethod.POST)) {
                sendSomething(ctx,"POST,"+uri);
            }
        }

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
            ctx.flush();*/
        }
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

    public void sendSomething(ChannelHandlerContext ctx,String string){
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