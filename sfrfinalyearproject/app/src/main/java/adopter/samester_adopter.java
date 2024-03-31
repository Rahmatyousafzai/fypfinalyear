package adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class samester_adopter extends RecyclerView.Adapter<samester_adopter.MyviewHolder>{

    private final List<student_show_samester> data;
    List<student_show_samester> Data;
    public static class MyviewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MyviewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.customcheckBox);
        }
    }

    public samester_adopter(List<student_show_samester> data) {
        this.data = data;
    }
    @NonNull
    
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkboxw_item_rows, parent, false);
        return new MyviewHolder(view);
    }

    public void onBindViewHolder(MyviewHolder holder, int position) {
        student_show_samester item = data.get(position);
        holder.checkBox.setText(item.getName());
        holder.checkBox.setChecked(item.isChecked());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
