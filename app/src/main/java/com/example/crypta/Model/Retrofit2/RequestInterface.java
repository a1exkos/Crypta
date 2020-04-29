package com.example.crypta.Model.Retrofit2;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("data/price")
    Single<Response<ResponseBody>> getAllCoin(@Query("fsym") String currency, @Query("tsyms") String coins_name);

}
