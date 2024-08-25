package facultyClasses;

public class Audience {

        private String senderid;
        private Integer wishId;

        public Audience(){

        }

        public Audience(String senderid, Integer wishId, String courseID, String discipline, String semesterID, String sectionID) {
            this.senderid = senderid;
            this.wishId = wishId;
            this.courseID = courseID;
            Discipline = discipline;
            this.semesterID = semesterID;
            this.sectionID = sectionID;
        }

        public String getSenderid() {
            return senderid;
        }

        public void setSenderid(String senderid) {
            this.senderid = senderid;
        }

        public Integer getWishId() {
            return wishId;
        }

        public void setWishId(Integer wishId) {
            this.wishId = wishId;
        }

        public String getCourseID() {
            return courseID;
        }

        public void setCourseID(String courseID) {
            this.courseID = courseID;
        }

        public String getDiscipline() {
            return Discipline;
        }

        public void setDiscipline(String discipline) {
            Discipline = discipline;
        }

        public String getSemesterID() {
            return semesterID;
        }

        public void setSemesterID(String semesterID) {
            this.semesterID = semesterID;
        }

        public String getSectionID() {
            return sectionID;
        }

        public void setSectionID(String sectionID) {
            this.sectionID = sectionID;
        }

        private String courseID;
        private String Discipline;
        private String semesterID;
        private String sectionID;

        // Getters and setters
        // ...
    }


