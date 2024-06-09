package studentClasses;

public class TeacherResponse {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TeacherData getData() {
        return data;
    }

    public void setData(TeacherData data) {
        this.data = data;
    }

    private String message;
    private TeacherData data;

    // Getters and Setters
}
