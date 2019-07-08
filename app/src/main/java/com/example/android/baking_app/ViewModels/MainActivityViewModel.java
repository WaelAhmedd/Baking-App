package com.example.android.baking_app.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.example.android.baking_app.Repositories.RecipeRepository;
import com.example.android.baking_app.Retrofit.TotalDataModel;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TotalDataModel>>recipeMutableLiveData;
    private RecipeRepository recipeRepository;

    public void init()
    {
        if(recipeMutableLiveData!=null)
        {
            return;

        }
        recipeRepository=RecipeRepository.getInstance();
        recipeMutableLiveData=recipeRepository.getRecipes();


    }

    public MutableLiveData<ArrayList<TotalDataModel>> getRecipeMutableLiveData() {
        return recipeMutableLiveData;
    }
}
