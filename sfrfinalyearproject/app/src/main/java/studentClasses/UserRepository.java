package studentClasses;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private Apiservices apiServices = RetrofitClient.getInstance();

    public void fetchUserData(String username, final UserRepositoryCallback callback) {
        Call<StudentResponse> call = apiServices.getUserData(username);
        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onFailure(new Exception("Failed to fetch user data"));
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    public interface UserRepositoryCallback {
        void onSuccess(studentData result);
        void onFailure(Exception e);
    }
}
