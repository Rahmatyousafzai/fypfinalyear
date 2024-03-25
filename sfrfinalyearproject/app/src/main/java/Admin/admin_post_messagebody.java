package Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.sfrfinalyearproject.R;

import Faculty.faculty_select_message_option;
import genral.templete;
import student.stteacher;

public class admin_post_messagebody extends AppCompatActivity {
    Button sharepost;
    CheckBox isEmail;
    ImageView templet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post_messagebody);



        sharepost=findViewById(R.id.pshare);
        isEmail=findViewById(R.id.isemail);
        templet=findViewById(R.id.btnforword);

share();
templete();




    }



    public  void  templete(){



        templet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(admin_post_messagebody.this, templete.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack
                // when the student_login activity starts
                finish();

            }
        });

    }


    public  void  share(){



        sharepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(admin_post_messagebody.this);

                // Set the dialog title, message, and buttons
                builder.setTitle("Title")
                        .setMessage("Message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Create an Intent to start the student_login activity
                                Intent intent = new Intent(admin_post_messagebody.this, ad_dashboard.class);
                                startActivity(intent);
                                // Finish the MainActivity so that it's removed from the back stack
                                // when the student_login activity starts
                                finish();
                                // Action to perform when OK button is clicked
                                dialog.dismiss(); // Close the dialog
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Action to perform when Cancel button is clicked
                                dialog.dismiss(); // Close the dialog
                            }
                        });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });






    }




}