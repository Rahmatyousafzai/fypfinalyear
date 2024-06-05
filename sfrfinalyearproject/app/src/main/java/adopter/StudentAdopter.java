package adopter;

import android.content.Context;
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

import ModeClasees.Student;
import mydataapi.RetrofitClient;

public class StudentAdopter  extends RecyclerView.Adapter<StudentAdopter.StudentViewHolder>{




        private Context context;
        private List<Student> alumniList;
    private String TAG;

    public StudentAdopter(Context context, List<Student> alumniList) {
            this.context = context;
            this.alumniList = alumniList;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.student_rows, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = alumniList.get(position);
            holder.username.setText(student.getUsername());
            holder.fname.setText(student.getFname());
            holder.lname.setText(student.getLname());

            String profileImage = student.getImage();
            Log.d(TAG, "Profile image:"+profileImage);


            if (profileImage != null && !profileImage.isEmpty()) {
                String imageUrl = RetrofitClient.getBaseUrl()+"images/profileimages/"+student.getImage()+".jpg";
                Log.d(TAG, "Loading image from URL: " + imageUrl);

                Picasso.get()
                        .load(imageUrl)
                        .error(R.drawable.baseline_account_circle_24)
                        .into(holder.profileImage, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d(TAG, "Image loaded successfully: " + imageUrl);
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e(TAG, "Error loading image: " + imageUrl, e);
                            }
                        });
            } else {
                Log.w(TAG, "Profile image is null or empty. Setting placeholder image.");
                holder.profileImage.setImageResource(R.drawable.baseline_account_circle_24);
            }






            Picasso.get().load(student.getImage()).into(holder.profileImage);
        }

        @Override
        public int getItemCount() {
            return alumniList.size();
        }

        public static class StudentViewHolder extends RecyclerView.ViewHolder {
            TextView username, fname, lname;
            ImageView profileImage;

            public StudentViewHolder(@NonNull View itemView) {
                super(itemView);
                username = itemView.findViewById(R.id.username);
                fname = itemView.findViewById(R.id.fname);
                lname = itemView.findViewById(R.id.lname);
                profileImage = itemView.findViewById(R.id.profile_image);
            }
        }
    }





