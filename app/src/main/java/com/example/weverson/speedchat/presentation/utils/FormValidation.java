package com.example.weverson.speedchat.presentation.utils;

import android.util.Patterns;

public class FormValidation {

    public static boolean checkInputEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean checkInputEquals(String value1, String value2){
        return value1.equals(value2);
    }

    public static boolean checkEmailValid(String email) {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
