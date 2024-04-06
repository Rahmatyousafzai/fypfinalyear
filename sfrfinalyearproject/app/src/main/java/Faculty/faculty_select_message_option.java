package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class faculty_select_message_option extends AppCompatActivity {

Button btnall,btngroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_select_message_option);

        btnall=findViewById(R.id.btnall);
        btngroup=findViewById(R.id.btngroup);




        btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavegateALL();

            }
        });

        btngroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navegatetogroup();


            }
        });



    }


    public void NavegateALL(){


        Intent intent=new Intent(faculty_select_message_option.this, faculty_public_messagebody.class);
        startActivity(intent);
        finish();


    }




    public void Navegatetogroup(){


Intent intent=new Intent(faculty_select_message_option.this,message_option_section_alumni_student.class);
startActivity(intent);
finish();
    }



}