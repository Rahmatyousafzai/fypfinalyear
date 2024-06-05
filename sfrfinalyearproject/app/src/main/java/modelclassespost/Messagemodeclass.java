package modelclassespost;

public class Messagemodeclass {

        private String Username;
        private int MessageId;
        private String Content;
        private Integer SectionId;
        private String CourseId;
        private Integer Discipline;
        private Integer SemesterId;
        private String SendTopapolation;

        public Messagemodeclass (String Username, int MessageId, String Content, Integer SectionId, String CourseId, Integer Discipline, Integer SemesterId, String SendTopapolation) {
            this.Username = Username;
            this.MessageId = MessageId;
            this.Content = Content;
            this.SectionId = SectionId;
            this.CourseId = CourseId;
            this.Discipline = Discipline;
            this.SemesterId = SemesterId;
            this.SendTopapolation = SendTopapolation;
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
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

        public String getSendTopapolation() {
            return SendTopapolation;
        }

        public void setSendTopapolation(String sendTopapolation) {
            SendTopapolation = sendTopapolation;
        }
    }


