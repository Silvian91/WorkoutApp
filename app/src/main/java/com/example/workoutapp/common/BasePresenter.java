package com.example.workoutapp.common;

import androidx.annotation.NonNull;

public interface BasePresenter < T extends BaseView > {
    void setView(@NonNull T view);
    void start();
    void finish();
}
