package Admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sfrfinalyearproject.R;

public class postbody extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postbody);

        // Initialize the dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnshare=findViewById(R.id.pshare);
        // Find buttons in the dialog layout
        Button buttonYes = dialog.findViewById(R.id.button_yes);
        Button buttonCancel = dialog.findViewById(R.id.button_cancel);

        ///////////////////////////////////////////////////////////////////////////////
        ////////////////////////////onclickListener////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Yes" button click
                // For example, you can proceed with an action or dismiss the dialog
                navigateTodhashboard(); // Dismiss the dialog
                // Your code for "Yes" button action
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Cancel" button click
                dialog.dismiss(); // Dismiss the dialog
                // Your code for "Cancel" button action
            }
        });
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////METHODS////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    private void navigateTodhashboard() {

        Intent intent=new Intent(postbody.this,ad_dashboard.class);
        startActivity(intent);
        finish();
    }
}