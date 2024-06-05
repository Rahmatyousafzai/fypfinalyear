package modelclassespost;

import com.google.gson.annotations.SerializedName;

public class SendWishRequestDto {
    @SerializedName("SenderId")
    private String senderId;

    @SerializedName("Content")
    private String content;

    @SerializedName("TemplateId")
    private Integer templateId;

    @SerializedName("MessageType")
    private String messageType;

    @SerializedName("EmojiId")
    private Integer emojiId;

    public SendWishRequestDto(String senderId, String content, Integer templateId, String messageType, Integer emojiId, Integer achievId, String dateTime, String esid, boolean isEmail, String enrollmentId, String receiverID, String sectionId, String semesterId, String discipline, String courseId, String papulation) {
        this.senderId = senderId;
        this.content = content;
        this.templateId = templateId;
        this.messageType = messageType;
        this.emojiId = emojiId;
        this.achievId = achievId;
        this.dateTime = dateTime;
        this.esid = esid;
        this.isEmail = isEmail;
        this.enrollmentId = enrollmentId;
        this.receiverID = receiverID;
        this.sectionId = sectionId;
        this.semesterId = semesterId;
        this.discipline = discipline;
        this.courseId = courseId;
        this.papulation = papulation;
    }

    @SerializedName("AchievId")
    private Integer achievId;

    @SerializedName("DateTime")
    private String dateTime;

    @SerializedName("Esid")
    private String esid;

    @SerializedName("IsEmail")
    private boolean isEmail;

    @SerializedName("EnrollmentId")
    private String enrollmentId;

    public SendWishRequestDto(String senderId, String content, Integer templateId, String messageType, Integer emojiId, Integer achievId, String dateTime, Boolean isEmail, String enrollmentId, String receiverId, String sectionId, String semesterId, String discipline, String courseId, String papulation) {
        this.senderId = senderId;
        this.content = content;
        this.templateId = templateId;
        this.messageType = messageType;
        this.emojiId = emojiId;
        this.achievId = achievId;
        this.dateTime = dateTime;
        this.isEmail = isEmail;
        this.enrollmentId = enrollmentId;
        this.receiverID = receiverId;
        this.sectionId = sectionId;
        this.semesterId = semesterId;
        this.discipline = discipline;
        this.courseId = courseId;
        this.papulation = papulation;
    }


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Integer getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(Integer emojiId) {
        this.emojiId = emojiId;
    }

    public Integer getAchievId() {
        return achievId;
    }

    public void setAchievId(Integer achievId) {
        this.achievId = achievId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEsid() {
        return esid;
    }

    public void setEsid(String esid) {
        this.esid = esid;
    }

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPapulation() {
        return papulation;
    }

    public void setPapulation(String papulation) {
        this.papulation = papulation;
    }

    @SerializedName("ReceiverID")
    private String receiverID;

    @SerializedName("SectionId")
    private String sectionId;

    @SerializedName("SemesterId")
    private String semesterId;

    @SerializedName("Discipline")
    private String discipline;

    @SerializedName("CourseId")
    private String courseId;

    @SerializedName("Papulation")
    private String papulation;

    public void setIsEmail(boolean b) {
    }

    // Getters and setters
}
