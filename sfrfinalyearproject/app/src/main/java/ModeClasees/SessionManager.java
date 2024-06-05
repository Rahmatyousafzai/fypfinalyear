package ModeClasees;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {




    private static final String PREF_NAME = "user_session";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // Save user session
    public void saveUserSession(String userId, String userName, String userEmail) {
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_EMAIL, userEmail);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    // Get user ID
    public String getUserId() {
        return preferences.getString(KEY_USER_ID, null);
    }

    // Get user name
    public String getUserName() {
        return preferences.getString(KEY_USER_NAME, null);
    }

    // Get user email
    public String getUserEmail() {
        return preferences.getString(KEY_USER_EMAIL, null);
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Clear user session
    public void logout() {
        editor.clear();
        editor.apply();
    }
}
