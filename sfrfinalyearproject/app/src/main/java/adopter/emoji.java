package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;

public class emoji extends RecyclerView.Adapter<emoji.ViewHolder> {
    private Context context;
    private ArrayList<Integer> imageList;

    public emoji(Context context, ArrayList<Integer> imageList) {
       this.context = context;
       this.imageList = imageList;
    }

   

    @NonNull
    @Override
    public emoji.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imoji_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int emojiImageResource = imageList.get(position);
        holder.Emojiimg.setImageResource(emojiImageResource);
    }

   
    @Override
    public int getItemCount() {
        return imageList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Emojiimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Emojiimg=itemView.findViewById(R.id.emojimage);
        }
    }
}
