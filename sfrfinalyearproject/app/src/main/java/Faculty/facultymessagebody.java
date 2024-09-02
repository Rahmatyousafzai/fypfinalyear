package Faculty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import ModeClasees.Message;
import facultyClasses.postpapolation;
import modelclassespost.ConversationAdapter;
import modelclassespost.ConversationItem;
import modelclassespost.EmojiAdapter;
import modelclassespost.RestrictedEmojiAdapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.UserDataSingleton;

public class facultymessagebody extends AppCompatActivity implements RestrictedEmojiAdapter.OnItemClickListener {

    private static final String TAG = "Facultymessagebody";
    private ImageView messageInputField, sendButton, emojiButton;
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private Apiservices apiService;
    private List<Emoji> allEmojis = new ArrayList<>();
    private List<Emoji> restrictedEmojis = new ArrayList<>();
    private List<ConversationItem> conversationItems;
    private int selectedEmojiIdFromAllEmojis = -1;
    private int selectedEmojiIdFromRestrictedEmojis = -1;
    private TextView profilename, teachername;
    private ImageView profile, teacherprofileimage;
    private String username, studentusername, FullName, profileImage, teacherUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultymessagebody);

        initializeUI();
        extractIntentData();
        initializeApiService();
        fetchInitialData();
        setClickListeners();
    }

    private void initializeUI() {
        recyclerView = findViewById(R.id.recycllerviewmessage);
        messageInputField = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);
        teachername = findViewById(R.id.teachername);
        teacherprofileimage = findViewById(R.id.teacherImage);

        username = UserDataSingleton.getInstance().getUsername();
        conversationItems = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ConversationAdapter(this, conversationItems, username, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void extractIntentData() {
        Intent intent = getIntent();
        studentusername = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        teacherUsername = intent.getStringExtra("teacher_username");
        String teacherFirstName = intent.getStringExtra("teacher_firstName");
        String teacherLastName = intent.getStringExtra("teacher_lastName");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");

        String teacherFullName = teacherFirstName + " " + teacherLastName;
        teachername.setText(teacherFullName);
        String teacherImageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacherProfileImage + ".jpg";
        Picasso.get().load(teacherImageUrl).into(teacherprofileimage);
        profilename.setText(FullName);
    }

    private void initializeApiService() {
        apiService = RetrofitClient.getInstance();
    }

    private void fetchInitialData() {
        fetchConversation();
        fetchAllEmojis();
        fetchRestrictedEmojis();
    }

    private void setClickListeners() {
        emojiButton.setOnClickListener(v -> {
            if (!allEmojis.isEmpty()) {
                showEmojisPopupWindow(allEmojis);
            } else {
                Toast.makeText(this, "Emojis not loaded yet", Toast.LENGTH_SHORT).show();
            }
        });

        sendButton.setOnClickListener(v -> {
            if (selectedEmojiIdFromAllEmojis != -1) {
                sendWish(username, teacherUsername, selectedEmojiIdFromAllEmojis);
                selectedEmojiIdFromAllEmojis = -1;
                fetchConversation();
            } else {
                Toast.makeText(this, "No emoji selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchConversation() {
        Log.d(TAG, "Fetching conversation for user: " + studentusername + " with teacher: " + teacherUsername);
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
                                message.getWishDateTime()
                        ));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(facultymessagebody.this, "Failed to fetch messages", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e(TAG, "Error fetching messages", t);
                Toast.makeText(facultymessagebody.this, "Error fetching messages", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAllEmojis() {
        apiService.getAllEmojis().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmojis.clear();
                    allEmojis.addAll(response.body());
                } else {
                    Toast.makeText(facultymessagebody.this, "Failed to fetch emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Toast.makeText(facultymessagebody.this, "Error fetching emojis", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchRestrictedEmojis() {
        apiService.getEmojiRequests().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    restrictedEmojis.clear();
                    restrictedEmojis.addAll(response.body());
                } else {
                    Toast.makeText(facultymessagebody.this, "Failed to fetch restricted emojis", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Toast.makeText(facultymessagebody.this, "Error fetching restricted emojis", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendWish(String sender, String receiver, int emojiId) {
        postpapolation post = new postpapolation(sender, receiver, emojiId);
        apiService.sendWish(post).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Wish sent successfully");
                } else {
                    Log.e(TAG, "Failed to send wish");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error sending wish", t);
            }
        });
    }

    private void showEmojisPopupWindow(List<Emoji> emojis) {
        if (emojis == null || emojis.isEmpty()) {
            Toast.makeText(this, "No emojis available", Toast.LENGTH_SHORT).show();
            return;
        }

        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        EmojiAdapter emojiAdapter = new EmojiAdapter(this, emojis, emoji -> {
            selectedEmojiIdFromAllEmojis = emoji.getEmojiID();
            String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
            Picasso.get().load(imageUrl).into(messageInputField);
        });
        emojisPopupRecyclerView.setAdapter(emojiAdapter);

        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(findViewById(R.id.send_button), Gravity.BOTTOM, 0, 0);
    }

    private void showRestrictedEmojiPopup() {
        if (restrictedEmojis == null || restrictedEmojis.isEmpty()) {
            Toast.makeText(this, "No restricted emojis available", Toast.LENGTH_SHORT).show();
            return;
        }

        View popupView = LayoutInflater.from(this).inflate(R.layout.restrictedemojipopup_window_layout, null);
        RecyclerView restrictedEmojiRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview12);
        restrictedEmojiRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RestrictedEmojiAdapter restrictedEmojiAdapter = new RestrictedEmojiAdapter(this, restrictedEmojis, emoji -> {
            selectedEmojiIdFromRestrictedEmojis = emoji.getEmojiID();
            showConfirmationDialog();
        });
        restrictedEmojiRecyclerView.setAdapter(restrictedEmojiAdapter);

        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(findViewById(R.id.send_button));
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Emoji")
                .setMessage("Do you want to send this emoji?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    sendWish(username, teacherUsername, selectedEmojiIdFromRestrictedEmojis);
                    selectedEmojiIdFromRestrictedEmojis = -1;
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onItemClick(Emoji emoji) {
        selectedEmojiIdFromRestrictedEmojis = emoji.getEmojiID();
        showConfirmationDialog();
    }
}
