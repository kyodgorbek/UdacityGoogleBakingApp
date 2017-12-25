package com.example.yodgorbekkomilov.bakingapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.yodgorbekkomilov.bakingapp.adapter.IngredientAdapter;
import com.example.yodgorbekkomilov.bakingapp.pojo.Ingredient;
import com.example.yodgorbekkomilov.bakingapp.pojo.RecyclerViewClickListener;
import com.example.yodgorbekkomilov.bakingapp.pojo.Recipe;
import com.example.yodgorbekkomilov.bakingapp.pojo.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientStepFragment extends Fragment  {

    public static final String LIST_STATE_KEY = "player state";
    public   RecyclerView recyclerView;
     public Parcelable mListState;
     public int position;
     public static  int POSITION_KEY;
     public RecyclerView.LayoutManager mLayoutManager;
    RecyclerViewClickListener listener = new RecyclerViewClickListener() {
        @Override
        public void onItemClick(int position) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            startActivity(intent);

        }
    };
    private TextView myTextView;


    //public static IngredientStepFragment newInstance(RecyclerViewClickListener listener) {
        // Required empty public constructor
     //   IngredientStepFragment ingredientFragment = new IngredientStepFragment();

   //     ingredientFragment.listener = listener;
     //   return ingredientFragment;
 //   }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){

        }


        View rootView = inflater.inflate(R.layout.fragment_ingredient_step, container, false);
        //Intent intent = getActivity().getIntent();
        //(Recipe)intent.getParcelableExtra("myRecipeKey");
        Recipe recipe = getArguments().getParcelable("myRecipeKey");
        // intent.putExtra("myRecipeKey", examples);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.addAll(recipe.getIngredients());

        final List<Step> steps = new ArrayList<>();
        steps.addAll(recipe.getSteps());
        myTextView = (TextView) rootView.findViewById(R.id.ingredients);
        //String ingredientString = "";
       // for (Ingredient ingredient : ingredients) {
        //    ingredientString += ingredient.getIngredient() + "\n";
       // }
        //myTextView.setText(ingredientString);

        // Writing the loaded Ingredients into the SharedPreferences
        SharedPreferences appSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefsEditor = appSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ingredients);
        Type type = new TypeToken<List<Ingredient>>() {
        }.getType();
        // ingredients = gson.toJson(json, type);
        prefsEditor.putString("MyIngredients", json);
        prefsEditor.commit();





        // @todo fix this
        List<Object> items = new ArrayList<>();
        items.addAll(ingredients);
        items.addAll(steps);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyclerView);
        IngredientAdapter adapter = new IngredientAdapter((ReceiptActivity) getActivity(), items, getActivity());



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerView.setAdapter(adapter);
        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
       Parcelable parcelable = recyclerView.getLayoutManager().onSaveInstanceState();
        savedState.putParcelable(LIST_STATE_KEY, parcelable);
        savedState.getParcelable("my_key");
        recyclerView.getLayoutManager().onRestoreInstanceState(savedState);

        }




  //  protected void onRestoreInstanceState(Bundle state) {
    //    super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions



    //}

    @Override
    public void onResume() {
        super.onResume();

        if (mListState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(mListState);
        }
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        if (state != null) {
            mListState = state.getParcelable(LIST_STATE_KEY);
            recyclerView.getLayoutManager().onRestoreInstanceState(state);
           // state.putInt(String.valueOf(POSITION_KEY), position);
        }

    }










    public void setArguments(Step step) {
    }


}









