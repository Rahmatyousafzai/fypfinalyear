package Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adopter.alumniAdopter;

public class adminAddAlumni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_alumni);
        ListView listView = findViewById(R.id.addalumni);

        // Initialize data
        List<String> dataList = new ArrayList<>();
        dataList.add(Arrays.toString(new String[]{"", "Rahmat Yousafzai"}));
        dataList.add(Arrays.toString(new String[]{"", "Jaweria"}));
        dataList.add(Arrays.toString(new String[]{"", "Sheih chilli"}));
        dataList.add(Arrays.toString(new String[]{"", "Hina perveen"}));
        dataList.add(Arrays.toString(new String[]{"", "Muhammad bilal"}));




        // Add more data as needed

        // Initialize adapter
        alumniAdopter adapter = new alumniAdopter(this, dataList);

        // Set adapter to ListView

        listView.setAdapter(adapter);
    }
}