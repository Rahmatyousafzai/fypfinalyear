package dashboardclasese;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
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
import mydataapi.Reaction;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class wishingadopter extends RecyclerView.Adapter<wishingadopter.ViewHolder> implements OnEmojiClickListener {

    private List<wishingclass> wishes;
    private Context context;
    private EmojiClickListener emojiClickListener;
    private List<Emoji> allEmojis;
    private PopupWindow popupWindow;

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

        if (wish.getContent() != null && !wish.getContent().isEmpty()) {
            holder.txtContent.setText(wish.getContent());
            holder.txtDateTime.setText(wish.getDateTime());

            // Fetch and display reaction count
            fetchReactionCount(holder.txtReactionCount, wish.getSwId());

            String fullName = wish.getFirstName() + " " + wish.getLastName();
            holder.pfname.setText(fullName);
        } else {
            Log.d("null wishes", "null wishes");
        }

        if (wish.getProfileImage() != null && !wish.getProfileImage().isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + wish.getProfileImage() + ".jpg";

            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(holder.profile, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}

                        @Override
                        public void onError(Exception e) {
                            Log.e("Picasso Error", "Failed to load profile image", e);
                        }
                    });
        } else {
            holder.profile.setImageResource(R.drawable.baseline_account_circle_24);
        }

        holder.itemView.setOnLongClickListener(v -> {
            showEmojiPopup(v, wish.getSwId());
            return true;
        });
    }

    private void showEmojiPopup(View anchorView, int swId) {
        if (allEmojis.isEmpty()) {
            Log.e("Emoji Error", "No emojis fetched yet.");
            return;
        }

        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        EmojiAdapter emojiAdapter = new EmojiAdapter(context, allEmojis, new OnEmojiClickListener() {
            @Override
            public void onEmojiClick(Emoji emoji) {
                // Handle emoji click with swId
                onEmojiClick(swId, emoji.getEmojiID());
            }

            @Override
            public void onEmojiFetched(List<Emoji> section3Emojis) {}

            @Override
            public void onEmojisFetched(List<Emoji> emojis) {}

            @Override
            public void onEmojiClickWithId(int emojiId) {

            }

            @Override
            public void onEmojiClickForWish(int wishId, int emojiId) {

            }

            @Override
            public void onEmojiClick(int emojiId) {

            }

            @Override
            public void onEmojiClick(int wishId, int emojiId) {

            }
        });
        emojisPopupRecyclerView.setAdapter(emojiAdapter);

        popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchorView);

        popupView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                popupWindow.dismiss();
                return true;
            }
            return false;
        });
    }

    private void fetchReactionCount(TextView reactionCountView, Integer swId) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<Reaction> call = apiService.getReactionCount(swId);

        call.enqueue(new Callback<Reaction>() {
            @Override
            public void onResponse(Call<Reaction> call, Response<Reaction> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int count = response.body().getCount();
                    reactionCountView.setText("Reactions: " + count);
                } else {
                    Log.d("Failed to get count", "Failed to get count");
                    reactionCountView.setText("Reactions: 0");
                }
            }

            @Override
            public void onFailure(Call<Reaction> call, Throwable t) {
                Log.d("reaction", "Failed to fetch reaction count", t);
                reactionCountView.setText("Reactions: 0");
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
        Log.d("wishingadopter", "Emoji clicked: " + emoji.getEmojiID());
    }

    @Override
    public void onEmojiFetched(List<Emoji> section3Emojis) {}

    @Override
    public void onEmojisFetched(List<Emoji> emojis) {}

    @Override
    public void onEmojiClickWithId(int emojiId) {

    }

    @Override
    public void onEmojiClickForWish(int wishId, int emojiId) {

    }

    @Override
    public void onEmojiClick(int emojiId) {
        // Handle emoji click with only emojiId
        emojiClickListener.onEmojiClick(emojiId);
    }

    @Override
    public void onEmojiClick(int wishId, int emojiId) {
        Log.d("wishingadopter", "Emoji clicked: " + emojiId + " for wish ID: " + wishId);
        animateEmojiZoom(emojiId);
        // Find the correct wish in the adapter
        for (wishingclass wish : wishes) {
            if (wish.getSwId() == wishId) {
                // Apply zoom effect and post reaction
                postReaction("2020-Arid-3535", wishId, emojiId);
                break;
            }
        }

        // Dismiss popup window
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    private void animateEmojiZoom(int emojiId) {
        // Ensure popup window is displayed before trying to animate
        if (popupWindow == null || !popupWindow.isShowing()) {
            Log.e("wishingadopter", "PopupWindow not shown.");
            return;
        }

        View emojiView = findEmojiViewById(emojiId);

        if (emojiView != null) {
            // Create a scale animation
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1.0f, 1.5f, // Start and end values for the X axis scaling
                    1.0f, 1.5f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f // Pivot point of Y scaling
            );
            scaleAnimation.setDuration(300); // Duration of the animation
            scaleAnimation.setFillAfter(true); // Keep the scaling after animation ends

            // Start the animation
            emojiView.startAnimation(scaleAnimation);
            Log.e("wishingadopter", "Animation started for emoji ID: " + emojiId);
        } else {
            Log.e("wishingadopter", "Emoji view not found for ID: " + emojiId);
        }
    }

    private View findEmojiViewById(int emojiId) {
        if (popupWindow != null && popupWindow.isShowing()) {
            View popupView = popupWindow.getContentView();
            RecyclerView recyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
            if (recyclerView != null) {
                EmojiAdapter adapter = (EmojiAdapter) recyclerView.getAdapter();
                if (adapter != null) {
                    return adapter.getEmojiViewById(emojiId); // Ensure this method is implemented in EmojiAdapter
                }
            }
        }
        return null; // Return null if view not found
    }

    private void postReaction(String reacterID, int sw_id, int emojiID) {
        Apiservices apiService = RetrofitClient.getInstance();

        // Create the reaction object
        Reaction reaction = new Reaction();
        reaction.setReacterID("2020-Arid-3538");
        reaction.setSw_id(1003);
        reaction.setEmojiId(6);
        reaction.setDatetime(""); // Ensure the server handles this correctly

        // Log the request payload
        Log.d("postReaction", "Posting reaction: " + reaction.toString());

        // Call the API
        Call<Void> call = apiService.postReaction(reaction);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API Response", "Reaction processed successfully");
                    // Update the reaction count for the specific wish
                    updateReactionCount(sw_id);
                } else {
                    Log.e("API Error", "Error response: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API Failure", "Failed to post reaction", t);
            }
        });
    }

    private void updateReactionCount(int swId) {
        // Iterate through wishes and find the correct wish
        for (int i = 0; i < wishes.size(); i++) {
            if (wishes.get(i).getSwId() == swId) {
                // Update reaction count for the correct wish
                notifyItemChanged(i);
                break;
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent, txtReactionCount;
        TextView txtDateTime, pfname;
        ImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);
            pfname = itemView.findViewById(R.id.pfname);
            profile = itemView.findViewById(R.id.senderimage);
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
