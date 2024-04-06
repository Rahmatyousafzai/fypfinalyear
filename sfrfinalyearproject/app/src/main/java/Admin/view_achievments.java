package Admin;

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

import Class_for_admin.Achievement;
import adopter.achivmentadopter;

public class view_achievments extends AppCompatActivity {
    private RecyclerView recyclerView;
    private achivmentadopter adapter;
    private List<Achievement> dataList;
    ImageView profileimage ,imgback;
    TextView profilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_achievments);
        profileimage=findViewById(R.id.profileimage);
        profilename=findViewById(R.id.profelname);
        imgback=findViewById(R.id.imgback);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int imageResourceId= intent.getIntExtra("image", 0);



        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////////////onclickListener////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////

        imgback.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        navigateToback();
    }
});

        // Set profile name
        profilename.setText(name);

        // Set profile image
        if (imageResourceId != 0) {
            profileimage.setImageResource(imageResourceId);
        }

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
///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////METHODS////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    private void navigateToback() {


        Intent intent=new Intent(view_achievments.this, ad_addachivment.class);
        startActivity(intent);
        finish();
    }
}
