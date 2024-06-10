package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import studentClasses.UserDataSingleton;

public class ft_send_message_by_course extends AppCompatActivity {
Button done;
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_course);

        username = UserDataSingleton.getInstance().getUsername();



        done=findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateTomessagebody();
            }
        });

    }

    private void navigateTomessagebody() {

        Intent intent=new Intent(ft_send_message_by_course.this,course_message_body.class);
        startActivity(intent);
        finish();



    }
}