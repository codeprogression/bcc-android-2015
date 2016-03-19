package com.codeprogression.bccandroidv3.ui.main;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.codeprogression.bccandroidv3.UnconventionalApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by richard on 3/18/16.
 */
public class ImageBindingAdapter {

  @BindingAdapter("imageUrl")
  public static void setImage(ImageView view, String imageUrl){
    Picasso picasso = UnconventionalApplication.getComponent().picasso();

    picasso.load(imageUrl).into(view);
  }
}
