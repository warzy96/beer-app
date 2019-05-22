package com.example.beerapp.di.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beerapp.app.BeerApplication;
import com.example.beerapp.di.ComponentFactory;

public abstract class DaggerActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getActivityComponent());
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getMovieApplication().getApplicationComponent());
        }
        return activityComponent;
    }

    private BeerApplication getMovieApplication() {
        return BeerApplication.from(this);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
