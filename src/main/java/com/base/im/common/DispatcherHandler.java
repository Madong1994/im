package com.base.im.common;

import com.base.im.common.handlers.BaseHandleImpl;
import com.base.im.common.handlers.BaseHandler;
import com.base.im.common.interceptor.HandlerInterceptor;
import com.base.im.common.protof.RequestModel;
import com.base.im.common.util.annotation.IMInterceptor;
import com.base.im.common.util.annotation.IMRequest;
import com.base.im.common.util.tool.ClassScaner;
import com.base.im.common.util.tool.StringUtils;
import com.jfinal.log.Log;
import org.tio.core.ChannelContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: DispatcherHandler.class
 * @包名: com.base.im.common
 * @描述: 通过@IMRequst注解寻找处理类
 * @所属: 华夏九鼎
 * @日期: 2017/8/16 14:51
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class DispatcherHandler {
    private static final Log log = Log.getLog(DispatcherHandler.class);
    private static List<Class<BaseHandler>> BaseHandleImplClassList = ClassScaner.scanSubClass(BaseHandler.class);

    public static String handler(RequestModel.ImRequest imRequest, ChannelContext<Object, IMPacket, Object> channelContext) {
        if (BaseHandleImplClassList != null) {
            for (Class<?> impl : BaseHandleImplClassList) {
                IMRequest annotation = impl.getAnnotation(IMRequest.class);
                IMInterceptor interceptor = impl.getAnnotation(IMInterceptor.class);
                if (null != annotation) {
                    try {
                        if(interceptor != null){
                            BaseHandler baseHandle = (BaseHandler) impl.newInstance();
                            HandlerInterceptor handlerInterceptor = new HandlerInterceptor(baseHandle);
                            BaseHandler baseHandle1 = (BaseHandler) Proxy.newProxyInstance(handlerInterceptor.getClass().getClassLoader(),baseHandle.getClass().getInterfaces(),handlerInterceptor);
                            return baseHandle1.init(imRequest, channelContext);
                        }else {
                            Method method = impl.getMethod("init", RequestModel.ImRequest.class, ChannelContext.class);
                            Object object = impl.newInstance();
                            return (String) method.invoke(object, imRequest, channelContext);
                        }

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
            }
        }
        return null;
    }
}
