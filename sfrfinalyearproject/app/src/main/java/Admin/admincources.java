package Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import studentClasses.UserDataSingleton;

public class admincources extends AppCompatActivity {

    private Button sendMessage;
    private EditText typeSomething;
    private TextView profileName;
    private TextView designation;
    private ImageView profile;
    private ProgressBar progressBar;
    private Apiservices apiservices;
    private Spinner spinner, spinner1;
    private String username;
    private List<String> allocations; // List to store allocation items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincources);

        initializeUI();
        username = UserDataSingleton.getInstance().getUsername();
        apiservices = RetrofitClient.getInstance();


    }

    private void initializeUI() {
        sendMessage = findViewById(R.id.done);
        typeSomething = findViewById(R.id.typesomthing);
        profileName = findViewById(R.id.profelname);
        designation = findViewById(R.id.disgnation);
        profile = findViewById(R.id.profilepicture);
        progressBar = findViewById(R.id.progressBar); // Ensure this view is in your layout
        spinner = findViewById(R.id.semsetersection);
        spinner1 = findViewById(R.id.alocationid);
    }

}