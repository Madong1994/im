package com.base.im.client.common.interceptor.impl;

import com.base.im.client.common.interceptor.BaseInterceptor;
import com.base.im.common.IMPacket;
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
