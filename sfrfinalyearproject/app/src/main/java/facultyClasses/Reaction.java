package facultyClasses;

public class Reaction {


    public  Reaction(){


    }
    private int count; // Or the appropriate data type

    // Getter for count
    public int getCount() {
        return count;
    }

    // Setter for count (if needed)
    public void setCount(int count) {
        this.count = count;
    }
    private String reacterID;
    private int sw_id;
    private int emojiID;

    public String getImagedata() {
        return imagedata;
    }

    public void setImagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    private String imagedata;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    private String datetime;
    private String fname;
    private String lname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getProfileimage() {
        return profileimage;
    }



    private String profileimage;
    public Reaction(String reacterID, int sw_id, int emojiID) {
        this.reacterID = reacterID;
        this.sw_id = sw_id;
        this.emojiID = emojiID;
    }

    // Getters and setters
    public String getReacterID() {
        return reacterID;
    }

    public void setReacterID(String reacterID) {
        this.reacterID = reacterID;
    }

    public int getSw_id() {
        return sw_id;
    }

    public void setSw_id(int sw_id) {
        this.sw_id = sw_id;
    }

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }
}
