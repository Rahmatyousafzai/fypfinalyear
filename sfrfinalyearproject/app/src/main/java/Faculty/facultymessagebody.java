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

public class facultymessagebody extends AppCompatActivity implements OnEmojiClickListener {
    // Declare UI components
    ImageView messageInputField;
    Button sendButton;
    ImageView emojiButton,emojiImageView;

    // RecyclerView for displaying messages
    private RecyclerView recyclerView;
    // Adapter for managing conversation items
    private ConversationAdapter adapter;
    // ApiService for making network calls
    private Apiservices apiService;

    // Mock user sender ID and receiver ID


    // List to store all emojis
    private List<Emoji> allEmojis;
    // Selected emoji
    private int selectedEmoji;
    TextView profilename,teachername;
    ImageView profile,teacherprofileimage;
    private String studentusername, FullName, profileImage,teacherUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultymessagebody);
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycllerviewmessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize UI components
        emojiImageView = findViewById(R.id.message_input_field);
        sendButton = findViewById(R.id.send_button);
        emojiButton = findViewById(R.id.emojibutton);
        profilename = findViewById(R.id.profelname);
        profile = findViewById(R.id.profileimage);
        teachername=findViewById(R.id.teachername);
        teacherprofileimage=findViewById(R.id.techerimage);

        Intent intent = getIntent();
        studentusername = intent.getStringExtra("username");
        FullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        Log.d("stteacher", "Profile Name: " + FullName);

        String username = intent.getStringExtra("username");
        String fullName = intent.getStringExtra("FullName");
        profileImage = intent.getStringExtra("profileimage");
        teacherUsername = intent.getStringExtra("teacher_username");
        String teacherFirstName = intent.getStringExtra("teacher_firstName");
        String teacherLastName = intent.getStringExtra("teacher_lastName");
        String teacherProfileImage = intent.getStringExtra("teacher_profileImage");

        String Techerfullname=teacherFirstName+" "+teacherLastName;
        teachername.setText(Techerfullname);

        if (teacherProfileImage != null && !teacherProfileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + teacherProfileImage + ".jpg";
            Picasso.get().load(imageUrl).into(teacherprofileimage);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }










        profilename.setText(FullName);

        // Load profile image using Picasso
        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).into(profile);
        } else {
            profile.setImageResource(R.drawable.baseline_account_circle_24);
        }




















        // Initialize ApiService
        apiService = RetrofitClient.getInstance();

        // Fetch conversation between sender and receiver

        // Fetch all emojis from the server
        fetchAllEmojis();

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
               // fetchConversation();
                messageInputField.setImageDrawable(null);



            }
        });

        // Fetch conversation between sender and receiver
       // fetchConversation();
    }

    // Function to fetch conversation between sender and receiver
   /* private void fetchConversation() {
        apiService.chatmessage(studentusername, teacherUsername).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Message> messages = response.body();
                    List<ConversationItem> conversationItems = new ArrayList<>();

                    // Convert Message objects to ConversationItem objects
                    for (Message message : messages) {
                        conversationItems.add(new ConversationItem(message.getSenderUsername(),
                                message.getReceiverUsername(),message.getReceiverProfileImage(),message.getSenderProfileImage(), message.getEmojiData()));
                        Log.d("Response Index", "Message: " + message);
                    }

                    // Create and set adapter to RecyclerView
                    String currentUserUsername = studentusername;
                    adapter = new ConversationAdapter(facultymessagebody.this, conversationItems, currentUserUsername);
                    recyclerView.setAdapter(adapter);
                    recyclerView.hasFixedSize();
                    recyclerView.setLayoutManager(new LinearLayoutManager(facultymessagebody.this));
                    Log.d("Adapter Data", "Conversation Items: " + conversationItems);

                } else {
                    // Handle unsuccessful response
                    Log.d("API chatmessage", "Failed to fetch conversation data");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                // Handle API call failure
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

    // Function to show emoji popup window
    private void showEmojisPopupWindow(List<Emoji> emojis) {
        if (emojis == null || emojis.isEmpty()) {
            return;
        }
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        EmojiAdapter emojiAdapter = new EmojiAdapter(this, emojis, this);
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

        Log.d("click emijo", "click emoji: " + emoji.getEmojiString());
        Log.d("Image URL", "Image URL: " + imageUrl);

        Log.d("Image URL", "Image ID: " + selectedEmoji);

    }
    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {

    }

    @Override
    public void onEmojisFetched(List<Emoji> emojis) {

    }

    @Override
    public void onEmojiClick(int emojiId) {

    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {

    }

    // Function to send message
// Function to send messageFunction to send message
    private void sendMessage() {
        if (selectedEmoji == 0) {
            Log.e("Validation Error", "Emoji and message text are required");
            return;
        }

        SendWishRequest request = new SendWishRequest("2020-arid-3535", "BIIT0001", selectedEmoji);

        //    // Insert data into the database
        apiService.createWish(request).enqueue(new Callback<SendWishResponse>() {
            @Override
            public void onResponse(Call<SendWishResponse> call, Response<SendWishResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Send Message", "Message sent successfully");
                    // Create a new ConversationItem representing the sent message
                    ConversationItem sentMessage = new ConversationItem(studentusername, teacherUsername, selectedEmoji);
                    //
                    //                // Add the sent message to the d
                    adapter.addItem(sentMessage);
                    //
                    //                // Notify the adapter that the dataset has changed
                    adapter.notifyDataSetChanged();

                    // Scroll RecyclerView to the bottom
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    //
                    //                // Clear the emojiImageView
                    emojiImageView.setImageDrawable(null);
                    //
                    //                // Reset the selected emoji ID
                    selectedEmoji = 0;
                    //
                    //                // Log to verify dataset update
                    Log.d("RecyclerView Update", "New item added to dataset: " + sentMessage);
                } else {
                    Log.e("API Error", "Failed to send message");
                }
            }
            //
            @Override
            public void onFailure(Call<SendWishResponse> call, Throwable t) {
                Log.e("Network Error", "Failed to send message", t);
            }
        });
    }


    public void onBackPressed() {
        // Navigate back to the login screen
        Intent intent = new Intent(this, facultmessage.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent returning to it when pressing back again
        super.onBackPressed(); // Call super method
    }


}



