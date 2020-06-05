package com.masum.daggerretrofit.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    @Inject
    public AuthViewModel() {
        Log.e("data", "auth view model is working");
    }
}
