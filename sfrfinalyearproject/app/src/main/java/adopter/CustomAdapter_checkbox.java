package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class CustomAdapter_checkbox extends ArrayAdapter<String> {

    private List<String> dataList;
    private LayoutInflater inflater;

    public CustomAdapter_checkbox(Context context, List<String> dataList) {
        super(context, 0, dataList);
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.checkboxw_item_rows, parent, false);
            holder = new ViewHolder();
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind data to views
        holder.checkBox.setText(dataList.get(position));

        return convertView;
    }

    static class ViewHolder {
        CheckBox checkBox;
    }
}
