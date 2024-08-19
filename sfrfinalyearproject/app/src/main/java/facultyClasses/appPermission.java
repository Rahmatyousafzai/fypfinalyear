package facultyClasses;

public class appPermission {


    private int atid;
    private String username;

    public appPermission( String username) {

        this.username = username;
    }

    public int getAtid() {
        return atid;
    }

    public void setAtid(int atid) {
        this.atid = atid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
