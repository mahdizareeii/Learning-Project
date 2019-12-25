package com.example.myapplication.RxJava.RxJavaWithRetrofit.Retrofit;

import com.example.myapplication.RxJava.RxJavaWithRetrofit.Model.Post;
import com.example.myapplication.RxJava.RxJavaWithRetrofit.Model.Comment;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestApi {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Observable<List<Comment>> getComments(@Path("id") int id);

}
