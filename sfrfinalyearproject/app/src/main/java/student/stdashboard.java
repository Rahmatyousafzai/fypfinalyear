package student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

public class stdashboard extends AppCompatActivity {

    TextView txtNews, txtMessage, txtNotification, txtFavTeacher;
    ImageView imgMessage, imgNotification;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdashboard);

        // Initialize UI elements
        txtNews = findViewById(R.id.news);
        txtMessage = findViewById(R.id.txtmessage);
        txtNotification = findViewById(R.id.txtnotification);
        txtFavTeacher = findViewById(R.id.favtecher);
        imgMessage = findViewById(R.id.imgmessage);
        imgNotification = findViewById(R.id.imgnptification);







        // Set click listeners
        txtFavTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFavTeacher();
            }
        });

        txtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNews();
            }
        });

        txtNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNotification();
            }
        });

        txtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMessage();
            }
        });
    }

    private void navigateToFavTeacher() {
        Intent intent = new Intent(stdashboard.this, favteacher.class);
        startActivity(intent);
        finish();
    }

    private void navigateToNews() {
        // Currently navigates to the same activity, update this as per your requirement
        Intent intent = new Intent(stdashboard.this, stdashboard.class);
        startActivity(intent);
        finish();
    }

    private void navigateToMessage() {
        Intent intent = new Intent(stdashboard.this, smassage.class);
        startActivity(intent);
        finish();
    }

    private void navigateToNotification() {
        Intent intent = new Intent(stdashboard.this, snotification.class);
        startActivity(intent);
        finish();
    }
}
