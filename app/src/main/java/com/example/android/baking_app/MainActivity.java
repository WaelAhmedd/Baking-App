package com.example.android.baking_app;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.baking_app.Adapters.RecipesAdapter;

import com.example.android.baking_app.Fragments.DetailsFragment;
import com.example.android.baking_app.Retrofit.MyRetrofit;
import com.example.android.baking_app.Retrofit.MyRetrofitInterface;
import com.example.android.baking_app.Retrofit.TotalDataModel;
import com.example.android.baking_app.ViewModels.MainActivityViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.ListItemClickListener {
    private RecyclerView recpiesList;
    private RecipesAdapter recipesAdapter;

    private ArrayList<TotalDataModel>recipess;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        recpiesList=findViewById(R.id.recipes_list);
        setupRecyclerView();

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();
        mainActivityViewModel.getRecipeMutableLiveData().observe(this, new Observer<ArrayList<TotalDataModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<TotalDataModel> recipes) {


                recipesAdapter.setRecipes(recipes);
                recipesAdapter.notifyDataSetChanged();
                recpiesList.setAdapter(recipesAdapter);

                recipess=recipesAdapter.getRecipes();
                recipesAdapter.notifyDataSetChanged();


            }
        });
    }
    private void setupRecyclerView(){
        if(recipesAdapter==null)
        {
            recipesAdapter=new RecipesAdapter(MainActivity.this,recipess,this);

            recpiesList.setAdapter(recipesAdapter);
        }
    else {
            recipesAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onListItemClick(int SelectedMovie) {
        Bundle bundle=new Bundle();
        TotalDataModel totalDataModel=recipess.get(SelectedMovie);
        Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("Recipe",totalDataModel);
        startActivity(intent);
    }


}
