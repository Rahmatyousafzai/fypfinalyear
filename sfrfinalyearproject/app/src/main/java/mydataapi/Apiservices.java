package mydataapi;

import java.util.List;

import adopter.Wish;
import adopter.userdetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface Apiservices {

    @GET("api/User/Login")
    Call<String> login(
            @Query("username") String username,
            @Query("password") String password,
            @Query("usertype") String userType
    );


    @GET("Api/User/GetUser")
    Call<userdetail> getUserDetails(@Query("username") String username);

    @GET("Api/User/GetWishes")
    public Call<List<Wish>> getWishes(@Query("receiverId") String receiverId) ;

    @GET("api/Student/GetALLTeacher")
    Call<List<userdetail>> getAllTeachers(); // Assuming the response is a string



}
