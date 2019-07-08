package com.example.android.baking_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.baking_app.R;
import com.example.android.baking_app.Retrofit.TotalDataModel;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.mainHolder> {

    private Context context;
    private ArrayList<TotalDataModel>recipes;
    final   private  ListItemClickListener mClickListener;
    public interface ListItemClickListener{
        void onListItemClick(int SelectedMovie);
    }

    public RecipesAdapter(Context context, ArrayList<TotalDataModel> recipes ,ListItemClickListener listItemClickListener ) {
        this.context = context;
        this.recipes = recipes;
        this.mClickListener=listItemClickListener;
    }

    public void setRecipes(ArrayList<TotalDataModel> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public ArrayList<TotalDataModel> getRecipes() {

        return recipes;
    }

    @NonNull
    @Override
    public mainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_list_item,parent,false);
        return new mainHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull mainHolder holder, int position) {
            TotalDataModel recipe=recipes.get(position);
        String name=recipe.getName();


           if(name.equals("Nutella Pie")) {

               holder.recioeImage.setImageResource(R.drawable.nuttela);

           }


           else if(name.equals("Brownies")) {
                holder.recioeImage.setImageResource(R.drawable.brownies);
         }

          else if(name.equals("Yellow Cake")) {
             holder.recioeImage.setImageResource(R.drawable.yellowcake);

           }
            else {
               holder.recioeImage.setImageResource(R.drawable.cheesecake);

            }

        holder.recipeName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return (recipes==null)?0:recipes.size();
    }


    class mainHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeName;
        ImageView recioeImage;
        CardView cardView;
        RelativeLayout relativeLayout;
    public mainHolder(@NonNull View itemView) {
        super(itemView);
        recipeName=itemView.findViewById(R.id.nam);
            recioeImage=itemView.findViewById(R.id.img);
            cardView=itemView.findViewById(R.id.cardView);
            relativeLayout=itemView.findViewById(R.id.rlativ);
        itemView.setOnClickListener(this);

    }

        @Override
        public void onClick(View view) {
            mClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
