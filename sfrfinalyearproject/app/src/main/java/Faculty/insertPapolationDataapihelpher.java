package Faculty;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import facultyClasses.Audience;
import facultyClasses.InsertPapolationDataDto;
import facultyClasses.InsertPapolationResponse;
import facultyClasses.MessageRecipient;
import facultyClasses.Sendwish;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insertPapolationDataapihelpher {


    private static Apiservices apiservices = RetrofitClient.getInstance();
    public interface InsertDataCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }
    public static void insertPapolationData(Context context, int programId,int sw_id, String programTitle, int[] semesterIds, int[] sections, String senderId,int adid, String content) {
        // Create SendWish object
        Sendwish sendWish = new Sendwish(0, content); // Replace 0 with actual sw_id if needed

        // Create Audience object
        Audience audience = new Audience(0,programId,semesterIds,sections); // Replace 0 with actual ad_id if needed

        // Process each combination of semesterIds and sections
        for (int i = 0; i < semesterIds.length; i++) {
            for (int j = 0; j < sections.length; j++) {
                int sectionName = sections[j];

                // Create MessageRecipient object for each combination
                MessageRecipient messageRecipient = new MessageRecipient(sendWish.getSw_id(),audience.getAd_id()); // Replace with actual IDs if needed

                // Create DTO object
                InsertPapolationDataDto dto = new InsertPapolationDataDto(sendWish, audience, messageRecipient, programId, new int[]{semesterIds[i]}, new int[]{sectionName});

                // Call API to insert data
                Call<InsertPapolationResponse> call = apiservices.insertPapolationData(dto);
                call.enqueue(new Callback<InsertPapolationResponse>() {
                    @Override
                    public void onFailure(Call<InsertPapolationResponse> call, Throwable t) {
                        Log.e("InsertData", "Error inserting data: " + t.getMessage());
                        Toast.makeText(context, "Error inserting data", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call<InsertPapolationResponse> call, Response<InsertPapolationResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("InsertData", "Data inserted successfully");
                            Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("InsertData", "Failed to insert data. Response code: " + response.code());
                            Toast.makeText(context, "Failed to insert data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }}
}
