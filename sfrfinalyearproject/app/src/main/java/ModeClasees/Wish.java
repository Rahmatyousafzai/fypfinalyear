// Wish.java
package ModeClasees;

import java.util.List;

public class Wish {



    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    private String LastName;
  private String Username;
        private String   FirstName;

    private String ProfileImage;
    private int SwId;


    public String getWishDateTime() {
        return WishDateTime;
    }

    public void setWishDateTime(String wishDateTime) {
        WishDateTime = wishDateTime;
    }

    private String       WishDateTime;

    public Wish(String username, String firstName, String profileImage, int swId, String content, String title, List<Emoji> emojis, int swid ,String WishDateTime) {
        Username = username;
        FirstName = firstName;
        ProfileImage = profileImage;
        SwId = swId;
        Content = content;
        Title = title;
        this.emojis = emojis;
        this.swid = swid;
        WishDateTime=WishDateTime;
    }

    private  String Content;

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

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public int getSwId() {
        return SwId;
    }

    public void setSwId(int swId) {
        SwId = swId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getSwid() {
        return swid;
    }

    public void setSwid(int swid) {
        this.swid = swid;
    }

    private  String Title;


    private List<Emoji> emojis;


    private int swid;


    public List<Emoji> getEmojis() {
        return emojis;
    }

    public void setEmojis(List<Emoji> emojis) {
        this.emojis = emojis;
    }



}
