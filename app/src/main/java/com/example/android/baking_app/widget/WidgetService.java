package com.example.android.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    public static Intent getIntent(Context context) {
        return new Intent(context, WidgetService.class);
    }
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


        return (new ListProvider(this.getApplicationContext()));
    }
}
