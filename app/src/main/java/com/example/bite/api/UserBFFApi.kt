import com.example.bite.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserBFFApi {
    @GET("/restaurant")
    fun getRestaurants(): Call<List<Restaurant>>;
}