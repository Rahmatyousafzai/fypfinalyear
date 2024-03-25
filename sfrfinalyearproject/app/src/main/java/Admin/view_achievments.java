package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adopter.achivmentadopter;
import adopter.alumniAdopter;

public class view_achievments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievments);

        ListView listView = findViewById(R.id.viewachivment);

        // Initialize data
        List<String> dataList = new ArrayList<>();
        dataList.add(Arrays.toString(new String[]{"", "Rahmat Yousafzai","student of the year"}));
        dataList.add(Arrays.toString(new String[]{"", "Jaweria","miss BiiT"}));
        dataList.add(Arrays.toString(new String[]{"", "Sheih chilli","jhoota dramabaz"}));
        dataList.add(Arrays.toString(new String[]{"", "Hina perveen",""}));
        dataList.add(Arrays.toString(new String[]{"", "Muhammad bilal"}));
        // Add more data as needed
        // Initialize adapter
        achivmentadopter adapter = new achivmentadopter(this, dataList);

        // Set adapter to ListView

        listView.setAdapter(adapter);






    }
}