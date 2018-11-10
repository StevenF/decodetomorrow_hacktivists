package com.frio.steven.decodetomorrow.Util;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

/**
 * Created by John Steven Frio on 11/10/2018.
 */

public class Validators {



    public Validators(){

    }

    public boolean isValidLogin(TextInputLayout til_loginEmail, TextInputEditText tet_loginEmail, TextInputLayout til_loginPassword, TextInputEditText tet_loginPassword){

        String errorMesage = "This field is required.";

        boolean valid = true;

        if(tet_loginEmail.getText().toString().isEmpty()){
            til_loginEmail.setError(errorMesage);
            valid = false;
        }

        if(tet_loginPassword.getText().toString().isEmpty()){
            til_loginPassword.setError(errorMesage);
            valid = false;
        }

        return valid;

    }

}
