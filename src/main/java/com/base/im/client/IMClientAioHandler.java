package com.base.im.client;


import com.base.im.client.common.IMAbsAioHandler;
import com.base.im.client.common.IMPacket;
import com.base.im.client.common.protof.ResponseModel;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
//IMAbsAioHandler implements ClientAioHandler<Object, IMPacket, Object>
public class IMClientAioHandler extends IMAbsAioHandler implements ClientAioHandler<Object, IMPacket, Object> {
	/** 
	 * 处理消息
	 */
	@Override
	public Object handler(IMPacket packet, ChannelContext<Object, IMPacket, Object> channelContext) throws Exception
	{
		byte[] body = packet.getBody();
		if (body != null)
		{
			ResponseModel.ImResponse imResponse = ResponseModel.ImResponse.parseFrom(body);
//			String str = new String(body, IMPacket.CHARSET);
			System.out.println("收到消息handler：" +  imResponse.getObjectJson());
		}

		return null;
	}

	private static IMPacket heartbeatPacket = new IMPacket();

	/** 
	 * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
	 */
	@Override
	public IMPacket heartbeatPacket()
	{
		return heartbeatPacket;
	}
}
