package com.example.data.network.client;

import com.example.data.network.mappers.BeerMapper;
import com.example.data.network.service.BeerService;
import com.example.domain.model.Beer;

import java.util.List;

import io.reactivex.Single;

public final class BeerClient {

    private final BeerService beerService;
    private final BeerMapper beerMapper;

    public BeerClient(BeerService beerService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
    }

    public Single<Beer> getRandomBeer() {
        return beerService.fetchRandomBeer().map(beerMapper::mapBeer);
    }

    public Single<List<Beer>> getAllBeers() {
        return beerService.fetchAllBeers().map(beerMapper::mapBeers);
    }

    public Single<List<Beer>> searchBeers(final String beerName, final String brewedBefore) {
        String brewedBeforeSearchParam = brewedBefore.replaceAll(" ", "_");
        return beerService.searchBeers(beerName, brewedBeforeSearchParam).map(beerMapper::mapBeers);
    }
}
