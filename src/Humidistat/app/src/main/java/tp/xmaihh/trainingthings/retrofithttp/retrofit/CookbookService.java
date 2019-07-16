package tp.xmaihh.trainingthings.retrofithttp.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tp.xmaihh.trainingthings.retrofithttp.bean.CookBook;

public interface CookbookService {
    /**
     * http://apis.juhe.cn/cook/query?key=0a4459b302a62181ab02f95294c07d5e&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&pn=3
     *
     * @param apikey
     * @param menu
     * @param rn
     * @param pn
     * @return
     */

    @POST("cook/query?key=0a4459b302a62181ab02f95294c07d5e")
    @FormUrlEncoded
    Observable<CookBook> getCookBookInfo(
            @Field("key") String apikey,
            @Field("menu") String menu,
            @Field("rn") String rn,
            @Field("pn") String pn
    );
}
