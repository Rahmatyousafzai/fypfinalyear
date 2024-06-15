package Faculty;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.List;

import ModeClasees.Message;
import adopter.MessagListAdopter;
import facultyClasses.message;
import modelclassespost.ConversationAdapter;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;

public class ft_message extends AppCompatActivity {


    private List<message> messageList1;
    private List<message> messageList2;
    private PopupWindow popupWindowCreateGroup;
    private EditText editGroupName, editDescription;
    private boolean isMessageList1Visible = true;

    ImageView messageImage, groupMessage, createGroup;





    private Apiservices apiServices = RetrofitClient.getInstance();
    RecyclerView recyclerView;

    private GridView gridView;
    private ConversationAdapter chatAdapter;
    private List<Message> messageList;
    TextView profilename, pfimageofteacher, tcname;
    ImageView profile, profileImageView, Emojipapolate;
    private String username, FullName, profileImage, TeacherUsername, teacher_lastname, teacher_firstname, teacher_username, TCfullname;
    private LinearLayout linearLayout;
    private MessagListAdopter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ft_message);

        // Initialize views
        messageImage = findViewById(R.id.message);
        groupMessage = findViewById(R.id.group);
        createGroup = findViewById(R.id.creategroup);
        createGroup.setVisibility(View.GONE);
        LinearLayout linearLayout = findViewById(R.id.search);

        recyclerView = findViewById(R.id.Rcmassage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Set OnClickListener for the groupMessage button
        groupMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle between the two lists
                if (isMessageList1Visible) {

                } else {

                }
                // Toggle the visibility of createGroup
                createGroup.setVisibility(isMessageList1Visible ? View.VISIBLE : View.GONE);
                isMessageList1Visible = !isMessageList1Visible;
            }
        });

        // Set OnClickListener for the messageImage (Message) ImageView
        messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show messageList1 when messageImage is clicked

                // Make sure createGroup is hidden
                createGroup.setVisibility(View.GONE);
                // Update visibility flag
                isMessageList1Visible = true;
                recyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        // Set OnClickListener for the createGroup ImageView
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show popup window for creating a group
                showCreateGroupPopup();


                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                createGroup.setVisibility(View.GONE);
            }
        });


        // Set item click listener for the adapter
    }


    // Utility class for generating sample data





    // Method to show the popup window for creating a group
    private void showCreateGroupPopup() {
        // Inflate the layout for the popup window
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_create_group, null);

        // Initialize views in the popup layout
        editGroupName = popupView.findViewById(R.id.editGroupName);
        editDescription = popupView.findViewById(R.id.editDescription);
        Button btnCreateGroup = popupView.findViewById(R.id.btnCreateGroup);
        ImageView btnAddUsers = popupView.findViewById(R.id.adduser); // Assuming you have an ImageView for adding users

        // Set OnClickListener for the btnCreateGroup
        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on Create Group button click
                String groupName = editGroupName.getText().toString().trim();
                String description = editDescription.getText().toString().trim();

                // Here, you can handle the creation of the group using the provided groupName and description
                // For demonstration purpose, I'm just dismissing the popup window
                popupWindowCreateGroup.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Create the popup window for creating a group
        popupWindowCreateGroup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // Set background color and animation style for the popup window
        popupWindowCreateGroup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popupWindowCreateGroup.setAnimationStyle(R.style.PopupAnimation);

        // Show the popup window
        popupWindowCreateGroup.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
    public void onGroupIconClicked(View view) {
        // Handle click event for selecting group icon
        // For demonstration purpose, let's open a dialog to choose an icon

        // Inflate the layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_icon, null);

        // Find the ImageView in the dialog layout
        final ImageView imgGroupIcon = dialogView.findViewById(R.id.imgGroupIcon);

        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Group Icon");
        builder.setView(dialogView);

        // Add a list of icons to choose from
        final String[] iconOptions = {"Gallery", "camra", "cancel"}; // Add your icon names here
        builder.setItems(iconOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Set the selected icon in the ImageView
                switch (which) {
                    case 0:
                        imgGroupIcon.setImageResource(R.drawable.heart);
                        break;
                    case 1:
                        imgGroupIcon.setImageResource(R.drawable.happy);
                        break;
                    case 2:
                        imgGroupIcon.setImageResource(R.drawable.saeed);
                        break;
                    // Add more cases for additional icons
                }
            }
        });

        // Display the dialog
        builder.show();
    }


}
