package com.frio.steven.decodetomorrow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.Util.StringConfig;
import com.frio.steven.decodetomorrow.Util.Validators;
import com.frio.steven.decodetomorrow.VolleyRequest.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout til_loginEmail, til_loginPassword;
    TextInputEditText tet_loginEmail, tet_loginPassword;
    Button btn_login,  btn_signMeUp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Validators validators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        til_loginEmail = findViewById(R.id.til_loginEmail);
        til_loginPassword = findViewById(R.id.til_loginPassword);

        tet_loginEmail = findViewById(R.id.tet_loginEmail);
        tet_loginPassword = findViewById(R.id.tet_loginPassword);

        btn_login = findViewById(R.id.btn_login);
        btn_signMeUp = findViewById(R.id.btn_signMeUp);

        sharedPreferences = getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        editor = sharedPreferences.edit();


        validators = new Validators();

        onClickListeners();

    }

    private void onClickListeners() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validators.isValidLogin(til_loginEmail, tet_loginEmail, til_loginPassword, tet_loginPassword)){

                }else{
                    //insert network request
                    //new VolleyRequest(LoginActivity.this).loginUser(tet_loginEmail.getText().toString(), tet_loginPassword.getText().toString());
                    loginUser(tet_loginEmail.getText().toString(), tet_loginPassword.getText().toString());
                }

            }
        });

        tet_loginEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(til_loginEmail.isErrorEnabled()){
                    til_loginEmail.setErrorEnabled(false);
                }

                return false;
            }
        });

        tet_loginPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(til_loginPassword.isErrorEnabled()){
                    til_loginPassword.setErrorEnabled(false);
                }

                return false;
            }
        });

    }


    public void loginUser(String loginEmail, String loginPassword){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Map<String, String> mapLogin = new HashMap<String, String>();
        mapLogin.put("email", loginEmail);
        mapLogin.put("password", loginPassword);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_LOGIN, new JSONObject(mapLogin), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(StringConfig.LOG_TAG, "loginResponse : " + response);

                try {
                    String jsonObjectLoginResult = response.getString("result");

                    Log.d(StringConfig.LOG_TAG, "jsonObjectLoginResult : " + jsonObjectLoginResult);

                    if(jsonObjectLoginResult.equals("success")){
                        String jsonResponseMessage = response.getString("message");
                        String jsonResponseId = String.valueOf(response.getInt("user_id"));

                        Log.d(StringConfig.LOG_TAG, "loginResponseMessage : " + jsonResponseMessage);
                        Log.d(StringConfig.LOG_TAG, "loginResponseId : " + jsonResponseId);

                        editor.putString(StringConfig.SHAREDPREF_USERKEY, jsonResponseId);
                        editor.commit();
                        //sharedPreferences.edit().putString(StringConfig.SHAREDPREF_USERKEY, jsonResponseId).apply();

                        Toast.makeText(LoginActivity.this, "Welcome.", Toast.LENGTH_SHORT);

                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonObjectRequest);


    }
}
