package facultyClasses;

public class Sendwish {
    private int sw_id;

    private String sw_content;

    public Sendwish( String sw_content, String senderId) {
        this.sw_id = sw_id;
        this.sw_content = sw_content;
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    private String senderId;

    public Sendwish(String sw_content) {
        this.sw_content = sw_content;
    }

    public Sendwish(int sw_id, String sw_content) {
        this.sw_id = sw_id;
        this.sw_content = sw_content;
    }

    // Add getters and setters as needed
    public int getSw_id() {
        return sw_id;
    }

    public void setSw_id(int sw_id) {
        this.sw_id = sw_id;
    }

    public String getSw_content() {
        return sw_content;
    }

    public void setSw_content(String sw_content) {
        this.sw_content = sw_content;
    }
}
