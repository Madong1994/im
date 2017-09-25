package com.base.im.client.common.handlers.impl;

import com.base.im.client.common.IMPacket;
import com.base.im.client.common.handlers.BaseHandler;
import com.base.im.client.common.protof.ResponseModel;
import com.base.im.client.common.util.annotation.IMInterceptor;
import com.base.im.client.common.util.annotation.IMResponse;
import org.tio.core.ChannelContext;


@IMResponse(responseCode = 0)
@IMInterceptor
public class LoginHandler implements BaseHandler {
    @Override
    public String init(ResponseModel.ImResponse imResponse, ChannelContext<Object, IMPacket, Object> channelContext) {
//        Aio.bindUser(channelContext,imResponse.getUniqueOne());
//        IMPacket imPacket = ImResponse();
//        Aio.send(channelContext,imPacket);
        return null;
    }
    private IMPacket ImResponse(){
        ResponseModel.ImResponse.Builder builder = ResponseModel.ImResponse.newBuilder();
        builder.setCode(0);//0表示成功，1表示失败
        builder.setHandler(0);//0表示响应，1表示请求
        builder.setObjectJson("登录成功！");
        ResponseModel.ImResponse imResponse = builder.build();
        IMPacket imPacket = new IMPacket();
        imPacket.setBody(imResponse.toByteArray());
        return imPacket;
    }
}
