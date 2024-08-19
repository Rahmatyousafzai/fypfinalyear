package chatClasses;

public class MessageResponse {


        private String SenderUsername;
        private String SenderFirstName;
        private String SenderLastName;
        private String SenderProfileImage;
        private String Receiverprofile;
        private String ReceiverUsername;
        private String ReceiverFirstName;
        private String ReceiverLastName;
        private String ReceiverProfileImage;
        private String Emojidata;
        private String WishDateTime;

    public MessageResponse(String senderUsername, String senderFirstName, String senderLastName, String senderProfileImage, String receiverprofile, String receiverUsername, String receiverFirstName, String receiverLastName, String receiverProfileImage, String emojidata, String wishDateTime, String wishcontent) {
        SenderUsername = senderUsername;
        SenderFirstName = senderFirstName;
        SenderLastName = senderLastName;
        SenderProfileImage = senderProfileImage;
        Receiverprofile = receiverprofile;
        ReceiverUsername = receiverUsername;
        ReceiverFirstName = receiverFirstName;
        ReceiverLastName = receiverLastName;
        ReceiverProfileImage = receiverProfileImage;
        Emojidata = emojidata;
        WishDateTime = wishDateTime;
        this.wishcontent = wishcontent;
    }

    public String getWishcontent() {
        return wishcontent;
    }

    public void setWishcontent(String wishcontent) {
        this.wishcontent = wishcontent;
    }

    private String wishcontent;

        // Getters and setters
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

        public String getReceiverprofile() {
            return Receiverprofile;
        }

        public void setReceiverprofile(String receiverprofile) {
            Receiverprofile = receiverprofile;
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


