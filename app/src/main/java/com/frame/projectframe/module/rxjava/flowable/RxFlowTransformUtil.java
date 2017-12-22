package com.frame.projectframe.module.rxjava.flowable;

import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by ruibing.han on 2017/12/22.
 */

//RxJava2 的转换类型操作符的使用
public class RxFlowTransformUtil {
    /**
     * map,flatMap,concatMap
     */

    /**
     * 一对一，1 - 1，2 - 2
     * 上游接收到一个数据1 就调用一次onNext(),在这调用了三次
     */
    public static void map() {
        Flowable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "数据：" + integer;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.t("map:" + s);
                    }
                });

    }

    /**
     * 一对多，多对多（无序的）
     * 1,2,3 -- 1,2,3（存在1，3，2）(上游将123进行保存后 连续发送数据给订阅者 -》 123) 只调用onNext()方法一次
     */
    public static void flatMap() {
        Flowable.just(1, 2, 3)
                .flatMap(new Function<Integer, Publisher<List<String>>>() {
                    @Override
                    public Publisher<List<String>> apply(Integer integer) throws Exception {
                        List<String> strings = new ArrayList<>();
                        return Flowable.just(strings);
                    }
                })
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        Logger.t("数据：" + s);
                    }
                });

    }

    /**
     * 一对多，多对多(相对与flagMap来说，concatMap是有序的)
     * 1，2，3 -- 接收到的数据一定是1，2，3
     */
    public static void concatMap() {
        Flowable.just(1, 2, 3)
                .concatMap(new Function<Integer, Publisher<List<String>>>() {
                    @Override
                    public Publisher<List<String>> apply(Integer integer) throws Exception {
                        List<String> strings = new ArrayList<>();
                        return Flowable.just(strings);
                    }
                })
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        Logger.t("数据：" + s);
                    }
                });

    }
}
