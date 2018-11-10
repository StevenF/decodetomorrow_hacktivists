package com.frio.steven.decodetomorrow.VolleyRequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John Steven Frio on 11/10/2018.
 */

public class VolleyRequest {

    Context context;

    public VolleyRequest(Context context){
        this.context = context;
    }

    /*public void loginUser(String loginEmail, String loginPassword){

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Map<String, String> mapLogin = new HashMap<String, String>();
        mapLogin.put("email", loginEmail);
        mapLogin.put("password", loginPassword);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_LOGIN, new JSONObject(mapLogin), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(StringConfig.LOG_TAG, "loginResponse : " + response);

                try {
                    String jsonObjectLoginResult = response.getString("result");

                    if(jsonObjectLoginResult.equals("success")){
                        String jsonResponseMessage = response.getString("message");
                        String jsonResponseId = String.valueOf(response.getInt("id"));

                        Log.d(StringConfig.LOG_TAG, "loginResponseMessage : " + jsonResponseMessage);
                        Log.d(StringConfig.LOG_TAG, "loginResponseId : " + jsonResponseId);

                        Toast.makeText(context, "Welcome.", Toast.LENGTH_SHORT);

                    }else{
                        Toast.makeText(context, "Login failed.", Toast.LENGTH_SHORT);
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


    }*/

}
