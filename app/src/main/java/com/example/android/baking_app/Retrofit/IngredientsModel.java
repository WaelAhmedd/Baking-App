package com.example.android.baking_app.Retrofit;

import java.io.Serializable;

public class IngredientsModel implements Serializable {

    String measure;
    double quantity;
    String ingredient;

    public IngredientsModel(String measure, double quantity, String ingredient) {
        this.measure = measure;
        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getMeasure() {
        return measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getIngredient() {
        return ingredient;
    }
}
