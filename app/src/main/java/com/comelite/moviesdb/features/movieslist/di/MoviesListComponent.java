package com.comelite.moviesdb.features.movieslist.di;

import com.comelite.moviesdb.core.di.components.NetComponent;
import com.comelite.moviesdb.features.movieslist.MoviesListActivity;

import dagger.Component;

@MoviesListScope
@Component(dependencies = NetComponent.class, modules = MoviesListModule.class)
public interface MoviesListComponent {
    void inject(MoviesListActivity activity);
}
