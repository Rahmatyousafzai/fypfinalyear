package adopter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sfrfinalyearproject.R;

import java.util.List;

import ModeClasees.Student;
import facultyClasses.FavritUser;
import facultyClasses.OnStudentInteractionListener;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;

public class StudentAdopter extends RecyclerView.Adapter<StudentAdopter.StudentViewHolder> {

    private List<Student> studentList;
    private Context context;
    private OnStudentInteractionListener listener;
    private String currentuser;

    public StudentAdopter(Context context, List<Student> studentList, OnStudentInteractionListener listener) {
        this.context = context;
        this.studentList = studentList;
        this.listener = listener;
        this.currentuser = UserDataSingleton.getInstance().getUsername();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each student row
        View view = LayoutInflater.from(context).inflate(R.layout.student_rows, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Get the current student
        Student student = studentList.get(position);

        // Set student details
        holder.textFname.setText(student.getFname());
        holder.textLname.setText(student.getLname());

        // Load student profile image using Glide
        Glide.with(context)
                .load(student.getImage())
                .placeholder(R.drawable.baseline_account_circle_24)
                .into(holder.profileImage);

        // Set up the click listener for the details button
        holder.detailsButton.setOnClickListener(v -> showFavoriteDialog(student));
    }

    @Override
    public int getItemCount() {
        // Return the total number of students
        return studentList.size();
    }

    private void showFavoriteDialog(Student student) {
        // Create and configure the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.popup_student_details, null);
        builder.setView(dialogView);

        ImageView dialogHeartImageView = dialogView.findViewById(R.id.dialogHeartImageView);
        TextView dialogTextView = dialogView.findViewById(R.id.dialogTextView);
        dialogTextView.setText("Favorite");

        // Check and update favorite status
        checkAndUpdateFavoriteStatus(student.getUsername(), dialogHeartImageView);

        // Set up the click listener for the heart icon
        dialogHeartImageView.setOnClickListener(v -> {
            if (dialogHeartImageView.getDrawable().getConstantState() == context.getDrawable(R.drawable.favorite_filled).getConstantState()) {
                deleteFavorite(student.getUsername(), dialogHeartImageView);
            } else {
                addFavorite(currentuser, student.getUsername(), dialogHeartImageView);
                listener.onSendMessage(student);
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void updateData(List<Student> newStudentList) {
        this.studentList = newStudentList;
        notifyDataSetChanged(); // Notify the adapter that data has changed
    }
    private void checkAndUpdateFavoriteStatus(String favtuserID, ImageView dialogHeartImageView) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<FavritUser>> call = apiService.getfavorite(currentuser, favtuserID);

        call.enqueue(new Callback<List<FavritUser>>() {
            @Override
            public void onResponse(Call<List<FavritUser>> call, Response<List<FavritUser>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FavritUser> favritUsers = response.body();
                    if (!favritUsers.isEmpty()) {
                        dialogHeartImageView.setImageResource(R.drawable.favorite_filled);
                    } else {
                        dialogHeartImageView.setImageResource(R.drawable.favorite_empty);
                    }
                } else {
                    dialogHeartImageView.setImageResource(R.drawable.favorite_empty);
                }
            }

            @Override
            public void onFailure(Call<List<FavritUser>> call, Throwable t) {
                Log.e("StudentAdopter", "Failed to fetch favorite users: " + t.getMessage());
                dialogHeartImageView.setImageResource(R.drawable.favorite_empty);
            }
        });
    }

    private void addFavorite(String userID, String favoriteID, ImageView dialogHeartImageView) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<Void> call = apiService.addFavorite(userID, favoriteID);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dialogHeartImageView.setImageResource(R.drawable.favorite_filled);
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 409) { // Conflict
                    Toast.makeText(context, "Already in favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add favorite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("StudentAdopter", "API call failed: " + t.getMessage());
                Toast.makeText(context, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteFavorite(String favoriteID, ImageView dialogHeartImageView) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<Void> call = apiService.deleteFavorite(currentuser, favoriteID);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dialogHeartImageView.setImageResource(R.drawable.favorite_empty);
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to remove favorite", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("StudentAdopter", "API call failed: " + t.getMessage());
                Toast.makeText(context, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView textFname;
        TextView textLname;
        ImageView detailsButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            textFname = itemView.findViewById(R.id.text_fname);
            textLname = itemView.findViewById(R.id.text_lname);
            detailsButton = itemView.findViewById(R.id.details_button);
        }
    }
}
