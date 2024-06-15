package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ad_addachivment extends AppCompatActivity {
    ImageView imgback,profileimage;
    Button btnsave;

    TextView viewach ,title,messagedescription,profilename;
Spinner catogary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_addachivment);
        viewach = findViewById(R.id.viewachevment);
        btnsave = findViewById(R.id.btnsave);
        imgback = findViewById(R.id.imageback);
        title = findViewById(R.id.title);
        messagedescription = findViewById(R.id.titlemessage);



                 ///////////////////////////////////////////////////////////////////////////////
                //////////////////////Click Listner///////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////
        viewach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDisplayachvement();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDisplayachvement();
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDashboard();
            }
        });




        ///////////////////////////////////////////////////////////////////////////////
        //////////////////////SET ADOPTER CATAGORY///////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
      // Make sure 'catogary' is defined and initialized as a Spinner object



    }


    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////FUNCTION////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    private void navigateToDashboard() {
        Intent intent = new Intent(ad_addachivment.this,ad_dashboard.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack

        // when the student_login activity starts
        finish();
    }

    private void navigateToDisplayachvement() {
        Intent intent = new Intent(ad_addachivment.this,view_achievments.class);

        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack

        // when the student_login activity starts
        finish();
    }

}














