package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ftfavstudent extends AppCompatActivity {

    ImageView imgback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftfavstudent);

        imgback=findViewById(R.id.tcback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                back();

            }
        });




    }



    public void back(){


        Intent intent=new Intent(ftfavstudent.this,faculty_dashboard.class);
        startActivity(intent);
        finish();






    }




    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, faculty_dashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }

}