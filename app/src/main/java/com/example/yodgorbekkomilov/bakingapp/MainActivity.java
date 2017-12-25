package com.example.yodgorbekkomilov.bakingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yodgorbekkomilov.bakingapp.adapter.ReceiptAdapter;
import com.example.yodgorbekkomilov.bakingapp.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    public static final String RECIPE_KEY = "myRecipeKey";
    public ReceiptAdapter adapter;
    public Call<ArrayList<Recipe>> examples;

    public MyApiEndpointInterface api;
    public Button button;
    public TextView textView;
    public RecyclerView recyclerView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_child_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        ab.setTitle(getString(R.string.app_name));

        initViews();


    }



    public void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    public  void loadJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MyApiEndpointInterface apiEndpointInterface = retrofit.create(MyApiEndpointInterface.class);
        Call<ArrayList<Recipe>> call = apiEndpointInterface.getId();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {


                ArrayList<Recipe> examples = response.body();
                Log.d("RETRO RESPONSE", examples.get(0).getName());

                // adapter = new ReceiptAdapter(examples);
                adapter = new ReceiptAdapter(examples, new ReceiptAdapter.OnItemClickListener() {


                    @Override
                    public void onItemClick(Recipe example) {

                        Intent intent = new Intent(MainActivity.this, ReceiptActivity.class);


                        intent.putExtra("myRecipeKey", example);
                        startActivity(intent);

                    }

                    @Override
                    public void onItemClick(View view) {

                    }


                }, MainActivity.this);
                recyclerView.setAdapter(adapter);


            }
            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }







        });


    }
}

















