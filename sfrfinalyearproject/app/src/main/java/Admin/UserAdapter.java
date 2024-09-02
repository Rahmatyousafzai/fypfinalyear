package Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
       User user = userList.get(position);
        holder.usernameTextView.setText(user.getUsername());
        holder.fnameTextView.setText(user.getFname());
        holder.lnameTextView.setText(user.getLname());
        holder.totalWishesTextView.setText(String.valueOf(user.getTotalWishes()));
        // Load profile image if needed, e.g., using Glide or Picasso
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView fnameTextView;
        TextView lnameTextView;
        TextView totalWishesTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            fnameTextView = itemView.findViewById(R.id.fname);
            lnameTextView = itemView.findViewById(R.id.lname);
            totalWishesTextView = itemView.findViewById(R.id.ttttwish);
        }
    }
}
