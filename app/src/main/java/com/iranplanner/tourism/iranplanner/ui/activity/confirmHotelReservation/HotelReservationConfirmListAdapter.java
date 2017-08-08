package com.iranplanner.tourism.iranplanner.ui.activity.confirmHotelReservation;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import entity.ReqLodgingReservation;
import tools.CustomDialogNumberPicker;

/**
 * Created by Hoda on 10/01/2017.
 */
public class HotelReservationConfirmListAdapter extends RecyclerView.Adapter<HotelReservationConfirmListAdapter.ViewHolder> {
    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;
    LayoutInflater inflater;
    List<entity.ResultRoom> resultRooms;
    Map<Integer, Integer> selectedRooms;
    List<ReqLodgingReservation> reqLodgingReservationList;
    Integer roomNumberSelected;
    Integer roomPricefinal;
    Integer PriceHalfboardIn;
    Integer PriceHalfboardOut, roomCapacityExtra, priceAddPeople, priceHalfInPrice, roomCapacityExtraPrice,
            priceHalfOutPrice;
    int durationTravel;
    Date startOfTravel;
    int selectAddPeople = 0;
    private Activity activity;

    public HotelReservationConfirmListAdapter(int durationTravel, Date startOfTravel, Activity a, DataTransferInterface dtInterface, Context context, int rowLayout, Map<Integer, Integer> selectedRooms, List<entity.ResultRoom> ResultRooms) {
        this.resultRooms = ResultRooms;
        this.context = context;
        this.rowLayout = rowLayout;
        this.selectedRooms = selectedRooms;
        this.activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.startOfTravel = startOfTravel;
        this.durationTravel = durationTravel;


    }

    @Override
    public HotelReservationConfirmListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.content_room_reservation_fill, viewGroup, false);
        return new HotelReservationConfirmListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

//        roomNumberSelected = (selectedRooms.get(position) != null) ? Integer.valueOf(selectedRooms.get(position)) : 1;
        roomPricefinal = (resultRooms.get(position).getRoom_price_final() != null) ? Integer.valueOf(resultRooms.get(position).getRoom_price_final()) : 1;
        PriceHalfboardIn = (resultRooms.get(position).getRoomPriceHalfboardIn() != null) ? Integer.valueOf(resultRooms.get(position).getRoomPriceHalfboardIn()) : 0;
        PriceHalfboardOut = (resultRooms.get(position).getRoomPriceHalfboardOut() != null) ? Integer.valueOf(resultRooms.get(position).getRoomPriceHalfboardOut()) : 0;
        roomCapacityExtra = (resultRooms.get(position).getRoomCapacityExtra() != null) ? Integer.valueOf(resultRooms.get(position).getRoomCapacityExtra()) : 0;
        roomCapacityExtraPrice = (resultRooms.get(position).getRoomPriceAdPeople() != null) ? Integer.valueOf(resultRooms.get(position).getRoomPriceAdPeople()) : 0;
        priceAddPeople = (resultRooms.get(position).getPriceAddPeople() != null) ? Integer.valueOf(resultRooms.get(position).getPriceAddPeople()) : 0;
        priceHalfInPrice = (resultRooms.get(position).getRoomPriceHalfboardIn() != null) ? Integer.valueOf(resultRooms.get(position).getRoomPriceHalfboardIn()) : 0;
        priceHalfOutPrice = (resultRooms.get(position).getRoomPriceHalfboardOut() != null) ? Integer.valueOf(resultRooms.get(position).getRoomPriceHalfboardOut()) : 0;
        viewHolder.roomType.setText(resultRooms.get(position).getRoomTitle());
        viewHolder.txtPrice.setText(roomPricefinal * durationTravel + "");
        viewHolder.txtAddPeople.setText(roomCapacityExtraPrice * durationTravel * selectAddPeople + "");
        if (resultRooms.get(position).getHalfIn() != null && resultRooms.get(position).getHalfOut() != null && resultRooms.get(position).getHalfIn() && resultRooms.get(position).getHalfOut()) {
            viewHolder.txthalfInPrice.setText(priceHalfOutPrice + priceHalfInPrice + "");
        } else if (resultRooms.get(position).getHalfIn() != null && resultRooms.get(position).getHalfIn()) {
            viewHolder.txthalfInPrice.setText(priceHalfInPrice + "");
        } else if (resultRooms.get(position).getHalfOut() != null && resultRooms.get(position).getHalfOut()) {
            viewHolder.txthalfInPrice.setText(priceHalfOutPrice + "");
        } else {
            viewHolder.txthalfInPrice.setText("");

        }
        viewHolder.edtHeadNameReservation.setText("");
