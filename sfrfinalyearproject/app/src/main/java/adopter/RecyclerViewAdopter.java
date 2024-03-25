package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.MyDataModel;
import com.example.sfrfinalyearproject.R;

import java.util.List;

public class RecyclerViewAdopter extends RecyclerView.Adapter<RecyclerViewAdopter.Recyclerviewholder>{


private List<String> mData;

    private List<MyDataModel> mDataList;
    private Context mContext;

    public RecyclerViewAdopter(Context context, List<MyDataModel> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    public RecyclerViewAdopter(List<Wish> wishes) {
    }

    @NonNull
    @Override
    public Recyclerviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_dashboard_row, parent, false);
        return new Recyclerviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerviewholder holder, int position) {
        MyDataModel currentItem = mDataList.get(position);

        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textViewName.setText(currentItem.getName());
        holder.textViewDesc.setText(currentItem.getDescription());
        holder.em1.setImageResource(currentItem.getIm1());
        holder.em2.setImageResource(currentItem.getI2());
        holder.em3.setImageResource(currentItem.getIm3());
        holder.em4.setImageResource(currentItem.getIm4());
        holder.em5.setImageResource(currentItem.getIm5());
        holder.em6.setImageResource(currentItem.getIm6());
        holder.em7.setImageResource(currentItem.getIm7());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class Recyclerviewholder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewDesc;
        public ImageView em1;
        public ImageView em2;
        public ImageView em3;
        public ImageView em4;
        public ImageView em5;
        public ImageView em6;
        public ImageView em7;

        public Recyclerviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pfimage);
            textViewName = itemView.findViewById(R.id.pfname);
            textViewDesc = itemView.findViewById(R.id.desc);
            em1 = itemView.findViewById(R.id.imj1);
            em2 = itemView.findViewById(R.id.imj2);
            em3 = itemView.findViewById(R.id.imj3);
            em4 = itemView.findViewById(R.id.imj4);
            em5 = itemView.findViewById(R.id.imj5);
            em6 = itemView.findViewById(R.id.imj6);
            em7 = itemView.findViewById(R.id.imj7);
        }



    }
}
