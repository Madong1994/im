package com.base.im.client;


import com.base.im.common.Const;
import com.base.im.common.IMPacket;

import com.base.im.common.protof.RequestModel;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IMClientStarter {
	private static Node serverNode = null;
	private static AioClient<Object, IMPacket, Object> aioClient;
	private static ClientGroupContext<Object, IMPacket, Object> clientGroupContext = null;
	private static ClientAioHandler<Object, IMPacket, Object> aioClientHandler = null;
	private static ClientAioListener<Object, IMPacket, Object> aioListener = null;
	private static ReconnConf<Object, IMPacket, Object> reconnConf = new ReconnConf<Object, IMPacket, Object>(
			5000L);// 用来自动连接的，不想自动连接请传null

	public static void main(String[] args) throws Exception {
		String serverIp = "127.0.0.1";
		int serverPort = Const.PORT;
		serverNode = new Node(serverIp, serverPort);
		aioClientHandler = new IMClientAioHandler();
		aioListener = new IMClientAioListener();

		clientGroupContext = new ClientGroupContext(aioClientHandler, aioListener, reconnConf);
		aioClient = new AioClient(clientGroupContext);

		ClientChannelContext<Object, IMPacket, Object> clientChannelContext = aioClient.connect(serverNode);

		// 以下内容不是启动的过程，而是属于发消息的过程
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String inString = in.readLine();
//
			RequestModel.ImRequest.Builder imBuilder = RequestModel.ImRequest.newBuilder();
			imBuilder.setHandler(1);
			imBuilder.setCode(1);
			RequestModel.ImRequest imRequest = imBuilder.build();
			byte[] outs = imRequest.toByteArray();
			IMPacket packet = new IMPacket();
//				packet.setBody(GZipUtil.doZip(JSON.toJSONString(r)));
			packet.setBody(outs);/**/
			Aio.send(clientChannelContext, packet);
//			}
		}
	}
}
