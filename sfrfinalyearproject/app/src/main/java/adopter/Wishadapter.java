package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;

public class Wishadapter extends RecyclerView.Adapter<Wishadapter.WishViewHolder> {
    private Context context;
    private ArrayList<Wish> mWishes;
    private ArrayList<Integer> imageList;

    public Wishadapter(Context context, ArrayList<Wish> wishes, ArrayList<Integer> imageList) {
        this.context = context;
        mWishes = wishes;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public WishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_dashboard_row, parent, false);
        return new WishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishViewHolder holder, int position) {
        Wish wish = mWishes.get(position);
        holder.pfimage.setImageResource(wish.getProfileImage());
        holder.icon.setImageResource(wish.getIcon());
        holder.pfname.setText(wish.getFirstName());
        holder.content.setText(wish.getContent());
        holder.addbtn.setImageResource(wish.getBtnadd());

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the dialog when addbtn is clicked
                showDialog();
            }
        });

        // Create an ArrayList of emoji resources
        ArrayList<Integer> emojiList = new ArrayList<>();
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        emojiList.add(R.drawable.heart);
        emojiList.add(R.drawable.smile);
        // Add more emojis as needed

        // Create an instance of the emoji adapter and set it to the RecyclerView
        emoji emojiAdapter = new emoji(context, emojiList);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(emojiAdapter);
    }

    @Override
    public int getItemCount() {
        return mWishes.size();
    }

    public static class WishViewHolder extends RecyclerView.ViewHolder {
        ImageView pfimage, icon, addbtn;
        TextView pfname, content;
        RecyclerView recyclerView;

        public WishViewHolder(View itemView) {
            super(itemView);
            pfimage = itemView.findViewById(R.id.pfimage);
            icon = itemView.findViewById(R.id.icon);
            pfname = itemView.findViewById(R.id.pfname);
            content = itemView.findViewById(R.id.desc);
            recyclerView = itemView.findViewById(R.id.RcEmoji);
            addbtn = itemView.findViewById(R.id.btnadd);
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Use context here
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_image_gallery, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Set size of dialog window
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // Set width as needed
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // Set height as needed
        dialog.getWindow().setAttributes(layoutParams);

        dialog.show();
    }





}