//        viewHolder.edtHeadLastNameReservation.setText(resultRooms.get(position).getHalfIn());
        viewHolder.addPerHolderHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNumber(position, roomCapacityExtra, viewHolder.txtaddPersonValue, null, " نفراضافه ");
            }
        });
        viewHolder.NationalHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogString(position, 1, viewHolder.txtNationalityValue, new String[]{"ایرانی", "خارجی"}, " نفراضافه ");

            }
        });
        viewHolder.edtHeadNameReservation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start != 0 || (start == 0 && before == 0 && count == 1)) {
//                    sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_pink));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
//                    resultRooms.get(position).setHeadName(editable.toString());
//                    notifyDataSetChanged();
//                    sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
                }
            }
        });
        viewHolder.checkHalfIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
                resultRooms.get(position).setHalfIn(b);
                notifyDataSetChanged();
//                }
            }
        });
        viewHolder.checkHalfOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
                resultRooms.get(position).setHalfOut(b);
                notifyDataSetChanged();
//                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return resultRooms.size();
    }

    private void showDialogNumber(final int position, int number, final TextView txtaddPersonValue, String[] strings, String title) {
        CustomDialogNumberPicker cdd = new CustomDialogNumberPicker(activity, number, 0, title, strings);
        cdd.show();
        cdd.setDialogResult(new CustomDialogNumberPicker.OnDialogNumberPick() {
            @Override
            public void finish(int result) {
                selectAddPeople = result;
                txtaddPersonValue.setText(result + "نفر اضافه");
                resultRooms.get(position).setSelectedAddNumbers(result + "");
                notifyDataSetChanged();
            }
        });
    }

    private void showDialogString(final int position, int number, final TextView txtaddPersonValue, String[] strings, String title) {
        CustomDialogNumberPicker cdd = new CustomDialogNumberPicker(activity, number, 0, title, strings);
        cdd.show();
        cdd.setDialogResult(new CustomDialogNumberPicker.OnDialogNumberPick() {
            @Override
            public void finish(int result) {
                selectAddPeople = result;
                txtaddPersonValue.setText( (result == 0) ? "ایرانی" : "خارجی");

//                txtaddPersonValue.setText(result + "نفر ddd");
                resultRooms.get(position).setSelectedNationality(result + "");
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.roomType)
        TextView roomType;
        @InjectView(R.id.txtPrice)
        TextView txtPrice;
        @InjectView(R.id.txtAddPeople)
        TextView txtAddPeople;

        @InjectView(R.id.checkHalfIn)
        CheckBox checkHalfIn;
        @InjectView(R.id.checkHalfOut)
        CheckBox checkHalfOut;
        @InjectView(R.id.txthalfInPrice)
        TextView txthalfInPrice;
        @InjectView(R.id.txtaddPersonValue)
        TextView txtaddPersonValue;
        @InjectView(R.id.txtNationalityValue)
        TextView txtNationalityValue;
       /* @InjectView(R.id.numberAdditional_spinner)
        Spinner numberAdditional_spinner;*/

        @InjectView(R.id.edtHeadNameReservation)
        EditText edtHeadNameReservation;
        @InjectView(R.id.edtHeadLastNameReservation)
        EditText edtHeadLastNameReservation;
        @InjectView(R.id.holderNationality)
        RelativeLayout holderNationality;
        @InjectView(R.id.addPerHolderHolder)
        RelativeLayout addPerHolderHolder;
        @InjectView(R.id.NationalHolder)
        RelativeLayout NationalHolder;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }


}


