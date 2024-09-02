package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.Emoji;
import facultyClasses.WishRequest;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ad_notification extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmojiRequestAdapter adapter;
    private List<Emoji> emojiRequestList = new ArrayList<>();
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






        recyclerView = findViewById(R.id.rcnotification);

        // Set up the RecyclerView
        adapter = new EmojiRequestAdapter(emojiRequestList, new EmojiRequestAdapter.OnItemClickListener() {
            @Override
            public void onPermitClick(Emoji emojiRequest) {
                // Handle permit action

                Toast.makeText(ad_notification.this, "Permit clicked: " + emojiRequest.getEsid(), Toast.LENGTH_SHORT).show();

                updateEmojiPermission(emojiRequest.getEsid(),null,"p",null);
                sendWish(emojiRequest.getRequsetedby(),emojiRequest.getRequestedfor(),emojiRequest.getEsid());


            }

            @Override
            public void onCancelClick(Emoji emojiRequest) {
                updateEmojiPermission(emojiRequest.getEsid(),null,"R",null);
                // Handle cancel action
                Toast.makeText(ad_notification.this, "Cancel clicked: " + emojiRequest.getEsid(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fetchEmojiRequests();







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



    private void updateEmojiPermission(int emojiId,String requestedBy,String permission,String requestedFor) {




        Log.d("API Call", "Updating emoji with ID: "  + ", permission: " + permission +
                ", requestedBy:  requestedFor: " );

        Apiservices apiservices=RetrofitClient.getInstance();
        apiservices.updateEmojiPermission(emojiId, permission, requestedBy, requestedFor).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("Update Emoji", "Emoji permission updated successfully");
                    Toast.makeText(ad_notification.this, "Permission request sent to admin", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("API Error", "Failed to update emoji permission. Response code: " + response.code());
                    Toast.makeText(ad_notification.this, "Failed to update permission", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Network Error", "Failed to update emoji permission", t);
                Toast.makeText(ad_notification.this, "Error updating permission", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void sendWish(String senderId, String receiverId, int emojiId) {
        Apiservices apiservices=RetrofitClient.getInstance();
        WishRequest wishRequest = new WishRequest(senderId, receiverId, emojiId);
        apiservices.sendSingleWish(wishRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ad_notification.this, "Wish sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ad_notification.this, "Failed to send wish", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API Error", t.getMessage());
                Toast.makeText(ad_notification.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchEmojiRequests() {
        Apiservices apiService = RetrofitClient.getInstance();
        Call<List<Emoji>> call = apiService.getEmojiRequests();

        call.enqueue(new Callback<List<Emoji>>() {
            @Override
            public void onResponse(Call<List<Emoji>> call, Response<List<Emoji>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    emojiRequestList.clear();
                    emojiRequestList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("API Error", "Error response: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Emoji>> call, Throwable t) {
                Log.d("API Failure", "Failed to fetch emoji requests", t);
            }
        });
    }
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