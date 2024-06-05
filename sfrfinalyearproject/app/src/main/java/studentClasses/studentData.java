package studentClasses;

import java.util.List;

public class studentData {
    private String Username;
    private String FirstName;
    private String LastName;
    private String ProfileImage;
    private int SectionId;
    private String SectionName;
    private int SemesterId;
    private int SemesterName;
    private int ProgramId;
    private String ProgramName;
    private List<Course> Courses;

    // Getters and Setters

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public int getSectionId() {
        return SectionId;
    }

    public void setSectionId(int sectionId) {
        SectionId = sectionId;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public int getSemesterId() {
        return SemesterId;
    }

    public void setSemesterId(int semesterId) {
        SemesterId = semesterId;
    }

    public int getSemesterName() {
        return SemesterName;
    }

    public void setSemesterName(int semesterName) {
        SemesterName = semesterName;
    }

    public int getProgramId() {
        return ProgramId;
    }

    public void setProgramId(int programId) {
        ProgramId = programId;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public List<Course> getCourses() {
        return Courses;
    }

    public void setCourses(List<Course> courses) {
        Courses = courses;
    }

    public static class Course {
        private String CourseId;
        private String CourseTitle;

        public String getCourseId() {
            return CourseId;
        }

        public void setCourseId(String courseId) {
            CourseId = courseId;
        }

        public String getCourseTitle() {
            return CourseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            CourseTitle = courseTitle;
        }
    }
}
