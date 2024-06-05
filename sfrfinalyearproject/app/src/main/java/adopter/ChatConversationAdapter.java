package adopter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import mydataapi.RetrofitClient;


public class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.ViewHolder> {
    private Context context;
    private List<ChatMessage> chatMessages;
    private String currentUserUsername = "2020-arid-3535";

    // View types for sender and receiver messages
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    public ChatConversationAdapter(Context context, List<ChatMessage> chatMessages, String currentUserUsername) {
        this.context = context;
        this.chatMessages = chatMessages;
        this.currentUserUsername = currentUserUsername;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SENDER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_sender_message, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_receiver_message, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage item = chatMessages.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENDER) {
            // Bind data for sender message
            Picasso.get()
                    .load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getContent() + ".png")
                    .into(holder.emojiImageViewSender);
        } else {
            // Bind data for receiver message
            Picasso.get()
                    .load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getContent() + ".png")
                    .into(holder.emojiImageViewReceiver);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages != null ? chatMessages.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage item = chatMessages.get(position);
        if (item != null && item.getUsername() != null && item.getUsername().equals(currentUserUsername)) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;
        }
    }

    public void addItem(ChatMessage newItem) {
        if (chatMessages != null) {
            chatMessages.add(newItem);
            notifyItemInserted(chatMessages.size() - 1);
            Log.d("Adapter", "Item added: " + newItem.toString());
        }
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageViewSender;
        ImageView emojiImageViewReceiver;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageViewSender = itemView.findViewById(R.id.emoji_image_view_sender);
            emojiImageViewReceiver = itemView.findViewById(R.id.emoji_image_view_receiver);
        }
    }
}
