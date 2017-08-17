package com.base.im.common.interceptor;

import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ${CLASS_NAME}
 * @包名: com.base.im.common.interceptor
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @所属: 华夏九鼎
 * @日期: 2017/8/17 11:49
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 * @版权: 2017 hxjd Inc. All rights reserved.
 * 注意：本内容仅限于华夏九鼎内部传阅，禁止外泄以及用于其他的商业目的
 */
public class TestInterceptor extends HandlerInterceptor {
    public TestInterceptor(Object object) {
        super(object);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("test---------》befor");
        String test = (String) method.invoke(proxy,args[0],args[1]);
        System.out.println("test---------》after");
        return super.invoke(proxy, method, args);

    }
}
