package br.com.victoriamachado.gitapp;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rm75283 on 28/10/2016.
 */
public interface GitAPI {

    @GET("/users/{user}")
    void getGithubModel(@Path("user") String user, Callback<GithubModel> githubModelCallback);
}
