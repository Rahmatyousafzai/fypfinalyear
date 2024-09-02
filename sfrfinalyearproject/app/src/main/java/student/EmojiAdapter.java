package student;

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

import ModeClasees.Emoji;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private Context context;
    private List<Emoji> emojis;
    private OnEmojiClickListener listener;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Emoji emoji);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public EmojiAdapter(Context context, List<Emoji> emojis, OnEmojiClickListener listener) {
        this.context = context;
        this.emojis = emojis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder holder, int position) {
        Emoji emoji = emojis.get(position);
        holder.bind(emoji);
    }

    @Override
    public int getItemCount() {
        return emojis.size();
    }



    class EmojiViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView;

        public EmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image_view);
        }

        public void bind(Emoji emoji) {
            // Load image into ImageView using Picasso
            String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png";
            Picasso.get().load(imageUrl).into(emojiImageView);

            // Set click listener for the entire itemView
            itemView.setOnClickListener(v -> {
                Log.d("EmojiAdapter", "Emoji clicked: ID " + emoji.getEmojiID());
                if (listener != null) {
                    listener.onEmojiClick(emoji);
                }
            });
        }
    }
}
