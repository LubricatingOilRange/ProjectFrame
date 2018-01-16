package com.frame.projectframe.module.rxjava;

import com.frame.projectframe.app.Constant;
import com.frame.projectframe.http.exception.AppException;
import com.frame.projectframe.http.response.ParamOneResponse;
import com.frame.projectframe.http.response.ParamThreeResponse;
import com.frame.projectframe.http.response.ParamTwoResponse;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理调度
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 没有基类  直接处理返回结果
     *
     * @param <T>
     */
    public static <T> FlowableTransformer<T, T> handleNoParamResult() {   //compose判断结果
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> response) {
                return response.flatMap(new Function<T, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(T t) throws Exception {
                        if (t != null) {
                            return createData(t);
                        } else {
                            return Flowable.error(new AppException("服务器异常", "-1"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 返回一个参数(result)的 返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ParamOneResponse<T>, T> handleOneParamResult() {   //compose判断结果
        return new FlowableTransformer<ParamOneResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ParamOneResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ParamOneResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ParamOneResponse<T> resultResponse) {
                        if (resultResponse.getData() != null) {
                            return createData(resultResponse.getData());
                        } else {
                            return Flowable.error(new AppException("服务器异常", "-1"));
                        }
                    }
                });
            }
        };
    }
    /**
     * 返回两个参数(result)的 返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ParamTwoResponse<T>, T> handleTwoParamResult() {   //compose判断结果
        return new FlowableTransformer<ParamTwoResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ParamTwoResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ParamTwoResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ParamTwoResponse<T> resultErrorResponse) {
                        if (!resultErrorResponse.isError()) {
                            return createData(resultErrorResponse.getData());
                        } else {
                            return Flowable.error(new AppException("服务器返回error", "-1"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 返回三个个参数(result)的 返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ParamThreeResponse<T>, T> handleThreeParamResult() {   //compose判断结果
        return new FlowableTransformer<ParamThreeResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ParamThreeResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ParamThreeResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ParamThreeResponse<T> baseResponse) {
                        if (baseResponse.getData().equals(Constant.SUCCESSFUL_CODE)) {//返回有数据的结果
                            return createData(baseResponse.getData());
                        } else {
                            return Flowable.error(new AppException(baseResponse.getMessage(), baseResponse.getErrorCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 自定义 -- 创建
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);//BUFFER--缓存策略，LATEST,DROP--需要什么就发射什么数据
    }
}
