package modelclassespost;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

import com.example.sfrfinalyearproject.R;
import com.squareup.picasso.Picasso;

public class ZoomableImageDialog {



        public static void showDialog(Context context, String imageUrl) {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_zoomable_image);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            ImageView imageView = dialog.findViewById(R.id.zoomable_image_view);
            Picasso.get().load(imageUrl).into(imageView);

            dialog.show();
        }



}
