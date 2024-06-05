package modelclassespost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Emoji;

public class EmojiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Emoji> emojis;
    private String senderId;

    public EmojiAdapter(Context context, List<Emoji> emojis, String senderId) {
        this.context = context;
        this.emojis = emojis;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.item_conversation_sender, parent, false);
            return new SenderEmojiViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_conversation_receiver, parent, false);
            return new ReceiverEmojiViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Emoji emoji = emojis.get(position);
        if (holder instanceof SenderEmojiViewHolder) {
            ((SenderEmojiViewHolder) holder).bind(emoji);
        } else if (holder instanceof ReceiverEmojiViewHolder) {
            ((ReceiverEmojiViewHolder) holder).bind(emoji);
        }
    }

    @Override
    public int getItemCount() {
        return emojis.size();
    }

    @Override
    public int getItemViewType(int position) {
        Emoji emoji = emojis.get(position);
        // Check if the emoji was sent by the sender
        return emoji.getSenderId().equals(senderId) ? 1 : 2;
    }

    static class SenderEmojiViewHolder extends RecyclerView.ViewHolder {
        private ImageView emojiImageView;

        public SenderEmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }

        public void bind(Emoji emoji) {
            // Load sender emoji image into ImageView
            Picasso.get().load(emoji.getImagePath()).into(emojiImageView);
        }
    }

    static class ReceiverEmojiViewHolder extends RecyclerView.ViewHolder {
        private ImageView emojiImageView;

        public ReceiverEmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view_reciver);
        }

        public void bind(Emoji emoji) {
            // Load receiver emoji image into ImageView
            Picasso.get().load(emoji.getImagePath()).into(emojiImageView);
        }
    }
}
