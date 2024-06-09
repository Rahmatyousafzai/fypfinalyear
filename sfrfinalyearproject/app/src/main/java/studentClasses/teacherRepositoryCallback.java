package studentClasses;

public interface teacherRepositoryCallback {
    void onSuccess(TeacherData data);
    void onFailure(String errorMessage);

    void onFailure(Exception e);
}
