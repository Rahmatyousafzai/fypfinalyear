package student;

public class Program {

    public Program(String programId, String programTitle) {
        ProgramId = programId;
        ProgramTitle = programTitle;
    }

    private String ProgramId;

    public String getProgramId() {
        return ProgramId;
    }

    public void setProgramId(String programId) {
        ProgramId = programId;
    }

    public String getProgramTitle() {
        return ProgramTitle;
    }

    public void setProgramTitle(String programTitle) {
        ProgramTitle = programTitle;
    }

    private  String  ProgramTitle;


}

