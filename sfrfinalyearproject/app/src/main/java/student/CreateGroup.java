package student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sfrfinalyearproject.R;

import java.util.ArrayList;
import java.util.List;

import ModeClasees.user;
import mydataapi.Apiservices;
import mydataapi.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studentClasses.Group;
import studentClasses.GroupMember;
import studentClasses.UserDataSingleton;

public class CreateGroup extends AppCompatActivity {
    private Apiservices apiServices = RetrofitClient.getInstance();
    private EditText groupName, groupDescription, groupIcon;
    private RecyclerView recyclerView;
    private Button createGroupButton;
    private SelectUserAdapter adapter;
    private List<user> selectedUsers = new ArrayList<>();
    String name;
    String description;
    String icon;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        groupName = findViewById(R.id.group_name);
        groupDescription = findViewById(R.id.group_description);
        groupIcon = findViewById(R.id.group_icon);
        recyclerView = findViewById(R.id.recycler_view);
        createGroupButton = findViewById(R.id.create_group_button);


        username = UserDataSingleton.getInstance().getUsername();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectUserAdapter(new ArrayList<>(), this::onUserSelected);
        recyclerView.setAdapter(adapter);

       // loadTeachers();

        createGroupButton.setOnClickListener(v -> createGroup());
    }

 /*   private void loadTeachers() {
        apiServices.getAllTeachers().enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<user> teachers = response.body();
                    adapter.setTeacherList(teachers);
                }
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                // Handle the error
                Toast.makeText(CreateGroup.this, "Failed to load teachers", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/
    private void onUserSelected(user user, boolean isSelected) {
        if (isSelected) {
            selectedUsers.add(user);
        } else {
            selectedUsers.remove(user);
        }
    }

    private void createGroup() {
         name = groupName.getText().toString().trim(); // Retrieve group name
         description = groupDescription.getText().toString().trim();
         icon = groupIcon.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || icon.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Group group = new Group(name, description, icon); // Include group name
        // Set the current user as admin

        apiServices.createGroup(group).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    int groupId = getGroupIdFromResponse(response);
                    if (groupId != -1) {
                        for (user u : selectedUsers) {
                            GroupMember groupMember = new GroupMember();
                            groupMember.setGroupId(groupId);
                            groupMember.setGroupMemberId(u.getUsername());
                            groupMember.setMemberType("member");
                            addGroupMember(groupMember);
                        }
                        // Add the current user as admin
                        GroupMember adminMember = new GroupMember();
                        adminMember.setGroupId(groupId);
                        adminMember.setGroupMemberId(username);
                        adminMember.setMemberType("admin");
                        addGroupMember(adminMember);

                        Toast.makeText(CreateGroup.this, "Group created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateGroup.this, "Failed to create group", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CreateGroup.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void addGroupMember(GroupMember groupMember) {
        apiServices.addGroupMember(groupMember).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CreateGroup.this, "Failed to add member to group", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CreateGroup.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Implement this method to extract groupId from the response
    private int getGroupIdFromResponse(Response<Void> response) {
        if (response.headers().get("Location") != null) {
            // Extract the group ID from the location header
            String locationHeader = response.headers().get("Location");
            String[] parts = locationHeader.split("/");
            if (parts.length > 0) {
                try {
                    return Integer.parseInt(parts[parts.length - 1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

}
