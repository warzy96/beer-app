package com.example.beerapp.di.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.beerapp.di.ComponentFactory;
import com.example.beerapp.di.activity.DaggerActivity;

public abstract class DaggerFragment extends Fragment {
    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getFragmentComponent());
    }

    protected abstract void inject(FragmentComponent fragmentComponent);

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getDaggerActivity().getActivityComponent());
        }
        return fragmentComponent;
    }

    public DaggerActivity getDaggerActivity() {
        return ((DaggerActivity) getActivity());
    }

}
