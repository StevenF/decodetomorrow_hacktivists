package com.frio.steven.decodetomorrow;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentInquiryDetails extends DialogFragment {

    String strId, strName, strBedroomCount, strFloorArea, strLotArea, userId;
    TextView tv_inquiryName, tv_inquiryBedrooomCount, tv_inquiryFloorArea, tv_inquiryLotArea;
    Button btn_dialogFragmentInquire;
    SharedPreferences sharedPreferences;

    public DialogFragmentInquiryDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_fragment_inquiry_details, container, false);

        tv_inquiryName = view.findViewById(R.id.tv_inquiryName);
        tv_inquiryBedrooomCount = view.findViewById(R.id.tv_inquiryBedroomCount);
        tv_inquiryFloorArea = view.findViewById(R.id.tv_inquiryFloorArea);
        tv_inquiryLotArea = view.findViewById(R.id.tv_inquiryLotArea);

        getDialog().setTitle("Details");

        btn_dialogFragmentInquire = view.findViewById(R.id.btn_dialogFragmentInquire);


        sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");


        Log.d(StringConfig.LOG_TAG, "sharedPrefValue : " + userId);



        Bundle bundle = this.getArguments();
        if(bundle != null){
            strId = bundle.getString("id");
            strName = bundle.getString("name");
            strBedroomCount = bundle.getString("bedroomCount");
            strFloorArea = bundle.getString("floorArea");
            strLotArea = bundle.getString("lotArea");

        }

        getDialog().setTitle(strName);

        tv_inquiryName.setText(strName);
        tv_inquiryBedrooomCount.setText("Number of Rooms: " + strBedroomCount);
        tv_inquiryFloorArea.setText("Floor Area: "+strFloorArea);
        tv_inquiryLotArea.setText("Lot Area: "+ strLotArea);

        btn_dialogFragmentInquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyCreateInquiry(strId, strName, strBedroomCount, strFloorArea, strLotArea);
            }
        });

        return view;
    }

    private void volleyCreateInquiry(String strId, String strName, String strBedroomCount, String strFloorArea, String strLotArea){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Map<String, String> mapCreateInquiry = new HashMap<String, String>();
        mapCreateInquiry.put("userID", userId);
        mapCreateInquiry.put("name", strName);
        mapCreateInquiry.put("bedroomCount", strBedroomCount);
        mapCreateInquiry.put("floorArea", strFloorArea);
        mapCreateInquiry.put("lotArea", strLotArea);
        mapCreateInquiry.put("status", "waiting for response");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, StringConfig.BASE_URL + StringConfig.URL_CREATE_INQUIRY, new JSONObject(mapCreateInquiry), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(StringConfig.LOG_TAG, "inquiryResponse : " + response.toString());
                getDialog().dismiss();
                Toast.makeText(getContext(),"Inquiry Sent. Just wait for someone to contact you.",Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

}
