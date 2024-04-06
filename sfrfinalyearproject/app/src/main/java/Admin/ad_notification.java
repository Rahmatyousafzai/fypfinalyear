package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class ad_notification extends AppCompatActivity {

    TextView news,message,notificat,profilename;
    ImageView imgnews,imgmessage,imgnotification,profileimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_notification);
        news=findViewById(R.id.news);
        message=findViewById(R.id.txtmessage);
        notificat=findViewById(R.id.txtnotification);
        profileimage=findViewById(R.id.profileimage);
        profilename=findViewById(R.id.profelname);

   ///////////////////////////////////////////////////////////////////////////////
   //////////////////////Click Listner///////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
         news.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        news();
    }
});
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adMessage();
            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
notification();
            }
        });





    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////FUNCTION///////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    public void notification() {
        // Assuming PROFILE_NAME_ID is a string containing the name and profileImage is an integer representing the drawable resource ID
        Intent intent = new Intent(ad_notification.this, ad_notification.class);
        intent.putExtra("username", profilename.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24); // Replace with the appropriate drawable resource ID
        startActivity(intent);
        finish();
    }


    public void adMessage() {
        Intent intent = new Intent(ad_notification.this, Admin.admessage.class);
        intent.putExtra("username", profilename.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }
    public void news() {
        Intent intent = new Intent(ad_notification.this, ad_dashboard.class);
        intent.putExtra("username", profilename.getText().toString());
        intent.putExtra("image", R.drawable.baseline_account_circle_24);
        startActivity(intent);
        finish();
    }


}