package facultyClasses;

import com.google.gson.annotations.SerializedName;

public class Course {


    @SerializedName("SemesterNo")
    private int semesterNo;

    @SerializedName("semesterId")
    private int semesterId;

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    @SerializedName("Sectionid")
    private int sectionId;

    public Course(int semesterNo, int semesterId, int sectionId, String sectionName, String courseTitle, int programId, String programTitle) {
        this.semesterNo = semesterNo;
        this.semesterId = semesterId;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.courseTitle = courseTitle;
        this.programId = programId;
        this.programTitle = programTitle;
    }

    @SerializedName("SectionName")
    private String sectionName;

    @SerializedName("courseTitle")
    private String courseTitle;

    @SerializedName("programID")
    private int programId;

    @SerializedName("ProgramTitle")
    private String programTitle;




}

