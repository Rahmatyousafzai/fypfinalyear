package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import studentClasses.UserDataSingleton;

public class ft_send_message_by_samester extends AppCompatActivity {
Button done;
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_send_message_by_samester);
        done=findViewById(R.id.done);
        username = UserDataSingleton.getInstance().getUsername();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });


    }



    private void done() {
        Intent intent=new Intent(ft_send_message_by_samester.this, ft_section.class);
        startActivity(intent);
        finish();

    }
}