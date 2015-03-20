package com.codeprogression.bccandroidv2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MovieActivity extends ActionBarActivity {
    private long id;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");
        title = extras.getString("title");
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
