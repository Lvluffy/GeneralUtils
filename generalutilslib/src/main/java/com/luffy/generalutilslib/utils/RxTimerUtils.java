package com.luffy.generalutilslib.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by lvlufei on 2019/2/26
 *
 * @desc Rxjava2.x实现轮询定时器
 */
public class RxTimerUtils {

    private Disposable mDisposable;

    private RxTimerUtils() {
    }

    public static RxTimerUtils getInstance() {
        return RxTimerUtilsHelper.mRxTimerUtils;
    }

    private static class RxTimerUtilsHelper {
        private static final RxTimerUtils mRxTimerUtils = new RxTimerUtils();
    }

    /**
     * 指定毫秒后执行next操作
     *
     * @param delay 延时
     * @param unit  时间单位
     * @param next  回调
     */
    public void timer(long delay, TimeUnit unit, final IRxNext next) {
        Observable.timer(delay, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }

    /**
     * 每隔指定毫秒后执行next操作
     *
     * @param delay 延时
     * @param unit  时间单位
     * @param next  回调
     */
    public void interval(long delay, TimeUnit unit, final IRxNext next) {
        Observable.interval(delay, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    /**
     * 执行回调
     */
    public interface IRxNext {
        void doNext(long number);
    }

}
