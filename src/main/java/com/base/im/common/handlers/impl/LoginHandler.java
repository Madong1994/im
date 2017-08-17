package com.base.im.common.handlers.impl;

import com.base.im.common.IMPacket;
import com.base.im.common.handlers.BaseHandleImpl;
import com.base.im.common.handlers.BaseHandler;
import com.base.im.common.protof.RequestModel;
import com.base.im.common.protof.ResponseModel;
import com.base.im.common.util.annotation.IMInterceptor;
import com.base.im.common.util.annotation.IMRequest;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: LoginHandler.class
 * @包名: com.base.im.common.handlers.impl
 * @描述: 登录
 * @所属: 华夏九鼎
 * @日期: 2017/8/16 14:34
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
@IMRequest(requestCode = 0)
@IMInterceptor
public class LoginHandler implements BaseHandler {
    @Override
    public String init(RequestModel.ImRequest imRequest, ChannelContext<Object, IMPacket, Object> channelContext) {
        Aio.bindUser(channelContext,imRequest.getUniqueOne());
        IMPacket imPacket = ImResponse();
        Aio.send(channelContext,imPacket);
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
