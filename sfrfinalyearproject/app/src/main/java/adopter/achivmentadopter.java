package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import Class_for_admin.Achievement;

public class achivmentadopter extends RecyclerView.Adapter< achivmentadopter.ViewHolder> {
        private List<Achievement> dataList;
        private Context context;

        public achivmentadopter(List<Achievement> dataList, Context context) {
            this.dataList = dataList;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achviement_view_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Achievement data = dataList.get(position);
            holder.profileImage.setImageResource(data.getImageResource());
            holder.profileName.setText(data.getName());
            holder.achievementDescription.setText(data.getDescription());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView profileImage;
            TextView profileName;
            TextView achievementDescription;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                profileImage = itemView.findViewById(R.id.imgprofile);
                profileName = itemView.findViewById(R.id.proname);
                achievementDescription = itemView.findViewById(R.id.achdesc);
            }
        }
    }
