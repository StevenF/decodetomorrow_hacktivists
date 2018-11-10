package com.frio.steven.decodetomorrow.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserShowInquiries;

import java.util.List;

/**
 * Created by JMM on 11/10/2018.
 */

public class RAdapterShowInquiries extends RecyclerView.Adapter<RAdapterShowInquiries.ViewHolder> {

    Context context;
    List<RParserShowInquiries> rParserShowInquiriesList;
    RAdapterShowInquiriesOnClickListener rAdapterShowInquiriesOnClickListener;


    public RAdapterShowInquiries(Context context, List<RParserShowInquiries> rParserShowInquiriesList, RAdapterShowInquiriesOnClickListener rAdapterShowInquiriesOnClickListener){
        this.context = context;
        this.rParserShowInquiriesList = rParserShowInquiriesList;
        this.rAdapterShowInquiriesOnClickListener = rAdapterShowInquiriesOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardrow_holder_inquiry_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RParserShowInquiries rParserShowInquiries = rParserShowInquiriesList.get(position);
        final String id = rParserShowInquiries.getId();
        String userId = rParserShowInquiries.getUserID();
        String name = rParserShowInquiries.getName();
        String bedroomCount = rParserShowInquiries.getBedroomCount();
        String floorArea = rParserShowInquiries.getFloorArea();
        String lotArea = rParserShowInquiries.getLotArea();
        String status = rParserShowInquiries.getStatus();


        holder.tv_cardrow_inquiry_name.setText(name);
        holder.tv_cardrow_inquiry_bedroomCount.setText(bedroomCount);
        holder.tv_cardrow_inquiry_floorArea.setText(floorArea);
        holder.tv_cardrow_inquiry_lotArea.setText(lotArea);
        holder.tv_cardrow_inquiry_status.setText(status);

        if (status.equals("Trip")){
            holder.tv_cardrow_setTripSched.setVisibility(View.VISIBLE);
        }

        holder.tv_cardrow_setTripSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rAdapterShowInquiriesOnClickListener.OnItemClick(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rParserShowInquiriesList.size();
    }

    public interface RAdapterShowInquiriesOnClickListener{
        void OnItemClick(String id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cardrow_inquiry_name, tv_cardrow_inquiry_bedroomCount, tv_cardrow_inquiry_floorArea, tv_cardrow_inquiry_lotArea, tv_cardrow_inquiry_status, tv_cardrow_setTripSched;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_cardrow_inquiry_name = itemView.findViewById(R.id.tv_cardrow_inquiry_name);
            tv_cardrow_inquiry_bedroomCount = itemView.findViewById(R.id.tv_cardrow_inquiry_bedroomCount);
            tv_cardrow_inquiry_floorArea = itemView.findViewById(R.id.tv_cardrow_inquiry_floorArea);
            tv_cardrow_inquiry_lotArea = itemView.findViewById(R.id.tv_cardrow_inquiry_lotArea);
            tv_cardrow_inquiry_status = itemView.findViewById(R.id.tv_cardrow_inquiry_status);
            tv_cardrow_setTripSched = itemView.findViewById(R.id.tv_cardrow_inquiry_setTripSched);

        }
    }
}
