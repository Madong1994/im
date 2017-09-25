package com.base.im.server.common.handlers;

import com.base.im.server.common.IMPacket;
import com.base.im.server.common.protof.RequestModel;
import org.tio.core.ChannelContext;


public interface BaseHandler {
    String init(RequestModel.ImRequest imRequest, ChannelContext<Object, IMPacket, Object> channelContext);
}
