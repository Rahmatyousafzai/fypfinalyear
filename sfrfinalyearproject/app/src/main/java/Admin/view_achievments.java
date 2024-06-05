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

import java.util.List;

import ModeClasees.Achievement;
import adopter.AchievementAdapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_achievments extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AchievementAdapter adapter;
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



        recyclerView = findViewById(R.id.allachiRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAchievements();
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


    }
///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////METHODS////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    private void fetchAchievements() {


        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<Achievement>> call = apiService.getAchievements();

        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Achievement> achievements = response.body();
                    adapter = new AchievementAdapter(achievements, view_achievments.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }






    private void navigateToback() {


        Intent intent=new Intent(view_achievments.this, ad_addachivment.class);
        startActivity(intent);
        finish();
    }
}
