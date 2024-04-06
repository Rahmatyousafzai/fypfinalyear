package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import genral.templete;

public class Ad_messagebody extends AppCompatActivity {
    ImageView imgAddtemplete;
    Button btnshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_messagebody);
        imgAddtemplete =findViewById(R.id.imgtamplate);
        btnshare= this.<Button>findViewById(R.id.btnshare);



        ///////////////////////////////////////////////////////////////////////////////
        //////////////////////Click Listner///////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        imgAddtemplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                templetechois();
            }
        });
        imgAddtemplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();
            }
        });












    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////     FUNCTION       ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    public  void templetechois(){

        imgAddtemplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ad_messagebody.this, templete.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });







    }

    public  void Share(){


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ad_messagebody.this, ad_dashboard.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });


    }



}