package com.base.im.server.common.interceptor;

import com.base.im.server.common.IMPacket;
import org.tio.core.ChannelContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class HandlerInterceptor implements InvocationHandler {
    private Object object;
    private BaseInterceptor interceptor;
    public HandlerInterceptor(Object object,BaseInterceptor interceptor){
        super();
        this.object = object;
        this.interceptor = interceptor;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("befor---------->HandlerInterceptor");
        interceptor.before((ChannelContext<Object, IMPacket, Object>)args[1]);
        String str = (String) method.invoke(object,args[0],args[1]);
        interceptor.after((ChannelContext<Object, IMPacket, Object>)args[1]);
        System.out.println("after---------->HandlerInterceptor"+str);
        return null;
    }
}
