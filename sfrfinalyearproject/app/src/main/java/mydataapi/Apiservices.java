package mydataapi;

import java.util.List;

import Faculty.BirthdayUser;
import ModeClasees.Achievement;
import ModeClasees.Emoji;
import ModeClasees.Message;
import ModeClasees.Student;
import ModeClasees.UserDataResponse;
import ModeClasees.Wish;
import ModeClasees.cuTeacher;
import ModeClasees.papulationselection;
import ModeClasees.user;
import dashboardclasese.wishingclass;
import facultyClasses.Course;
import facultyClasses.FavritUser;
import facultyClasses.InsertPapolationDataDto;
import facultyClasses.InsertPapolationResponse;
import facultyClasses.Reaction;
import facultyClasses.Sendwish;
import facultyClasses.StudentInfoDto;
import facultyClasses.WishRequest;
import facultyClasses.appPermission;
import facultyClasses.forwordsetting;
import facultyClasses.mWishlist;
import facultyClasses.postpapolation;
import modelclassespost.SendWishRequestDto;
import modelclassespost.SendWishResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import student.Section;
import student.SelectAudeince;
import studentClasses.Group;
import studentClasses.GroupMember;
import studentClasses.GroupsData;
import studentClasses.StudentResponse;
import studentClasses.TeacherData;
import studentClasses.TeacherResponse;

public interface Apiservices {



    // Login API call
    @GET("api/User/Login")
    Call<String> login(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("api/wish/GetBirthdayUsers")
    Call<List<user>>getBirhday(@Query("teacherUsername") String teacherUsername);



    @GET("api/Wish/GetReactionCount")
    Call<Reaction> getReactionCount(@Query("swId") Integer swId);
    @POST("api/Wish/Reaction")
    Call<Void> postReaction(@Body Reaction reaction);


    @GET("api/wish/GetBirthdayUsers")
    Call<List<BirthdayUser>> getBirthdayUsers(@Query("username") String username);
    @GET("api/Emoji/GetDistinctReactions/{sw_id}")
    Call<List<user>>getreaction(@Query("teacherUsername") String teacherUsername);
    ///student api end point
    @GET("api/Student/GetALLTeacher")
    Call<List<TeacherData>> getAllTeachers();
    // Get wishes API call
    @GET("api/Student/GetfavTeacher")
    Call<List<cuTeacher>> getFavTeacher(@Query("username") String username);


    @GET("api/Wish/GetpermittedEmoji")
    Call<List<Emoji>> GetpermittedEmoji();





    // Get all teachers API call
    @GET("api/Student/GetCurrentTeacher")
    Call<List<cuTeacher>> getCurrentTeacher(@Query("username") String username);


    //////common api endpoint

    @GET("api/Wish/GetRelatedWishes")
    Call<List<mWishlist>> GetRelatedWishes(@Query("userId") String userId);





    @GET("api/Teacher/apppermisoin")  // Update this to your actual endpoint
    Call<List<appPermission>> getPermissions(@Query("filterUsername") String filterUsername);


    @POST("api/Teacher/InsertAppPermission")
    Call<Void> insertAppPermission(@Body appPermission appPermission);


    @DELETE("api/Teacher/apppermission/{id}")
    Call<Void> deleteAppPermission(@Path("id") int id);


    @GET("api/Teacher/forwodsetting")  // Update this to your actual endpoint
    Call<List<forwordsetting>> getforwordsetting(@Query("filterUsername") String filterUsername);

    @POST("api/Teacher/Insertforwodsetting")
    Call<Void> insertForwordSetting(@Body forwordsetting newForwordSetting);

    @PUT("api/Teacher/Updateforwodsetting/{id}")
    Call<Void> updateForwardSetting(@Path("id") int id, @Body forwordsetting forwardSetting);

    @DELETE(" api/Teacher/Deleteforwodsetting/{id}")
    Call<Void> Deleteforwodsetting(@Path("id") int id);

    @GET("api/Wish/inboxmessageList")
    Call<List<Wish>> getinboxMessage( @Query("senderID") String senderID);


    @GET("api/Wish/GroupinboxmessageList")
    Call<List<GroupsData>> getGroupinboxMessage(@Query("username") String username);















    @GET("api/Wish/GetWishesForReceiverInbox")
    Call<List<Message>> chatmessage(@Query("receiverId") String receiverId, @Query("senderID") String senderID);


    @POST("api/Teacher/SenBulkWish")
    Call<Void> sendBulkWish(@Body SelectAudeince request);

    @POST("api/Wish/SendSinglewish")
    Call<Void> sendSingleWish(@Body WishRequest wishRequest);
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

    @GET("api/Teacher/GetStudents")
    Call<List<Student>> getstudent();


    // Fetch the favorite status for a specific user
    @GET("api/Teacher/GetFavritUsers")
    Call<List<FavritUser>> getfavorite(@Query("Username") String Username, @Query("Username") String favtuserID);

    // Add a new favorite
    @POST("api/Teacher/AddFavorite")
    Call<Void> addFavorite(@Query ("UserID")String UserID,@Query("favriteID") String favriteID);

    // Delete a favorite
    @DELETE("api/Teacher/DeleteFavorite")
    Call<Void> deleteFavorite(@Query("userID") String userID, @Query("Favuser") String Favuser);
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



    @GET("api/Teacher/GetSections")
    Call<List<SelectAudeince>> getAllocations(@Query("teacherId") String teacherId);


    @GET("api/Teacher/GetStudentsForPapulation")
    Call<List<StudentInfoDto>> GetStudentsForPapulation(
            @Query("semesterNOs") List<String> semesterNOs,
            @Query("sections") List<String> sections,
            @Query("deportments") List<String> deportments
    );


///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////


    @POST("api/Wish/InsertpapolationData")
     Call<Void> insertpublicmessage(@Body InsertPapolationDataDto dto);







    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    @GET("api/Teacher/GetSectionsForSemester")
    Call<List<Section>> getSections(@Query("teacherUsername") String teacherUsername, @Query("semesterid") String semesterid);

    @POST("api/Wish/InsertSendWish")
    Call<InsertPapolationResponse> insertSendWish(@Body Sendwish sendWish);



    @POST("api/Wish/Post")
    Call<Void> sendWish(@Body postpapolation sendWishRequestDto);
    @GET("api/Wish/Getdashboardmessages")
    Call<List<wishingclass>> getpost(
            @Query("currentuser") String currentuser,
            @Query("tcname") String tcname,
            @Query("messagtype") String messagtype);





    @GET("api/Wish/Getbulkwishes")
    Call<List<wishingclass>> getDashboardMessages(
            @Query("username") String username)

    ;

    @GET("api/Wish/GetAllreaction")
    Call<List<Reaction>> getAllReactions(@Query("swId") Integer swId);


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
