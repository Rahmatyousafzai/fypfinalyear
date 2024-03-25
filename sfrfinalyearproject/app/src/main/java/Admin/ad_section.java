package Admin;
import static com.example.sfrfinalyearproject.R.id.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.SectionCustomAdapter;


public class ad_section extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_section);

        ListView listView = findViewById(sectionlistview);
        Button Done;

                 //   Done=findViewById(adddone);

        // Sample data
        List<String[]> dataList = new ArrayList<>();
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        dataList.add(new String[]{"","2020_arid_3535", "Rahmat Yousafzai", "Male", "1234"});
        dataList.add(new String[]{"","2020-arid-3434", "jaweria", "Female", "1122"});
        // Add more rows as needed

        // Create and set the adapter
        SectionCustomAdapter adaptersection = new SectionCustomAdapter(this, R.layout.ad_section_rowdesign, dataList);
        listView.setAdapter(adaptersection);



        /* Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ad_section.this, Ad_messagebody.class);
                startActivity(intent);
                // Finish the MainActivity so that it's removed from the back stack

                // when the student_login activity starts
                finish();

            }
        });*/




    }


}