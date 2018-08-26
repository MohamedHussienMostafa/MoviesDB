package com.comelite.moviesdb.features.movieslist;


import com.comelite.moviesdb.models.MergedResponse;
import com.comelite.moviesdb.models.MovieModel;

import java.util.ArrayList;

public interface MoviesListContract {
    interface View {
        void showContent(MergedResponse mergedResponse);

        void showError(String message);

        void showComplete();

        void showLoading();
    }

    interface Presenter {
        void loadPopularMovies();
    }
}
