package modelclassespost;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import mydataapi.RetrofitClient;

public class ConversationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ConversationItem> conversationItems;
    private String currentUserUsername;
    private RecyclerView recyclerView;

    // View types for sender and receiver messages
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    public ConversationAdapter(Context context, List<ConversationItem> conversationItems, String currentUserUsername, RecyclerView recyclerView) {
        this.context = context;
        this.conversationItems = conversationItems;
        this.currentUserUsername = currentUserUsername;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SENDER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_sender_message, parent, false);
            return new SenderViewHolder(view);
        } else if (viewType == VIEW_TYPE_RECEIVER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_receiver_message, parent, false);
            return new ReceiverViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ConversationItem item = conversationItems.get(position);

        Log.d("RecyclerView", "Binding item: " + item.toString());
        Log.d("RecyclerView", "Textmessaeg: " + item.getWishcontent().toString());
        Log.d("RecyclerView", "EMoji: " + item.getEmojiData().toString());

        if (holder.getItemViewType() == VIEW_TYPE_SENDER) {
            SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
            senderViewHolder.senderName.setText(item.getSenderFirstName());

            if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
                Picasso.get().load(getProfileImageUrl(item.getSenderProfileImage())).into(senderViewHolder.senderImage);
            } else {
                senderViewHolder.senderImage.setImageResource(R.drawable.baseline_account_circle_24);
            }

            String emojiData = item.getWishcontent();
            String wishContent = item.getEmojiData();

            if (wishContent != null && !wishContent.isEmpty()) {
                Log.d("RecyclerView", "Sender wish content: " + wishContent);
                senderViewHolder.messageTextViewsender.setText(wishContent);
                senderViewHolder.messageTextViewsender.setVisibility(View.VISIBLE);
                senderViewHolder.senderemojiImageView.setVisibility(View.GONE);
            } else if (emojiData != null && !emojiData.isEmpty()) {
                String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emojiData + ".png";
                Log.d("RecyclerView", "Loading emoji image from: " + imageUrl);

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.sad)
                        .error(R.drawable.sad)
                        .into(senderViewHolder.senderemojiImageView);

                senderViewHolder.messageTextViewsender.setVisibility(View.GONE);
                senderViewHolder.senderemojiImageView.setVisibility(View.VISIBLE);
            } else {
                senderViewHolder.messageTextViewsender.setVisibility(View.GONE);
                senderViewHolder.senderemojiImageView.setVisibility(View.GONE);
            }
        } else {
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
            receiverViewHolder.receiverName.setText(item.getSenderFirstName());

            if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
                Picasso.get().load(getProfileImageUrl(item.getSenderProfileImage())).into(receiverViewHolder.receiverImage);
            } else {
                receiverViewHolder.receiverImage.setImageResource(R.drawable.baseline_account_circle_24);
            }

            String emojiData = item.getWishcontent();
            String wishContent = item.getEmojiData();

            if (wishContent != null && !wishContent.isEmpty()) {
                Log.d("RecyclerView", "Receiver wish content: " + wishContent);
                receiverViewHolder.messageTextViewreciver.setText(wishContent);
                receiverViewHolder.messageTextViewreciver.setVisibility(View.VISIBLE);
                receiverViewHolder.emojiImageView.setVisibility(View.GONE);
            } else if (emojiData != null && !emojiData.isEmpty()) {
                String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emojiData + ".png";
                Log.d("RecyclerView", "Loading emoji image from: " + imageUrl);

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.sad)
                        .error(R.drawable.sad)
                        .into(receiverViewHolder.emojiImageView);

                receiverViewHolder.messageTextViewreciver.setVisibility(View.GONE);
                receiverViewHolder.emojiImageView.setVisibility(View.VISIBLE);
            } else {
                receiverViewHolder.messageTextViewreciver.setVisibility(View.GONE);
                receiverViewHolder.emojiImageView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return conversationItems != null ? conversationItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        ConversationItem item = conversationItems.get(position);
        if (currentUserUsername.equals(item.getSenderUsername())) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;
        }
    }

    public void addItem(ConversationItem newItem) {
        if (conversationItems != null) {
            conversationItems.add(newItem);
            notifyItemInserted(conversationItems.size() - 1);
            // Scroll to the bottom after adding a new item
            if (recyclerView != null) {
                recyclerView.smoothScrollToPosition(conversationItems.size() - 1);
            }
        }
    }

    public void setData(List<ConversationItem> newData) {
        conversationItems.clear();
        conversationItems.addAll(newData);
        notifyDataSetChanged();
        // Scroll to the bottom after updating data
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(conversationItems.size() - 1);
        }
    }

    private String getProfileImageUrl(String profileImage) {
        return RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
    }

    static class SenderViewHolder extends RecyclerView.ViewHolder {
        ImageView senderemojiImageView, senderImage;
        TextView senderName, messageTextViewsender;

        SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderemojiImageView = itemView.findViewById(R.id.emoji_image_view_sender);
            senderImage = itemView.findViewById(R.id.senderImage);
            senderName = itemView.findViewById(R.id.sender_name);
            messageTextViewsender = itemView.findViewById(R.id.sender_message);
        }
    }

    static class ReceiverViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView, receiverImage;
        TextView receiverName, messageTextViewreciver;

        ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view_receiver);
            receiverImage = itemView.findViewById(R.id.receiverImage);
            receiverName = itemView.findViewById(R.id.receiver_name);
            messageTextViewreciver = itemView.findViewById(R.id.receiver_message);
        }
    }
}
