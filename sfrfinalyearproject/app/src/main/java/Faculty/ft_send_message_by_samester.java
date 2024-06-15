package Faculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import facultyClasses.Audience;
import facultyClasses.InsertPapolationDataDto;
import facultyClasses.InsertPapolationResponse;
import facultyClasses.MessageRecipient;
import facultyClasses.Sendwish;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;

public class ft_send_message_by_samester extends AppCompatActivity {

    private Button done;
    private EditText typesomething;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);

        done = findViewById(R.id.done);
        typesomething = findViewById(R.id.typesomthing);
        username = UserDataSingleton.getInstance().getUsername();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from intent or user input
                int programId = getIntent().getIntExtra("programId", 0);
                int[] semesterIds = getIntent().getIntArrayExtra("semesterIds");
                int[] sections = getIntent().getIntArrayExtra("sections");
                String senderId = username;
                String content = typesomething.getText().toString();

                // Create Sendwish object
                Sendwish sendWish = new Sendwish(content); // Using the constructor that accepts only content

                // Create Audience object
                Audience audience = new Audience(0,programId, semesterIds, sections); // Replace 0 with actual ad_id if needed

                // Create MessageRecipient object
                MessageRecipient messageRecipient = new MessageRecipient(audience.getAd_id(),sendWish.getSw_id());

                // Create DTO object
                InsertPapolationDataDto dto = new InsertPapolationDataDto(sendWish, audience, messageRecipient,
                        programId, semesterIds, sections);

                // Show progress dialog
                ProgressDialog progressDialog = new ProgressDialog(ft_send_message_by_samester.this);
                progressDialog.setMessage("Inserting data...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // Call Retrofit API
                Apiservices apiService = RetrofitClient.getInstance();
                Call<InsertPapolationResponse> call = apiService.insertPapolationData(dto);
                call.enqueue(new Callback<InsertPapolationResponse>() {
                    @Override
                    public void onResponse(Call<InsertPapolationResponse> call, Response<InsertPapolationResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            showSuccessDialog();
                        } else {
                            showErrorDialog("Failed to insert data. Server returned error.");
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertPapolationResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        showErrorDialog("Failed to insert data. " + t.getMessage());
                    }
                });
            }
        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage("Data inserted successfully!")
                .setPositiveButton("OK", (dialog, which) -> {
                    startActivity(new Intent(ft_send_message_by_samester.this, faculty_dashboard.class));
                    finish();
                })
                .show();
    }

    private void showErrorDialog(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("OK", null)
                .show();
    }
}
