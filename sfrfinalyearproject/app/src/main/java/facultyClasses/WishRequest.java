package facultyClasses;

public class WishRequest {


    private String senderId;

    public WishRequest(String senderId, String reciverID, int emojiID) {
        this.senderId = senderId;
        ReciverID = reciverID;
        this.emojiID = emojiID;
        this.forword = forword;
        this.content = content;
    }

    private String ReciverID;
    private int emojiID;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReciverID() {
        return ReciverID;
    }

    public void setReciverID(String reciverID) {
        ReciverID = reciverID;
    }

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }

    public Integer getForword() {
        return forword;
    }

    public void setForword(Integer forword) {
        this.forword = forword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Integer forword;
    private String content;
    }

