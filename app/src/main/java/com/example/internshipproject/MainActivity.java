package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public final static String apikey = "b9e090197ea794053e55d5932378f919";
    public static String categoryTopRated = "top_rated";
    public static String categoryPopular = "popular";
    private MovieResponse movieResponse;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onCreate initilazes the gridview to a popular search and starts the gridView on click listener
        gridView = findViewById(R.id.gridView);
        PopularCategorySearch();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, MovieDetailsActivity.class).putExtra("data", movieResponse.getResults().get(position)));
            }
        });

    }

    // Oncreation this creates the menu action buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.topRated:
                Toast.makeText(this, "top_rated was selected", Toast.LENGTH_SHORT).show();
                TopRatedCategorySearch();
                return true;
            case R.id.Popular:
                Toast.makeText(this,"popuar was selected", Toast.LENGTH_SHORT).show();
                PopularCategorySearch();
        }
        return super.onOptionsItemSelected(item);
    }


    public void TopRatedCategorySearch(){
        gridView = findViewById(R.id.gridView);
        ApiInterface myInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<MovieResponse> call = myInterface.getListOfMovies(categoryTopRated, apikey);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieResponse = response.body();
                MovieAdapter adapter = new MovieAdapter(movieResponse.getResults(), MainActivity.this);

                gridView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


    public void PopularCategorySearch(){
        ApiInterface myInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<MovieResponse> call = myInterface.getListOfMovies(categoryPopular, apikey);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieResponse = response.body();
                MovieAdapter adapter = new MovieAdapter(movieResponse.getResults(), MainActivity.this);

                gridView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


}