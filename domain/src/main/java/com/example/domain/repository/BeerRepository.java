package com.example.domain.repository;

import com.example.domain.model.Beer;

import java.util.List;

import io.reactivex.Single;

public interface BeerRepository {

    Single<Beer> getRandomBeer();

    Single<List<Beer>> getAllBeers();

    Single<List<Beer>> searchBeers(final String name, final String brewedBefore);
}
