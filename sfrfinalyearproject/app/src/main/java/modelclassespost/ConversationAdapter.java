package modelclassespost;

import android.content.Context;
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
    private String currentUser; // This will be the username of the logged-in user
    private RecyclerView recyclerView;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    public ConversationAdapter(Context context, List<ConversationItem> conversationItems, String currentUser, RecyclerView recyclerView) {
        this.context = context;
        this.conversationItems = conversationItems;
        this.currentUser = currentUser;
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemViewType(int position) {
        ConversationItem item = conversationItems.get(position);

        // Log to verify view type determination
        String messageType = item.getSenderUsername().equals(currentUser) ? "SENT" : "RECEIVED";
        System.out.println("Message at position " + position + " is " + messageType);

        return item.getSenderUsername().equals(currentUser) ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_conversation_sender, parent, false);
            return new SentViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_conversation_receiver, parent, false);
            return new ReceivedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ConversationItem item = conversationItems.get(position);

        if (holder instanceof SentViewHolder) {
            bindSentViewHolder((SentViewHolder) holder, item);
        } else if (holder instanceof ReceivedViewHolder) {
            bindReceivedViewHolder((ReceivedViewHolder) holder, item);
        }
    }

    private void bindSentViewHolder(SentViewHolder holder, ConversationItem item) {
        holder.wishDateTime.setText(item.getSenderFirstName() + " " + item.getSenderLastName());

        if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
            Picasso.get().load(RetrofitClient.getBaseUrl() + "images/profileimages/" + item.getSenderProfileImage() + ".jpg").into(holder.senderProfileImage);
        } else {
            holder.senderProfileImage.setImageResource(R.drawable.baseline_account_circle_24); // Default image
        }

        if (item.getWishcontent() != null) {
            holder.wishContent.setText(item.getWishcontent());
            holder.emojiImageView.setVisibility(View.GONE);
        } else {
            holder.wishContent.setVisibility(View.GONE);
            holder.emojiImageView.setVisibility(View.VISIBLE);
            if (item.getEmojiData() != null && !item.getEmojiData().isEmpty()) {
                Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/" +
                        item.getEmojiData() + ".png").into(holder.emojiImageView);
            } else {
                holder.emojiImageView.setImageDrawable(null);
            }
        }
    }

    private void bindReceivedViewHolder(ReceivedViewHolder holder, ConversationItem item) {
        holder.wishDateTime.setText(item.getSenderFirstName() + " " + item.getSenderLastName());

        if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
            Picasso.get().load(RetrofitClient.getBaseUrl() + "images/profileimages/" + item.getSenderProfileImage() + ".jpg").into(holder.receiverProfileImage);
        } else {
            holder.receiverProfileImage.setImageResource(R.drawable.baseline_account_circle_24); // Default image
        }

        if (item.getWishcontent() != null) {
            holder.wishContent.setText(item.getWishcontent());
            holder.emojiImageView.setVisibility(View.GONE);
        } else {
            holder.wishContent.setVisibility(View.GONE);
            holder.emojiImageView.setVisibility(View.VISIBLE);
            if (item.getEmojiData() != null && !item.getEmojiData().isEmpty()) {
                Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getEmojiData()+ ".png").into(holder.emojiImageView);
            } else {
                holder.emojiImageView.setImageDrawable(null);
            }
        }
    }
    @Override
    public int getItemCount() {
        return conversationItems.size();
    }

    public void addNewMessage(ConversationItem newItem) {
        conversationItems.add(newItem);
        notifyItemInserted(conversationItems.size() - 1);
        recyclerView.scrollToPosition(conversationItems.size() - 1);
    }

    static class SentViewHolder extends RecyclerView.ViewHolder {
        TextView wishContent, wishDateTime;
        ImageView senderProfileImage, emojiImageView;

        SentViewHolder(@NonNull View itemView) {
            super(itemView);
            wishContent = itemView.findViewById(R.id.wish_content);
            wishDateTime = itemView.findViewById(R.id.sewish_date_time);
            senderProfileImage = itemView.findViewById(R.id.sender_profile_image);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }
    }

    static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        TextView wishContent, wishDateTime;
        ImageView receiverProfileImage, emojiImageView;

        ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            wishContent = itemView.findViewById(R.id.wish_content);
            wishDateTime = itemView.findViewById(R.id.rewish_date_time);
            receiverProfileImage = itemView.findViewById(R.id.receiver_profile_image);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }
    }
}