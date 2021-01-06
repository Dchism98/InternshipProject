package com.example.internshipproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    ListView simpleList;
    public static String BASE_URL ="https://api.themoviedb.org";
    public static String apikey = "b9e090197ea794053e55d5932378f919";
    public MovieResponse.Result movie;
    public ReviewResponse something;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        Intent intent = getIntent();
        movie = (MovieResponse.Result) intent.getSerializableExtra("data");
        loadReview(movie);

    }


    public void loadReview(MovieResponse.Result movie){
        ApiInterface myInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ReviewResponse> call = myInterface.listOfMovies(movie.getId(), apikey);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if(response == null){
                    System.out.println("are result is null");
                }
                something = response.body();
                System.out.println(something.getResults().get(0).getContent());

                ReviewAdapter arrayAdapter = new ReviewAdapter(something.getResults(), ReviewActivity.this);

                simpleList.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });

    }

}
