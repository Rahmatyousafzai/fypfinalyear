package dashboardclasese;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import mydataapi.RetrofitClient;

public class wishingadopter extends RecyclerView.Adapter<wishingadopter.ViewHolder> {

    private List<wishingclass> wishes;
    private Context context;

    public wishingadopter(Context context, List<wishingclass> wishes) {
        this.context = context;
        this.wishes = wishes;
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
        holder.pfname.setText(wish.getUsername());
        holder.txtContent.setText(wish.getContent());


        holder.txtDateTime.setText("");

String Firstname=wish.getFirstName();
String lastname=wish.getLastName();

String Fullname=Firstname+" "+ lastname;














        holder.pfname.setText(Fullname);


        if (wish.getDateTime() != null) {
            // Convert int day to String before setting to TextView
            holder.txtDateTime.setText(String.valueOf(wish.getDateTime()));
        } else {
            holder.txtDateTime.setText("N/A"); // Or any default text if DateTime is null
        }

        if (wish.getProfileImage() != null && !wish.getProfileImage().isEmpty()) {
            String imageUrl = RetrofitClient.getBaseUrl() + "images/profileimages/" + wish.getProfileImage() + ".jpg";

            Picasso.get()
                    .load(imageUrl)
                    .error(R.drawable.baseline_account_circle_24)
                    .into(holder.profile, new Callback() {
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

        // Set other fields as needed
    }


    public void setData(List<wishingclass> wishes) {
        this.wishes = wishes;
        notifyDataSetChanged(); // Notify adapter that dataset has changed
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent;
        TextView txtDateTime,pfname;
        ImageView profile;

        // Add other views as needed

        public ViewHolder(View itemView) {
            super(itemView);
            pfname= itemView.findViewById(R.id.pfname);
            profile = itemView.findViewById(R.id.senderimage);
            txtDateTime = itemView.findViewById(R.id.dateTime);
            txtContent = itemView.findViewById(R.id.wishcontent);
            // Initialize other views
        }
    }
}
