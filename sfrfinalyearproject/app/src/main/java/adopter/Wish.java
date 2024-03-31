package adopter;

import java.util.ArrayList;

public class Wish {

    private String firstName;

    private int profileImage;
    private int icon;
    private String content;
    private ArrayList<emoji_class> imageList;

    public Wish(String firstName, int profileImage, int icon, String content, ArrayList<emoji_class> imageList) {
        this.firstName = firstName;
        this.profileImage = profileImage;
        this.icon = icon;
        this.content = content;
        this.imageList = imageList;
    }

    public String getFirstName() {
        return firstName;
    }



    public int getProfileImage() {
        return profileImage;
    }

    public String getContent() {
        return content;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getIcon() {
        return icon;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<emoji_class> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<emoji_class> imageList) {
        this.imageList = imageList;
    }

    public void notifyDatsetchange() {
    }
}
