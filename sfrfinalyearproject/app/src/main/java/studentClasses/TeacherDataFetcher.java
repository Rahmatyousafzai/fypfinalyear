package studentClasses;

import android.util.Log;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherDataFetcher {

    private Apiservices apiServices;

    public TeacherDataFetcher() {
        this.apiServices = RetrofitClient.getInstance();
    }

    public void fetchUserData(DataFetchCallback callback) {
        String username = TeacherDataSingleton.getInstance().getUsername();
        if (username == null) {
            Log.e("DataFetcher", "Username is null. Cannot fetch user data.");
            callback.onFailure(new Exception("Username is null. Cannot fetch user data."));
            return;
        }

        Call<TeacherResponse> call = apiServices.getTeacherData(username);
        call.enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TeacherResponse teacherResponse = response.body();
                    TeacherResponse teacherdata = new TeacherResponse(); // Create an instance of TeacherResponse
                    TeacherData data = teacherResponse.getData(); // Call getData() method on the instance


                    TeacherDataSingleton.getInstance().setUserData(data);
                    Log.d("DataFetcher", "User data fetched and saved successfully.");
                    callback.onSuccess(data);
                } else {
                    Log.e("DataFetcher", "Failed to fetch user data: " + response.message());
                    callback.onFailure(new Exception("Failed to fetch user data: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {
                Log.e("DataFetcher", "Network error: " + t.getMessage());
                callback.onFailure(new Exception("Network error: " + t.getMessage()));
            }
        });
    }

    public interface DataFetchCallback {
        void onSuccess(TeacherData userData);
        void onFailure(Exception e);
    }
}
