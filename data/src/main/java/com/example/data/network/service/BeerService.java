package com.example.data.network.service;

import com.example.data.network.model.ApiBeer;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface BeerService {

    @GET("beers/random")
    Single<ApiBeer> fetchRandomBeer();

    @GET("beers")
    Single<List<ApiBeer>> fetchAllBeers();
}
