package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ft_section extends AppCompatActivity {
ImageView imgback;
Button btnwish;
CheckBox chksection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_section);

       imgback=findViewById(R.id.back);
        btnwish=findViewById(R.id.wish);
        chksection=findViewById(R.id.chksection);



        btnwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavgateTosection();



            }
        });






    }

    private void NavgateTosection() {


        Intent intent=new Intent(ft_section.this,select_section_all_student.class);
        startActivity(intent);
        finish();

    }

    private void NavigateToback() {
        Intent intent=new Intent(ft_section.this,ft_send_message_by_samester.class);
        startActivity(intent);
        finish();



    }


}