package com.comelite.moviesdb.features.moviedetails.di;


import com.comelite.moviesdb.features.moviedetails.MovieDetailsContract;
import com.comelite.moviesdb.features.movieslist.MoviesListContract;
import com.comelite.moviesdb.features.movieslist.di.MoviesListScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsModule {
    private final MovieDetailsContract.View mView;

    public MovieDetailsModule(MovieDetailsContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @MovieDetailsScope
    MovieDetailsContract.View provideMovieDetailsContractView() {
        return mView;
    }
}
