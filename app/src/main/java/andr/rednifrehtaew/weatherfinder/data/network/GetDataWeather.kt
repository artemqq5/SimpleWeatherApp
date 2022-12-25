package andr.rednifrehtaew.weatherfinder.data.network

import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import retrofit2.Response

interface GetDataWeather {

    suspend fun getWeatherData(city: String): Response<WeatherModel>

}