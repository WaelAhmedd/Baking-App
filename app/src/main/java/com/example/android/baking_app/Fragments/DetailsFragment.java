package com.example.android.baking_app.Fragments;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.baking_app.Adapters.IngredientsAdapter;
import com.example.android.baking_app.Adapters.StepsAdapter;
import com.example.android.baking_app.BakingAppWidget;
import com.example.android.baking_app.R;
import com.example.android.baking_app.Retrofit.IngredientsModel;
import com.example.android.baking_app.Retrofit.StepsModel;
import com.example.android.baking_app.Retrofit.TotalDataModel;

import java.util.ArrayList;



public class DetailsFragment extends android.support.v4.app.Fragment implements StepsAdapter.stepClickListener {
    RecyclerView stepsList,ingredientsList;
    TotalDataModel totalDataModel;
    StepsAdapter stepsAdapter;
    IngredientsAdapter ingredientsAdapter;
    ImageView imageView;
    Context context;
    ArrayList<StepsModel>stepsModels;
   public ArrayList<IngredientsModel>ingredientsModels;
    Button buttonWidget;
    boolean hab=false;

    StepsModel stepsModel;

    CardView ingredientsHeader,stepsHeader,ingredientsCard,stepsCard;
    ImageView ingIcon,stepIcon,ingIconRecycler,stepIconRecycler;


    public DetailsFragment() {
        // Required empty public constructor
    }
    onStepClickListener stepClickListener;
    public interface onStepClickListener{
        void setStep(StepsModel step);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_details, container, false);
        context=getContext();
        hab=true;
        if(getArguments()!=null)
        {
            totalDataModel= (TotalDataModel) getArguments().getSerializable("Recipe");
            ingredientsModels=totalDataModel.getIngredients();
            stepsModels=totalDataModel.getSteps();

        }
        ingIconRecycler=view.findViewById(R.id.ing_icon_recycler);
        stepIconRecycler=view.findViewById(R.id.step_icon_recycler);
        ingIcon=view.findViewById(R.id.ing_icon);
        stepIcon=view.findViewById(R.id.step_icon);
        ingredientsHeader=view.findViewById(R.id.ingredients_header_card_view);
        ingredientsCard=view.findViewById(R.id.ing_card);
        stepsHeader=view.findViewById(R.id.steps_header_card_view);
        stepsCard=view.findViewById(R.id.steps_card);
        defineCards();
        ingredientsList=view.findViewById(R.id.rv_ingredients);
        buttonWidget=view.findViewById(R.id.add_widget);
        imageView=view.findViewById(R.id.iv_recipe_detail);
        imageView.setImageResource(R.drawable.nuttela);
        stepsList=view.findViewById(R.id.rv_steps);
        ingredientsList.setLayoutManager(new LinearLayoutManager(context));
        stepsList.setLayoutManager(new LinearLayoutManager(context));
        stepsAdapter=new StepsAdapter(context,stepsModels,this);
        ingredientsAdapter=new IngredientsAdapter(context,ingredientsModels);
        stepsList.setAdapter(stepsAdapter);
        ingredientsList.setAdapter(ingredientsAdapter);

        buttonWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ArrayList<String>InPref= makeItString(ingredientsModels);
               setArrayPrefs("ingredients",InPref,context);


                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                        new ComponentName(getContext(), BakingAppWidget.class));

                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

                BakingAppWidget.updateAppWidget(getContext(), appWidgetManager, appWidgetIds);


                Toast.makeText(getActivity(), "added to the widget", Toast.LENGTH_SHORT).show();
            }
        });

        stepIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepsCard.setVisibility(View.VISIBLE);
                stepsHeader.setVisibility(View.INVISIBLE);
            }
        });
        ingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsCard.setVisibility(View.VISIBLE);
                ingredientsHeader.setVisibility(View.INVISIBLE);
            }
        });

        ingIconRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsCard.setVisibility(View.INVISIBLE);
                ingredientsHeader.setVisibility(View.VISIBLE);
            }
        });
            stepIconRecycler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stepsCard.setVisibility(View.INVISIBLE);
                    stepsHeader.setVisibility(View.VISIBLE);
                }
            });
    return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            stepClickListener=(onStepClickListener)context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+"sss");
        }
    }

    @Override
    public void onStepClick(int selectedStep) {
        String s=Integer.toString(selectedStep);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        stepsModel=stepsModels.get(selectedStep);
        stepClickListener.setStep(stepsModel);
    }
    public static void setArrayPrefs(String arrayName, ArrayList<String> array, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appWidget", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }
        private ArrayList<String> makeItString(ArrayList<IngredientsModel> ingredientsModels)
        {
            ArrayList<String>ingredientsInPref=new ArrayList<>();
            for (int i=0;i<ingredientsModels.size();i++)
            {
                String ingre=(i+1)+"-"+ingredientsModels.get(i).getQuantity()
                        +" "+ingredientsModels.get(i).getMeasure()
                        +" of "+ingredientsModels.get(i).getIngredient();
                ingredientsInPref.add(ingre);
            }
            return ingredientsInPref;
        }
    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appWidget", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;}


        private void defineCards()
        {
            ingredientsHeader.setVisibility(View.VISIBLE);
            stepsHeader.setVisibility(View.VISIBLE);
            stepsCard.setVisibility(View.INVISIBLE);
            ingredientsCard.setVisibility(View.INVISIBLE);
        }
}
