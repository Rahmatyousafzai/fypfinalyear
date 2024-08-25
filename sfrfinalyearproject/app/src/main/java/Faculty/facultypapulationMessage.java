package Faculty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.SelectAudeince;
import studentClasses.UserDataSingleton;

public class facultypapulationMessage extends AppCompatActivity {

    private Apiservices apiservices;
    private String username;
    private LinearLayout layoutAllocations;
    private Map<Integer, String> selectedAllocations = new HashMap<>();
    private Map<Integer, String> unselectedAllocations = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultypapulation_message);

        initializeViews();
        initializeApiServices();
        Button Done=findViewById(R.id.btndone);

        username = UserDataSingleton.getInstance().getUsername();
        fetchAllocations();

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendAllocationsToNextActivity();
            }
        });



    }



    private void initializeViews() {
        layoutAllocations = findViewById(R.id.checkboxelayout);
    }

    private void initializeApiServices() {
        apiservices = RetrofitClient.getInstance();
    }

    private void fetchAllocations() {
        Call<List<SelectAudeince>> call = apiservices.getAllocations(username);
        call.enqueue(new Callback<List<SelectAudeince>>() {
            @Override
            public void onResponse(Call<List<SelectAudeince>> call, Response<List<SelectAudeince>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SelectAudeince> allocations = response.body();
                    populateAllocationCheckBoxes(allocations);
                } else {
                    handleFetchError("fetchAllocations", response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SelectAudeince>> call, Throwable t) {
                handleFetchFailure("fetchAllocations", t);
            }
        });
    }

    private void populateAllocationCheckBoxes(List<SelectAudeince> allocations) {
        layoutAllocations.removeAllViews();
        unselectedAllocations.clear(); // Clear previous data

        for (SelectAudeince allocation : allocations) {
            unselectedAllocations.put(allocation.getTaId(), allocation.getTitle()); // Initially all allocations are unselected

            CheckBox checkBox = createCustomCheckBox(allocation);
            Log.d("checkboxITem", "checkboxitem" + allocation.getTitle());
            // Alternate row colors
            if (allocations.indexOf(allocation) % 2 == 0) {
                checkBox.setBackgroundColor(Color.parseColor("#FF03DAC5")); // Light grey
            } else {
                checkBox.setBackgroundColor(Color.parseColor("#CC6CE7")); // White
            }
            layoutAllocations.addView(checkBox);
        }
    }

    private CheckBox createCustomCheckBox(SelectAudeince allocation) {
        CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkboxw_item_rows, null);
        checkBox.setText(allocation.getTitle());
        checkBox.setTag(allocation.getTaId());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int allocationId = (int) buttonView.getTag();
            String allocationTitle = (String) buttonView.getText();

            if (isChecked) {
                selectedAllocations.put(allocationId, allocationTitle);
                unselectedAllocations.remove(allocationId);
            } else {
                selectedAllocations.remove(allocationId);
                unselectedAllocations.put(allocationId, allocationTitle);
            }
        });

        return checkBox;
    }

    private void handleFetchError(String methodName, int code, String message) {
        String errorMessage = "Failed to fetch data in " + methodName + ". Response code: " + code + ", Message: " + message;
        Log.e(methodName, errorMessage);
        Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void handleFetchFailure(String methodName, Throwable t) {
        String errorMessage = "Failed to fetch data in " + methodName + ": " + t.getMessage();
        Log.e(methodName, errorMessage);
        Toast.makeText(facultypapulationMessage.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void sendAllocationsToNextActivity() {
        Intent intent = new Intent(facultypapulationMessage.this, ft_send_message_by_samester.class);
        intent.putIntegerArrayListExtra("selectedIds", new ArrayList<>(selectedAllocations.keySet()));
        intent.putStringArrayListExtra("selectedTitles", new ArrayList<>(selectedAllocations.values()));
        intent.putIntegerArrayListExtra("unselectedIds", new ArrayList<>(unselectedAllocations.keySet()));
        intent.putStringArrayListExtra("unselectedTitles", new ArrayList<>(unselectedAllocations.values()));
        startActivity(intent);
    }
}
