package com.frio.steven.decodetomorrow;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frio.steven.decodetomorrow.Util.StringConfig;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentScheduleTrip extends DialogFragment {

    EditText et_datePicker, et_timePicker;
    String tripId;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Button btn_scheduleTrip;
    SharedPreferences sharedPreferences;

    public DialogFragmentScheduleTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_fragment_schedule_trip, container, false);

        et_datePicker = view.findViewById(R.id.et_datePicker);
        et_timePicker = view.findViewById(R.id.et_timePicker);
        btn_scheduleTrip = view.findViewById(R.id.btn_scheduleTrip);

        Bundle bundle = getArguments();

        if (bundle != null) {
            tripId = bundle.getString("tripId");
            Log.d(StringConfig.LOG_TAG, "dialogFragmentScheduleTripID : " + tripId);
        }

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();


            }

        };

        et_datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        et_timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_timePicker.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

            }
        });

        btn_scheduleTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyUpdateTripSchedule();
            }
        });

        return view;

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        et_datePicker.setText(sdf.format(myCalendar.getTime()));
    }

    private void volleyUpdateTripSchedule(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        sharedPreferences = getContext().getSharedPreferences(StringConfig.SHAREDPREF_NAME, 0);
        String userID = sharedPreferences.getString(StringConfig.SHAREDPREF_USERKEY, "");

        Map<String, String> mapScheduleTrip = new HashMap<String, String>();
        mapScheduleTrip.put("userID", userID);
        mapScheduleTrip.put("inquiryID", tripId);
        mapScheduleTrip.put("date", et_datePicker.getText().toString());
        mapScheduleTrip.put("time", et_timePicker.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(StringConfig.BASE_URL + StringConfig.URL_SCHEDULE_TRIP, new JSONObject(mapScheduleTrip), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(StringConfig.LOG_TAG, "tripResponse : " + response.toString());
                Toast.makeText(getContext(), "Schedule updated.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

}
