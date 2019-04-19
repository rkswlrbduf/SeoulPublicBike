package blackstone.com.mybike.`interface`

import blackstone.com.mybike.data.BikeLocation
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/5669657651726b73313130514c677458/json/bikeList/1/1000")
    fun loadData(): Call<BikeLocation>

}