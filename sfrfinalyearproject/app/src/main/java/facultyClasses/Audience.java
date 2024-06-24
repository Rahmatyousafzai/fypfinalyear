package facultyClasses;

public class Audience {
    private int program_id;

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    private int semesterID;

    public Audience(int program_id, int semesterID, int sectionId, String courseid) {
        this.program_id = program_id;
        this.semesterID = semesterID;
        this.sectionId = sectionId;
        this.courseid = courseid;
    }

    private int sectionId;
    private String courseid;
}
