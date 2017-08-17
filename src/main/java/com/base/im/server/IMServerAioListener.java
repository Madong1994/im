package com.base.im.server;

//import com.base.im.IMcacheMap;
import com.base.im.common.IMPacket;
import org.tio.core.ChannelContext;
import org.tio.server.intf.ServerAioListener;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: IMServerAioListener
 * @包名: com.base.im.server
 * @描述: IM监听类
 * @所属: 华夏九鼎
 * @日期: 2017/6/11 13:51
 * @版本: V1.0
 * @创建人：hxjd
 * @修改人：hxjd
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class IMServerAioListener implements ServerAioListener<Object, IMPacket, Object> {
    @Override
    public void onAfterConnected(ChannelContext<Object, IMPacket, Object> channelContext, boolean b, boolean b1) throws Exception {
        System.out.println("---------------onAfterConnected");//连接成功！//（第一次连接成功调用。好像body是空的）
    }

    @Override
    public void onAfterSent(ChannelContext<Object, IMPacket, Object> channelContext, IMPacket imPacket, boolean b) throws Exception {
        System.out.println("---------------onAfterSent");//目测是服务端调用Aio.send()过会调用
    }

    @Override
    public void onAfterReceived(ChannelContext<Object, IMPacket, Object> channelContext, IMPacket imPacket, int i) throws Exception {
        System.out.println("---------------onAfterReceived");//好像是接收消息方法运行完成调用
    }

    @Override
    public void onAfterClose(ChannelContext<Object, IMPacket, Object> channelContext, Throwable throwable, String s, boolean b) throws Exception {
        System.out.println("---------------客户端断开"+channelContext.getUserid()+"onAfterClose");//终端断开连接
//        IMcacheMap.cacheMap.removeValue(channelContext);
    }
}
