package com.base.im.client.common.handlers;

import com.base.im.client.common.IMPacket;
import com.base.im.client.common.protof.ResponseModel;
import org.tio.core.ChannelContext;


public interface BaseHandler {
    String init(ResponseModel.ImResponse imResponse, ChannelContext<Object, IMPacket, Object> channelContext);
}
