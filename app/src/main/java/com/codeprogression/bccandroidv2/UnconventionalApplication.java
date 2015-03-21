package com.codeprogression.bccandroidv2;

import android.app.Application;

import com.codeprogression.bccandroidv2.api.TmdbApiService;
import com.codeprogression.bccandroidv2.api.models.Configuration;
import com.codeprogression.bccandroidv2.ui.LauncherActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import timber.log.Timber;

public class UnconventionalApplication extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Singleton
    @Component(modules = {
            ApplicationModule.class,
            DataModule.class
    })
    public interface ApplicationComponent{

        Picasso picasso();
        TmdbApiService service();
        Configuration configuration();

        void inject(LauncherActivity launcherActivity);
    }

    public void inject(){
        component = Dagger_UnconventionalApplication_ApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
//        } else {
            // Timber.plant(new CrashlyticsTree());
        }
    }

}
