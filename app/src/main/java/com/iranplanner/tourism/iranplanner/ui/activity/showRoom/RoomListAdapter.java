package com.iranplanner.tourism.iranplanner.ui.activity.showRoom;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.LodgingRoomBed;
import entity.LodgingRoomFacility;
import entity.ResultRoom;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {
    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;
    LayoutInflater inflater;
    List<entity.ResultRoom> ResultRoom;


    public RoomListAdapter(Activity a, DataTransferInterface dtInterface, List<ResultRoom> ResultRoom, Context context, int rowLayout) {
        this.ResultRoom = ResultRoom;
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public RoomListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.activity_reservation_room_detail, viewGroup, false);
        return new RoomListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.roomType.setText(ResultRoom.get(position).getRoomTitle());
        if (ResultRoom.get(position).getRoomCapacityExtra() != null && !ResultRoom.get(position).getRoomCapacityExtra().equals("0")) {
            viewHolder.txtCapacity.setText(ResultRoom.get(position).getRoomCapacityAdult() + " نفر +" + ResultRoom.get(position).getRoomCapacityExtra() + "نفر اضافه");
        } else {
            viewHolder.txtCapacity.setText(ResultRoom.get(position).getRoomCapacityAdult() + " نفر");
        }
        viewHolder.BreakfastHolder.setVisibility(View.VISIBLE);
        List<LodgingRoomFacility> LodgingRoomFacility = ResultRoom.get(position).getLodgingRoomFacility();
        for (entity.LodgingRoomFacility lodgingRoomFacility : LodgingRoomFacility) {
            if (lodgingRoomFacility.getRoomFacilityId().equals("3754")) {
                viewHolder.BreakfastHolder.setVisibility(View.VISIBLE);
            }
            if (lodgingRoomFacility.getRoomFacilityId().equals("3755")) {
                viewHolder.WifiHolder.setVisibility(View.VISIBLE);
            }
        }
        if (ResultRoom.get(position).getLodgingRoomBed().size() > 0) {
            List<LodgingRoomBed> LodgingRoomBeds = ResultRoom.get(position).getLodgingRoomBed();
            int index = 0;
            for (LodgingRoomBed lodgingRoomBed : LodgingRoomBeds) {
                if (index == 0) {
                    viewHolder.capacityRoomHolderDetail1.setVisibility(View.VISIBLE);
                    viewHolder.txtCapacityRoomDetail1.setText(lodgingRoomBed.getRoomBedName() + " " + lodgingRoomBed.getRoomBedCount() + " عدد");
                }
                if (index == 1) {
                    viewHolder.capacityRoomHolderDetail2.setVisibility(View.VISIBLE);
                    viewHolder.txtCapacityRoomDetail2.setText(lodgingRoomBed.getRoomBedName() + " " + lodgingRoomBed.getRoomBedCount() + " عدد");
                }
                if (index == 2) {
                    viewHolder.capacityRoomHolderDetail3.setVisibility(View.VISIBLE);
                    viewHolder.txtCapacityRoomDetail3.setText(lodgingRoomBed.getRoomBedName() + " " + lodgingRoomBed.getRoomBedCount() + " عدد");
                }
                if (index == 3) {
                    viewHolder.capacityRoomHolderDetail4.setVisibility(View.VISIBLE);
                    viewHolder.txtCapacityRoomDetail4.setText(lodgingRoomBed.getRoomBedName() + " " + lodgingRoomBed.getRoomBedCount() + " عدد");
                }
                index++;

            }
        }
        viewHolder.txtPriceRoom.setText(Util.persianNumbers(String.valueOf(Long.decode(ResultRoom.get(position).getRoomPrice()) / 10)) + " تومان");
        viewHolder.txtnewPrice.setText(ResultRoom.get(position).getRoomPricePromotion()!=null ? Util.persianNumbers(String.valueOf(Long.decode(ResultRoom.get(position).getRoomPricePromotion()) / 10)): "");
        viewHolder.txtShowPercentPercentage.setText(ResultRoom.get(position).getRoomPriceDifferencePercent()!=null ? "تخفیف تا %"+Util.persianNumbers(ResultRoom.get(position).getRoomPriceDifferencePercent()): "");
    }

    @Override
    public int getItemCount() {
        return ResultRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.roomType)
        TextView roomType;
        @InjectView(R.id.txtCapacity)
        TextView txtCapacity;
        @InjectView(R.id.txtShowPercentPercentage)
        TextView txtShowPercentPercentage;
        @InjectView(R.id.txtCapacityRoomDetail1)
        TextView txtCapacityRoomDetail1;
        @InjectView(R.id.txtCapacityRoomDetail2)
        TextView txtCapacityRoomDetail2;
        @InjectView(R.id.txtCapacityRoomDetail3)
        TextView txtCapacityRoomDetail3;
        @InjectView(R.id.txtCapacityRoomDetail4)
        TextView txtCapacityRoomDetail4;
        @InjectView(R.id.txtnewPrice)
        TextView txtnewPrice;
        @InjectView(R.id.txtPriceRoom)
        TextView txtPriceRoom;
        @InjectView(R.id.capacityRoomHolderDetail1)
        RelativeLayout capacityRoomHolderDetail1;
        @InjectView(R.id.capacityRoomHolderDetail2)
        RelativeLayout capacityRoomHolderDetail2;
        @InjectView(R.id.capacityRoomHolderDetail3)
        RelativeLayout capacityRoomHolderDetail3;
        @InjectView(R.id.capacityRoomHolderDetail4)
        RelativeLayout capacityRoomHolderDetail4;
        @InjectView(R.id.WifiHolder)
        RelativeLayout WifiHolder;
        @InjectView(R.id.BreakfastHolder)
        RelativeLayout BreakfastHolder;
        @InjectView(R.id.chooseHolder)
        RelativeLayout chooseHolder;
        @InjectView(R.id.txtNumberChoose)
        TextView txtNumberChoose;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }


}


