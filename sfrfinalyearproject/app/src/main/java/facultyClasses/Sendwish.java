package facultyClasses;

import java.util.ArrayList;

public class Sendwish {
    private String content;
    private String username;
    private String programId;
    private String dateTime;
    private String sectionId;
    private String semesterId;
    private String isEmail;
    private ArrayList<Integer> selectedSemesterIds;
    private ArrayList<Integer> selectedSectionIds;

    public Sendwish(String content, String username, String programId, String dateTime,
                    String sectionId, String semesterId, String isEmail,
                    ArrayList<Integer> selectedSemesterIds, ArrayList<Integer> selectedSectionIds) {
        this.content = content;
        this.username = username;
        this.programId = programId;
        this.dateTime = dateTime;
        this.sectionId = sectionId;
        this.semesterId = semesterId;
        this.isEmail = isEmail;
        this.selectedSemesterIds = selectedSemesterIds;
        this.selectedSectionIds = selectedSectionIds;
    }

    // Getters and setters if needed
}
