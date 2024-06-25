package dashboardclasese;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import modelclassespost.EmojiAdapter;
import mydataapi.Apiservices;
import mydataapi.OnEmojiClickListener;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class wishingadopter extends RecyclerView.Adapter<wishingadopter.ViewHolder> implements OnEmojiClickListener {

    private List<wishingclass> wishes;
    private Context context;
    private EmojiClickListener emojiClickListener;
    private List<Emoji> allEmojis;

    public wishingadopter(Context context, List<wishingclass> wishes, EmojiClickListener emojiClickListener) {
        this.context = context;
        this.wishes = wishes;
        this.emojiClickListener = emojiClickListener;
        this.allEmojis = new ArrayList<>();
        fetchAllEmojis();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wish, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        wishingclass wish = wishes.get(position);
        holder.txtContent.setText(wish.getContent());
        holder.txtDateTime.setText(wish.getDateTime());
        holder.txtReactionCount.setText("Reactions: " + wish.getReactionCount());

        String Firstneme=wish.getFirstName();
        String lastname=wish.getLastName();
        String Fullname=Firstneme+" "+lastname;

        holder.pfname.setText(Fullname);




        if (wish.getProfileImage() != null && !wish.getProfileImage().isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + wish.getProfileImage() + ".jpg";

            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(holder.profile, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        } else {

           holder.profile.setImageResource(R.drawable.baseline_account_circle_24);
        }










        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEmojiPopup(v, wish.getSwId());
                return true;
            }
        });
    }

    private void showEmojiPopup(View anchorView, int wishId) {
        if (allEmojis.isEmpty()) {
            Log.e("Emoji Error", "No emojis fetched yet.");
            return;
        }

        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        EmojiAdapter emojiAdapter = new EmojiAdapter(context, allEmojis, this);
        emojisPopupRecyclerView.setAdapter(emojiAdapter);

        PopupWindow popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchorView);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    private void fetchAllEmojis() {
        Apiservices apiService = RetrofitClient.getInstance();
        apiService.getAllEmojis().enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmojis = response.body();
                    notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Failed to fetch all emojis");
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Network Error", "Failed to fetch all emojis", t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        // Handle emoji click from EmojiAdapter
        // You can implement this if needed
    }

    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {

    }

    @Override
    public void onEmojisFetched(List<Emoji> emojis) {

    }

    @Override
    public void onEmojiClick(int emojiId) {
        // Handle emoji click with only emojiId
        emojiClickListener.onEmojiClick(emojiId);
    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        // Handle emoji click with both wishId and emojiId
        emojiClickListener.onEmojiClick(wishId, emojiId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent,txtReactionCount;
        TextView txtDateTime,pfname;

        ImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);
            pfname = itemView.findViewById(R.id.pfname);
            profile=itemView.findViewById(R.id.senderimage);
            txtContent = itemView.findViewById(R.id.wishcontent);
            txtDateTime = itemView.findViewById(R.id.dateTime);
            txtReactionCount = itemView.findViewById(R.id.reactionCount);

        }
    }

    public interface EmojiClickListener {
        void onEmojiClick(int wishId, int emojiId);

        void onEmojiClick(int emojiId);
    }
}
