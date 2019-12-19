package com.example.myapplication.RxJava;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class RxSample {

    //observable is used for emitting item
    private Observable<String> observable;
    //subscriber is used for consuming
    private Subscriber<String> subscriber;

    private Context context;

    public RxSample(Context context) {
        this.context = context;
    }

    //******************** sample one
    public void observable1(String text) {
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(text);
                subscriber.onCompleted();
            }
        });
    }

    public void subscriber1() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
        //now link them observable and subscribe
        observable.subscribe(subscriber);
    }

    //******************** sample two
    public void observable2(String text) {
        observable = Observable.just(text);
    }

    public void subscriber2() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
        //now link them observable and subscribe
        observable.subscribe(subscriber);
    }

    //******************** sample three
    public void observable3(ArrayList<String> arrayList) {
        observable = Observable.from(arrayList);
    }

    public void subscribe3() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
        //now link them observable and subscribe
        observable.subscribe(subscriber);
    }

    //******************** sample four

    public void observable4(String text) {
        observable = Observable.just(text).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + " changed String";
            }
        });
    }

    public void subscribe4() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
        //now link them observable and subscribe
        observable.subscribe(subscriber);
    }

    //******************** sample five

    public void observable5(String text) {
        observable = Observable.just(text).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return text + " changed String 1";
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return text + " changed String 2";
            }
        });
    }

    public void subscribe5() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
        observable.subscribe(subscriber);
    }

    //******************** sample six

    public void observable6(int number) {
        observable = Observable.just(number).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        });
    }

    public void subscribe6() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, "your number converted to string : " + s, Toast.LENGTH_SHORT).show();
            }
        };
        observable.subscribe(subscriber);
    }

}
