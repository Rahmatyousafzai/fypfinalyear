package facultyClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sfrfinalyearproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mydataapi.RetrofitClient;

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.ReactionViewHolder> {
    private Context context;
    private List<Reaction> reactionList;

    public ReactionAdapter(Context context, List<Reaction> reactionList) {
        this.context = context;
        this.reactionList = reactionList;
    }




    @NonNull
    @Override
    public ReactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reaction, parent, false);
        return new ReactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactionViewHolder holder, int position) {
        Reaction reaction = reactionList.get(position);
        holder.txtName.setText(reaction.getFname() + " " + reaction.getLname());

        if (reaction.getDatetime() != null && !reaction.getDatetime().isEmpty()) {
            // Define date and time formats
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            // Define the expected input format
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

            try {
                // Parse the datetime string
                Date dateTime = inputFormat.parse(reaction.getDatetime());
                if (dateTime != null) {
                    // Format the date and time
                    String formattedDate = dateFormat.format(dateTime);
                    String formattedTime = timeFormat.format(dateTime);

                    // Set the formatted date and time to the TextViews
                    holder.txtDate.setText(formattedDate);
                   // holder.txtTime.setText(formattedTime);
                } else {
                    // Handle the case where parsing results in null
                    holder.txtDate.setText("Invalid date");
                    //holder.txtTime.setText("Invalid time");
                }
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parse exception
                holder.txtDate.setText("Invalid date");
                //holder.txtTime.setText("Invalid time");
            }
        } else {
            // Handle the case where datetime is null or empty
            holder.txtDate.setText("No date available");
            //holder.txtTime.setText("No time available");
        }





        // Load profile image
        Glide.with(context)
                .load(RetrofitClient.getBaseUrl() + "images/profileimages/" + reaction.getProfileimage() + ".jpg")
                .placeholder(R.drawable.baseline_account_circle_24) // Default placeholder image
                .into(holder.imgProfile);

        // Load emoji image
        Glide.with(context)
                .load(RetrofitClient.getBaseUrl() + "images/emojis/" + reaction.getImagedata() + ".png")
                .placeholder(R.drawable.baseline_account_circle_24) // Default placeholder emoji
                .into(holder.imgEmoji);
    }

    @Override
    public int getItemCount() {
        return reactionList.size();
    }

    public static class ReactionViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        ImageView imgEmoji;
        TextView txtName;
        TextView txtDate;

        public ReactionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            imgEmoji = itemView.findViewById(R.id.imgEmoji);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
