package com.comelite.moviesdb.features.moviedetails.di;

import com.comelite.moviesdb.core.di.components.NetComponent;
import com.comelite.moviesdb.features.moviedetails.MovieDetailsActivity;
import com.comelite.moviesdb.features.movieslist.MoviesListActivity;
import com.comelite.moviesdb.features.movieslist.di.MoviesListModule;
import com.comelite.moviesdb.features.movieslist.di.MoviesListScope;

import dagger.Component;

@MovieDetailsScope
@Component(dependencies = NetComponent.class, modules = MovieDetailsModule.class)
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity activity);
}
