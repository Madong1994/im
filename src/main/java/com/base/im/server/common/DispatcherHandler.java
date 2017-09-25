package com.base.im.server.common;

import com.base.im.server.common.handlers.BaseHandler;
import com.base.im.server.common.interceptor.BaseInterceptor;
import com.base.im.server.common.interceptor.HandlerInterceptor;
import com.base.im.server.common.protof.RequestModel;
import com.base.im.server.common.util.annotation.IMInterceptor;
import com.base.im.server.common.util.annotation.IMRequest;
import com.base.im.server.common.util.tool.ClassScaner;
import com.jfinal.log.Log;
import org.tio.core.ChannelContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class DispatcherHandler {
    public static BaseInterceptor b_interceptor;//拦截器逻辑接口
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
                            //使用注解拦截器
                            BaseHandler baseHandle = (BaseHandler) impl.newInstance();
                            interThrows();
                            HandlerInterceptor handlerInterceptor = new HandlerInterceptor(baseHandle,b_interceptor);
                            BaseHandler baseHandle1 = (BaseHandler) Proxy.newProxyInstance(handlerInterceptor.getClass().getClassLoader(),baseHandle.getClass().getInterfaces(),handlerInterceptor);
                            return baseHandle1.init(imRequest, channelContext);
                        }else {
                            //没有使用拦截器
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
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
            }
        }
        return null;
    }
    private static void interThrows() throws Exception{
        if(b_interceptor == null) {
            throw new NullPointerException("regiestInterceptor=null,可能原因注册地方出错，请换在IMServerAioHandler类中registInterceptor()方法中注册");
        }
    }
}
