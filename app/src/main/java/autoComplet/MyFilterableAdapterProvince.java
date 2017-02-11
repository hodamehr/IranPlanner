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

import entity.Province;

/**
 * Created by h.vahidimehr on 08/01/2017.
 */

public class MyFilterableAdapterProvince extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Province> items;
    private List<Province> tempItems;
    private List<Province> suggestions;

    public MyFilterableAdapterProvince(Context context, int resource, List<Province> items) {
        super(context, resource, 0, items);

        this.context = context;
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<Province>(items);
        suggestions = new ArrayList<Province>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        Province item = items.get(position);

        if (item != null && view instanceof TextView) {
            ((TextView) view).setText(item.getProvinceName());
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Province) resultValue).getProvinceName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            int index;
            if (constraint != null) {
                suggestions.clear();
                boolean first = true;
                for (Province names : tempItems) {
//                    tempItems.indexOf(names);

                    if (first) {
                        if (names.getProvinceName().toLowerCase().contains(constraint.toString())) {
                            suggestions.add(names);
                            first = false;
                        }
                    } else {
                        if (names.getProvinceName().toLowerCase().contains(constraint.toString())) {
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
            List<Province> filterList = (ArrayList<Province>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Province item : filterList) {
                    add(item);
                    notifyDataSetChanged();
                }
            }
        }
    };

}
