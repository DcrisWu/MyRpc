package com.dcris.rpc_v3.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 同样的与服务端解码和编码格式
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 获取通道的管道对象，用于添加或删除处理器
        ChannelPipeline pipeline = ch.pipeline();
        // 添加一个长度域解码器，用于解码服务器发送的消息，根据长度域的值判断消息的完整性
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
        // 添加一个长度域编码器，用于编码客户端发送的消息，将消息的长度写入到前4个字节中
        pipeline.addLast(new LengthFieldPrepender(4));
        // 添加一个对象编码器，用于将RPCRequest对象序列化为字节数组
        pipeline.addLast(new ObjectEncoder());
        // 添加一个对象解码器，用于将字节数组反序列化为RPCResponse对象
        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return Class.forName(className);
            }
        }));
        // 添加一个自定义的处理器，用于处理服务器返回的RPCResponse对象
        pipeline.addLast(new NettyClientHandler());
    }
}
