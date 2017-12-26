package com.frame.projectframe.module.rxjava.flowable;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by ruibing.han on 2017/12/26.
 */

// RxJava2 的组合类型操作符的使用
public class RxFlowGroupUtil {
    /**
     * CombineLatest
     */

    /**
     * CombineLatest
     * 当两个Observables中的任何一个发射了一个数据时，将两个Observables数据通过指定的规则进行处理，将结果进行发射
     */
    public static void combineLatest() {
        Flowable<Long> flow1 = Flowable.interval(0, 500, TimeUnit.MILLISECONDS).take(3);
        Flowable<Long> flow2 = Flowable.interval(500, 500, TimeUnit.MILLISECONDS).take(3);

        Flowable.combineLatest(flow1, flow2, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long integer1, Long integer2) throws Exception {
                Logger.i("integer1:" + integer1 + " --- integer2:" + integer2);
                return integer1 + integer2;
            }
        })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        Logger.i("combineLatest:" + integer);
                    }
                });
        /**
         * 显示结果
         * 1 + 0 = 1   -  500 , 500
         * 1 + 1 = 2
         * 2 + 1 = 3
         * 2 + 2 = 4
         */

        /**
         * 0 , 500 , 1000 , 1500
         *
         */
    }
}
