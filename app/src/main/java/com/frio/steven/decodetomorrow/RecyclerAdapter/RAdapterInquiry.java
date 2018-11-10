package com.frio.steven.decodetomorrow.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserInquiry;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by John Steven Frio on 11/10/2018.
 */

public class RAdapterInquiry extends RecyclerView.Adapter<RAdapterInquiry.ViewHolder>{


    Context context;
    List<RParserInquiry> rParserInquiryList;
    RAdapterInquiryOnItemClick rAdapterInquiryOnItemClick;

    public RAdapterInquiry(Context context, List<RParserInquiry> rParserInquiryList, RAdapterInquiryOnItemClick rAdapterInquiryOnItemClick){
        this.context = context;
        this.rParserInquiryList = rParserInquiryList;
        this.rAdapterInquiryOnItemClick = rAdapterInquiryOnItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardrow_holder_inquiry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RParserInquiry rParserInquiry = rParserInquiryList.get(position);
        final String id = String.valueOf(rParserInquiry.getId());
        final String name = rParserInquiry.getName();
        final String bedroomCount = rParserInquiry.getBedroomCount();
        final String floorArea = rParserInquiry.getFloorArea();
        final String lotArea = rParserInquiry.getLotArea();
        String imageUrl = rParserInquiry.getImageUrl();

        Picasso.get().load(imageUrl).resize(1080,600).centerCrop().into(holder.iv_cardrow_inquiry);

        holder.btn_cardrow_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rAdapterInquiryOnItemClick.onItemClick(holder, position, id, name, bedroomCount, floorArea, lotArea);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rParserInquiryList.size();
    }

    public interface RAdapterInquiryOnItemClick{
        void onItemClick(RecyclerView.ViewHolder holder, int position, String id, String name, String bedroomCount, String floorArea, String lotArea);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_cardrow_inquiry;
        public Button btn_cardrow_details;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_cardrow_inquiry = itemView.findViewById(R.id.iv_cardrow_inquiry);
            btn_cardrow_details = itemView.findViewById(R.id.btn_cardrow_details);

        }
    }
}
