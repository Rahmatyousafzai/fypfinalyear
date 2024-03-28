package adopter;

import java.util.Date;

public class userdetail {
    public String username;
    public String password;
    public String fname ;
    public String lname ;

    public Date DOB;
    public String profileimage;
    public String email;
    public String usertype;

    public userdetail(String username, String password, String fname, String lname, Date DOB, String profileimage, String email, String usertype) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.DOB = DOB;
        this.profileimage = profileimage;
        this.email = email;
        this.usertype = usertype;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
