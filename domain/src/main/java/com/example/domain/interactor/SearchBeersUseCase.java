package com.example.domain.interactor;

import com.example.domain.interactor.type.SingleQueryUseCase;
import com.example.domain.model.Beer;
import com.example.domain.repository.BeerRepository;

import java.util.List;

import io.reactivex.Single;

public final class SearchBeersUseCase implements SingleQueryUseCase<String, List<Beer>> {

    private final BeerRepository beerRepository;

    public SearchBeersUseCase(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Single<List<Beer>> execute(String name, String brewedBefore) {
        return beerRepository.searchBeers(name, brewedBefore);
    }
}
