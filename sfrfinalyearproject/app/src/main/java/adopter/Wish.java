package adopter;

public class Wish {
    private String firstName;
    private String lastName;
    private String profileImage;
    private String content;


    public Wish(String firstName, String lastName, String profileImage, String content) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.content = content;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getContent() {
        return content;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
