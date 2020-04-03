package per.fxq.thirdExample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
	public static void main(String[] args) throws Exception {
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			Bootstrap clientBootstrap = new Bootstrap();
			clientBootstrap.group(clientGroup).channel(NioSocketChannel.class)
					.handler(new ChatClientInitializer());
			Channel channel = clientBootstrap.connect("localhost", 8899).sync()
					.channel();
			BufferedReader buffreader = new BufferedReader(
					new InputStreamReader(System.in));
			for (;;) {
				channel.writeAndFlush(buffreader.readLine()+"\r\n");
			}
		} finally {
			System.out.println("客户端执行了");
			clientGroup.shutdownGracefully();
		}
	}
}
