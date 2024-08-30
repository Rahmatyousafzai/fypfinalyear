package facultyClasses;

import android.content.Context;
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

import Faculty.BirthdayUser;
import mydataapi.RetrofitClient;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder> {
    private final Context context;
    private final List<BirthdayUser> userList;
    private OnItemClickListener onItemClickListener;

    public UserDetailsAdapter(Context context, List<BirthdayUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_birthday_user, parent, false);
        return new UserDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailsViewHolder holder, int position) {
        BirthdayUser user = userList.get(position);
        holder.txtName.setText(user.getFirstName()+""+user.getLastName());
        Glide.with(context)
                .load(RetrofitClient.getBaseUrl()+""+user.getProfileImage()+""+".jpg") // Ensure profile image URL is correctly formatted
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(holder.imgProfile);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    static class UserDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgProfile;

        UserDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }
}
