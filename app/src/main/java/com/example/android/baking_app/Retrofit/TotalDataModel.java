package com.example.android.baking_app.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TotalDataModel implements Serializable
{
    @SerializedName("id")
    int id;
    String name;
    ArrayList<IngredientsModel> ingredients;
    ArrayList<StepsModel>steps;
    int servings;
    String image;

    public TotalDataModel(){}

    public TotalDataModel(int id, String name, ArrayList<IngredientsModel> ingredients, ArrayList<StepsModel> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<IngredientsModel> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(ArrayList<StepsModel> steps) {
        this.steps = steps;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<IngredientsModel> getIngredients() {
        return ingredients;
    }

    public ArrayList<StepsModel> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }




}
