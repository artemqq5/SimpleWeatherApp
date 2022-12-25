package andr.rednifrehtaew.weatherfinder.data.network

import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//import retrofit2.converter.gson.GsonConverterFactory

class GetDataWeatherImp : GetDataWeather {

    val apiInstance = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi
                    .Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(APIWeather::class.java)

    override suspend fun getWeatherData(city: String): Response<WeatherModel> {
        return apiInstance.getWeatherData(city)
    }


}