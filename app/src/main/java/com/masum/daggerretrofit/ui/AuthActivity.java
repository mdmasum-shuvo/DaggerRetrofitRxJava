package com.masum.daggerretrofit.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.masum.daggerretrofit.R;
import com.masum.daggerretrofit.databinding.ActivityAuthBinding;
import com.masum.daggerretrofit.models.User;
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

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.authenticateWithId(Integer.parseInt(binding.etLogin.getText().toString()));
            }
        });


        subcribeObserver();
    }

    private void progressLoading(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void progressHide(){
        binding.progressBar.setVisibility(View.GONE);
    }

    private void subcribeObserver() {
        viewModel.observerUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {

                    switch (userAuthResource.status) {
                        case LOADING:
                            progressLoading();
                            break;
                        case ERROR:
                            progressHide();
                            Log.e("data",userAuthResource.message);
                            break;
                        case AUTHENTICATED:
                            Log.e("data",userAuthResource.data.getEmail());

                            break;
                        case NOT_AUTHENTICATED:
                            break;
                    }
                }
            }
        });
    }

    private void setLogo() {
        requestManager.load(image).into(binding.image);
    }
}