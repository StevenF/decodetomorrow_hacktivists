package com.frio.steven.decodetomorrow.NavigationDrawer;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.R;
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
public class NavFragmentFive extends Fragment {

    Spinner spinner_feedbackType;
    TextInputEditText tet_feedbackMessage;
    Button btn_feedbackSubmit;

    public NavFragmentFive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nav_fragment_five, container, false);

        spinner_feedbackType = view.findViewById(R.id.spinner_feedbackType);
        tet_feedbackMessage = view.findViewById(R.id.tet_feedbackMessage);
        btn_feedbackSubmit = view.findViewById(R.id.btn_feedbackSubmit);

        initSpinner();

        btn_feedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feedbackType = spinner_feedbackType.getSelectedItem().toString();
                String feedbackMessage = tet_feedbackMessage.getText().toString();

                SharedPreferences sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
                String userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");

                volleySubmitFeedback(userId, feedbackType, feedbackMessage);
            }
        });



        return view;
    }

    private void volleySubmitFeedback(String userId, String feedbackType, String feedbackMessage) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Map<String, String> mapFeedback = new HashMap<String, String>();
        mapFeedback.put("userID", userId);
        mapFeedback.put("feedbackType", feedbackType);
        mapFeedback.put("message", feedbackMessage);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_CREATE_FEEDBACKS, new JSONObject(mapFeedback), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String created_at = response.getString("created_at");
                    if(!created_at.isEmpty()){
                        Toast.makeText(getContext(), "Success !", Toast.LENGTH_SHORT).show();
                        tet_feedbackMessage.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void initSpinner() {
        List<String> spinnerContent = new ArrayList<>();
        spinnerContent.add("Complaint");
        spinnerContent.add("Request");
        spinnerContent.add("Repair/Renovation");
        spinnerContent.add("Comments and Suggestion");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerContent);
        spinner_feedbackType.setAdapter(dataAdapter);
    }


}
