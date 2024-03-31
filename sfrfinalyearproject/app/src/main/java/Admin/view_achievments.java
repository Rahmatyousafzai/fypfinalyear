package Admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import Class_for_admin.Achievement;
import adopter.achivmentadopter;

public class view_achievments extends AppCompatActivity {
    private RecyclerView recyclerView;
    private achivmentadopter adapter;
    private List<Achievement> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievments);

        recyclerView = findViewById(R.id.viewachivment);
        dataList = new ArrayList<>();

        // Add your data to dataList
        dataList.add(new Achievement(R.drawable.heart, "Rahmat Yousafzai", "Sample description"));
        dataList.add(new Achievement(R.drawable.heart, "Rahmat Yousafzai", "Sample description"));
        dataList.add(new Achievement(R.drawable.heart, "Rahmat Yousafzai", "Sample description"));
        dataList.add(new Achievement(R.drawable.heart, "Rahmat Yousafzai", "Sample description"));
        dataList.add(new Achievement(R.drawable.heart, "Rahmat Yousafzai", "Sample description"));


        adapter = new achivmentadopter(dataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
