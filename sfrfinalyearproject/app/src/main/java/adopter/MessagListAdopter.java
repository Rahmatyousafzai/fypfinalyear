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

import facultyClasses.mWishlist;
import mydataapi.RetrofitClient;

public class MessagListAdopter extends RecyclerView.Adapter<MessagListAdopter.ViewHolder> {

    private List<mWishlist> teacherList;
    private final OnTeacherClickListener listener;

    public MessagListAdopter(List<mWishlist> teacherList, OnTeacherClickListener listener) {
        this.teacherList = teacherList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlewish, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mWishlist wish = teacherList.get(position);
        holder.bind(wish, listener);
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void setTeacherList(List<mWishlist> teacherList) {
        this.teacherList = teacherList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;

        private final TextView senddate;
        private final ImageView profileImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            senddate=itemView.findViewById(R.id.senddate);
            nameTextView = itemView.findViewById(R.id.name);
            profileImageView = itemView.findViewById(R.id.wishImage);
        }

        void bind(final mWishlist wish, final OnTeacherClickListener listener) {
            nameTextView.setText(wish.getFirstName() + " " + wish.getLastName());
            senddate.setText(wish.getWishDateTime());
            String imageUrl =RetrofitClient.getBaseUrl() + "images/profileimages/" + wish.getProfileImage() + ".jpg";
            Picasso.get().load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(profileImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTeacherClick(wish);  // Ensure this is correct
                }
            });
        }

    }
}
