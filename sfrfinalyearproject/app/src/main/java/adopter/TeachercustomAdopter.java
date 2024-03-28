package adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import mydataapi.Apiservices;

public class TeachercustomAdopter extends RecyclerView.Adapter<TeachercustomAdopter.ViewHolder> implements Filterable {
    private Context mContext;
    private List<userdetail> mTeacherList;
    private List<userdetail> mFilteredTeacherList;
    private LayoutInflater mInflater;
    private Apiservices mAPIInterface;

    public TeachercustomAdopter(Context context, List<userdetail> teacherList, Apiservices apiInterface) {
        mContext = context;
        mTeacherList = teacherList;
        mFilteredTeacherList = new ArrayList<>(teacherList);
        mInflater = LayoutInflater.from(context);
        mAPIInterface = apiInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.teacherlistvies_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        userdetail user = mFilteredTeacherList.get(position);
        holder.textViewTitle.setText(user.getFname() + " " + user.getLname());
        // Set other data as needed
    }

    @Override
    public int getItemCount() {
        return mFilteredTeacherList.size();
    }

    @Override
    public Filter getFilter() {
        return new CustomFilter();
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            List<userdetail> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mTeacherList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (userdetail user : mTeacherList) {
                    if (user.getFname().toLowerCase().contains(filterPattern)) {
                        filteredList.add(user);
                    }
                }
            }
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredTeacherList.clear();
            mFilteredTeacherList.addAll((List<userdetail>) results.values);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        ImageView imageView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.proimage);
            textViewTitle = itemView.findViewById(R.id.txtname);
            imageView2 = itemView.findViewById(R.id.favimage);
        }
    }
}
