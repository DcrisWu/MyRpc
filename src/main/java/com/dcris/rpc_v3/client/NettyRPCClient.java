package com.dcris.rpc_v3.client;

import com.dcris.rpc_v3.common.RPCRequest;
import com.dcris.rpc_v3.common.RPCResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyRPCClient implements RPCClient {

    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private int port;

    public NettyRPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // netty client 初始化
    static {
        bootstrap = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup();
        // 启动器设置 channel 和 handler
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    // 因为netty的传输是异步的，发送一个请求就会立即返回一个值，而不是想要的相应的response
    @Override
    public RPCResponse sendRequest(RPCRequest rpcRequest) {
        try {
            // 连接服务器
            // 使用bootstrap对象连接指定的主机和端口，并同步等待连接结果
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // 获取连接成功后的channel 对象
            Channel channel = channelFuture.channel();
            // 发送数据
            // 通过通道对象将rpcRequest对象写入并刷新到服务器
            channel.writeAndFlush(rpcRequest);
            // 同步等待channel关闭，也就是等待服务端返回结果，
            // 调用sync()方法后，当前线程会阻塞，直到通道关闭，
            // 这样就可以保证在获取RPCResponse之前，通道已经接收到了服务器的响应，并将其存储在通道的属性中
            channel.closeFuture().sync();

            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数

            // 创建一个属性键对象，用于获取channel的属性值
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            // 从channel 的属性中获取值
            RPCResponse response = channel.attr(key).get();

            System.out.println(response);
            return response;

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
