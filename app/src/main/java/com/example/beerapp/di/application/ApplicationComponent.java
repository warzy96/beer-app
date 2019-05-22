package com.example.beerapp.di.application;

import com.example.beerapp.app.BeerApplication;
import com.example.beerapp.di.activity.ActivityComponent;
import com.example.beerapp.di.application.module.ApplicationModule;
import com.example.beerapp.di.application.module.DataModule;
import com.example.beerapp.di.application.module.ThreadingModule;
import com.example.beerapp.di.application.module.UseCaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                UseCaseModule.class,
                ThreadingModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        static public ApplicationComponent init(final BeerApplication beerApplication) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(beerApplication))
                    .dataModule(new DataModule())
                    .threadingModule(new ThreadingModule())
                    .useCaseModule(new UseCaseModule())
                    .build();
        }

        private Initializer() {

        }
    }

    ActivityComponent.Builder activityComponentBuilder();
}
