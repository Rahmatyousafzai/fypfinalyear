package adopter;

public class userdetail {

    private String UserName;
    private String FirstName;
    private String LastName;
    private String ImagePath;


    public String getUserName() {
        return UserName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public userdetail(String userName, String firstName, String lastName, String imagePath) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        ImagePath = imagePath;
    }
}
