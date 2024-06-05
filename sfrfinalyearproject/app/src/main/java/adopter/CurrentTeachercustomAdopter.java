package adopter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Wish;
import ModeClasees.cuTeacher;
import ModeClasees.user;
import mydataapi.RetrofitClient;

public class CurrentTeachercustomAdopter extends RecyclerView.Adapter<CurrentTeachercustomAdopter.ViewHolder> {

    private List<cuTeacher> teacherList;
    private OnTeacherClickListener clickListener;

    public CurrentTeachercustomAdopter(List<cuTeacher> teacherList, OnTeacherClickListener clickListener) {
        this.teacherList = teacherList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherlistvies_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cuTeacher teacher = teacherList.get(position);
        holder.bind(teacher);
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onTeacherClick(teacher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void setTeacherList(List<cuTeacher> teacherList) {
        this.teacherList = teacherList;
        notifyDataSetChanged();
    }

    public interface OnTeacherClickListener {
        void onTeacherClick(user teacher);

        void onTeacherClick(cuTeacher teacher);

        void onTeacherClick(Wish wish);

        void onTeacherClick(Object item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView fullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            fullName = itemView.findViewById(R.id.full_name);
        }

        public void bind(cuTeacher teacher) {
            // Set profile image using Picasso
            if (teacher.getProfileImage() != null && !teacher.getProfileImage().isEmpty()) {
                String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacher.getProfileImage() + ".jpg";

                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.baseline_account_circle_24)
                        .into(profileImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            } else {

                profileImage.setImageResource(R.drawable.baseline_account_circle_24);
            }

            // Log the values of firstName and lastName
            Log.d("UserDetails", "First Name: " + teacher.getFirstName() + ", Last Name: " + teacher.getLastName());

            // Set full name
            String firstName = teacher.getFirstName();
            String lastName = teacher.getLastName();

            // If both firstName and lastName are not null or empty, display the concatenation of firstName and lastName
            // Otherwise, display "Unknown User"
            String fullNameText;
            if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
                fullNameText = firstName + " " + lastName;
            } else {
                fullNameText = "Unknown User";
            }
            fullName.setText(fullNameText);
        }

    }
}
