package com.example.beerapp.di.fragment;

import com.example.beerapp.di.activity.ActivityComponent;
import com.example.beerapp.di.fragment.module.FragmentModule;
import com.example.beerapp.di.fragment.module.FragmentPresenterModule;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
        modules = {
                FragmentModule.class,
                FragmentPresenterModule.class
        }
)
public interface FragmentComponent extends FragmentComponentInjects {

    @Subcomponent.Builder
    interface Builder {

        Builder fragmentModule(final FragmentModule fragmentModule);

        FragmentComponent build();

        Builder fragmentPresenterModule(final FragmentPresenterModule fragmentPresenterModule);
    }

    final class Initializer {

        static public FragmentComponent init(final DaggerFragment fragment, final ActivityComponent activityComponent) {
            return activityComponent.fragmentComponentBuilder()
                    .fragmentModule(new FragmentModule(fragment))
                    .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                    .build();
        }

        private Initializer() {

        }
    }
}
