package ModeClasees;

import com.google.gson.annotations.SerializedName;

public class cuTeacher {


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public cuTeacher(String firstName, String lastName, String profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }

    @SerializedName("FirstName")
        private String firstName;

        @SerializedName("LastName")
        private String lastName;

        @SerializedName("ProfileImage")
        private String profileImage;

    public String getUsername() {
        return null;
    }

    // Constructor, getters, and setters
    }


