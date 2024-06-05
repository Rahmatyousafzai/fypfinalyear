package modelclassespost;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class EmojiTarget implements Target {



        private ImageView imageView;
        private String imageUrl;

        public EmojiTarget(ImageView imageView, String imageUrl) {
            this.imageView = imageView;
            this.imageUrl = imageUrl;
        }


        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imageView.setImageBitmap(bitmap);
        }


        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            // Handle the error
        }


        public void onPrepareLoad(Drawable placeHolderDrawable) {
            // Handle the placeholder
        }

    @Override
    public ElementType[] value() {
        return new ElementType[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
