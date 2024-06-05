package ModeClasees;

public class Emoji  {

    public Emoji(String senderId, boolean sentBySender, String emojiString, int emojiID, String imagePath) {
        this.senderId = senderId;
        this.sentBySender = sentBySender;
        this.emojiString = emojiString;
        this.emojiID = emojiID;
       this.ImagePath = imagePath;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    private String senderId;
    public boolean isSentBySender() {
        return sentBySender;
    }

    public void setSentBySender(boolean sentBySender) {
        this.sentBySender = sentBySender;
    }

    private boolean sentBySender;




    public Emoji(boolean sentBySender, String emojiString, int emojiID, String imagePath) {

        this.emojiString = emojiString;
        this.emojiID = emojiID;
        ImagePath = imagePath;
    }






    private String emojiString;

    public void setEmojiString(String emojiString) {
        this.emojiString = emojiString;
    }

    public Emoji(String emojiString, int emojiID, String imagePath) {
        this.emojiString = emojiString;
        this.emojiID = emojiID;
        ImagePath = imagePath;
    }

    public Emoji(String emojiString) {
        this.emojiString = emojiString;
    }

    public String getEmojiString() {
        return emojiString;
    }


        private int emojiID;
        private String ImagePath;

        public int getEmojiID() {
            return emojiID;
        }

        public void setEmojiID(int emojiID) {
            this.emojiID = emojiID;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            this.ImagePath = imagePath;
        }

        public Emoji(int emojiID, String imagePath) {
            this.emojiID = emojiID;
            this.ImagePath = imagePath;
        }

        // Getters and setters

    }



    // Getters and setters




