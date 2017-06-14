package cn.tanjianff.igoodo.api.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("helloServerInitializer")
public class BootstrapServerInitializer extends ChannelInitializer<SocketChannel> {
    // 设置6秒检测chanel是否接受过心跳数据
    private static final int READ_WAIT_SECONDS = 120;

    // 定义客户端没有收到服务端的ping消息的最大次数
    private static final int MAX_UN_REC_PING_TIMES = 3;
    @Autowired
    private BootstrapServerHandler bootstrapServerHandler;
	
	@Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 以("\n")为结尾分割的 解码器
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        pipeline.addLast("LBFDecoder",new LineBasedFrameDecoder(1024));
        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder()); 
        pipeline.addLast("encoder", new StringEncoder());

        // 自己的逻辑Handler
        pipeline.addLast("handler", bootstrapServerHandler);
    }
}