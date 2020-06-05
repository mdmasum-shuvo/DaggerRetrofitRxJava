package com.masum.daggerretrofit.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.masum.daggerretrofit.R;
import com.masum.daggerretrofit.databinding.ActivityAuthBinding;
import com.masum.daggerretrofit.viewmodels.AuthViewModel;
import com.masum.daggerretrofit.viewmodels.ViewModelProviderFactory;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private ActivityAuthBinding binding;
    private AuthViewModel viewModel;

    @Inject
    Drawable image;
    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        setLogo();
    }

    private void setLogo() {
        requestManager.load(image).into(binding.image);
    }
}