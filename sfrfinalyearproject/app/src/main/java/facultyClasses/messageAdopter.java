package facultyClasses;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class messageAdopter extends RecyclerView.Adapter<messageAdopter.ViewHolder> {

    private List<message> userList;
    private OnItemClickListener listener;

    public messageAdopter(List<message> userList) {
        this.userList = userList;
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(String userName, int imageResource);
    }

    // Setter method for setting the item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_rows_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        message message = userList.get(position);
        holder.txtName.setText(message.getFirstName() + " " + message.getLastName());
        holder.dateTime.setText(message.getDateTime());
        holder.proImage.setImageResource(message.getImageResource());

        // Set click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(message.getFirstName() + " " + message.getLastName(), message.getImageResource());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView dateTime;
        ImageView proImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtname);
            dateTime = itemView.findViewById(R.id.dateTime);
            proImage = itemView.findViewById(R.id.proimage);
        }
    }
}
