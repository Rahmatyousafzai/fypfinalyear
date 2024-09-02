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

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import ModeClasees.Message;
import chatClasses.PollingManager;
import facultyClasses.WishRequest;
import modelclassespost.ConversationAdapter;
import modelclassespost.ConversationItem;
import mydataapi.Apiservices;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import student.EmojiAdapter;

public class facultymessagebody extends AppCompatActivity implements OnEmojiClickListener {
    private ImageView messageInputField;
    private Button sendButton;
    private ImageView emojiButton, emojiImageView;
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private Apiservices apiService;

    private List<Emoji> allEmojis;
    private List<Emoji> restrictedEmojis;
    private List<ConversationItem> conversationItems;
    private int selectedEmoji = -1;
    private PollingManager pollingManager;
    private TextView profilename, teachername;
    private ImageView profile, teacherprofileimage;
    private String username, studentusername, FullName, profileImage, teacherUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultymessagebody);

        // Initialize UI components
        recyclerView = findViewById(R.id.recycllerviewmessage);
        messageInputField = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        emojiImageView = findViewById(R.id.message_input_field);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);
        teachername = findViewById(R.id.teachername);
        teacherprofileimage = findViewById(R.id.teacherImage);

        // Initialize lists
        conversationItems = new ArrayList<>();

        // Initialize RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ConversationAdapter(this, conversationItems, username, recyclerView);
        recyclerView.setAdapter(adapter);

        // Extract data from Intent
        Intent intent = getIntent();
        studentusername = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        teacherUsername = intent.getStringExtra("teacher_username");
        String teacherFirstName = intent.getStringExtra("teacher_firstName");
        String teacherLastName = intent.getStringExtra("teacher_lastName");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");

        // Set profile and teacher details
        String teacherFullName = teacherFirstName + " " + teacherLastName;
        teachername.setText(teacherFullName);
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
            if (selectedEmoji != -1) {
                sendWish(username, teacherUsername, selectedEmoji);
                emojiImageView.setImageDrawable(null);
                fetchConversation();
            } else {
                Toast.makeText(this, "Please select an emoji", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchConversation() {
        Log.d("API Call", "Fetching conversation for user: " + studentusername + " with teacher: " + teacherUsername);
        apiService.chatmessage(username, teacherUsername).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Message> messages = response.body();
                    conversationItems.clear();
                    for (Message message : messages) {
                        conversationItems.add(new ConversationItem(
                                message.getSenderUsername(),
                                message.getSenderFirstName(),
                                message.getSenderLastName(),
                                message.getSenderProfileImage(),
                                message.getReceiverUsername(),
                                message.getReceiverFirstName(),
                                message.getReceiverLastName(),
                                message.getReceiverProfileImage(),
                                message.getWishcontent(),
                                message.getEmojidata(),
                                message.getWishDateTime()
                        ));
                    }
                    runOnUiThread(() -> adapter.notifyDataSetChanged());
                    Log.d("API Call", "Conversation items loaded: " + conversationItems.size());
                } else {
                    Log.e("API Call", "Failed to fetch conversation data: " + response.message());
                    Toast.makeText(facultymessagebody.this, "Failed to fetch messages", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e("API Call", "Failed to fetch conversation data", t);
                Toast.makeText(facultymessagebody.this, "Error occurred", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(facultymessagebody.this, "Error fetching emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch all emojis", t);
                Toast.makeText(facultymessagebody.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchRestrictedEmojis() {
        apiService.GetpermittedEmoji().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restrictedEmojis = response.body();
                } else {
                    Log.e("API Error", "Failed to fetch restricted emojis");
                    Toast.makeText(facultymessagebody.this, "Error fetching restricted emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch restricted emojis", t);
                Toast.makeText(facultymessagebody.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showEmojisPopupWindow(List<Emoji> emojis) {
        if (emojis == null || emojis.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        ImageView restrictedEmoji = popupView.findViewById(R.id.ristrictemoji);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, emojis, this);
        emojisPopupRecyclerView.setAdapter(emojiAdapter);
        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 10, 10);

        restrictedEmoji.setOnClickListener(v -> showRestrictedEmojiPopupWindow(restrictedEmojis));
    }

    private void showRestrictedEmojiPopupWindow(List<Emoji> restrictedEmojis) {
        if (restrictedEmojis == null || restrictedEmojis.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.restrictedemojipopup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, restrictedEmojis, this);
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
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateEmojiPermission(Emoji emoji) {
        int emojiId = emoji.getEmojiID();
        String permission = "R";
        String requestedBy = studentusername;
        String requestedFor = teacherUsername;

        Log.d("API Call", "Updating emoji with ID: " + emojiId + ", permission: " + permission +
                ", requestedBy: " + requestedBy + ", requestedFor: " + requestedFor);

        apiService.updateEmojiPermission(emojiId, permission, requestedBy, requestedFor).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("Update Emoji", "Emoji permission updated successfully");
                    Toast.makeText(facultymessagebody.this, "Permission request sent to admin", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("API Error", "Failed to update emoji permission. Response code: " + response.code());
                    Toast.makeText(facultymessagebody.this, "Failed to update permission", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Network Error", "Failed to update emoji permission", t);
                Toast.makeText(facultymessagebody.this, "Error updating permission", Toast.LENGTH_SHORT).show();
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
    public void onEmojiClickWithId(int emojiId) {
        // Implement if needed
    }

    @Override
    public void onEmojiClickForWish(int wishId, int emojiId) {
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

    private void sendWish(String senderId, String ReciverID, int emojiID) {
        WishRequest wishRequest = new WishRequest(senderId, ReciverID, emojiID);
        apiService.sendSingleWish(wishRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(facultymessagebody.this, "Wish sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(facultymessagebody.this, "Failed to send wish", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API Error", t.getMessage());
                Toast.makeText(facultymessagebody.this, "Error occurred", Toast.LENGTH_SHORT).show();
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
