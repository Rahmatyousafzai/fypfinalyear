package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import genral.templete;

public class faculty_public_messagebody extends AppCompatActivity {
Button sendwish;
ImageView addtemplate,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_public_messagebody);

        sendwish=findViewById(R.id.sendwish);
        addtemplate=findViewById(R.id.addtem);
        back=findViewById(R.id.back);


        sendwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendwish();


            }
        });
        addtemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addtemplate();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });




    }

    private void back() {
        Intent intent= new Intent(faculty_public_messagebody.this,faculty_select_message_option.class);
        startActivity(intent);
        finish();
    }

    private void addtemplate() {
        Intent intent= new Intent(faculty_public_messagebody.this, templete.class);
        startActivity(intent);
        finish();


    }

    private void sendwish() {
        Intent intent= new Intent(faculty_public_messagebody.this, faculty_dashboard.class);
        startActivity(intent);
        finish();

    }
}