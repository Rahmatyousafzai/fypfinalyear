package ModeClasees;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("senderUsername")
private String senderUsername;

    @SerializedName("senderFirstName")
    private String senderFirstName;

    @SerializedName("senderLastName")
    private String senderLastName;

    @SerializedName("senderProfileImage")
    private String senderProfileImage;

    @SerializedName("receiverUsername")
    private String receiverUsername;

    @SerializedName("receiverFirstName")
    private String receiverFirstName;

    @SerializedName("receiverLastName")
    private String receiverLastName;

    @SerializedName("receiverProfileImage")
    private String receiverProfileImage;

    @SerializedName("emojiData")
    private String emojiData;

    @SerializedName("wishDateTime")
    private String wishDateTime;

    // Getters and setters
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
    }}