package chatClasses;

import android.os.Handler;
import android.os.Looper;

import Faculty.facultymessagebody;
import mydataapi.Apiservices;

public class PollingManager {

    private static final long POLLING_INTERVAL = 5000; // 5 seconds
    private Handler handler;
    private Runnable pollingRunnable;
    private Apiservices apiService;
    private String username;
    private String teacherUsername;
    private facultymessagebody activity;

    public PollingManager(facultymessagebody activity, Apiservices apiService, String username, String teacherUsername) {
        this.activity = activity;
        this.apiService = apiService;
        this.username = username;
        this.teacherUsername = teacherUsername;
        handler = new Handler(Looper.getMainLooper());
    }

    public void startPolling() {
        pollingRunnable = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(this, POLLING_INTERVAL);
            }
        };
        handler.post(pollingRunnable);
    }

    public void stopPolling() {
        if (pollingRunnable != null) {
            handler.removeCallbacks(pollingRunnable);
        }
    }


}
