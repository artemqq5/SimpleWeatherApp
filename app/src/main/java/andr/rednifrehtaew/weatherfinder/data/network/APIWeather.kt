package andr.rednifrehtaew.weatherfinder.data.network

import andr.rednifrehtaew.weatherfinder.BuildConfig
import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIWeather {

    @GET("current.json?key=${BuildConfig.API_KEY}")
    suspend fun getWeatherData(@Query("q") city: String): Response<WeatherModel>

}