package studentClasses;

import android.util.Log;

public class UserDataSingleton implements UserRepository.UserRepositoryCallback {
    private static UserDataSingleton instance;
    private studentData userData;
    private String username;

    private UserDataSingleton() {
        // Private constructor to prevent instantiation
    }

    public static synchronized UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    public studentData getUserData() {
        return userData;
    }

    public void setUserData(studentData userData) {
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
        UserRepository userRepository = new UserRepository();
        userRepository.fetchUserData(username, this);
    }

    @Override
    public void onSuccess(studentData data) {
        userData = data;

        // Log the retrieved data
        Log.d("UserDataSingleton", "Fetched program name: " + data.getProgramName());
        Log.d("UserDataSingleton", "Fetched section name: " + data.getSectionName());

        // Access user data fields
        String programName = data.getProgramName();
        String sectionName = data.getSectionName();

        // Log user data
        Log.e("UserDataSingleton", "Program Name: " + programName);
        Log.e("UserDataSingleton", "Section Name: " + sectionName);

        // Optionally, update UI or notify listeners
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("UserDataSingleton", "Error fetching user data: " + e.getMessage());
        // Handle error case, e.g., show a toast or an error message
    }
}
