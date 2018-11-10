package com.frio.steven.decodetomorrow.NavigationDrawer;


import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.DialogFragmentScheduleTrip;
import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.RecyclerAdapter.RAdapterShowInquiries;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserShowInquiries;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragmentTwo extends Fragment {

    RecyclerView rv_inquiryShow;
    SwipeRefreshLayout srl_inquiryShow;
    List<RParserShowInquiries> rParserShowInquiriesList;
    LinearLayoutManager linearLayoutManager;
    RAdapterShowInquiries rAdapterShowInquiries;
    SharedPreferences sharedPreferences;
    String userId;

    public NavFragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nav_fragment_two, container, false);

        rParserShowInquiriesList = new ArrayList<>();


        sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");

        srl_inquiryShow = view.findViewById(R.id.srl_inquiryShow);
        rv_inquiryShow = view.findViewById(R.id.rv_inquiryShow);
        loadMyInquiries();

        srl_inquiryShow.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_inquiryShow.setRefreshing(true);
                loadMyInquiries();
                srl_inquiryShow.setRefreshing(false);
            }
        });

        return view;
    }


    private void loadMyInquiries(){
        rParserShowInquiriesList.clear();

        volleyShowInquiries();
        initRecyclerView();
        fillRecyclerView();
    }

    private void volleyShowInquiries() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());



        srl_inquiryShow.setRefreshing(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(StringConfig.BASE_URL + StringConfig.URL_SHOW_INQUIRY+ "/" + userId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(StringConfig.LOG_TAG, "showInquiries : " + response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = String.valueOf(jsonObject.getInt("id"));
                        Log.d(StringConfig.LOG_TAG, "showInquiriesId : " + id);

                        String userId = jsonObject.getString("userID");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesUserId : " + userId);

                        String name = jsonObject.getString("name");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesName : " + name);

                        String bedroomCount = jsonObject.getString("bedroomCount");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesBedroomCount : " + bedroomCount);

                        String floorArea = jsonObject.getString("floorArea");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesFloorArea : " + floorArea);

                        String lotArea = jsonObject.getString("lotArea");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesLotArea : " + lotArea);

                        String status = jsonObject.getString("status");
                        Log.d(StringConfig.LOG_TAG, "showInquiriesStatus: " + status);



                        RParserShowInquiries rParserShowInquiries = new RParserShowInquiries(id, userId, name, bedroomCount, floorArea, lotArea, status);
                        rParserShowInquiriesList.add(rParserShowInquiries);
                        rAdapterShowInquiries.notifyDataSetChanged();

                    }
                }catch (JSONException e){

                }


                srl_inquiryShow.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                srl_inquiryShow.setRefreshing(false);
            }
        });

        requestQueue.add(jsonArrayRequest);

    }


    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_inquiryShow.setLayoutManager(linearLayoutManager);
    }

    private void fillRecyclerView() {
        rAdapterShowInquiries = new RAdapterShowInquiries(getContext(), rParserShowInquiriesList, new RAdapterShowInquiries.RAdapterShowInquiriesOnClickListener() {
            @Override
            public void OnItemClick(String id) {
                Log.d(StringConfig.LOG_TAG, "tripId : " + id);

                DialogFragmentScheduleTrip dialogFragmentScheduleTrip = new DialogFragmentScheduleTrip();
                FragmentManager fragmentManager = getFragmentManager();

                Bundle bundle = new Bundle();
                bundle.putString("tripId", id);

                dialogFragmentScheduleTrip.setArguments(bundle);

                dialogFragmentScheduleTrip.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragmentScheduleTrip.show(fragmentManager, "DialogFragmentScheduleTrip");

            }
        });
        rv_inquiryShow.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv_inquiryShow.setAdapter(rAdapterShowInquiries);
    }

}
