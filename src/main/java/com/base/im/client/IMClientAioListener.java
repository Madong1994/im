package com.base.im.client;


import com.base.im.client.common.IMPacket;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;

public class IMClientAioListener implements ClientAioListener<Object, IMPacket, Object> {
    @Override
    public void onAfterConnected(ChannelContext<Object, IMPacket, Object> channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        System.out.println("-----心跳连接？尝试连接--------客户端onAfterConnected"+channelContext.getUserid()+isConnected+isReconnect);
    }

    @Override
    public void onAfterSent(ChannelContext<Object, IMPacket, Object> channelContext, IMPacket packet, boolean isSentSuccess) throws Exception {
        System.out.println("-------------客户端onAfterSent");
    }

    @Override
    public void onAfterReceived(ChannelContext<Object, IMPacket, Object> channelContext, IMPacket packet, int packetSize) throws Exception {
        System.out.println("-------------客户端onAfterReceived");
    }

    @Override
    public void onAfterClose(ChannelContext<Object, IMPacket, Object> channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        System.out.println("-------------服务端断开"+channelContext.getUserid()+"onAfterClose"+throwable.getMessage()+" "+remark+" "+isRemove);
    }
}
