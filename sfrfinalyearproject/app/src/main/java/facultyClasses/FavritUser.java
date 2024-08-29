package facultyClasses;

public class FavritUser {
    private int ID;
    private String Usernmae;
    private String favoritID;

    public FavritUser(String usernmae, String favoritID) {
        Usernmae = usernmae;
        this.favoritID = favoritID;
    }

    public FavritUser() {
        // Default constructor needed for Retrofit
    }

    // Getters and Setters
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getUsernmae() { return Usernmae; }
    public void setUsernmae(String Usernmae) { this.Usernmae = Usernmae; }

    public String getFavoritID() { return favoritID; }
    public void setFavoritID(String favoritID) { this.favoritID = favoritID; }
}
