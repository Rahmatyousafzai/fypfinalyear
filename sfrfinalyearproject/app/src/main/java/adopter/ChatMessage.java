package adopter;
public class ChatMessage {
    private String Username;
    private String fname;
    private String lname;
    private String profileimage;
    private int MessageId;
    private String Content;
    private Integer SectionId;
    private String CourseId;
    private Integer Discipline;
    private Integer SemesterId;
    private String date;
    private String SendTopapolation;

    // Getters and Setters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int messageId) {
        MessageId = messageId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getSectionId() {
        return SectionId;
    }

    public void setSectionId(Integer sectionId) {
        SectionId = sectionId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public Integer getDiscipline() {
        return Discipline;
    }

    public void setDiscipline(Integer discipline) {
        Discipline = discipline;
    }

    public Integer getSemesterId() {
        return SemesterId;
    }

    public void setSemesterId(Integer semesterId) {
        SemesterId = semesterId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSendTopapolation() {
        return SendTopapolation;
    }

    public void setSendTopapolation(String sendTopapolation) {
        SendTopapolation = sendTopapolation;
    }
}

