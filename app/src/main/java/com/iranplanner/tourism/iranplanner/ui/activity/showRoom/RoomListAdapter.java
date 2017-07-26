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

import entity.LodgingRoomBed;
import entity.LodgingRoomFacility;
import entity.ResultRoom;

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
        if(ResultRoom.get(position).getRoomCapacityExtra()!=null && (Integer) ResultRoom.get(position).getRoomCapacityExtra()!=0){
            viewHolder.txtCapacity.setText(ResultRoom.get(position).getRoomCapacityAdult() + " نفر +" + (Integer) ResultRoom.get(position).getRoomCapacityExtra() + "نفر اضافه");
        }else {
            viewHolder.txtCapacity.setText(ResultRoom.get(position).getRoomCapacityAdult() + " نفر");
        }
        viewHolder.BreakfastHolder.setVisibility(View.VISIBLE);
        List<LodgingRoomFacility> LodgingRoomFacility= ResultRoom.get(position).getLodgingRoomFacility();
        for (entity.LodgingRoomFacility lodgingRoomFacility : LodgingRoomFacility) {
            if (lodgingRoomFacility.getRoomFacilityId() == 3754) {
                viewHolder.BreakfastHolder.setVisibility(View.VISIBLE);
            }
            if (lodgingRoomFacility.getRoomFacilityId() == 3755) {
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
        viewHolder.txtPrice.setText(Utils.persianNumbers(String.valueOf(ResultRoom.get(position).getRoomPrice()/10))+" تومان");
//        viewHolder.txtnewPrice.setText(ResultRoom.get(position).getRoomTitle());
    }

    @Override
    public int getItemCount() {
        return ResultRoom.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView roomType,txtPrice,txtnewPrice;
        private TextView txtCapacity, txtCapacityRoomDetail1, txtCapacityRoomDetail2, txtCapacityRoomDetail3, txtCapacityRoomDetail4;
        private RelativeLayout capacityRoomHolderDetail1, capacityRoomHolderDetail2, capacityRoomHolderDetail3, capacityRoomHolderDetail4
                ,BreakfastHolder,WifiHolder;

        public ViewHolder(View view) {
            super(view);
            roomType = (TextView) view.findViewById(R.id.roomType);
            txtCapacity = (TextView) view.findViewById(R.id.txtCapacity);
            txtCapacityRoomDetail1 = (TextView) view.findViewById(R.id.txtCapacityRoomDetail1);
            txtCapacityRoomDetail2 = (TextView) view.findViewById(R.id.txtCapacityRoomDetail2);
            txtCapacityRoomDetail3 = (TextView) view.findViewById(R.id.txtCapacityRoomDetail3);
            txtCapacityRoomDetail4 = (TextView) view.findViewById(R.id.txtCapacityRoomDetail4);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtnewPrice = (TextView) view.findViewById(R.id.txtnewPrice);
            capacityRoomHolderDetail1 = (RelativeLayout) view.findViewById(R.id.capacityRoomHolderDetail1);
            capacityRoomHolderDetail2 = (RelativeLayout) view.findViewById(R.id.capacityRoomHolderDetail2);
            capacityRoomHolderDetail3 = (RelativeLayout) view.findViewById(R.id.capacityRoomHolderDetail3);
            capacityRoomHolderDetail4 = (RelativeLayout) view.findViewById(R.id.capacityRoomHolderDetail4);
            WifiHolder = (RelativeLayout) view.findViewById(R.id.WifiHolder);
            BreakfastHolder = (RelativeLayout) view.findViewById(R.id.BreakfastHolder);
        }
    }


}


