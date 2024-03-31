package adopter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;

public class Wishadapter extends RecyclerView.Adapter<Wishadapter.WishViewHolder> {
    private Context Context;
    private ArrayList<Wish> mWishes;

    public Wishadapter(Context context, ArrayList<Wish> wishes) {
        Context = context;
        mWishes = wishes;
    }

    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(Context).inflate(R.layout.student_dashboard_row, parent, false);
        return new WishViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        Wish wish = mWishes.get(position);
        holder.pfimage.setImageResource(wish.getProfileImage());
        holder.icon.setImageResource(wish.getIcon());
        holder.pfname.setText(wish.getFirstName());
        holder.content.setText(wish.getContent());

        // Create an ArrayList of emoji resources
        ArrayList<Integer> emojiList = new ArrayList<>();
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.happy);
        emojiList.add(R.drawable.smiley);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.happy);
        emojiList.add(R.drawable.smiley);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.happy);
        emojiList.add(R.drawable.smiley);

        // Add more emojis as needed

        // Create an instance of the emoji adapter and set it to the RecyclerView
        emoji emojiAdapter = new emoji(Context, emojiList);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(Context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(emojiAdapter);
    }


    @Override
    public int getItemCount() {
        return mWishes.size();
    }

    public static class WishViewHolder extends RecyclerView.ViewHolder {
        ImageView pfimage, icon;
        TextView pfname, content;
        RecyclerView recyclerView;

        public WishViewHolder(View itemView) {
            super(itemView);
            pfimage = itemView.findViewById(R.id.pfimage);
            icon = itemView.findViewById(R.id.icon);
            pfname = itemView.findViewById(R.id.pfname);
            content = itemView.findViewById(R.id.desc);
            recyclerView = itemView.findViewById(R.id.RcEmoji);
        }
    }
}

