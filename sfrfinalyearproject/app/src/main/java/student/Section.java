package student;

public class Section {
   private int sectionid;

    public Section(int sectionid, String sectionname) {
        this.sectionid = sectionid;
        this.sectionname = sectionname;
    }

    private String sectionname;

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }
}
