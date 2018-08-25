package com.comelite.moviesdb.features.moviedetails;


import com.comelite.moviesdb.models.MergedResponse;

public interface MovieDetailsContract {
    interface View {
        void showContent(MergedResponse mergedResponse);

        void showError(String message);

        void showComplete();

        void showLoading();
    }

    interface Presenter {

    }
}
