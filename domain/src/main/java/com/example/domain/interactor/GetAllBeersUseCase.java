package com.example.domain.interactor;

import com.example.domain.interactor.type.FetchPagedUseCase;
import com.example.domain.model.Beer;
import com.example.domain.repository.BeerRepository;

import java.util.List;

import io.reactivex.Single;

public final class GetAllBeersUseCase implements FetchPagedUseCase<Single<List<Beer>>> {

    private final BeerRepository beerRepository;

    public GetAllBeersUseCase(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Single<List<Beer>> execute(final int page) {
        return beerRepository.getAllBeers(page);
    }
}
