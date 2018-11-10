package com.frio.steven.decodetomorrow;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragmentInquiryDetails extends DialogFragment {

    String strId, strName, strBedroomCount, strFloorArea, strLotArea;
    TextView tv_inquiryName, tv_inquiryBedrooomCount, tv_inquiryFloorArea, tv_inquiryLotArea;
    Button btn_dialogFragmentInquire;

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

        btn_dialogFragmentInquire = view.findViewById(R.id.btn_dialogFragmentInquire);



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
        tv_inquiryBedrooomCount.setText(strBedroomCount);
        tv_inquiryFloorArea.setText(strFloorArea);
        tv_inquiryLotArea.setText(strLotArea);

        btn_dialogFragmentInquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
