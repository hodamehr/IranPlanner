package com.iranplanner.tourism.iranplanner.ui.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.util.List;

import entity.HomeCountryProvince;
import tools.Util;

/**
 * Created by Hoda on 10/01/2017.
 */
// in adapter baraye neshon dadane list az itinerary ha hast.
public class HomeProvinceListAdapter extends RecyclerView.Adapter<HomeProvinceListAdapter.ViewHolder> {
    private List<HomeCountryProvince> homeCountryProvince;
    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;

    LayoutInflater inflater;

    public HomeProvinceListAdapter(Activity a, DataTransferInterface dtInterface, List<HomeCountryProvince> homeCountryProvince, Context context, int rowLayout) {
        this.homeCountryProvince = homeCountryProvince;
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public HomeProvinceListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.content_province_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeProvinceListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txtProvinceName.setText(homeCountryProvince.get(i).getProvinceTitle());
        ImageView imageView = viewHolder.imgProvince;
        if (homeCountryProvince.get(i).getImageUrl() != null) {
            Util.setImageView(String.valueOf(homeCountryProvince.get(i).getImageUrl()), context, imageView,null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return homeCountryProvince.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */ {
        private TextView txtProvinceName;
        private ImageView imgProvince;

        public ViewHolder(View view) {
            super(view);
            txtProvinceName = (TextView) view.findViewById(R.id.txtProvinceName);
            imgProvince = (ImageView) view.findViewById(R.id.imgProvince);
        }
    }
}


