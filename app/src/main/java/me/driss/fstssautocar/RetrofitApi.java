package me.driss.fstssautocar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
@GET("/")
Call<ResponseBody> setState(
        @Query("State") String state);

}



