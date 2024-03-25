package adopter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfrfinalyearproject.R;

import java.util.List;
public class TeachercustomAdopter  extends  ArrayAdapter<String>{







        private List<String> dataList;
        private LayoutInflater inflater;

        public TeachercustomAdopter(Context context, List<String> dataList) {
            super(context, 0, dataList);
            this.dataList = dataList;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.teacherlistvies_item, parent, false);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.proimage);
                holder.textViewTitle = convertView.findViewById(R.id.txtname);
                holder.imageView2 = convertView.findViewById(R.id.favimage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // Bind data to views
            holder.textViewTitle.setText(dataList.get(position));

            return convertView;
        }

        static class ViewHolder {
            ImageView imageView;
            TextView textViewTitle;
            ImageView imageView2;
        }
    }








