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
    private final Subject<Object> rxBus;

    private RxBus() {
        rxBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return RxBusHolder.instance;
    }

    private static class RxBusHolder {
        private static final RxBus instance = new RxBus();
    }

    public void send(Object object) {
        rxBus.onNext(object);
    }

    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return rxBus.ofType(eventType);
    }
}
