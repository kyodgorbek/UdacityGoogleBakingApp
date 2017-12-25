package com.edgar.yodgorbekkomilov.yodgorbekkomilov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.RecyclerViewClickListener;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Recipe;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Step;

import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity implements RecyclerViewClickListener{

    public ArrayList<Step> stepsList;
    private Boolean mTableMode;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);




        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_child_toolbar);
        setSupportActionBar(myChildToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Get a support ActionBar corresponding to this toolbar
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Enable the Up button ab.setDisplayHomeAsUpEnabled(true);





        IngredientStepFragment ingredientStep = new IngredientStepFragment();

        Bundle bundle = new Bundle();
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("myRecipeKey")) {
            recipe = intent.getParcelableExtra("myRecipeKey");
        }



        bundle.putParcelable("myRecipeKey", recipe);
        ingredientStep.setArguments(bundle);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainDetailFragment, ingredientStep).commit();

        }



        if (findViewById(R.id.detailContainer) != null) {
            mTableMode = true;

            DetailFragment detailFragment = new DetailFragment();
            Bundle args = new Bundle();
           // Step step = recipe.getSteps().get(0);
            ArrayList<Step> stepsList = (ArrayList<Step>) recipe.getSteps();
            args.putParcelableArrayList("stepsList", (ArrayList) recipe.getSteps());
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.detailContainer, detailFragment).commit();



            //  DetailFragment detailFragment = new DetailFragment();
            // getSupportFragmentManager().beginTransaction().replace(R.id.mainDetailFragment, detailFragment).commit();
        } else {
            mTableMode = false;
        }
    }

    public boolean isTablet() {
        return mTableMode;
    }

    private void replaceFragment(int position) {
        //   Bundle extraBundle = new Bundle();
        //  Intent intent = getIntent();
       // Recipe recipe = intent.getParcelableExtra("myRecipeKey");
        // extraBundle.putParcelable("myRecipeKey", recipe);
        //IngredientStepFragment customerFragment = new IngredientStepFragment();
        //android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //customerFragment.setArguments(extraBundle);
        //ft.replace(R.id.container, customerFragment);
        //ft.commit();

        Bundle args  = new Bundle();
        args.putInt("position",position);
        ArrayList<Step> stepsList = (ArrayList<Step>) recipe.getSteps();
        args.putParcelableArrayList("stepsList",  stepsList);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.detailContainer, detailFragment).commit();
    }

    public void launchDetailActivity(int position) {
        ArrayList<Step> stepsList = (ArrayList<Step>) recipe.getSteps();

        Intent intent = new Intent(this, DetailActivity.class);
        //Attach step to Intent as extra
        intent.putParcelableArrayListExtra("stepsList", stepsList);
        intent.putExtra("position", position);
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    public void onItemClick(int position) {
        if (isTablet()) {
            replaceFragment(position);
        } else {

            launchDetailActivity(position);
        }
    }
}






