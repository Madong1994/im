package com.base.im.server;


import com.base.im.common.DispatcherHandler;
import com.base.im.common.IMAbsAioHandler;
import com.base.im.common.IMPacket;
import com.base.im.common.protof.RequestModel;
import com.base.im.common.util.HandlerCode;
import com.base.im.common.util.IMcacheMap;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.server.intf.ServerAioHandler;

/**
 * @author tanyaowu
 * @创建时间 2016年11月18日 上午9:13:15
 * @操作列表 编号 | 操作时间 | 操作人员 | 操作说明 (1) | 2016年11月18日 | tanyaowu | 新建类
 */
public class IMServerAioHandler extends IMAbsAioHandler implements ServerAioHandler<Object, IMPacket, Object> {


    /**
     * 处理消息
     * 1、解密
     * 2、分发到相应的处理类
     */
    @Override
    public Object handler(IMPacket packet, ChannelContext<Object, IMPacket, Object> channelContext)
            throws Exception {
        byte[] body = packet.getBody();
        if (body != null) {
            /**
             * 1、验证是否已登录，如果已经登录不拦截，如果没有登录，先登录
             */
            RequestModel.ImRequest imRequest = RequestModel.ImRequest.parseFrom(body);
            if (imRequest.getHandler() == HandlerCode.REQUEST) {
                DispatcherHandler.handler(imRequest, channelContext);
            }
        }
        return null;
    }
}

