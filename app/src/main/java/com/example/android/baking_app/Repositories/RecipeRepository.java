package com.example.android.baking_app.Repositories;

import android.arch.lifecycle.MutableLiveData;


import com.example.android.baking_app.Retrofit.MyRetrofit;
import com.example.android.baking_app.Retrofit.MyRetrofitInterface;
import com.example.android.baking_app.Retrofit.TotalDataModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private static RecipeRepository recipeRepositoryInstance;
    private MyRetrofitInterface myRetrofitInterface;
    private MyRetrofit myRetrofits;

    public static RecipeRepository  getInstance()
    {
        if (recipeRepositoryInstance==null)
        {
            recipeRepositoryInstance=new RecipeRepository();

        }
        return recipeRepositoryInstance;
    }


    public MutableLiveData<ArrayList<TotalDataModel>> getRecipes()
    {
        final MutableLiveData<ArrayList<TotalDataModel>> arrayListMutableLiveData=new MutableLiveData<>();
        myRetrofits=new MyRetrofit();

        myRetrofitInterface=myRetrofits.getRetrofit().create(MyRetrofitInterface.class);
        Call<ArrayList<TotalDataModel>> call=myRetrofitInterface.GetData();
        call.enqueue(new Callback<ArrayList<TotalDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TotalDataModel>> call, Response<ArrayList<TotalDataModel>> response) {
                arrayListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TotalDataModel>> call, Throwable t) {

            }
        });

        return arrayListMutableLiveData;
    }


  /* public MutableLiveData<ArrayList<Recipe>> getRecipes()
    {
        final MutableLiveData<ArrayList<Recipe>>arrayListMutableLiveData=new MutableLiveData<>();


        myRetrofit=new MyRetrofit();

        webServices=myRetrofit.getRetrofit().create(WebServices.class);


        Call<ArrayList<Recipe>> call=webServices.GetData();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable throwable) {

            }
        });


      webServices.GetData().enqueue(new Callback<ArrayList<Recipe>>() {
          @Override
          public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
            arrayListMutableLiveData.setValue(response.body());
          }

          @Override
          public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

          }
      });

      return arrayListMutableLiveData;

    }*/


}
