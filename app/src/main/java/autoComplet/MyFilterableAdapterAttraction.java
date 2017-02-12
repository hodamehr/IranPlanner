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

import entity.Attraction;

/**
 * Created by h.vahidimehr on 08/01/2017.
 */

public class MyFilterableAdapterAttraction extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Attraction> items;
    private List<Attraction> tempItems;
    private List<Attraction> suggestions;

    public MyFilterableAdapterAttraction(Context context, int resource, List<Attraction> items) {
        super(context, resource, 0, items);

        this.context = context;
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<Attraction>(items);
        suggestions = new ArrayList<Attraction>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }

        Attraction item = items.get(position);

        if (item != null && view instanceof TextView) {
            ((TextView) view).setText(item.getAttractionName());
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
            String str =((Attraction) resultValue).getAttractionName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {int index;
            if (constraint != null) {
                suggestions.clear();
                boolean first=true;
                for (Attraction names : tempItems) {
//                    tempItems.indexOf(names);

                    if(first){
                        if (names.getAttractionName().toLowerCase().contains(constraint.toString())) {
                            suggestions.add(names);
                            first=false;
                        }
                    }else {
                        if (names.getAttractionName().toLowerCase().contains(constraint.toString())) {
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
            List<Attraction> filterList = (ArrayList<Attraction>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Attraction item : filterList) {
                    add(item);
                    notifyDataSetChanged();
                }
            }
        }
    };

}
