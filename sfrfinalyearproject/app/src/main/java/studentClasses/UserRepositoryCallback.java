package studentClasses;

public interface UserRepositoryCallback {
    void onSuccess(studentData data);
    void onFailure(String errorMessage);
}
