package adopter;

import java.util.List;

public class ChatMessagesResponse {


    private String message;
    private List<ChatMessage> data;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatMessage> getData() {
        return data;
    }

    public void setData(List<ChatMessage> data) {
        this.data = data;
    }
}
