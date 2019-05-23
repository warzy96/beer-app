package com.example.data;

import com.example.data.network.client.BeerClient;
import com.example.domain.model.Beer;
import com.example.domain.repository.BeerRepository;

import java.util.List;

import io.reactivex.Single;

public final class BeerRepositoryImpl implements BeerRepository {

    private final BeerClient beerClient;

    public BeerRepositoryImpl(BeerClient beerClient) {
        this.beerClient = beerClient;
    }

    @Override
    public Single<Beer> getRandomBeer() {
        return beerClient.getRandomBeer();
    }

    @Override
    public Single<List<Beer>> getAllBeers() {
        return beerClient.getAllBeers();
    }

    @Override
    public Single<List<Beer>> searchBeers(final String name, final String brewedBefore) {
        return beerClient.searchBeers(name, brewedBefore);
    }
}
