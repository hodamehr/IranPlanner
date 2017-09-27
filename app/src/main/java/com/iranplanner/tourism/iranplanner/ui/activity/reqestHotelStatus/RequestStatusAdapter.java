package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;

import java.util.ArrayList;
import java.util.List;

import entity.ResultReqCount;

/**
 * Created by h.vahidimehr on 29/08/2017.
 */

public class RequestStatusAdapter extends RecyclerView.Adapter<RequestStatusAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ResultReqCount> resultReqCountList;

    public RequestStatusAdapter(Context context, List<ResultReqCount> resultReqCountList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.resultReqCountList = resultReqCountList;
        this.resultReqCountList.remove(0);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.request_status_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String current = resultReqCountList.get(position).getReservationReqStatus().getStatusTitle();
//        resultReqCountList.get(position).getReservationReqStatus().getStatusId()dgf
        holder.setData(current, position,resultReqCountList.get(position).getReservationReqStatus().getStatusCount());
    }

    @Override
    public int getItemCount() {
        return resultReqCountList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button btnPayment, btnView;
        private TextView tvTitle, tvCount;
        private View itemView ;

        public Holder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            btnPayment = (Button) itemView.findViewById(R.id.requestStatusRowPaymentBtn);
            tvTitle = (TextView) itemView.findViewById(R.id.requestStatusRowTitleTv);
            tvCount = (TextView) itemView.findViewById(R.id.requestStatusRowCountTv);
            btnPayment.setVisibility(View.GONE);
            btnPayment.setOnClickListener(this);
//            btnView.setOnClickListener(this);
        }

        public void setData(String current, int position, String count) {
            if (position == 2)
                btnPayment.setVisibility(View.VISIBLE);
            else btnPayment.setVisibility(View.GONE);
            tvTitle.setText(current);
            tvCount.setText(count);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.requestStatusRowPaymentBtn:
                    Toast.makeText(context, "Pay for SomeThing", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
