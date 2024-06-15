package facultyClasses;

public class InsertPapolationDataDto {
    private Sendwish sendWish;
    private Audience audience;
    private MessageRecipient messageRecipient;
    private int programId;
    private int[] semesterIds;
    private int[] sections;

    public InsertPapolationDataDto(Sendwish sendWish, Audience audience, MessageRecipient messageRecipient,
                                   int programId, int[] semesterIds, int[] sections) {
        this.sendWish = sendWish;
        this.audience = audience;
        this.messageRecipient = messageRecipient;
        this.programId = programId;
        this.semesterIds = semesterIds;
        this.sections = sections;
    }

    // Getters and setters as needed
    public Sendwish getSendWish() {
        return sendWish;
    }

    public void setSendWish(Sendwish sendWish) {
        this.sendWish = sendWish;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public MessageRecipient getMessageRecipient() {
        return messageRecipient;
    }

    public void setMessageRecipient(MessageRecipient messageRecipient) {
        this.messageRecipient = messageRecipient;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
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
