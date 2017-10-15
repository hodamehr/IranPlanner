package com.iranplanner.tourism.iranplanner.ui.activity.reservationRequestList;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iranplanner.tourism.iranplanner.R;

import java.util.ArrayList;
import java.util.List;

import entity.ResultReservationReqList;
import tools.Util;

/**
 * Created by h.vahidimehr on 05/09/2017.
 */

public class ReservationRequestAdapter extends RecyclerView.Adapter<ReservationRequestAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ResultReservationReqList> reservationReqLists;

    public ReservationRequestAdapter(Context context, List<ResultReservationReqList> reservationReqLists) {
        this.context = context;
        this.reservationReqLists = reservationReqLists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.content_purchase_hotel_list, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ResultReservationReqList current = reservationReqLists.get(position);
        holder.setData(current);
    }

    @Override
    public int getItemCount() {
        return reservationReqLists.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView tvCode, tvName, tvRoomType;
        private ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.reservationRequestRowCodeTv);
            tvName = (TextView) itemView.findViewById(R.id.reservationRequestRowNameTv);
            tvRoomType = (TextView) itemView.findViewById(R.id.reservationRequestRowRoomTypeTv);

            imageView = (ImageView) itemView.findViewById(R.id.requestStatusIv);
        }

        public void setData(ResultReservationReqList current) {

            String roomType = "نوع اتاق : " + current.getRequest().getReqRoomTitle();
            String code = "کد درخواست : " + current.getRequest().getReqId();

            tvCode.setText(Util.persianNumbers(code));
            tvName.setText(current.getRequest().getReqRoomTitle());
            tvRoomType.setText(roomType);

            // TODO: 10/15/17  Get Image Url From Api and Load it With Glide
        }
    }
}
