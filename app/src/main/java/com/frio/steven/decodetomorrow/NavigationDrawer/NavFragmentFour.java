package com.frio.steven.decodetomorrow.NavigationDrawer;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragmentFour extends Fragment {

    private View view1;
    private View home;
    private TextView textView;

    SwipeRefreshLayout srl_constructionUpdate;
    public NavFragmentFour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nav_fragment_four, container, false);

        //srl_constructionUpdate = view.findViewById(R.id.srl_constructionUpdate);
        view1 = view.findViewById(R.id.background);
        home = view.findViewById(R.id.home);
        textView = view.findViewById(R.id.percent);

                volleyUpdateConstruction();

                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animate();
                    }
                });

        /*srl_constructionUpdate.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                volleyUpdateConstruction();
            }
        });*/
        return view;
    }

    private void animate(){

        view1.animate().translationY(-view1.getHeight()).setDuration(1000);

    }

    private void volleyUpdateConstruction() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());


        SharedPreferences sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        String userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");

        StringRequest stringRequest = new StringRequest(StringConfig.BASE_URL + StringConfig.URL_CONSTRUCTION_UPDATE + userId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    textView.setText(status.toString() + " %");

                    Log.d(StringConfig.LOG_TAG, "construction status : " + status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);

    }


}
