package adopter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.user;
import mydataapi.RetrofitClient;
import student.stteacher;

public class TeachercustomAdopter extends RecyclerView.Adapter<TeachercustomAdopter.ViewHolder> {
    private List<user> teacherList;
    private OnTeacherClickListener listener;
    Context context;

    private static final String TAG = "TeachercustomAdopter";

    public TeachercustomAdopter(List<user> teacherList, OnTeacherClickListener listener) {
        this.teacherList = teacherList;
        this.listener = listener;
        this.context=context;

    }

    public TeachercustomAdopter(List<user> teacherList, stteacher stteacher) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_row_, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user teacher = teacherList.get(position);

        String profileImage = teacher.getProfileImage();
        Log.d(TAG, "Profile image:"+profileImage);


        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl()+"images/profileimages/"+teacher.getProfileImage()+".jpg";
            Log.d(TAG, "Loading image from URL: " + imageUrl);

            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(holder.profileImageView, new Callback() {
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
            holder.profileImageView.setImageResource(R.drawable.baseline_account_circle_24);
        }

        holder.nameTextView.setText(teacher.getFirstName() + " " + teacher.getLastName());

        holder.itemView.setOnClickListener(v -> {
            listener.onTeacherClick(teacher);
        });

        holder.Teacherdeitaile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmojisPopupWindow();

            }
        });


    }






    private void showEmojisPopupWindow() {


        View popupView=LayoutInflater.from(context).inflate(R.layout.teacherdetial,null);


        ImageView iconprofile=popupView.findViewById(R.id.iconprofile);
        ImageView iconpfav=popupView.findViewById(R.id.iconfav);
        ImageView iconwish=popupView.findViewById(R.id.iconwish);

        TextView dprofilename=popupView.findViewById(R.id.dprofilename);

        TextView txtfavourite=popupView.findViewById(R.id.txtfav);

        TextView txtwish=popupView.findViewById(R.id.txtwish);


        PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.HORIZONTAL,LinearLayout.VERTICAL);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 10, 10);





    }
    @Override
    public int getItemCount() {
        if (teacherList == null) {
            return 0; // Return 0 if the list is null
        }
        return teacherList.size(); // Return the size of the list
    }


    public void setTeacherList(List<user> teacherList) {
        this.teacherList = teacherList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView;
        TextView nameTextView;
     ImageView   Teacherdeitaile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.pfimage);
            nameTextView = itemView.findViewById(R.id.pfname);
            Teacherdeitaile=itemView.findViewById(R.id.Teacherdeitaile);

        }
    }
}
