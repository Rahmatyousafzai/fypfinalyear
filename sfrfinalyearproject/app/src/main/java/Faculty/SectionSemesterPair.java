package Faculty;

import student.Section;
import student.Semester;

public class SectionSemesterPair {
    private Section section;
    private Semester semester;

    public SectionSemesterPair(Section section, Semester semester) {
        this.section = section;
        this.semester = semester;
    }

    public Section getSection() {
        return section;
    }

    public Semester getSemester() {
        return semester;
    }
}
