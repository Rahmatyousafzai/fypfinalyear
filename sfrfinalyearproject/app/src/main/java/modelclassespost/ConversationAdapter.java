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
    private String currentUserUsername;

    // View types for sender and receiver messages
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    public ConversationAdapter(Context context, List<ConversationItem> conversationItems, String currentUserUsername) {
        this.context = context;
        this.conversationItems = conversationItems;
        this.currentUserUsername = currentUserUsername;
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
        return null; // Add default return statement or handle other view types
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ConversationItem item = conversationItems.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_SENDER) {
            SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
            senderViewHolder.senderName.setText(item.getSenderFirstName());
            if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
                Picasso.get().load(getProfileImageUrl(item.getSenderProfileImage())).into(senderViewHolder.senderImage);
            } else {
                senderViewHolder.senderImage.setImageResource(R.drawable.baseline_account_circle_24); // Load default image from drawable
            }
            Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/" + item.getEmojiData() + ".png").into(senderViewHolder.emojiImageView);
        } else {
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
            receiverViewHolder.receiverName.setText(item.getSenderFirstName());
            if (item.getSenderProfileImage() != null && !item.getSenderProfileImage().isEmpty()) {
                Picasso.get().load(getProfileImageUrl(item.getSenderProfileImage())).into(receiverViewHolder.receiverImage);
            } else {
                receiverViewHolder.receiverImage.setImageResource(R.drawable.baseline_account_circle_24); // Load default image from drawable
            }
            Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/"  + item.getEmojiData() + ".png").into(receiverViewHolder.emojiImageView);
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
        } else  {
            return VIEW_TYPE_RECEIVER;
        }
         // Handle other cases or return a default value
    }


    public void addItem(ConversationItem newItem) {
        if (conversationItems != null) {
            conversationItems.add(newItem);
            notifyItemInserted(conversationItems.size() - 1);
        }
    }



    private String getProfileImageUrl(String profileImage) {
        // Logic to get profile image URL based on profile image data
        return RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
    }

    static class SenderViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView, senderImage;
        TextView senderName;

        SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view_sender);
            senderImage = itemView.findViewById(R.id.senderImage);
            senderName = itemView.findViewById(R.id.sender_name);
        }
    }

    public void setData(List<ConversationItem> newData) {
        conversationItems.clear();
        conversationItems.addAll(newData);
        notifyDataSetChanged();
    }

    static class ReceiverViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView, receiverImage;
        TextView receiverName;

        ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.Reciveremoji);
            receiverImage = itemView.findViewById(R.id.emoji_image_view_receiver);
            receiverName = itemView.findViewById(R.id.rcname);
        }
    }
}
