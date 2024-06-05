package adopter;

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
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;


public class EmojiAdapter2 extends RecyclerView.Adapter<EmojiAdapter2.EmojiViewHolder> {


        private Context context;
        private List<Emoji> emojis;
        private OnEmojiClickListener listener;

        public EmojiAdapter2(Context context, List<Emoji> emojis, OnEmojiClickListener listener) {
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
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onEmojiClick(emojis.get(getAdapterPosition()));
                    }
                });
            }

            public void bind(Emoji emoji) {
                // Load image into ImageView using Picasso
                Picasso.get()
                        .load(RetrofitClient.getBaseUrl() + "images/emojis/" + emoji.getImagePath() + ".png")
                        .into(emojiImageView);
            }
        }
    }
