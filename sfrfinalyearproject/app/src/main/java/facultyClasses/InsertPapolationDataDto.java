package facultyClasses;

import java.util.List;

public class InsertPapolationDataDto {
    private Sendwish sendwish;
    private Audience audience;
    private MessageRecipient messageRecipient;

    public Sendwish getSendwish() {
        return sendwish;
    }

    public void setSendwish(Sendwish sendwish) {
        this.sendwish = sendwish;
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

    public int getSendWishId() {
        return sendWishId;
    }

    public void setSendWishId(int sendWishId) {
        this.sendWishId = sendWishId;
    }

    public List<Integer> getSelectedSemesterIds() {
        return selectedSemesterIds;
    }

    public void setSelectedSemesterIds(List<Integer> selectedSemesterIds) {
        this.selectedSemesterIds = selectedSemesterIds;
    }

    public List<Integer> getSelectedSectionIds() {
        return selectedSectionIds;
    }

    public void setSelectedSectionIds(List<Integer> selectedSectionIds) {
        this.selectedSectionIds = selectedSectionIds;
    }

    private int sendWishId;
    private List<Integer> selectedSemesterIds;
    private List<Integer> selectedSectionIds;

    public InsertPapolationDataDto(Sendwish sendwish, Audience audience, MessageRecipient messageRecipient,
                                   int sendWishId, List<Integer> selectedSemesterIds, List<Integer> selectedSectionIds) {
        this.sendwish = sendwish;
        this.audience = audience;
        this.messageRecipient = messageRecipient;
        this.sendWishId = sendWishId;
        this.selectedSemesterIds = selectedSemesterIds;
        this.selectedSectionIds = selectedSectionIds;
    }

    // Getters and setters
    // ...
}
