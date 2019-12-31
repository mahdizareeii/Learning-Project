package com.example.myapplication.RxJava.RxJava3;

import android.content.Context;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxSample3{

    private Context context;

    public RxSample3(Context context) {
        this.context = context;
    }

    public void observableWithSubscribeActual(int a, int b, int c) {

        /*new Observable<Integer>(){
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {

            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer t) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        Observable<Integer> observable = new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(a);
                observer.onNext(b);
                observer.onNext(c);
            }
        };
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

    }

    public void observableWithJust(int a, int b, int c) {

        /*Observable.just(a,b,c)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer t) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

        Observable<Integer> observable = Observable.just(a, b, c);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    public void observableWithFilter(int a, int b, int c) {
        Observable<Integer> observable = Observable.just(a, b, c);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) throws Exception {
                if (i == 1) {
                    return false;
                }
                return true;
            }
        }).subscribe(getObserver());
    }

    public void observableWithMap(int a, int b, int c) {
        Observable<Integer> observable = Observable.just(a, b, c);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer i) throws Exception {
                        return  i*5;
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer t) {
                Toast.makeText(context, t + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


}
