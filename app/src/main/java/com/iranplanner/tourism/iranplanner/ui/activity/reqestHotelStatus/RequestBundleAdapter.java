package com.iranplanner.tourism.iranplanner.ui.activity.reqestHotelStatus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ResultReqBundle;
import entity.ResultReqCount;
import tools.Util;

/**
 * Created by h.vahidimehr on 29/08/2017.
 */

public class RequestBundleAdapter extends RecyclerView.Adapter<RequestBundleAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ResultReqBundle> resultReqBundleList;

    public RequestBundleAdapter(Context context, List<ResultReqBundle> resultReqBundleList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.resultReqBundleList = resultReqBundleList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.content_status_bundle, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.hotelNameTv.setText(resultReqBundleList.get(position).getBundleRequest().getBundleLodgingTitle());
        holder.hotelRoomNumberTv.setText(resultReqBundleList.get(position).getBundleRequest().getBundleDateCount());
        holder.txtHotelDateReq.setText(Util.persianNumbers(Utils.getSimpleDateMilli(Long.valueOf(resultReqBundleList.get(position).getBundleRequest().getBundleDateFrom()))));
    }

    @Override
    public int getItemCount() {
        return resultReqBundleList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.imageHotel)
        ImageView imageHotel;
        @InjectView(R.id.hotelNameTv)
        TextView hotelNameTv;
        @InjectView(R.id.hotelRoomNumberTv)
        TextView hotelRoomNumberTv;
        @InjectView(R.id.txtHotelDateReq)
        TextView txtHotelDateReq;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
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
