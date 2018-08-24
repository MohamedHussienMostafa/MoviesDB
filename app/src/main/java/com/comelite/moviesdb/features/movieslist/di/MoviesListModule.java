package com.comelite.moviesdb.features.movieslist.di;


import com.comelite.moviesdb.features.movieslist.MoviesListContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesListModule {
    private final MoviesListContract.View mView;

    public MoviesListModule(MoviesListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @MoviesListScope
    MoviesListContract.View providesMainScreenContractView() {
        return mView;
    }
}
