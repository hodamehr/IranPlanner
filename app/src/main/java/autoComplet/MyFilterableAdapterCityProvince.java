package autoComplet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import entity.CityProvince;
import entity.Province;

/**
 * Created by h.vahidimehr on 08/01/2017.
 */

public class MyFilterableAdapterCityProvince extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<CityProvince> items;
    private List<CityProvince> tempItems;
    private List<CityProvince> suggestions;

    public MyFilterableAdapterCityProvince(Context context, int resource, List<CityProvince> items) {
        super(context, resource, 0, items);

        this.context = context;
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<CityProvince>(items);
        suggestions = new ArrayList<CityProvince>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        CityProvince item = items.get(position);

        if (item != null && view instanceof TextView) {
            ((TextView) view).setText(item.getTitle());
        }

        return view;
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((CityProvince) resultValue).getTitle();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            int index;
            if (constraint != null) {
                suggestions.clear();
                boolean first = true;
                for (CityProvince names : tempItems) {
//                    tempItems.indexOf(names);

                    if (first) {
                        if (names.getTitle().toLowerCase().contains(constraint.toString())) {
                            suggestions.add(names);
                            first = false;
                        }
                    } else {
                        if (names.getTitle().toLowerCase().contains(constraint.toString())) {
                            suggestions.add(names);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<CityProvince> filterList = (ArrayList<CityProvince>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (CityProvince item : filterList) {
                    add(item);
                    notifyDataSetChanged();
                }
            }
        }

    };

}
