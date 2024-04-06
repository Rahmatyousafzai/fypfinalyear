package Admin;

import static com.example.sfrfinalyearproject.R.drawable;
import static com.example.sfrfinalyearproject.R.id;
import static com.example.sfrfinalyearproject.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;

import adopter.Wish;
import adopter.Wishadapter;
import adopter.emoji_class;

public class ad_dashboard extends AppCompatActivity {

    // Declare views
    private TextView adminPost, addTeacher, adNews, adMessage, addAchievement, adStudent, profileName, notification;
    private ImageView profileImage, popup;
    private RecyclerView recyclerView;
    private Wishadapter wishAdapter;
    private ArrayList<Wish> wishes;
    private ArrayList<Integer> imageList;


    // Constants for view IDs
    private static final int ADMIN_POST_ID = id.adminpost;
    private static final int ADMIN_NOTIFICATION_ID = id.adtxtnotification;
    private static final int ADD_TEACHER_ID = id.textadteacher;
    private static final int AD_NEWS_ID = id.adnews;
    private static final int AD_MESSAGE_ID = id.adtxtmessage;
    private static final int ADD_ACHIEVEMENT_ID = id.adtextachvment;
    private static final int AD_STUDENT_ID = id.adstudent;
    private static final int PROFILE_NAME_ID = id.profelname;
    private static final int RECYCLER_VIEW_ID = id.adrcview;
    private static final int PROFILE_IMAGE_ID = id.profileimage;
    private static final int POPUP_ID = id.popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_ad_dashboard);


        // Initialize views


        // Initialize image list
        initImageList();

        initViews();


        // Set up RecyclerView
        setUpRecyclerView();

        // Set click listeners for views
        setClickListeners();
    }

    private void initViews() {
        adminPost = findViewById(ADMIN_POST_ID);
        notification = findViewById(ADMIN_NOTIFICATION_ID);
        addTeacher = findViewById(ADD_TEACHER_ID);
        adNews = findViewById(AD_NEWS_ID);
        adMessage = findViewById(AD_MESSAGE_ID);
        addAchievement = findViewById(ADD_ACHIEVEMENT_ID);
        adStudent = findViewById(AD_STUDENT_ID);
        profileName = findViewById(PROFILE_NAME_ID);
        recyclerView = findViewById(RECYCLER_VIEW_ID);
        profileImage = findViewById(PROFILE_IMAGE_ID);
        popup = findViewById(POPUP_ID);
        profileImage.setImageResource(drawable.baseline_account_circle_24);
        profileName.setText("Admin");


    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishes = new ArrayList<>();
        String name="Ms Umer";
        // Add wishes
        wishes.add(new Wish("Rahmat", drawable.rahmatpic, drawable.baseline_more_vert_24, "happy Birthday",drawable.add_button ,new ArrayList<emoji_class>()));
        wishes.add(new Wish("DR.Saeed", drawable.saeed, drawable.baseline_more_vert_24, "congratulation to" + ""+name+" " + "for being selected best techer of the year",drawable.add_button , new ArrayList<emoji_class>()));
        wishes.add(new Wish("Rahmat", drawable.rahmatpic, drawable.baseline_more_vert_24, "happy Birthday",drawable.add_button ,new ArrayList<emoji_class>()));
        wishes.add(new Wish("DR.Saeed", drawable.saeed, drawable.baseline_more_vert_24, "congratulation to" +name+ "for being selected best techer of the year",drawable.add_button , new ArrayList<emoji_class>()));
        wishes.add(new Wish("Rahmat", drawable.rahmatpic, drawable.baseline_more_vert_24, "happy Birthday",drawable.add_button ,new ArrayList<emoji_class>()));
        wishes.add(new Wish("DR.Saeed", drawable.saeed, drawable.baseline_more_vert_24, "congratulation to" +name+ "for being selected best techer of the year",drawable.add_button , new ArrayList<emoji_class>()));


        wishAdapter = new Wishadapter(this, wishes,imageList);
        recyclerView.setAdapter(wishAdapter);
    }
    private void initImageList() {
        imageList = new ArrayList<>();
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        imageList.add(drawable.heart);
        imageList.add(drawable.img_2);
        // Add more images as needed
    }

    private void setClickListeners() {
        adminPost.setOnClickListener(v -> adminpost());
        notification.setOnClickListener(v -> notification());
        adMessage.setOnClickListener(v -> adMessage());

        adNews.setOnClickListener(v -> news());

        addTeacher.setOnClickListener(v -> addTeacher());

        adStudent.setOnClickListener(v -> addStudent());

        addAchievement.setOnClickListener(v -> viewAchievement());









    }

 





    private void adminpost() {
        Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
    private void Sharepost(){



    Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
    startActivity(intent);
    finish();
}
    private void viewAchievement() {
        Intent intent = new Intent(ad_dashboard.this, ad_addachivment.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);

        startActivity(intent);
        finish();
    }
    public void notification() {
        // Assuming PROFILE_NAME_ID is a string containing the name and profileImage is an integer representing the drawable resource ID
        Intent intent = new Intent(ad_dashboard.this, ad_notification.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24); // Replace with the appropriate drawable resource ID
        startActivity(intent);
        finish();
    }


    public void adMessage() {
        Intent intent = new Intent(ad_dashboard.this, Admin.admessage.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
    public void news() {
        Intent intent = new Intent(ad_dashboard.this, ad_dashboard.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
    public void addTeacher() {
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_teachers.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
    public void addStudent() {
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_student.class);
        intent.putExtra("username", profileName.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }


}
