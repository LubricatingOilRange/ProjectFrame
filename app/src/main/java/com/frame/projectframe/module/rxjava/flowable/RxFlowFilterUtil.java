package com.frame.projectframe.module.rxjava.flowable;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;

/*
 * Created by ruibing.han on 2017/12/25.
 */

// RxJava2 的过滤类型操作符的使用
public class RxFlowFilterUtil {
    /**
     * deBounce
     */

    public static void deBounce() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                try {
                    for (int i = 0; i < 5; i++) {
                        emitter.onNext("emitter:" + i);
                        Thread.sleep(200);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER)
                .onBackpressureDrop()
                .debounce(600, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i("deBounce:" + s);
                    }
                });
    }
}
