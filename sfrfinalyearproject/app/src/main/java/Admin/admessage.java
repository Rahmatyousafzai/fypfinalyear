package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

public class admessage extends AppCompatActivity {

    RecyclerView recyclerView;
    private TextView adNews, adMessage, addnotification;
ImageView imgback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admessage);

        adNews=findViewById(R.id.news);
        adMessage=findViewById(R.id.txtmessage);
        addnotification=findViewById(R.id.txtnotification);
        addnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             notification();
            }
        });
        adNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
news();
            }
        });
        adMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                adMessage();
            }
        });






    }



    public void notification() {
        Intent intent = new Intent(admessage.this, Admin.ad_notification.class);
        startActivity(intent);
        finish();
    }
    public void adMessage() {
        Intent intent = new Intent(admessage.this, Admin.admessage.class);
        startActivity(intent);
        finish();
    }

    public void news() {
        Intent intent = new Intent(admessage.this, ad_dashboard.class);
        startActivity(intent);
        finish();
    }
}