package student;

public class Section {
   private String  sectionname;
   private String  sectionid;

    public Section(String sectionname, String sectionid) {
        this.sectionname = sectionname;
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }
}
