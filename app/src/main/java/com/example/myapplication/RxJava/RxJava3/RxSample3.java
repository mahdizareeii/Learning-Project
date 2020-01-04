package com.example.myapplication.RxJava.RxJava3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxSample3 {

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
                        return i * 5;
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

    public void showToastWithRx(String a, String b, String c) {
        Observable<String> observable = Observable.just(a, b, c);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "operator";
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return null;
                    }
                }).switchMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return null;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
                Log.i("rxjava", "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Log.i("rxjava", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rxjava", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("rxjava", "onComplete: ");
            }
        });

    }

    public void addItemWithRx(String[] item) {
        Observable<String[]> observable = Observable.just(item);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        for (int i = 0; i < strings.length; i++) {
                            strings[i] = String.valueOf(new Random().nextInt());
                            Log.i("rxTag", strings[i]);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
