package com.comelite.moviesdb.core.di.components;


import com.comelite.moviesdb.core.di.modules.AppModule;
import com.comelite.moviesdb.core.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
}
