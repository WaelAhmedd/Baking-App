package com.example.android.baking_app.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.baking_app.R;
import com.example.android.baking_app.Retrofit.StepsModel;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.stepsHolder>  {

    Context context;
    ArrayList<StepsModel>stepsModels;
    stepClickListener mStepClick;


    public interface stepClickListener
    {
        void onStepClick(int selectedStep);
    }

    public StepsAdapter(Context context, ArrayList<StepsModel> stepsModels, stepClickListener mStepClick) {
        this.context = context;
        this.stepsModels = stepsModels;
        this.mStepClick=mStepClick;
    }

    @NonNull
    @Override
    public stepsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.step_row_item,parent,false);
            return new stepsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stepsHolder holder, int position) {
            StepsModel stepsModel=stepsModels.get(position);
            holder.step.setText(stepsModel.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return (stepsModels==null)?0:stepsModels.size();
    }

    class stepsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView step;
        public stepsHolder(@NonNull View itemView) {
            super(itemView);
            step=itemView.findViewById(R.id.tv_step);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mStepClick.onStepClick(getAdapterPosition());
        }
    }
}
