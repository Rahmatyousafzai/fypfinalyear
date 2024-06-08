package modelclassespost;

public class ConversationItem {

    private String SenderUsername;
    private String SenderFirstName;
    private String SenderLastName;
    private String SenderProfileImage;
    private String Reciverprofile;
    private String ReceiverUsername;
    private String ReceiverFirstName;
    private String ReceiverLastName;
    private String ReceiverProfileImage;
    private String Emojidata;
    private String WishDateTime;

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }

    private int emojiID;

    public ConversationItem(String senderUsername,
                            String receiverUsername,
                            String receiverProfileImage,
                            String senderProfileImage,
                            String emojiData) {

        this.SenderUsername=senderUsername;
        this.ReceiverUsername=receiverUsername;
        this.ReceiverProfileImage=receiverProfileImage;
        this.SenderProfileImage=senderProfileImage;
        this.Emojidata=emojiData;




    }

    public ConversationItem(String studentusername, String teacherUsername, int selectedEmoji) {

   this.SenderUsername=studentusername;
   this.ReceiverUsername=teacherUsername;
   this.emojiID=selectedEmoji;


    }

    public String getSenderUsername() {
        return SenderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        SenderUsername = senderUsername;
    }

    public String getSenderFirstName() {
        return SenderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        SenderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return SenderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        SenderLastName = senderLastName;
    }

    public String getSenderProfileImage() {
        return SenderProfileImage;
    }

    public void setSenderProfileImage(String senderProfileImage) {
        SenderProfileImage = senderProfileImage;
    }

    public String getReciverprofile() {
        return Reciverprofile;
    }

    public void setReciverprofile(String reciverprofile) {
        Reciverprofile = reciverprofile;
    }

    public String getReceiverUsername() {
        return ReceiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        ReceiverUsername = receiverUsername;
    }

    public String getReceiverFirstName() {
        return ReceiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        ReceiverFirstName = receiverFirstName;
    }

    public String getReceiverLastName() {
        return ReceiverLastName;
    }

    public void setReceiverLastName(String receiverLastName) {
        ReceiverLastName = receiverLastName;
    }

    public String getReceiverProfileImage() {
        return ReceiverProfileImage;
    }

    public void setReceiverProfileImage(String receiverProfileImage) {
        ReceiverProfileImage = receiverProfileImage;
    }

    public String getEmojidata() {
        return Emojidata;
    }

    public void setEmojidata(String emojidata) {
        Emojidata = emojidata;
    }

    public String getWishDateTime() {
        return WishDateTime;
    }

    public void setWishDateTime(String wishDateTime) {
        WishDateTime = wishDateTime;
    }



}