package facultyClasses;

import java.util.List;

public class postpapolation{



 private String senderId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReciverID() {
        return ReciverID;
    }

    public void setReciverID(String reciverID) {
        ReciverID = reciverID;
    }

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }

    private  String           ReciverID;
  private int          emojiID;

    public postpapolation(String senderId, String reciverID, int emojiID) {
        this.senderId = senderId;
        ReciverID = reciverID;
        this.emojiID = emojiID;
    }

    private String senderid;
        private String content;




    public List<Audience> getAudience() {
        return audience;
    }

    public void setAudience(List<Audience> audience) {
        this.audience = audience;
    }

    private List<Audience> audience;

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEsid() {
        return esid;
    }

    public void setEsid(String esid) {
        this.esid = esid;
    }

    public List<Audience> getAudienceList() {
        return audienceList;
    }

    public void setAudienceList(List<Audience> audienceList) {
        this.audienceList = audienceList;
    }

    private String esid;
        private List<Audience> audienceList;




        // Constructors
        public postpapolation() {}

        public postpapolation(String senderid, String content, String esid, List<Audience> audienceList) {
            this.senderid = senderid;
            this.content = content;
            this.esid = esid;
            this.audienceList = audienceList;
        }

        // Getters and setters
        // ...
    }


