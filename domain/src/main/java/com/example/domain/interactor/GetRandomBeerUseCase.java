package com.example.domain.interactor;

import com.example.domain.interactor.type.FetchUseCase;
import com.example.domain.model.Beer;
import com.example.domain.repository.BeerRepository;

import io.reactivex.Single;

public final class GetRandomBeerUseCase implements FetchUseCase<Single<Beer>> {

    private final BeerRepository beerRepository;

    public GetRandomBeerUseCase(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Single<Beer> execute() {
        return beerRepository.getRandomBeer();
    }
}
