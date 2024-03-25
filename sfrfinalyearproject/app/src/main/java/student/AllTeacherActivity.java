package student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

public class AllTeacherActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allteacher);


        imgBack = findViewById(R.id.imgback);

        // Retrieve teacher names from intent


        // Set click listener for back button
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to stteacher activity
                Intent intent = new Intent(AllTeacherActivity.this, stteacher.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
