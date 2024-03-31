package Class_for_admin;

public class sectionofstudent {


    private String username;
    private String name;
    private String email;

    public sectionofstudent(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
