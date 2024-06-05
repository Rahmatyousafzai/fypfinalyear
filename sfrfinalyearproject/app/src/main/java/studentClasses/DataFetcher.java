package studentClasses;

import android.util.Log;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataFetcher {

    private Apiservices apiServices;

    public DataFetcher() {
        this.apiServices = RetrofitClient.getInstance();
    }

    public void fetchUserData(DataFetchCallback callback) {
        String username = UserDataSingleton.getInstance().getUsername();
        if (username == null) {
            Log.e("DataFetcher", "Username is null. Cannot fetch user data.");
            callback.onFailure(new Exception("Username is null. Cannot fetch user data."));
            return;
        }

        Call<StudentResponse> call = apiServices.getUserData(username);
        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StudentResponse studentResponse = response.body();
                    studentData userData = studentResponse.getData();
                    UserDataSingleton.getInstance().setUserData(userData);
                    Log.d("DataFetcher", "User data fetched and saved successfully.");
                    callback.onSuccess(userData);
                } else {
                    Log.e("DataFetcher", "Failed to fetch user data: " + response.message());
                    callback.onFailure(new Exception("Failed to fetch user data: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                Log.e("DataFetcher", "Network error: " + t.getMessage());
                callback.onFailure(new Exception("Network error: " + t.getMessage()));
            }
        });
    }

    public interface DataFetchCallback {
        void onSuccess(studentData userData);
        void onFailure(Exception e);
    }
}
