package com.example.beerapp;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.beerapp.di.activity.ActivityComponent;
import com.example.beerapp.di.activity.DaggerActivity;
import com.example.beerapp.router.Router;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends DaggerActivity {

    @Inject
    Router router;

    @BindView(R.id.appbar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(appBar);
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
