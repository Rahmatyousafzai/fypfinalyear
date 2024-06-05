package mydataapi;

public class SendWishRequest {
    private int emojiId;

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    private String senderid;
    private String messageType; // Assuming messageType serves as the receiver ID

    public int getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(int emojiId) {
        this.emojiId = emojiId;
    }





    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public SendWishRequest(String senderid, String messageType, int emojiId) {
        this.senderid = senderid;
        this.messageType = messageType;
        this.emojiId = emojiId;
    }

    // Getter and setter methods
}
