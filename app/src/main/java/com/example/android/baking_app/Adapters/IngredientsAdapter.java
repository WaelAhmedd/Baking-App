package com.example.android.baking_app.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.baking_app.R;
import com.example.android.baking_app.Retrofit.IngredientsModel;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ingredientsHolder>{

    Context context;
    ArrayList<IngredientsModel>ingredientsModels;

    public IngredientsAdapter(Context context, ArrayList<IngredientsModel> ingredientsModels) {
        this.context = context;
        this.ingredientsModels = ingredientsModels;
    }

    @NonNull
    @Override
    public ingredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ingredient_row_item,parent,false);
        return new ingredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientsHolder holder, int position) {
        String ingridentSentence;

        IngredientsModel ingredientsModel=ingredientsModels.get(position);
        ingridentSentence=(position+1)+"- "+ingredientsModel.getQuantity()+" "+ingredientsModel.getMeasure()+" of "+ingredientsModel.getIngredient();
        holder.ingredient.setText(ingridentSentence);
    }

    @Override
    public int getItemCount() {
        return (ingredientsModels==null) ?0:ingredientsModels.size();
    }

    class ingredientsHolder extends RecyclerView.ViewHolder {

        TextView ingredient;
        public ingredientsHolder(@NonNull View itemView) {
            super(itemView);
            ingredient=itemView.findViewById(R.id.tv_ingredient);
        }
    }
}
