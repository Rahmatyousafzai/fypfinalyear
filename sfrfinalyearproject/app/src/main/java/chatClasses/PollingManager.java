package chatClasses;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;

import Faculty.facultymessagebody;
import ModeClasees.Message;
import modelclassespost.ConversationItem;
import mydataapi.Apiservices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollingManager {

    private static final long POLLING_INTERVAL = 5000; // 5 seconds
    private Handler handler;
    private Runnable pollingRunnable;
    private Apiservices apiService;
    private String username;
    private String teacherUsername;
    private facultymessagebody activity;

    public PollingManager(facultymessagebody activity, Apiservices apiService, String username, String teacherUsername) {
        this.activity = activity;
        this.apiService = apiService;
        this.username = username;
        this.teacherUsername = teacherUsername;
        handler = new Handler(Looper.getMainLooper());
    }

    public void startPolling() {
        pollingRunnable = new Runnable() {
            @Override
            public void run() {
                fetchMessages();
                handler.postDelayed(this, POLLING_INTERVAL);
            }
        };
        handler.post(pollingRunnable);
    }

    public void stopPolling() {
        if (pollingRunnable != null) {
            handler.removeCallbacks(pollingRunnable);
        }
    }

    private void fetchMessages() {
        // Make sure to pass appropriate parameters to fetch new messages
        apiService.chatmessage(username, teacherUsername).enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Message> messages = response.body();
                    for (Message message : messages) {
                        ConversationItem newItem = new ConversationItem(
                                message.getSenderUsername() != null ? message.getSenderUsername() : "",
                                message.getSenderFirstName() != null ? message.getSenderFirstName() : "",
                                message.getSenderLastName() != null ? message.getSenderLastName() : "",
                                message.getSenderProfileImage() != null ? message.getSenderProfileImage() : "",
                                message.getReceiverUsername() != null ? message.getReceiverUsername() : "",
                                message.getReceiverFirstName() != null ? message.getReceiverFirstName() : "",
                                message.getReceiverLastName() != null ? message.getReceiverLastName() : "",
                                message.getReceiverProfileImage() != null ? message.getReceiverProfileImage() : "",
                                message.getWishcontent() != null ? message.getWishcontent() : "",
                                message.getEmojidata() != null ? message.getEmojidata() : "",
                                message.getWishDateTime() != null ? message.getWishDateTime() : ""
                        );

                        // Only add new items to the RecyclerView
                        activity.runOnUiThread(() -> activity.addNewMessage(newItem));
                    }
                } else {
                    Log.d("PollingManager", "Failed to fetch messages");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e("PollingManager", "Failed to fetch messages", t);
            }
        });
    }
}
