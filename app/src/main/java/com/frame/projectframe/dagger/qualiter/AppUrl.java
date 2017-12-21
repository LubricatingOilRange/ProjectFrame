package com.frame.projectframe.dagger.qualiter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ruibing.han on 2017/12/15.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface AppUrl {
}
