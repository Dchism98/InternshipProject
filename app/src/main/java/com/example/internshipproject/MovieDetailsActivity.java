package com.example.internshipproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity implements TrailerAdapter.OnNoteListener {
    MovieResponse.Result movie;
    TextView titleView;
    TextView descriptionView;
    TextView voteAverageView;
    TextView releaseDateView;
    ImageView imageView;
    ImageButton favoriteButton;
    private TrailerAdapter trailerAdapter;
    private RecyclerView myRecyclerView;
    private View.OnClickListener clickListener;
    private List<TrailerResponse.Trailer> trailerList;

    ListView trailerListView;
    private TrailerResponse trailerResponse;
    public final static String apikey = "b9e090197ea794053e55d5932378f919";
    public static String BASE_URL = "https://api.themoviedb.org";
    public TrailerResponse something;
    TextView textUser;
    private SharedPreferences Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        favoriteButton = findViewById(R.id.imageButton);
        imageView = findViewById(R.id.movieImage);
        titleView = findViewById(R.id.movieTitle);
        descriptionView = findViewById(R.id.movieDescription);
        voteAverageView = findViewById(R.id.voteAverage);
        releaseDateView = findViewById(R.id.releaseDate);



        //TODO have it open trailers in browser or youtube

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            movie = (MovieResponse.Result) intent.getSerializableExtra("data");
            System.out.println(movie.getId());
            System.out.println(movie.getOriginalTitle());
            titleView.setText(movie.getTitle());
            descriptionView.setText(movie.getOverview());
            voteAverageView.setText(movie.getVoteAverage().toString());
            releaseDateView.setText(movie.getReleaseDate());
            voteAverageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MovieDetailsActivity.this, ReviewActivity.class).putExtra("data",movie));
                }
            });

            GlideApp.with(this)
                    .load("https://image.tmdb.org/t/p/w185/"+movie.getPosterPath())
                    .into(imageView);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<TrailerResponse> call = myInterface.getTrailers(movie.getId(),apikey);


        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                trailerList = response.body().getResults();
                loadDataList(trailerList);
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });


    }
    private void loadDataList(List<TrailerResponse.Trailer> usersList) {

        myRecyclerView = findViewById(R.id.myRecyclerView);
        trailerAdapter = new TrailerAdapter(usersList,this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieDetailsActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(trailerAdapter);
    }

    @Override
    public void OnNoteClick(int position) {
        //TODO This is where we open up the browser or youtube
        Uri uri = Uri.parse("https://www.youtube.com/watch?v="+trailerList.get(position).getKey());
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}