package network;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import model.Repository;
import model.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bruno.bertelli on 05/04/2017.
 */

public class APIClient {

    private APIInterface apiInterface;
    private static APIClient INSTANCE;

    /**
     * Sets up the singleton instance
     *
     * @return Singleton instance
     */
    public static APIClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIClient();
        }
        return INSTANCE;
    }

    private APIClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiInterface = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(APIInterface.class);
    }

    public void getUser(String username) {
        Call<User> userResponse = apiInterface.doGetUser(username);
        userResponse.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful())
                    EventBus.getDefault().post(response.body());
                else {
                    EventBus.getDefault().post(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ResponseBody errorBody = new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                };
                EventBus.getDefault().post(retrofit2.Response.error(417, errorBody));
            }
        });
    }

    public void getUserRepositories(String username) {
        Call<List<Repository>> userRepositoriesResponse = apiInterface.listRepositories(username);
        userRepositoriesResponse.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful())
                    EventBus.getDefault().post(response.body());
                else {
                    EventBus.getDefault().post(response);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                ResponseBody errorBody = new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                };
                EventBus.getDefault().post(retrofit2.Response.error(417, errorBody));
            }
        });
    }

    public void getFollowers(String username) {
        Call<List<User>> followersResponse = apiInterface.listFollowers(username);
        followersResponse.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful())
                    EventBus.getDefault().post(response.body());
                else {
                    EventBus.getDefault().post(response);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                ResponseBody errorBody = new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                };
                EventBus.getDefault().post(retrofit2.Response.error(417, errorBody));
            }
        });
    }

    public void getFollowing(String username) {
        Call<List<User>> followingResponse = apiInterface.listFollowing(username);
        followingResponse.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful())
                    EventBus.getDefault().post(response.body());
                else {
                    EventBus.getDefault().post(response);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                ResponseBody errorBody = new ResponseBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public long contentLength() {
                        return 0;
                    }

                    @Override
                    public BufferedSource source() {
                        return null;
                    }
                };
                EventBus.getDefault().post(retrofit2.Response.error(417, errorBody));
            }
        });
    }
}
