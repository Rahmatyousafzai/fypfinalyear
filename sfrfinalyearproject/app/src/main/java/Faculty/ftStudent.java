package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ftStudent extends AppCompatActivity {
ImageView imgback;
ListView tclistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_student);
        imgback=findViewById(R.id.tcback);
        tclistview=findViewById(R.id.tclistview);



        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });




    }


    public void back(){


        Intent intent=new Intent(ftStudent.this,faculty_dashboard.class);
        startActivity(intent);
        finish();

    }


}