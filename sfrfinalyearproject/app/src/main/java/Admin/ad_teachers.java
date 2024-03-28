package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import adopter.TeachercustomAdopter;
import adopter.userdetail;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ad_teachers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeachercustomAdopter adapter;
    private List<userdetail> dataList;
    private Apiservices mAPIInterface;
    private ImageView pfimage, back;
    private TextView pfname, search;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_teachers);

        // Initialize views
        pfimage = findViewById(R.id.profileimage);
        pfname = findViewById(R.id.profelname);
        back = findViewById(R.id.back);
        search = findViewById(R.id.search);
        recyclerView = findViewById(R.id.rvteacher);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize Retrofit instance from separate class
        RetrofitClient retrofitClient = new RetrofitClient();
        mAPIInterface = retrofitClient.getClient().create(Apiservices.class);

        // Initialize data list
        dataList = new ArrayList<>();

        // Create adapter and set it to RecyclerView
        adapter = new TeachercustomAdopter(this, dataList, mAPIInterface);
        recyclerView.setAdapter(adapter);

        // Fetch data from API
        fetchDataFromAPI();

        // Get username from intent and set it to TextView
        Intent intent = getIntent();
        String username = intent.getStringExtra("prfilename");
        pfname.setText(username);

        // Handle back button click
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDashboard();
            }
        });

        // Setup search functionality
        setupSearch();
    }

    // Method to fetch data from API using Retrofit
    private void fetchDataFromAPI() {
        mAPIInterface.getAllTeachers().enqueue(new Callback<List<userdetail>>() {
            @Override
            public void onResponse(Call<List<userdetail>> call, Response<List<userdetail>> response) {
                hideProgressBar();
                if (response.isSuccessful() && response.body() != null) {
                    List<userdetail> responseData = response.body();
                    Log.d("API_RESPONSE", "Response Data: " + responseData.toString());
                    dataList.clear();
                    dataList.addAll(responseData);
                    adapter.notifyDataSetChanged();
                } else {
                    showToast("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<List<userdetail>> call, Throwable t) {
                hideProgressBar();
                showToast("Network error");
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }


    private void navigateToDashboard() {
        Intent intent = new Intent(ad_teachers.this, ad_dashboard.class);
        startActivity(intent);
        finish();
    }

    private void setupSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
