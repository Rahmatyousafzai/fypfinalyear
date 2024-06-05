package modelclassespost;

import com.google.gson.annotations.SerializedName;

public class SendWishResponse {
    @SerializedName("SendWishId")
    private int sendWishId;

    // Getter and setter
    public int getSendWishId() {
        return sendWishId;
    }

    public void setSendWishId(int sendWishId) {
        this.sendWishId = sendWishId;
    }


    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public SendWishResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


}
