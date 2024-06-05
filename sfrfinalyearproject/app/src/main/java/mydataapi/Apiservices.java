package mydataapi;

import java.util.List;

import ModeClasees.Achievement;
import ModeClasees.Emoji;
import ModeClasees.Message;
import ModeClasees.Student;
import ModeClasees.UserDataResponse;
import ModeClasees.Wish;
import ModeClasees.cuTeacher;
import ModeClasees.papulationselection;
import ModeClasees.user;
import facultyClasses.Course;
import modelclassespost.SendWishRequestDto;
import modelclassespost.SendWishResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import studentClasses.StudentResponse;

public interface Apiservices {

    // Login API call
    @GET("api/User/Login")
    Call<String> login(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("api/Student/GetALLTeacher")
    Call<List<user>> getAllTeachers();
    // Get wishes API call
    @GET("api/Student/GetfavTeacher")
    Call<List<cuTeacher>> getFavTeacher(@Query("username") String username);
    @GET("api/wish/GetAllEmojis")
    Call<List<Emoji>> getAllEmojis();
    @GET("api/Wish/GetStudentDashBoardWishes")
    Call<List<Wish>> getWishes();

    // Get all teachers API call
    @GET("api/Student/GetCurrentTeacher")
    Call<List<cuTeacher>> getCurrentTeacher(@Query("username") String username);


    @GET("api/Wish/messageList")
    Call<List<Wish>> getinboxlist( @Query("messageType") String messageType);

    @GET("api/Wish/inboxmessageList")
    Call<List<Wish>> getinboxMessage( @Query("senderID") String senderID);
    @GET("api/Wish/chatmessage")
    Call<List<Message>> chatmessage(
            @Query("senderID") String senderID,
            @Query("ReciverId") String ReciverId
    );

    @GET("api/Wish/GetAchievements")
    Call<List<Achievement>> getAchievements();

    @GET("api/Teacher/GetStudent")
    Call<List<Student>> getstudent();

    @GET("api/Teacher/GetSemesterSectionProgram")
    Call<List<papulationselection>> getSemesterSectionProgram(@Query("teacherId") String teacherId);

    @GET("api/User/getstudentinfo")
    Call<UserDataResponse> getUserInfo(@Query("username") String username);



    @GET("api/Teacher/GetSemesterSectionProgram")
    Call<List<Course>> getSemesterSection(@Query("teacherId") String teacherId);


    @GET("api/Wish/GetUserMessages")
    Call<List<Message>> getMessages();






    @POST("api/Wish/SendWishByPapluation")
    Call<SendWishResponse> sendWishByPapluation(@Body SendWishRequestDto sendWishRequest);

    @POST("api/Wish/CreateWish")
    Call<SendWishResponse> createWish(@Body SendWishRequest sendWishRequest);



    @GET("api/User/getstudentinfo")
    Call<StudentResponse> getUserData(@Query("username") String username);


}
