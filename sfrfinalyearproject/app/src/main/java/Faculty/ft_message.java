package Faculty;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import facultyClasses.message;
import facultyClasses.messageAdopter;

public class ft_message extends AppCompatActivity {
    private RecyclerView recyclerView;
    private messageAdopter userAdapter;
    private List<message> messageList1;
    private List<message> messageList2;
    private PopupWindow popupWindowCreateGroup;
    private EditText editGroupName, editDescription;
    private boolean isMessageList1Visible = true;

    ImageView messageImage, groupMessage, createGroup;

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

        // Generate sample data lists
        messageList1 = UserUtil.generateSampleMessages();
        messageList2 = UserUtil.generateDifferentSampleMessages();
        showData(messageList1);

        // Set OnClickListener for the groupMessage button
        groupMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle between the two lists
                if (isMessageList1Visible) {
                    showData(messageList2);
                } else {
                    showData(messageList1);
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
                showData(messageList1);
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
        userAdapter.setOnItemClickListener(new messageAdopter.OnItemClickListener() {
            @Override
            public void onItemClick(String userName, int imageResource) {
                // Handle item click here
                Intent intent = new Intent(ft_message.this, groupmessage_body.class);
                intent.putExtra("userName", userName);
                intent.putExtra("imageResource", imageResource);
                startActivity(intent);
            }
        });

    }

    // Method to display data in the RecyclerView
    private void showData(List<message> dataList) {
        // Create and set the adapter with the provided data list
        userAdapter = new messageAdopter(dataList);
        recyclerView.setAdapter(userAdapter);

    }


    // Utility class for generating sample data
    public static class UserUtil {
        public static List<message> generateSampleMessages() {
            List<message> userList = new ArrayList<>();
            userList.add(new message("Rahmat", "yousafzai", "12/09/2024", R.drawable.rahmatpic));
            userList.add(new message("Khurram", "Qaser", "12/09/2024", R.drawable.zindalash));
            userList.add(new message("Jaweria", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("Rahmat", "yousafzai", "12/09/2024", R.drawable.rahmatpic));
            userList.add(new message("Khurram", "Qaser", "12/09/2024", R.drawable.zindalash));
            userList.add(new message("Jaweria", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("Rahmat", "yousafzai", "12/09/2024", R.drawable.rahmatpic));
            userList.add(new message("Khurram", "Qaser", "12/09/2024", R.drawable.zindalash));
            userList.add(new message("Jaweria", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("Rahmat", "yousafzai", "12/09/2024", R.drawable.rahmatpic));
            userList.add(new message("Khurram", "Qaser", "12/09/2024", R.drawable.zindalash));
            userList.add(new message("Jaweria", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            return userList;
        }

        public static List<message> generateDifferentSampleMessages() {
            List<message> userList = new ArrayList<>();
            userList.add(new message("muhammad", "bilal", "12/09/2024", R.drawable.bilal));
            userList.add(new message("yousafzai", "Doe", "12/09/2024", R.drawable.care));
            userList.add(new message("Alice", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("muhammad", "bilal", "12/09/2024", R.drawable.bilal));
            userList.add(new message("yousafzai", "Doe", "12/09/2024", R.drawable.care));
            userList.add(new message("Alice", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("muhammad", "bilal", "12/09/2024", R.drawable.bilal));
            userList.add(new message("yousafzai", "Doe", "12/09/2024", R.drawable.care));
            userList.add(new message("Alice", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed
            userList.add(new message("muhammad", "bilal", "12/09/2024", R.drawable.bilal));
            userList.add(new message("yousafzai", "Doe", "12/09/2024", R.drawable.care));
            userList.add(new message("Alice", "Smith", "12/09/2024", R.drawable.baseline_account_circle_24));
            // Add more sample data as needed


            return userList;
        }
    }

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
