package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Emoji;
import modelclassespost.ConversationAdapter;
import modelclassespost.ConversationItem;
import modelclassespost.SendWishResponse;
import mydataapi.Apiservices;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;
import mydataapi.SendWishRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.EmojiAdapter;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class groupmessage_body extends AppCompatActivity  implements OnEmojiClickListener {
ImageView userimage;
TextView userusername;



    ImageView messageInputField;
    Button sendButton;
    ImageView emojiButton, emojiImageView;

    // RecyclerView for displaying messages
    private RecyclerView recyclerView;
    // Adapter for managing conversation items
    private ConversationAdapter adapter;
    // ApiService for making network calls
    private Apiservices apiService;

    // Mock user sender ID and receiver ID
    TextView txtNews, txtMessage, txtNotification;

    // List to store all emojis
    private List<Emoji> allEmojis;
    private List<Emoji> RistricEmoji;
    // Selected emoji
    private int selectedEmoji;
    TextView profilename, GroupnameUI;
    ImageView profile, teacherprofileimage;
    private String username,GroupID,Groupname,Groupdescription,firstName,lastName,profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupmessage_body);


        Intent intent = getIntent();

// Initialize RecyclerView
        recyclerView = findViewById(R.id.recycllerviewmessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize UI components
        emojiImageView = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        GroupnameUI = findViewById(R.id.teachername); // Make sure this matches your layout file
        teacherprofileimage = findViewById(R.id.techerimage); // Make sure this matches your layout file

        username = UserDataSingleton.getInstance().getUsername();
        UserRepository userRepository = new UserRepository();
        // Initialize RecyclerView
        // In any other activity where you want to access the username
        userRepository.fetchUserData(username, new UserRepository.UserRepositoryCallback() {
            @Override
            public void onSuccess(studentData data) {
                // Access user data fields
                String programName = data.getProgramName();
                int semesterName = data.getSemesterName();
                String sectionName = data.getSectionName();

                profileImage = data.getProfileImage();
                firstName = data.getFirstName();
                lastName = data.getLastName();

                // Log user data
                Log.e("UserData.......", "Program Name...........: " + programName);
                Log.e("UserData......", "Semester Name..........: " + semesterName);
                Log.e("UserData.........", "Section Name:........... " + sectionName);

                // Optionally, update UI with user data
                TextView sectionandsamester = findViewById(R.id.sectionandsamester);
                String displayData = "(" + programName + " " + semesterName + "" + sectionName + ")";
                sectionandsamester.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                } else {
                    profile.setImageResource(R.drawable.baseline_account_circle_24);
                }

                String fullName = firstName + " " + lastName;
                profilename.setText(fullName);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("SomeActivity", "Error fetching user data: " + e.getMessage());
                // Handle error case, e.g., show a toast or an error message
            }
        });



        GroupID = intent.getStringExtra("group_id");
        Groupname = intent.getStringExtra("group_name");
         Groupdescription = intent.getStringExtra("group_Dis");
        String teacherProfileImage = intent.getStringExtra("group_image");

        GroupnameUI.setText(Groupname);







        if (teacherProfileImage != null && !teacherProfileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacherProfileImage + ".jpg";
            if (teacherprofileimage != null) {
                Picasso.get().load(imageUrl).into(teacherprofileimage);
            } else {
                Log.e("smessagebody", "teacherprofileimage ImageView is null");
            }
        } else {
            if (teacherprofileimage != null) {
                teacherprofileimage.setImageResource(R.drawable.baseline_account_circle_24);
            } else {
                Log.e("smessagebody", "teacherprofileimage ImageView is null");
            }
        }






        // Initialize ApiService
        apiService = RetrofitClient.getInstance();

        // Fetch conversation between sender and receiver


        // Fetch all emojis from the server
        fetchAllEmojis();
        fetcRistrictEmojis();

        Log.d("GroupId", "Groupid: " + GroupID);
        // Set OnClickListener for emoji button to show emoji popup window
        emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmojisPopupWindow(allEmojis);
            }
        });

        // Set OnClickListener for send button to send message
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();

            }
        });
    }

    // Function to fetch conversation between sender and receiver


    // Function to fetch all emojis from the server
    private void fetchAllEmojis() {
        apiService.getAllEmojis().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmojis = response.body();
                } else {
                    Log.e("API Error", "Failed to fetch all emojis");
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch all emojis", t);
            }
        });
    }


    private void fetcRistrictEmojis() {
        apiService.GetpermittedEmoji().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmojis = response.body();
                } else {
                    Log.e("API Error", "Failed to fetch all emojis");
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch all emojis", t);
            }
        });
    }

    // Function to show emoji popup window
    private void showEmojisPopupWindow(List<Emoji> emojis) {
        if (emojis == null || emojis.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        ImageView ristrictemoji=popupView.findViewById(R.id.ristrictemoji);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, emojis,this);
        emojisPopupRecyclerView.setAdapter(emojiAdapter);
        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 10, 10);

        ristrictemoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ristrcitemoji(allEmojis);
            }
        });



    }


    //ristricted emoji admin will approve access
    private void Ristrcitemoji(List<Emoji> ristricEmoji) {
        if (ristricEmoji== null || ristricEmoji.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.restrictedemojipopup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);

        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this,ristricEmoji, this);
        emojisPopupRecyclerView.setAdapter(emojiAdapter);
        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);





    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        // Store the selected emoji ID
        selectedEmoji = emoji.getEmojiID();

        // Set the clicked emoji image in the ImageView
        String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
        Picasso.get().load(imageUrl).into(emojiImageView);

        Log.d("click emoji", "click emoji: " + emoji.getEmojiString());
        Log.d("Image URL", "Image URL: " + imageUrl);
        Log.d("Image URL", "Image ID: " + selectedEmoji);
    }

    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {

    }


    @Override
    public void onEmojisFetched(List<Emoji> emojis) {
    }

    // Function to send message
    private void sendMessage() {
        if (selectedEmoji == 0) {
            Log.e("Validation Error", "Emoji and message text are required");
            return;
        }

        SendWishRequest request = new SendWishRequest("2020-arid-3535", "BIIT0001", selectedEmoji);

        // Insert data into the database
        apiService.createWish(request).enqueue(new Callback<SendWishResponse>() {
            @Override
            public void onResponse(Call<SendWishResponse> call, Response<SendWishResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Send Message", "Message sent successfully");
                    // Create a new ConversationItem representing the sent message
                    ConversationItem sentMessage = new ConversationItem(username, GroupID, selectedEmoji);

                    // Add the sent message to the adapter
                    adapter.addItem(sentMessage);

                    // Scroll RecyclerView to the bottom
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);

                    // Clear the emojiImageView
                    emojiImageView.setImageDrawable(null);

                    // Reset the selected emoji ID
                    selectedEmoji = 0;

                    // Log to verify dataset update
                    Log.d("RecyclerView Update", "New item added to dataset: " + sentMessage);
                } else {
                    Log.e("API Error", "Failed to send message");
                }
            }

            @Override
            public void onFailure(Call<SendWishResponse> call, Throwable t) {
                Log.e("Network Error", "Failed to send message", t);
            }
        });
    }
}
