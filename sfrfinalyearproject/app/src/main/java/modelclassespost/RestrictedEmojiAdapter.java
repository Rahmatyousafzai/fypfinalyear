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

// RestrictedEmojiAdapter.java
public class RestrictedEmojiAdapter extends RecyclerView.Adapter<RestrictedEmojiAdapter.ViewHolder> {

    private final Context context;
    private final List<Emoji> emojis;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Emoji emoji);
    }

    public RestrictedEmojiAdapter(Context context, List<Emoji> emojis, OnItemClickListener listener) {
        this.context = context;
        this.emojis = emojis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.emoji_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emoji emoji = emojis.get(position);
        holder.bind(emoji, listener);
    }

    @Override
    public int getItemCount() {
        return emojis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView emojiImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emoji_image);
        }

        public void bind(final Emoji emoji, final OnItemClickListener listener) {
            Picasso.get().load(emoji.getImagePath()).into(emojiImageView);
            itemView.setOnClickListener(v -> listener.onItemClick(emoji));
        }
    }
}
