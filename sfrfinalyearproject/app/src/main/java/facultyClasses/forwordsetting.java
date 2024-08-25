package facultyClasses;

public class forwordsetting {


     private int stid;



     private String Fname;

    public String getFname() {
        return Fname;
    }

    public forwordsetting(String currentuser, String forworduser) {
        this.currentuser = currentuser;
        this.forworduser = forworduser;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    private String lname;
    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public forwordsetting(int stid, String forworduser) {
        this.stid = stid;
        this.forworduser = forworduser;
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
