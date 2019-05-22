package com.example.data.network.model;

import java.util.LinkedList;
import java.util.List;

public final class ApiBeersList {


    private final List<ApiBeer> beers;

    public ApiBeersList() {
        this.beers = new LinkedList<>();
    }

    public List<ApiBeer> getBeers() {
        return beers;
    }
}
