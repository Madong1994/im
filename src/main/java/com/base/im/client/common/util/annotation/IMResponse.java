package com.base.im.client.common.util.annotation;

import java.lang.annotation.*;


@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IMResponse {
    int responseCode();
}
