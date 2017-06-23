package test.mvptestapp.model.retrofit;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by user on 23.06.2017.
 */

public class Repository {

    public static APIStackOverFlow getRepository(){
        String apiUrl = "https://api.stackexchange.com/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIStackOverFlow.class);
    }
}
