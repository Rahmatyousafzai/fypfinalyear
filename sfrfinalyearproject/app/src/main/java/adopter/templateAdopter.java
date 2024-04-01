package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class templateAdopter extends RecyclerView.Adapter<templateAdopter.ViewHolder> {
        private List<String> data;
        private Context context;

        public templateAdopter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templete_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.templateTextView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView templateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            templateTextView = itemView.findViewById(R.id.template);
        }
    }


}
