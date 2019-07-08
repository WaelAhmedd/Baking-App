package com.example.android.baking_app;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.example.android.baking_app.Fragments.DetailsFragment;
import com.example.android.baking_app.Retrofit.StepsModel;
import com.example.android.baking_app.Retrofit.TotalDataModel;

public class DetailsActivity extends AppCompatActivity implements DetailsFragment.onStepClickListener{
    DetailsFragment detailsFragment;
    TotalDataModel totalDataModel;

    boolean isTablet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        totalDataModel= (TotalDataModel) getIntent().getSerializableExtra("Recipe");
        getSupportActionBar().setTitle(totalDataModel.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       FragmentManager   fragmentManager = getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        detailsFragment=new DetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("Recipe",totalDataModel);
        detailsFragment.setArguments(bundle);



        isTablet=tst();

        if(!isTablet) {
            fragmentTransaction.replace(R.id.details_container, detailsFragment);
            fragmentTransaction.addToBackStack(null).commit();
        }
        else {
            fragmentTransaction.replace(R.id.details_containertab,detailsFragment);
            fragmentTransaction.commit();


        }

    }

    @Override
    public void setStep(StepsModel step) {
        if(!isTablet) {
            String v = Integer.toString(step.getId());
            Toast.makeText(this, "this is the id" + v, Toast.LENGTH_SHORT).show();
            int id = step.getId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            StepFragment stepFragment = new StepFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Step", step);
            bundle.putInt("id", id);
            bundle.putSerializable("total", totalDataModel);
            stepFragment.setArguments(bundle);
            Log.d("hh", fragmentManager.getBackStackEntryCount() + "");
            fragmentTransaction.replace(R.id.details_container, stepFragment).commit();
            Log.d("hh", fragmentManager.getBackStackEntryCount() + "");
        }
        else
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                StepFragment stepFragment = new StepFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Step", step);

                bundle.putSerializable("total", totalDataModel);
                stepFragment.setArguments(bundle);
                Log.d("hh", fragmentManager.getBackStackEntryCount() + "");
                fragmentTransaction.replace(R.id.step_containertab, stepFragment).commit();

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private boolean tst()
    {
     if(findViewById(R.id.tab_linearLayout)!=null)
     {
         return true;
     }
     else return false;
    }


}
