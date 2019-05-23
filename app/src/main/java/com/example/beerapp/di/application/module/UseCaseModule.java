package com.example.beerapp.di.application.module;

import com.example.domain.interactor.GetAllBeersUseCase;
import com.example.domain.interactor.GetRandomBeerUseCase;
import com.example.domain.interactor.SearchBeersUseCase;
import com.example.domain.repository.BeerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UseCaseModule {

    @Provides
    @Singleton
    GetAllBeersUseCase provideGetAllBeersUseCase(final BeerRepository beerRepository) {
        return new GetAllBeersUseCase(beerRepository);
    }

    @Provides
    @Singleton
    GetRandomBeerUseCase provideGetRandomBeerUseCase(final BeerRepository beerRepository) {
        return new GetRandomBeerUseCase(beerRepository);
    }

    @Provides
    @Singleton
    SearchBeersUseCase provideSearchBeersUseCase(final BeerRepository beerRepository) {
        return new SearchBeersUseCase(beerRepository);
    }
}
