package per.fxq.secondExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			Bootstrap clientBootstrap = new Bootstrap();
			clientBootstrap.group(clientGroup)
					.channel(NioSocketChannel.class)
					.handler(new SocketClientInitializer());
			ChannelFuture channelFuture = clientBootstrap.connect("localhost", 8899);
			channelFuture.channel().closeFuture().sync();
		} finally {
			clientGroup.shutdownGracefully();
		}

	}
}
