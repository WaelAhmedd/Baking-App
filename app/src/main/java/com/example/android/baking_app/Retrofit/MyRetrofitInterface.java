package com.example.android.baking_app.Retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyRetrofitInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<TotalDataModel>>GetData();
}
