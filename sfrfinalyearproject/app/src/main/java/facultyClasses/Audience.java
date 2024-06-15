package facultyClasses;

public class Audience {
    private int ad_id;
    private int programid;

    public int getProgramid() {
        return programid;
    }

    public void setProgramid(int programid) {
        this.programid = programid;
    }

    private int[] semesterIds;
    private int[] sections;

    public Audience(int ad_id, int programid,int[] semesterIds, int[] sections) {
        this.ad_id = ad_id;
        this.programid=programid;
        this.semesterIds = semesterIds;
        this.sections = sections;
    }

    // Getters and setters as needed
    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int[] getSemesterIds() {
        return semesterIds;
    }

    public void setSemesterIds(int[] semesterIds) {
        this.semesterIds = semesterIds;
    }

    public int[] getSections() {
        return sections;
    }

    public void setSections(int[] sections) {
        this.sections = sections;
    }
}
