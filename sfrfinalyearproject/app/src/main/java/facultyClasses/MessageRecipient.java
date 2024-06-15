package facultyClasses;

public class MessageRecipient {
    private int message_id;

    public MessageRecipient(int message_id, String senderId, int audienceID) {
        this.message_id = message_id;
        this.senderId = senderId;
        this.audienceID = audienceID;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    private  String senderId;
    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getAudienceID() {
        return audienceID;
    }

    public void setAudienceID(int audienceID) {
        this.audienceID = audienceID;
    }

    private int audienceID;

    public MessageRecipient(int message_id, int audienceID) {
        this.message_id = message_id;
        this.audienceID = audienceID;
    }

    // Getters and setters
}