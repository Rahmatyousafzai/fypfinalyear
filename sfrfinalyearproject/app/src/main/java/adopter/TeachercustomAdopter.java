package adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sfrfinalyearproject.R;

import java.util.List;

public class TeachercustomAdopter extends RecyclerView.Adapter<TeachercustomAdopter.ViewHolder> {
    private List<userdetail> teacherList;

    public TeachercustomAdopter(List<userdetail> teacherList) {
        this.teacherList = teacherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherlistvies_item, parent, false);
        return new ViewHolder(view);
    }


    // Inside onBindViewHolder method

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        userdetail teacher = teacherList.get(position);

        // Check if the context retrieved from the itemView is not null before using it with Glide
        if (holder.itemView.getContext() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(teacher.getProfileimage())
                    .into(holder.profileImageView);
        } else {
            // Handle the case where the context is null
        }

        holder.nameTextView.setText(teacher.getFname());
        holder.favoriteImageView.setImageResource(teacher.isFavorite() ? R.drawable.favorite_filled : R.drawable.favorite_empty);

        // Set click listener for favorite icon
        holder.favoriteImageView.setOnClickListener(v -> {
            teacher.setFavorite(!teacher.isFavorite());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView;
        TextView nameTextView;
        ImageView favoriteImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.proimage);
            nameTextView = itemView.findViewById(R.id.txtname);
            favoriteImageView = itemView.findViewById(R.id.favimage);
        }
    }
}
