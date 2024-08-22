package facultyClasses;

public class WishRequest {

    private String content;
    private int emojiID;
    private Integer templeteID;
    private Integer achievID;
    private boolean isEmail;
    private String senderId;
    private String ReceiverId;
    private int Adid;
    private int Status;

    // Constructors, getters, and setters
    public WishRequest(String content, int emojiID, Integer templeteID, Integer achievID, boolean isEmail, String senderId, String ReceiverId, int Adid, int Status) {
        this.content = content;
        this.emojiID = emojiID;
        this.templeteID = templeteID;
        this.achievID = achievID;
        this.isEmail = isEmail;
        this.senderId = senderId;
        this.ReceiverId = ReceiverId;
        this.Adid = Adid;
        this.Status = Status;
    }

    // Getters and Setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getEmojiID() { return emojiID; }
    public void setEmojiID(int emojiID) { this.emojiID = emojiID; }

    public Integer getTempleteID() { return templeteID; }
    public void setTempleteID(Integer templeteID) { this.templeteID = templeteID; }

    public Integer getAchievID() { return achievID; }
    public void setAchievID(Integer achievID) { this.achievID = achievID; }

    public boolean isEmail() { return isEmail; }
    public void setEmail(boolean isEmail) { this.isEmail = isEmail; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return ReceiverId; }
    public void setReceiverId(String ReceiverId) { this.ReceiverId = ReceiverId; }

    public int getAdid() { return Adid; }
    public void setAdid(int Adid) { this.Adid = Adid; }

    public int getStatus() { return Status; }
    public void setStatus(int Status) { this.Status = Status; }
}
