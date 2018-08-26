package com.comelite.moviesdb.features.moviedetails;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    public Retrofit retrofit;
    MovieDetailsContract.View mView;

    @Inject
    public MovieDetailsPresenter(Retrofit retrofit, MovieDetailsContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }
}
