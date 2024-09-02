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
import mydataapi.RetrofitClient;

// EmojiAdapter.java
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private Context context;
    private List<Emoji> emojiList;
    private OnEmojiClickListener onEmojiClickListener;

    public EmojiAdapter(Context context, List<Emoji> emojis, OnEmojiClickListener onEmojiClickListener) {
        this.context = context;
        this.emojiList = emojis;
        this.onEmojiClickListener = onEmojiClickListener;
    }

    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
        Emoji emoji = emojiList.get(position);
        holder.bind(emoji);
        holder.itemView.setOnClickListener(v -> {
            if (onEmojiClickListener != null) {
                onEmojiClickListener.onEmojiClick(emoji);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public static class EmojiViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView;

        public EmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }

        public void bind(Emoji emoji) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
            Picasso.get().load(imageUrl).into(emojiImageView);
        }
    }

    public interface OnEmojiClickListener {
        void onEmojiClick(Emoji emoji);
    }
}
