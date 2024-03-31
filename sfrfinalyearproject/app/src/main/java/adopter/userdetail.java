package adopter;

public class userdetail {

    public String fname ;
    public String lname ;
    private boolean favorite;

    public int profileimage;

    public userdetail( String fname,  int profileimage, boolean favorite) {

        this.fname = fname;

        this.profileimage = profileimage;
        this.favorite = favorite;
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


    public int getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(int profileimage) {
        this.profileimage = profileimage;
    }





    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
