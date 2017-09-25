package com.base.im.server.common.util.annotation;

import java.lang.annotation.*;


@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IMRequest {
    int requestCode();
}
