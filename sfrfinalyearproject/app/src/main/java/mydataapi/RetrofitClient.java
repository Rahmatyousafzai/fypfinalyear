package mydataapi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.250/fyp_api_versoin3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;









    }

}
