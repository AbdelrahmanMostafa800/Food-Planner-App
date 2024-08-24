package com.example.mealmate.model.userrepo;

import android.content.Context;

public interface UserAuthReposatoryInterface {
    String getUserId();

    boolean loginOut(Context context);
}
