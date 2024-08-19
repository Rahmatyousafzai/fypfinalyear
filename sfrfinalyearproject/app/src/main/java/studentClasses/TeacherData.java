package studentClasses;

public class TeacherData {

        private String Username;
        private  String        FirstName;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getDisgnatione() {
        return disgnatione;
    }

    public void setDisgnatione(String disgnatione) {
        this.disgnatione = disgnatione;
    }

    private String        LastName;

    public TeacherData(String username, String firstName, String lastName, String profileImage, String dob, String disgnatione) {
        Username = username;
        FirstName = firstName;
        LastName = lastName;
        ProfileImage = profileImage;
        Dob = dob;
        this.disgnatione = disgnatione;
    }

    private String    ProfileImage;
    private String        Dob;
    private String        disgnatione;
    }

