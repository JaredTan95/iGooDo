package cn.tanjianff.igoodo.api.tcp.httpServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * Created by tanjian on 2017/6/11.
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 服务端，对请求解码
        pipeline.addLast("http-decoder",
                new HttpRequestDecoder());

        // 聚合器，把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse
        pipeline.addLast("http-aggregator",
                new HttpObjectAggregator(65536));

        // 服务端，对响应编码
        pipeline.addLast("http-encoder",
                new HttpResponseEncoder());
        // 块写入处理器
        pipeline.addLast("http-chunked",
                new ChunkedWriteHandler());
       /* *
         * http-request解码器
         * http服务器端对request解码，server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解

        pipeline.addLast("decoder", new HttpRequestDecoder());
        *
         * http-response解码器
         * http服务器端对response编码，server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码

        pipeline.addLast("encoder", new HttpResponseEncoder());*/

        /**
         * 压缩
         * Compresses an HttpMessage and an HttpContent in gzip or deflate encoding
         * while respecting the "Accept-Encoding" header.
         * If there is no matching encoding, no compression is done.
         */
     /*   pipeline.addLast("deflater", new HttpContentCompressor());*/

        pipeline.addLast("handler", new HttpServerInboundHandler());
    }
}
