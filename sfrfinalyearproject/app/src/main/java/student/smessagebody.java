package student;

import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import modelclassespost.ConversationAdapter;
import mydataapi.Apiservices;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;
import studentClasses.UserRepository;
import studentClasses.studentData;

public class smessagebody extends AppCompatActivity implements OnEmojiClickListener {

    // Declare UI components
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
    TextView profilename, teachername;
    ImageView profile, teacherprofileimage;
    private String username, firstName, lastName, profileImage, teacherUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smessagebody);


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycllerviewmessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize UI components
        emojiImageView = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profilepicture);
        teachername = findViewById(R.id.teachername); // Make sure this matches your layout file
        teacherprofileimage = findViewById(R.id.techerimage); // Make sure this matches your layout file

        // Fetch user data

        // In any other activity where you want to access the username
        username = UserDataSingleton.getInstance().getUsername();

        // Fetch user data from repository
        UserRepository userRepository = new UserRepository();
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
                String displayData = "(" + programName + " " + semesterName + "" + sectionName + ")";
                profilename.setText(displayData);
                if (profileImage != null && !profileImage.isEmpty()) {
                    String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
                    Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profile);
                }
                else {
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


        Intent intent = getIntent();
        teacherUsername = intent.getStringExtra("teacher_username");
        String teacherFirstName = intent.getStringExtra("teacher_firstName");
        String teacherLastName = intent.getStringExtra("teacher_lastName");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");

        String teacherFullName = teacherFirstName + " " + teacherLastName;
        if (teachername != null) {
            teachername.setText(teacherFullName);
        } else {
            Log.e("smessagebody", "teachername TextView is null");
        }

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
        //fetchConversation(username,teacherUsername);
        // Initialize adapter
        adapter = new ConversationAdapter(this, new ArrayList<>(), username,recyclerView);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        // Fetch conversation between sender and receiver
        // fetchConversation(username,teacherUsername);

        // Fetch all emojis from the server
        fetchAllEmojis();
        fetchRestrictedEmojis();


        Log.d("TeacherUsername", "teacherUsername: " + teacherUsername);
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

            //    fetchConversation(username,teacherUsername);
                messageInputField=null;
            }
        });
    }

    // Function to fetch conversation between sender and receiver
   /* private void fetchConversation(String username,String teacherUsername) {
        apiService.chatmessage(username, teacherUsername).enqueue(new Callback<List<MessageResponse>>() {
            @Override
            public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MessageResponse> messageResponses = response.body();
                    List<ConversationItem> conversationItems = new ArrayList<>();

                    // Convert MessageResponse objects to ConversationItem objects
                    for (MessageResponse messageResponse : messageResponses) {
                        conversationItems.add(new ConversationItem(
                                messageResponse.getSenderUsername(),
                                messageResponse.getSenderFirstName(),
                                messageResponse.getSenderLastName(),
                                messageResponse.getSenderProfileImage(),
                                messageResponse.getReceiverUsername(),
                                messageResponse.getReceiverFirstName(),
                                messageResponse.getReceiverLastName(),
                                messageResponse.getReceiverProfileImage(),
                                messageResponse.getEmojidata(),
                                messageResponse.getWishDateTime()
                        ));
                    }

                    // Update adapter with new data
                    adapter.setData(conversationItems);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API chatmessage", "Failed to fetch conversation data. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch conversation data", t);
            }
        });
    }

    */
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

    private void fetchRestrictedEmojis() {
        apiService.GetpermittedEmoji().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RistricEmoji = response.body();
                } else {
                    Log.e("API Error", "Failed to fetch restricted emojis");
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch restricted emojis", t);
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
        ImageView ristrictemoji = popupView.findViewById(R.id.ristrictemoji);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, emojis, this);
        emojisPopupRecyclerView.setAdapter(emojiAdapter);
        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 10, 10);

        ristrictemoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRestrictedEmojiPopupWindow(RistricEmoji);
            }
        });
    }

    // Function to show restricted emojis popup window
// Function to show restricted emojis popup window
    private void showRestrictedEmojiPopupWindow(List<Emoji> ristricEmoji) {
        if (ristricEmoji == null || ristricEmoji.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.restrictedemojipopup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);

        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, ristricEmoji, new OnEmojiClickListener() {
            @Override
            public void onEmojiClick(Emoji emoji) {
                // Pass the clicked emoji to the confirmation dialog
                showConfirmationDialog(emoji);
                Log.e("clicked emoji.....","clickedEMoji......"+emoji.getEmojiID());
            }

            @Override
            public void onEmojiFetched(List<Emoji> section3Emojis) {}

            @Override
            public void onEmojisFetched(List<Emoji> emojis) {}

            @Override
            public void onEmojiClickWithId(int emojiId) {

            }

            @Override
            public void onEmojiClickForWish(int wishId, int emojiId) {

            }

            @Override
            public void onEmojiClick(int emojiId) {

            }

            @Override
            public void onEmojiClick(int wishId, int emojiId) {

            }
        });
        emojisPopupRecyclerView.setAdapter(emojiAdapter);
        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
    }


    // Function to show confirmation dialog for restricted emoji
    private void showConfirmationDialog(Emoji emoji) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restricted Emoji");
        builder.setMessage("This emoji is restricted. Do you want to proceed?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            updateEmojiPermission(emoji);
            builder.setMessage("request succesfully send to admin ");

            // Make sure emoji is correct here
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // Function to update emoji permission in the database
    private void updateEmojiPermission(Emoji emoji) {
        int esid = selectedEmoji; // Ensure this returns the correct ID
        String permission = "R";
        String requestedBy = username;  // Replace with actual value
        String requestedFor = teacherUsername;  // Replace with actual value

        Log.d("API Call", "Updating emoji with ID: " + esid + ", permission: " + permission +
                ", requestedBy: " + requestedBy + ", requestedFor: " + requestedFor);

        apiService.updateEmojiPermission(esid, permission, requestedBy, requestedFor).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("Update Emoji", "Emoji permission updated successfully");
                    // Handle success (e.g., notify the user, update UI, etc.)
                } else {
                    Log.e("API Error", "Failed to update emoji permission. Response code: " + response.code());
                    // Handle the failure (e.g., show error message)
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Network Error", "Failed to update emoji permission", t);
                // Handle network error (e.g., show error message)
            }
        });
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        selectedEmoji = emoji.getEmojiID();
        String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
        Picasso.get().load(imageUrl).into(emojiImageView);
        Log.d("click emoji", "click emoji: " + emoji.getEmojiString());
        Log.d("Image URL", "Image URL: " + imageUrl);
        Log.d("Image ID", "Image ID: " + selectedEmoji);
    }

    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {}

    @Override
    public void onEmojisFetched(List<Emoji> emojis) {}

    @Override
    public void onEmojiClickWithId(int emojiId) {

    }

    @Override
    public void onEmojiClickForWish(int wishId, int emojiId) {

    }

    @Override
    public void onEmojiClick(int emojiId) {

    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {

    }

    // Function to send message


    @Override
    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, smassage.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }
}
