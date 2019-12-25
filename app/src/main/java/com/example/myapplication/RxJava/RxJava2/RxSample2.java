package com.example.myapplication.RxJava.RxJava2;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxSample2 {

    private static final String TAG = "RX_JAVA";

    //whats happen for observable or subscribes adding to CompositeDisposable and clear it in onDestroyMethod;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RxSample2() {

    }

    public void getListRxWithFreezingUi() {
        //this operation doing before coming ui
        Observable<Task> observable = Observable.fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: called");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Task task) {
                Log.i(TAG, "onNext: " + Thread.currentThread().getName());
                Log.i(TAG, "onNext: " + task.getDescription());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: called");
            }
        });
    }

    public void getListRxWithoutFreezingUi() {
        //this operation doing with coming ui
        Observable<Task> observable = Observable.fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        Log.i(TAG, "test: " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                });

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: called");
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Task task) {
                Log.i(TAG, "onNext: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

        //we can adding the subscribe in dispose directly with this method
        compositeDisposable.add(observable.subscribe(new Consumer<Task>() {
            @Override
            public void accept(Task task) throws Exception {

            }
        }));
    }

    /*clear and dispose CompositeDisposable in on onDestroyMethod;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }*/
}
