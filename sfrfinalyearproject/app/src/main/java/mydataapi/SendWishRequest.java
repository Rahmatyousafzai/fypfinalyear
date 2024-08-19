package mydataapi;

public class SendWishRequest {
    private Integer emojiId;

    public String getSenderid() {
        return senderid;
    }

    public SendWishRequest(Integer emojiId, String content, String senderid, String messageType) {
        this.emojiId = emojiId;
        this.content = content;
        this.senderid = senderid;
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

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



    // Getter and setter methods
}
