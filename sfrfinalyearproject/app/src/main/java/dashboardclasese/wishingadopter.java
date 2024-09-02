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
import facultyClasses.Reaction;
import facultyClasses.ReactionAdapter;
import modelclassespost.EmojiAdapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class wishingadopter extends RecyclerView.Adapter<wishingadopter.ViewHolder> {

    private List<wishingclass> wishes;
    private Context context;
    private EmojiClickListener emojiClickListener;
    private List<Emoji> allEmojis;
    private PopupWindow popupWindow;
    private int currentSwId; // To store the current long-pressed swId

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

        if (wish.getContent() != null && !wish.getContent().isEmpty() &&
                !wish.getFirstName().isEmpty() && !wish.getLastName().isEmpty()) {
            holder.txtContent.setText(wish.getContent());
            holder.txtDateTime.setText(wish.getDateTime());

            fetchReactionCount(holder.txtReactionCount, wish.getSwId());

            String fullName = wish.getFirstName() + " " + wish.getLastName();
            holder.pfname.setText(fullName);
        } else {
            Log.d("null wishes", "null wishes");
        }

        holder.itemView.setOnLongClickListener(v -> {
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
            currentSwId = wish.getSwId();
            showEmojiPopup(v);
            return true;
        });

        // Setup click listener to fetch and show reactions
        holder.txtReactionCount.setOnClickListener(v -> {
            fetchReactionsForWish(wish.getSwId(), reactions -> {
                showReactionsPopup(v, reactions);
            });
        });
        holder.allreaction.setOnClickListener(v -> {
            fetchReactionsForWish(wish.getSwId(), reactions -> {
                showReactionsPopup(v, reactions);
            });
        });
    }

    private void showEmojiPopup(View anchorView) {
        if (allEmojis.isEmpty()) {
            Log.e("Emoji Error", "No emojis fetched yet.");
            return;
        }

        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_layout, null);
        RecyclerView emojisPopupRecyclerView = popupView.findViewById(R.id.emojis_popup_recyclerview);
        emojisPopupRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        EmojiAdapter emojiAdapter = new EmojiAdapter(context, allEmojis, emoji -> {
            if (emojiClickListener != null) {
                emojiClickListener.onEmojiClick(currentSwId, emoji.getEmojiID()); // Pass currentSwId and selected emoji ID
            }
            if (popupWindow != null) {
                popupWindow.dismiss();
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

    private void showReactionsPopup(View anchorView, List<Reaction> reactions) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }

        View popupView = LayoutInflater.from(context).inflate(R.layout.reactions_popup_window, null);
        RecyclerView reactionsRecyclerView = popupView.findViewById(R.id.reactions_recyclerview);
        reactionsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        ReactionAdapter reactionAdapter = new ReactionAdapter(context, reactions);
        reactionsRecyclerView.setAdapter(reactionAdapter);

        PopupWindow reactionsPopupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        reactionsPopupWindow.setFocusable(true);
        reactionsPopupWindow.showAsDropDown(anchorView);

        popupView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                reactionsPopupWindow.dismiss();
                return true;
            }
            return false;
        });
    }

    private void fetchReactionsForWish(int swId, ReactionCallback callback) {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<Reaction>> call = apiService.getAllReactions(swId);

        call.enqueue(new Callback<List<Reaction>>() {
            @Override
            public void onResponse(Call<List<Reaction>> call, Response<List<Reaction>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onReactionsFetched(response.body());
                } else {
                    Log.d("Failed to get reactions", "Failed to get reactions");
                    callback.onReactionsFetched(new ArrayList<>()); // Return an empty list if fetching fails
                }
            }

            @Override
            public void onFailure(Call<List<Reaction>> call, Throwable t) {
                Log.d("reactions", "Failed to fetch reactions", t);
                callback.onReactionsFetched(new ArrayList<>()); // Return an empty list if fetching fails
            }
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
        Call<List<Emoji>> call = apiService.getAllEmojis();

        call.enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    allEmojis.clear();
                    allEmojis.addAll(response.body());
                } else {
                    Log.e("Emoji Error", "Failed to fetch emojis");
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.e("Emoji Error", "Failed to fetch emojis", t);
            }
        });
    }

    public interface ReactionCallback {
        void onReactionsFetched(List<Reaction> reactions);
    }

    public interface EmojiClickListener {
        void onEmojiClick(int wishId, int emojiId);

        void onEmojiClick(int emojiId);
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile, allreaction;
        TextView txtContent, txtDateTime, txtReactionCount, pfname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.senderimage);
            txtContent = itemView.findViewById(R.id.wishcontent);
            txtDateTime = itemView.findViewById(R.id.dateTime);
            txtReactionCount = itemView.findViewById(R.id.reactionCount);
            pfname = itemView.findViewById(R.id.pfname);
            allreaction = itemView.findViewById(R.id.allreaction);
        }
    }

    public void animateEmojiZoom(int emojiId) {
        if (popupWindow == null || !popupWindow.isShowing()) {
            Log.e("wishingadopter", "PopupWindow not shown.");
            return;
        }

        View emojiView = findEmojiViewById(emojiId);

        if (emojiView != null) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1.0f, 1.5f, // Start and end values for the X axis scaling
                    1.0f, 1.5f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f // Pivot point of Y scaling
            );
            scaleAnimation.setDuration(300); // Duration of the animation
            scaleAnimation.setFillAfter(true); // Keep the scaling after animation ends

            emojiView.startAnimation(scaleAnimation);
            Log.d("wishingadopter", "Animation started for emoji ID: " + emojiId);
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
                    // Ensure this method is implemented in EmojiAdapter
                }
            }
        }
        return null; // Return null if view not found
    }

    public void updateReactionCount(int wishId) {
        for (int i = 0; i < wishes.size(); i++) {
            if (wishes.get(i).getSwId() == wishId) {
                notifyItemChanged(i);
                break;
            }
        }
    }
}
