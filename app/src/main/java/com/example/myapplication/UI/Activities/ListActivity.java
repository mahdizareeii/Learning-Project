package com.example.myapplication.UI.Activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RxJava.RxJavaWithRetrofit.Model.Comment;
import com.example.myapplication.RxJava.RxJavaWithRetrofit.Model.Post;
import com.example.myapplication.RxJava.RxJavaWithRetrofit.RecyclerView.RecyclerViewAdapter;
import com.example.myapplication.RxJava.RxJavaWithRetrofit.Retrofit.ServiceGenerator;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListActivity extends BaseActivity {

    public static final String TAG = "BaseActivity";

    private RecyclerView recyclerView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();

        getPostObservable().subscribeOn(Schedulers.io()).flatMap(new Function<Post, ObservableSource<Post>>() {
            @Override
            public ObservableSource<Post> apply(Post post) throws Exception {
                return getCommentsObservable(post);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Post>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Post post) {
                updatePost(post);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void updatePost(Post post) {
        adapter.updatePost(post);
    }

    private Observable<Post> getCommentsObservable(final Post post) {
        return ServiceGenerator.getRequestApi().getComments(post.getId()).map(new Function<List<Comment>, Post>() {
            @Override
            public Post apply(List<Comment> comments) throws Exception {
                int delay = ((new Random()).nextInt(5) + 1) * 1000;
                Thread.sleep(delay);
                post.setComments(comments);
                return post;
            }
        }).subscribeOn(Schedulers.io());
    }

    private Observable<Post> getPostObservable() {
        return ServiceGenerator.getRequestApi().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(List<Post> posts) throws Exception {
                        adapter.setPosts(posts);
                        return Observable.fromIterable(posts).subscribeOn(Schedulers.io());
                    }
                });
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }
}
