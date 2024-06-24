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
import chatClasses.MessageResponse;
import facultyClasses.Course;
import facultyClasses.InsertPapolationDataDto;
import facultyClasses.InsertPapolationResponse;
import facultyClasses.Sendwish;
import facultyClasses.postpapolation;
import modelclassespost.SendWishRequestDto;
import modelclassespost.SendWishResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import student.Program;
import student.Section;
import student.Semester;
import studentClasses.Group;
import studentClasses.GroupMember;
import studentClasses.GroupsData;
import studentClasses.StudentResponse;
import studentClasses.TeacherResponse;

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


    @GET("api/Wish/GetpermittedEmoji")
    Call<List<Emoji>> GetpermittedEmoji();



    @GET("api/Wish/GetStudentDashBoardWishes")
    Call<List<Wish>> getWishes();

    // Get all teachers API call
    @GET("api/Student/GetCurrentTeacher")
    Call<List<cuTeacher>> getCurrentTeacher(@Query("username") String username);


    @GET("api/Wish/messageList")
    Call<List<Wish>> getinboxlist( @Query("messageType") String messageType);

    @GET("api/Wish/inboxmessageList")
    Call<List<Wish>> getinboxMessage( @Query("senderID") String senderID);


    @GET("api/Wish/GroupinboxmessageList")
    Call<List<GroupsData>> getGroupinboxMessage(@Query("username") String username);















    @GET("api/Wish/chatmessage")
    Call<List<MessageResponse>> chatmessage(@Query("senderID") String senderID, @Query("ReciverId") String receiverId);

    @GET("api/wish/GetAllEmojis")
    Call<List<Emoji>> getAllEmojis();

    @POST("api/Wish/CreateWish")
    Call<SendWishResponse> createWish(@Body SendWishRequest request);

    @POST("api/Wish/Reaction")
    Call<ReactionResponse> createReaction(@Body Reaction reaction);












    @GET("api/Wish/Groupchatmessage")
    Call<List<Message>> groupmessage(
            @Query("GroupId") String GroupId

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


    @POST("api/Teacher/CreateGroup")
    Call<Void> createGroup(@Body Group group);

    @POST("api/Teacher/AddGroupMember")
    Call<Void> addGroupMember(@Body GroupMember groupMember);



    @POST("api/Wish/SendWishByPapluation")
    Call<SendWishResponse> sendWishByPapluation(@Body SendWishRequestDto sendWishRequest);




    @GET("api/User/getstudentinfo")
    Call<StudentResponse> getUserData(@Query("username") String username);

    @GET("api/Teacher/GetTeacherInfo")
    Call<TeacherResponse> getTeacherData(@Query("username") String username);



    @GET("api/Teacher/GetPrograms")
    Call<List<Program>> getPrograms(@Query("teacherId") String teacherId);
    @GET("api/Teacher/GetCourse")
    Call<List<Course>> getCourse(@Query("username") String username);
    @GET("api/Teacher/GetSemestersForProgram")
    Call<List<Semester>> GetSemestersForProgram(@Query("teacherUsername") String teacherId, @Query("programid") int programId);

    @GET("api/Teacher/GetSectionsForSemester")
    Call<List<Section>> getSections(@Query("teacherUsername") String teacherUsername, @Query("semesterid") int semesterid);

    @POST("api/Wish/InsertSendWish")
    Call<InsertPapolationResponse> insertSendWish(@Body Sendwish sendWish);



    @POST("api/Wish/Post")
    Call<Void> sendWish(@Body postpapolation sendWishRequestDto);


    @POST("api/Wish/InsertpapolationData")
    Call<InsertPapolationResponse> insertPapolationData(@Body InsertPapolationDataDto dto);
////api for update

    @POST("api/Emoji/UpdateEmojistickerPermission")
    Call<Void> updateEmojiPermission(
            @Query("esid") int esid,
            @Query("permission") String permission,
            @Query("requestedBy") String requestedBy,
            @Query("requestedFor") String requestedFor
    );

}
