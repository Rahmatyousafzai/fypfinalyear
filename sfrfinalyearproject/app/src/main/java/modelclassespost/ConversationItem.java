package modelclassespost;

public class ConversationItem {
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(int emojiId) {
        this.emojiId = emojiId;
    }

    private String senderId;

    public ConversationItem(String senderId, String receiverId, int emojiId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.emojiId = emojiId;
    }

    private String receiverId;
    private int emojiId;
    private String senderUsername;

    public ConversationItem(String senderUsername, String receiverUsername, String emojiData) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.emojiData = emojiData;
    }

    private String receiverUsername;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getEmojiData() {
        return emojiData;
    }

    public void setEmojiData(String emojiData) {
        this.emojiData = emojiData;
    }

    private String emojiData;



}
