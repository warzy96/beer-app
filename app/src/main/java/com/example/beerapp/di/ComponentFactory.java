package com.example.beerapp.di;

import com.example.beerapp.app.BeerApplication;
import com.example.beerapp.di.activity.ActivityComponent;
import com.example.beerapp.di.activity.DaggerActivity;
import com.example.beerapp.di.application.ApplicationComponent;
import com.example.beerapp.di.fragment.DaggerFragment;
import com.example.beerapp.di.fragment.FragmentComponent;

public final class ComponentFactory {

    private ComponentFactory() {

    }

    public static ApplicationComponent createApplicationComponent(final BeerApplication beerApplication) {
        return ApplicationComponent.Initializer.init(beerApplication);
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
        return ActivityComponent.Initializer.init(daggerActivity, applicationComponent);
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment daggerFragment, final ActivityComponent activityComponent) {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent);
    }
}
