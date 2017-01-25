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

import entity.City;

/**
 * Created by h.vahidimehr on 08/01/2017.
 */

public class MyFilterableAdapterCity extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<City> items;
    private List<City> tempItems;
    private List<City> suggestions;

    public MyFilterableAdapterCity(Context context, int resource, List<City> items) {
        super(context, resource, 0, items);

        this.context = context;
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<City>(items);
        suggestions = new ArrayList<City>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        City item = items.get(position);

        if (item != null && view instanceof TextView) {
            ((TextView) view).setText(item.getCityName());
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue)
        {
            String str =((City) resultValue).getCityName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {int index;
            if (constraint != null) {
                suggestions.clear();
                boolean first=true;
                for (City names : tempItems) {
//                    tempItems.indexOf(names);

                    if(first){
                        if (names.getCityName().toLowerCase().regionMatches(0, constraint.toString(), 0, 2)) {
                            suggestions.add(names);
                            first=false;
                        }
                    }else {
                        if (names.getCityName().toLowerCase().regionMatches(0, constraint.toString(), 0, constraint.length())) {
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
            List<City> filterList = (ArrayList<City>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (City item : filterList) {
                    add(item);
                    notifyDataSetChanged();
                }
            }
        }
    };

}
