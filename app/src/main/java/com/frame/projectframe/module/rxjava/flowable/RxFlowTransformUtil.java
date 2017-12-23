package com.frame.projectframe.module.rxjava.flowable;

import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by ruibing.han on 2017/12/22.
 */

//RxJava2 的转换类型操作符的使用
public class RxFlowTransformUtil {
    /**
     * Buffer(2) 缓存后每次发射两个数据，直到没有数据
     * Map (1 对1)
     * FlatMap 无序（1 对 1，多 对 多）
     * concatMap 有序（1 对 1，多 对 多）
     * GroupBy
     * Scan
     * Window
     */

    /**
     * buffer缓存的意思:将12345进行缓存后 每次发射2个
     */
    public static void buffer() {
        Flowable.just(1, 2, 3, 4, 5)
                .buffer(2)//一次发射2个
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Logger.i("buffer" + integers.toString());
                    }
                });
    }

    /**
     * 一对一，1 - 1，2 - 2
     * 上游接收到一个数据1 就调用一次onNext(),在这调用了三次
     */
    public static void map() {
        Flowable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Logger.i("map:" + integer);
                        return "数据：" + integer;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i("map:" + s);
                    }
                });

    }

    /**
     * 一对多，多对多（无序的）
     * 1,2,3 -- 1,2,3（存在1，3，2）(上游将123进行保存后 连续发送数据给订阅者 -》 123) 只调用onNext()方法一次
     */
    public static void flatMap() {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        Flowable.just("1", "2", "3")
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(final String dataString) throws Exception {
                        Logger.i("flatMap：" + dataString);
                        return Flowable.create(new FlowableOnSubscribe<String>() {
                            @Override
                            public void subscribe(FlowableEmitter<String> e) throws Exception {
                                try {
                                    e.onNext("数据：" + dataString);
                                    e.onComplete();
                                } catch (Exception e1) {
                                    e.onError(e1);
                                }
                            }
                        }, BackpressureStrategy.BUFFER);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.i("flatMap：" + s);
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
                        Logger.i("数据：" + s);
                    }
                });

    }

    /**
     * 一对一  每次发射一个数据都将其分组判断
     */
    public static void groupBy() {
        Flowable.range(0, 5)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Logger.i("groupBy:" + integer);
                        return integer % 2 == 0 ? "A" : "B";//分组的条件判断
                    }
                })
                .subscribe(new Consumer<GroupedFlowable<String, Integer>>() {
                    @Override
                    public void accept(final GroupedFlowable<String, Integer> stringIntegerGroupedFlowable) throws Exception {
                        stringIntegerGroupedFlowable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                //分组的组名和发射的数据
                                Logger.i("Group -->" + stringIntegerGroupedFlowable.getKey() + "  &&  OnNext -->" + integer);
                            }
                        });
                    }
                });
    }

    public static void scan() {
        Flowable.range(0, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
    }
}
