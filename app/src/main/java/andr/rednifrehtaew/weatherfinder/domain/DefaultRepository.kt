package andr.rednifrehtaew.weatherfinder.domain

import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import retrofit2.Response

interface DefaultRepository {

    suspend fun getApiData(city: String): Response<WeatherModel>

}