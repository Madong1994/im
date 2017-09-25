package com.base.im.client.common.util;

import com.base.im.client.common.util.annotation.IMInterceptor;
import com.base.im.client.common.util.tool.ClassScaner;
import com.base.im.common.handlers.BaseHandleImpl;
import com.base.im.common.interceptor.BaseInterceptor;
import com.base.im.common.interceptor.HandlerInterceptor;
import com.jfinal.log.Log;

import java.lang.reflect.Proxy;
import java.util.List;


public class AnnotationUtil {
    public static BaseInterceptor regiestInterceptor;//拦截器逻辑接口
    private static final Log log = Log.getLog(AnnotationUtil.class);
    private static List<Class<BaseHandleImpl>> BaseHandleImplClassList = ClassScaner.scanSubClass(BaseHandleImpl.class);
    private static void handler(){
        if(BaseHandleImplClassList != null){
            for(Class<?> impl : BaseHandleImplClassList){
                IMInterceptor interceptor = impl.getAnnotation(IMInterceptor.class);
                if(interceptor != null){
                    try {
                        BaseHandleImpl baseHandle = (BaseHandleImpl) impl.newInstance();
                        HandlerInterceptor handlerInterceptor = new HandlerInterceptor(baseHandle,regiestInterceptor);
                        BaseHandleImpl baseHandle1 = (BaseHandleImpl) Proxy.newProxyInstance(handlerInterceptor.getClass().getClassLoader(),baseHandle.getClass().getInterfaces(),handlerInterceptor);
//                        baseHandle1.init()
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
