package com.example.android.baking_app.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    public static final String BASE_URL="https://d17h27t6h515a5.cloudfront.net";
    public  Retrofit retrofit;

    public Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
             retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;
    }
}
