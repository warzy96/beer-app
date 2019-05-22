package com.example.beerapp.ui.beerlist;

import com.example.beerapp.di.fragment.FragmentComponent;
import com.example.beerapp.ui.base.BaseFragment;

public final class BeerListFragment extends BaseFragment<BeerListContract.Presenter> implements BeerListContract.View {

    public static final String TAG = "BeerListFragment";

    public static BeerListContract.View newInstance() {
        return new BeerListFragment();
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public void render(BeersViewModel beersViewModel) {

    }
}
