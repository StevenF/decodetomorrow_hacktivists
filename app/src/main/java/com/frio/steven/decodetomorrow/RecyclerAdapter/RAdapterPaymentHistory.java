package com.frio.steven.decodetomorrow.RecyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frio.steven.decodetomorrow.R;
import com.frio.steven.decodetomorrow.RecyclerParser.RParserPaymentHistory;

import java.util.List;

/**
 * Created by JMM on 11/11/2018.
 */

public class RAdapterPaymentHistory extends RecyclerView.Adapter<RAdapterPaymentHistory.ViewHolder> {

    Context context;
    List<RParserPaymentHistory> rParserPaymentHistoryList;

    public RAdapterPaymentHistory(Context context, List<RParserPaymentHistory> rParserPaymentHistoryList) {
        this.context = context;
        this.rParserPaymentHistoryList = rParserPaymentHistoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardrow_holder_payment_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RParserPaymentHistory rParserPaymentHistory = rParserPaymentHistoryList.get(position);
        String amount = rParserPaymentHistory.getAmount();
        String date = rParserPaymentHistory.getDate();
        String time = rParserPaymentHistory.getTime();
        String penalty = rParserPaymentHistory.getPenalty();

        holder.tet_paymentAmount.setText("₱ "+amount);
        holder.tet_paymentDate.setText(date);
        holder.tet_paymentTime.setText(time);
        holder.tet_paymentPenalty.setText(penalty);

    }

    @Override
    public int getItemCount() {
        return rParserPaymentHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tet_paymentAmount, tet_paymentDate, tet_paymentTime, tet_paymentPenalty;

        public ViewHolder(View itemView) {
            super(itemView);

            tet_paymentAmount = itemView.findViewById(R.id.tet_paymentAmount);
            tet_paymentDate = itemView.findViewById(R.id.tet_paymentDate);
            tet_paymentTime = itemView.findViewById(R.id.tet_paymentTime);
            tet_paymentPenalty = itemView.findViewById(R.id.tet_paymentPenalty);

        }
    }
}
