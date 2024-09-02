package Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Emoji;
import mydataapi.RetrofitClient;

public class EmojiRequestAdapter extends RecyclerView.Adapter<EmojiRequestAdapter.ViewHolder> {
    private List<Emoji> emojiRequestList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onPermitClick(Emoji emojiRequest);
        void onCancelClick(Emoji emojiRequest);
    }

    public EmojiRequestAdapter(List<Emoji> emojiRequestList, OnItemClickListener listener) {
        this.emojiRequestList = emojiRequestList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emoji_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emoji emojiRequest = emojiRequestList.get(position);
        holder.bind(emojiRequest, listener);



    }

    @Override
    public int getItemCount() {
        return emojiRequestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView emojiImage;
        TextView requestedFor;
        TextView requestedBy;
        Button permitButton;
        Button cancelButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emojiImage = itemView.findViewById(R.id.emoji_image);
            requestedFor = itemView.findViewById(R.id.requested_for);

            permitButton = itemView.findViewById(R.id.permit_button);
            cancelButton = itemView.findViewById(R.id.cancel_button);
        }

        public void bind(final Emoji emojiRequest, final OnItemClickListener listener) {
            // Assuming that the ImagePath is a drawable resource name
            int imageResId = itemView.getContext().getResources().getIdentifier(RetrofitClient.getBaseUrl() + "images/emojis/" + emojiRequest.getImagePath() + ".png", "drawable", itemView.getContext().getPackageName());



            String imageUrl = RetrofitClient.getBaseUrl() + "images/emojis/" + emojiRequest.getImagePath() + ".png";

            requestedFor.setText(emojiRequest.getFirstname()+" is Requested to send this emoji ");

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.sad)
                    .error(R.drawable.sad)
                    .into(emojiImage);

            permitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPermitClick(emojiRequest);
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCancelClick(emojiRequest);
                }
            });
        }
    }
}
