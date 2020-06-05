package com.masum.daggerretrofit.di;

import com.masum.daggerretrofit.di.auth.AuthModule;
import com.masum.daggerretrofit.di.auth.AuthViewModelModule;
import com.masum.daggerretrofit.ui.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
            modules ={ AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity authActivity();


}
