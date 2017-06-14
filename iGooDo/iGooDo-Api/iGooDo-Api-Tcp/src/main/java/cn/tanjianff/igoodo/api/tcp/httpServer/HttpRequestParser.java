package cn.tanjianff.igoodo.api.tcp.httpServer;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanjian on 2017/6/13.
 * HTTP请求参数解析器, 支持GET, POST
 */
public class HttpRequestParser {
    private FullHttpRequest fullHttpRequest;

    public HttpRequestParser(FullHttpRequest fullHttpRequest) {
        this.fullHttpRequest = fullHttpRequest;
    }

    /**
     * 解析请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws BaseCheckedException
     * @throws IOException
     */
    public Map<String,String> parse() throws IOException {
        HttpMethod method=fullHttpRequest.method();
        Map<String,String> parseMap=new HashMap<>();
        if(HttpMethod.GET==method){
            //是GET请求
            QueryStringDecoder decoder=new QueryStringDecoder(fullHttpRequest.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parseMap.put(entry.getKey(), entry.getValue().get(0));
            });
        }else if(HttpMethod.POST==method){
            // 是POST请求
            HttpPostRequestDecoder decoder=new HttpPostRequestDecoder(fullHttpRequest);
            decoder.offer(fullHttpRequest);
            List<InterfaceHttpData> parmList=decoder.getBodyHttpDatas();
            for (InterfaceHttpData parm : parmList) {
                Attribute data = (Attribute) parm;
                parseMap.put(data.getName(), data.getValue());
            }
        }else {
            //不支持其它方法，待完善
            System.out.println("未完善其它Http Method!");
        }
        return parseMap;
    }
}
