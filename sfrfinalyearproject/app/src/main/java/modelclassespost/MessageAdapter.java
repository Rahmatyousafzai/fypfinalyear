package modelclassespost;

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

import ModeClasees.Message;
import mydataapi.RetrofitClient;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messages;

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        // Bind data to your ViewHolder views
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    // Method to set new data to the adapter
    public void setData(List<Message> newData) {
        messages.clear();
        messages.addAll(newData);
        notifyDataSetChanged();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImage;
        private TextView firstName, lastName, messageContent;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            messageContent = itemView.findViewById(R.id.messageContent);
        }

        public void bind(Message message) {
            // Bind data to views
            firstName.setText(message.getFirstName());
            lastName.setText(message.getLastName());
            messageContent.setText(message.getContent());
            // Load profile image using Picasso
            if (message.getProfileImageUrl() != null && !message.getReceiverProfileImage().isEmpty()) {
                String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + message.getProfileImageUrl() + ".jpg";
                Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(profileImage);
            } else {
                profileImage.setImageResource(R.drawable.baseline_account_circle_24);
            }
        }
    }
}
