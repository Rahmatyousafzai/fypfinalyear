package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class groupmessage_body extends AppCompatActivity {
ImageView userimage;
TextView userusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmessage_body);
        userimage=findViewById(R.id.userimage);
        userusername=findViewById(R.id.userusername);
        Intent intent = getIntent();

        // Retrieve the data from the Intent
        String userName = intent.getStringExtra("userName");
        int imageResource = intent.getIntExtra("imageResource", 0);
        userusername.setText(userName);
        userimage.setImageResource(imageResource);
    }
}