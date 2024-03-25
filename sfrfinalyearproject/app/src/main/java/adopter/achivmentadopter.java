package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class achivmentadopter extends BaseAdapter {


    private List<String> mData;
    private LayoutInflater mInflater;

    // Constructor
    public achivmentadopter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        alumniAdopter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.achviement_view_list, parent, false);
            holder = new alumniAdopter.ViewHolder();
            holder.textView= convertView.findViewById(R.id.proname);
            holder.imageView = convertView.findViewById(R.id.imgprofile);
            holder.textView = convertView.findViewById(R.id.achdesc);


            convertView.setTag(holder);
        } else {
            holder = (alumniAdopter.ViewHolder) convertView.getTag();
        }

        String name = mData.get(position);
        holder.textView.setText(name);

        return convertView;
    }

    static class ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView imageView;



    }




}
