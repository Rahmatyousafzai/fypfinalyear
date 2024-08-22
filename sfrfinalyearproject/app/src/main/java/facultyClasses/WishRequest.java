package facultyClasses;

public class WishRequest {


    private String senderId;

    public WishRequest(String senderId, String receiverId, String content, Integer emojiID, Integer templeteID, Integer achievID, boolean isEmail, Integer adid, Integer status) {
        this.senderId = senderId;
        ReceiverId = receiverId;
        this.content = content;
        EmojiID = emojiID;
        this.templeteID = templeteID;
        this.achievID = achievID;
        this.isEmail = isEmail;
        Adid = adid;
        Status = status;
    }

    private String ReceiverId;

    private String    content;
        private Integer  EmojiID ;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String receiverId) {
        ReceiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getEmojiID() {
        return EmojiID;
    }

    public void setEmojiID(Integer emojiID) {
        EmojiID = emojiID;
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

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public Integer getAdid() {
        return Adid;
    }

    public void setAdid(Integer adid) {
        Adid = adid;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    private Integer templeteID;
        private Integer achievID;
        private boolean isEmail;
        private Integer Adid;
        private Integer Status;
    }

