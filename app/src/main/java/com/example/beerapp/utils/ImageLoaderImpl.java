package com.example.beerapp.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public final class ImageLoaderImpl implements ImageLoader{

    private final Context context;

    public ImageLoaderImpl(Context context) {
        this.context = context;
    }

    @Override
    public void renderImage(String imageSource, ImageView imageView, float circularProgressbarStrokeWidth) {
        Glide.with(context)
                .load(imageSource)
                .apply(new RequestOptions().placeholder(initCircularProgressDrawable(circularProgressbarStrokeWidth)))
                .into(imageView);
    }

    private CircularProgressDrawable initCircularProgressDrawable(final float circularProgressbarStrokeWidth) {
        final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(circularProgressbarStrokeWidth);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
