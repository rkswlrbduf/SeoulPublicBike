package blackstone.com.mybike.util

import blackstone.com.mybike.`interface`.RetrofitInterface
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_URL =
    "http://openapi.seoul.go.kr:8088"

class NetWorkUtil {

    companion object {
        fun getRetro(): RetrofitInterface {
            val retrofit = Retrofit.Builder()
            val gson = GsonBuilder().setLenient().create()
            return retrofit.baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RetrofitInterface::class.java)
        }
    }

}