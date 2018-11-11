package com.frio.steven.decodetomorrow;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    Button btn_notifOkay, btn_notifResched;
    String id;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        btn_notifOkay = view.findViewById(R.id.btn_notifOkay);
        btn_notifResched = view.findViewById(R.id.btn_notifResched);

        final Bundle bundle = getArguments();
        if(bundle!=null){
            id = bundle.getString("userId");
        }

        btn_notifOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              DialogFragmentDecision dialogFragmentDecision = new DialogFragmentDecision();
              FragmentManager fragmentManager = getFragmentManager();

              Bundle bundle1 = new Bundle();
              bundle1.putString("inquiryId", id);
              dialogFragmentDecision.setArguments(bundle);

                dialogFragmentDecision.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
                dialogFragmentDecision.show(fragmentManager, "DialogFragmentDecision");
            }
        });

        btn_notifResched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
