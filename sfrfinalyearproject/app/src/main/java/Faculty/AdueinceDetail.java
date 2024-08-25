package Faculty;

public class AdueinceDetail {

        private String ReceiverID;



        public AdueinceDetail(){


        }
    public AdueinceDetail(String receiverID, Integer adid, Integer wishID, String readdate, Integer status) {
        ReceiverID = receiverID;
        Adid = adid;
        this.wishID = wishID;
        Readdate = readdate;
        this.status = status;
    }

    public String getReceiverID() {
        return ReceiverID;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }

    public Integer getAdid() {
        return Adid;
    }

    public void setAdid(Integer adid) {
        Adid = adid;
    }

    public Integer getWishID() {
        return wishID;
    }

    public void setWishID(Integer wishID) {
        this.wishID = wishID;
    }

    public String getReaddate() {
        return Readdate;
    }

    public void setReaddate(String readdate) {
        Readdate = readdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer Adid;
        private Integer wishID;
        private String Readdate;
        private Integer status;

        // Getters and setters
        // ...
    }


