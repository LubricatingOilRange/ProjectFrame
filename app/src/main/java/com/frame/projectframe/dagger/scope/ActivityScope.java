package com.frame.projectframe.dagger.scope;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * Created by ruibing.han on 2017/12/15.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
    //创建一个Activity作用域注解
}
