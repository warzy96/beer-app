package com.example.beerapp.di.activity;

import com.example.beerapp.di.activity.module.ActivityModule;
import com.example.beerapp.di.application.ApplicationComponent;
import com.example.beerapp.di.fragment.FragmentComponent;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        Builder activityModule(final ActivityModule activityModule);

        ActivityComponent build();
    }

    final class Initializer {

        static public ActivityComponent init(final DaggerActivity daggerActivity, final ApplicationComponent applicationComponent) {
            return applicationComponent.activityComponentBuilder()
                    .activityModule(new ActivityModule(daggerActivity))
                    .build();
        }

        private Initializer() {

        }
    }

    FragmentComponent.Builder fragmentComponentBuilder();
}
