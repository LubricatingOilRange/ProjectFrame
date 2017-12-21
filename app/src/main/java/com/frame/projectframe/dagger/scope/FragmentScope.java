package com.frame.projectframe.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ruibing.han on 2017/12/15.
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
    //创建一个Fragment作用域注解
}
