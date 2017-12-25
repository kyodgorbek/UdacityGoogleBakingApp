package com.example.yodgorbekkomilov.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

//import com.example.yodgorbekkomilov.bakingapp.pojo.Ingredient.*;

//import com.example.yodgorbekkomilov.bakingapp.pojo.Recipe.*;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {



    public static String MY_WIDGET_UPDATE = "android.appwidget.action.MY_OWN_WIDGET_UPDATE";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        if(MY_WIDGET_UPDATE.equals(intent.getAction())){

            Bundle extras = intent.getExtras();
            if(extras!=null) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisAppWidget = new ComponentName(context.getPackageName(), RecipeWidgetProvider.class.getName());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

                onUpdate(context, appWidgetManager, appWidgetIds);
            }

            //Toast.makeText(context, "onReceiver()", Toast.LENGTH_LONG).show();
        }
    }


    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetId) {


        final int N = appWidgetId.length;
        for (int i = 0; i < N; i++) {
            RemoteViews remoteViews = updateAppWidgetListView(context, appWidgetId[i]);
            appWidgetManager.updateAppWidget(appWidgetId[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetId);
    }

    private RemoteViews updateAppWidgetListView(Context context, int appWidgetId) {
        // which layout to show on widget
    //  List<Ingredient> ingredients = new ArrayList<>();
    //SharedPreferences appSharedPreferences = PreferenceManager
    //      .getDefaultSharedPreferences(context);
    //SharedPreferences.Editor prefsEditor = appSharedPreferences.edit();
    //Gson gson = new Gson();
    //  String json = gson.toJson(ingredients);
    //Type type = new TypeToken<List<Ingredient>>() {
    //}.getType();
    //ingredients = gson.fromJson(json, type);
    //prefsEditor.putString("MyIngredients", json);


    //prefsEditor.commit();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), com.example.yodgorbekkomilov.bakingapp.R.layout.recipe_widget);
        Intent svcIntent = new Intent(context, WidgetService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(appWidgetId, com.example.yodgorbekkomilov.bakingapp.R.id.listViewWidget, svcIntent);
        remoteViews.setEmptyView(com.example.yodgorbekkomilov.bakingapp.R.id.listViewWidget, com.example.yodgorbekkomilov.bakingapp.R.id.empty_view);

        return remoteViews;


    }


    // Construct the RemoteViews object
    // RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);


    // Instruct the widget manager to update the widget
    //  appWidgetManager.updateAppWidget(appWidgetId, views);


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyIngredients", Context.MODE_PRIVATE);

        // RemoteViewsService.RemoteViewsFactory remoteViewsFactory


        // There may be multiple widgets active, so update all of them
        for (int appWidgetIds : appWidgetId) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

