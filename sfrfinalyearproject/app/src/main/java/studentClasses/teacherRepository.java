package studentClasses;

import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacherRepository {
    private Apiservices apiServices = RetrofitClient.getInstance();

    public void fetchTeacherData(String username, final teacherRepositoryCallback callback) {
        Call<TeacherResponse> call = apiServices.getTeacherData(username);
        call.enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getData());
                } else {
                    callback.onFailure(new Exception("Failed to fetch user data"));
                }
            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    public interface teacherRepositoryCallback{
        void onSuccess(TeacherData result);
        void onFailure(Exception e);
    }
}
