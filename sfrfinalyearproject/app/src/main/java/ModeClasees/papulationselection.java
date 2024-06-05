package ModeClasees;

public class papulationselection {

    private int SemesterNo;

    public int getSemesterNo() {
        return SemesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        SemesterNo = semesterNo;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSectionid() {
        return Sectionid;
    }

    public void setSectionid(int sectionid) {
        Sectionid = sectionid;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public String getProgramTitle() {
        return ProgramTitle;
    }

    public void setProgramTitle(String programTitle) {
        ProgramTitle = programTitle;
    }

    private int semesterId;

    public papulationselection(int semesterNo, int semesterId, int sectionid, String sectionName, String courseTitle, int programID, String programTitle) {
        SemesterNo = semesterNo;
        this.semesterId = semesterId;
        Sectionid = sectionid;
        SectionName = sectionName;
        this.courseTitle = courseTitle;
        this.programID = programID;
        ProgramTitle = programTitle;
    }

    private int Sectionid;
    private String SectionName;
    private String courseTitle;
    private int programID;
    private String ProgramTitle;


}
