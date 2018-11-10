package com.frio.steven.decodetomorrow;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {


    TextInputEditText tet_firstname, tet_lastname, tet_address, tet_email, tet_password, tet_passwordConfirmation, tet_mobileNumber, tet_landlineNumber;
    TextInputLayout til_firstname, til_lastname, til_address, til_email, til_password, til_passwordConfirmation, til_mobileNumber, til_landlineNumber;
    Button btn_signup, btn_haveAnAccount;

    String strFirstName, strLastName, strAddress, strEmail, strMobileNumber, strLandlineNumber, strPassword, strPasswordConfirmation;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tet_firstname = findViewById(R.id.tet_firstname);
        tet_lastname = findViewById(R.id.tet_lastname);
        tet_address = findViewById(R.id.tet_address);
        tet_email = findViewById(R.id.tet_email);
        tet_mobileNumber = findViewById(R.id.tet_mobileNumber);
        tet_landlineNumber = findViewById(R.id.tet_landlineNumber);
        tet_password = findViewById(R.id.tet_password);
        tet_passwordConfirmation = findViewById(R.id.tet_passwordConfirmation);


        til_firstname = findViewById(R.id.til_firstname);
        til_lastname = findViewById(R.id.til_lastname);
        til_address = findViewById(R.id.til_address);
        til_email = findViewById(R.id.til_email);
        til_mobileNumber = findViewById(R.id.til_mobileNumber);
        til_landlineNumber = findViewById(R.id.til_landlineNumber);
        til_password = findViewById(R.id.til_password);
        til_passwordConfirmation = findViewById(R.id.til_passwordConfirmation);

        btn_signup = findViewById(R.id.btn_signup);
        btn_haveAnAccount = findViewById(R.id.btn_haveAnAccount);


        onClickListeners();


    }

    private void onClickListeners() {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strFirstName = tet_firstname.getText().toString();
                strLastName = tet_lastname.getText().toString();
                strAddress = tet_address.getText().toString();
                strEmail = tet_email.getText().toString();
                strMobileNumber = tet_mobileNumber.getText().toString();
                strLandlineNumber = tet_landlineNumber.getText().toString();
                strPassword = tet_password.getText().toString();
                strPasswordConfirmation = tet_passwordConfirmation.getText().toString();


                if(!validateField()) {

                }else {
                    networkSignup(strFirstName, strLastName, strAddress, strEmail, strPassword, strPasswordConfirmation, strMobileNumber, strLandlineNumber);
                }


            }
        });

        tet_firstname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_firstname.isErrorEnabled()){
                    til_firstname.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_lastname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_lastname.isErrorEnabled()){
                    til_lastname.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_address.isErrorEnabled()){
                    til_address.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_email.isErrorEnabled()){
                    til_email.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_password.isErrorEnabled()){
                    til_password.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_passwordConfirmation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_passwordConfirmation.isErrorEnabled()){
                    til_passwordConfirmation.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_mobileNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_mobileNumber.isErrorEnabled()){
                    til_mobileNumber.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_landlineNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(til_landlineNumber.isErrorEnabled()){
                    til_landlineNumber.setErrorEnabled(false);
                }

                return false;
            }
        });
    }

    private boolean validateField() {
        String strFirstName = tet_firstname.getText().toString();
        String strLastName = tet_lastname.getText().toString();
        String strAddress = tet_address.getText().toString();
        String strEmail = tet_email.getText().toString();
        String strMobileNumber = tet_mobileNumber.getText().toString();
        String strLandlineNumber = tet_landlineNumber.getText().toString();

        boolean valid = true;

        if(strFirstName.isEmpty()){
            til_firstname.setError("This field is empty");
            valid = false;
        }

        if(strLastName.isEmpty()){
            til_lastname.setError("This field is empty");
            valid = false;
        }

        if(strAddress.isEmpty()){
            til_address.setError("This field is empty");
            valid = false;
        }

        if(strEmail.isEmpty()){
            til_email.setError("This field is empty");
            valid = false;
        }

        if(strMobileNumber.isEmpty()){
            til_mobileNumber.setError("This field is empty");
            valid = false;
        }

        if(strLandlineNumber.isEmpty()){
            til_landlineNumber.setError("This field is empty");
            valid = false;
        }

        return valid;


    }


    private void networkSignup(String strFirstName, String strLastName, String strAddress, String strEmail, String strPassword, String strPasswordConfirmation, String strMobileNumber, String strLandlineNumber) {

        requestQueue = Volley.newRequestQueue(this);

        Map<String, String> mapSignup = new HashMap<String, String>();
        mapSignup.put("email", strEmail);
        mapSignup.put("first_name", strFirstName);
        mapSignup.put("last_name", strLastName);
        mapSignup.put("address", strAddress);
        mapSignup.put("mobile_number", strMobileNumber);
        mapSignup.put("landline_number", strLandlineNumber);
        mapSignup.put("password", strPassword);
        mapSignup.put("password_confirmation", strPasswordConfirmation);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_SIGNUP, new JSONObject(mapSignup), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Log.d(StringConfig.LOG_TAG, response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}
