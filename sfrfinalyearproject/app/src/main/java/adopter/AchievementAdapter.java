package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import ModeClasees.Achievement;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {

    private List<Achievement> achievements;
    private Context context;

    public AchievementAdapter(List<Achievement> achievements, Context context) {
        this.achievements = achievements;
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
        Achievement achievement = achievements.get(position);
        holder.username.setText(achievement.getUsername());
        holder.firstName.setText(achievement.getFirstName());
        holder.lastName.setText(achievement.getLastName());
        holder.description.setText(achievement.getDescription());
        holder.title.setText(achievement.getTitle());
        holder.event.setText(achievement.getEvent());
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView firstName;
        public TextView lastName;
        public TextView description;
        public TextView title;
        public TextView event;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            event = itemView.findViewById(R.id.event);
        }
    }
}
