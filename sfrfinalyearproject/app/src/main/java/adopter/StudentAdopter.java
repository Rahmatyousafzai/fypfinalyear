package adopter;// StudentAdapter.java

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sfrfinalyearproject.R;

import java.util.List;

import Faculty.facultymessagebody;
import ModeClasees.Student;

public class StudentAdopter extends RecyclerView.Adapter<StudentAdopter.StudentViewHolder> {

    private List<Student> studentList;
    private Context context;

    public StudentAdopter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_rows, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        if (holder.textFname != null) {
            holder.textFname.setText(student.getFname());
        } else {
            Log.e("StudentAdapter", "textFname is null");
        }

        if (holder.textLname != null) {
            holder.textLname.setText(student.getLname());
        } else {
            Log.e("StudentAdapter", "textLname is null");
        }

        if (holder.profileImage != null) {
            Glide.with(context)
                    .load(student.getImage())
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .into(holder.profileImage);
        } else {
            Log.e("StudentAdapter", "profileImage is null");
        }

        if (holder.detailsButton != null) {
            holder.detailsButton.setOnClickListener(v -> {
                showDetailsPopup(student);
            });
        } else {
            Log.e("StudentAdapter", "detailsButton is null");
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    private void showDetailsPopup(Student student) {
        new AlertDialog.Builder(context)
                .setTitle(student.getFname() + " " + student.getLname())
                .setMessage("Username: " + student.getUsername())
                .setPositiveButton("View Details", (dialog, which) -> {
                    Intent intent = new Intent(context, facultymessagebody.class);
                    intent.putExtra("username", student.getUsername());
                    intent.putExtra("fname", student.getFname());
                    intent.putExtra("lname", student.getLname());
                    intent.putExtra("image", student.getImage());
                    context.startActivity(intent);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }





    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView textFname;
        TextView textLname;
        Button detailsButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            textFname = itemView.findViewById(R.id.text_fname);
            textLname = itemView.findViewById(R.id.text_lname);
            detailsButton = itemView.findViewById(R.id.details_button);
        }
    }
}
