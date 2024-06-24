package facultyClasses;

public class MessageRecipient {
    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public void setCourse_id(Integer course_id) {
        Course_id = course_id;
    }

    public void setSendTopapolation(String sendTopapolation) {
        SendTopapolation = sendTopapolation;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }

    private Integer message_id;
    private Integer Course_id;
    private String SendTopapolation;
    private String ReceiverID;

    public MessageRecipient(Integer message_id, Integer Course_id, String SendTopapolation, String ReceiverID) {
        this.message_id = message_id;
        this.Course_id = Course_id;
        this.SendTopapolation = SendTopapolation;
        this.ReceiverID = ReceiverID;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public Integer getCourse_id() {
        return Course_id;
    }

    public String getSendTopapolation() {
        return SendTopapolation;
    }

    public String getReceiverID() {
        return ReceiverID;
    }
}
