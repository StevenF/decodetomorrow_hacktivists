package com.frio.steven.decodetomorrow.NavigationDrawer;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.DownloadManager;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.DialogFragmentInquiryDetails;
import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.RecyclerAdapter.RAdapterInquiry;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserInquiry;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragmentOne extends Fragment {

    SwipeRefreshLayout srl_inquiry;
    RecyclerView rv_inquiry;

    RAdapterInquiry rAdapterInquiry;
    List<RParserInquiry> rParserInquiryList;
    RParserInquiry rParserInquiry;

    LinearLayoutManager linearLayoutManager;

    public NavFragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nav_fragment_one, container, false);

        srl_inquiry = view.findViewById(R.id.srl_inquiry);
        rv_inquiry = view.findViewById(R.id.rv_inquiry);
        rParserInquiryList = new ArrayList<>();

        loadJSONInquiry();

        srl_inquiry.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_inquiry.setRefreshing(true);
                loadJSONInquiry();
                srl_inquiry.setRefreshing(false);

            }
        });

        return view;

    }

    private void loadJSONInquiry(){
        rParserInquiryList.clear();

        volleyRequestInquiry();
        initRecyclerView();
        fillRecyclerView();

    }

    private void volleyRequestInquiry(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        srl_inquiry.setRefreshing(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(StringConfig.BASE_URL + StringConfig.URL_SHOW_HOUSES, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        Log.d(StringConfig.LOG_TAG, "inquiryId : " + id);

                        String name = jsonObject.getString("name");
                        Log.d(StringConfig.LOG_TAG, "inquiryName : " + name);

                        String bedroomCount = jsonObject.getString("bedroomCount");
                        Log.d(StringConfig.LOG_TAG, "inquiryBedroomCount : " + bedroomCount);

                        String floorArea = jsonObject.getString("floorArea");
                        Log.d(StringConfig.LOG_TAG, "inquiryFloorArea : " + floorArea);

                        String lotArea = jsonObject.getString("lotArea");
                        Log.d(StringConfig.LOG_TAG, "inquiryLotArea : " + lotArea);

                        JSONObject jsonObjectImageUrl = jsonObject.getJSONObject("image");
                        String imageUrl = StringConfig.BASE_URL + jsonObjectImageUrl.getString("url");
                        Log.d(StringConfig.LOG_TAG, "inquiryImageUrl: " + imageUrl);

                        RParserInquiry rParserInquiry = new RParserInquiry(id, name, bedroomCount, floorArea, lotArea, imageUrl);
                        rParserInquiryList.add(rParserInquiry);
                        rAdapterInquiry.notifyDataSetChanged();

                        srl_inquiry.setRefreshing(false);

                    }
                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();

                srl_inquiry.setRefreshing(false);

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void initRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_inquiry.setLayoutManager(linearLayoutManager);
    }

    private void fillRecyclerView(){
        rAdapterInquiry = new RAdapterInquiry(getContext(), rParserInquiryList, new RAdapterInquiry.RAdapterInquiryOnItemClick() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position, String id, String name, String bedroomCount, String floorArea, String lotArea) {

                Log.d(StringConfig.LOG_TAG, "detailedId : " + id);
                Log.d(StringConfig.LOG_TAG, "detailedName : " + name);
                Log.d(StringConfig.LOG_TAG, "detailedBedroomCount : " + bedroomCount);
                Log.d(StringConfig.LOG_TAG, "detailedFloorArea : " + floorArea);
                Log.d(StringConfig.LOG_TAG, "detailedLotArea : " + lotArea);

                DialogFragmentInquiryDetails dialogFragmentInquiryDetails = new DialogFragmentInquiryDetails();
                FragmentManager fragmentManager = getFragmentManager();

                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("name", name);
                bundle.putString("bedroomCount", bedroomCount);
                bundle.putString("floorArea", floorArea);
                bundle.putString("lotArea", lotArea);

                dialogFragmentInquiryDetails.setArguments(bundle);

                dialogFragmentInquiryDetails.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragmentInquiryDetails.show(fragmentManager, "DialogFragmentInquiryDetails");


            }
        });

/*
        rv_inquiry.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
*/
        rv_inquiry.setAdapter(rAdapterInquiry);
    }

}
