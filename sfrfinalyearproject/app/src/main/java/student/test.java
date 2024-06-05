package student;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.CustomAdapter;
import adopter.Type1Object;
import adopter.Type2Object;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


            List<Object> itemList = new ArrayList<>();
            itemList.add(new Type1Object("Type 1 Item 1"));
            itemList.add(new Type2Object(R.drawable.saeed));
            itemList.add(new Type1Object("Type 1 Item 2"));
            itemList.add(new Type2Object(R.drawable.zindalash));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            CustomAdapter adapter = new CustomAdapter(itemList);
            recyclerView.setAdapter(adapter);
        } else {


            Log.d("test","empty");
            // Handle the case where RecyclerView is null
        }


    }
}