package ModeClasees;

public class Message {






    private String profileImageUrl;
    private String firstName;
    private String lastName;
    private String content;

    public Message(String profileImageUrl, String firstName, String lastName, String content) {
        this.profileImageUrl = profileImageUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContent() {
        return content;
    }
    private String senderUsername;
    private String senderFirstName;
    private String senderLastName;

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
        return Emojidata;
    }

    public void setEmojiData(String emojiData) {
        this.Emojidata = emojiData;
    }

    public String getWishDateTime() {
        return wishDateTime;
    }

    public void setWishDateTime(String wishDateTime) {
        this.wishDateTime = wishDateTime;
    }

    private String senderProfileImage;
    private String receiverUsername;
    private String receiverFirstName;

    public Message(String senderUsername, String senderFirstName, String senderLastName, String senderProfileImage, String receiverUsername, String receiverFirstName, String receiverLastName, String receiverProfileImage, String emojiData, String wishDateTime) {
        this.senderUsername = senderUsername;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderProfileImage = senderProfileImage;
        this.receiverUsername = receiverUsername;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        this.receiverProfileImage = receiverProfileImage;
        this.Emojidata = emojiData;
        this.wishDateTime = wishDateTime;
    }

    private String receiverLastName;
    private String receiverProfileImage;
    private String Emojidata;
    private String wishDateTime;



}