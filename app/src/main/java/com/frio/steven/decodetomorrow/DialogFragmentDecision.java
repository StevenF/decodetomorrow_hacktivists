package com.frio.steven.decodetomorrow;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentDecision extends DialogFragment {

    Spinner spinner_decision;
    TextInputEditText tet_decisionMessage;
    Button btn_decisionSubmit;

    public DialogFragmentDecision() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_fragment_decision, container, false);

        spinner_decision = view.findViewById(R.id.spinner_decision);
        btn_decisionSubmit = view.findViewById(R.id.btn_decisionSubmit);
        tet_decisionMessage = view.findViewById(R.id.tet_decisionMessage);


        btn_decisionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = spinner_decision.getSelectedItem().toString();
                String message = tet_decisionMessage.getText().toString();

                volleySubmitDecision(status, message);
            }
        });

        initSpinner();

        return view;
    }

    private void volleySubmitDecision(String status, String message) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Log.d(StringConfig.LOG_TAG, "decisionMessage : " + message);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        String userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");
        String inquiryId = sharedPreferences.getString(StringConfig.SHAREDPREF_INQUIRY_ID, "");

        Map<String, String> mapDecision = new HashMap<String, String>();
        mapDecision.put("inquiryID", inquiryId);
        mapDecision.put("status", status);
        mapDecision.put("message", message);
        mapDecision.put("userID", userId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_SUBMIT_DECISION, new JSONObject(mapDecision), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String created_at = response.getString("created_at");
                    if(!created_at.isEmpty()){
                        Toast.makeText(getContext(), "Success.", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    } else{
                        Toast.makeText(getContext(), "Failed.", Toast.LENGTH_SHORT).show();
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

    private void initSpinner() {

        List<String> spinnerContent = new ArrayList<>();
        spinnerContent.add("Undecided");
        spinnerContent.add("Interested");
        spinnerContent.add("Not Interested");
        spinnerContent.add("Decided to buy");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerContent);
        spinner_decision.setAdapter(dataAdapter);
    }





}
