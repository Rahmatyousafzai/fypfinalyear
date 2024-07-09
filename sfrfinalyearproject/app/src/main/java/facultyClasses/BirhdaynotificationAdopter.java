package facultyClasses;

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

import ModeClasees.user;
import mydataapi.RetrofitClient;

public class BirhdaynotificationAdopter extends RecyclerView.Adapter<BirhdaynotificationAdopter.bithdayViewHolder> {




    private Context context;
    private List<user> Birthdayuser;
    private String TAG;

    public BirhdaynotificationAdopter(Context context, List<user> Birthdayuser) {
        this.context = context;
        this.Birthdayuser = Birthdayuser;
    }

    @NonNull
    @Override
    public bithdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.birthdayuser_rows, parent, false);
        return new BirhdaynotificationAdopter.bithdayViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull BirhdaynotificationAdopter.bithdayViewHolder holder, int position) {
        user user = Birthdayuser.get(position);
        holder.username.setText(user.getUsername());
        holder.fname.setText(user.getFirstName());
        holder.lname.setText(user.getLastName());

        String profileImage = user.getProfileImage();
        Log.d(TAG, "Profile image:"+profileImage);


        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl()+"images/profileimages/"+user.getProfileImage()+".jpg";
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






        Picasso.get().load(user.getProfileImage()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return Birthdayuser.size();
    }

    public static class bithdayViewHolder extends RecyclerView.ViewHolder {
        TextView username, fname, lname;
        ImageView profileImage;

        public bithdayViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            profileImage = itemView.findViewById(R.id.profile_image);
        }
    }




}
