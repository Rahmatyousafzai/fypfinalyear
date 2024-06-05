package adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;

    private List<Object> itemList;

    public CustomAdapter(List<Object> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = itemList.get(position);
        if (item instanceof Type1Object) {
            return TYPE_1;
        } else if (item instanceof Type2Object) {
            return TYPE_2;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case TYPE_1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_type_1, parent, false);
                return new Type1ViewHolder(itemView);
            case TYPE_2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_type_2, parent, false);
                return new Type2ViewHolder(itemView);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_1:
                ((Type1ViewHolder) holder).bind((Type1Object) item);
                break;
            case TYPE_2:
                ((Type2ViewHolder) holder).bind((Type2Object) item);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Define Type1ViewHolder
    public static class Type1ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_type_1);
        }

        public void bind(Type1Object type1Object) {
            textView.setText(type1Object.getText());
        }
    }

    // Define Type2ViewHolder
    public static class Type2ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public Type2ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_type_2);
        }

        public void bind(Type2Object type2Object) {
            imageView.setImageResource(type2Object.getImageResource());
        }
    }
}
