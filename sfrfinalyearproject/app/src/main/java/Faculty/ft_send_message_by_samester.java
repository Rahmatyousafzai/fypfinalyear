package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ft_send_message_by_samester extends AppCompatActivity {
Button done;
ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);
        done=findViewById(R.id.done);
        imgback=findViewById(R.id.back);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    private void back() {
        Intent intent=new Intent(ft_send_message_by_samester.this, message_option_section_alumni_student.class);
        startActivity(intent);
        finish();
    }

    private void done() {
        Intent intent=new Intent(ft_send_message_by_samester.this, ft_section.class);
        startActivity(intent);
        finish();

    }
}