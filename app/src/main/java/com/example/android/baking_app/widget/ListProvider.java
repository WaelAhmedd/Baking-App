package com.example.android.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.baking_app.Fragments.DetailsFragment;
import com.example.android.baking_app.R;

import java.util.ArrayList;

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    ArrayList<String>ingredientsInPref;


    public ListProvider(Context context) {
        this.context = context;



    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
       ingredientsInPref= getArrayPrefs("ingredients",context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredientsInPref == null) {
            return 0;
        }
        return ingredientsInPref.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_list_row);
        String ing=ingredientsInPref.get(i);
        remoteView.setTextViewText(R.id.widget_item_textview,ing);
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("appWidget", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;}
}
