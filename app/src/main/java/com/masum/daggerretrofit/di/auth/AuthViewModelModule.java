package com.masum.daggerretrofit.di.auth;

import androidx.lifecycle.ViewModel;

import com.masum.daggerretrofit.di.ViewModelKey;
import com.masum.daggerretrofit.viewmodels.AuthViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindViewModelKey(AuthViewModel viewModel);

}
