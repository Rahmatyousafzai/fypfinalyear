package ModeClasees;

import com.google.gson.annotations.SerializedName;

public class UserDataResponse {


    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @SerializedName("data")
    private UserData userData;
}
