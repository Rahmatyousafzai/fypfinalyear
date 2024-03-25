package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class customespinner extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mValues;

    public customespinner(Context context, List<String> values) {
        super(context, R.layout.custome_spinner, values);
        this.mContext = context;
        this.mValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.custome_spinner, parent, false);
        TextView textView = row.findViewById(R.id.spinner_item_text);
        textView.setText(mValues.get(position));
        return row;
    }
}