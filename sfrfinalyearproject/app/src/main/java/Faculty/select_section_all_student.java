package Faculty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import studentClasses.UserDataSingleton;
import studentClasses.teacherRepository;

public class select_section_all_student extends AppCompatActivity {
Button btndone;
String username;
ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_section_all_student);
btndone=findViewById(R.id.btndone);




        username = UserDataSingleton.getInstance().getUsername();

        teacherRepository userRepository = new teacherRepository();

     



btndone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});






    }
















}