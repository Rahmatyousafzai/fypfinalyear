package studentClasses;

import android.util.Log;

public class TeacherDataSingleton implements teacherRepository.teacherRepositoryCallback{
    private static TeacherDataSingleton instance;
    private TeacherData userData;
    private String username;

    private TeacherDataSingleton() {
        // Private constructor to prevent instantiation
    }

    public static synchronized TeacherDataSingleton getInstance() {
        if (instance == null) {
            instance = new TeacherDataSingleton();
        }
        return instance;
    }

    public TeacherData getUserData() {
        return userData;
    }

    public void setUserData(TeacherData userData) {
        this.userData = userData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (this.username == null) { // Set only if it's not already set
            this.username = username;
            // Fetch user data when username is set
            fetchAndStoreUserData();
        }
    }

    private void fetchAndStoreUserData() {
        teacherRepository userRepository = new teacherRepository();
        userRepository.fetchTeacherData(username,this);
    }

    @Override
    public void onSuccess(TeacherData data) {
        userData = data;

        // Log the retrieved data
        Log.d("TeacherDataSingleton", "Fetched program name: " + data.getFirstName());
        Log.d("TeacherDataSingleton", "Fetched section name: " + data.getLastName());
        Log.d("TeacherDataSingleton", "Fetched program name: " + data.getProfileImage());
        Log.d("TeacherDataSingleton", "Fetched section name: " + data.getDisgnation());



        // Access user data fields
        String programName = data.getProgramName();
        String sectionName = data.getSectionName();

        // Log user data
        Log.e("TeacherDataSingleton", "Program Name: " + programName);
        Log.e("TeacherDataSingleton", "Section Name: " + sectionName);

        // Optionally, update UI or notify listeners
    }





    @Override
    public void onFailure(Exception e) {
        Log.e("TeacherDataSingleton", "Error fetching user data: " + e.getMessage());
        // Handle error case, e.g., show a toast or an error message
    }
}
