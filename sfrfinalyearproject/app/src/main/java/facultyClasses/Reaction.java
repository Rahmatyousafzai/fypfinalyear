package facultyClasses;

public class Reaction {
    private String reacterID;
    private int sw_id;
    private int emojiID;

    public Reaction(String reacterID, int sw_id, int emojiID) {
        this.reacterID = reacterID;
        this.sw_id = sw_id;
        this.emojiID = emojiID;
    }

    // Getters and setters
    public String getReacterID() {
        return reacterID;
    }

    public void setReacterID(String reacterID) {
        this.reacterID = reacterID;
    }

    public int getSw_id() {
        return sw_id;
    }

    public void setSw_id(int sw_id) {
        this.sw_id = sw_id;
    }

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }
}
