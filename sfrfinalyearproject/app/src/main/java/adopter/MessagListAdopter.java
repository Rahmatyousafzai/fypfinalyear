package adopter;

import android.content.Intent;
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

import Faculty.groupmessage_body;
import ModeClasees.Wish;
import mydataapi.RetrofitClient;
import studentClasses.GroupsData;

public class MessagListAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> messageList;
    private OnTeacherClickListener listener;

    private static final int VIEW_TYPE_SIMPLE = 1;
    private static final int VIEW_TYPE_GROUP = 2;

    public MessagListAdopter(List<Object> messageList, OnTeacherClickListener listener) {
        this.messageList = messageList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position) instanceof Wish) {
            return VIEW_TYPE_SIMPLE;
        } else {
            return VIEW_TYPE_GROUP;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SIMPLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelist_rows, parent, false);
            return new SimpleMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupmessagelist_rows, parent, false);
            return new GroupMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_SIMPLE) {
            Wish wish = (Wish) messageList.get(position);
            ((SimpleMessageViewHolder) holder).bind(wish);
        } else {
            GroupsData groupMessage = (GroupsData) messageList.get(position);
            ((GroupMessageViewHolder) holder).bind(groupMessage);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void setTeacherList(List<?> messages) {
        messageList.clear();
        messageList.addAll(messages);
        notifyDataSetChanged();
    }

    class SimpleMessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderName;
        ImageView profileImage;

        SimpleMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderName = itemView.findViewById(R.id.sender_name);
            profileImage = itemView.findViewById(R.id.profile_image);
        }

        void bind(Wish wish) {
            senderName.setText(wish.getFirstName() + " " + wish.getLastName());
            Picasso.get().load(RetrofitClient.getBaseUrl() + "images/profileimages/" + wish.getProfileImage() + ".jpg")
                    .error(R.drawable.baseline_account_circle_24).into(profileImage);
            itemView.setOnClickListener(v -> listener.onTeacherClick(wish));
        }
    }

    class GroupMessageViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        ImageView groupIcon;

        GroupMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            groupIcon = itemView.findViewById(R.id.group_icon);
        }

        void bind(GroupsData groupMessage) {
            groupName.setText(groupMessage.getGropnmae());
            Picasso.get().load(groupMessage.getGroupicon()).error(R.drawable.baseline_account_circle_24).into(groupIcon);
            itemView.setOnClickListener(v -> {
                // Pass data to the next activity when clicked
                Intent intent = new Intent(v.getContext(), groupmessage_body.class);
                intent.putExtra("group_id", groupMessage.getGID());
                intent.putExtra("group_name", groupMessage.getGropnmae());
                intent.putExtra("group_Dis", groupMessage.getGropnmae());
                intent.putExtra("group_image", groupMessage.getGropnmae());
                // Add more data as needed
                v.getContext().startActivity(intent);
            });
        }
    }
}
