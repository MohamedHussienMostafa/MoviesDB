package com.comelite.moviesdb.features;

import android.app.Activity;
import android.content.Intent;

import com.comelite.moviesdb.features.moviedetails.MovieDetailsActivity;
import com.comelite.moviesdb.models.MovieModel;

import static com.comelite.moviesdb.features.moviedetails.MovieDetailsActivity.MOVIE;

public class NavigationManager {

    public static void openMovieDetailsScreen(Activity activity, MovieModel movieModel) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(MOVIE, movieModel);
        activity.startActivity(intent);
    }

}
