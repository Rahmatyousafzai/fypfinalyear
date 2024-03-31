package adopter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

public class Recyclerviewholder extends RecyclerView.ViewHolder {

    ImageView imgview ,em1,em2,em3,em4,em5,em6,em7;
    TextView txtname ,txtdesc;


    public Recyclerviewholder(@NonNull View itemView) {
        super(itemView);
        imgview=itemView.findViewById(R.id.pfimage);
        txtname=itemView.findViewById(R.id.pfname);
        txtdesc=itemView.findViewById(R.id.desc);





    }
}
