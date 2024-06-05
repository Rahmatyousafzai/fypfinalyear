package adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Wish;
import mydataapi.RetrofitClient;

public class MessagListAdopter extends RecyclerView.Adapter<MessagListAdopter.ViewHolder> {
    private List<Wish> messageList;
    private OnTeacherClickListener listener;

    public MessagListAdopter(List<Wish> messageList, OnTeacherClickListener listener) {
        this.messageList = messageList;
        this.listener = listener;
    }

    public void setTeacherList(List<Wish> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelist_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wish wish = messageList.get(position);

        String image=wish.getProfileImage();
        holder.teacherName.setText(wish.getFirstName() + " " + wish.getLastName());
        String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" +image+ ".jpg";
        if (wish.getProfileImage() != null && !wish.getProfileImage().isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.teacherImage);
        } else {
            holder.teacherImage.setImageResource(R.drawable.baseline_account_circle_24);
        }

        holder.itemView.setOnClickListener(v -> listener.onTeacherClick(wish));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teacherName;
        ImageView teacherImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.teacher_name);
            teacherImage = itemView.findViewById(R.id.teacher_image);
        }
    }
}
