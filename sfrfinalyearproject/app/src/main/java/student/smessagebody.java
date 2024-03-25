package student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sfrfinalyearproject.R;

import genral.templete;

public class smessagebody extends AppCompatActivity {
    ImageView imgtemplate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smessagebody);


    imgtemplate=findViewById(R.id.imgtamplate);













    }




    public void templete(){


        imgtemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
                Intent intent = new Intent(smessagebody.this, templete.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();


            }
        });




    }

}