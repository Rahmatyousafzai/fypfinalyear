package mydataapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    // Base URL for the API
    private static final String BASE_URL = "http://192.168.43.94/fyp-api-versoin4/";
    private static Retrofit retrofit;
    private static Apiservices apiService;


    // Getter method for BASE_URL
    public static String getBaseUrl() {
        return BASE_URL;
    }
    // Private constructor to prevent instantiation
    private RetrofitClient() {
    }

    // Singleton pattern for Retrofit instance
    public static synchronized Apiservices getInstance() {
        if (apiService == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(Apiservices.class);
        }
        return apiService;
    }
}
