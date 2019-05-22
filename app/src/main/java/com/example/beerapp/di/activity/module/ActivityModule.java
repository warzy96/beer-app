package com.example.beerapp.di.activity.module;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentManager;

import com.example.beerapp.di.activity.ActivityScope;
import com.example.beerapp.di.activity.DaggerActivity;
import com.example.beerapp.di.activity.ForActivity;
import com.example.beerapp.router.Router;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Context provideActivityContext() {
        return daggerActivity;
    }

    @Provides
    @ForActivity
    Router provideRouter(final FragmentManager fragmentManager) {
        return new Router(fragmentManager);
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return daggerActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return daggerActivity.getLayoutInflater();
    }

}
