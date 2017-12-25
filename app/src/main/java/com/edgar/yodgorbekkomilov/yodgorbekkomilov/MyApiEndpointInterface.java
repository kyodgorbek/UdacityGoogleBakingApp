package com.edgar.yodgorbekkomilov.yodgorbekkomilov;

import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Ingredient;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Recipe;
import com.edgar.yodgorbekkomilov.yodgorbekkomilov.pojo.Step;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yodgorbekkomilov on 6/21/17.
 */

public interface MyApiEndpointInterface  {
 

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Recipe>> getId();
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<Ingredient>       getQuantity();
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<Step>    getId1();




}
