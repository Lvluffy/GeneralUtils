package com.luffy.utils.rxlib;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by lvlufei on 2018/11/3
 *
 * @name RxBus
 */
public class RxBus {
    private static final Subject<Object> rxBus = PublishSubject.create().toSerialized();

    public static void send(Object object) {
        rxBus.onNext(object);
    }

    public static <T> Observable<T> toObserverable(Class<T> eventType) {
        return rxBus.ofType(eventType);
    }
}
