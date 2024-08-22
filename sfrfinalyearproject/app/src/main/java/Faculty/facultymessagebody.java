package Faculty;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import ModeClasees.Message;
import facultyClasses.WishRequest;
import modelclassespost.ConversationAdapter;
import modelclassespost.ConversationItem;
import mydataapi.Apiservices;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.EmojiAdapter;
import studentClasses.UserDataSingleton;

public class facultymessagebody extends AppCompatActivity implements OnEmojiClickListener {
    // Declare UI components
    private ImageView messageInputField;
    private Button sendButton;
    private ImageView emojiButton, emojiImageView;
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private Apiservices apiService;

    private List<Emoji> allEmojis;
    private List<Emoji> RistricEmoji;
    private int selectedEmoji;
    private TextView profilename, teachername;
    private ImageView profile, teacherprofileimage;
    private String username, studentusername, FullName, profileImage, teacherUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultymessagebody);
        username = UserDataSingleton.getInstance().getUsername();
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycllerviewmessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize UI components
        emojiImageView = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);
        teachername = findViewById(R.id.teachername);
        teacherprofileimage = findViewById(R.id.techerimage);

        // Extract data from Intent
        Intent intent = getIntent();
        studentusername = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        teacherUsername = intent.getStringExtra("teacher_username");
        String teacherFirstName = intent.getStringExtra("teacher_firstName");
        String teacherLastName = intent.getStringExtra("teacher_lastName");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");
        String forwordName = intent.getStringExtra("forwordedName");

        // Set profile and teacher details
        String Techerfullname = teacherFirstName + " " + teacherLastName;
        teachername.setText(Techerfullname);
        if (teacherProfileImage != null && !teacherProfileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacherProfileImage + ".jpg";
            Picasso.get().load(imageUrl).into(teacherprofileimage);
        } else {
            teacherprofileimage.setImageResource(R.drawable.baseline_account_circle_24);
        }

        profilename.setText(FullName);
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        // Initialize ApiService
        apiService = RetrofitClient.getInstance();

        // Fetch data
        fetchConversation();
        fetchAllEmojis();

        fetchRestrictedEmojis();

        // Set OnClickListener for emoji button
        emojiButton.setOnClickListener(v -> showEmojisPopupWindow(allEmojis));

        // Set OnClickListener for send button
        sendButton.setOnClickListener(v -> {
            insertSingleData(studentusername,teacherUsername,null,selectedEmoji,null,null,false,null,0);
            fetchConversation();
            emojiImageView.setImageDrawable(null);
        });
    }

    private void fetchConversation() {
        Log.d("API Call", "Fetching conversation for user: " + studentusername + " with teacher: " + teacherUsername);
        apiService.chatmessage( username, teacherUsername).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Message> messages = response.body();
                    List<ConversationItem> conversationItems = new ArrayList<>();

                    for (Message message : messages) {
                        String senderProfileImage = message.getSenderProfileImage() != null ? message.getSenderProfileImage() : "";
                        String receiverProfileImage = message.getReceiverProfileImage() != null ? message.getReceiverProfileImage() : "";
                        String emojidata = message.getEmojidata() != null ? message.getEmojidata() : "";
                        String wishDateTime = message.getWishDateTime() != null ? message.getWishDateTime() : "";

                        conversationItems.add(new ConversationItem(
                                message.getSenderUsername() != null ? message.getSenderUsername() : "",
                                message.getSenderFirstName() != null ? message.getSenderFirstName() : "",
                                message.getSenderLastName() != null ? message.getSenderLastName() : "",
                                senderProfileImage,
                                message.getReceiverUsername() != null ? message.getReceiverUsername() : "",
                                message.getReceiverFirstName() != null ? message.getReceiverFirstName() : "",
                                message.getReceiverLastName() != null ? message.getReceiverLastName() : "",
                                receiverProfileImage,
                                emojidata,
                                wishDateTime
                        ));
                    }

                    adapter = new ConversationAdapter(facultymessagebody.this, conversationItems,  username);
                    recyclerView.setAdapter(adapter);

// Initialize the LayoutManager
                    LinearLayoutManager layoutManager = new LinearLayoutManager(facultymessagebody.this);

// Set reverse layout to true if you want to show the latest messages at the bottom
                    layoutManager.setReverseLayout(true);

// Set stack from end to true if you want the RecyclerView to start from the bottom
                    layoutManager.setStackFromEnd(true);

// Apply the LayoutManager to the RecyclerView
                    recyclerView.setLayoutManager(layoutManager);

// Set RecyclerView to have a fixed size (optional, but good for performance if the size is fixed)
                    recyclerView.setHasFixedSize(true);

                    Log.d("API Call", "Conversation items loaded: " + conversationItems.size());
                } else {
                    Log.d("API Call", "Failed to fetch conversation data");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e("API Call", "Failed to fetch conversation data", t);
            }
        });
    }




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
        String requestedBy = studentusername;  // Replace with actual value
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

        Log.d("Emoji Click", "Clicked emoji: " + emoji.getEmojiString() + " with ID: " + selectedEmoji);
    }

    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {
        // Implement if needed
    }

    @Override
    public void onEmojisFetched(List<Emoji> emojis) {
        // Implement if needed
    }

    @Override
    public void onEmojiClick(int emojiId) {
        // Implement if needed
    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        // Implement if needed
    }

    // Inside your Activity, ViewModel, or Repository class

    public void insertSingleData(String senderId, String receiverId, String content, Integer emojiID, Integer templeteID, Integer achievID, boolean isEmail, Integer adid, Integer status) {

        // Create the Retrofit service


        // Create the request object
        WishRequest request = new WishRequest(
                senderId,
                receiverId,
                content,
                emojiID,
                 templeteID,
                achievID,
                 isEmail,
                 adid,
                status);

        // Call the API
        Call<ResponseBody> call = apiService.insertSingleData(request);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Handle the successful response
                    Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the error response
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Toast.makeText(getApplicationContext(), "Failed to insert data: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error parsing error response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle network failure or other errors
                Toast.makeText(getApplicationContext(), "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, facultmessage.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
