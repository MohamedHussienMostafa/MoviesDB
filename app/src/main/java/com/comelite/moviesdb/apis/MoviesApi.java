package com.comelite.moviesdb.apis;

import com.comelite.moviesdb.core.constants.APIsConstants;
import com.comelite.moviesdb.models.MovieModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET(APIsConstants.POPULAR_MOVIES_API)
    Single<Response<ArrayList<MovieModel>>> getPopularMoviesList(
            @Query(APIsConstants.API_KEY_PARAM) String param);

    @GET(APIsConstants.TOP_RATED_MOVIES_API)
    Single<Response<ArrayList<MovieModel>>> getTopRatedMoviesList(
            @Query(APIsConstants.API_KEY_PARAM) String param);

    @GET(APIsConstants.UPCOMING_MOVIES_API)
    Single<Response<ArrayList<MovieModel>>> getUpcomingMoviesList(
            @Query(APIsConstants.API_KEY_PARAM) String param);

}
