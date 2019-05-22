package com.example.beerapp.app;

import android.app.Application;
import android.content.Context;

import com.example.beerapp.di.ComponentFactory;
import com.example.beerapp.di.application.ApplicationComponent;

public final class BeerApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static BeerApplication from(final Context context) {
        return (BeerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        injectMe();
    }

    private void injectMe() {
        applicationComponent.inject(this);
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
