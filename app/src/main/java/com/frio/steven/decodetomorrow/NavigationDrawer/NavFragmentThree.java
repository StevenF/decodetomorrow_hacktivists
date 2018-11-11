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
import android.widget.TextView;
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
import com.frio.steven.decodetomorrow.RecyclerAdapter.RAdapterPaymentHistory;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserInquiry;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserPaymentHistory;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragmentThree extends Fragment {

    TextView tv_paymentDeadline;
    SwipeRefreshLayout srl_paymentHistory;
    RecyclerView rv_paymentHistory;
    List<RParserPaymentHistory> rParserPaymentHistoryList;
    LinearLayoutManager linearLayoutManager;
    RAdapterPaymentHistory rAdapterPaymentHistory;

    public NavFragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nav_fragment_three, container, false);

        tv_paymentDeadline = view.findViewById(R.id.tv_paymentDeadline);
        srl_paymentHistory = view.findViewById(R.id.srl_paymentHistory);
        rv_paymentHistory = view.findViewById(R.id.rv_paymentHistory);
        rParserPaymentHistoryList = new ArrayList<>();

        loadJSONPayment();

        srl_paymentHistory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_paymentHistory.setRefreshing(true);
                loadJSONPayment();
                srl_paymentHistory.setRefreshing(false);

            }
        });

        return view;
    }

    private void loadJSONPayment() {
        rParserPaymentHistoryList.clear();

        volleyRequestInquiry();
        initRecyclerView();
        fillRecyclerView();

    }


    private void volleyRequestInquiry(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        srl_paymentHistory.setRefreshing(true);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        String userId = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(StringConfig.BASE_URL + StringConfig.URL_PAYMENT_HISTORY + userId, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String amount = jsonObject.getString("amount");
                        String date = jsonObject.getString("date");
                        String time = jsonObject.getString("time");
                        String penalty = jsonObject.getString("penalty");

                        RParserPaymentHistory rParserPaymentHistory =  new RParserPaymentHistory(amount, date, time, penalty);

                        rParserPaymentHistoryList.add(rParserPaymentHistory);
                        rAdapterPaymentHistory.notifyDataSetChanged();

                        srl_paymentHistory.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);

    }

    private void initRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_paymentHistory.setLayoutManager(linearLayoutManager);
    }

    private void fillRecyclerView(){
        rAdapterPaymentHistory = new RAdapterPaymentHistory(getContext(), rParserPaymentHistoryList);
        rv_paymentHistory.setAdapter(rAdapterPaymentHistory);
    }

}
