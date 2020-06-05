package com.masum.daggerretrofit.di;

import androidx.lifecycle.ViewModelProvider;
import com.masum.daggerretrofit.viewmodels.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory factory);
}
