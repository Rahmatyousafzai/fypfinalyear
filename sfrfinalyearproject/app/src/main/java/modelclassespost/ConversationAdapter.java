package modelclassespost;

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
import studentClasses.UserDataSingleton;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {
    private Context context;
    private List<ConversationItem> conversationItems;
    String username2 = UserDataSingleton.getInstance().getUsername();


    // View types for sender and receiver messages
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    public ConversationAdapter(Context context, List<ConversationItem> conversationItems, String currentUserUsername) {
        this.context = context;
        this.conversationItems = conversationItems;
        this.username2 = currentUserUsername;
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
        ConversationItem item = conversationItems.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENDER) {
            // Bind data for sender message
            Picasso.get()
                    .load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getEmojiData() + ".png")
                    .into(holder.emojiImageViewSender);
        } else {
            // Bind data for receiver message
            Picasso.get()
                    .load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getEmojiData() + ".png")
                    .into(holder.emojiImageViewReceiver);
        }
    }

    @Override
    public int getItemCount() {
        return conversationItems != null ? conversationItems.size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        ConversationItem item = conversationItems.get(position);
        if ((username2!=null)) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;
        }
    }



    public void addItem(ConversationItem newItem) {
        if (conversationItems != null) {
            conversationItems.add(newItem);
            notifyItemInserted(conversationItems.size() - 1);
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
