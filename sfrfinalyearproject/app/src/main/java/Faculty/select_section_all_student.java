package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class select_section_all_student extends AppCompatActivity {
Button btndone;
ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_section_all_student);
btndone=findViewById(R.id.btndone);
imgback=findViewById(R.id.back);

btndone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
imgback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(select_section_all_student.this, ft_section.class);
        startActivity(intent);
        finish();
    }
});





    }







}