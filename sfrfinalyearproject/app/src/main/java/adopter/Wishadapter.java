/*package adopter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import ModeClasees.Emoji;
import ModeClasees.Wish;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wishadapter extends RecyclerView.Adapter<Wishadapter.WishViewHolder> {
//    private List<Wish> wishList;
   /* private OnTeacherClickListener listener;
    private EmojiAdapter2 emojiAdapter;
    private Context context;

    public Wishadapter(Context context, List<Wish> wishList, OnTeacherClickListener listener) {
        this.context = context;
        this.wishList = wishList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_dashboard_row, parent, false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        Wish wish = wishList.get(position);
        String profileImage = wish.getProfileImage();

        if (profileImage != null && !profileImage.isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + profileImage + ".jpg";
            Picasso.get().load(imageUrl).error(R.drawable.baseline_account_circle_24).into(holder.postProfileImage);
        } else {
            holder.postProfileImage.setImageResource(R.drawable.baseline_account_circle_24);
        }

        holder.postProfileName.setText(wish.getFirstName() + " " + wish.getLastName());
        holder.wishTitle.setText(wish.getTitle());
        holder.wishContent.setText(wish.getContent());

        holder.itemView.setOnClickListener(v -> listener.onTeacherClick(wish));

        // Setup Emoji RecyclerView
        setupEmojiRecyclerView(holder, wish);
    }

    private void setupEmojiRecyclerView(WishViewHolder holder, Wish wish) {
        Apiservices apiService = RetrofitClient.getInstance();

        Call<List<Emoji>> call = apiService.getAllEmojis();
        call.enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Emoji> emojis = response.body();
                    if (!emojis.isEmpty()) {
                        holder.emojiRecyclerView.setVisibility(View.VISIBLE);
                        emojiAaapter = new EmojiAdapter(context, emojis); // Initialize EmojiAdapter2 with data
                        holder.emojiRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                        holder.emojiRecyclerView.setAdapter(emojiAdapter);
                    } else {
                        holder.emojiRecyclerView.setVisibility(View.GONE);
                    }
                } else {
                    handleEmojiFetchError(holder.itemView);
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                handleEmojiFetchError(holder.itemView);
                Log.e("WishAdapter", "Failed to fetch emojis: " + t.getMessage());
            }
        });
    }

    private void handleEmojiFetchError(View itemView) {
        RecyclerView emojiRecyclerView = itemView.findViewById(R.id.emojiRecyclerView);
        emojiRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    static class WishViewHolder extends RecyclerView.ViewHolder {
        TextView wishTitle, wishContent, postProfileName;
        ImageView postProfileImage;
        RecyclerView emojiRecyclerView;

        WishViewHolder(View itemView) {
            super(itemView);
            wishTitle = itemView.findViewById(R.id.textViewName);
            wishContent = itemView.findViewById(R.id.textViewContent);
            postProfileName = itemView.findViewById(R.id.postprofilename);
            postProfileImage = itemView.findViewById(R.id.postprofileimage);
            emojiRecyclerView = itemView.findViewById(R.id.emojiRecyclerView);
        }
    }
}

 */
