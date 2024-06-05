package student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import ModeClasees.Emoji;
import adopter.EmojiAdapter2;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class snotification extends AppCompatActivity {
    RecyclerView recyclerView;
    private EmojiAdapter2 emojiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snotification);


       recyclerView = findViewById(R.id.rcnotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchEmojis();
    }





    private void fetchEmojis() {
        Apiservices apiService = RetrofitClient.getInstance();

        Call<List<Emoji>> call = apiService.getAllEmojis();
        call.enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Emoji> emojiList = response.body();
                   // emojiAdapter = new EmojiAdapter2(snotification.this, emojiList);
                    recyclerView.setAdapter(emojiAdapter);
                } else {
                    Toast.makeText(snotification.this, "Failed to fetch emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("EmojiListActivity", "API call failed: " + t.getMessage());
                Toast.makeText(snotification.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }





    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, stdashboard.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }
}