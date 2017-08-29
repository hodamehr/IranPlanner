package com.iranplanner.tourism.iranplanner.ui.fragment.myaccount;

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

/**
 * Created by h.vahidimehr on 29/08/2017.
 */

public class RequestStatusAdapter extends RecyclerView.Adapter<RequestStatusAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> statuses;

    public RequestStatusAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        statuses = getStatuses();
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
        String current = statuses.get(position);
        holder.setData(current, position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button btnPayment, btnView;
        private TextView tvTitle, tvCount;

        public Holder(View itemView) {
            super(itemView);
            btnPayment = (Button) itemView.findViewById(R.id.requestStatusRowPaymentBtn);
            btnView = (Button) itemView.findViewById(R.id.requestStatusRowViewBtn);
            tvTitle = (TextView) itemView.findViewById(R.id.requestStatusRowTitleTv);
            tvCount = (TextView) itemView.findViewById(R.id.requestStatusRowCountTv);

            btnPayment.setVisibility(View.GONE);

            btnPayment.setOnClickListener(this);
            btnView.setOnClickListener(this);
        }

        public void setData(String current, int position) {
            if (position == 1)
                btnPayment.setVisibility(View.VISIBLE);
            else btnPayment.setVisibility(View.GONE);

            tvTitle.setText(current);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.requestStatusRowPaymentBtn:
                    Toast.makeText(context, "Pay for SomeThing", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.requestStatusRowViewBtn:
                    Toast.makeText(context, "View SomeThing", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private ArrayList<String> getStatuses() {
        ArrayList<String> strings = new ArrayList<>();

        strings.add("درحال بررسی");
        strings.add("در انتظار پرداخت");
        strings.add("پرداهت شده");
        strings.add("لغو شده");

        return strings;
    }
}
