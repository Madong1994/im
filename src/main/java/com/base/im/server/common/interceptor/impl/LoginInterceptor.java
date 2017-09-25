package com.base.im.server.common.interceptor.impl;

import com.base.im.server.common.IMPacket;
import com.base.im.server.common.interceptor.BaseInterceptor;
import org.tio.core.ChannelContext;

/**
 * Created by 马东 on 2017/8/23.
 */
public class LoginInterceptor implements BaseInterceptor {
    @Override
    public void before(ChannelContext<Object, IMPacket, Object> channelContext) {
        System.out.println("---wozai--before--");
    }

    @Override
    public void after(ChannelContext<Object, IMPacket, Object> channelContext) {
        System.out.println("---wozai--after--");
    }
}
