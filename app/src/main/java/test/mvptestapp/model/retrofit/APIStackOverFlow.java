package test.mvptestapp.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIStackOverFlow {
    @GET("/2.2/search/advanced?order=desc&sort=activity&site=stackoverflow")
    Observable<AnswerModel> getAnswers(@Query("q") String query, @Query("page") int page);
}
