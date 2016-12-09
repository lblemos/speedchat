package com.example.weverson.speedchat.utils;

import android.util.Patterns;

public class Validation {

    public static boolean checkEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean checkEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
