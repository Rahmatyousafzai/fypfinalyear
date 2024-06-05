package adopter;

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
import mydataapi.RetrofitClient;

public class emoji extends RecyclerView.Adapter<emoji.ViewHolder> {
    private Context context;
    private List<Emoji> emojiList;

    public emoji(Context context, List<Emoji> emojiList) {
        this.context = context;
        this.emojiList = emojiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imoji_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emoji emoji = emojiList.get(position);
        String imageUrl = RetrofitClient.getBaseUrl()+"images/emojis/"+emoji.getImagePath()+".png";
        Picasso.get().load(imageUrl).into(holder.emojiImageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                Log.d("EmojiAdapter", "Image loaded: " + imageUrl);
            }

            @Override
            public void onError(Exception e) {
                Log.e("EmojiAdapter", "Error loading image: " + e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImageView;

        ViewHolder(View itemView) {
            super(itemView);
            emojiImageView = itemView.findViewById(R.id.emojiImageView);

        }
    }
}
