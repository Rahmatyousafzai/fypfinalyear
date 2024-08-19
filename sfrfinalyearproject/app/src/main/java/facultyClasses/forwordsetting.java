package facultyClasses;

public class forwordsetting {


     private int stid;

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public String getForworduser() {
        return forworduser;
    }

    public void setForworduser(String forworduser) {
        this.forworduser = forworduser;
    }

    public forwordsetting(int stid, String currentuser, String forworduser) {
        this.stid = stid;
        this.currentuser = currentuser;
        this.forworduser = forworduser;
    }

    private String    currentuser;
     private String        forworduser;



}
