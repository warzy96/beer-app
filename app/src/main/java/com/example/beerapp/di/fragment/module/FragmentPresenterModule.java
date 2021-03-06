package com.example.beerapp.di.fragment.module;

import com.example.beerapp.di.fragment.DaggerFragment;
import com.example.beerapp.di.fragment.FragmentComponent;
import com.example.beerapp.di.fragment.FragmentScope;
import com.example.beerapp.ui.beerlist.BeerListContract;
import com.example.beerapp.ui.beerlist.BeerListPresenter;
import com.example.beerapp.ui.searchlist.SearchBeersContract;
import com.example.beerapp.ui.searchlist.SearchBeersPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment daggerFragment;

    public FragmentPresenterModule(final DaggerFragment daggerFragment) {
        this.daggerFragment = daggerFragment;
    }

    private FragmentComponent getFragmentComponent() {
        return daggerFragment.getFragmentComponent();
    }

    @Provides
    @FragmentScope
    public BeerListContract.Presenter provideBeerListPresenter() {
        final BeerListPresenter beerListPresenter = new BeerListPresenter();
        getFragmentComponent().inject(beerListPresenter);
        return beerListPresenter;
    }

    @Provides
    @FragmentScope
    public SearchBeersContract.Presenter provideSearchBeersPresenter() {
        final SearchBeersPresenter searchBeersPresenter = new SearchBeersPresenter();
        getFragmentComponent().inject(searchBeersPresenter);
        return searchBeersPresenter;
    }

    @Provides
    @FragmentScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
