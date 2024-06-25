package dashboardclasese;

public class wishingclass {

         private String Username;
    private String        FirstName;

    public wishingclass(String username, String firstName, String lastName, String profileImage, String content, String dateTime, String swId) {
        Username = username;
        FirstName = firstName;
        LastName = lastName;
        ProfileImage = profileImage;
        Content = content;
        this.dateTime = dateTime;
        SwId = swId;
    }

    private String      LastName;
    private String ProfileImage;

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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSwId() {
        return SwId;
    }

    public void setSwId(String swId) {
        SwId = swId;
    }

    private String Content;
    private String dateTime;
    private String SwId;
}
