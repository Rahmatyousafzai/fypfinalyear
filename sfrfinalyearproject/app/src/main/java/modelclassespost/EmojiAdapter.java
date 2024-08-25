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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ModeClasees.Emoji;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private Context context;
    private List<Emoji> emojis;
    private OnEmojiClickListener listener;
    private Map<Integer, View> emojiViewMap = new HashMap<>();

    public EmojiAdapter(Context context, List<Emoji> emojis, OnEmojiClickListener listener) {
        this.context = context;
        this.emojis = emojis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each emoji item
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
        Emoji emoji = emojis.get(position);
        holder.bind(emoji);

        // Set click listener on each emoji item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEmojiClick(emoji);
            }
        });

        // Update the map with the latest emoji view
        emojiViewMap.put(emoji.getEmojiID(), holder.itemView);
    }

    @Override
    public int getItemCount() {
        return emojis.size();
    }

    public View getEmojiViewById(int emojiId) {
        return emojiViewMap.get(emojiId);
    }

    class EmojiViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView;

        public EmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }

        public void bind(Emoji emoji) {
            if (emoji != null) {
                String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
                Picasso.get().load(imageUrl).into(emojiImageView);
            }
        }
    }
}
