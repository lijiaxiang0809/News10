package com.teach.news10.design;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teach.news10.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .apply(new RequestOptions().error(R.drawable.banner_default))
                .into(imageView);
    }

    /*@Override
    public ImageView createImageView(Context context) {
        return new RoundAngleImageView(context);
    }*/
}
