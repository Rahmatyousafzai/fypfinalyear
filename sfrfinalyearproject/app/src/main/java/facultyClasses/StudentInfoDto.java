package facultyClasses;

public class StudentInfoDto {



    private String username;
    private String fname;

    public StudentInfoDto(String username, String fname, String lname, String session, String semesterNO, String deportment, String section) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.session = session;
        this.semesterNO = semesterNO;
        this.deportment = deportment;
        this.section = section;
    }

    private String lname;
    private String session;
    private String semesterNO;
    private String deportment;
    private String section;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSemesterNO() {
        return semesterNO;
    }

    public void setSemesterNO(String semesterNO) {
        this.semesterNO = semesterNO;
    }

    public String getDeportment() {
        return deportment;
    }

    public void setDeportment(String deportment) {
        this.deportment = deportment;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
