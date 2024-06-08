// SelectUserAdapter.java
package student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.user;
import mydataapi.RetrofitClient;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.ViewHolder> {
    private List<user> userList;
    private OnUserSelectListener listener;

    public SelectUserAdapter(List<user> userList, OnUserSelectListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_select_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user user = userList.get(position);
        holder.userName.setText(user.getFirstName() + " " + user.getLastName());
        Picasso.get().load(RetrofitClient.getBaseUrl() + "images/profileimages/" + user.getProfileImage() + ".jpg")
                .error(R.drawable.baseline_account_circle_24)
                .into(holder.profileImage);

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(holder.checkBox.isChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onUserSelect(user, isChecked));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setTeacherList(List<user> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        ImageView profileImage;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.txtname);
            profileImage = itemView.findViewById(R.id.profileimage);
            checkBox = itemView.findViewById(R.id.chkbox);
        }
    }

    public interface OnUserSelectListener {
        void onUserSelect(user user, boolean isSelected);
    }
}
