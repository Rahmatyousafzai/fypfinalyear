package facultyClasses;

public class publicWish {
    private String content;


    public publicWish() {
    }

    public publicWish(String content, Integer emojiID, Integer templeteID, Integer achievID, String dateTime, Boolean isEmail) {
        this.content = content;
        this.emojiID = emojiID;
        this.templeteID = templeteID;
        this.achievID = achievID;
        DateTime = dateTime;
        this.isEmail = isEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(Integer emojiID) {
        this.emojiID = emojiID;
    }

    public Integer getTempleteID() {
        return templeteID;
    }

    public void setTempleteID(Integer templeteID) {
        this.templeteID = templeteID;
    }

    public Integer getAchievID() {
        return achievID;
    }

    public void setAchievID(Integer achievID) {
        this.achievID = achievID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public Boolean getEmail() {
        return isEmail;
    }

    public void setEmail(Boolean email) {
        isEmail = email;
    }

    private Integer emojiID;
    private Integer templeteID;
    private Integer achievID;
    private String DateTime;
    private Boolean isEmail;
}
