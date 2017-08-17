package com.base.im.client;

import com.base.im.common.IMPacket;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ${CLASS_NAME}
 * @包名: com.base.im.client
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2017/8/16 11:16
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class IMClientAioListener implements ClientAioListener<Object, IMPacket, Object>  {
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
