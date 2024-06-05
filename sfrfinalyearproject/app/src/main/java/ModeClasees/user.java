package ModeClasees;

import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("fname")
    private String firstName;

    @SerializedName("lname")
    private String lastName;

    @SerializedName("DOB")
    private String dob;

    @SerializedName("profileimage")
    private String profileImage;

    @SerializedName("email")
    private String email;

    @SerializedName("usertype")
    private String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Other fields can be added as needed

    // Getters and setters
    // ...





}
