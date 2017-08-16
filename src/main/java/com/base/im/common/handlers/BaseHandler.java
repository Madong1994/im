package com.base.im.common.handlers;

import com.base.im.common.IMPacket;
import com.base.im.common.protof.RequestModel;
import org.tio.core.ChannelContext;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ${CLASS_NAME}
 * @包名: com.base.im.common.handlers
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2017/8/16 11:59
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface BaseHandler {
    String init(RequestModel.ImRequest imRequest,ChannelContext<Object, IMPacket, Object> channelContext);
}
