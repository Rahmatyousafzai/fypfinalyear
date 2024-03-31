package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class sectionmessage_body extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectionmessage_body);
        Button btnshare=findViewById(R.id.pshare);



        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharepost();
            }
        });



    }

    private void navigateToBack() {
        Intent intent=new Intent(sectionmessage_body.this,ad_section.class);
        startActivity(intent);
        finish();

    }

    private void sharepost() {
        Intent intent=new Intent(sectionmessage_body.this,ad_dashboard.class);
        startActivity(intent);
        finish();
    }
}