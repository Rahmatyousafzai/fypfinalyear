package Admin;

import static com.example.sfrfinalyearproject.R.id.btnsave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfrfinalyearproject.R;

public class ad_addachivment extends AppCompatActivity {
    ImageView imgback;
    Button btnsave;

    TextView viewach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_addachivment);
        TextView viewach=findViewById(R.id.viewachivment);
        Button btnsave=findViewById(R.id.btnsave);
        ImageView imgback=findViewById(R.id.back);

















    }


    public  void back(){

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_addachivment.this,ad_dashboard.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });




    }
    public  void save(){

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_addachivment.this,ad_dashboard.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });




    }



    public  void  viewAchivment(){


        viewach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_addachivment.this, view_achievments.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });






    }










}