package network;

import java.util.List;

import model.Repository;
import model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bruno.bertelli on 05/04/2017.
 */

public interface APIInterface {

    @GET("users/{user}")
    Call<User> doGetUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repository>> listRepositories(@Path("user") String user);

    @GET("users/{user}/followers")
    Call<List<User>> listFollowers(@Path("user") String user);

    @GET("users/{user}/following")
    Call<List<User>> listFollowing(@Path("user") String user);
}
