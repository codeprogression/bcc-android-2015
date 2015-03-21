package com.codeprogression.bccandroidv2.ui.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.codeprogression.bccandroidv2.R;
import com.codeprogression.bccandroidv2.api.models.Movie;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NowPlayingView extends RelativeLayout {

    @InjectView(R.id.now_playing) RecyclerView nowPlaying;
    @InjectView(R.id.progress) ProgressBar progress;

    private NowPlayingAdapter adapter;

    public NowPlayingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.inject(this);
        nowPlaying.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.reset(this);
    }

    public void bind(Movie.Collection collection){
        progress.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new NowPlayingAdapter(getContext());
            nowPlaying.setAdapter(adapter);
        }
        adapter.update(collection.getResults());
    }
}
