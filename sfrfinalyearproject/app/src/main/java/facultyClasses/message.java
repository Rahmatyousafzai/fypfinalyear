package facultyClasses;

public class message {


    private String senderUsername;
    private String senderFirstName;
    private String senderLastName;

    public message(String senderUsername, String senderFirstName, String senderLastName, String senderProfileImage, String receiverUsername, String receiverFirstName, String receiverLastName, String receiverProfileImage, String emojiData, String wishDateTime) {
        this.senderUsername = senderUsername;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderProfileImage = senderProfileImage;
        this.receiverUsername = receiverUsername;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        this.receiverProfileImage = receiverProfileImage;
        this.emojiData = emojiData;
        this.wishDateTime = wishDateTime;
    }

    private String senderProfileImage;
    private String receiverUsername;
    private String receiverFirstName;
    private String receiverLastName;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getSenderProfileImage() {
        return senderProfileImage;
    }

    public void setSenderProfileImage(String senderProfileImage) {
        this.senderProfileImage = senderProfileImage;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }

    public String getReceiverLastName() {
        return receiverLastName;
    }

    public void setReceiverLastName(String receiverLastName) {
        this.receiverLastName = receiverLastName;
    }

    public String getReceiverProfileImage() {
        return receiverProfileImage;
    }

    public void setReceiverProfileImage(String receiverProfileImage) {
        this.receiverProfileImage = receiverProfileImage;
    }

    public String getEmojiData() {
        return emojiData;
    }

    public void setEmojiData(String emojiData) {
        this.emojiData = emojiData;
    }

    public String getWishDateTime() {
        return wishDateTime;
    }

    public void setWishDateTime(String wishDateTime) {
        this.wishDateTime = wishDateTime;
    }

    private String receiverProfileImage;
    private String emojiData;
    private String wishDateTime;

}