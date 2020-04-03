package per.fxq.fourExample;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandle extends ChannelInboundHandlerAdapter{
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if(evt instanceof IdleStateEvent){
			IdleStateEvent idleEvent = (IdleStateEvent)evt;
			String eventType = null;
			switch(idleEvent.state()){
				case IdleState.READER_IDLE:
					eventType="读空闲";
					break;
				case IdleState.WRITER_IDLE:
					eventType="写空闲";
					break;
				case IdleState.ALL_IDLE:
					eventType="读写空闲";
					break;
			}
		}
	}
}
