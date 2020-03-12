package com.example.kidapp.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * this hide keyboard after move from editText
 */
public class KeyboardUtil {
    public static void closeKeyboard(View view){
        //View view= this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager inputMethodManager=(InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        }
    }
}
