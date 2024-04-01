package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;

import adopter.Wish;
import adopter.Wishadapter;
import adopter.emoji_class;

public class ad_dashboard extends AppCompatActivity {

    // Declare views
    private TextView adminPost, addTeacher, adNews, adMessage, addAchievement, adStudent, profileName,notification;
    private ImageView profileImage, popup;
    private RecyclerView recyclerView;
    private Wishadapter wishAdapter;
    private ArrayList<Wish> wishes;


    // Constants for view IDs
    private static final int ADMIN_POST_ID = R.id.adminpost;
    private static final int ADMIN_NOTIFICATION_ID = R.id.adtxtnotification;
    private static final int ADD_TEACHER_ID = R.id.textadteacher;
    private static final int AD_NEWS_ID = R.id.adnews;
    private static final int AD_MESSAGE_ID = R.id.adtxtmessage;
    private static final int ADD_ACHIEVEMENT_ID = R.id.adtextachvment;
    private static final int AD_STUDENT_ID = R.id.adstudent;
    private static final int PROFILE_NAME_ID = R.id.profelname;
    private static final int RECYCLER_VIEW_ID = R.id.adrcview;
    private static final int PROFILE_IMAGE_ID = R.id.profileimage;
    private static final int POPUP_ID = R.id.popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_dashboard);


        // Initialize views


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
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishes = new ArrayList<>();
        // Add wishes
        wishes.add(new Wish("Rahmat", R.drawable.rahmatpic, R.drawable.baseline_more_vert_24, "happy Birthday", new ArrayList<emoji_class>()));
        wishes.add(new Wish("DR.Saeed", R.drawable.saeed, R.drawable.baseline_more_vert_24, "happy Birthday happy Birthday happy Birthday happy Birthday happy Birthday happy Birthday happy Birthday happy Birthday", new ArrayList<emoji_class>()));
        wishAdapter = new Wishadapter(this, wishes);
        recyclerView.setAdapter(wishAdapter);
    }

    private void setClickListeners() {
        adminPost.setOnClickListener(v -> adminpost());
        notification.setOnClickListener(v -> notification());
        adMessage.setOnClickListener(v -> adMessage());

        adNews.setOnClickListener(v -> news());

        addTeacher.setOnClickListener(v -> addTeacher());

        adStudent.setOnClickListener(v -> addStudent());

        addAchievement.setOnClickListener(v -> viewAchievement());

        popup.setOnClickListener(this::showPopupMenu);
    }

    private void adminpost() {
        Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
        startActivity(intent);
        finish();
    }

    private void Sharepost(){



    Intent intent = new Intent(ad_dashboard.this, Admin.postshareoption.class);
    startActivity(intent);
    finish();
}
    private void viewAchievement() {
        Intent intent = new Intent(ad_dashboard.this, ad_addachivment.class);
        startActivity(intent);
        finish();
    }
    public void notification() {
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_notification.class);
        startActivity(intent);
        finish();
    }
    public void adMessage() {
        Intent intent = new Intent(ad_dashboard.this, Admin.admessage.class);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(ad_dashboard.this, ad_dashboard.class);
        startActivity(intent);
        finish();
    }
    public void addTeacher() {
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_teachers.class);
        intent.putExtra("prfilename", profileName.getText().toString());
        startActivity(intent);
        finish();
    }
    public void addStudent() {
        Intent intent = new Intent(ad_dashboard.this, Admin.ad_student.class);
        startActivity(intent);
        finish();
    }
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(ad_dashboard.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            // Handle menu item click

            return false;
        });
        popupMenu.show();
    }
}
