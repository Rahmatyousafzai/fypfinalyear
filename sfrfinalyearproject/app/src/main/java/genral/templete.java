package genral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.templateAdopter;

public class templete extends AppCompatActivity {
    private RecyclerView alltemplet;
    private templateAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templete);
        TextView txtcond;
        ImageView back;
        back=findViewById(R.id.back);
        txtcond= findViewById(R.id.txtcondolence);
  alltemplet=findViewById(R.id.alltemplet);
        alltemplet.setLayoutManager(new LinearLayoutManager(this));


        txtcond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
              navigatetoCondolence();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the student_login activity
             navigatetoBack();


            }
        });

        populateData();

    }

    private void navigatetoBack() {

        Intent intent = new Intent(templete.this, condolencestemplate.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();

    }

    private void navigatetoCondolence() {
        Intent intent = new Intent(templete.this, condolencestemplate.class);
        startActivity(intent);
        // Finish the MainActivity so that it's removed from the back stack
        // when the student_login activity starts
        finish();
    }

    private void populateData() {
        List<String> data = new ArrayList<>();
        // Populate your data here, for example:
        data.add("\"Happy Birthday! \n" +
                "On your special day, I wish you all the happiness\n" +
                "love, and success in the world. May your birthday \n" +
                "be filled with laughter, joy, and unforgettable \n" +
                "moments surrounded by the people you cherish\n" +
                " most.");
        data.add("\"Congratulations on your achievement! \n" +
                "Your hard work, dedication, and perseverance \n" +
                "have paid off, and I couldn't be prouder of your\n" +
                " success. This achievement is a testament to your\n" +
                " talent, determination, and unwavering\n" +
                " commitment to excellence.");

        data.add("Wishing you continued success, happiness, and\n" +
                " fulfillment as you embark on the next chapter \n" +
                "of your journey. Remember to celebrate your \n" +
                "victories and always keep pushing forward\n" +
                "towards your goals.\n" +
                "Congratulations once again on this outstanding \n" +
                "achievement! You've earned every bit of the \n" +
                "success that comes your way, and I can't wait to \n" +
                "see where your journey takes you next.");

        data.add("\"Happy Birthday! \n" +
                "On your special day, I wish you all the happiness\n" +
                "love, and success in the world. May your birthday \n" +
                "be filled with laughter, joy, and unforgettable \n" +
                "moments surrounded by the people you cherish\n" +
                " most.");
        data.add("\"Congratulations on your achievement! \n" +
                "Your hard work, dedication, and perseverance \n" +
                "have paid off, and I couldn't be prouder of your\n" +
                " success. This achievement is a testament to your\n" +
                " talent, determination, and unwavering\n" +
                " commitment to excellence.");

        data.add("Wishing you continued success, happiness, and\n" +
                " fulfillment as you embark on the next chapter \n" +
                "of your journey. Remember to celebrate your \n" +
                "victories and always keep pushing forward\n" +
                "towards your goals.\n" +
                "Congratulations once again on this outstanding \n" +
                "achievement! You've earned every bit of the \n" +
                "success that comes your way, and I can't wait to \n" +
                "see where your journey takes you next.");
        data.add("\"Happy Birthday! \n" +
                "On your special day, I wish you all the happiness\n" +
                "love, and success in the world. May your birthday \n" +
                "be filled with laughter, joy, and unforgettable \n" +
                "moments surrounded by the people you cherish\n" +
                " most.");
        data.add("\"Congratulations on your achievement! \n" +
                "Your hard work, dedication, and perseverance \n" +
                "have paid off, and I couldn't be prouder of your\n" +
                " success. This achievement is a testament to your\n" +
                " talent, determination, and unwavering\n" +
                " commitment to excellence.");

        data.add("Wishing you continued success, happiness, and\n" +
                " fulfillment as you embark on the next chapter \n" +
                "of your journey. Remember to celebrate your \n" +
                "victories and always keep pushing forward\n" +
                "towards your goals.\n" +
                "Congratulations once again on this outstanding \n" +
                "achievement! You've earned every bit of the \n" +
                "success that comes your way, and I can't wait to \n" +
                "see where your journey takes you next.");



        // Add more messages as needed

       templateAdopter adapter = new templateAdopter(this, data);
       alltemplet.setAdapter(adapter);
    }





}