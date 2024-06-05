package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class UserMessageAdapte extends RecyclerView.Adapter<UserMessageAdapte.ViewHolder> {

    private List<UserMessage> userMessages;
    private Context context;

    public UserMessageAdapte(List<UserMessage> userMessages, Context context) {
        this.userMessages = userMessages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserMessage message = userMessages.get(position);
        holder.usernameTextView.setText(message.getUsername());
        holder.contentTextView.setText(message.getContent());
        holder.dateTextView.setText(message.getDate());
    }

    @Override
    public int getItemCount() {
        return userMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, contentTextView, dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
