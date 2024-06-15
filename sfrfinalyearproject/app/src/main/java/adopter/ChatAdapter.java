package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Message;
import mydataapi.RetrofitClient;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private List<Message> messageList;
    private String senderId;

    public ChatAdapter(Context context, List<Message> messageList, String senderId) {
        this.context = context;
        this.messageList = messageList;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);

        if (message.getSenderUsername().equals(senderId)) {
            holder.sentMessageLayout.setVisibility(View.VISIBLE);
            holder.receivedMessageLayout.setVisibility(View.GONE);


            if (message.getEmojiData() != null) {
                holder.sentEmoji.setVisibility(View.VISIBLE);
                Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/" + message.getEmojiData()+".png").into(holder.sentEmoji);
            } else {
                holder.sentEmoji.setVisibility(View.GONE);
            }
        } else {
            holder.receivedMessageLayout.setVisibility(View.VISIBLE);
            holder.sentMessageLayout.setVisibility(View.GONE);


            if (message.getEmojiData() != null) {
                holder.receivedEmoji.setVisibility(View.VISIBLE);
                Picasso.get().load(RetrofitClient.getBaseUrl() + "images/emojis/" + message.getEmojiData()+".png").into(holder.receivedEmoji);
            } else {
                holder.receivedEmoji.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sentMessage, receivedMessage;
        private ImageView sentEmoji, receivedEmoji;
        private LinearLayout sentMessageLayout, receivedMessageLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sentMessage = itemView.findViewById(R.id.sent_message);
            receivedMessage = itemView.findViewById(R.id.received_message);
            sentEmoji = itemView.findViewById(R.id.sent_emoji);
            receivedEmoji = itemView.findViewById(R.id.received_emoji);
            sentMessageLayout = itemView.findViewById(R.id.sent_message_layout);
            receivedMessageLayout = itemView.findViewById(R.id.received_message_layout);
        }
    }
}
