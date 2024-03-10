package com.example.p5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {
    private MutableLiveData<String> string;
    public LiveData<String> getString() {
        if (string == null) {
            string = new MutableLiveData<>();
            string.setValue("");
        }
        return string;
    }
    public void setString(String s) {
        this.string.setValue(s);
    }
}
