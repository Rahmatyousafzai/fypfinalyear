package mydataapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // Base URL for the API

    private static final String BASE_URL = "http://192.168.100.9/fyp-api-versoin4/";

    private static Retrofit retrofit;
    private static Apiservices apiService;

    // Private constructor to prevent instantiation
    private RetrofitClient() {
    }

    // Singleton pattern for Retrofit instance
    public static synchronized Apiservices getInstance() {
        if (apiService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .callTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(Apiservices.class);
        }
        return apiService;
    }

    // Getter method for BASE_URL
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
