package com.example.yodgorbekkomilov.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.yodgorbekkomilov.bakingapp.pojo.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 11/22/17.
 */

public class ListProvider implements RemoteViewsService.RemoteViewsFactory {


    public List<Ingredient> ingredients = new ArrayList<Ingredient>();
    Object[] myArrayList = ingredients.toArray();

    Context context = null;


    public ListProvider(Context context, Intent intent) {
        this.context = context;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        //populateRecipeItem();
    }

    //  private Void populateRecipeItem() {
//        for (int i = 0; i < 10; i++) {
    //          RecipeItem recipeItem = new RecipeItem();
    //        recipeItem.ingredients = String.valueOf(+i);

//        }

    //      ingredients.add(myArrayList);


    //}


    @Override
    public void onCreate() {

    }


    @Override
    public void onDataSetChanged() {
        // Reading the Ingredients from the SharedPreferences
        SharedPreferences appSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = appSharedPreferences.getString("MyIngredients", "");
        Gson gson = new Gson();
        ingredients = gson.fromJson(json, new TypeToken<List<Ingredient>>() {
        }.getType());
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }


    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), com.example.yodgorbekkomilov.bakingapp.R.layout.list_rows);
        Ingredient ingredient = ingredients.get(position);

        remoteViews.setTextViewText(com.example.yodgorbekkomilov.bakingapp.R.id.recipe, ingredient.getIngredient());
        return remoteViews;
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
}
