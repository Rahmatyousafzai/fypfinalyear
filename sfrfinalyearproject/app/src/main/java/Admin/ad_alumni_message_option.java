package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class  ad_alumni_message_option extends AppCompatActivity {
    public Button sendtoAll,sendtoGroup,newalumni;
    ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_alumni_message_option);

        sendtoAll=findViewById(R.id.aprivate);
        sendtoGroup=findViewById(R.id.apublic);
        newalumni=findViewById(R.id.newalumni);
        ImageView imgback=findViewById(R.id.imgback);




        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////////////onclickListener////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToback();
            }
        });

        sendtoGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendtoGroup();

            }
        });
        sendtoAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessagetoAll();


            }
        });
        newalumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newalumni();


            }
        });



    }
 //////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// METHOD////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
    private void navigateToback() {
   
Intent intent=new Intent(ad_alumni_message_option.this,postshareoption.class);
startActivity(intent);
finish();


    }



    public void  SendMessagetoAll(){
        Intent intent = new Intent(ad_alumni_message_option.this, postbody.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();



    }
    public void  SendtoGroup(){


        Intent intent = new Intent(ad_alumni_message_option.this, postbody.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();



    }
    public void  newalumni(){

        Intent intent = new Intent(ad_alumni_message_option.this, adminAddAlumni.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();

    }






}