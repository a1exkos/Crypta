package com.example.crypta.Model.Retrofit2;

public class UtilsApi {

    public static RequestInterface getAllRequests() {
        return RetrofitCreate.getRetrofit("https://min-api.cryptocompare.com/").create(RequestInterface.class);
    }
}
