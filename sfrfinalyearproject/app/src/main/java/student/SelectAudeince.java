package student;

public class SelectAudeince {
    private  int alocationid;

    private String senderId;

    public SelectAudeince(String senderId, String allocationIds, String wishMessage) {
        this.senderId = senderId;
        this.allocationIds = allocationIds;
        this.wishMessage = wishMessage;
    }

    public SelectAudeince(String senderId, String wishMessage) {
        this.senderId = senderId;
        this.wishMessage = wishMessage;
    }

    private String allocationIds;
    private String wishMessage;

  private int  TaId;

    public int getAlocationid() {
        return alocationid;
    }

    public void setAlocationid(int alocationid) {
        this.alocationid = alocationid;
    }

    public int getTaId() {
        return TaId;
    }

    public void setTaId(int taId) {
        TaId = taId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;










}
