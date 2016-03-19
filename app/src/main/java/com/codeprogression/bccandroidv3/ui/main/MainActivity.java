package com.codeprogression.bccandroidv3.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codeprogression.bccandroidv3.R;
import com.codeprogression.bccandroidv3.UnconventionalApplication;
import com.codeprogression.bccandroidv3.ui.main.dagger.DaggerMainActivityComponent;
import com.codeprogression.bccandroidv3.ui.main.dagger.MainActivityComponent;

public class MainActivity extends AppCompatActivity {

  private static MainActivityComponent component;

  public static void startActivity(Context context) {
    final Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  public static MainActivityComponent getComponent() {
    return component;
  }

  private void createComponent() {
    component = DaggerMainActivityComponent.builder()
        .applicationComponent(UnconventionalApplication.getComponent())
        .build();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    createComponent();
    setContentView(R.layout.activity_main);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
