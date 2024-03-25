package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class SectionCustomAdapter extends ArrayAdapter<String[]> {
    private Context mContext;
    private int mResource;

    public SectionCustomAdapter(@NonNull Context context, int resource, @NonNull List<String[]> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        String[] data = getItem(position);
        if (data != null) {
            CheckBox chkSelection = convertView.findViewById(R.id.chkselection);
            TextView textName = convertView.findViewById(R.id.textName);
            TextView textMobile = convertView.findViewById(R.id.textMobile);
            TextView textNumber = convertView.findViewById(R.id.textNumber);
            TextView textSection = convertView.findViewById(R.id.textSection);

            chkSelection.setText(data[0]);
            textName.setText(data[1]);
            textMobile.setText(data[2]);
            textNumber.setText(data[3]);
            textSection.setText(data[4]);

            // Change row color based on condition (for example, if the section is "A")
            if (data[4].equals("A")) {
                convertView.setBackgroundColor(mContext.getResources().getColor(com.google.android.material.R.color.cardview_shadow_end_color));
            } else {
                convertView.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
            }




        }

        return convertView;
    }







}
