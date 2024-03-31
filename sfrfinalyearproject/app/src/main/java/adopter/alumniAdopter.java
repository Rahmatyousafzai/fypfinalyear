package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import Class_for_admin.alumni;
import de.hdodenhof.circleimageview.CircleImageView;

public class alumniAdopter extends RecyclerView.Adapter<alumniAdopter.ViewHolder> {
    private List<alumni> profileList;
    private Context context;

    public alumniAdopter(List<alumni> profileList, Context context) {
        this.profileList = profileList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumni_add_rows, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        alumni profile = profileList.get(position);
        holder.profileImage.setImageResource(profile.getImageResource()); // Use profile.getImageResource() instead of alumni.getImageResource()
        holder.profileName.setText(profile.getName());
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add button click handling
            }
        });
    }


    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView profileName;
        Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.pfimage);
            profileName = itemView.findViewById(R.id.pfname);
            addButton = itemView.findViewById(R.id.alumadd);
        }
    }
}
