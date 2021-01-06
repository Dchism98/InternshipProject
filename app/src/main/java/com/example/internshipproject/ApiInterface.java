package com.example.internshipproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/{category}")
    Call<MovieResponse> getListOfMovies(
            @Path("category") String category,
            @Query("api_key") String api_key
    );

    //TODO MOVIE TRAILERS

    @GET("/3/movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(
            @Path("movie_id") int movieID,
            @Query("api_key") String api_key
    );

    //TODO GET Reviews
    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewResponse> listOfMovies(
            @Path("movie_id") int movieId,
            @Query("api_key") String api_key
    );


}
