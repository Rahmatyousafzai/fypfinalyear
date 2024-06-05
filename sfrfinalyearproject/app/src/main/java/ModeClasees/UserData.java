package ModeClasees;

import com.google.gson.annotations.SerializedName;

public class UserData {



    @SerializedName("username")
    private String username;

    @SerializedName("FirstName")
    private String firstName;

    public UserData(String username, String firstName, String lastName, String profileImage, int sectionId, String sectionName, int semesterId, int semesterName, String courseId, String courseTitle, int programId, String programName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.programId = programId;
        this.programName = programName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public int getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(int semesterName) {
        this.semesterName = semesterName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("ProfileImage")
    private String profileImage;

    @SerializedName("Sectionid")
    private int sectionId;

    @SerializedName("SectionName")
    private String sectionName;

    @SerializedName("semesterId")
    private int semesterId;

    @SerializedName("Semestername")
    private int semesterName;

    @SerializedName("CourseId")
    private String courseId;

    @SerializedName("CourseTitle")
    private String courseTitle;

    @SerializedName("ProgramId")
    private int programId;

    @SerializedName("ProgramName")
    private String programName;

    // Getters and setters
}
